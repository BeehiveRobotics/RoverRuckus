package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Motors.Motor
import org.BeehiveRobotics.Library.Servos.Servo
import org.BeehiveRobotics.Library.Sensors.REVTouchSensor
import com.qualcomm.robotcore.util.ElapsedTime
import org.BeehiveRobotics.Library.Systems.RobotSystem

class RelicRecoveryForklift(private val opMode: BROpMode): RobotSystem(opMode) {
    private val motor: Motor = Motor(opMode, "flm")
    private val rightClaw: Servo = Servo(opMode, "flrc")
    private val leftClaw: Servo = Servo(opMode, "fllc")
    private val topButton: REVTouchSensor = REVTouchSensor(opMode, "fltb")
    private val bottomButton: REVTouchSensor = REVTouchSensor(opMode, "flbb")
    private var motorSpeed: Double = 0.0
    private final val CLAW_GRAB_POSITION: Double = 0.525
    private final val CLAW_PUSH_IN_BLOCK_POSITION: Double = 0.85
    private final val CLAW_OPEN_POSITION: Double = 0.0
    private final val CLAW_OPEN_ALL_THE_WAY_POSITION: Double = 0.0
    private final val CLAW_CLOSE_POSITION: Double = 0.0
    private var task: Tasks = Tasks.Stop
    
    init {
        rightClaw.setDirection(com.qualcomm.robotcore.hardware.Servo.Direction.REVERSE)
    }
    
    enum class Tasks {
        MoveUntilDown, MoveUntilUp, Stop
    }

    fun init() {
        openClaw()
        moveUntilDown()
    }

    fun autoInit() {
        openClaw()
        init()
        closeClaw()
        sleep(350)
        moveMotor(1.0, 200)
    }

    fun closeClaw() {
        setClawPosition(CLAW_GRAB_POSITION)
    }

    fun openClaw() {
        setClawPosition(CLAW_OPEN_POSITION)
    }

    fun setClawPositionPushInBlock() {
        setClawPosition(CLAW_PUSH_IN_BLOCK_POSITION)
    }

    fun openAllTheWay() {
        setClawPosition(CLAW_OPEN_ALL_THE_WAY_POSITION)
    }

    fun closeAllTheWay() {
        setClawPosition(CLAW_CLOSE_POSITION)
    }

    fun moveMotor(speed: Double) {
        var speed: Double = speed
        if(speed < 0.0 && bottomButton.isPressed()) {
            speed = 0.0
        }
        if(speed > 0.0 && topButton.isPressed()) {
            speed = 0.0
        }
        if(!opMode.opModeIsActive()) {
            speed = 0.0
        }
        this.motorSpeed = speed
        motor.rawPower = speed
    }
    
    private fun moveMotor(speed: Double, safety: Boolean) {
        if(!safety) {
            motor.rawPower = speed
        } else {
            moveMotor(speed)
        }
    }
    
    private fun setClawPosition(position: Double) {
        rightClaw.setPosition(position)
        leftClaw.setPosition(position)
    }
    
    fun moveMotor(speed: Double, milliseconds: Long) {
        val runTime: ElapsedTime = ElapsedTime()
        runTime.reset()
        while(runTime.milliseconds() < milliseconds) {
            moveMotor(speed)
        }
        stop()
    }
    
    fun moveUntilDown(speed: Double = 1.0, waitforCompletion: Boolean = true) {
        this.motorSpeed = speed
        if(!waitforCompletion) {
            task = Tasks.MoveUntilDown
            val thread: Thread = Thread(this)
            thread.start()
            return
        }
        var calculatedSpeed = -Math.abs(speed)
        while(!bottomButton.isPressed()) {
            if(!opMode.opModeIsActive()) {
                stop()
                return
            }
            moveMotor(calculatedSpeed)
            if(topButton.isPressed()) {
                calculatedSpeed *= -1.0
                moveMotor(calculatedSpeed, false)
                while(topButton.isPressed()) {
                    if(!opMode.opModeIsActive()) {
                       stop()
                        return
                    }
                    sleep(200)
                }
            }
        }
        stop()
    }
    
    fun moveUntilUp (speed: Double = 1.0, runOnOtherThread: Boolean = false) {
        this.motorSpeed = speed
        if(runOnOtherThread) {
            task = Tasks.MoveUntilUp
            val thread: Thread = Thread(this)
            thread.start()
            return
        }
        while(!topButton.isPressed()) {
            moveMotor(Math.abs(speed))
        }
        stop()
    }
    
    fun stop() {
        motor.rawPower = 0.0
    }
        
    override fun run() {
        when(task) {
            Tasks.Stop -> stop()
            Tasks.MoveUntilDown -> moveUntilDown(motorSpeed, false)
            Tasks.MoveUntilUp -> moveUntilUp(motorSpeed, false)
        }
    }
}
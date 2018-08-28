package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Motors.Motor
import org.BeehiveRobotics.Library.Servos.Servo
import org.BeehiveRobotics.Library.Sensors.REVTouchSensor
import com.qualcomm.robotcore.util.ElapsedTime
import org.BeehiveRobotics.Library.Systems.RobotSystem

class RelicRecoveryForklift(private val opMode: BROpMode): RobotSystem(opMode), Runnable {
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
    private var milliseconds = 0.0
    
    init {
        rightClaw.direction = com.qualcomm.robotcore.hardware.Servo.Direction.REVERSE
    }
    
    enum class Tasks {
        MoveUntilDown, MoveUntilUp, Stop, AutoInit, MoveMotor
    }

    fun init() {
        openClaw()
        moveUntilDown()
    }

    fun autoInit(waitforCompletion: Boolean = true) {
        if(!waitforCompletion) {
            task = Tasks.AutoInit
            val thread = Thread(this)
            thread.start()
        }
        openClaw()
        init()
        closeClaw()
        sleep(350)
        moveMotor(1.0, 200.0)
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
        var calculated_speed: Double = speed
        if(speed < 0.0 && bottomButton.isPressed()) {
            calculated_speed = 0.0
        }
        if(speed > 0.0 && topButton.isPressed()) {
            calculated_speed = 0.0
        }
        if(!opMode.opModeIsActive()) {
            calculated_speed = 0.0
        }
        this.motorSpeed = speed
        motor.rawPower = calculated_speed
    }
    
    private fun moveMotor(speed: Double, safety: Boolean) {
        if(!safety) {
            motor.rawPower = speed
        } else {
            moveMotor(speed)
        }
    }
    
    private fun setClawPosition(position: Double) {
        rightClaw.position = position
        leftClaw.position = position
    }
    
    fun moveMotor(speed: Double, milliseconds: Double, waitForCompletion: Boolean = true) {
        this.motorSpeed = speed
        this.milliseconds = milliseconds
        if(!waitForCompletion) {
            task = Tasks.MoveMotor
            val thread = Thread(this)
            thread.start()
        }
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
    
    fun moveUntilUp (speed: Double = 1.0, waitforCompletion: Boolean = true) {
        this.motorSpeed = speed
        if(!waitforCompletion) {
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
            Tasks.AutoInit -> autoInit()
            Tasks.MoveMotor -> moveMotor(motorSpeed, milliseconds)
        }
    }
}
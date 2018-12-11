package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Motors.Motor
import org.BeehiveRobotics.Library.Servos.Servo
import com.qualcomm.robotcore.hardware.Servo.Direction

class Gathering(private val opMode: BROpMode): RobotSystem(opMode), Runnable {
    private val gatherMotor = Motor(opMode, "ggm")
    val inOutMotor = Motor(opMode, "giom")
    val rightServo = Servo(opMode, "grs")
    val leftServo = Servo(opMode, "gls")
    var UP_POSITION = 0.24
    private val DOWN_POSITION = 1.0
    private val IN_BETWEEN_POSITION = 0.5
    var gatherMotorSpeed = 0.0
    var isOn = false
    var isUp = true
    enum class Tasks {
        DUMP, NONE
    }
    private var task = Tasks.NONE

    override fun start() {
        val thread = Thread(this)
        thread.start()
    }

    override fun init() {
        rightServo.direction = Direction.REVERSE
    }

    fun toggleFlip() {
        if(isUp) {
            down()
        } else {
            up()
        }
    }

    fun down() {
        isUp = false
        setServoPositions(DOWN_POSITION)
    }

    fun dump() {
        task = Tasks.DUMP
    }

    fun up() {
        off()
        isUp = true
        setServoPositions(UP_POSITION)
    }
    
    fun inBetween() {
        off()
        isUp = false
        setServoPositions(IN_BETWEEN_POSITION)
    }

    fun toggleGathering() {
        if(isOn) {
            off()
        } else {
            on()
        }
    }

    fun on() {
        isOn = true
        gatherMotorSpeed = 1.0
    }

    fun off() {
        isOn = false
        gatherMotorSpeed = 0.0
    }
    fun gatherBackwards() {
        gatherMotorSpeed = -1.0
    }

    private fun setServoPositions(position: Double) {
        rightServo.position = position
        leftServo.position = position
    }

    override fun toString(): String =
        gatherMotor.toString() + 
        inOutMotor.toString() + if(isUp) "Currently Up" else "Currently down"
        
    override fun run() {
        while(opMode.opModeIsActive()) {
            if(isUp) gatherMotorSpeed = 0.0
            gatherMotor.rawPower = gatherMotorSpeed
            when(task) {
                Tasks.DUMP -> {
                    isUp = true
                    setServoPositions(UP_POSITION)
                    sleep(1000)
                    inBetween()
                    task = Tasks.NONE
                }
            }
        }
    }

}
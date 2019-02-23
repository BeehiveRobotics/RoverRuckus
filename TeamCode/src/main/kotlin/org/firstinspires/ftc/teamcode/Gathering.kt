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
    var UP_POSITION = 0.0
    var DUMP_POSITION = 0.24
    private val DOWN_POSITION = 1.0
    private val IN_BETWEEN_POSITION = 0.64
    var gatherMotorSpeed = 0.0
    var isOn = false
    var isUp = false
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
        inOutMotor.rampingType = Motor.RampingType.None
        gatherMotor.rampingType = Motor.RampingType.None
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
        gatherMotor.power = 1.0
    }

    fun off() {
        isOn = false
        gatherMotor.power = 0.0
    }
    fun eject() {
        isOn = true
        gatherMotor.power = -1.0
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
            when(task) {
                Tasks.DUMP -> {
                    setServoPositions(DUMP_POSITION)
                    sleep(950L)
                    inBetween()
                    task = Tasks.NONE
                }
            }
        }
    }

}
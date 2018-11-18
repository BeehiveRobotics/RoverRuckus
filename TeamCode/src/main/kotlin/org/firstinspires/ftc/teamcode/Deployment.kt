package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Servos.Servo as BRServo
import com.qualcomm.robotcore.hardware.Servo
import org.BeehiveRobotics.Library.Sensors.REVColorSensor

class Deployment(private val opMode: BROpMode): RobotSystem(opMode) {
    val knockerServo = BRServo(opMode, "dks")
    val rightFlipServo = BRServo(opMode, "drfs")
    val leftFlipServo = BRServo(opMode, "dlfs")
    val balanceServo = BRServo(opMode, "dbs")
    val cs = REVColorSensor(opMode, "dcs")
    private val REVEALED_POSITION = 0.2
    private val STOWED_POSITION = 0.8
    private val UP_POSITION = 0.8
    private val RIGHT_POSITION = 0.0
    private val MIDDLE_POSITION = 0.5
    private val LEFT_POSITION = 1.0
    private var isOut = false

    override fun init() {
        rightFlipServo.direction = Servo.Direction.REVERSE
        balanceServo.speed = 0.7
        balanceServo.direction = Servo.Direction.REVERSE
    }

    fun reveal() {
        isOut = true
        setFlipPosition(REVEALED_POSITION)
    }

    fun stow() {
        isOut = false
        setFlipPosition(STOWED_POSITION)
    }

    fun toggleOut() {
        if(isOut) {
            stow()
        } else {
            reveal()
        }
    }
    fun knock() {
        
    }

    fun up() {
        setFlipPosition(UP_POSITION)
    }

    fun right() {
        setKnockerPosition(RIGHT_POSITION)
        sleep(600)
        middle()
    }
    fun left() {
        setKnockerPosition(LEFT_POSITION)
        sleep(600)
        middle()
    }
    private fun middle() {
        setKnockerPosition(MIDDLE_POSITION)
    }

    private fun setKnockerPosition(position: Double) {
        knockerServo.position = position
    }

    private fun setFlipPosition(position: Double) {
        rightFlipServo.position = position
        leftFlipServo.position = position
        balanceServo.position = position
    }

}
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
    val REVEALED_POSITION = 1.0
    val STOWED_POSITION = 0.0
    val UP_POSITION = 0.8
    val MIDDLE_POSITION = 0.5 //ish, change later
    val RIGHT_POSITION = 0.0
    val LEFT_POSITION = 1.0

    override fun init() {
        rightFlipServo.direction = Servo.Direction.REVERSE
        balanceServo.speed = 0.7
    }

    fun reveal() {
        setFlipPosition(REVEALED_POSITION)
    }

    fun stow() {
        setFlipPosition(STOWED_POSITION)
    }

    fun knock() {
        
    }

    fun up() {
        setFlipPosition(UP_POSITION)
    }

    private fun setFlipPosition(position: Double) {
        rightFlipServo.position = position
        leftFlipServo.position = position
        balanceServo.position = position
    }

}
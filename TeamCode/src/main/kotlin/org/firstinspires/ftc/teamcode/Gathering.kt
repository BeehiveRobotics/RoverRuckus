package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Motors.Motor
import org.BeehiveRobotics.Library.Servos.Servo
import com.qualcomm.robotcore.hardware.Servo.Direction

class Gathering(private val opMode: BROpMode): RobotSystem(opMode) {
    val gatherMotor = Motor(opMode, "ggm")
    val inOutMotor = Motor(opMode, "giom")
    val rightServo = Servo(opMode, "grs")
    val leftServo = Servo(opMode, "gls")
    var UP_POSITION = 0.0
    private val DOWN_POSITION = 1.0
    var isOn = false
    var isUp = true

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

    fun up() {
        isUp = true
        setServoPositions(UP_POSITION)
        off()
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
        gatherMotor.rawPower = 1.0
    }

    fun off() {
        isOn = false
        gatherMotor.rawPower = 0.0
    }

    private fun setServoPositions(position: Double) {
        rightServo.position = position
        leftServo.position = position
    }
}
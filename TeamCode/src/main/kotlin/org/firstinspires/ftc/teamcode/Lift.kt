package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Motors.Motor
import org.BeehiveRobotics.Library.Servos.Servo
import com.qualcomm.robotcore.hardware.DcMotor

class Lift(private val opMode: BROpMode): RobotSystem(opMode) {
    enum class Tasks {
        CLIMBING, DEPLOYING, NEITHER
    }
    val lockServo = Servo(opMode, "lls")
    val leftMotor = Servo(opMode, "llm")
    val rightMotor = Servo(opMode, "lrm")
    init {
        leftMotor.direction = com.qualcomm.robotcore.hardware.DcMotor.Direction.REVERSE
    }
    fun move(speed: Double) {
        leftMotor.power = 
    }
    fun move(speed: Double, time: Long) {

    }

    fun move(speed: Double, distance: Double) {

    }
    isBusy 
        get() = leftMotor.isBusy || rightMotor.isBusy
}
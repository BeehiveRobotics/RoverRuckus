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
    val leftMotor = Motor(opMode, "llm")
    val rightMotor = Motor(opMode, "lrm")
    init {
        leftMotor.direction = com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE
        leftMotor.rampingType = Motor.RampingType.Piecewise
        rightMotor.rampingType = Motor.RampingType.Piecewise
    }
    var power: Double
        set(value) {
            leftMotor.power = value
            rightMotor.power = value
        }
        get() = leftMotor.power

    fun move(speed: Double, time: Long, waitForCompletion: Boolean = true) {
        leftMotor.runForTime(speed, time, false)
        rightMotor.runForTime(speed, time, waitForCompletion)
    }

    fun runToPosition(speed: Double, position: Double, waitForCompletion: Boolean = true) {
        leftMotor.runToPosition(speed, position, false)
        rightMotor.runToPosition(speed, position, waitForCompletion)
    }
    override var isBusy = false
        get() = leftMotor.isBusy || rightMotor.isBusy
}
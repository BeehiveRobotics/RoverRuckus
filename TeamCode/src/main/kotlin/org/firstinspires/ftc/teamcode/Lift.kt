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
    val lock = Servo(opMode, "lls")
    val leftMotor = Motor(opMode, "llm")
    val rightMotor = Motor(opMode, "lrm")

    val LOCK_POSITION = 0.0
    val UNLOCK_POSITION = 0.85

    init {
        leftMotor.direction = com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE
        leftMotor.rampingType = Motor.RampingType.None
        rightMotor.rampingType = Motor.RampingType.None
        
    }
    var power: Double = 0.0
        set(value) {
            leftMotor.power = value
            rightMotor.power = value
            field = value
        }

    fun runForTime(speed: Double, time: Long, waitForCompletion: Boolean = true) {
        leftMotor.runForTime(speed, time, false)
        rightMotor.runForTime(speed, time, waitForCompletion)
    }

    fun runToPosition(speed: Double, position: Double, waitForCompletion: Boolean = true) {
        leftMotor.runToPosition(speed, position, false)
        opMode.dashboard.showLine("tgh")
        rightMotor.runToPosition(speed, position, waitForCompletion)
    }

    fun lock() {
        lock.position = LOCK_POSITION
    }
    fun unlock() {
        lock.position = UNLOCK_POSITION
    }
    override var isBusy = false
        get() = leftMotor.isBusy || rightMotor.isBusy
}
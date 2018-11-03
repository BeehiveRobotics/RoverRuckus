package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Motors.Motor
import org.BeehiveRobotics.Library.Servos.Servo
import com.qualcomm.robotcore.hardware.DcMotor

class Lift(private val opMode: BROpMode): RobotSystem(opMode) {
    private val climbMotor = Motor(opMode, "lcm")
    val deploymentMotor = Motor(opMode, "ldm")

    val lockServo = Servo(opMode, "lls")
    val SERVO_LOCK_POSITION = 1.0
    val SERVO_UNLOCK_POSITION = 0.0
    var isLocked = true

    fun lock() {
        isLocked = true
        lockServo.position = SERVO_LOCK_POSITION
    }

    fun unlock() {
        isLocked = false
        lockServo.position = SERVO_UNLOCK_POSITION
    }

    fun toggleLock() {
        if(isLocked) {
            unlock()
        } else {
            lock()
        }
    }

    fun climb(speed: Double) {
        climbMotor.rawPower = speed
        deploymentMotor.rawPower = 0.12 * speed
    }

    override fun init() {
        climbMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        climbMotor.rawPower = 0.0
        deploymentMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        deploymentMotor.rawPower = 0.0
    }
}
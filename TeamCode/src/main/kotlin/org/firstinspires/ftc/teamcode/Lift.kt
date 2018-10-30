package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Motors.Motor
import org.BeehiveRobotics.Library.Servos.Servo
import com.qualcomm.robotcore.hardware.DcMotor

class Lift(private val opMode: BROpMode): RobotSystem(opMode) {
    val motor = Motor(opMode, "lm")
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

    override fun init() {
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        motor.rawPower = 0.0
    }
}
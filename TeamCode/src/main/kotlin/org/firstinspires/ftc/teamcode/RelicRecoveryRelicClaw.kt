package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Motors.Motor
import org.BeehiveRobotics.Library.Servos.Servo
import com.qualcomm.robotcore.util.ElapsedTime
import org.BeehiveRobotics.Library.Systems.RobotSystem

class RelicRecoveryRelicClaw(private val opMode: BROpMode): RobotSystem(opMode), Runnable {
    private val motor: Motor = Motor(opMode, "rcm")
    private val claw: Servo = Servo(opMode, "rcc")
    private val arm: Servo = Servo(opMode, "rca")
    private final val CLOSE_POSITION: Double = 0.0
    private final val OPEN_POSITION: Double = 1.0
    private final val DOWN_POSIITION: Double = 1.0
    private final val PICKUP_POSITION: Double = 0.67
    private final val DRIVING_POSITION: Double = 0.58
    private final val UP_POSITION: Double = 0.0

    fun init() {
        down()
        openClaw()
    }
    
    fun closeClaw() { 
        claw.position = CLOSE_POSITION
    }

    fun openClaw() { 
        claw.position = OPEN_POSITION
    }

    fun up() { 
        arm.position = UP_POSITION
    }

    fun down() { 
        arm.position = DOWN_POSIITION
    }

    fun driving() { 
        arm.position = DRIVING_POSITION
    }

    fun pickup() { 
        arm.position = PICKUP_POSITION
        claw.position = CLOSE_POSITION
    }
    
    fun moveMotor(speed: Double) {
        motor.rawPower = speed
    }

    fun moveMotor(speed: Double, milliseconds: Long) {
        moveMotor(speed)
        sleep(milliseconds)
        stop()
    }

    fun stop() {
        moveMotor(0.0)
    }
    
    override fun run() {}

}
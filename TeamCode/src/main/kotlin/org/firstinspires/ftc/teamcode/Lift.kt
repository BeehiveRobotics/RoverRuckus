package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Motors.Motor
import org.BeehiveRobotics.Library.Servos.Servo
import com.qualcomm.robotcore.hardware.DcMotor

class Lift(private val opMode: BROpMode): RobotSystem(opMode), Runnable {
    enum class Tasks {
        CLIMBING, DEPLOYING, NEITHER
    }
    val climbMotor = Motor(opMode, "lcm")
    private val deploymentMotor = Motor(opMode, "ldm")

    val lockServo = Servo(opMode, "lls")
    val SERVO_LOCK_POSITION = 1.0
    val SERVO_UNLOCK_POSITION = 0.0
    var isLocked = true

    var deployingSpeed = 0.0
    var climbingSpeed = 0.0

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
        climbMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        climbMotor.rawPower = 0.0
        deploymentMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        deploymentMotor.rawPower = 0.0
    }
    override fun start() {
        val thread = Thread(this)
        thread.start()
    }

    override fun run() {
        var currentTask = Tasks.NEITHER
        while(opMode.opModeIsActive()) {
            if(Math.abs(deployingSpeed) > Math.abs(climbingSpeed)) currentTask = Tasks.DEPLOYING
            else if(Math.abs(climbingSpeed) > Math.abs(deployingSpeed)) currentTask = Tasks.CLIMBING
            else currentTask = Tasks.NEITHER
            when(currentTask) {
                Tasks.CLIMBING -> {
                    deploymentMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
                    climbMotor.rawPower = climbingSpeed
                }
                Tasks.DEPLOYING -> {
                    deploymentMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
                    deploymentMotor.rawPower = deployingSpeed
                }
                Tasks.NEITHER -> {
                    deploymentMotor.rawPower = 0.0
                    climbMotor.rawPower = 0.0
                }
            }
        }
    }
}
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
    private var currentTask = Tasks.NEITHER
    val climbMotor = Motor(opMode, "lcm")
    val deploymentMotor = Motor(opMode, "ldm")

    val SERVO_LOCK_POSITION = 1.0
    val SERVO_UNLOCK_POSITION = 0.0
    var isLocked = true

    var deployingSpeed = 0.0
    var climbingSpeed = 0.0

    override fun init() {
        climbMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        climbMotor.rawPower = 0.0
        climbMotor.MIN_SPEED = 0.4
        climbMotor.RAMPING_COEFFICIENT = 0.6
        deploymentMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        deploymentMotor.rawPower = 0.0
    }
    override fun start() {
        val thread = Thread(this)
        thread.start()
    }

    override fun toString(): String {
        return "" + 
            "Task: " + currentTask.toString() + "\n" + 
            "Deploying Speed: " + deployingSpeed + "\n" + 
            "Climbing Speed: " + climbingSpeed + "\n" + 
            "Deploying Motor Speed: " + deploymentMotor.rawPower + "\n" + 
            "Climbing Motor Speed: " + climbMotor.rawPower
    }

    override fun run() {
        while(opMode.opModeIsActive()) {
            if(Math.abs(deployingSpeed) > Math.abs(climbingSpeed)) {
                deploymentMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
                deploymentMotor.rawPower = deployingSpeed
                climbMotor.rawPower = 0.0
                climbingSpeed = 0.0
            }
            else if(Math.abs(climbingSpeed) > Math.abs(deployingSpeed)) {
                deploymentMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
                climbMotor.rawPower = climbingSpeed
                deploymentMotor.rawPower = 0.0
                deployingSpeed = 0.0
            }
            else {
                deploymentMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
                deploymentMotor.rawPower = 0.0
                deployingSpeed = 0.0
                climbMotor.rawPower = 0.0
                climbingSpeed = 0.0
            }
        }
    }
}
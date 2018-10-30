package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Motors.Motor
import org.BeehiveRobotics.Library.Servos.Servo
import org.BeehiveRobotics.Library.Sensors.REVTouchSensor
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.ElapsedTime

class Deployment(private val opMode: BROpMode): RobotSystem(opMode), Runnable {

    enum class Tasks {
        STOP
    }

    var currentTask = Tasks.STOP
    val motor = Motor(opMode, "dm")
    val bottomSensor = REVTouchSensor(opMode, "dbs")
    var milliseconds: Long = 0

    override fun init() {
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        motor.rawPower = 0.0
    }

    fun moveUntilDown(speed: Double = 1.0) {
        motor.rawPower = -Math.abs(speed)
        while(!bottomSensor.isPressed()) {}
        motor.rawPower = 0.0
    }

    override fun run() {
        when(currentTask) {
            Tasks.STOP -> motor.rawPower = 0.0
        }
        currentTask = Tasks.STOP
    }
}
package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.BeehiveRobotics.Library.Util.Kotlin.BROpMode
import java.util.*

@Autonomous(name = "Testing Gyro", group = "Test")
class KotLinTest : BROpMode() {
    private val robot: TankRobot = TankRobot(this)
    override fun initialize() {
        setOpModeType(OpModeType.Autonomous)
        this.robot.init()
    }

    override fun run() {
        robot.drive.leftGyro(-1.0, 1.0, 180.0)
        robot.drive.rightGyro(1.0, -1.0, 90.0)
        robot.drive.rightGyro(1.0, -1.0, 270.0)
        robot.drive.leftGyro(-1.0, 1.0, 90.0)

    }

    override fun end() {
        this.robot.drive.stopMotors()
    }
}
package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.BeehiveRobotics.Library.Util.BROpMode

@Autonomous(name = "KotlinTestTankRobot", group = "Test")
class KotLinTest : BROpMode() {
    private val robot: TankRobot = TankRobot(this)
    override fun initialize() {
        setOpModeType(OpModeType.Autonomous)
        this.robot.init()
    }

    override fun run() {
        robot.drive.forward(1.0, 12.0)
        robot.drive.forward(1.0, 12.0)
        robot.drive.backward(1.0, 12.0)
        robot.drive.spinRight(1.0, 12.0)
        robot.drive.spinLeft(1.0, 12.0)
        robot.drive.rightForward(1.0, 12.0)
        robot.drive.rightBackward(1.0, 12.0)
        robot.drive.leftForward(1.0, 12.0)
        robot.drive.leftBackward(1.0, 12.0)
    }

    override fun end() {
        this.robot.drive.stopMotors()
    }
}
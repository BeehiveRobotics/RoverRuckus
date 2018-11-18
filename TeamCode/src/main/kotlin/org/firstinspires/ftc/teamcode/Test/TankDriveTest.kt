package org.firstinspires.ftc.teamcode.Test

import org.BeehiveRobotics.Library.Robots.TankRobot
import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

//@Autonomous(name="Test TankDrive", group = "Test")
class TankDriveTest: BROpMode(OpModeType.Autonomous) {
    private val robot: TankRobot = TankRobot(this)
    override fun initialize() {
        this.robot.init()
    }
    override fun run() {
        dashboard.addLine("Forward", true)
        this.robot.drive.forward(1.0, 12.0)
        dashboard.addLine("Backward", true)
        this.robot.drive.backward(1.0, 12.0)
        dashboard.addLine("Spin Right", true)
        this.robot.drive.spinRight(1.0, 12.0)
        dashboard.addLine("Spin Left", true)
        this.robot.drive.spinLeft(1.0, 12.0)
        dashboard.addLine("Left Forward", true)
        this.robot.drive.leftForward(1.0, 12.0)
        dashboard.addLine("Left Backward", true)
        this.robot.drive.leftBackward(1.0, 12.0)
        dashboard.addLine("Right Forward", true)
        this.robot.drive.rightForward(1.0, 12.0)
        dashboard.addLine("Right Backward", true)
        this.robot.drive.rightBackward(1.0, 12.0)

        dashboard.addLine("Forward", true)
        this.robot.drive.forward(1.0, 6.0)
        dashboard.addLine("Backward", true)
        this.robot.drive.backward(1.0, 6.0)
        dashboard.addLine("Spin Right", true)
        this.robot.drive.spinRight(1.0, 6.0)
        dashboard.addLine("Spin Left", true)
        this.robot.drive.spinLeft(1.0, 6.0)
        dashboard.addLine("Left Forward", true)
        this.robot.drive.leftForward(1.0, 6.0)
        dashboard.addLine("Left Backward", true)
        this.robot.drive.leftBackward(1.0, 6.0)
        dashboard.addLine("Right Forward", true)
        this.robot.drive.rightForward(1.0, 6.0)
        dashboard.addLine("Right Backward", true)
        this.robot.drive.rightBackward(1.0, 6.0)

        dashboard.addLine("Forward", true)
        this.robot.drive.forward(0.5, 12.0)
        dashboard.addLine("Backward", true)
        this.robot.drive.backward(0.5, 12.0)
        dashboard.addLine("Spin Right", true)
        this.robot.drive.spinRight(0.5, 12.0)
        dashboard.addLine("Spin Left", true)
        this.robot.drive.spinLeft(0.5, 12.0)
        dashboard.addLine("Left Forward", true)
        this.robot.drive.leftForward(0.5, 12.0)
        dashboard.addLine("Left Backward", true)
        this.robot.drive.leftBackward(0.5, 12.0)
        dashboard.addLine("Right Forward", true)
        this.robot.drive.rightForward(0.5, 12.0)
        dashboard.addLine("Right Backward", true)
        this.robot.drive.rightBackward(0.5, 12.0)

    }
}
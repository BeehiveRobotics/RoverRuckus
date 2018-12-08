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
        dashboard.addLine("Forward")
        this.robot.drive.forward(1.0, 12.0)
        dashboard.addLine("Backward")
        this.robot.drive.backward(1.0, 12.0)
        dashboard.addLine("Spin Right")
        this.robot.drive.spinRight(1.0, 12.0)
        dashboard.addLine("Spin Left")
        this.robot.drive.spinLeft(1.0, 12.0)
        dashboard.addLine("Left Forward")
        this.robot.drive.leftForward(1.0, 12.0)
        dashboard.addLine("Left Backward")
        this.robot.drive.leftBackward(1.0, 12.0)
        dashboard.addLine("Right Forward")
        this.robot.drive.rightForward(1.0, 12.0)
        dashboard.addLine("Right Backward")
        this.robot.drive.rightBackward(1.0, 12.0)

        dashboard.addLine("Forward")
        this.robot.drive.forward(1.0, 6.0)
        dashboard.addLine("Backward")
        this.robot.drive.backward(1.0, 6.0)
        dashboard.addLine("Spin Right")
        this.robot.drive.spinRight(1.0, 6.0)
        dashboard.addLine("Spin Left")
        this.robot.drive.spinLeft(1.0, 6.0)
        dashboard.addLine("Left Forward")
        this.robot.drive.leftForward(1.0, 6.0)
        dashboard.addLine("Left Backward")
        this.robot.drive.leftBackward(1.0, 6.0)
        dashboard.addLine("Right Forward")
        this.robot.drive.rightForward(1.0, 6.0)
        dashboard.addLine("Right Backward")
        this.robot.drive.rightBackward(1.0, 6.0)

        dashboard.addLine("Forward")
        this.robot.drive.forward(0.5, 12.0)
        dashboard.addLine("Backward")
        this.robot.drive.backward(0.5, 12.0)
        dashboard.addLine("Spin Right")
        this.robot.drive.spinRight(0.5, 12.0)
        dashboard.addLine("Spin Left")
        this.robot.drive.spinLeft(0.5, 12.0)
        dashboard.addLine("Left Forward")
        this.robot.drive.leftForward(0.5, 12.0)
        dashboard.addLine("Left Backward")
        this.robot.drive.leftBackward(0.5, 12.0)
        dashboard.addLine("Right Forward")
        this.robot.drive.rightForward(0.5, 12.0)
        dashboard.addLine("Right Backward")
        this.robot.drive.rightBackward(0.5, 12.0)

    }
}
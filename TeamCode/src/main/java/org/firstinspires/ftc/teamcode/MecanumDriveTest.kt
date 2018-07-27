package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.hardware.DcMotor
import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Robots.MecanumRobot

@Autonomous(name = "Test MecanumDrive", group = "Test")
class MecanumDriveTest: BROpMode() {
    private val robot: MecanumRobot = MecanumRobot(this)
    override fun initialize() {
        opModeType = OpModeType.Autonomous
        this.robot.init()
    }
    override fun run() {
        showLine("Forward")
        robot.drive.forward(1.0, 12.0)
        showLine("Backward")
        robot.drive.backward(1.0, 12.0)
        showLine("Strafe Left")
        robot.drive.strafeLeft(1.0, 12.0)
        showLine("Strafe Right")
        robot.drive.strafeRight(1.0, 12.0)
        showLine("Spin Left")
        robot.drive.spinLeft(1.0, 12.0)
        showLine("Spin Right")
        robot.drive.spinRight(1.0, 12.0)
        showLine("Left Forward")
        robot.drive.leftForward(1.0, 12.0)
        showLine("Left Backward")
        robot.drive.leftBackward(1.0, 12.0)
        showLine("Right Forward")
        robot.drive.rightForward(1.0, 12.0)
        showLine("Right Backward")
        robot.drive.rightBackward(1.0, 12.0)
        showLine("Forward Left")
        robot.drive.forwardLeft(1.0, 12.0)
        showLine("Forward Right")
        robot.drive.forwardRight(1.0, 12.0)
        showLine("Backward Left")
        robot.drive.backwardLeft(1.0, 12.0)
        showLine("Backward Right")
        robot.drive.backwardRight(1.0, 12.0)

        showLine("Forward")
        robot.drive.forward(0.5, 12.0)
        showLine("Backward")
        robot.drive.backward(0.5, 12.0)
        showLine("Strafe Left")
        robot.drive.strafeLeft(0.5, 12.0)
        showLine("Strafe Right")
        robot.drive.strafeRight(0.5, 12.0)
        showLine("Spin Left")
        robot.drive.spinLeft(0.5, 12.0)
        showLine("Spin Right")
        robot.drive.spinRight(0.5, 12.0)
        showLine("Left Forward")
        robot.drive.leftForward(0.5, 12.0)
        showLine("Left Backward")
        robot.drive.leftBackward(0.5, 12.0)
        showLine("Right Forward")
        robot.drive.rightForward(0.5, 12.0)
        showLine("Right Backward")
        robot.drive.rightBackward(0.5, 12.0)
        showLine("Forward Left")
        robot.drive.forwardLeft(0.5, 12.0)
        showLine("Forward Right")
        robot.drive.forwardRight(0.5, 12.0)
        showLine("Backward Left")
        robot.drive.backwardLeft(0.5, 12.0)
        showLine("Backward Right")
        robot.drive.backwardRight(0.5, 12.0)


        showLine("Forward")
        robot.drive.forward(1.0, 6.0)
        showLine("Backward")
        robot.drive.backward(1.0, 6.0)
        showLine("Strafe Left")
        robot.drive.strafeLeft(1.0, 6.0)
        showLine("Strafe Right")
        robot.drive.strafeRight(1.0, 6.0)
        showLine("Spin Left")
        robot.drive.spinLeft(1.0, 6.0)
        showLine("Spin Right")
        robot.drive.spinRight(1.0, 6.0)
        showLine("Left Forward")
        robot.drive.leftForward(1.0, 6.0)
        showLine("Left Backward")
        robot.drive.leftBackward(1.0, 6.0)
        showLine("Right Forward")
        robot.drive.rightForward(1.0, 6.0)
        showLine("Right Backward")
        robot.drive.rightBackward(1.0, 6.0)
        showLine("Forward Left")
        robot.drive.forwardLeft(1.0, 6.0)
        showLine("Forward Right")
        robot.drive.forwardRight(1.0, 6.0)
        showLine("Backward Left")
        robot.drive.backwardLeft(1.0, 6.0)
        showLine("Backward Right")
        robot.drive.backwardRight(1.0, 6.0)

    }
    override fun end() {
        robot.drive.stopMotors()
    }
}

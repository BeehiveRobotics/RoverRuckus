package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.hardware.DcMotor
import org.BeehiveRobotics.Library.Util.Kotlin.BROpMode
import org.BeehiveRobotics.Library.Motors.Kotlin.MecanumRobot

@Autonomous(name = "Testing MecanumDrive", group = "Test")
class TestMecanumDrive: BROpMode() {
    private val robot: MecanumRobot = MecanumRobot(this)
    override fun initialize() {
        setOpModeType(OpModeType.Autonomous)
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
    }
    override fun end() {
        robot.drive.stopMotors()
    }
}
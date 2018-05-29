package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.BeehiveRobotics.Library.Util.BROpMode

@TeleOp(name = "KotlinTestTankRobot", group = "Test")
class KotLinTest : BROpMode() {
    private val robot: TankRobot = TankRobot(this)
    override fun initialize() {
        setOpModeType(OpModeType.TeleOp)
        this.robot.init()
    }

    override fun run() {
        var left: Double = controller1.leftStickY()
        var right: Double = controller1.rightStickY()
        this.robot.drive.drive(left, right)
    }

    override fun end() {
        this.robot.drive.stopMotors()
    }
}
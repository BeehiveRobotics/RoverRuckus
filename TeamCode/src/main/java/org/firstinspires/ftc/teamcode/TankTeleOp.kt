package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Robots.TankRobot
import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name="Tank TeleOp", group="Test")
class TankTeleOp: BROpMode() {
    private val robot: TankRobot = TankRobot(this)
    override fun initialize() {
        opModeType = OpModeType.TeleOp
        robot.init()
    }
    override fun run() {
        val left: Double = controller1.leftStickY()
        val right: Double = controller1.rightStickY()
        robot.drive.drive(left, right)
    }
}
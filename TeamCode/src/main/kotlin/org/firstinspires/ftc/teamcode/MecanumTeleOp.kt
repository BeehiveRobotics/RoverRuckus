package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Robots.MecanumRobot
import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

//@TeleOp(name="Mecanum TeleOp", group="Test")
class MecanumTeleop: BROpMode(OpModeType.TeleOp) {
    private val robot: MecanumRobot = MecanumRobot(this)
    override fun initialize() {
        robot.init()
    }
    override fun run() {
        val forward = controller1.leftStickY
        val side = controller1.leftStickX
        val spin = controller1.rightStickX
        robot.drive.drive(side, forward, spin)
    }
}
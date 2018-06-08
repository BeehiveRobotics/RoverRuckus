package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Motors.Kotlin.TankRobot
import org.BeehiveRobotics.Library.Util.Kotlin.BROpMode

class TankTeleOp: BROpMode() {
    private val robot: TankRobot = TankRobot(this)
    override fun initialize() {
        robot.init()
    }
    override fun run() {
        val left: Double = controller1.leftStickY()
        val right: Double = controller1.rightStickY()
        robot.drive.drive(left, right)
    }
    override fun end() {
        robot.stop()
    }
}
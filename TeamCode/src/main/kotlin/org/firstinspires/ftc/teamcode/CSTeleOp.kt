package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode 
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name="Color Sensor Value Testing", group="Test")
class CSTeleOp(): BROpMode(OpModeType.TeleOp) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
    }
    override fun run() {
        dashboard.addLine(robot.drive.toString())
    }
}
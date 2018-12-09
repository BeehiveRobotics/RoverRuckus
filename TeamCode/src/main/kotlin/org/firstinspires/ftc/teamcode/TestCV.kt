package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode

@Autonomous(name="Test CV", group="Test")
class TestCV(): BROpMode(OpModeType.TeleOp) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
        robot.cv.startCV()
    }

    override fun run() {
        dashboard.addLine(robot.cv.getGoldMineralPosition().toString())
        dashboard.update()
    }
    override fun end() {
        robot.cv.stopCV()
    }
}
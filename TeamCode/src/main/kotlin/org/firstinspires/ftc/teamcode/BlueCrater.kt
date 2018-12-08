package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode

@Autonomous(name="Blue Crater", group="RR2")
class BlueCrater(): BROpMode(BROpMode.OpModeType.Autonomous) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
    }
    override fun run() {
        robot.startTelemetry()
        robot.land()
        robot.cv.startCV()
        val goldMineralPosition = robot.cv.getGoldMineralPosition()
        dashboard.addLine(goldMineralPosition.toString())
        dashboard.update()
        robot.cv.stopCV()
    }
}
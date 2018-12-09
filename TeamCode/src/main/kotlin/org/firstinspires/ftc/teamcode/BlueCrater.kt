package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.firstinspires.ftc.teamcode.CV.GoldMineralPosition

@Autonomous(name="Blue Crater", group="RR2")
class BlueCrater(): BROpMode(BROpMode.OpModeType.Autonomous) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
    }
    override fun onStartPressed() {
        robot.start()
        robot.cv.startCV()
    }
    override fun run() {
        robot.land()
        //sleep(2000)
        val goldMineralPosition = robot.cv.getGoldMineralPosition()
        dashboard.addLine(goldMineralPosition.toString())
        dashboard.update()
        robot.cv.stopCV()
        robot.drive.forward(1.0, 14.0)
        when(goldMineralPosition) {
            GoldMineralPosition.LEFT -> {
                robot.drive.strafeLeft(1.0, 16.0)
            }
            GoldMineralPosition.CENTER -> {
                robot.drive.strafeRight(1.0, 6.0)
            }
            GoldMineralPosition.RIGHT -> {
                robot.drive.strafeRight(1.0, 24.0)
            }
        }
        robot.drive.forward(1.0, 9.0)
        robot.drive.backward(1.0, 9.0)
        when(goldMineralPosition) {
            GoldMineralPosition.LEFT -> {
                robot.drive.strafeLeft(1.0, 18.0)
            }
            GoldMineralPosition.CENTER -> {
                robot.drive.strafeLeft(1.0, 40.0)
            }
            GoldMineralPosition.RIGHT -> {
                robot.drive.strafeLeft(1.0, 58.0)
            }
        }

    }
}
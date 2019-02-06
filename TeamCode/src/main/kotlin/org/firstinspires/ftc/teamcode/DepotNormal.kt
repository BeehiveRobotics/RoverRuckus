package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.firstinspires.ftc.teamcode.CV.GoldMineralPosition

@Autonomous(name="Depot - Normal", group="RR2")
class DepotNormal(): BROpMode(BROpMode.OpModeType.Autonomous) {
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
        val goldMineralPosition = robot.cv.getGoldMineralPosition()
        robot.cv.stopCV()
        robot.drive.forward(1.0, 14.0, false)
        sleep(500)
        robot.lift.runToPosition(1.0, 2000.0, false) //NEEDS CHANGED
        robot.waitUntilNotBusy()
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
        robot.drive.forward(1.0, 12.0)
        robot.drive.backward(1.0, 12.0)
        when(goldMineralPosition) {
            GoldMineralPosition.LEFT -> {
                robot.drive.strafeLeft(1.0, 24.0)
            }
            GoldMineralPosition.CENTER -> {
                robot.drive.strafeLeft(1.0, 46.0)
            }
            GoldMineralPosition.RIGHT -> {
                robot.drive.strafeLeft(1.0, 64.0)
            }
        }
        //robot.drive.rightGyro(1.0, -135.0)
        robot.drive.leftGyro(1.0, 30.0) //was 45.0
        //robot.drive.backward(1.0, 12.0)
        robot.drive.forward(1.0, 12.0)
        //robot.drive.forward(1.0, 3.0)
        //robot.drive.rightGyro(1.0, 50.0)
        //robot.drive.forward(1.0, 4.0)
        robot.drive.strafeRight(1.0, 46.0)
        robot.teamMarker.down()
        robot.drive.strafeLeft(1.0, 46.0, false) //was 56.0
        sleep(500)
        robot.teamMarker.up()
        robot.waitUntilNotBusy()
        robot.drive.leftGyro(1.0, 130.0)
        robot.drive.forward(1.0, 16.0, false)
        robot.gathering.inOutMotor.runForTime(-1.0, 500L)
        robot.gathering.down()
        robot.waitUntilNotBusy()
    }
}
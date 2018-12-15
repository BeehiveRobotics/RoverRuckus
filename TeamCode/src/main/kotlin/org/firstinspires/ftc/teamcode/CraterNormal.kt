package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.firstinspires.ftc.teamcode.CV.GoldMineralPosition
import com.qualcomm.robotcore.util.ElapsedTime

@Autonomous(name="Crater - Normal", group="RR2")
class CraterNormal(): BROpMode(BROpMode.OpModeType.Autonomous) {
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
        val runTime = ElapsedTime()
        val goldMineralPosition = robot.cv.getGoldMineralPosition()
        robot.cv.stopCV()
        robot.drive.forward(1.0, 14.0, false)
        sleep(500)
        robot.lift.deploymentMotor.runToPosition(1.0, 2000.0, false)
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
        robot.drive.backward(1.0, 10.0)
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
        robot.drive.rightGyro(1.0, -135.0)
        robot.drive.backward(0.8, 12.0)
        robot.drive.forward(0.8, 1.5)
        if(goldMineralPosition == GoldMineralPosition.RIGHT) robot.drive.forward(1.0, 8.0)
        robot.drive.strafeRight(1.0, 50.0)
        robot.teamMarker.down()
        robot.drive.strafeLeft(1.0, 56.0, false)
        sleep(500)
        robot.teamMarker.up()
        robot.waitUntilNotBusy()
        robot.gathering.inBetween()
        robot.drive.leftGyro(1.0, -45.0)
        robot.drive.forward(1.0, 16.0, false)
        robot.gathering.inOutMotor.runForTime(-1.0, 500L)
        robot.gathering.down()
        robot.waitUntilNotBusy()
    }
}
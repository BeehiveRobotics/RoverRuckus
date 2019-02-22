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
        robot.cv.startCV()
    }
    override fun onStartPressed() {
        robot.start()
    }
    override fun run() {
        robot.land()
        val goldMineralPosition = robot.cv.getGoldMineralPosition()
        robot.cv.stopCV()
        robot.drive.spinRight(0.3, 1.0)
        robot.drive.forward(1.0, 10.0, waitForCompletion = false)
        sleep(150)
        robot.lift.runToPosition(-1.0, 2500.0, waitForCompletion = false)
        robot.drive.waitUntilNotBusy()
        when(goldMineralPosition) {
            GoldMineralPosition.LEFT ->   robot.drive.strafeLeft(0.7, 16.0)
            GoldMineralPosition.CENTER -> robot.drive.strafeRight(0.4, 4.0)
            GoldMineralPosition.RIGHT ->  robot.drive.strafeRight(0.7, 18.0)
        }
        robot.drive.forward(0.75, 14.0)   
        robot.drive.backward(0.75, 9.0)
        when(goldMineralPosition) {
            GoldMineralPosition.LEFT ->   robot.drive.strafeLeft(0.75, 24.0)
            GoldMineralPosition.CENTER -> robot.drive.strafeLeft(0.75, 50.0)
            GoldMineralPosition.RIGHT ->  robot.drive.strafeLeft(0.75, 64.0)
        }
        robot.drive.spinLeft(0.7, 10.0)
        robot.drive.forward(0.6)
        sleep(500L)
        robot.drive.stopMotors()
        robot.drive.backward(0.8, 1.0)
        robot.drive.strafeLeft(0.85, 42.0)
        robot.teamMarker.down()
        robot.drive.spinLeft(1.0, 1.5)
        robot.drive.strafeRight(0.85, 45.0, waitForCompletion = false)
        sleep(500)
        robot.teamMarker.up()
        robot.waitUntilNotBusy()
        robot.drive.spinRight(1.0, 20.0)
        robot.drive.strafeLeft(0.6, 10.0)
        robot.drive.forward(1.0, 24.0, waitForCompletion = false)
        robot.gathering.inBetween()
        sleep(350)
        robot.gathering.inOutMotor.runForTime(-1.0, 500L)
        robot.gathering.down()
        robot.waitUntilNotBusy()
    }
}
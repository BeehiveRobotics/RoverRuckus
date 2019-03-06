package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.firstinspires.ftc.teamcode.CV.GoldMineralPosition
import com.qualcomm.robotcore.util.ElapsedTime

@Autonomous(name="Crater - No Claim", group="RR2")
class CraterNoClaim(): BROpMode(BROpMode.OpModeType.Autonomous) {
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
        robot.drive.spinRight(0.3, 0.7)
        robot.drive.forward(1.0, 10.0, waitForCompletion = false)
        sleep(150)
        robot.lift.runToPosition(-1.0, 2500.0, waitForCompletion = false)
        robot.drive.waitUntilNotBusy()
        when(goldMineralPosition) {
            GoldMineralPosition.LEFT ->   robot.drive.strafeLeft(0.7, 16.0)
            //GoldMineralPosition.CENTER -> robot.drive.strafeRight(0.4, 4.0)
            GoldMineralPosition.RIGHT ->  robot.drive.strafeRight(0.7, 18.0)
        }
        robot.drive.forward(0.75, 14.0, false)   
        robot.gathering.inBetween()
        sleep(350)
        robot.gathering.inOutMotor.runForTime(-1.0, 500L)
        robot.gathering.down()
        robot.waitUntilNotBusy()
    }
}
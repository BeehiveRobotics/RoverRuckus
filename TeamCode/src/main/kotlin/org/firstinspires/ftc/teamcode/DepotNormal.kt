package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.firstinspires.ftc.teamcode.CV.GoldMineralPosition

@Autonomous(name="Depot - Normal", group="RR2")
class DepotNormal(): BROpMode(BROpMode.OpModeType.Autonomous) {
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
        robot.sample(goldMineralPosition)
        when(goldMineralPosition) {
            GoldMineralPosition.LEFT ->   robot.drive.strafeLeft(0.75, 24.0)
            GoldMineralPosition.CENTER -> robot.drive.strafeLeft(0.75, 50.0)
            GoldMineralPosition.RIGHT ->  robot.drive.strafeLeft(0.75, 64.0)
        }
        robot.drive.spinRight(1.0, 34.0)
        robot.drive.backward(0.4)
        sleep(1250L)
        robot.drive.stopMotors()
        robot.drive.leftBackward(0.3)
        sleep(200L)
        
        robot.drive.strafeLeft(0.75, 46.0)
        robot.teamMarker.down()
        robot.drive.leftBackward(1.0, 4.0)
        robot.drive.strafeRight(0.75, 40.0, false) 
        sleep(750)
        robot.teamMarker.up()
        robot.waitUntilNotBusy()
        robot.drive.spinRight(1.0, 24.0)
        robot.drive.forward(1.0, 24.0, false)
        robot.gathering.inBetween()
        sleep(500)
        robot.gathering.inOutMotor.runForTime(-1.0, 500L)
        robot.gathering.down()
        robot.waitUntilNotBusy()
    }
}
package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Robots.MecanumRobot
import org.BeehiveRobotics.Library.Util.BROpMode

@Autonomous(name="Test Multi Threading", group="Test")
class MultiThreadTest: BROpMode() {
    val robot: MecanumRobot  = MecanumRobot(this)
    override fun initialize() {
        opModeType = OpModeType.TeleOp
        robot.init()
    }
    override fun run() {
        robot.drive.forward(1.0, 12.0, false)
        addData("Main Thread ID", Thread.currentThread().id.toString())
        updateTelemetry()
        robot.waitUntilNotBusy()
    }
}
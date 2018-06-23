package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Robots.Kotlin.MecanumRobot
import org.BeehiveRobotics.Library.Util.Kotlin.BROpMode

@Autonomous(name="Testing Multi-Threading", group="Test")
class MulitThreadTest: BROpMode() {
    val robot: MecanumRobot  = MecanumRobot(this)
    override fun initialize() {
        setOpModeType(OpModeType.TeleOp)
        robot.init()
    }
    override fun run() {
        robot.drive.forward(1.0, 12.0, false)
        val threadID: Long = Thread.currentThread().id
        addData("Main Thread ID", threadID.toString())
        updateTelemetry()
        robot.waitUntilNotBusy()
    }
    override fun end() {
        robot.drive.stopMotors()
    }

}
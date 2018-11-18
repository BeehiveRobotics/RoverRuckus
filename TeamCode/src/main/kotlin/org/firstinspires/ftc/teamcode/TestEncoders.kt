package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode

@Autonomous(name="Test Encoders", group="RR2")
class TestEncoders(): BROpMode(OpModeType.Autonomous) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
    }
    override fun run() {
        robot.drive.forward(1.0, 32.0, waitForCompletion = false) 
        while(robot.isBusy) {
            dashboard.addLine(robot.drive.toString())
        }
    }
}
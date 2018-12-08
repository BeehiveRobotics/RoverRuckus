package org.firstinspires.ftc.teamcode.Test

import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Robot

@TeleOp(name="Testing that one encoder thing", group="Test")
class TestEncoderValue(): BROpMode(OpModeType.TeleOp) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
    }
    override fun onStartPressed() {
        robot.start()
    }
    override fun run() {
        robot.lift.climbMotor.rawPower = controller1.leftTrigger - controller1.rightTrigger
        dashboard.addData("Encoder value: ", robot.lift.climbMotor.currentPosition)
    }
}
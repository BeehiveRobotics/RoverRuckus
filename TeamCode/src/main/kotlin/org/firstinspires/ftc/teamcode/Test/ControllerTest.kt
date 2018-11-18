package org.firstinspires.ftc.teamcode.Test

import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

//@TeleOp(name="Test Controller", group="Test")
class ControllerTest: BROpMode(OpModeType.TeleOp) {
    override fun initialize() {}
    override fun run() {
        dashboard.addData("Left Stick X", controller1.leftStickX.toString())
        dashboard.addData("Left Stick Y", controller1.leftStickY.toString())
        dashboard.addData("Right Stick X", controller1.rightStickX.toString())
        dashboard.addData("Right Stick Y", controller1.rightStickY.toString())
        dashboard.addData("A", controller1.a.toString())
        dashboard.addData("B", controller1.b.toString())
        dashboard.addData("X", controller1.x.toString())
        dashboard.addData("Y", controller1.y.toString())
        dashboard.addData("Dpad up", controller1.dpadUp.toString())
        dashboard.addData("Dpad down", controller1.dpadDown.toString())
        dashboard.addData("Dpad left", controller1.dpadLeft.toString())
        dashboard.addData("Dpad right", controller1.dpadRight.toString())
        dashboard.addData("Left Bumper", controller1.leftBumper.toString())
        dashboard.addData("Right Bumper", controller1.rightBumper.toString())
        dashboard.addData("Left Trigger", controller1.leftTrigger.toString())
        dashboard.addData("Right Trigger", controller1.rightTrigger.toString())
        dashboard.addData("A toggle", controller1.aToggle.toString())
        dashboard.addData("B toggle", controller1.bToggle.toString())
        dashboard.addData("X toggle", controller1.xToggle.toString())
        dashboard.addData("Y toggle", controller1.yToggle.toString())
        dashboard.addData("Dpad up toggle", controller1.dpadUpToggle.toString())
        dashboard.addData("Dpad down toggle", controller1.dpadDownToggle.toString())
        dashboard.addData("Dpad left toggle", controller1.dpadLeftToggle.toString())
        dashboard.addData("Dpad right toggle", controller1.dpadRightToggle.toString())
        dashboard.addData("Left bumper toggle", controller1.leftBumperToggle.toString())
        dashboard.addData("Right bumper toggle", controller1.rightBumperToggle.toString())
    }
}
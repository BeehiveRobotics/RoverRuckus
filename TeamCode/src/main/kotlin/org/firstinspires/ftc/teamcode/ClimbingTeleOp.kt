package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name="Climbing TeleOp", group="RR2")
class ClimbingTeleOp(): BROpMode(OpModeType.TeleOp) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
    }
    override fun run() {
        robot.drive.drive(controller1.leftStickX, controller1.leftStickY, controller1.rightStickX, controller1.rightStickY)
        if(controller1.b) {
            robot.lift.climbingSpeed = controller1.leftTrigger - controller1.rightTrigger
        } else {
            robot.lift.deployingSpeed = controller1.leftTrigger - controller1.rightTrigger
        }
        if(controller1.aToggle) {
            robot.lift.toggleLock()
        }
        addLine(robot.lift.toString())
    }
}
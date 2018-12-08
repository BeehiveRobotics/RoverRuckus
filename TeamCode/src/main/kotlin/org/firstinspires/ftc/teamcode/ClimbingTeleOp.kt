package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name="Climbing TeleOp", group="RR2")
class ClimbingTeleOp(): BROpMode(OpModeType.TeleOp) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
    }
    override fun onStartPressed() {
        robot.start()
    }
    override fun run() {
        robot.drive.driveLeftRight(controller1.leftStickX, controller1.leftStickY, controller1.rightStickX, controller1.rightStickY)
        if(controller1.b) {
            robot.lift.climbingSpeed = controller1.rightTrigger - controller1.leftTrigger
        } else {
            robot.lift.deployingSpeed = controller1.rightTrigger - controller1.leftTrigger
        }
        dashboard.addLine(robot.lift.toString())
        dashboard.addLine(robot.lift.climbMotor.currentPosition.toString())
    }
}
package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
    
@TeleOp(name="TeleOp", group="RR2")
class RR2TeleOp(): BROpMode(OpModeType.TeleOp) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
    }
    override fun run() {
        robot.drive.drive(controller1.leftStickX, controller1.leftStickY, controller1.rightStickX, controller1.rightStickY)
        robot.gathering.inOutMotor.rawPower = controller1.rightTrigger - controller1.leftTrigger
        if(controller1.aToggle) {
            robot.gathering.toggleFlip()
        } 
        if(controller1.bToggle) {
            robot.gathering.toggleGathering()
        }
    }
}
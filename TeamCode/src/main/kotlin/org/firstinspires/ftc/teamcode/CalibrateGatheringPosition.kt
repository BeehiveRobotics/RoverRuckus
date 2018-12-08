package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
    
@TeleOp(name="Zack test thing", group="RR2")
class CalibrateGatheringPosition(): BROpMode(OpModeType.TeleOp) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
    }
    override fun run() {
        robot.drive.driveLeftRight(controller1.leftStickX, controller1.leftStickY, controller1.rightStickX, controller1.rightStickY)
        robot.gathering.inOutMotor.rawPower = controller1.rightTrigger - controller1.leftTrigger
        if(controller1.aToggle) {
            if(robot.gathering.isUp) {
                robot.gathering.down()
            } else {
                robot.gathering.up()
            }
        } 
        if(controller1.bToggle) {
            robot.gathering.toggleGathering()
        }
        if(controller1.dpadUpToggle && robot.gathering.UP_POSITION <= 1.0) {
            robot.gathering.UP_POSITION += 0.01
        }
        if(controller1.dpadDownToggle && robot.gathering.UP_POSITION >= 0.0) {
            robot.gathering.UP_POSITION -= 0.01
        }
        dashboard.addData("Position", robot.gathering.UP_POSITION)
    }
}
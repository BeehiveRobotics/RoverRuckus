package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
    
@TeleOp(name="TeleOp", group="RR2")
class RR2TeleOp(): BROpMode(OpModeType.TeleOp) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
    }
    override fun onStartPressed() {
        robot.start()
    }
    override fun run() {
        //Drive
        robot.drive.driveLeftRight(controller1.leftStickX, controller1.leftStickY, controller1.rightStickX, controller1.rightStickY)
        
        //Gathering
        if (controller2.x) {
            robot.gathering.gatherBackwards()
        } else {
            if(controller2.bToggle) {
                robot.gathering.toggleGathering()
            }
            if(!robot.gathering.isOn) {
                robot.gathering.off()
            }
        }
        if(controller2.aToggle)           robot.gathering.down()
        if(controller2.yToggle)           robot.gathering.inBetween()
        if(controller2.rightBumperToggle) robot.gathering.dump()
        if(controller2.leftBumper)        robot.gathering.up()
        robot.gathering.inOutMotor.rawPower = controller2.leftTrigger - controller2.rightTrigger
        //Deployment
        if(controller2.dpadUpToggle)      robot.deployment.reveal()
        if(controller2.dpadDownToggle)    robot.deployment.stow()
        if(controller2.dpadLeftToggle)    robot.deployment.left()
        if(controller2.dpadRightToggle)   robot.deployment.right()
        //Lift
        robot.lift.deployingSpeed = -controller2.leftStickY
        robot.lift.climbingSpeed = -(controller1.leftTrigger - controller1.rightTrigger)

    }
}
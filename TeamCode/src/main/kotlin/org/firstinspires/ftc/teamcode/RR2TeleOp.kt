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
        if     (controller1.dpadUp)    robot.drive.forward(0.4)
        else if(controller1.dpadDown)  robot.drive.backward(0.4)
        else if(controller1.dpadLeft)  robot.drive.strafeLeft(0.4)
        else if(controller1.dpadRight) robot.drive.strafeRight(0.4) 
        else                           robot.drive.driveLeftRight(controller1.leftStickX, controller1.leftStickY, controller1.rightStickX, controller1.rightStickY)
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
        if(controller2.a)                 robot.gathering.down()
        if(controller2.y)                 robot.gathering.inBetween()
        if(controller2.rightBumper)       robot.gathering.dump()
        if(controller2.leftBumper)        robot.gathering.up()
        if(controller1.leftBumper)        robot.gathering.up()
        robot.gathering.inOutMotor.rawPower = controller2.leftTrigger - controller2.rightTrigger
        //Deployment
        if(controller2.dpadUp)      robot.deployment.reveal()
        if(controller2.dpadDown)    robot.deployment.stow()
        if(controller2.dpadLeft)    robot.deployment.left()
        if(controller2.dpadRight)   robot.deployment.right()
        //Lift
        robot.lift.deployingSpeed = -controller2.leftStickY
        robot.lift.climbingSpeed = -(controller1.leftTrigger - controller1.rightTrigger)
        dashboard.addLine(robot.lift.toString())
        }
}
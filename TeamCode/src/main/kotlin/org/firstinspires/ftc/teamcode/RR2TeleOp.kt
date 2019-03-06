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
        robot.deployment.stow()
        robot.gathering.down()
        robot.gathering.on()
    }
    override fun run() {
        //Drive
        if     (controller1.dpadUp)    robot.drive.forward(0.4)
        else if(controller1.dpadDown)  robot.drive.backward(0.4)
        else if(controller1.dpadLeft)  robot.drive.strafeLeft(0.4)
        else if(controller1.dpadRight) robot.drive.strafeRight(0.4) 
        else                           robot.drive.driveLeftRight(controller1.leftStickX, controller1.leftStickY, controller1.rightStickX, controller1.rightStickY)
        if(controller1.x) robot.wiggle()
        //Gathering
        if (controller2.x) {
            robot.gathering.eject()
        }
        else {
            if(controller2.bToggle) {
                if(robot.gathering.isOn)   robot.gathering.off()
                else                       robot.gathering.on()
            }
        }
        if(controller2.aToggle)                 robot.gathering.down()
        if(controller2.yToggle)                 robot.gathering.inBetween()
        if(controller2.rightBumperToggle) {
            robot.lift.runForTime(-1.0, 350L, false)
            robot.gathering.dump()
        }
        if(controller2.leftBumperToggle)        robot.gathering.up()
        if(controller1.leftBumperToggle)        robot.gathering.up()
        robot.gathering.inOutMotor.power = controller2.leftTrigger - controller2.rightTrigger
        //Deployment
        if(controller2.dpadUpToggle)      robot.deployment.reveal()
        if(controller2.dpadDownToggle)    robot.deployment.stow()
        if(controller2.dpadLeftToggle)    robot.deployment.knock()
        if(controller2.dpadRightToggle)   robot.deployment.knock()
        if(controller1.yToggle)           robot.deployment.purge()
        //Lift
        robot.lift.power = controller2.leftStickY - (controller1.leftTrigger - controller1.rightTrigger)
        if(controller1.aToggle) robot.lift.lock()
        if(controller1.bToggle) robot.lift.unlock()

    }
}
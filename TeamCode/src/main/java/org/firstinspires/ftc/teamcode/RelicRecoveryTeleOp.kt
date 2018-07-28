package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "RRTeleOp", group = "RR")
class RelicRecoveryTeleOp(): BROpMode() {
    private var robot: RelicRecoveryRobot = RelicRecoveryRobot(this)
    override fun initialize() {
        robot = RelicRecoveryRobot(this)
        opModeType = OpModeType.TeleOp
        robot.init()
    }
    override fun run() {
        //drive
        if (Math.abs(controller1.leftStickX()) + Math.abs(controller1.leftStickY()) + Math.abs(controller1.rightStickX()) + Math.abs(controller1.rightStickY()) > Math.abs(controller2.leftStickX()) + Math.abs(controller2.leftStickY()) + Math.abs(controller2.rightStickX()) + Math.abs(controller2.rightStickY())) {
            if (controller1.rightBumper()) {
                robot.drive.drive(controller1.leftStickX() * robot.BUMPER_SLOW_SPEED, controller1.leftStickY() * robot.BUMPER_SLOW_SPEED, controller1.rightStickX() * robot.BUMPER_SLOW_SPEED, controller1.rightStickY() * robot.BUMPER_SLOW_SPEED)
            } else {
                robot.drive.drive(controller1.leftStickX(), controller1.leftStickY(), controller1.rightStickX(), controller1.rightStickY())
            }
        } else if (Math.abs(controller2.leftStickX()) + Math.abs(controller2.leftStickY()) + Math.abs(controller2.rightStickX()) + Math.abs(controller2.rightStickY()) > Math.abs(controller1.leftStickX()) + Math.abs(controller1.leftStickY()) + Math.abs(controller1.rightStickX()) + Math.abs(controller1.rightStickY())) {
            if (controller2.rightBumper()) {
                robot.drive.drive(controller2.leftStickX() * robot.BUMPER_SLOW_SPEED, controller2.leftStickY() * robot.BUMPER_SLOW_SPEED, controller2.rightStickX() * robot.BUMPER_SLOW_SPEED, controller2.rightStickY() * robot.BUMPER_SLOW_SPEED)
            } else {
                robot.drive.drive(controller2.leftStickX(), controller2.leftStickY(), controller2.rightStickX(), controller2.rightStickY())

            }
        } else {
            if (controller1.dpad_up()) robot.drive.forward(robot.D_PAD_SLOW_SPEED)
            else if (controller1.dpad_left()) robot.drive.strafeRight(robot.D_PAD_SLOW_SPEED)
            else if (controller1.dpad_down()) robot.drive.backward(robot.D_PAD_SLOW_SPEED)
            else if (controller1.dpad_right()) robot.drive.strafeLeft(robot.D_PAD_SLOW_SPEED)
            else if (controller2.dpad_left()) robot.drive.strafeLeft(robot.D_PAD_SLOW_SPEED)
            else if (controller2.dpad_right()) robot.drive.strafeRight(robot.D_PAD_SLOW_SPEED)
            else robot.drive.stopMotors()
        }
        if (controller1.leftBumper()) robot.drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT)

        if (controller2.leftBumper()) robot.drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)

        //ForkLift
        if (controller1.a()) robot.forklift.closeClaw()

        if (controller1.b()) {
            robot.forklift.openClaw()
            robot.jewelArm.up()
            robot.phone.faceSideways()
        }

        if (controller1.x()) robot.grabSecondGlyph()

        if(controller1.y()) robot.grabSecondGlyphSimple()
        
        robot.forklift.moveMotor(controller1.rightTrigger() - controller1.leftTrigger())

        //RelicClaw
        if (controller2.a()) robot.relicClaw.closeClaw()
        if (controller2.b()) robot.relicClaw.openClaw()
        if (controller2.dpad_up()) robot.relicClaw.up()
        if (controller2.dpad_down()) robot.relicClaw.down()
        if (controller2.x()) robot.relicClaw.pickup()
        if (controller2.y()) robot.relicClaw.driving()
        
        robot.relicClaw.moveMotor(controller2.leftTrigger() - controller2.rightTrigger())
    }
    override fun end() {
        robot.drive.stopMotors()
        robot.forklift.stop()
        robot.relicClaw.stop()
    }

}
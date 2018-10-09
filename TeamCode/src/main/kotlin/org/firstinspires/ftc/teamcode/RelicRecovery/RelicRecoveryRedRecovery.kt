package org.firstinspires.ftc.teamcode.RelicRecovery

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark
import com.qualcomm.robotcore.util.ElapsedTime
import org.BeehiveRobotics.Library.Util.AllianceColor

@Autonomous(name="RR Red Recovery", group = "RR")
class RelicRecoveryRedRecovery(): BROpMode(OpModeType.Autonomous) {
    private val robot = RelicRecoveryRobot(this)
    private var pictograph = RelicRecoveryVuMark.UNKNOWN
    private val runTime = ElapsedTime()
    override fun initialize() = robot.init()
    override fun run() {
        runTime.reset()
        robot.relicClaw.openClaw()
        robot.relicClaw.down()
        robot.jewelArm.knockJewel(AllianceColor.RED, waitForCompletion = false)
        robot.forklift.autoInit(waitforCompletion = false)
        pictograph = robot.phone.getMark(waitForCompletion = false)
        if (pictograph == RelicRecoveryVuMark.UNKNOWN) pictograph = RelicRecoveryVuMark.RIGHT
        robot.drive.forward(robot.DRIVE_OFF_BALANCE_BOARD_SPEED, robot.DRIVE_OFF_BALANCE_BOARD_DISTANCE)
        when(pictograph) {
            RelicRecoveryVuMark.LEFT -> robot.drive.forward(robot.MAX_SPEED, robot.MOVE_TOWARDS_CRYPTOBOX_DISTANCE_RED_RECOVERY + robot.CRYPTOBOX_COLUMNS_OFFSET)
            RelicRecoveryVuMark.CENTER -> robot.drive.forward(robot.MAX_SPEED, robot.MOVE_TOWARDS_CRYPTOBOX_DISTANCE_RED_RECOVERY)
            RelicRecoveryVuMark.RIGHT -> robot.drive.forward(robot.MAX_SPEED, robot.MOVE_TOWARDS_CRYPTOBOX_DISTANCE_RED_RECOVERY - robot.CRYPTOBOX_COLUMNS_OFFSET + 1)
        }
        robot.drive.rightGyro(robot.MAX_SPEED, -90.0)
        robot.forklift.moveMotor(-1.0, 200.0, waitForCompletion=false)
        robot.phone.faceFront()
        robot.drive.forward(robot.MAX_SPEED, 3.0)
        robot.forklift.openClaw()
        robot.drive.forward(robot.MAX_SPEED, 5.0)
        robot.drive.backward(robot.MAX_SPEED, 6.0)
    }
}
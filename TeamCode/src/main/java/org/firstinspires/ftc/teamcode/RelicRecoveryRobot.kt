package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.MecanumDrive
import com.qualcomm.robotcore.util.ElapsedTime
import org.BeehiveRobotics.Library.Robots.Robot

class RelicRecoveryRobot(private val opMode: BROpMode): Robot(opMode) {
    internal lateinit  var drive: MecanumDrive
    internal lateinit var forklift: RelicRecoveryForklift
    internal lateinit var relicClaw: RelicRecoveryRelicClaw
    internal lateinit var jewelArm: RelicRecoveryJewelArm
    internal lateinit var phone: RelicRecoveryPhone
    val HALF_SPEED = 0.5
    val DRIVE_INTO_GLYPH_PIT_DISTANCE = 28.0
    val DRIVE_INTO_GLYPHS_SPEED = 0.6
    val DRIVE_INTO_GLYPHS_DISTANCE = 4.0
    val MIN_MOVE_SPEED = 0.25
    val MAX_SPEED = 1.0
    val MIN_SPIN_SPEED = 0.5
    val MIN_STRAFE_SPEED = 0.35
    val DRIVE_OFF_BALANCE_BOARD_SPEED = 0.6 //0.6 works for sure. Testing faster
    val DRIVE_OFF_BALANCE_BOARD_DISTANCE = 18.0
    val STRAFING_PAST_CRYPTOBOX_SPEED = 0.75
    val DRIVE_INTO_CRYPTOBOX_SPEED = 0.8
    val DEFAULT_MOVING_TOWARDS_CRYPTOBOX_DISTANCE_FAR_POSITION = 14.0
    val CRYPTOBOX_COLUMNS_OFFSET = 8.0
    val CRYPTOBOX_COLUMNS_OFFSET_FAR = 11.0
    val DRIVE_TO_CYRPTOBOX_DISTANCE_FAR = 24.0
    val DISTANCE_TO_FAR_COLUMN = 32.75
    val DISTANCE_TO_CENTER_COLUMN = 25.5
    val DISTANCE_TO_CLOSE_COLUMN = 17.5
    val D_PAD_SLOW_SPEED = 0.25
    val BUMPER_SLOW_SPEED = 0.25
    val MOVE_TOWARDS_CRYPTOBOX_DISTANCE_RED_RECOVERY = 31.0
    val STRAFING_DAMPEN_FACTOR_FOR_MULTI_GLYPH = 0.1

    fun init() {
        drive = MecanumDrive(opMode)
        drive.init()
        forklift = RelicRecoveryForklift(opMode)
        relicClaw = RelicRecoveryRelicClaw(opMode)
        jewelArm = RelicRecoveryJewelArm(opMode)
        phone = RelicRecoveryPhone(opMode)
    }
    fun grabSecondGlyph() {
        forklift.openClaw()
        drive.backward(1.0, 4.0)
        forklift.moveUntilDown()
        drive.forward(1.0, 5.0)
        forklift.closeClaw()
        sleep(250)
        forklift.moveMotor(1.0, 300.0)
    }
    fun grabSecondGlyphSimple() {
        forklift.openClaw()
        drive.backward(1.0, 1.0)
        forklift.moveUntilDown()
        drive.forward(1.0, 1.5)
        forklift.closeClaw()
        sleep(250)
        forklift.moveMotor(1.0, 300.0)
    }
    
    override fun waitUntilNotBusy() {
        while(opMode.opModeIsActive() && drive.isBusy && forklift.isBusy && relicClaw.isBusy && jewelArm.isBusy && phone.isBusy) {}
    }
}
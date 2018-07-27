package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.MecanumDrive
import com.qualcomm.robotcore.util.ElapsedTime

class RelicRecoveryRobot(private val opMode: BROpMode) {
    internal lateinit  var drive: MecanumDrive
    internal lateinit var forklift: RelicRecoveryForklift
    internal lateinit var relicClaw: RelicRecoveryRelicClaw
    final val BUMPER_SLOW_SPEED: Double = 0.25
    final val D_PAD_SLOW_SPEED: Double = 0.25
    fun init() {
        drive = MecanumDrive(opMode)
        forklift = RelicRecoveryForklift(opMode)
        relicClaw = RelicRecoveryRelicClaw(opMode)
    }
    fun grabSecondGlyph() {
        forklift.openClaw()
        drive.backward(1.0, 4.0)
        forklift.moveUntilDown()
        drive.forward(1.0, 5.0)
        forklift.closeClaw()
        sleep(250)
        forklift.moveMotor(1.0, 300)
    }
    fun grabSecondGlyphSimple() {
        forklift.openClaw()
        drive.backward(1.0, 1.0)
        forklift.moveUntilDown()
        drive.forward(1.0, 1.5)
        forklift.closeClaw()
        sleep(250)
        forklift.moveMotor(1.0, 300)
    }
    private fun sleep(milliseconds: Long) {
        val time: ElapsedTime = ElapsedTime()
        time.reset()
        while(time.milliseconds() < milliseconds) {
            if(!opMode.opModeIsActive()) {
                return
            }
        }
    }



}
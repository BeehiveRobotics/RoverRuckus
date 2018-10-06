package org.firstinspires.ftc.teamcode


import com.disnodeteam.dogecv.DogeCV
import com.disnodeteam.dogecv.CameraViewDisplay
import com.disnodeteam.dogecv.detectors.roverrukus.GoldDetector
import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name="KT gold detector test", group = "DogeCV")
class GoldDetectorEx: BROpMode(BROpMode.OpModeType.TeleOp) {

    val detector = GoldDetector()

    override fun initialize() {
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance())
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA
        detector.maxAreaScorer.weight = 0.005

        detector.ratioScorer.weight = 5.0
        detector.ratioScorer.perfectRatio = 1.0

        detector.enable()
    }
    override fun run() {
        showData("XPos", detector.xPosition)
    }

    override fun end() {
        detector.disable()
    }
}
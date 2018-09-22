package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.RoverRuckusCV.Detectors.SampleDetector
import org.BeehiveRobotics.RoverRuckusCV.CameraViewDisplay
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name="TestCV", group="Test")
class CVTest: BROpMode(OpModeType.TeleOp) {
    private val cv: SampleDetector = SampleDetector()
    override fun initialize() {
        cv.init(hardwareMap.appContext, CameraViewDisplay.getInstance())
        cv.enable()
    }
    override fun run() {
        while(opModeIsActive()) {
        }
    }
    override fun end() {
        cv.disable()
    }
}
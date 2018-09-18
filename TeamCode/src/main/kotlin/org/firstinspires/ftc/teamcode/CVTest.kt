package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.RoverRuckusCV.Detectors.SampleDetector

class CVTest: BROpMode(OpModeType.TeleOp) {
    private lateinit var cv: SampleDetector
    override fun initialize() {
        cv = SampleDetector(this)
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
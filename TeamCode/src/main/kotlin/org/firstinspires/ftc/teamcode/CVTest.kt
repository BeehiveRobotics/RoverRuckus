package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.RoverRuckusCV.Detectors.SampleDetector
import org.BeehiveRobotics.RoverRuckusCV.CameraViewDisplay
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime

@TeleOp(name="TestCV", group="Test")
class CVTest: BROpMode(OpModeType.Autonomous) {
    private val cv: SampleDetector = SampleDetector()
    override fun initialize() {
        cv.init(hardwareMap.appContext, CameraViewDisplay.getInstance())
        cv.enable()
    }
    override fun run() {
        val runTime = ElapsedTime()
        while(runTime.seconds()<2){}
        cv.disable()
    }
    override fun end() {
    }
}
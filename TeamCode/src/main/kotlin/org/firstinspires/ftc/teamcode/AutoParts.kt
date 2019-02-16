package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.firstinspires.ftc.teamcode.CV.GoldMineralPosition

@Autonomous(name="Testing auto", group="RR2")
class AuroParts(): BROpMode(BROpMode.OpModeType.Autonomous) {
    private val robot = Robot(this)
    override fun initialize() {
        robot.init()
        robot.cv.startCV()
        robot.cv.stopCV()        
    }
    override fun onStartPressed() {
        robot.start()
        robot.lift.unlock()
    }
    override fun run() {
        robot.land()
    }
}
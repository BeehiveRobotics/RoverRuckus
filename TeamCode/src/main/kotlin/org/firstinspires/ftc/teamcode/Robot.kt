package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.MecanumDrive
import com.qualcomm.robotcore.util.ElapsedTime
import org.BeehiveRobotics.Library.Robots.Robot

class Robot(private val opMode: BROpMode): Robot(opMode) {
    internal lateinit var drive: MecanumDrive
    internal lateinit var lift: Lift
    override fun init() {
        drive = MecanumDrive(opMode)
        drive.init()
        lift = Lift(opMode)
    }
    
    override fun waitUntilNotBusy() {
        while(opMode.opModeIsActive() && drive.isBusy && lift.isBusy) {}
    }
}
package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.MecanumDrive
import com.qualcomm.robotcore.util.ElapsedTime
import org.BeehiveRobotics.Library.Robots.Robot

internal class Robot(private val opMode: BROpMode): Robot(opMode) {
    internal lateinit var drive: MecanumDrive
    internal lateinit var lift: Lift
    internal lateinit var deployment: Deployment
    internal lateinit var gathering: Gathering
    
    override fun init() {
        drive = MecanumDrive(opMode)
        drive.init()
        lift = Lift(opMode)
        lift.init()
        deployment = Deployment(opMode)
        deployment.init()
        gathering = Gathering(opMode)
        gathering.init()
    }
    fun land() {

    }
    override fun start() {
        lift.start()
    }
    override fun waitUntilNotBusy() {
        while(opMode.opModeIsActive() && drive.isBusy && lift.isBusy && deployment.isBusy && gathering.isBusy) {}
    }
}
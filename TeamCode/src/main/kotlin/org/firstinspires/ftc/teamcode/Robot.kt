package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.BeehiveRobotics.Library.Robots.Robot

internal class Robot(private val opMode: BROpMode): Robot(opMode), Runnable {
    internal lateinit var drive: Drive
    internal lateinit var lift: Lift
    internal lateinit var deployment: Deployment
    internal lateinit var gathering: Gathering
    internal lateinit var cv: CV
    private var showTelemetry = true
    
    fun land() {
        opMode.dashboard.showLine("Landing")
        deployment.stow()
        lift.deploymentMotor.rawPower = -0.16
        lift.climbMotor.runToPosition(13600.0, -1.0)
        lift.deploymentMotor.rawPower = -0.3
        drive.strafeLeft(0.75, 2.0)
        lift.deploymentMotor.rawPower = 0.0
        opMode.dashboard.showLine("Done landing")
    }

    fun moveMineralsFromGatheringToDeploymentAndReadyDeployment() {
        gathering.dump()
        lift.deploymentMotor.runToPosition(4000.0, 1.0)
    }
    
    override fun init() {
        drive = Drive(opMode)
        drive.init()
        lift = Lift(opMode)
        lift.init()
        deployment = Deployment(opMode)
        deployment.init()
        gathering = Gathering(opMode)
        gathering.init()
        cv = CV(opMode)
        cv.init()
    }
    
    override fun start() {
        drive.start()
        lift.start()
        deployment.start()
        gathering.start()
        
    }
    fun startTelemetry() {
        showTelemetry = true
        val thread = Thread(this)
        thread.start()
    }
    fun stopTelemetry() {
        showTelemetry = false
    }

    override fun waitUntilNotBusy() {
        while(opMode.opModeIsActive() && isBusy) {}
    }
    var isBusy = false
        private set
        get() = drive.isBusy || lift.isBusy || deployment.isBusy || gathering.isBusy

    override fun run() {
        while(opMode.opModeIsActive() && showTelemetry) {
            opMode.dashboard.addLine(drive.toString())
            opMode.dashboard.addLine(lift.toString())
            opMode.dashboard.addLine(deployment.toString())
            opMode.dashboard.addLine(gathering.toString())
        }
        Thread.currentThread().interrupt()
    }

}
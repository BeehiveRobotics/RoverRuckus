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
    internal lateinit var teamMarker: TeamMarker
    private var showTelemetry = true
    
    fun land() {
        opMode.dashboard.showLine("Landing")
        deployment.stow()
        gathering.up()
        teamMarker.outOfTheWay()
        lift.deploymentMotor.rawPower = -0.25
        lift.climbMotor.runToPosition(-1.0, 13600.0)
        drive.forward(0.5, 0.5)
        drive.leftForward(0.6, 1.0)
        lift.deploymentMotor.rawPower = -0.275
        drive.strafeLeft(0.75, 3.1) //was 3.0
        drive.leftBackward(0.6, 1.0)
        lift.deploymentMotor.rawPower = 0.0
        opMode.dashboard.showLine("Done landing")
        sleep(250)
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
        teamMarker = TeamMarker(opMode)
        teamMarker.init()
    }
    
    override fun start() {
        drive.start()
        lift.start()
        deployment.start()
        gathering.start()
        cv.start()
        teamMarker.start()
        
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
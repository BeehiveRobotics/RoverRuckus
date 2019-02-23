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
    
    enum class Tasks {
        DeploymentUp, DeploymentDown, None
    }
    var task = Tasks.None
    fun land() { 
        opMode.dashboard.showLine("Landing")
        deployment.stow()
        teamMarker.outOfTheWay()
        lift.unlock()
        lift.runForTime(-1.0, 250L)
        lift.runToPosition(1.0, 2500.0) 
        lift.runForTime(1.0, 100L)
        drive.forward(0.3, 1.0)
        drive.spinLeft(0.3, 1.0)
        opMode.dashboard.showLine("Done landing")

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
        val thread = Thread(this)
        thread.start()
    }

    override fun waitUntilNotBusy() {
        while(opMode.opModeIsActive() && isBusy) {}
    }
    var isBusy = false
        private set
        get() = drive.isBusy || lift.isBusy || deployment.isBusy || gathering.isBusy

    fun deploymentDown() {
        task = Tasks.DeploymentDown
    }
    fun deploymentUp() {
        task = Tasks.DeploymentUp
    }

    override fun run() {
        
    }

}
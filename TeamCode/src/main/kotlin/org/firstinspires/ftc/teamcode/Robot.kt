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
        lift.runForTime(-1.0, 1000L)
        lift.runToPosition(1.0, 2500.0) 
        lift.runForTime(1.0, 100L)
        drive.forward(0.3, 1.0)
        drive.spinLeft(0.3, 1.2)
        opMode.dashboard.showLine("Done landing")
        sleep(300L)
    }
    fun sample(goldMineralPosition: CV.GoldMineralPosition) {
        drive.spinRight(0.3, 1.2)
        drive.forward(1.0, 10.0, waitForCompletion = false)
        sleep(150)
        lift.runToPosition(-1.0, 2500.0, waitForCompletion = false)
        drive.waitUntilNotBusy()
        when(goldMineralPosition) {
            CV.GoldMineralPosition.LEFT ->   drive.strafeLeft(0.7, 16.0)
            //GoldMineralPosition.CENTER ->  drive.strafeRight(0.4, 4.0)
            CV.GoldMineralPosition.RIGHT ->  drive.strafeRight(0.7, 18.0)
        }
        drive.forward(0.75, 14.0)   
        drive.backward(0.75, 11.0)
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
    fun wiggle() {
        
        drive.setRawPowers((-5..5).random().toDouble() / 10.0, (-5..5).random().toDouble() / 10.0, (-5..5).random().toDouble() / 10.0, (-5..5).random().toDouble() / 10.0)
        deployment.setFlipPosition((900..1000).random().toDouble() / 1000.0)
        sleep(30)
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
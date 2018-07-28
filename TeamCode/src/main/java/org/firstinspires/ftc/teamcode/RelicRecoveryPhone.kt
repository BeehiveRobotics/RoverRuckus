package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Servos.Servo
import com.qualcomm.robotcore.util.ElapsedTime
import com.vuforia.CameraDevice
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables
import org.firstinspires.ftc.robotcore.internal.vuforia.VuforiaLocalizerImpl
import org.BeehiveRobotics.Library.Systems.RobotSystem

class RelicRecoveryPhone(private val opMode: BROpMode): RobotSystem(opMode), Runnable {
    private val SIDE_POSITION = 0.5
    val  PHONE_DISTANCE_OFFSET = 3.0
    val parameters = VuforiaLocalizer.Parameters(opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName()))
    init {
        parameters.vuforiaLicenseKey = "AQfaFkD/////AAAAGXGcl3Y64kcghjX73kddwxOG8QFmwZwdDenQL/6cYT4JrZ70fydV0F5+iIWald5VzqX9BOtH9HwJ93W9oSnZmSwZSEQbnV3ELVR08qyIoujP5Z7O5p9yyepVydgdsjNw2shES0SmGoqhJF25ZIBN2YRVAYM++FTu4nuEEpLxN9LzbnrYLEfZB6mcuV9jea6D+CLXoQW7VpRpey73HjKCxPw1Hs3CjRx9/80Z6AR8YNjr3Yqx5MSZWNIn48rSR+nC0urM6YLs8xBwNA662icRKwkgAoCUXehfvxjK6LcSCnuQKG76IlOmSp3SZB9MFJ1HasxaFLxfS1xEa+6fdA7jE/WhukyuNvzmOVrWatS2WWDm"
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK 
    }
    private val vuforia = RelicRecoveryClosableVuforiaLocalizer(parameters)
    private var vuMark = RelicRecoveryVuMark.UNKNOWN
    private val relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark")
    private val relicTemplate = relicTrackables.get(0)
    private val servo = Servo(opMode, "ps")
    private val PICTOGRAPH_POSITION = 0.5
    private val FRONT_POSITION = 0.0
    private val isCameraOpened = false
    private var task = Tasks.GetMark

    enum class Tasks {
        GetMark, None
    }
    fun getMark(waitForCompletion: Boolean = true): RelicRecoveryVuMark {
        isBusy = true
        if(!waitForCompletion) {
            this.task = Tasks.GetMark
            val thread = Thread(this)
            thread.start()
        }
        CameraDevice.getInstance().setFlashTorchMode(true)
        relicTrackables.activate()
        setServoPosition(PICTOGRAPH_POSITION)
        val time = ElapsedTime()
        while(this.vuMark == RelicRecoveryVuMark.UNKNOWN) {
          vuMark = RelicRecoveryVuMark.from(relicTemplate)
          if(time.seconds()>2) {
              break
          }
        }
        opMode.telemetry.addData("Pictograph", "%s visible", vuMark)
        opMode.telemetry.update()
        closeVuforia()
        isBusy = false
        return vuMark
    }
    private fun setServoPosition(position: Double) {
      servo.setPosition(position)
    }
    fun closeVuforia() {
        vuforia.close()
    }
    fun faceFront() {setServoPosition(FRONT_POSITION)}

    fun faceSideways() {
        setServoPosition(SIDE_POSITION)
    }
    override fun run() {
        isBusy = true
        when(task) {
            Tasks.None -> return
            Tasks.GetMark -> getMark()
        }
        isBusy = false
    }
}
package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.RobotSystem

import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector
import org.firstinspires.ftc.robotcore.external.tfod.Recognition

class CV(private val opMode: BROpMode): RobotSystem(opMode) {
    private val TFOD_MODEL_ASSET = "RoverRuckus.tflite"
    private val LABEL_GOLD_MINERAL = "Gold Mineral"
    private val LABEL_SILVER_MINERAL = "Silver Mineral"

    private val VUFORIA_KEY = "AQfaFkD/////AAAAGXGcl3Y64kcghjX73kddwxOG8QFmwZwdDenQL/6cYT4JrZ70fydV0F5+iIWald5VzqX9BOtH9HwJ" + 
    "93W9oSnZmSwZSEQbnV3ELVR08qyIoujP5Z7O5p9yyepVydgdsjNw2shES0SmGoqhJF25ZIBN2YRVAYM++FTu4nuEEpLxN9LzbnrYLEfZB6mcuV9jea6D+C" + 
    "LXoQW7VpRpey73HjKCxPw1Hs3CjRx9/80Z6AR8YNjr3Yqx5MSZWNIn48rSR+nC0urM6YLs8xBwNA662icRKwkgAoCUXehfvxjK6LcSCnuQKG76IlOmSp3S" + 
    "ZB9MFJ1HasxaFLxfS1xEa+6fdA7jE/WhukyuNvzmOVrWatS2WWDm"

    private lateinit var vuforia: VuforiaLocalizer

    private lateinit var tfod: TFObjectDetector

    private lateinit var allShapes: MutableList<Recognition> 

    private var xTotal = 0.0f

    var highX = Float.MIN_VALUE

    var lowX = Float.MAX_VALUE

    var frames = 0.0f

    var lastMineralCount = 0

    enum class GoldMineralPosition {
        LEFT, CENTER, RIGHT, UNKNOWN
    }

    override fun init() {
        val parameters = VuforiaLocalizer.Parameters()
        parameters.vuforiaLicenseKey = this.VUFORIA_KEY
        parameters.cameraDirection = CameraDirection.BACK
        this.vuforia = ClassFactory.getInstance().createVuforia(parameters)
    }
    fun startCV() {
        val tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName())
        val tfodParameters = TFObjectDetector.Parameters(tfodMonitorViewId)
        this.tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia)
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL)
        tfod.activate()
    }

    fun getGoldMineralPosition(framesToTest: Int = 30): GoldMineralPosition {
        var position = GoldMineralPosition.UNKNOWN
        var left = 0
        var center = 0
        var right = 0
        for(frame in 1..framesToTest) {
            val newShapes = tfod.getRecognitions()
            if(newShapes!=null) {
                for(shape in newShapes) {
                    if(shape.getLabel().equals(LABEL_GOLD_MINERAL)) {
                        val xPos = (shape.getLeft() + shape.getRight()) / 2
                        val yPos = shape.getTop()
                        if(yPos>0.7*shape.getImageHeight()) {
                            if(xPos>0.5*shape.getImageWidth()) {
                                center++
                            } else {
                                left++
                            }
                        } else {
                            right++
                        }
                    } else {
                        right++
                    }
                }
            }
        }
        return if(right>left && right>center) GoldMineralPosition.RIGHT else if (center>left && center>right) GoldMineralPosition.CENTER else if(left>right && left>center) GoldMineralPosition.LEFT else GoldMineralPosition.UNKNOWN
    }
    fun stopCV() {
        tfod.shutdown()
    }
}
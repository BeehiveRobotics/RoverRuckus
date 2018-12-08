package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector
import org.firstinspires.ftc.robotcore.external.tfod.Recognition

import org.BeehiveRobotics.Library.Util.BROpMode


@TeleOp(name = "TensorFlow Vuforia Object Detection", group = "Concept")
class ConceptTensorFlowObjectDetection(): BROpMode(BROpMode.OpModeType.TeleOp) {
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

    override fun initialize() {
        initVuforia()
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod()
        } else {
            dashboard.addData("Sorry!", "This device is not compatible with TFOD")
            sleep(1000)
        }
        if (this::tfod.isInitialized) tfod.activate()
        allShapes = tfod.getUpdatedRecognitions()

    }
    override fun run() {
        frames++
        if (this::tfod.isInitialized) {
            val newShapes = tfod.getUpdatedRecognitions()
            if(newShapes != null) {
                dashboard.addData("Minerals Found", newShapes.size)
                for(shape in newShapes) {
                    allShapes.add(shape)
                }
            } else {
                dashboard.addLine("No new minerals found")
            }
            for(shape in allShapes) {
                val pos = shape.getLeft()
                if(pos > highX) highX = pos
                if(pos < lowX) lowX = pos
                xTotal += pos
                //if(shape.getLabel().equals(LABEL_GOLD_MINERAL)) addData("Gold mineral x pos", shape.getLeft())
            }
            val newMineralCount = allShapes.size-lastMineralCount
            dashboard.addData("Highest X value", highX)
            dashboard.addData("Lowest X value", lowX)
            dashboard.addData("Number of gold minerals", allShapes.size / frames)
            dashboard.addData("Average X value", xTotal/frames)
            dashboard.addData("New minerals", newMineralCount-frames)
            dashboard.update()
            lastMineralCount = allShapes.size
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.

            /*
            var updatedRecognitions = tfod.getUpdatedRecognitions()
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size.toString())
                if (updatedRecognitions.size == 3) {
                    var goldMineralX = -1
                    var silverMineral1X = -1
                    var silverMineral2X = -1
                    for (recognition in updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = recognition.getLeft().toInt()
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = recognition.getLeft().toInt()
                        } else {
                            silverMineral2X = recognition.getLeft().toInt()
                        }
                    }
                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Left")
                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Right")
                        } else {
                            telemetry.addData("Gold Mineral Position", "Center")
                        }
                    }
                }
                telemetry.update()
            }
            */
        }   
    }

    override fun end() {
        if (this::tfod.isInitialized) {
            tfod.shutdown()
        }
    }
    /**
     * Initialize the Vuforia localization engine.
     */
    fun initVuforia() {
        val parameters = VuforiaLocalizer.Parameters()
        parameters.vuforiaLicenseKey = this.VUFORIA_KEY
        parameters.cameraDirection = CameraDirection.BACK
        this.vuforia = ClassFactory.getInstance().createVuforia(parameters)
    }
    private fun initTfod() {
        val tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName())
        val tfodParameters = TFObjectDetector.Parameters(tfodMonitorViewId)
        this.tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia)
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL)
    }
}

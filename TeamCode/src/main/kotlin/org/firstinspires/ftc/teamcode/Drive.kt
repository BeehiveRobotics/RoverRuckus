package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Systems.MecanumDrive
import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Sensors.REVColorSensor
import com.qualcomm.robotcore.util.ElapsedTime

class Drive(opMode: BROpMode): MecanumDrive(opMode) {
    val rightCS = REVColorSensor(opMode, "drcs")
    val leftCS = REVColorSensor(opMode, "dlcs")
    var showTelemetry = false

    fun lineLineUp() {
        val expectedValue = 0//who knows what this needs to be
        val k =  0.03
        var lHSV = leftCS.HSV()
        var rHSV = rightCS.HSV()
        val runTime = ElapsedTime()
        while(runTime.seconds() < 2) {
            super.drive(lHSV[1]*k - expectedValue, rHSV[1]*k - expectedValue)
        }
    }
    override fun toString(): String {
        return super.toString() + "\n" + 
            "Left Color Sensor: " + "\n" + 
            "\t" + "Red: " + leftCS.RGB()[0] + "\n" + 
            "\t" + "Green: " + leftCS.RGB()[1] + "\n" + 
            "\t" + "Blue: " + leftCS.RGB()[2] + "\n" + 
            "\t" + "Hue: " + leftCS.HSV()[0] + "\n" + 
            "\t" + "Saturation: " + leftCS.HSV()[1] + "\n" + 
            "\t" + "Value: " + leftCS.HSV()[2] + "\n" + 
            "Right Color Sensor: " + "\n" + 
            "\t" + "Red: " + rightCS.RGB()[0] + "\n" + 
            "\t" + "Green: " + rightCS.RGB()[1] + "\n" + 
            "\t" + "Blue: " + rightCS.RGB()[2] + "\n" + 
            "\t" + "Hue: " + rightCS.HSV()[0] + "\n" + 
            "\t" + "Saturation: " + rightCS.HSV()[1] + "\n" + 
            "\t" + "Value: " + rightCS.HSV()[2]
    }
}
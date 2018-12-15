package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Systems.MecanumDrive
import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Sensors.REVColorSensor
import com.qualcomm.robotcore.util.ElapsedTime
import org.BeehiveRobotics.Library.Motors.Motor

class Drive(opMode: BROpMode): MecanumDrive(opMode, gearRatio = 0.5) {
    val rightCS = REVColorSensor(opMode, "drcs")
    val leftCS = REVColorSensor(opMode, "dlcs")
    var showTelemetry = false

    override fun init() {
        super.init()
        model = Motor.MotorModel.NEVEREST20
        super.MIN_SPEED = 0.25
        
    }

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
    override fun toString(): String =
        super.toString() + "\n" + 
        "Left Color Sensor: " + "\n" + 
        "\tRed: ${leftCS.RGB()[0]}\n" + 
        "\tGreen: ${leftCS.RGB()[1]}\n" + 
        "\tBlue: ${leftCS.RGB()[2]}\n" + 
        "\tHue: ${leftCS.HSV()[0]}\n" + 
        "\tSaturation: ${leftCS.HSV()[1]}\n" + 
        "\tValue: ${leftCS.HSV()[2]}\n" + 
        "Right Color Sensor: \n" + 
        "\tRed: ${rightCS.RGB()[0]}\n" + 
        "\tGreen: ${rightCS.RGB()[1]}\n" + 
        "\tBlue: ${rightCS.RGB()[2]}\n" + 
        "\tHue: ${rightCS.HSV()[0]}\n" + 
        "\tSaturation: ${rightCS.HSV()[1]}\n" + 
        "\tValue: ${rightCS.HSV()[2]}"
}
package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Servos.Servo
import org.BeehiveRobotics.Library.Util.AllianceColor
import org.BeehiveRobotics.Library.Sensors.REVColorSensor

class RelicRecoveryJewelArm(private val opMode: BROpMode) {
    internal val upDownServo = Servo(opMode, "jav")
    internal val endServo = Servo(opMode, "jak")
    internal val cs = REVColorSensor(opMode, "jacs")
    private val DOWN_POSITION = 0.0
    private val UP_POSITION = 1.0
    private val RIGHT_POSITION = 0.8
    private val LEFT_POSITION = 0.2
    private val MIDDLE_POSITION = 0.0
    private val MIN_COLOR_DETECTION_THRESHOLD = 25.0

    fun down() = setUpDownPosition(DOWN_POSITION)
    fun up() = setUpDownPosition(UP_POSITION)

    fun right() = setEndPosition(RIGHT_POSITION)
    fun left() = setEndPosition(LEFT_POSITION)
    fun middle() = setEndPosition(MIDDLE_POSITION)

    fun init() {
        up()
        cs.enableLED(true)
    }
    fun knockJewel(allianceColor: AllianceColor) {
        var jewelColor = AllianceColor.UNKNOWN
        if(cs.red > cs.blue) jewelColor = AllianceColor.RED
        else jewelColor = AllianceColor.BLUE
        if(allianceColor == AllianceColor.RED) {
            if(jewelColor == AllianceColor.RED) right()
            else left()
        } else {
            if(jewelColor == AllianceColor.BLUE) right()
            else left()
        }    
    }
    fun setEndPosition(position: Double) {
        endServo.setPosition(position)
    }
    fun setUpDownPosition(position: Double) {
        upDownServo.setPosition(position)
    }

}
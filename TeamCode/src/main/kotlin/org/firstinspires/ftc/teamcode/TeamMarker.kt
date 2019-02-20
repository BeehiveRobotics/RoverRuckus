package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Servos.Servo
import org.BeehiveRobotics.Library.Systems.RobotSystem

class TeamMarker(private val opMode: BROpMode): RobotSystem(opMode) {
    private val servo = Servo(opMode, "tms")
    private val UP_POSITION = 0.5
    private val OUT_OF_THE_WAY_POSITION = 0.7
    private val DOWN_POSITION = 1.0
    fun up() {
        setServoPosition(UP_POSITION)
    }
    fun outOfTheWay() {
        setServoPosition(OUT_OF_THE_WAY_POSITION)
    }
    fun down() {
        setServoPosition(DOWN_POSITION)
    }
    private fun setServoPosition(position: Double) {
        servo.position = position
    }
}
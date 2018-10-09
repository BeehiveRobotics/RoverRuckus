package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Motors.Motor

class Lift(private val opMode: BROpMode): RobotSystem(opMode) {
    val motor = Motor(opMode, "lm")
}
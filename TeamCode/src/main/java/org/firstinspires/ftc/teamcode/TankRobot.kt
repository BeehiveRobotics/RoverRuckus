package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Motors.Kotlin.KTTankDrive
import org.BeehiveRobotics.Library.Util.Kotlin.BROpMode

class TankRobot(opMode: BROpMode) {
    private val opMode: BROpMode = opMode
    val drive: KTTankDrive = KTTankDrive(opMode)
    fun init() {
        drive.mapHardware()
        drive.init()
    }

    fun stop() {
        drive.stopMotors()
    }

    companion object {
        fun sleep(milliseconds: Long) {
            try {
                Thread.sleep(milliseconds)
            } catch (e: Exception) {
            }
        }
    }

}
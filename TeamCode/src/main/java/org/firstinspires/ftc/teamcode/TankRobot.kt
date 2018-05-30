package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Motors.Kotlin.KTTankDrive
import org.BeehiveRobotics.Library.Util.BROpMode

class TankRobot (opMode:BROpMode){
    private val opMode: BROpMode = opMode
    val drive: KTTankDrive = KTTankDrive(opMode).setGearedType(KTTankDrive.GearedType.NORMAL)
    fun init() {
        drive.mapHardware()
        drive.init()
    }
    companion object {
        fun Sleep(milliseconds: Long) {
            try {
                Thread.sleep(milliseconds)
            } catch(e: Exception) {}
        }
    }

}
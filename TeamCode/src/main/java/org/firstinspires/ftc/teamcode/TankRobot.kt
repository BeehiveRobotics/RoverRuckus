package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Motors.KTTankDrive
import org.BeehiveRobotics.Library.Util.BROpMode

class TankRobot (opMode:BROpMode){
    private var opMode: BROpMode = opMode
    var drive: KTTankDrive = KTTankDrive(opMode).setGearedType(KTTankDrive.GearedType.NORMAL)
    fun init() {
        drive.init()
    }

}
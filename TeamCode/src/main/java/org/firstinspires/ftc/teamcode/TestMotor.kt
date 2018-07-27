package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Motors.Motor

@Autonomous(name="Test Motor", group="Test")
class TestMotor: BROpMode() {
    private lateinit var motor: Motor
    override fun initialize() {
        opModeType = OpModeType.Autonomous
        this.motor = Motor(this, "fl")
    }
    override fun run() {
        this.motor.target = 1120.0 * 10
        while(!(this.motor.isAtTarget())) {
            this.motor.power = 1.0
        }

    }
    override fun end() {
        this.motor.stopMotor()
    }


}
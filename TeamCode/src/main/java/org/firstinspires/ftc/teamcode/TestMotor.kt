package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Motors.*

@Autonomous(name="TestMotorRamping", group="Test")
class TestMotor: BROpMode() {
    private lateinit var motor: Motor
    override fun initialize() {
        setOpModeType(OpModeType.Autonomous)
        this.motor = Motor(this, "fl")
    }
    override fun run() {
        this.motor.setTarget(1120.0 * 10)
        while(!(this.motor.isAtTarget())) {
            this.motor.setPower(1.0)
        }

    }
    override fun end() {
        this.motor.stopMotor()
    }


}
package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Servos.Servo

@Autonomous(name="Test Servo", group="Test")
class ServoTest: BROpMode() {
    private lateinit var servo: Servo
    override fun initialize() {
        servo = Servo(this, "servo")
        servo.position = 0.0
    }
    override fun run() {
        servo.position = 1.0
    }
}
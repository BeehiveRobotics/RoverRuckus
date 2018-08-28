package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Servos.Servo

@Autonomous(name="Test Servo", group="Test")
class ServoTest: BROpMode(OpModeType.Autonomous) {
    private lateinit var servo: Servo
    override fun initialize() {
        servo = Servo(this, "servo")
        servo.position = 0.0
        servo.speed = 1.0
    }
    override fun run() {
        servo.position = 1.0
        servo.speed = 0.75
        servo.position = 0.0
        servo.speed = 0.25
        servo.position = 0.5
        servo.speed = 0.0
        servo.position = 0.0
        sleep(300)
    }
}
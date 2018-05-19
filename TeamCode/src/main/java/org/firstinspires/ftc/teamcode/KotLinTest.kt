package org.firstinspires.ftc.teamcode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.BeehiveRobotics.Library.Util.BROpMode

@TeleOp(name="KotlinTankDriveTeleOp", group="Test")
class KotLinTest : BROpMode() {
    private lateinit var robot: TankRobot
    override fun run() {
        var left : Double = this.controller1.leftStickY()
        var right : Double = this.controller1.rightStickY()
        this.robot.drive.drive(left, right)
    }
    override fun end() {
        this.robot.drive.stopMotors()
    }
    override fun initialize() {
        setOpModeType(OpModeType.Autonomous)

        this.robot.init()
    }
}
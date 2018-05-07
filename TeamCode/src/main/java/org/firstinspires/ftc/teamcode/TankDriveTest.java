package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.BeehiveRobotics.Library.Motors.TankDrive;
import org.BeehiveRobotics.Library.Util.BROpMode;

@TeleOp(name="TestDrive", group="Test")
public class TankDriveTest extends BROpMode{
    double left;
    double right;
    TankDrive drive = new TankDrive(this);
    public void initialize() {
        setOpModeType(OpModeType.TeleOp);
        TankDrive drive = new TankDrive(this);
    }
    public void run() {
        left = controller1.leftStickY();
        right = controller1.rightStickY();
        drive.drive(left, right);
    }
    public void end() {
        drive.stopMotors();
    }
}

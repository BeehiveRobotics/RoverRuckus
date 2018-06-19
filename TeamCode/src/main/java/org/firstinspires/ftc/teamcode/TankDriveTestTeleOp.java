package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.BeehiveRobotics.Library.Motors.Java.TankDrive;
import org.BeehiveRobotics.Library.Util.Java.BROpMode;

@TeleOp(name = "TestDriveTeleOp", group = "Test")
public class TankDriveTestTeleOp extends BROpMode {
    private double left;
    private double right;
    private TankDrive drive = new TankDrive(this);

    public void initialize() {
        setOpModeType(OpModeType.TeleOp);
        drive.mapHardware();
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

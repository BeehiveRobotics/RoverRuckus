package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.BeehiveRobotics.Library.Motors.Java.TankDrive;
import org.BeehiveRobotics.Library.Util.BROpMode;

@Autonomous(name="TankDrive", group="Test")
public class TankDriveTestAuto extends BROpMode {
    double left;
    double right;
    private TankDrive drive = new TankDrive(this);
    public void initialize() {
        setOpModeType(OpModeType.Autonomous);
        drive.mapHardware();
    }
    public void run() {
        drive.forward(1, 12);
        drive.backward(1, 12);
        drive.spinRight(1, 12);
        drive.spinLeft(1, 12);
        drive.rightForward(1, 12);
        drive.rightBackward(1, 12);
        drive.leftForward(1, 12);
        drive.leftBackward(1, 12);
    }
    
    public void end() {
        drive.stopMotors();
    }
}

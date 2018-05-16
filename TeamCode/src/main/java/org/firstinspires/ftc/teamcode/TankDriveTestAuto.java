package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.BeehiveRobotics.Library.Motors.TankDrive;
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
        waitUntilNotBusy();
        drive.backward(1, 12);
        waitUntilNotBusy();
        drive.spinRight(1, 12);
        waitUntilNotBusy();
        drive.spinLeft(1, 12);
        waitUntilNotBusy();
        drive.rightForward(1, 12);
        waitUntilNotBusy();
        drive.rightBackward(1, 12);
        waitUntilNotBusy();
        drive.leftForward(1, 12);
        waitUntilNotBusy();
        drive.leftBackward(1, 12);
        waitUntilNotBusy();

    }
    public void end() {
        drive.stopMotors();
    }
}

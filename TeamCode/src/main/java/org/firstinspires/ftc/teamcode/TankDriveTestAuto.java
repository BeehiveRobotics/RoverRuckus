package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.BeehiveRobotics.Library.Motors.TankDrive;
import org.BeehiveRobotics.Library.Util.BROpMode;

@Autonomous(name="TankDrive", group="Test")
public class TankDriveTestAuto extends BROpMode {
    double left;
    double right;
    TankDrive drive = new TankDrive(this);
    public void initialize() {
        setOpModeType(OpModeType.TeleOp);
        drive.mapHardware();
    }
    public void run() {
        drive.forward(1, 12);
        showLine("Done with forward");
        sleep(1000);
        drive.backward(1, 12);
        showLine("done with backward");
        sleep(1000);
        drive.spinRight(1, 12);
        showLine("done with spinright");
        sleep(1000);
        drive.spinLeft(1, 12);
        showLine("done with spinleft");
        sleep(1000);
        drive.rightForward(1, 12);
        sleep(1000);
        drive.rightBackward(1, 12);
        sleep(1000);
        drive.leftForward(1, 12);
        sleep(1000);
        drive.leftBackward(1, 12);
    }
    public void end() {
        drive.stopMotors();
    }
}

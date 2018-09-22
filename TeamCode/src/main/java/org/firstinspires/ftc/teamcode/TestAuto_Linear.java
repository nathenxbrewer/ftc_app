package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="TestAuto", group="Linear Opmode")
//@Disabled
public class TestAuto_Linear extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private Servo leftGrabberServo = null;
    private Servo rightGrabberServo = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        leftGrabberServo = hardwareMap.servo.get("leftGrabber");
        rightGrabberServo = hardwareMap.servo.get("rightGrabber");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftGrabberServo.setPosition(0.0);
        rightGrabberServo.setPosition(0.0);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Drive Forward then turn.
            DriveForward(1);
            sleep(1000);
            DriveForward(0.0);
            TurnLeft(1.0);
            sleep(2000);
            //spin in circles
            TurnLeft(1);
            sleep(5000);
            TurnRight(1.0);
            sleep(5000);




            // Show the elapsed game time and wheel power.
            double leftPower = leftDrive.getPower();
            double rightPower = rightDrive.getPower();
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
    public void DriveForward(double power)
    {
        leftDrive.setPower(power);
        rightDrive.setPower(power);
    }
    public void DriveBackward(double power)
    {
        leftDrive.setPower(-power);
        rightDrive.setPower(-power);
    }
    public void TurnLeft(double power)
    {
        leftDrive.setPower(0.0);
        rightDrive.setPower(power);
    }
    public void TurnRight(double power)
    {
        leftDrive.setPower(power);
        rightDrive.setPower(0.0);
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="TestAuto", group="Linear Opmode")
//@Disabled
public class TestAuto_Linear extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor rightDrive = null;
    private DcMotor leftDrive = null;
    private DcMotor grabberSlider = null;
    private Servo leftGrabberServo = null;
    private Servo rightGrabberServo = null;

    @Override
    public void runOpMode() throws InterruptedException
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        grabberSlider = hardwareMap.get(DcMotor.class, "grabberSlider");
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



        DriveForward(1);
        Thread.sleep(5000);
        TurnRight(1);
        Thread.sleep(1800);
        DriveForward(1);
        Thread.sleep(5000);
        TurnRight(1);
        Thread.sleep(1800);
        DriveForward(1);
        Thread.sleep(5000);
        TurnLeft(1);
        StopMoving();
        TurnRight(1);
        Thread.sleep(5000);




        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

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

    public void DriveBackwards(double power)
    {
      leftDrive.setPower(-power);
      rightDrive.setPower(-power);
    }

    public void TurnRight(double power)
    {
        leftDrive.setPower(power);
        rightDrive.setPower(-power);
    }

    public void TurnLeft (double power)
    {
        leftDrive.setPower(-power);
        rightDrive.setPower(power);
    }

    public void StopMoving ()
    {
        DriveForward(0);
    }

    public void TurnAround (double power) throws InterruptedException
    {
       TurnRight(power);
       Thread.sleep(5000);
       StopMoving();
    }

    public void OpenGrabber (double power)
    {
        leftGrabberServo.setPosition(180);
        rightGrabberServo.setPosition(180);
    }

    public void CloseGrabber (double power)
    {
        leftGrabberServo.setPosition(0);
        rightGrabberServo.setPosition(0);
    }
}

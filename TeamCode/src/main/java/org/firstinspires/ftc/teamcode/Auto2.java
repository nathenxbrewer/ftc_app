package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto2", group="Linear Opmode")
//@Disabled
public class Auto2 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor rightDrive = null;
    private DcMotor leftDrive = null;
    private DcMotor grabberslide = null;
    private Servo rightgrabber = null;
    private Servo leftgrabber = null;

    @Override
    public void runOpMode() throws InterruptedException
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        grabberslide = hardwareMap.get(DcMotor.class, "grabberslide");
        rightgrabber = hardwareMap.servo.get("rightgrabber");
        leftgrabber = hardwareMap.servo.get("leftgrabber");




        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        leftgrabber.setPosition(0);
        rightgrabber.setPosition(0);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        DriveForward(1);
        Thread.sleep(5000);
        TurnRight(1);
        DriveForward(1);
        Thread.sleep(5000);
        TurnRight(1);
        DriveForward(1);
        Thread.sleep(5000);
        TurnRight(1);
        openGrabber();
        Thread.sleep(500);
        closeGrabber();
       Thread.sleep(500);
        openGrabber();
        Thread.sleep(500);
        closeGrabber();
        Thread.sleep(500);
        openGrabber();
        Thread.sleep(500);
        closeGrabber();
        Thread.sleep(500);
        openGrabber();
        Thread.sleep(500);
        closeGrabber();
        Thread.sleep(500);
        openGrabber();
        Thread.sleep(500);
        closeGrabber();
        Thread.sleep(500);
        TurnRight(1);
        Thread.sleep(8000);







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
        DriveForward(-power);
    }

    public void TurnRight(double power) throws InterruptedException
    {
       leftDrive.setPower(power);
       rightDrive.setPower(-power);
       Thread.sleep(1800);
    }

    public void TurnLeft (double power, long time) throws InterruptedException
    {
        leftDrive.setPower(-power);
        rightDrive.setPower(power);
        Thread.sleep(1800);
    }

    public void openGrabber ()
    {
        leftgrabber.setPosition(180);
        rightgrabber.setPosition(180);
    }
    public void closeGrabber ()
    {
        leftgrabber.setPosition(0);
        rightgrabber.setPosition(0);
    }
}
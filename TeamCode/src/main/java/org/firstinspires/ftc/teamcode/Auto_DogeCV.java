package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;

@Autonomous(name="Auto_DogeCV", group="Linear Opmode")
//@Disabled
public class Auto_DogeCV extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor rightDrive = null;
    private DcMotor leftDrive = null;
    private DcMotor grabberslide = null;
    private Servo rightgrabber = null;
    private Servo leftgrabber = null;
    private Servo arm = null;

    private GoldAlignDetector detector;

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
        arm = hardwareMap.servo.get("arm");

        telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");

        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        // Optional Tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;


        detector.enable();




        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        leftgrabber.setPosition(0);
        rightgrabber.setPosition(0);




        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Show the elapsed game time and wheel power.
            double leftPower = leftDrive.getPower();
            double rightPower = rightDrive.getPower();
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();

            telemetry.addData("IsAligned" , detector.getAligned()); // Is the bot aligned with the gold mineral
            telemetry.addData("X Pos" , detector.getXPosition()); // Gold X pos.\\

            if (!detector.getAligned()){
                DriveForward(1);
                Thread.sleep(100);
                if (!detector.getAligned())
                {
                    DriveForward(1);
                    Thread.sleep(100);
                }
                }
            else if (detector.getAligned()){
                DriveForward(0);
                DriveBackwards(1);
                Thread.sleep(200);
                arm.setPosition(180);
                Thread.sleep(200);
                break;
            }
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
       Thread.sleep(500);
    }

    public void TurnLeft (double power) throws InterruptedException
    {
        leftDrive.setPower(-power);
        rightDrive.setPower(power);
        Thread.sleep(1000);
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
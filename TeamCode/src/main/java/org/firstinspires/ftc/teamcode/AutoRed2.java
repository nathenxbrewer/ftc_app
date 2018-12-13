package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoRed2", group="Linear Opmode")
//@Disabled
public class AutoRed2 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor RF = null;
    private DcMotor RB = null;
    private DcMotor LF = null;
    private DcMotor LB = null;
    private DcMotor Piston = null;
    private DcMotor Intake = null;
    @Override
    public void runOpMode() throws InterruptedException
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        RF  = hardwareMap.get(DcMotor.class, "RF");
        RB = hardwareMap.get(DcMotor.class, "RB");
        LF  = hardwareMap.get(DcMotor.class, "LF");
        LB  = hardwareMap.get(DcMotor.class, "LB");
        LB.setDirection(DcMotor.Direction.REVERSE);
        LF.setDirection(DcMotor.Direction.REVERSE);

        double MotorStrenght = 0.75;



        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();


        DriveForward(MotorStrenght);
        Thread.sleep(4000);
        OutTake(1);
        Thread.sleep(1000);
        StrafeRight(1.0);
        Thread.sleep(1000);
        TurnRight(MotorStrenght);
        Thread.sleep(2000);
        DriveForward(MotorStrenght);
        Thread.sleep(5000);
        StopMoving();
        ExtendArm(MotorStrenght);
        Thread.sleep(3000);






        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
    public void DriveForward(double power)
    {
        LF.setPower(power);
        RF.setPower(power);
        LB.setPower(power);
        RB.setPower(power);


    }

    public void DriveBackwards(double power)
    {
      LF.setPower(-power);
      RF.setPower(-power);
      LB.setPower(-power);
      RB.setPower(-power);
    }

    public void TurnRight(double power) throws InterruptedException {
        LF.setPower(power);
        RF.setPower(-power);
        LB.setPower(power);
        RB.setPower(-power);
        Thread.sleep(2000);
    }
    public void StrafeRight(double power) throws InterruptedException {
        LF.setPower(power);
        RF.setPower(-power);
        LB.setPower(-power);
        RB.setPower(power);
    }

    public void StrafeLeft(double power) throws InterruptedException {
        LF.setPower(-power);
        RF.setPower(power);
        LB.setPower(power);
        RB.setPower(-power);
    }

    public void TurnLeft (double power) throws InterruptedException {
        LF.setPower(-power);
        RF.setPower(power);
        LB.setPower(-power);
        RB.setPower(power);
        Thread.sleep(2000);
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
    public void ExtendArm (double power) throws  InterruptedException
    {
     Piston.setPower(1.0);
     Thread.sleep(3000);
     StopMoving();
    }

    public  void OutTake (double power) throws  InterruptedException
    {
        Intake.setPower(-1);
    }
   /* public void OpenGrabber (double power)
    {
        leftGrabberServo.setPosition(180);
        rightGrabberServo.setPosition(180);
    }

    public void CloseGrabber (double power)
    {
        leftGrabberServo.setPosition(0);
        rightGrabberServo.setPosition(0);
    }
    */
}

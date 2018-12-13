package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="", group="Linear Opmode")
//@Disabled
public class AutoRed1 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor RF = null;
    private DcMotor RB = null;
    private DcMotor LF = null;
    private DcMotor LB = null;
    private DcMotor Piston = null;
    private DcMotor Intake = null;
    private Servo Hook = null;
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
        Hook = hardwareMap.servo.get("Hook");
        Piston = hardwareMap.get(DcMotor.class, "Piston");
        Intake = hardwareMap.get(DcMotor.class, "Intake");


        double MotorStrenght = 0.5;
        double IntakePower;
        LB.setDirection(DcMotor.Direction.REVERSE);
        LF.setDirection(DcMotor.Direction.REVERSE);



        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        Hook.setPosition(90);
        Thread.sleep(3000);
        Hook.setPosition(0);
        Thread.sleep(1000);
        Piston.setPower(-1.0);
        Thread.sleep(50);
        DriveForward(MotorStrenght);
        Thread.sleep(900);
        DriveBackwards(MotorStrenght);
        Thread.sleep(450);
        StrafeLeft(0.50);
        Thread.sleep(1800);
        //Had to remove TurnAround and put TurnLeft.
        TurnLeft(0.75);
        Thread.sleep(700);
        DriveForward(MotorStrenght);
        Thread.sleep(1000);
        //OutTake(1);
        //Thread.sleep(1000);
        TurnLeft(0.75);
        Thread.sleep(800);
        DriveForward(MotorStrenght);
        Thread.sleep(1000);
        StopMoving();
        ExtendArm(1);
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
        LF.setPower(-power);
        RF.setPower(-power);
        LB.setPower(-power);
        RB.setPower(-power);
    }

    public void DriveBackwards(double power)  throws InterruptedException
    {
      LF.setPower(power);
      RF.setPower(power);
      LB.setPower(power);
      RB.setPower(power);
    }

    public void TurnRight(double power) throws InterruptedException {
        LF.setPower(-power);
        RF.setPower(power);
        LB.setPower(-power);
        RB.setPower(power);
    }
    public void StrafeRight(double power) throws InterruptedException {
        LF.setPower(-power);
        RF.setPower(-power);
        LB.setPower(power);
        RB.setPower(power);
    }

    public void StrafeLeft(double power) throws InterruptedException {
        LF.setPower(power);
        RF.setPower(power);
        LB.setPower(-power);
        RB.setPower(-power);
    }

    public void TurnLeft (double power) throws InterruptedException {
        LF.setPower(power);
        RF.setPower(-power);
        LB.setPower(power);
        RB.setPower(-power);
    }

    public void StopMoving () throws InterruptedException
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
     StopMoving();
    }

    public void OutTake (double power) throws InterruptedException
    {
        Intake.setPower(-power);
    }
}

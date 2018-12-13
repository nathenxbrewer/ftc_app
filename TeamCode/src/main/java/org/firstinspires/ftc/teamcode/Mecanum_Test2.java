/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Mecanum_Test2", group="Linear Opmode")
public class Mecanum_Test2 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor RF = null;
    private DcMotor RB = null;
    private DcMotor LF = null;
    private DcMotor LB = null;
    private DcMotor Piston = null;
    private DcMotor Intake = null;
    private DcMotor Pivot = null;
    //private DcMotor Scoop = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        RF  = hardwareMap.get(DcMotor.class, "RF");
        RB = hardwareMap.get(DcMotor.class, "RB");
        LF  = hardwareMap.get(DcMotor.class, "LF");
        LB  = hardwareMap.get(DcMotor.class, "LB");
        Piston = hardwareMap.get(DcMotor.class, "Piston");
        Intake = hardwareMap.get(DcMotor.class, "Intake");
        Pivot = hardwareMap.get(DcMotor.class, "Pivot");
        // Scoop = hardwareMap.get(DcMotor.class, "Scoop");



        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        LB.setDirection(DcMotor.Direction.REVERSE);
        LF.setDirection(DcMotor.Direction.REVERSE);

        //rightDrive.setDirection(DcMotor.Direction.REVERSE);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            boolean TURBO_ENABLED = gamepad1.right_bumper;
            double MotorStrenght = 0;

            if (TURBO_ENABLED == true)
            {
                MotorStrenght = 1.0;
            }
            else if (TURBO_ENABLED == false)
            {
                MotorStrenght = 0.75;
            }

            // Setup a variable for each drive wheel to save power level for telemetry
            double LFPower;
            double RFPower;
            double LBPower;
            double RBPower;

            double IntakePower;
            double PistonPower = 0;
            double IntakePowerReverse = 0;
            double PivotPower = 0;


            if (gamepad2.dpad_down )
            {
                PistonPower = 1.0;
            }
            if (gamepad2.dpad_up)
            {
                PistonPower = -1.0;
            }



            IntakePower = gamepad2.left_trigger;
            IntakePowerReverse = (-gamepad2.right_trigger);
            PivotPower = (gamepad2.left_stick_y);


           // IntakePower = gamepad2.left_trigger;

            Intake.setPower(IntakePower);
            Piston.setPower(PistonPower);
            Intake.setPower(IntakePowerReverse);
            Pivot.setPower(PivotPower * 0.75);



            LFPower = (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
            LBPower = (gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
            RFPower = (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
            RBPower = (gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);


            LF.setPower(MotorStrenght * LFPower);
            RF.setPower(MotorStrenght * RFPower);
            LB.setPower(MotorStrenght * LBPower);
            RB.setPower(MotorStrenght * RBPower);


            /*if (gamepad1.x) {
                TURBO_ENABLED = !TURBO_ENABLED;

                //decided to shortcut code
               /* if (TURBO_ENABLED == false) {
                    TURBO_ENABLED = !TURBO_ENABLED;
                } else if (TURBO_ENABLED == true) {
                    TURBO_ENABLED = !TURBO_ENABLED;

                }*/


            



/*
            if (gamepad2.right_bumper) {
                Scoop.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Scoop.setTargetPosition(610);
                Scoop.setPower(1);
            }

            if (gamepad2.left_bumper) {
                Scoop.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Scoop.setTargetPosition(0);
                Scoop.setPower(1);
            }*/







           /* while (gamepad1.left_stick_y <= 1.0 &gamepad1.left_stick_y >= -1.0) {
                LF.setPower(gamepad1.left_stick_y);
                LB.setPower(gamepad1.left_stick_y);
                RF.setPower(gamepad1.left_stick_y);
                RB.setPower(gamepad1.left_stick_y);
            }

            while (gamepad1.left_stick_x <= 1.0 &gamepad1.left_stick_x >= -1.0){
                LF.setPower(gamepad1.left_stick_x);
                LB.setPower(-gamepad1.left_stick_x);
                RF.setPower(-gamepad1.left_stick_x);
                RB.setPower(gamepad1.left_stick_x);
            } */





            // Send calculated power to wheels
           // LF.setPower(LFPower);
           // LB.setPower(LBPower);
          //  RF.setPower(RFPower);
          //  RB.setPower(RBPower);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("TURBO", "Enabled: " + TURBO_ENABLED);
            telemetry.addData("Motor Strength", "Value: " + MotorStrenght);
            telemetry.addData("Motor Strength_LF", LF.getPower());
            telemetry.addData("Motor Strength_LB", LB.getPower());
            telemetry.addData("Motor Strength_RF", RF.getPower());
            telemetry.addData("Motor Strength_RB", RB.getPower());

           // telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}

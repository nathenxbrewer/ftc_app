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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MechanumDrive", group="Linear Opmode")
public class MechanumDrive extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorLF = null;
    private DcMotor motorLB = null;
    private DcMotor motorRF = null;
    private DcMotor motorRB = null;



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        motorLF  = hardwareMap.get(DcMotor.class, "motorLF");
        motorLB  = hardwareMap.get(DcMotor.class, "motorLB");
        motorRF  = hardwareMap.get(DcMotor.class, "motorRF");
        motorRB  = hardwareMap.get(DcMotor.class, "motorRB");





        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        motorRF.setDirection(DcMotor.Direction.REVERSE);
        motorRB.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry

            //Motor speed variables
            //Direction: left_stick_x ranges from -1 to 1, where -1 is full left and 1 is full right.
//declares variables to store the value of the two sticks combined.
            double LFspeed = gamepad1.left_stick_y - gamepad1.left_stick_x;
            double LBspeed = gamepad1.left_stick_y + gamepad1.left_stick_x;
            double RFspeed = gamepad1.right_stick_y + gamepad1.left_stick_x;
            double RBspeed = gamepad1.right_stick_y - gamepad1.left_stick_x;

//Clips values to keep them between -1 and 1.
            LFspeed = Range.clip(LFspeed, -1, 1);
            LBspeed = Range.clip(LBspeed, -1, 1);
            RFspeed = Range.clip(RFspeed, -1, 1);
            RBspeed = Range.clip(RBspeed, -1, 1);

//sets motors to the above values.
            motorLF.setPower(LFspeed);
            motorLB.setPower(LBspeed);
            motorRF.setPower(RFspeed);
            motorRB.setPower(RBspeed);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left_front (%.2f), left_back (%.2f), right_front (%.2f), right_back (%.2f)", motorLF, motorLB, motorRF, motorRB);
            telemetry.update();
        }
    }
}

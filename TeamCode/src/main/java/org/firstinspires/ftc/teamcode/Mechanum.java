//Direction: left_stick_x ranges from -1 to 1, where -1 is full left and 1 is full right.
//declares variables to store the value of the two sticks combined.
float LFspeed = gamepad1.left_stick_y - gamepad1.left_stick_x;
float LBspeed = gamepad1.left_stick_y + gamepad1.left_stick_x;
float RFspeed = gamepad1.right_stick_y + gamepad1.left_stick_x;
float RBspeed = gamepad1.right_stick_y - gamepad1.left_stick_x;

//Clips values to keep them between -1 and 1.
LFSpeed = Range.clip(LFspeed, -1, 1);
LBSpeed = Range.clip(LBSpeed, -1, 1);
RFSpeed = Range.clip(RFSpeed -1, 1);
RBSpeed = Range.clip(RBSpeed, -1, 1);

//sets motors to the above values. 
motorLF.setPower(LFspeed);
motorLB.setPower(LBspeed);
motorRF.setPower(RFspeed);
motorRB.setPower(RBspeed);
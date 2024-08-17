package org.example;

public class Main{
    public static void main(String[] args) {

        //////////////////////////////////////////////////////////////////////////////////////////////////
        //
        // Robotics members, IGNORE THIS CODE!!
        //
        GamePad gamepad2 = new GamePad();

        // Pass the gamePad into the thread as a reference.
        Thread controller = new Thread(new ReadAllEvents(gamepad2));
        controller.start();



        //////////////////////////////////////////////////////////////////////////////////////////////////
        //
        // Robotics members, PUT YOUR LOGIC HERE FOR THE CONTROLLER PART OF THE ROBOT.
        //

        boolean opModeIsActive = true;
        boolean oldArmInt = false;
        boolean oldIntakeArmInt = false;
        boolean oldGripperInt = false;
        boolean joggingup = false;
        boolean joggingdown = false;

        boolean bottomLimit = false;

        int intakeLeft = 0;
        int intakeRight = 0;
        float armSwingPosition = 1;
        float gripper = 1;

        while (opModeIsActive) {

            // What button is this on the controllers??? -Brandon
            if (gamepad2.back) {
                //robot.svrHang.setPosition(.9);
                System.out.println("svrHang: .9");
            }

            //intake
            //take in pixels
            if (gamepad2.dpad_right) {
//                robot.mtrI.setDirection(DcMotor.Direction.REVERSE);
//                robot.mtrI.setPower(0.6);
                System.out.println("mtrI Power: 0.6, Direction reverse.");
            }
            //spit out pixels
            if (gamepad2.dpad_left) {
//                robot.mtrI.setDirection(DcMotor.Direction.FORWARD);
//                robot.mtrI.setPower(0.6);
                System.out.println("mtrI Power: 0.6, Direction Forward.");
            }
            //kill the power on the intake
            // b = button 2
            if (gamepad2.b) {
                //robot.mtrI.setPower(0);
                System.out.println("mtrI Power: 0");
            }

            // x = button 0
            if(gamepad2.x && !oldIntakeArmInt) {
                if  (intakeLeft == 0 && intakeRight== 1) {
//                    robot.intakeLeft.setPosition(.07);
//                    robot.intakeRight.setPosition(.92);
                    System.out.println("intakeLeft, position: .07");
                    System.out.println("intakeRight, position: .92");
                    oldIntakeArmInt = true;
                } else {
//                    robot.intakeRight.setPosition(1);
//                    robot.intakeLeft.setPosition(0);
                    System.out.println("intakeRight: 1");
                    System.out.println("intakeLeft: 0");
                    oldIntakeArmInt = true;
                }
            } else if(!gamepad2.x) {
                oldIntakeArmInt = false;
            }

            if (gamepad2.dpad_down /*&& elapsedTime.time() > 60)*/) {
//                robot.mtrHang.setDirection(DcMotorSimple.Direction.FORWARD);
//                robot.mtrHang.setPower(1.0);

                System.out.println("mtrHang: 1, direction reverse");
            }

            if (gamepad2.dpad_up) {
//                robot.mtrHang.setDirection(DcMotor.Direction.REVERSE);
//                robot.mtrHang.setPower(1.0);

                System.out.println("mtrHang: 1, direction reverse");
            }

            if (!gamepad2.dpad_down && !gamepad2.dpad_up) {
                //robot.mtrHang.setPower(0);
                //System.out.println("mtrHang: 0");
            }

            // right_trigger = button 7
            // Replaced >= .9 with true
            if (gamepad2.right_trigger == true && !joggingdown) {
                //robot.mtrLift.setDirection(DcMotorSimple.Direction.FORWARD);
                //robot.mtrLift2.setDirection(DcMotorSimple.Direction.REVERSE);
                //robot.mtrLift.setVelocity(1000);

                System.out.println("mtrLift Velocity: 1000, forward");
                //robot.mtrLift2.setVelocity(1000);
                joggingup = true;

            }
            //if we were already jogging up, then stop the lift.
            //we need this extra variable check b/c otherwise
            //gamepad2 right trigger will be false while we're moving
            //the lift downward, which will constantly stop our lift
            //and not let us move down.
            // changed from 0 to false.
            if (gamepad2.right_trigger == false && joggingup) {
                //robot.mtrLift.setVelocity(0);

                System.out.println("mtrLift velocity: 0");
                //robot.mtrLift2.setVelocity(0);
                joggingup = false;
                joggingdown = false;

            }

            // Changed from 0.9 to true
            if (gamepad2.left_trigger == true && !bottomLimit && !joggingup) {
                //robot.mtrLift.setDirection(DcMotorSimple.Direction.REVERSE);
                //robot.mtrLift2.setDirection(DcMotorSimple.Direction.FORWARD);
                //robot.mtrLift.setVelocity(700);
                System.out.println("mtrLift velocity: 700, Direction reverse");
                //robot.mtrLift2.setVelocity(1000);
                joggingdown = true;

            }
            //if we're moving down but our limit switch pressed, STOP!!!!
            if (joggingdown && bottomLimit) {
                //robot.mtrLift.setVelocity(0);
                System.out.println("mtrLift velocity: 0");


                //robot.mtrLift2.setVelocity(0);
                joggingdown = false;
                joggingup = false;

                //if we're holding down the left trigger & our limit switch isn't pressed
                //move our lift down, and set our memory/control bit to true.
            }

            //if we stop holding down our trigger and
            //we were moving down to begin with,
            //stop the lift.
            //this extra variable check is in place b/c of the
            //same reasoning as the other trigger check
            //this also doesn't need the limit switch to stop

            // Changed from 0.2 to false
            if (gamepad2.left_trigger == false && joggingdown) {
                //robot.mtrLift.setVelocity(0);
                System.out.println("mtrLift velocity: 0");
                //robot.mtrLift2.setVelocity(0);
                joggingdown = false;
                joggingup = false;
            }

            //shoot the drone launcher
            // left_bumper = button 4
            if (gamepad2.left_bumper) {
                //robot.droneLauncher.setPosition(.689);
                System.out.println("drone launcher: .689");
            }

            // a = button 1
            if(gamepad2.a && !oldArmInt) {
                if  (armSwingPosition <= 0.02) {
                    //robot.armSwing.setPosition(1);
                    System.out.println("armSwing: 1");
                    oldArmInt = true;
                } else {
                    //robot.armSwing.setPosition(0.00);
                    System.out.println("armSwing: 0");
                    oldArmInt = true;
                }
            } else if(!gamepad2.a) {
                oldArmInt = false;
            }

            // right_bumper = buton 5
            if(gamepad2.right_bumper && !oldGripperInt) {
                if  (gripper <=.32) {
                    //robot.gripper.setPosition(.57);
                    System.out.println("gripper: .57");
                    oldGripperInt = true;
                } else {
                    //robot.gripper.setPosition(.32);
                    System.out.println("gripper: .32");
                    oldGripperInt = true;
                }
            } else if(!gamepad2.right_bumper) {
                oldGripperInt = false;
            }
        }
    }
}


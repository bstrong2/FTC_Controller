package org.example;

public class GamePad
{
    // buttons
    public boolean a = false;
    public boolean b = false;
    public boolean x = false;
    public boolean y = false;

    // Dpad
    public boolean dpad_down = false;
    public boolean dpad_up = false;
    public boolean dpad_left = false;
    public boolean dpad_right = false;

    // Triggers
    public boolean left_trigger = false;
    public boolean right_trigger = false;
    public boolean left_bumper = false;
    public boolean right_bumper = false;

    // joysticks
    public float x_axis;
    public float y_axis;
    public float z_axis;


    public boolean back = false;
    public boolean start = false;
}

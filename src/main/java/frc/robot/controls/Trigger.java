package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;

public class Trigger extends Button
{

    public Trigger(Joystick stick, int buttonCode) {
        super(stick, buttonCode);
    }

    @Override
    public void setStates()
    {
        isPressed = stick.getRawAxis(code) == 1 ? isHold ? false : true : false;
        //isPressed = stick.getRawButtonPressed(code);
        //isReleased = stick.getRawButtonReleased(code);
        
        if (isPressed)
        {
            isHold = true;
        }
        else if (isReleased)
        {
            isHold = false;
        }
    }

}
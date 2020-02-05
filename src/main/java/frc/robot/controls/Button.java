package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;

/*
    Why do I even need to create this class?
*/
public class Button
{
    private boolean isPressed;
    private boolean isReleased;

    //Since Joystick.getRawButtonPressed(int code) only gets the button press on a single tick, 
    //isHold checks whether the button is still being held or not.
    //As far as I know, the wpilib does not provide a function to check whether a button is still being held.
    //If they do, delete this class and end my life.
    private boolean isHold;

    private int code;
    private Joystick stick;

    public Button(Joystick stick, int buttonCode)
    {
        isPressed = false;
        isReleased = false;
        isHold = false;

        this.stick = stick;
        code = buttonCode;
    }

    public int getCode()
    {
        return code;
    }

    public void setStates()
    {
        isPressed = stick.getRawButtonPressed(code);
        isReleased = stick.getRawButtonReleased(code);
        
        if (isPressed)
        {
            isHold = true;
        }
        else if (isReleased)
        {
            isHold = false;
        }
    }

    public boolean isPressed()
    {
        return this.isPressed;
    }

    public boolean isReleased()
    {
        return this.isReleased;

    }

    public boolean isHold()
    {
        return this.isHold;
    }
    

}
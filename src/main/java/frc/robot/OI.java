package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

//OI class: A control system class
//OI class checks control inputs such as joysticks and buttons and update the variables accordingly.
//You will find this class being used essentially everywhere (unless it is not).

public class OI {

    //Joystick takes in the port number? <-- Not very sure, ask the builders or even the instructors to know more.
    public static final Joystick colorStick = new Joystick(2);
    public static final Joystick leftStick = new Joystick(0);
    public static final Joystick rightStick = new Joystick(1);

    //Setting the button code on the control pad. 
    public static final int BLUE_CODE = 1;
    public static final int RED_CODE = 2;
    public static final int YELLOW_CODE = 3;
    public static final int GREEN_CODE = 4;

    //Since index starts at 0, using the codes above as index needs to subtract 1.
    private static Button[] buttons = new Button[]
    {
        new Button(), //Blue
        new Button(), //Red
        new Button(), //Yellow
        new Button() //Green
    };

    //Getting the inputs of the joystick and update the variables
    public static void update()
    {
        for (int i = 0; i < buttons.length; i++)
        {
            buttons[i].setPressState(colorStick.getRawButtonPressed(i + 1));
            buttons[i].setReleaseState(colorStick.getRawButtonReleased(i + 1));
        }

        //runDebug();
    }

    //Getting button presses on colorStick Joystick.
    //Use button codes (ex: OI.BLUE_CODE) above for buttonCode!
    public static boolean isButtonPressed(int buttonCode)
    {
        return buttons[buttonCode - 1].isPressed();
    }

    //Getting button releases on colorStick Joystick.
    //Use button codes (ex: OI.BLUE_CODE) above for buttonCode!
    public static boolean isButtonReleased(int buttonCode)
    {
        return buttons[buttonCode - 1].isReleased();
    }

    //Getting button holds on colorStick Joystick.
    //Use button codes (ex: OI.BLUE_CODE) above for buttonCode!
    public static boolean isButtonHold(int buttonCode)
    {
        return buttons[buttonCode - 1].isHold();
    }

    //Runs debug to check what button is pressed/released/hold.
    private static void runDebug()
    {
        //System.out.print("----------- New Loop -------------");
        for (int i = 0; i < buttons.length; i++)
        {
            if (buttons[i].isPressed()) System.out.println("Button " + i + " is just pressed!");
            if (buttons[i].isReleased()) System.out.println("Button " + i + " is just released!");
            if (buttons[i].isHold()) System.out.println("Button " + i + " is still being held!");
        }
    }
}

/*
    Why do I even need to create this class?
*/
class Button
{
    private boolean isPressed;
    private boolean isReleased;

    //Since Joystick.getRawButtonPressed(int code) only gets the button press on a single tick, 
    //isHold checks whether the button is still being hold or not.
    //As far as I know, the wpilib does not provide a function to check whether a button is still being held.
    //If they do, delete this class and end my life.
    private boolean isHold;

    public Button()
    {
        isPressed = false;
        isReleased = false;
        isHold = false;
    }

    public void setPressState(boolean isPressed)
    {
        this.isPressed = isPressed;
        if (isPressed) this.isHold = true;
    }

    public void setReleaseState(boolean isReleased)
    {
        this.isReleased = isReleased;
        if (isReleased) this.isHold = false;
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
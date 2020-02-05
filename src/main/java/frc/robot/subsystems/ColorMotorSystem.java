package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.controls.OI;

/*
    A color motor system to turn that color motor.

    ****Game Description Goes Here****

    It is used in Robot.java and probably nowhere else.
*/
public class ColorMotorSystem
{
    private final SpeedController COLOR_MOTOR = new PWMVictorSPX(4);
    private final ColorDetectionSystem COLOR_DETECTION = new ColorDetectionSystem();
    private final int COLOR_AMOUNT = 8;

    private int colorsPassed;
    private String colorSelection;
    private String currentColor;
    private String prevColor;

    /*
        There are 3 statuses for motorStatus
        IDLE: motor not doing anything, trying to get a color button input
        PREP_ON_STANBY: reseting variables, motor on standby
        SPIN_3_TIMES: motor starts to spin the color wheel 3 times
        SPIN_TO_SELECTED_COLOR: motor starts to spin to selected color from color button input.
    */
    private String motorStatus;

    public ColorMotorSystem()
    {
        colorsPassed = 0;
        motorStatus = "IDLE";
        colorSelection = "";
        currentColor = "";
        prevColor = "";
    }

    //Getting 'color' button presses every updates only when motor is idle.
    //If a 'color' button is pressed, set colorSelection variable to the color selected.
    //Use in update() function down below. 
    private boolean scanColorButton()
    {
        if (motorStatus.equals("IDLE"))
        {
            if (OI.BLUE_BUTTON.isPressed()) colorSelection = "Blue";
            else if (OI.RED_BUTTON.isPressed()) colorSelection = "Red";
            else if (OI.YELLOW_BUTTON.isPressed()) colorSelection = "Yellow";
            else if (OI.GREEN_BUTTON.isPressed()) colorSelection = "Green";
            if (!colorSelection.equals("")) 
            {   
            //    System.out.println("COLOR HAVE BEEN SELECTED: " + colorSelection);
            //    System.out.println("BEGIN PREPING FOR MOTOR SPIN...");
                return true;
            }
        }
        return false;
    }

    //Set motor speed to 1.0 (max) until it has spin the color wheel (control panel) 3.5 times.
    //Use in update() function down below.
    private void spinThreeTimes()
    {
        //System.out.println(currentColor);
        //System.out.println("Number of Color Passed - " + colorsPassed); //Debugging
        currentColor = COLOR_DETECTION.runDetection();
        COLOR_MOTOR.set(1.0);

        //If the sensor sees anything other than RGBY ignore it
        if (!currentColor.equals("Unknown"))
        {
            //If the previously detected color is not the same as the current
            if (!prevColor.equals(currentColor))
            {
                colorsPassed++;
                prevColor = currentColor;
            }
            //If it has spun 3.5 times (passing 28 colors), stop it
            if (colorsPassed >= COLOR_AMOUNT * 3.5)
            {
                COLOR_MOTOR.stopMotor();
                System.out.println("SPIN_3_TIMES ACTION ENDED!");
                motorStatus = "SPIN_TO_SELECTED_COLOR";
            }
        }
    }

    /*
        Set motor speed to 1.0 (max) until it has spin the color wheel (control panel) to the 
        selected color (colorSelection).

        Used in update() function down below.
    */
    private void spinToColorSeleted()
    {
        COLOR_MOTOR.set(1.0);
        if (COLOR_DETECTION.isColorMatch(colorSelection))
        {
            COLOR_MOTOR.stopMotor();
            colorSelection = "";
            motorStatus = "IDLE";
        }
    }

    // update function, updates color motor status every cycle/tick/loop of teleopPeriodic() function in Robot.java
    public void update()
    {
        //System.out.println("------------- New Color Motor System Loop ---------------");
        if(scanColorButton()) motorStatus = "PREP_ON_STANBY";
        if(motorStatus.equals("PREP_ON_STANBY"))
        {
            colorsPassed = 0;  
            currentColor = COLOR_DETECTION.runDetection();
            prevColor = COLOR_DETECTION.runDetection();
            motorStatus = "SPIN_3_TIMES";
          //  System.out.println("SPIN_3_TIMES ACTION INITIATED!");
        }
        if (motorStatus.equals("SPIN_3_TIMES")) spinThreeTimes();
        if (motorStatus.equals("SPIN_TO_SELECTED_COLOR")) spinToColorSeleted();
    }
}
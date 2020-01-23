package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Joystick;

public class ColorMotorSystem
{
    private SpeedController colorMotor;
    private Joystick m_colorStick;
  
    private ColorDetection colorDetection;
    private final int COLOR_AMOUNT = 8;
    private final int blueColorCodeButton = 1;
    private final int redColorCodeButton = 2;
    private final int yellowColorCodeButton = 3;
    private final int greenColorCodeButton = 4;
    private final int spinColorCodeButton = 5;

    private int colorsPassed;
    private String colorSelection;
    private String currentColor;
    private String prevColor;


    private String currentStatus;

    public ColorMotorSystem()
    {
        colorMotor = new PWMVictorSPX(4);
        colorDetection = new ColorDetection();
        m_colorStick = new Joystick(2); 

        colorsPassed = 0;
        currentStatus = "IDLE";
        colorSelection = "";
        currentColor = "";
        prevColor = "";
    }

    private boolean checkButtonPress()
    {
        if (m_colorStick.getRawButtonPressed(blueColorCodeButton))
        {
            colorSelection = "Blue";
            return true;
        }
       
        if (m_colorStick.getRawButtonPressed(redColorCodeButton))
        {
            colorSelection = "Red";
            return true;
        } 
        
        if (m_colorStick.getRawButtonPressed(yellowColorCodeButton))
        {
            colorSelection = "Yellow";
            return true; 
        } 
        
        if (m_colorStick.getRawButtonPressed(greenColorCodeButton))
        {
            colorSelection = "Green";
            return true;
        } 
        
        if (m_colorStick.getRawButtonPressed(spinColorCodeButton))
        {
            colorsPassed = 0;
            colorSelection = "";
            return true;
        } 
        return false;
    }

    private void startSpin()
    {
        colorMotor.set(1.0);
        //This means that it should rotate between 3-5 times
        if (colorSelection.equals(""))
        {
            //If the sensor sees anything other than RGBY ignore it
            if (!currentColor.equals("Unknown"))
            {
                //If the previously detected color is not the same as the current
                if (!prevColor.equals(currentColor))
                {
                    colorsPassed++;
                    System.out.println(currentColor + ", " + colorsPassed);
                }
                //If it has spun 3.5 times, stop it
                if (colorsPassed >= COLOR_AMOUNT * 3.5)
                {
                    colorMotor.stopMotor();
                }
            }
        }
        else if (colorDetection.isColorMatch(colorSelection))
        {
            colorMotor.stopMotor();
            colorSelection = "";
        }

        prevColor = currentColor;
    }

    private void runStatus()
    {
        if(checkButtonPress())
        {
            currentStatus = "RUN_MOTOR_3_TIMES";
        }

        if (currentStatus.equals("RUN_MOTOR_3_TIMES"))
        {
            
        }
    }

    public void update()
    {
        currentColor = colorDetection.runDetection();

        
        
        
    }

    

}
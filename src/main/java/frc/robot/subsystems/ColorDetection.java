package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class ColorDetection
{
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    private String colorString;

    public ColorDetection()
    {
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);  
    }

    //Checking if the detected color in the colorString matches the intended color.
    public boolean isColorMatch(String intendedColor)
    {
        return intendedColor == colorString;
    }

    //Running color detection. Should be in the teleopPeriodic method for automatic update.
    //Setting the detected color to the colorString.
    public String runDetection()
    { 
        Color detectedColor = m_colorSensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
        
        System.out.println(match.color.red + ", " + match.color.blue + ", " + match.color.green + ", ");

        if (match.color == kBlueTarget) 
        {
            colorString = "Blue";
        } 
        else if (match.color == kRedTarget) 
        {
            colorString = "Red";
        } 
        else if (match.color == kGreenTarget) 
        {
            colorString = "Green";
        } 
        else if (match.color == kYellowTarget) 
        {
            colorString = "Yellow";
        } 
        else 
        {
            colorString = "Unknown";
        }

        return colorString;
    }
}
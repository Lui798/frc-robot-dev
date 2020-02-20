package frc.robot.subsystems;
import frc.robot.util.Debug;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

/* 
    A color detection system to detect color using color sensor.
    It's not really a system. It is more like a state class to optimize code and make it look cleaner. (DRY)
    It is used in ColorMotorSystem.java and probably nowhere else.
*/
public class ColorDetectionSystem
{
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color BLUE_TARGET = ColorMatch.makeColor(0.143, 0.452, 0.429);
    private final Color GREEN_TARGET = ColorMatch.makeColor(0.267, 0.499, 0.240);
    private final Color RED_TARGET = ColorMatch.makeColor(0.525, 0.355, 0.120);
    private final Color YELLOW_TARGET = ColorMatch.makeColor(0.361, 0.524, 0.113);
    private Colors color;

    public ColorDetectionSystem()
    {
        m_colorMatcher.addColorMatch(BLUE_TARGET);
        m_colorMatcher.addColorMatch(GREEN_TARGET);
        m_colorMatcher.addColorMatch(RED_TARGET);
        m_colorMatcher.addColorMatch(YELLOW_TARGET);
    }

    //Checking if the detected color in the colorString matches the intended color.
    public boolean isColorMatch(Colors intendedColor)
    {
        runDetection();
        return intendedColor.equals(color);
    }

    //Running color detection. Should be in the teleopPeriodic method for automatic update.
    //Setting the detected color to the colorString.
    public Colors runDetection()
    { 
        Color detectedColor = m_colorSensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == BLUE_TARGET) 
        {
            color = Colors.Blue;
        } 
        else if (match.color == RED_TARGET) 
        {
            color = Colors.Red;
        } 
        else if (match.color == GREEN_TARGET) 
        {
            color = Colors.Green;
        } 
        else if (match.color == YELLOW_TARGET) 
        {
            color = Colors.Yellow;
        } 
        else 
        {
            color = Colors.Unknown;
        }

       // Debug.printOnce(color + ": " + detectedColor.red + ", " + detectedColor.green + ", " + detectedColor.blue);

        return color;
    }

    public enum Colors 
    {
        Blue,
        Red,
        Green,
        Yellow,
        Unknown
    }
}
package frc.robot.subsystems;

public class ControlModule
{
    private static ControlMode mode = ControlMode.Intake;
    private static ControlMode[] modes = ControlMode.values();
    private static int intMode = 0;

    //newMode is the mode that is being checked. If newMode is the current mode, return true
    public static boolean checkStatus(ControlMode newMode)
    {
        return mode == newMode;
    }

    public static void changeMode(boolean negative)
    {
        if (!negative)
        {
            intMode++;
            if (intMode <= modes.length)
            {
                intMode = 0;
            }
        }
        else
        {
            intMode--;
            if (intMode < 0)
            {
                intMode = modes.length - 1;
            }
        }
        mode = modes[intMode];
    }

    public enum ControlMode
    {
        Intake,
        Shooter,
        Arm,
        Conveyor
    }
}
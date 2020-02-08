package frc.robot.subsystems;

import frc.robot.enums.ControlMode;

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

    public static void changeMode()
    {
        intMode++;
        if (intMode == modes.length)
        {
            mode = modes[0];
        }
        else
        {
            mode = modes[intMode];
        }
    }
}
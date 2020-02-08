package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.util.Debug;
import frc.robot.controls.OI;


public class SolenoidSystem 
{
    private static final DoubleSolenoid SOLENOID = new DoubleSolenoid(0, 1);
    private static final SpeedController SOLENOID_CONTROL_MOTOR = new PWMVictorSPX(7);

    // Send the solenoid forward
    public static void forward() 
    {
        // Set solenoid value
        SOLENOID.set(DoubleSolenoid.Value.kForward);
    }

    // Send the solenoid in reverse
    public static void reverse() 
    {
        // Set solenoid value
        Debug.printOnce("Reversed!");
        SOLENOID.set(DoubleSolenoid.Value.kReverse);
    }

    private static boolean scanSolenoidButton() 
    {
        if (OI.PNEU_BACKWARD_BUTTON.isPressed()) reverse();
        if (OI.PNEU_FORWARD_BUTTON.isPressed()) forward();
        if (OI.COLOR_STICK.getPOV() == 0)
        {
            Debug.printOnce(": " + OI.COLOR_STICK.getPOV());
            SOLENOID_CONTROL_MOTOR.set(.5);
        } 
        // if (OI.colorStick.getPOV() == 180) 
        // {
        //     Debug.printOnce(": " + OI.colorStick.getPOV());
        //     SOLENOID_CONTROL_MOTOR.set(-.5);
        // }
        // if (OI.colorStick.getPOV() == -1) 
        // {
        //     Debug.printOnce(": " + OI.colorStick.getPOV());
        //     SOLENOID_CONTROL_MOTOR.stopMotor();
        // }

        return false;
    }

    public static void update() {
        scanSolenoidButton();
    }
}

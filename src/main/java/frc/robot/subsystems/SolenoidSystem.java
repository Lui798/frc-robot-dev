package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.Debug;
import frc.robot.OI;

public class SolenoidSystem 
{
    private final DoubleSolenoid SOLENOID = new DoubleSolenoid(0, 1);
    private final SpeedController SOLENOID_CONTROL_MOTOR = new PWMVictorSPX(7);

    public SolenoidSystem()
    {}

    // Send the solenoid forward
    public void forward() 
    {
        // Set solenoid value
        Debug.printOnce("Forward!");
        this.SOLENOID.set(DoubleSolenoid.Value.kForward);
    }

    // Send the solenoid in reverse
    public void reverse() 
    {
        // Set solenoid value
        Debug.printOnce("Reversed!");
        this.SOLENOID.set(DoubleSolenoid.Value.kReverse);
    }

    private boolean scanSolenoidButton() 
    {
        if (OI.isButtonPressed(OI.PNEU_BACKWARD_CODE)) reverse();
        if (OI.isButtonPressed(OI.PNEU_FORWARD_CODE)) forward();
        if (OI.colorStick.getPOV() == 0)
        {
            Debug.printOnce(": " + OI.colorStick.getPOV());
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

    public void update() {
        scanSolenoidButton();
    }
}

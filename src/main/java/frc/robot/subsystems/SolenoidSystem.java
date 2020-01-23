package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.OI;

public class SolenoidSystem {
    private final DoubleSolenoid solenoid;

    public SolenoidSystem(int forwardChannel, int reverseChannel) {
        this.solenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
    }

    // Send the solenoid forward
    public void forward() {
        // Set solenoid value
        this.solenoid.set(DoubleSolenoid.Value.kForward);
    }

    // Send the solenoid in reverse
    public void reverse() {
        // Set solenoid value
        this.solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    private boolean checkButtonPress() {
        int reverseButton = 1;
        int forwardButton = 2;

        if (OI.colorStick.getRawButtonPressed(reverseButton)) {
            reverse();
        }
        if (OI.colorStick.getRawButtonPressed(forwardButton)) {
            forward();
        }

        return false;
    }

    public void update() {
        checkButtonPress();
    }
}

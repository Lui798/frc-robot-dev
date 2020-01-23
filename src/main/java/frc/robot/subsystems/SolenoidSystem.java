package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SolenoidSystem {
    private final DoubleSolenoid solenoid;

    public SolenoidSystem(int forwardChannel, int reverseChannel) {
        this.solenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
    }

    public void forward() {
        this.solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void reverse() {
        this.solenoid.set(DoubleSolenoid.Value.kReverse);
    }
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SolenoidSubsystem extends Subsystem{

    public DoubleSolenoid grabber = new DoubleSolenoid(6,7); //Right Transmission
    public DoubleSolenoid driveShift = new DoubleSolenoid(2,3); //left Transmission
    public DoubleSolenoid frontLift = new DoubleSolenoid(0, 1);
    public DoubleSolenoid backLift = new DoubleSolenoid(4, 5);

    boolean isUp = false;

    @Override
    protected void initDefaultCommand() { }

    public void shiftUp() {
        grabber.set(DoubleSolenoid.Value.kForward);
        // shiftUp(grabber);
    }

    public void shiftUp(DoubleSolenoid solenoid) {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void shiftDown() {
        grabber.set(DoubleSolenoid.Value.kReverse);
        // shiftDown(grabber);
    }

    public void shiftDown(DoubleSolenoid solenoid) {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void shiftDrive() {
        if (isUp) {
            shiftUp(driveShift);
            shiftUp(grabber);
            isUp = false;
        } else {
            shiftDown(driveShift);
            shiftDown(grabber);
            isUp = true;
        }
    }

    public void shiftDrive(boolean up) {
        if (up) {
            shiftUp(driveShift);
        } else {
            shiftDown(driveShift);
        }
    }

}
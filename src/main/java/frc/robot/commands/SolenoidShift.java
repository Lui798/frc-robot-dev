package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SolenoidShift extends Command {
    
    int state;

    public SolenoidShift(int state) {
        this.requires(Robot.solenoid);
        this.state = state;
    }

    @Override
    protected void initialize() {
        if (state == 0)
            Robot.solenoid.shiftUp();
        if (state == 1)
            Robot.solenoid.shiftDown();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}
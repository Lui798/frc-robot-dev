package frc.robot.subsystems;

import java.util.Arrays;
import java.util.Collections;

import frc.robot.RobotMap;


import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	
	SpeedController fl = new Victor(RobotMap.DRIVETRAIN_LEFT_FRONT); 
	SpeedController bl = new Victor(RobotMap.DRIVETRAIN_LEFT_BACK);
	SpeedController fr = new Victor(RobotMap.DRIVETRAIN_RIGHT_FRONT);
	SpeedController br = new Victor(RobotMap.DRIVETRAIN_RIGHT_BACK);

	public boolean lifting = false;
	
	public double speedMultiplier = 1;

	public DriveSubsystem() {
		// fl.setInverted(true);
		// bl.setInverted(true);
	}

	@Override
	protected void initDefaultCommand() {
		// setDefaultCommand(new DefaultDriveCommand());
	}
	
	public void drive(double speed) {
		drive(speed,speed);
	}
	
	public void drive(double leftSpeed, double rightSpeed) {
		drive(leftSpeed,rightSpeed,leftSpeed,rightSpeed);
	}

	public void adjustDrive(double leftSpeed, double rightSpeed) {
		drive(leftSpeed,rightSpeed,
		leftSpeed ,rightSpeed);
	}
	
	public void drive(double flSpeed, double frSpeed, double blSpeed, double brSpeed) {
		fl.set(flSpeed);
		fr.set(frSpeed);
		bl.set(blSpeed);
		br.set(brSpeed);

	}


	public void shiftDrive(double y) {
		fl.set(y);
		bl.set(y);
		fr.set(y);
		br.set(y);
	}

	public void shiftDrive(double left, double right) {
		fl.set(left);
		bl.set(left);
		fr.set(right);
		br.set(right);
	}

	public void turnLeft(double speed) {
		fl.set(speed);
		bl.set(speed);
		fr.set(-speed);
		br.set(-speed);
	}

	public void turnRight(double speed) {
		fl.set(-speed);
		bl.set(-speed);
		fr.set(speed);
		br.set(speed);
	}
	

}
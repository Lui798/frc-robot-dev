/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveArcade;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	// Victors
	Victor leftFrontVictor = null;
	Victor leftBackVictor = null;
	Victor rightFrontVictor = null;
	Victor rightBackVictor = null;

	DifferentialDrive differentialDrive = null;

	// Encoders
	Encoder driveEncoder = null;

	public Drivetrain() {
		// Victors
		leftFrontVictor = new Victor(RobotMap.DRIVETRAIN_LEFT_FRONT);
		leftBackVictor = new Victor(RobotMap.DRIVETRAIN_LEFT_BACK);
		rightFrontVictor = new Victor(RobotMap.DRIVETRAIN_RIGHT_FRONT);
		rightBackVictor = new Victor(RobotMap.DRIVETRAIN_RIGHT_BACK);

		SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftFrontVictor, leftBackVictor);
		SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightFrontVictor, rightBackVictor);

		differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

		// Encoders
		driveEncoder = new Encoder(RobotMap.DRIVETRAIN_DRIVE_ENCODER_A, RobotMap.DRIVETRAIN_DRIVE_ENCODER_B);
	}

	public void arcadeDrive(double moveSpeed, double rotateSpeed) {
		differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
	}

	public double getDriveEncoderCount() {
		return driveEncoder.get();
	}

	public void resetDriveEncoderCount() {
		driveEncoder.reset();
	}

	public double getDriveEncoderDistance() {
		double driveEncoderCountsPerFoot = 1;
		return (getDriveEncoderCount() / (driveEncoderCountsPerFoot)) * 12;
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveArcade());
	}
}

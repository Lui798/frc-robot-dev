package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.controls.OI;

//A drive system.
//To do what? Drive the robot, of course!
//It is used in Robot.java and probably nowhere else (at least it shouldn't be);

public class DriveSystem
{
    private DifferentialDrive wheelsMotor1; //The left... or right motor? At this point, I don't even know.
    private DifferentialDrive wheelsMotor2; //The opposite of the wheelsMotor1.
    private final double SPEED_MULTIPLIER = 0.951;

    public DriveSystem()
    {
        //Remember, the PWNVictorSPX is the actual motor. 
        //PWMVictorSPX takes in the port number of the motor.
        //DifferentialDrive is a class that makes running the two motors together easier.
        //If you want to know more, maybe consider reading the frc wpilib documentation to know more.
        wheelsMotor1 = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(2));
        wheelsMotor2 = new DifferentialDrive(new PWMVictorSPX(1), new PWMVictorSPX(3));
    }
    
    //Pretty obvious what this function does. Run this in the Robot.java teleopPeriodic() function to run the robot.
    public void update()
    {
        wheelsMotor2.tankDrive(-OI.leftStick.getY() * SPEED_MULTIPLIER, -(OI.rightStick.getY() * SPEED_MULTIPLIER));
        wheelsMotor1.tankDrive(-OI.leftStick.getY() * SPEED_MULTIPLIER, -(OI.rightStick.getY() * SPEED_MULTIPLIER));
    }
}
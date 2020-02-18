package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.controls.OI;
import frc.robot.util.Debug;

//A drive system.
//To do what? Drive the robot, of course!

public class DriveSystem
{
    /*  
    Remember, the PWNVictorSPX is the actual motor. 
    PWMVictorSPX takes in the port number of the motor.
    DifferentialDrive is a class that makes running the two motors together easier.
    If you want to know more, maybe consider reading the frc wpilib documentation to know more.
    */
    //The left... or right motor? At this point, I don't even know.
    private static final DifferentialDrive wheelsMotor1 = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(2));
    //The opposite of the wheelsMotor1.
    private static final DifferentialDrive wheelsMotor2 = new DifferentialDrive(new PWMVictorSPX(1), new PWMVictorSPX(3)); 
    private static final double SPEED_MULTIPLIER = 0.951;
    
    //Pretty obvious what this function does. Run this in the Robot.java teleopPeriodic() function to run the robot.
    public static void update()
    {
        wheelsMotor2.tankDrive(-OI.LEFT_STICK.getY() * SPEED_MULTIPLIER, -(OI.RIGHT_STICK.getY() * SPEED_MULTIPLIER));
        wheelsMotor1.tankDrive(-OI.LEFT_STICK.getY() * SPEED_MULTIPLIER, -(OI.RIGHT_STICK.getY() * SPEED_MULTIPLIER));
    }

    public static void moveWheels(double leftSpeed, double rightSpeed)
    {
        Debug.printOnce("moveWheels: Hello");
        wheelsMotor2.tankDrive(leftSpeed, rightSpeed);
        wheelsMotor1.tankDrive(leftSpeed, rightSpeed);
    }

    public static void stopWheels()
    {
        wheelsMotor2.stopMotor();
        wheelsMotor1.stopMotor();
    }
}
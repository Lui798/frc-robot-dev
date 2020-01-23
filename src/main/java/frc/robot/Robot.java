/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.subsystems.ColorDetection;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive wheelsMotor1;
  private DifferentialDrive wheelsMotor2;
  private SpeedController colorMotor;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private Joystick m_colorStick;

  private final ColorDetection colorDetection = new ColorDetection();
  private final int ColorAmount = 8;
  private int colorsPassed = 0;
  private String colorSelection;
  private String currentColor;
  private String prevColor;
  private boolean spin;

  @Override
  public void robotInit() {
    wheelsMotor1 = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(2));
    wheelsMotor2 = new DifferentialDrive(new PWMVictorSPX(1), new PWMVictorSPX(3));
    colorMotor = new PWMVictorSPX(4);
    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);
    m_colorStick = new Joystick(2); 

    colorSelection = "";
    currentColor = "";
    prevColor = "";
    spin = false;
  }

  //An automatic update method.
  @Override
  public void teleopPeriodic() 
  {
    currentColor = colorDetection.runDetection();

    //Driving with controllers
    wheelsMotor2.tankDrive(-m_leftStick.getY(), -(m_rightStick.getY()*.951));
    wheelsMotor1.tankDrive(-m_leftStick.getY(), -(m_rightStick.getY()*.951));
    
    int blueColorCodeButton = 1;
    int redColorCodeButton = 2;
    int yellowColorCodeButton = 3;
    int greenColorCodeButton = 4;
    int spinColorCodeButton = 5;

    if (m_colorStick.getRawButtonPressed(blueColorCodeButton))
    {
      colorSelection = "Blue";
      spin = true;
    }
    else if (m_colorStick.getRawButtonPressed(redColorCodeButton))
    {
      colorSelection = "Red";
      spin = true;
    } 
    else if (m_colorStick.getRawButtonPressed(yellowColorCodeButton))
    {
      colorSelection = "Yellow";
      spin = true;
    } 
    else if (m_colorStick.getRawButtonPressed(greenColorCodeButton))
    {
      colorSelection = "Green";
      spin = true;
    } 
    else if (m_colorStick.getRawButtonPressed(spinColorCodeButton))
    {
      colorsPassed = 0;
      colorSelection = "";
      spin = true;
    } 
    
    if (spin)
    {
      colorMotor.set(1.0);
      //This means that it should rotate between 3-5 times
      if (colorSelection.equals(""))
      {
        //If the sensor sees anything other than RGBY ignore it
        if (!currentColor.equals("Unknown"))
        {
          //If the previously detected color is not the same as the current
          if (!prevColor.equals(currentColor))
          {
            colorsPassed++;
            System.out.println(currentColor + ", " + colorsPassed);
          }
          //If it has spun 3.5 times, stop it
          if (colorsPassed >= ColorAmount * 3.5)
          {
            colorMotor.stopMotor();
            spin = false;
          }
        }
      }
      else if (colorDetection.isColorMatch(colorSelection))
      {
        colorMotor.stopMotor();
        colorSelection = "";
        spin = false;
      }
    }

    prevColor = currentColor;
  }

  public void updateColorMotor(int rawColorCodeButton)
  {
    
  }
}

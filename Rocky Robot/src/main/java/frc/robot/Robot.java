// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot 
{
  //Private variables 
  private Joystick m_controller;
  private Drive m_driveController;
  private Pnumatics m_pnumatics;
  private Gripper m_gripper;


  /**
   * @implNote Robot Constructor
   */
  public Robot()
  {
  
  }


  @Override
  public void robotInit() 
  {
      //Construct a joystic object on specified port
      m_controller = new Joystick(0);
      //Construct a differential drive
      m_driveController = new Drive(0,1);
      //Construct pnumatics
      m_pnumatics = new Pnumatics();
      //Construct a gripper that takes pnumatics
      m_gripper = new Gripper(m_pnumatics);

      //Initialize any other variables here
      // m_pnumatics.EnableCompressor();
  }

  @Override
  public void teleopPeriodic() {

    if(m_driveController != null && m_controller != null)
    {
      //Get the x and y values of the controller and send to the arcade drive
      m_driveController.MoveArcade(-m_controller.getY(),-m_controller.getX());

      if (m_controller.getRawButtonPressed(6)){
        m_gripper.OpenGripper();
      } else {
        m_gripper.CloseGripper();
      }

      // if (m_controller.getRawButtonPressed(4)){
        // m_gripper.CloseGripper();
      // }
    }
    //TODO: Check for other items such as pnumatic statuses, arm locations, etc.

  }

}
  
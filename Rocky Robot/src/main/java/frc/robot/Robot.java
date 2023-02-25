// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot 
{
  //Private variables 
  // private Joystick m_controller;
  // private Drive m_driveController;
  // private Pnumatics m_pnumatics;
  // private Gripper m_gripper;
  // private Trigger m_gripperBtn;
  // private Command m_gripperBtnCommand;

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;  


  /**
   * @implNote Robot Constructor
   */
  public Robot()
  {
      m_robotContainer = new RobotContainer();
  }


  @Override
  public void robotInit() 
  {
      //Construct a joystic object on specified port
      //m_controller = new Joystick(0);
      //Construct a differential drive
      //m_driveController = new Drive(0,1);
      //Construct pnumatics
      //m_pnumatics = new Pnumatics();
      //Construct a gripper that takes pnumatics
      //m_gripper = new Gripper(m_pnumatics);

      //Initialize any other variables here
      // m_pnumatics.EnableCompressor();

      // m_gripperBtnCommand = new Command() {
      //   return this.runOnce(() => m_gripper.OpenGripper());
      // };
      // m_gripperBtn = new JoystickButton(m_controller,6).whileTrue(m_gripper.OpenGripper());

  }

  @Override
  public void teleopPeriodic() {

    //---------- Below is to move the robot with out using the command framework ------
    // if(m_driveController != null && m_controller != null)
    // {
    //   //Get the x and y values of the controller and send to the arcade drive
    //   m_driveController.MoveArcade(-m_controller.getY(),-m_controller.getX());

    //   if (m_controller.getRawButtonPressed(6)){
    //     m_gripper.OpenGripper();
    //   } else {
    //     m_gripper.CloseGripper();
    //   }

    //   if (m_controller.getRawButtonPressed(4)){
    //     m_gripper.CloseGripper();
    //   } else {
    //     m_gripper.OpenGripper();
    //   }
    // }
    //TODO: Check for other items such as pnumatic statuses, arm locations, etc.

    

  }

  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.schedule();\\
    // }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}

}
  
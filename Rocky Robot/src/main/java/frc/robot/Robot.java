// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private final VictorSP m_leftMotor = new VictorSP(0);
  private final VictorSP m_rightMotor = new VictorSP(1);

  private final Joystick m_controller = new Joystick(0);
  //private final XboxController controller = new XboxController(0);
  
  //Potentially for future use. This may be used for the gripper. I think we should be able to use a neo brushless with a spark max.
  //the can option can give us some telemetry (current and/or encoder counts)
  private final CANSparkMax m_gripperMotor =  new CANSparkMax(0,MotorType.kBrushless);


  
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    
    //m_rightMotor.setInverted(true);

    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.arcadeDrive(-m_controller.getY(), -m_controller.getX()); 

    // m_myRobot.tankDrive(-m_controller.getY(), m_controller.getY());
  }

  // public Boolean CountEncoder()
  // {
    
  // }

}
  
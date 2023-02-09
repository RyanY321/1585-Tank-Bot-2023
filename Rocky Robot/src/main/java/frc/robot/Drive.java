package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class Drive {

    private DifferentialDrive m_driveController;
    private final VictorSP m_leftMotor;
    private final VictorSP m_rightMotor;
    
    public Drive(int leftMotorChannel, 
                int rightMotorChannel)
    {
        m_leftMotor = new VictorSP(leftMotorChannel);
        m_rightMotor = new VictorSP(rightMotorChannel);
    }

    /**
     * @implNote Move the robot using arcade single joystick
     * @param xSpeed motor speed
     * @param zRotation motor speed
     */
    public void MoveArcade(double xSpeed, double zRotation)
    {
        m_driveController.arcadeDrive(xSpeed, zRotation); 
    }
  
}

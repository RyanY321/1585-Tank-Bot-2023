package Subsystems;

import frc.robot.Pnumatics;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//TODO : This is a stub class for a gripper. We will 
// need to fill in code with real implementations

public class Gripper extends SubsystemBase
{
    private final Pnumatics m_pnumatics;

    /**
     * Example command factory method.
     *
     * @return a command
     */
    public CommandBase openGripperCommand() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
                this.OpenGripper();
            });
    }

    public CommandBase closeGrippercommand() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
                this.CloseGripper();
            });
    }

    public Gripper(Pnumatics pnumatics)
    {
        m_pnumatics = pnumatics;
    }

    public void OpenGripper()
    {
        m_pnumatics.TriggerGripperSolenoid(true,false);
    }

    public void CloseGripper()
    {
        m_pnumatics.TriggerGripperSolenoid(false,true);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }
    
}

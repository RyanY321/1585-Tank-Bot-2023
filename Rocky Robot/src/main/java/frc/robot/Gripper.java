package frc.robot;

//TODO : This is a stub class for a gripper. We will 
// need to fill in code with real implementations

public class Gripper 
{
    private final Pnumatics m_pnumatics;

    public Gripper(Pnumatics pnumatics)
    {
        m_pnumatics = pnumatics;
    }

    public void OpenGripper()
    {
        m_pnumatics.TriggerSolenoidA(true);
    }

    public void CloseGripper()
    {
        m_pnumatics.TriggerSolenoidA(false);
    }
    
}

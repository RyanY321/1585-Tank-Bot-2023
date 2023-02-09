package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


//TODO: this is a stub class that needs implementation. I have
// Added some functions that I think we need. They are in no 
// way final and will need to be filled in.

//Potentially for future use. This may be used for the gripper. I think we should be able to use a neo brushless with a spark max.
//the can option can give us some telemetry (current and/or encoder counts)




public class Arm 
{
    //TODO : Need to determine actual motor controllers and swap out
    private final VictorSP m_liftMotor;
    private final VictorSP m_extendMotor;

    //Thi
    private final CANSparkMax m_winchMotor;

    public Arm(int liftMotorChannel, 
            int extendMotorChannel,
            int winchMotorDeviceId)
    {
        m_liftMotor = new VictorSP(liftMotorChannel);
        m_extendMotor = new VictorSP(extendMotorChannel);
        m_winchMotor=  new CANSparkMax(winchMotorDeviceId,MotorType.kBrushed);
    }

    /**
     * @implNote Extend the Arm to the max endstop
     */
    public void ExtendArm()
    {

    }
    
    /**
     * @implNote Retract arm to min endstop
     */
    public void RetractArm()
    {

    }

    
    /**
     * @implNote Lift the arm x pulses
     * @param pulses
     */
    public void LiftArm(int pulses)
    {

    }

    /**
     * @implNote Lower the arm x pulses
     * @param pulses
     */
    public void LowerArm(int pulses)
    {

    }
    
}

package Subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;


//TODO: this is a stub class that needs implementation. I have
// Added some functions that I think we need. They are in no 
// way final and will need to be filled in.
public class Arm extends SubsystemBase
{
    //TODO : Need to determine actual motor controllers and swap out
    private final PWMSparkMax m_liftMotor;
    //private final VictorSP m_liftMotor;
    //private final VictorSP m_extendMotor;
    private final PWMSparkMax m_extendMotor;

    //This can be the winch motor controller. We can setup this controller to have a closed
    //loop setup where we tie in the endstops that way we dont have to keep track of them
    // private final PWMSparkMax m_winchMotor;
    // private final CANSparkMax m_winchMotor


    //private final Relay m_testLiftMotor = new Relay(0);
    //private final Relay m_testExtendMotor = new Relay(1);

    public Arm(int liftMotorChannel, 
            int extendMotorChannel)
            // int winchMotorDeviceId)
    {
        //TODO: Build correct motor controllers for the motors to be installed
        // m_liftMotor = new VictorSP(liftMotorChannel);
        m_liftMotor = new PWMSparkMax(liftMotorChannel);
        // m_extendMotor = new VictorSP(extendMotorChannel);
        m_extendMotor = new PWMSparkMax(extendMotorChannel);
        // m_winchMotor=  new CANSparkMax(winchMotorDeviceId,MotorType.kBrushed);
        // m_winchMotor = new PWMSparkMax(2);
    }


    //TODO : We probably should create a new command in the command directory that this is attached to. This should do for now 
    public CommandBase extendArmCommand() {
        return run(
            () -> {
                this.ExtendArm();
            });
    }

    public CommandBase retractArmCommand() {
        return run(
            () -> {
                this.RetractArm();
            });
    }

    public CommandBase liftArmCommand() {
        return run(
            () -> {
                this.LiftArm();
            });
    }

    public CommandBase lowerArmCommand() {
        return run(
            () -> {
                this.LowerArm();
            });
    }

    public CommandBase stopArmLiftCommand() {
        return runOnce(
            () -> {
                this.StopArmLift();
            });
    }

    public CommandBase stopArmExtendCommand() {
        return runOnce(
            () -> {
                this.StopArmExtend();
            });
    }

    /**
     * @implNote Extend the Arm to the max endstop
     */
    private void ExtendArm()
    {
        System.out.println("Extending Arm");
        m_extendMotor.set(0.80);
        // m_testExtendMotor.set(Relay.Value.kForward);
    }
    
    /**
     * @implNote Retract arm to min endstop
     */
    private void RetractArm()
    {
        System.out.println("Retracting Arm");
        m_extendMotor.set(-0.80);
        // m_testExtendMotor.set(Relay.Value.kReverse);
    }

    
    /**
     * @implNote Lift the arm
     * @param pulses
     */
    private void LiftArm()
    {
        System.out.println("Lift Arm");
        m_liftMotor.set(0.30);
        //m_testLiftMotor.set(Relay.Value.kForward);
    }

    /**
     * @implNote Lower the arm
     * @param pulses
     */
    private void LowerArm()
    {
        System.out.println("Lower Arm");
        m_liftMotor.set(-0.20);
        // m_testLiftMotor.set(Relay.Value.kReverse);
    }

    private void StopArmLift()
    {
        System.out.println("Arm Lift Stopped");
        m_liftMotor.set(0.00);
    }

    private void StopArmExtend()
    {
        System.out.println("Arm Extension stopped");
        m_extendMotor.set(0.00);
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

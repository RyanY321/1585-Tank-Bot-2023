package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;



/**
 * @implNote A class to deal with the pnumatics controller 
 * All items dealing with the pnumatics components will be here. 
 */
public class Pnumatics 
{
    //Compressor object
    private final Compressor m_compressor;
    private final Solenoid m_solenoidA;
    private final Solenoid m_SolenoidB;

    //TODO: add parameters to the constructor to allow for configuration. 
    public Pnumatics()
    {
        m_compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
        m_solenoidA = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
        m_SolenoidB = new Solenoid(PneumaticsModuleType.CTREPCM, 1);

    }


    /**
     * @implNote Get the current compressor state
     * @return True = on , False = off
     */
    public Boolean GetCompressorState()
    {
        return m_compressor.isEnabled();
    }
    
    /**
     * @implNote Get the current pressure switch state
     * @return True = on, False = off
     */
    public Boolean GetPressureSwitchState()
    {
        return m_compressor.getPressureSwitchValue();
    }


    /**
     * @implNote Getthe compressor current (amps)
     * @return double current(amp) usage 
     */
    public double GetCompressorCurrent()
    {
        return m_compressor.getCurrent();
    }


    /**
     * @implNote Get the current psi of the compressor
     * @return Double psi
     */
    public double GetCompressorPsi()
    {
        return m_compressor.getPressure();
    }


    /**
     * @implNote = Enable the compressor
     * @return Compressor state, True = on, False = off
     */
    public boolean EnableCompressor()
    {
        m_compressor.enableDigital();

        return GetCompressorState();
    }

    /**
     * @implNote Disable the compressor
     * @return  Compressor State, True = on, false = off
     */
    public boolean DisableCompressor()
    {
        m_compressor.disable();

        return GetCompressorState();
    }


    /**
     * @implNote Toggle Solenoid A
     * @return state of Solenoid A , True = open, false = close
     */
    public boolean TriggerSolenoidA()
    {
        m_solenoidA.toggle();
        return m_solenoidA.get();
    }


    /**
     * @implNote Toggle Solenoid A
     * @return state of Solenoid B, True = open, False = close
     */
    public boolean TriggerSolenoidB()
    {
        m_SolenoidB.toggle();
        return m_SolenoidB.get();
    }
    
}

package Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class IO extends SubsystemBase 
{

    //TODO : create variables for the buttons for other subsystem such as the arm, modify to correct button mapping
    private final int gripperCloseButton = 2;
    private final int gripperOpenButton = 1;
    private final int raiseArmBtn = 5;
    private final int lowerArmBtn = 6;
    private final int extendArmBtn = 4;
    private final int retractArmBtn = 3;


    //TODO: Swap between xbox and joystick
    private CommandJoystick m_joystickController =  new CommandJoystick(0);
    private CommandXboxController m_controller = new CommandXboxController(1);

    public IO()
    {

    }


     /**
      * @details Get the controllers X axis value
      * @return double 
      */
    public double GetX()
    {
        return m_joystickController.getX();
    }

    /**
     * @details get the Controllers y axis valu
     * @return double
     */
    public double GetY()
    {
       return m_joystickController.getY();
    }


    //TODO: Create more methods like these for extending/retracting, raising/lowering the arm

    /**
     * @details Get the open gripper button trigger 
     * @return Trigger
     */
    public Trigger GetOpenGripperBtn()
    {
        return m_controller.button(gripperOpenButton);
    }


    /**
     * @details Get the close gripper btn trigger
     * @return
     */
    public Trigger GetCloseGripperBtn()
    {
        return m_controller.button(gripperCloseButton);
    }

     /**
     * @details Get the raise arm  btn trigger
     * @return
     */
    public Trigger GetRaiseArmBtn()
    {
        return m_controller.button(raiseArmBtn);
    }

     /**
     * @details Get the lower arm  btn trigger
     * @return
     */
    public Trigger GetLowerArmBtn()
    {
        return m_controller.button(lowerArmBtn);
    }

    /**
     * @details Get the Extend Arm btn trigger
     * @return Trigger
     */
    public Trigger GetExtendArmBtn()
    {
        return m_controller.button(extendArmBtn);
    }

    /**
     * @details Get the retract arm  btn trigger
     * @return Trigger
     */
    public Trigger GetRetractArmBtn()
    {
        return m_controller.button(retractArmBtn);
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

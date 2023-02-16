package frc.robot;
import Subsystems.Drive;
import Subsystems.Gripper;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer 
{ 
  private CommandJoystick m_controller =  new CommandJoystick(0);
  private Drive m_driveController  = new Drive(0,1);
  private Pnumatics m_pnumatics = new Pnumatics();
  private Gripper m_gripper = new Gripper(m_pnumatics);

  //private DriveCommand m_driveRobotCommand;
  private RunCommand m_driveRobotCommand;
  // private Trigger m_gripperOpenBtn;
  // private Trigger m_gripperCloseBtn;
  // private Command m_gripperBtnCommand;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger()::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));


    //Schedule axis to move robot
    m_driveRobotCommand = new RunCommand(() -> m_driveController.moveArcadeCommand(
    m_controller.getY(),
    m_controller.getX()), m_driveController);

  
    //schedule button 6 to open gripper while it is pushed
    m_controller.button(6).whileTrue(m_gripper.openGripperCommand());

    //schedule button 4 to close gripper while it is pushed
    m_controller.button(4).whileTrue(m_gripper.closeGrippercommand());
    //m_gripperCloseBtn = new JoystickButton(m_controller,4).whileTrue(m_gripper.closeGrippercommand());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
 // public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem);
  //}
}
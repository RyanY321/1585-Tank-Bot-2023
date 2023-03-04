package frc.robot;

import Subsystems.Drive;
import Subsystems.Gripper;
import Subsystems.IO;
import Commands.DriveCommand;
import Subsystems.Arm;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.cameraserver.CameraServer;

public class RobotContainer {
  private IO m_controller = new IO();
  private Drive m_driveController = new Drive(0, 1);
  private Pnumatics m_pnumatics = new Pnumatics();
  private Gripper m_gripper = new Gripper(m_pnumatics);

  // TODO: Figure out what the motor channels will be
  private Arm m_arm = new Arm(5, 4);

  // private DriveCommand m_driveRobotCommand;
  private DriveCommand m_DriveCommand = new DriveCommand(m_driveController, m_controller);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

  CameraServer.startAutomaticCapture();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    // Schedule the drive controller to move
    m_driveController.setDefaultCommand(m_DriveCommand);

    // schedule button 6 to open gripper while it is pushed
    m_controller.GetOpenGripperBtn().whileTrue(m_gripper.openGripperCommand());
    m_controller.GetOpenGripperBtn().onFalse(m_gripper.StopGrippercommand());

    // schedule button 4 to close gripper while it is pushed
    m_controller.GetCloseGripperBtn().whileTrue(m_gripper.closeGrippercommand());
    m_controller.GetCloseGripperBtn().onFalse(m_gripper.StopGrippercommand());

    // TODO: Determine which button should extend the arm, create new functions in
    // the IO class and copy above structure
    m_controller.GetRaiseArmBtn().whileTrue(m_arm.liftArmCommand());
    m_controller.GetRaiseArmBtn().onFalse(m_arm.stopArmLiftCommand());

    m_controller.GetLowerArmBtn().whileTrue(m_arm.lowerArmCommand());
    m_controller.GetLowerArmBtn().onFalse(m_arm.stopArmLiftCommand());

    // TODO: Determine which button should retract the arm
    m_controller.GetExtendArmBtn().whileTrue(m_arm.extendArmCommand());
    m_controller.GetExtendArmBtn().onFalse(m_arm.stopArmExtendCommand());

    m_controller.GetRetractArmBtn().whileTrue(m_arm.retractArmCommand());
    m_controller.GetRetractArmBtn().onFalse(m_arm.stopArmExtendCommand());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  // An example command will be run in autonomous
  // return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
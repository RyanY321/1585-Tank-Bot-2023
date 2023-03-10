package Commands;

import Subsystems.Arm;
import Subsystems.IO;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ArmCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Arm m_armSubsystem;
  private final IO m_controller;
    

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArmCommand(Arm armSubsystem, IO controller) {
    m_armSubsystem = armSubsystem;
    m_controller = controller;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armSubsystem);
    addRequirements(controller);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Drive Command initialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_armSubsystem.LiftArm(m_controller.GetLiftArmValue());
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

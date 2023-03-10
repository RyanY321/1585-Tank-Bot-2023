package frc.robot;

import Subsystems.Drive;
import Subsystems.Gripper;
import Subsystems.IO;

import Commands.DriveCommand;
import Commands.DriveAutoCommand;
import Subsystems.Arm;
import Subsystems.Auto;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;

public class RobotContainer {
  private IO m_controller = new IO();
  private Drive m_driveController = new Drive(0, 1);
  private Pnumatics m_pnumatics = new Pnumatics();
  private Gripper m_gripper = new Gripper(m_pnumatics);
  private Arm m_arm = new Arm(5, 4);
  private DriveCommand m_DriveCommand = new DriveCommand(m_driveController, m_controller);
  private Auto m_auto = new Auto(m_driveController);

  private SequentialCommandGroup m_progOneAuto = new SequentialCommandGroup();
  private SequentialCommandGroup m_progTwoAuto = new SequentialCommandGroup();


  //-------Simulator Variables -----///
  //TODO: put these in a seperate subsystem
  private AnalogGyro gyro;
  private AnalogGyroSim m_gyroSim;

  private Encoder leftEncoder;
  private Encoder rightEncoder;
  private EncoderSim m_leftEncoderSim;
  private EncoderSim m_rightEncoderSim;

  private static final double kWheelRadius = 0.0508; // meters
  private static final int kEncoderResolution = 4096;

  
  private DifferentialDrivetrainSim m_driveSim = DifferentialDrivetrainSim.createKitbotSim(
    KitbotMotor.kDoubleFalcon500PerSide, // 2 CIMs per side.
    KitbotGearing.k10p71, // 10.71:1
    KitbotWheelSize.kSixInch, // 6" diameter wheels.
    null // No measurement noise.
);

private DifferentialDriveOdometry m_odometry;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // Configure the controller bindings
    configureBindings();

    //Start camera server for teleop
    CameraServer.startAutomaticCapture();

    //Setup basic autonomous commands one
    m_progOneAuto.addCommands(
      new DriveAutoCommand(m_driveController, .50, .50),
      new WaitCommand(10),
      new DriveAutoCommand(m_driveController, -.50, -.50),
      new WaitCommand(10),
      new DriveAutoCommand(m_driveController, 0, 0)
    );

    m_progTwoAuto.addCommands(
      new DriveAutoCommand(m_driveController, .50, .50), //Forward
      new WaitCommand(3),
      new DriveAutoCommand(m_driveController, -.50, .50), //Left
      new WaitCommand(.25),
      new DriveAutoCommand(m_driveController, .50, .50),
      new WaitCommand(4),
      new DriveAutoCommand(m_driveController, 0, 0)
    );

    
    //--- Simulator variable setup ----///
    gyro = new AnalogGyro(0);
    //addChild("gyro", gyro);
    gyro.setSensitivity(0.007);
    m_gyroSim = new AnalogGyroSim(gyro);
    leftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
    rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
    leftEncoder.setDistancePerPulse(2 * Math.PI * kWheelRadius / kEncoderResolution);
    rightEncoder.setDistancePerPulse(2 * Math.PI * kWheelRadius / kEncoderResolution);

    m_leftEncoderSim = new EncoderSim(leftEncoder);
    m_rightEncoderSim = new EncoderSim(rightEncoder);

    m_odometry = new DifferentialDriveOdometry(gyro.getRotation2d(), 0, 0);

    leftEncoder.reset();
    rightEncoder.reset();
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

    //Gripper Close Button
    m_controller.GetOpenGripperBtn().whileTrue(m_gripper.openGripperCommand());
    m_controller.GetOpenGripperBtn().onFalse(m_gripper.StopGrippercommand());

    //Gripper Close Button
    m_controller.GetCloseGripperBtn().whileTrue(m_gripper.closeGrippercommand());
    m_controller.GetCloseGripperBtn().onFalse(m_gripper.StopGrippercommand());

    //Raise Arm button
    m_controller.GetRaiseArmBtn().whileTrue(m_arm.liftArmCommand());
    m_controller.GetRaiseArmBtn().onFalse(m_arm.stopArmLiftCommand());

    //Lower Arm Button
    m_controller.GetLowerArmBtn().whileTrue(m_arm.lowerArmCommand());
    m_controller.GetLowerArmBtn().onFalse(m_arm.stopArmLiftCommand());

    //Extend Arm Button
    m_controller.GetExtendArmBtn().whileTrue(m_arm.extendArmCommand());
    m_controller.GetExtendArmBtn().onFalse(m_arm.stopArmExtendCommand());

    //Retract Arm Button
    m_controller.GetRetractArmBtn().whileTrue(m_arm.retractArmCommand());
    m_controller.GetRetractArmBtn().onFalse(m_arm.stopArmExtendCommand());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public CommandBase getAutonomousCommand(String selectedAuto) {

      System.out.println(String.format("Getting autonomous program for selected, %s", selectedAuto));  
      switch(selectedAuto)
      {
        case "Program One":
        return m_progOneAuto;
        case "Program Two":
        return m_progTwoAuto;
        default:
        return m_progOneAuto;
        
      }

  }

  public Pose2d GetPoseMeters()
  {
    return m_odometry.getPoseMeters();
  }

  public void SimPeriodic()
  {
      double leftVal = m_driveController.GetLeft();
      double rightVal = m_driveController.GetRight();

      m_driveSim.setInputs(leftVal* RobotController.getInputVoltage(),
                rightVal * RobotController.getInputVoltage());

        // Advance the model by 20 ms. Note that if you are running this
        // subsystem in a separate thread or have changed the nominal timestep
        // of TimedRobot, this value needs to match it.
        m_driveSim.update(0.02);

        // Update all of our sensors.
        m_leftEncoderSim.setDistance(m_driveSim.getLeftPositionMeters());
        m_leftEncoderSim.setRate(m_driveSim.getLeftVelocityMetersPerSecond());
        m_rightEncoderSim.setDistance(m_driveSim.getRightPositionMeters());
        m_rightEncoderSim.setRate(m_driveSim.getRightVelocityMetersPerSecond());
        m_gyroSim.setAngle(-m_driveSim.getHeading().getDegrees());

        m_odometry.update(gyro.getRotation2d(),
        leftEncoder.getDistance(),
        rightEncoder.getDistance());
  }
}
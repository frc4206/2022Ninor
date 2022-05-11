// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.VisionAlignStopCommand;
import frc.robot.commands.conveyor.ConveyorAutoCommand;
import frc.robot.commands.conveyor.ConveyorForwardCommand;
import frc.robot.commands.harvestor.HarvestorInCommand;
import frc.robot.commands.harvestor.HarvestorOutCommand;
import frc.robot.commands.harvestor.HarvestorReverseCommand;
import frc.robot.commands.shooter.ShooterStopCommand;
import frc.robot.commands.shooter.ShooterWallHubCommand;
import frc.robot.commands.shooter.ShooterWallHubPlusCommand;
import frc.robot.commands.shooter.ShooterWallHubSemiPlusCommand;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.HarvestorSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import java.util.List;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoBallLeftKickAuto extends SequentialCommandGroup {
  public TwoBallLeftKickAuto(SwerveSubsystem s_Swerve, HarvestorSubsystem m_harvestor, ConveyorSubsystem m_conveyor, ShooterSubsystem m_shooter, PneumaticsSubsystem m_pneumatics){
      TrajectoryConfig config =
          new TrajectoryConfig(
                  Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                  Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
              .setKinematics(Constants.Swerve.swerveKinematics);


      //------------An example trajectory to follow.  All units in meters.-------------------//
      Trajectory tarjectoryPart1 =
          TrajectoryGenerator.generateTrajectory(
              // Start at the origin facing the +X direction
              new Pose2d(0, 0, new Rotation2d(0)),
              // Pass through these two interior waypoints, making an 's' curve path
              List.of(new Translation2d(Units.inchesToMeters(0), Units.inchesToMeters(-15)), new Translation2d( Units.inchesToMeters(-10), Units.inchesToMeters(-55)) , new Translation2d( Units.inchesToMeters(-10), Units.inchesToMeters(-70))),
              // End 3 meters straight ahead of where we started, facing forward
              new Pose2d( Units.inchesToMeters(15), Units.inchesToMeters(-70), new Rotation2d(Units.degreesToRadians(0))),
              config);

              Trajectory tarjectoryPart2 =
              TrajectoryGenerator.generateTrajectory(
                  // Start at the origin facing the +X direction
                  new Pose2d( Units.inchesToMeters(15), Units.inchesToMeters(-70), new Rotation2d(Units.degreesToRadians(0))),
                  // Pass through these two interior waypoints, making an 's' curve path
                  List.of(new Translation2d(Units.inchesToMeters(25), Units.inchesToMeters(-45)), new Translation2d(Units.inchesToMeters(42), Units.inchesToMeters(-20))),
                  // End 3 meters straight ahead of where we started, facing forward
                  new Pose2d( Units.inchesToMeters(70), Units.inchesToMeters(-20), new Rotation2d(Units.degreesToRadians(0))),
                  config);
              //need to fix come back trajectory

              Trajectory tarjectoryPart3 =
              TrajectoryGenerator.generateTrajectory(
                  // Start at the origin facing the +X direction
                  new Pose2d(Units.inchesToMeters(70), Units.inchesToMeters(-20), new Rotation2d(Units.degreesToRadians(0))),
                  // Pass through these two interior waypoints, making an 's' curve path
                  List.of(new Translation2d(Units.inchesToMeters(50), Units.inchesToMeters(0))),
                  // End 3 meters straight ahead of where we started, facing forward
                  new Pose2d(Units.inchesToMeters(10), Units.inchesToMeters(0), new Rotation2d(-60)),
                  config);


      //------------------------The PID Controller for the actual auto------------------------//
      var thetaController =
          new ProfiledPIDController(
              2, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
      thetaController.enableContinuousInput(-Math.PI, Math.PI);


      //------------------------Making Of driving Commands----------------------------------//
      SwerveControllerCommand drivingPart1 =
          new SwerveControllerCommand(
              tarjectoryPart1,
              s_Swerve::getPose,
              Constants.Swerve.swerveKinematics,
              new PIDController(Constants.AutoConstants.kPXController, 0, 0),
              new PIDController(Constants.AutoConstants.kPYController, 0, 0),
              thetaController,
              s_Swerve::setModuleStates,
              s_Swerve);       

      SwerveControllerCommand drivingPart2 =
          new SwerveControllerCommand(
              tarjectoryPart2,
              s_Swerve::getPose,
              Constants.Swerve.swerveKinematics,
              new PIDController(Constants.AutoConstants.kPXController, 0, 0),
              new PIDController(Constants.AutoConstants.kPYController, 0, 0),
              thetaController,
              s_Swerve::setModuleStates,
              s_Swerve);     

              SwerveControllerCommand drivingPart3 =
              new SwerveControllerCommand(
                  tarjectoryPart3,
                  s_Swerve::getPose,
                  Constants.Swerve.swerveKinematics,
                  new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                  new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                  thetaController,
                  s_Swerve::setModuleStates,
                  s_Swerve);    


      //---------------------The Actual Command List That will Run-----------------//

      addCommands(

          //resets odemetry
          new InstantCommand(() -> s_Swerve.resetOdometry(tarjectoryPart1.getInitialPose())),

          new HarvestorOutCommand(m_harvestor, m_pneumatics).withTimeout(0.1),

          //drives to ball and spins
          new ParallelRaceGroup(
            drivingPart1,
            new HarvestorReverseCommand(m_harvestor).withTimeout(4)
            ),

            new ParallelCommandGroup(
                drivingPart2,
                new HarvestorOutCommand(m_harvestor, m_pneumatics).withTimeout(0.1)
                ),

            //comes back
            new ParallelCommandGroup(
                drivingPart3,
                new ConveyorAutoCommand(m_conveyor).withTimeout(1),
                new ShooterWallHubSemiPlusCommand(m_shooter).withTimeout(1)

            ),


          //turns around
          new ParallelCommandGroup(
            new VisionAlignStopCommand(s_Swerve, true, true).withTimeout(0.2),
            new ShooterWallHubSemiPlusCommand(m_shooter).withTimeout(0.2)
          ),

          new ParallelCommandGroup(
              new ShooterWallHubPlusCommand(m_shooter).withTimeout(3),
              new ConveyorForwardCommand(m_conveyor).withTimeout(3),
              new VisionAlignStopCommand(s_Swerve, true, true).withTimeout(3)
          ),

          new ParallelCommandGroup(
              new ShooterWallHubPlusCommand(m_shooter).withTimeout(3),
              new HarvestorInCommand(m_harvestor, m_pneumatics).withTimeout(0.2),
              new ConveyorForwardCommand(m_conveyor).withTimeout(3),
              new VisionAlignStopCommand(s_Swerve, true, true).withTimeout(3)
          )
      );
  }
}
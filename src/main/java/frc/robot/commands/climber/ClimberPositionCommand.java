// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberPositionCommand extends CommandBase {
  private final ClimberSubsystem motors;
  private double position;
  private boolean isFinished;
  /** Creates a new Com_Motors. */
  public ClimberPositionCommand(ClimberSubsystem subsystem, double pos) {
    // Use addRequirements() here to declare subsystem dependencies.
    motors = subsystem;
    position = pos;
    addRequirements(motors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isFinished = true;
    motors.goTo(position);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    isFinished = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}

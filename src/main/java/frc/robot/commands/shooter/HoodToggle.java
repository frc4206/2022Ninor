// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Pneumatics;
import frc.robot.subsystems.PneumaticsSubsystem;
public class HoodToggle extends CommandBase {
  private final PneumaticsSubsystem m_pnuematics;
  public HoodToggle(PneumaticsSubsystem pneumatics) {
    m_pnuematics = pneumatics;
    addRequirements(pneumatics);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_pnuematics.HoodShifter();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

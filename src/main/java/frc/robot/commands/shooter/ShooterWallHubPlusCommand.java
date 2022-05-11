// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterWallHubPlusCommand extends CommandBase {
  private final ShooterSubsystem motors;

  public ShooterWallHubPlusCommand(ShooterSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    motors = subsystem;
    addRequirements(motors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    motors.shooterSetPowerWallHighPlus();
<<<<<<< HEAD
    motors.shooterWallHighPlus();
=======
    motors.shooterWallHigh();
>>>>>>> 6071e81e5e5ff658c96a85cc3f9d81964ea001ec
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
<<<<<<< HEAD
    motors.ledColor1();
=======
>>>>>>> 6071e81e5e5ff658c96a85cc3f9d81964ea001ec

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
<<<<<<< HEAD
=======
    Robot.getRobotContainer().offRumble();
>>>>>>> 6071e81e5e5ff658c96a85cc3f9d81964ea001ec

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

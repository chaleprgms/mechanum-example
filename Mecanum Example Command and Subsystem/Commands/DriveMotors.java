/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveMotors extends Command {
  
  public DriveMotors() {
    /*
     * Sets up command dependency and links it to Robot.m_drive (NeoDriveBase Subsystem)
     */ 
    requires(Robot.m_drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    /*
     * Pulls our trigger values to send to our TStrafe Commands
     */

    double left_trigger = Robot.m_oi.controller.getRawAxis(2);
    double right_trigger = Robot.m_oi.controller.getRawAxis(3);


    /*
     * Logic to judge if a trigger is pressed, if so run respective tstrafe command
     *
     * Else set check our controller deadzone, then call our standard drive() method
     */
     
    if(left_trigger > 0){
      Robot.m_drive.strafeLeft(left_trigger);
    }else if(right_trigger > 0){
      Robot.m_drive.strafeRight(right_trigger);
    }else{
      if(Math.abs(left)<RobotMap.DEADZONE){
          left = 0;
        }else if(Math.abs(right)<RobotMap.DEADZONE){
          right = 0;
      }

      Robot.m_drive.drive();
    }


    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

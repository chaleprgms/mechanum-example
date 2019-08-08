/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveMotors;


/**
 *
 *  PLEASE NOTE:
 *  FOR THE COMMAND TO ACCESS THIS SUBSYSTEM (command based robots)
 *  YOU MUST CREATE A SUBSYSTEM OBJECT IN YOUR Robot.java FILE
 *  EX: 'public static NeoDriveBase m_drive = new NeoDriveBase();'
 *  
 *  WITHOUT THIS YOU CANNOT ACCESS YOUR SUBSYSTEM IN THE COMMAND FILES
 *
 */
public class NeoDriveBase extends Subsystem {
  /*
   * Opens 4 unintialized motor controllers, for ORCA-nizational porpoise-s (organizational purposes)
   */

  CANSparkMax lf_motor, rf_motor, lb_motor, rb_motor;

  /*
   * Constructor to call and initialize our motors above
   */
  public NeoDriveBase()
  {


    /*
     * Initializes a 4 wheel / 4 motor direct drive base 
     * made up complete of REV Neo Brushless motors / CANSparkMaxes
     *
     * If your drive base isn't REV Neo / SparkMaxes, be sure to use 
     * your proper motor controller objects, e. g. Spark(), TalonSRX(), VictorSPX(),
     * and be sure to initialize them properly.
     *
     * Be sure to keep track of the front left / right
     * and back left / right motors
     */

    lf_motor = new CANSparkMax(RobotMap.LEFT_FRONT_SPARK, MotorType.kBrushless);
    lb_motor = new CANSparkMax(RobotMap.LEFT_BACK_SPARK, MotorType.kBrushless);
    rf_motor = new CANSparkMax(RobotMap.RIGHT_FRONT_SPARK, MotorType.kBrushless);
    rb_motor = new CANSparkMax(RobotMap.RIGHT_BACK_SPARK, MotorType.kBrushless);

  }
  


  


  

  
  @Override
  public void initDefaultCommand() {
    /*
     * Sets our default command to do basic tank drive w/ trigger strafing as opposed to holonomic
     */

     setDefaultCommand(new DriveMotors());
  }

  public void drive()
  {
   
    /*
     * For us, we had issues with the controller
     * causing jittering due to inability to properly
     * center itself.
     * 
     * TrueLeftX and TrueRightX refer to us setting a deadzone
     * to stop jittering, while still giving us absolute position
     *
     * This sets our left front / back and right f/b motors to operate
     * under two stick tank drive using an Xbox controller
     */

    lf_motor.set(Robot.m_oi.TrueLeftX() * -1);
    lb_motor.set(Robot.m_oi.TrueLeftX() * - 1);
    rf_motor.set(Robot.m_oi.TrueRightX());
    rb_motor.set(Robot.m_oi.TrueRightX());
    
  
  }


public void stop()
{

   /**
   *   Handle stopping the drive, used to be a mock edition
   *   of brake / coast on the speed controllers
   */ 
  drive.tankDrive(0, 0);
}
  public void strafeLeft(double power){
    

    /*
     * Takes in double power from trigger
     */

    /*
     * brief explaination of how mecaunums work:
     * https://www.robotshop.com/media/files/pdf/mecanum-wheels-introduction-10009.pdf
     * note the model near the bottom of the page, specifically the left / right strafe
     * models.
     *
     *  
     *  What we're doing below is essentially following this model and powering
     * the proper wheels in the proper directions to cause lateral movement aka "Strafing"
     * 
     * Since we're doing T-Strafing, we have below the xbox controller trigger (left for this example) operating all
     * wheel movements, with proper negation to the value since triggers are always 0-1 (analog)
     */
    
    lf_motor.set(Robot.m_oi.controller.getRawAxis(2)*-1);
    lb_motor.set(Robot.m_oi.controller.getRawAxis(2) );
    rf_motor.set(Robot.m_oi.controller.getRawAxis(2)*-1);
    rb_motor.set(Robot.m_oi.controller.getRawAxis(2) );

  }
  
  public void strafeRight(double power){

     /*
     * Takes in double power from trigger
     */


    /*
     * Ditto to the above, but i'll copy the explaination below in case needed,
     * but be sure to notice it isnt the same layout of negation, because we want to do
     * the opposite of the above and move right
     *
     * brief explaination of how mecaunums work:
     * https://www.robotshop.com/media/files/pdf/mecanum-wheels-introduction-10009.pdf
     * note the model near the bottom of the page, specifically the left / right strafe
     * models.
     *
     *  
     *  What we're doing below is essentially following this model and powering
     * the proper wheels in the proper directions to cause lateral movement aka "Strafing"
     * 
     * Since we're doing T-Strafing, we have below the xbox controller trigger (right for this example) operating all
     * wheel movements, with proper negation to the value since triggers are always 0-1 (analog)    
     */

    lf_motor.set(Robot.m_oi.controller.getRawAxis(3) );
    lb_motor.set(Robot.m_oi.controller.getRawAxis(3) *-1);
    rf_motor.set(Robot.m_oi.controller.getRawAxis(3) );
    rb_motor.set(Robot.m_oi.controller.getRawAxis(3)*-1);
  
  }


}

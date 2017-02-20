/**
 * @author preetipidatala
 *
 */
package org.usfirst.frc.team2264.robot;

import java.awt.Color;

import org.usfirst.frc.team2264.robot.OI; 
import org.usfirst.frc.team2264.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import xyz.remexre.robotics.frc2016.controls.Controls;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String driveForward = "Drive Forward";
	final String gearAuto = "Gear Auto";
	String autoSelected;
	RobotDrive drive;
	OI oi;
	CANTalon left;
	CANTalon right;
	Shooter shooter;
	ControllerButtons buttons;
	BallPickup pickup;
	Winch winch;
	
	SendableChooser<String> chooser = new SendableChooser<>();
	UltrasonicSensor ultrasonicSensor;
	double speedAdjustment=.65;
	boolean onButton;
	boolean offButton;
	boolean lBumperPressed;
	boolean rBumperPressed;
	boolean winchTriggerPressed;
	boolean WinchLimit;
	double leftReading;
	double rightReading;
	long autonomousStartTime;
	long timeInAuto;
	auto auton;
	int slowRange=4;//inches
	boolean dangerRange;
	Teleop tele;
	boolean newShooterOn;
	boolean newShooterOff;
	boolean shooterBack;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		ultrasonicSensor = new UltrasonicSensor();
		chooser.addDefault("Drive Forward", driveForward);
		chooser.addObject("Gear Auto", gearAuto);
		SmartDashboard.putData("Auto choices", chooser);
		left= new CANTalon(RobotMap.leftDriveMotor);
		right= new CANTalon(RobotMap.rightDriveMotor);
		drive= new RobotDrive(left,right);
		oi= new OI();
		buttons = new ControllerButtons(RobotMap.GamepadButtons.gamepadPort);
		WinchLimit=false;
		pickup = new BallPickup();
		shooter = new Shooter();
		winch = new Winch();
		auton= new auto();
		CameraServer.getInstance().startAutomaticCapture();
		tele= new Teleop();

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		this.autonomousStartTime = System.currentTimeMillis();
		System.out.println("Auto selected: " + autoSelected);

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("distance", ultrasonicSensor.getAverageFeet());
		SmartDashboard.putNumber("TimeINAUTO", System.currentTimeMillis()-autonomousStartTime);
		dangerRange=(ultrasonicSensor.getAverageFeet()<=slowRange);
		switch (autoSelected) {
		case gearAuto:
			timeInAuto=System.currentTimeMillis()- autonomousStartTime;
			if(((timeInAuto>=1000)&&timeInAuto<=4590)){
				auton.gearAuto(left,right,dangerRange);
			}	
			else{
				left.set(0);
				right.set(0);
			}
			// Put custom auto code here
			break;
		case driveForward:
		default:
			timeInAuto=System.currentTimeMillis()- autonomousStartTime;
			if((timeInAuto>=1000)&&timeInAuto<=2500){
				auton.DriveForward(left, right);
			}
//			else if((timeInAuto>2500)&&(timeInAuto<=3000)){
//				auton.TurnRight(left, right);
//			}
			else{
				left.set(0);
				right.set(0);
			}
			// Put default auto code here
			//if (time i
			break;

		}
	}


	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		ReadButtons();
		tele.SmartDashboardOutputs(oi, pickup,ultrasonicSensor,chooser,lBumperPressed);
		DriveTrainMotor();
		BallPickupOnOff();
		//shooter.ShooterMotorOn(lBumperPressed);
		shooter.FeederMotorOn(rBumperPressed);
		winch.motorOn(winchTriggerPressed);
		if(newShooterOn){
		shooter.motorOn();
		}
		else if(newShooterOff){
		shooter.motorOff();
		}
		tele.EasyMoveBackward(left, right, oi.leftStick);
		tele.EasyMoveForward(left, right, oi.rightStick);
		if(lBumperPressed){
			pickup.MotorBack();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	
	}
	public void ReadButtons(){
		onButton=buttons.getAButton();
		offButton=buttons.getYButton();
		lBumperPressed=buttons.getBumper(Hand.kRight);
		rBumperPressed=buttons.getBumper(Hand.kLeft);
		winchTriggerPressed= ;
		newShooterOn=buttons.getXButton();
		newShooterOff=buttons.getBButton();
		leftReading = oi.getLeftJoystick();
		rightReading = oi.getRightJoystick();
		
	}
	public void BallPickupOnOff(){
		if (onButton){
			pickup.Motoron();	
		}
		else if(offButton){
			pickup.Motoroff();	
		}
	}
	public void DriveTrainMotor(){
		int rMotorAdjustment=-1;//because one of the motors is backwards
		left.set(rMotorAdjustment*speedAdjustment*JoystickSensetivities.sensitivityAdjustment(JoystickSensetivities.getLeft(leftReading, rightReading)));
		right.set(speedAdjustment*JoystickSensetivities.sensitivityAdjustment(JoystickSensetivities.getRight(leftReading, rightReading)));
	}
}


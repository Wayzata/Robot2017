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
import edu.wpi.first.wpilibj.command.Command;
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
	SendableChooser<String> chooser= new SendableChooser<>();
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
	int slowRange=3;//feet
	boolean dangerRange;
	Teleop tele;
	/**
	
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//chooser = new SendableChooser<String>();
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
		CameraServer.getInstance().startAutomaticCapture(1);
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
		ultrasonicSensor.resetUltraSonic();
		autoSelected =chooser.getSelected();
		this.autonomousStartTime = System.currentTimeMillis();
		SmartDashboard.putString("Selected", autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
			dangerRange=(ultrasonicSensor.getAverageFeet()<=slowRange);
		switch (autoSelected) {
		case gearAuto:
			timeInAuto=System.currentTimeMillis()- autonomousStartTime;
			if(((timeInAuto>=1000)&&timeInAuto<=2500)){
				auton.gearAuto(left,right,dangerRange);
			}	
		
			break;
		case driveForward:
		default:
			timeInAuto=System.currentTimeMillis()- autonomousStartTime;
			if((timeInAuto>=1000)&&timeInAuto<=2500){
				auton.DriveForward(left, right);
			}
			else if((timeInAuto>2500)&&(timeInAuto<=3000)){
				auton.TurnRight(left, right);
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
		shooter.ShooterMotorOn(lBumperPressed);
		shooter.FeederMotorOn(rBumperPressed);
		winch.motorOn(winchTriggerPressed);	
		getSafeDistance();
		tele.EasyMoveForward(left,right, oi.rightStick);
		tele.EasyMoveBackward(left,right, oi.leftStick);
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
		winchTriggerPressed= buttons.getXButton();
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

		left.set(speedAdjustment*JoystickSensetivities.sensitivityAdjustment(JoystickSensetivities.getLeft(leftReading, rightReading)));
		right.set(speedAdjustment*JoystickSensetivities.sensitivityAdjustment(JoystickSensetivities.getRight(leftReading, rightReading)));
	}
	public void getSafeDistance(){
		if (ultrasonicSensor.getAverageFeet()<=5){
			SmartDashboard.putString("Distance From Object", "<5 FEET");
	}
		else{
			SmartDashboard.putString("Distance From Object", "");
		}
}
}

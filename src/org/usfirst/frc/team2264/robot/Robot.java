/**
 * @author preetipidatala
 *
 */
package org.usfirst.frc.team2264.robot;

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
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
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
	double speedAdjustment=.65;
	boolean onButton;
	boolean offButton;
	boolean lBumperPressed;
	boolean rBumperPressed;
	boolean winchTriggerPressed;
	boolean WinchLimit;
	long autonomousStartTime;
	long timeInAuto;
	auto auton;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
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
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			timeInAuto=System.currentTimeMillis()- autonomousStartTime;
			if((timeInAuto>=3000)&&timeInAuto<=3500){
				auton.DriveForward(left, right);
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
		int debug=1;
	
		onButton=buttons.getAButton();
		offButton=buttons.getYButton();
		lBumperPressed=buttons.getBumper(Hand.kRight);
		rBumperPressed=buttons.getBumper(Hand.kLeft);
		winchTriggerPressed= buttons.getXButton();
		SmartDashboard.putNumber("JoystickR", RobotMap.rightStickPort);  
		SmartDashboard.putNumber("JoystickL", RobotMap.leftStickPort);
		SmartDashboard.putNumber("left speed", oi.leftStick.getY());
		SmartDashboard.putNumber("right speed", oi.rightStick.getY());
		SmartDashboard.putBoolean("motor off", pickup.motoroff);
	
		double leftReading = oi.getLeftJoystick();
		double rightReading = oi.getRightJoystick();
		left.set(speedAdjustment*JoystickSensetivities.sensitivityAdjustment(JoystickSensetivities.getLeft(leftReading, rightReading)));
		right.set(speedAdjustment*JoystickSensetivities.sensitivityAdjustment(JoystickSensetivities.getRight(leftReading, rightReading)));
		debug=2;
	
			
		
		if (onButton){
			debug=3;
			pickup.Motoron();	
		}
		else if(offButton){
			debug=4;
			pickup.Motoroff();	
		}
		shooter.ShooterMotorOn(lBumperPressed);
		shooter.FeederMotorOn(rBumperPressed);
		winch.motorOn(winchTriggerPressed);


		
System.out.println(debug);
	
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


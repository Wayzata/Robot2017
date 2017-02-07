package org.usfirst.frc.team2264.robot;

import org.usfirst.frc.team2264.robot.OI; 
import org.usfirst.frc.team2264.robot.RobotMap;
import org.usfirst.frc.team2264.robot.RobotMap.GamepadButtons;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 
import java.math.*;
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
	Gamepad gamepad;
	BallPickup pickup;
	SendableChooser<String> chooser = new SendableChooser<>();
	double speedAdjustment=.65;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		drive= new RobotDrive(RobotMap.leftDriveMotor,RobotMap.rightDriveMotor);
		oi= new OI();
		left= new CANTalon(RobotMap.leftDriveMotor);
		right= new CANTalon(RobotMap.rightDriveMotor);
		gamepad= new Gamepad(RobotMap.GamepadButtons.gamepadPort);
		pickup = new BallPickup();
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
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
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
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("JoystickR", RobotMap.rightStickPort);  
		SmartDashboard.putNumber("JoystickL", RobotMap.leftStickPort);
		SmartDashboard.putNumber("left speed", oi.leftStick.getY());
		SmartDashboard.putNumber("right speed", oi.rightStick.getY());
		//left.set(oi.getLeftJoystick()*speedAdjustment);
		//right.set(oi.getRightJoystick()*speedAdjustment);
<<<<<<< HEAD
		left.set(speedAdjustment*JoystickSensetivities.sensitivityAdjustment(oi.getLeftJoystick()));
		right.set(speedAdjustment*JoystickSensetivities.sensitivityAdjustment(oi.getRightJoystick()));
if (gamepad.getRawButton(RobotMap.GamepadButtons.AButtNumber)){
	pickup.TakeButtonPress();
}
=======
		double leftReading = oi.getLeftJoystick();
		double rightReading = oi.getRightJoystick();
		left.set(speedAdjustment*JoystickSensetivities.sensitivityAdjustment(JoystickSensetivities.getLeft(leftReading, rightReading)));
		right.set(speedAdjustment*JoystickSensetivities.sensitivityAdjustment(JoystickSensetivities.getRight(leftReading, rightReading)));

>>>>>>> origin/master
		//drive.tankDrive(oi.leftStick.getY(),oi.rightStick.getY());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}


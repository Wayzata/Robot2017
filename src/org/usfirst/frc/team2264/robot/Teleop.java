/**
 * @author preetipidatala
 *
 */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon; 

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop {
	double MotorBalance=.9765625;
	public void SmartDashboardOutputs(OI oi, BallPickup pickup, UltrasonicSensor ultrasonicSensor, SendableChooser<String> chooser,boolean pressed){
		SmartDashboard.putNumber("left speed", oi.leftStick.getY());
		SmartDashboard.putNumber("right speed", oi.rightStick.getY());
		SmartDashboard.putBoolean(" Ball pickup on", !pickup.motoroff);
		SmartDashboard.putNumber("Average Feet from object", ultrasonicSensor.getAverageFeet());
		SmartDashboard.putData("Auto choices", chooser);
		SmartDashboard.putBoolean("Shooter Motor On", pressed);
	
	}
public void EasyMoveForward(CANTalon left,CANTalon right, Joystick Rstick){
	//these easy move methods simplify the tank drive train by setting both motors to the same speed(essentially moving forward) when the trigger is pressed.
	if(Rstick.getTrigger()){
		left.set(-.3*MotorBalance);// this is set to .512 because the right motor is more powerful than the left motor. If both motors are perfectly balanced, we can left.set(right.getSpeed()); 
		right.set(.3);
	}
}
	public void EasyMoveBackward(CANTalon left,CANTalon right, Joystick Lstick){
		if(Lstick.getTrigger()){
			left.set(.3*MotorBalance);
			right.set(-.3);
		}
//		public void EasyMoveForward(CANTalon left,CANTalon right, Joystick Rstick){
//	//these easy move methods simplify the tank drive train by setting both motors to the same speed(essentially moving forward) when the trigger is pressed.
//	if(Rstick.getTrigger()){
//		left.set(MotorBalance*right.getSpeed());// this is set to .512 because the right motor is more powerful than the left motor. If both motors are perfectly balanced, we can left.set(right.getSpeed()); 
//	}
}
	}


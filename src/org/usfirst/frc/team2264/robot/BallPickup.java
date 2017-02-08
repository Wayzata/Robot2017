package org.usfirst.frc.team2264.robot;

import org.usfirst.frc.team2264.robot.Gamepad;
import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2264.robot.OI;
import org.usfirst.frc.team2264.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.XboxController;

public class BallPickup extends Button {
	CANTalon pickupMotor;
	Button toggle;
	boolean motoroff;
public BallPickup(){
	
	
pickupMotor= new CANTalon(RobotMap.collectorMotor);

motoroff=true;
//toggle=new XboxController. (RobotMap.GamepadButtons.AButtNumber);
}

public void start(){

	
	}
public void TakeButtonPress(){
		motoroff = (!motoroff);
	if(motoroff){
	pickupMotor.set(0);
	}
	else{
		pickupMotor.set(.5);
	}
}

public boolean get() {
	// TODO Auto-generated method stub
	return false;
}
}

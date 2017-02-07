package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.buttons.Button;

public class Shooter {
	
	CANTalon shooterMotor;
	CANTalon feederMotor;
	Button rTrigger;
	Button lTrigger;
	
	public void ShooterInit(){
		shooterMotor = new CANTalon(RobotMap.shooterMotor);
		feederMotor = new CANTalon(RobotMap.feederMotor);
		rTrigger = new Button(RobotMap.GamepadButtons.RTriggerNumber);
		lTrigger = new Button(RobotMap.GamepadButtons.LTriggerNumber);
	}
	
	public void MotorOn(){
		if (rTrigger){
			shooterMotor.set(.5);
		}
		else{
			shooterMotor.set(0);
		}
	}
	public void helperOn(){
		if (lTrigger){
			feederMotor.set(.5);
		}
		else{
			feederMotor.set(0);
		}
	}
}

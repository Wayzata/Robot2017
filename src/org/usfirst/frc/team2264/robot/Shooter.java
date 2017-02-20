/**
 * @author preetipidatala
 *
 */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;

public class Shooter {

	CANTalon shooterMotor;
	CANTalon feederMotor;

	ControllerButtons buttons;

	public Shooter(){


		shooterMotor = new CANTalon(RobotMap.shooterMotor);
		feederMotor = new CANTalon(RobotMap.feederMotor);
		shooterMotor.setPID(1, 0, 0);

	}
//
//	public void ShooterMotorOn(boolean pressed){
//		if (pressed){
//			shooterMotor.set(.2);
//
//		}
//		else{
//			shooterMotor.set(0);
//		}
//	}
	public void motorOn(){

		shooterMotor.set(.8);
}
public void motorOff(){

	shooterMotor.set(0);
}
	public void FeederMotorOn(boolean pressed){
		if (pressed){
			feederMotor.set(-.9);
		}
		else{
			feederMotor.set(0);
		}
	}
}

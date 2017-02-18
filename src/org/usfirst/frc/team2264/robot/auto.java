/**
 * @author preetipidatala
 *
 */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;

public class auto {
	//double motorPower=.006;
	double motorPower=.6;
	double slowMult=.5;
	double MotorBalance=.951;// bc the left motor is more powerful
	public void DriveForward(CANTalon left, CANTalon right){

		left.set(motorPower);
		right.set(-1*motorPower);
	}
	public void TurnRight(CANTalon left, CANTalon right){

		left.set(motorPower);
		right.set(motorPower);
	}
	public void gearAuto(CANTalon left, CANTalon right,boolean danger){
		if(!danger){
			left.set(MotorBalance*motorPower);
			right.set(-1*motorPower);
		}
		else if(danger){
			left.set(MotorBalance*slowMult*motorPower);
			right.set(-1*slowMult*motorPower);
		}
	}
	
}
/**
 * @author preetipidatala
 *
 */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;

public class auto {
	//double motorPower=.006;
	double motorPower=.2;
	double slowMult=.25;
	public void DriveForward(CANTalon left, CANTalon right){

		left.set(-1*motorPower);
		right.set(motorPower);
	}
	public void TurnRight(CANTalon left, CANTalon right){

		left.set(motorPower);
		right.set(motorPower);
	}
	public void gearAuto(CANTalon left, CANTalon right,boolean danger){
		if(!danger){
			left.set(-1*motorPower);
			right.set(motorPower);
		}
		else if(danger){
			left.set(-1*slowMult*motorPower);
			right.set(slowMult*motorPower);
		}
	}
	
}
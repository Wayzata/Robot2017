/**
 *author preetipidatala
 */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;

public class Winch {
	CANTalon winchMotor;
	public Winch(){
		winchMotor= new CANTalon(RobotMap.WinchMotor);
		winchMotor.setPID(1, 0, 0);
	}
//	public void motorOn(boolean pressed){
//		if (pressed){
//			winchMotor.set(1);
//		}
//		else{
//			winchMotor.set(0);
//		}
//	}
	public void motorOn(){

			winchMotor.set(1);
	}
	public void motorOff(){

		winchMotor.set(0);
}
	public void motorLimit(boolean limit){
		if (limit){
			winchMotor.disableControl();
		}
	}
}

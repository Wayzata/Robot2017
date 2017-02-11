/**
 *author preetipidatala
 */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;

public class Winch {
CANTalon winchMotor;
public Winch(){
	winchMotor= new CANTalon(RobotMap.WinchMotor);
}
public void motorOn(boolean pressed){
	if (pressed){
		winchMotor.set(.5);
	}
	else{
		winchMotor.set(0);
	}
}
public void motorLimit(boolean limit){
	if (limit){
		winchMotor.disableControl();
	}
}
}

package org.usfirst.frc.team2264.robot;
	import com.ctre.CANTalon;

	public class REALWinch {
	CANTalon winchMotor;
	public REALWinch(){
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
}

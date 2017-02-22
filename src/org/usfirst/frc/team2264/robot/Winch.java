/**
 *author preetipidatala
 */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;

public class Winch {//class for the NEW SHOOTER
	CANTalon winchMotor;
	double agitatorSpeed;
	public Winch(){
		winchMotor= new CANTalon(RobotMap.WinchMotor);

		winchMotor.setPID(1, 0, 0);
		agitatorSpeed=1;
	}
//	public void motorOn(boolean pressed){
//		if (pressed){
//			winchMotor.set(.75);
//		}
//		else{
//			winchMotor.set(0);
//		}
//	}
//	public void motorOnTurn(boolean left){
//		long time=System.currentTimeMillis();
//		if(left){
//winchMotor.set(.8);
//		}
//		else{
//			motorOnTurnR();
//		}
//	while((time<System.currentTimeMillis())&&(System.currentTimeMillis()<time+50)){
//		motorOff();
//	}
//	}
	public void motorOnTurnR(){
		winchMotor.set(-1*agitatorSpeed);
			}
	public void motorOnTurnF(){
		winchMotor.set(agitatorSpeed);
	}
	public void motorOff(){

		winchMotor.set(0);
}
//	public void motorLimit(boolean limit){
//		if (limit){
//			winchMotor.disableControl();
//		}
//	}
}

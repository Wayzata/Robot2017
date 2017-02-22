/**
 *author preetipidatala
 */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;

public class Agitator {//class for the NEW SHOOTER
	CANTalon agitatorMotor;
	double agitatorSpeed;
	public Agitator(){
		agitatorMotor= new CANTalon(RobotMap.AgitatorMotor);

		agitatorMotor.setPID(1, 0, 0);
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
		agitatorMotor.set(-1*agitatorSpeed);
			}
	public void motorOnTurnF(){
		agitatorMotor.set(agitatorSpeed);
	}
	public void motorOff(){

		agitatorMotor.set(0);
}
//	public void motorLimit(boolean limit){
//		if (limit){
//			winchMotor.disableControl();
//		}
//	}
}

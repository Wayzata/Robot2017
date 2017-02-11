/**
 * @author preetipidatala
 *
 */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;

public class auto {
	//double motorPower=.006;
	double motorPower=.1;
public void DriveForward(CANTalon left, CANTalon right){
	
	left.set(motorPower);
	right.set(motorPower);
}
}
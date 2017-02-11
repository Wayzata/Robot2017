package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicSensor {
	
	Ultrasonic uSensor;
	
	public UltrasonicSensor(){
		
		uSensor = new Ultrasonic(1, 1);
	}
	/*
	 * If the uSensor sees the distance is a measurable distance, it will continue the IF statement to get the actual range
	 * Otherwise, the uSensor will do nothing.
	 */
	
	public void Enable(){
		if (uSensor.isRangeValid()){
			uSensor.getRangeInches();
		}
		else{
		}
	}
}




package org.usfirst.frc.team2264.robot;

public class JoystickSensetivities {
	public static double sensitivityAdjustment(double position){
		double adjustment =Math.pow(position, 2);
		if(position>=0){
			return adjustment;
		}
		else{
			return -1*adjustment;
		}


	}

}

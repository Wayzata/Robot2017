/**
 * @author DrakeJohnson
 * @author LexieMcfann
 *
 */
package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickSensetivities {
	
	//makes acceleration parabolic
	public static double sensitivityAdjustment(double position){
		double adjustment =Math.pow(position, 2);
		if(position>=0){
			return adjustment;
		}
		else{
			return -1*adjustment;
		}
	}

	//makes the joysticks same sensitivity if there is a hand bias
	public static double getLeft(double leftJoystick, double rightJoystick){
		// (leftJoystick - rightJoystick) = double joystickDifference
		// (joystickDifference < .05) = boolean withinRange
		// joystickDifference || withinRange = ???
		SmartDashboard.putNumber("Left input", leftJoystick);
		SmartDashboard.putNumber("Right input", rightJoystick);
		if (Math.abs(rightJoystick - leftJoystick) <= .10) {
			SmartDashboard.putNumber("Left output", rightJoystick);
			return rightJoystick;
		}
		else{
			SmartDashboard.putNumber("Left output", leftJoystick);
			return leftJoystick;
		}
	}
	public static double getRight(double leftJoystick, double rightJoystick){
		SmartDashboard.putNumber("Right output", rightJoystick);
		return rightJoystick;
	}
}

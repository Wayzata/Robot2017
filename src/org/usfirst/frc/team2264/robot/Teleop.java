/**
 * @author preetipidatala
 *
 */
package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop {
	public void SmartDashboardOutputs(OI oi, BallPickup pickup){
		SmartDashboard.putNumber("JoystickR", RobotMap.rightStickPort);  
		SmartDashboard.putNumber("JoystickL", RobotMap.leftStickPort);
		SmartDashboard.putNumber("left speed", oi.leftStick.getY());
		SmartDashboard.putNumber("right speed", oi.rightStick.getY());
		SmartDashboard.putBoolean("motor off", pickup.motoroff);
	}

}

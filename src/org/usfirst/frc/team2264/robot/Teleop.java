/**
 * @author preetipidatala
 *
 */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop {
	public void SmartDashboardOutputs(OI oi, BallPickup pickup, UltrasonicSensor ultrasonicSensor, SendableChooser<String> chooser,boolean pressed){
		SmartDashboard.putNumber("left speed", oi.leftStick.getY());
		SmartDashboard.putNumber("right speed", oi.rightStick.getY());
		SmartDashboard.putBoolean(" Ball pickup on", !pickup.motoroff);
		SmartDashboard.putNumber("Average Feet from object", ultrasonicSensor.getAverageFeet());
		SmartDashboard.putData("Auto choices", chooser);
		SmartDashboard.putBoolean("Shooter Motor On", pressed);
	}
public void EasyMoveForward(CANTalon left,CANTalon right, Joystick Rstick){
	if(Rstick.getTrigger()){
		left.set(-.512);
		right.set(-.5);
	}
}
	public void EasyMoveBackward(CANTalon left,CANTalon right, Joystick Lstick){
		if(Lstick.getTrigger()){
			left.set(.512);
			right.set(.5);
		}
}

}

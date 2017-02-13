/**
 * @author preetipidatala
 *
 */
package org.usfirst.frc.team2264.robot;

//import org.usfirst.frc.team2264.robot.Gamepad; 
import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2264.robot.OI;
import org.usfirst.frc.team2264.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.XboxController;

public class BallPickup extends Button {
	CANTalon pickupMotor;
	Button toggle;
	boolean motoroff;
	int PickupMotorSpeed=1;
	public BallPickup(){


		pickupMotor= new CANTalon(RobotMap.collectorMotor);

		motoroff=true;
		//toggle=new XboxController. (RobotMap.GamepadButtons.AButtNumber);
	}

	public void start(){


	}
	public void Motoroff(){

		pickupMotor.set(0);
	}

	public void Motoron(){

		pickupMotor.set(PickupMotorSpeed);

	}


	public boolean get() {
		// TODO Auto-generated method stub
		return false;
	}
}

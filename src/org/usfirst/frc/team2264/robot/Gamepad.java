package org.usfirst.frc.team2264.robot;
import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;


public class Gamepad extends GamepadBase {
	public Gamepad(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getRawAxis(int axis) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getBumper(Hand hand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getStickButton(Hand hand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getRawButton(int button) {	
		
		return false;
	}

	@Override
	public int getPOV(int pov) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPOVCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HIDType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOutput(int outputNumber, boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOutputs(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRumble(RumbleType type, double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getX(Hand hand) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY(Hand hand) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}

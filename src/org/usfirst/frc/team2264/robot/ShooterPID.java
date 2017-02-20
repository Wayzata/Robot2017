package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class ShooterPID extends PIDSubsystem {

	public ShooterPID(double p, double i, double d) {
		super(p, i, d);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}

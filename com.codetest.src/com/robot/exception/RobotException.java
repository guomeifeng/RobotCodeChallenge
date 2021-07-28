package com.robot.exception;

public abstract class RobotException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RobotException(String message) {
		super(message);
	}

}

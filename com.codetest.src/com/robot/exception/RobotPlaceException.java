package com.robot.exception;

public class RobotPlaceException extends RobotException{

	private static final long serialVersionUID = 1L;

	public RobotPlaceException(String message) {
		super("Cannot place!" + message);
	}

}

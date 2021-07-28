package com.robot.exception;

public class RobotMoveException extends RobotException{

	private static final long serialVersionUID = 1L;

	public RobotMoveException(String message) {
		super("Cannot Move!" + message);
	}

}

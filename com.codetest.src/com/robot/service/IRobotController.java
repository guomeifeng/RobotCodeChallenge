package com.robot.service;

import com.robot.exception.RobotMoveException;
import com.robot.exception.RobotPlaceException;

public interface IRobotController {

	public boolean placeRobot(String placeStr) throws RobotPlaceException;

	public boolean moveOrTurnRobot(String moveStr) throws RobotMoveException;

	public String reportRobot();
}

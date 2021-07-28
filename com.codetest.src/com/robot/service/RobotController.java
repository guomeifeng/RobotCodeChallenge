package com.robot.service;

import java.util.logging.Logger;

import com.robot.entity.IRobot;
import com.robot.entity.ISquareTable;
import com.robot.enums.ActionType;
import com.robot.enums.FaceToType;
import com.robot.exception.RobotException;
import com.robot.exception.RobotMoveException;
import com.robot.exception.RobotPlaceException;
import com.robot.util.StringUtil;

public class RobotController implements IRobotController {
	/**
	 * the robot which will be controlled
	 */
	private IRobot robot;
	/**
	 * the table that robot is placed on
	 */
	private ISquareTable squareTable;
	/**
	 * when user move or turn the robot incorrectly, will raise exception including this message
	 */
	private String USER_ENTER_ERROR = "User Enter Error.";

	private static Logger logger = Logger.getLogger(RobotController.class.getName());

	public RobotController(IRobot robot, ISquareTable table) {
		this.robot = robot;
		this.squareTable = table;
	}
	/**
	 * place the robot on the table.
	 * @param placeStr (i.e. PLACE 1,2,LEFT) ignore upper or lower cases.
	 * @throws RobotPlaceException if the position to be placed is illegal
	 */
	@Override
	public boolean placeRobot(String placeStr) throws RobotPlaceException {
		try {
			ActionType action = ActionType.valueOf(placeStr.substring(0, 5).toUpperCase());
			String[] strArray = placeStr.substring(6).split(",");
			int indexX = Integer.valueOf(strArray[0]);
			int indexY = Integer.valueOf(strArray[1]);
			FaceToType faceTo = FaceToType.valueOf(strArray[2].toUpperCase());
			if (ActionType.PLACE.equals(action) && validate(indexX, indexY, ActionType.PLACE)) {
				robot.place(indexX, indexY, faceTo);
				return true;
			} else {
				throw new RobotPlaceException(USER_ENTER_ERROR);
			}
		} catch (Exception ex) {
			throw new RobotPlaceException(USER_ENTER_ERROR);
		}
	}
	/**
	 * move the robot to next step or turn the robot to different direction.
	 * @param moveStr (i.e. MOVE or LEFT) ignore upper or lower cases.
	 * @throws RobotMoveException if the position to be moved is illegal
	 */
	@Override
	public boolean moveOrTurnRobot(String moveStr) throws RobotMoveException {
		try {
			ActionType action = ActionType.valueOf(moveStr.toUpperCase());
			switch (action) {
			case MOVE: {
				int[] nextMove = this.robot.getNextMove();
				if (validate(nextMove[0], nextMove[1], ActionType.MOVE)) {
					robot.place(nextMove[0],nextMove[1],this.robot.getFaceTo());
					return true;
				} else {
					throw new RobotMoveException(USER_ENTER_ERROR);
				}
			}
			case LEFT: {
				robot.left();
				return true;
			}
			case RIGHT: {
				robot.right();
				return true;
			}
			case REPORT: {
				this.reportRobot();
				return true;
			}
			default:
				throw new RobotMoveException(USER_ENTER_ERROR);
			}
		} catch (IllegalArgumentException ex) {
			throw new RobotMoveException(USER_ENTER_ERROR);
		}
	}
	/**
	 * report the position of the robot
	 */
	@Override
	public String reportRobot() {
		System.out.println("OUTPUT: " + this.robot.toString());
		logger.info("OUTPUT: " + this.robot.toString());
		return "OUTPUT: " + this.robot.toString();
	}
	/**
	 * validate if the place of the robot to be moved / placed illegal
	 * @param indexX
	 * @param indexY
	 * @param type
	 * @return
	 * @throws RobotException
	 */
	private boolean validate(int indexX, int indexY, ActionType type) throws RobotException {
		String errorMsg = null;
		if (indexX >= this.squareTable.getLength()) {
			errorMsg = " X = " + indexX + " which is greater than or equal to " + this.squareTable.getLength();
		}
		if (indexY >= this.squareTable.getWidth()) {
			errorMsg = " Y = " + indexY + " which is greater than or equal to " + this.squareTable.getWidth();
		}

		if (indexX < 0) {
			errorMsg = " X = " + indexX + " which is less than 0.";
		}
		if (indexY < 0) {
			errorMsg = " Y = " + indexY + " which is less than 0.";
		}
		if (StringUtil.isBlank(errorMsg))
			return true;
		if (ActionType.PLACE.equals(type)) {
			throw new RobotPlaceException(errorMsg);
		}
		if (ActionType.MOVE.equals(type)) {
			throw new RobotMoveException(errorMsg);
		}
		return false;
	}
}

package com.robot.entity;

import com.robot.enums.FaceToType;
import com.robot.exception.RobotException;
/**
 * This class is for creating robot objects, 
 * place it in some position with specific direction, 
 * and move it to next position following the same direction
 * move the 
 * @author kelly
 *
 */
public class Robot implements IRobot{
	/**
	 * position X of the robot
	 */
	private int indexX = -1;
	/**
	 * position Y of the robot
	 */
	private int indexY = -1;
	/**
	 * the orientation (EAST, WEST, NORTH, SOUTH) of the robot face to 
	 */
	private FaceToType faceTo;
	/**
	 * set new FaceTo for the robot when he turns right
	 */
	public void right() {
		switch (this.faceTo) {
		case EAST:{
			this.faceTo = FaceToType.SOUTH;
			break;
		}
		case WEST:{
			this.faceTo = FaceToType.NORTH;
			break;
		}
		case SOUTH:{
			this.faceTo = FaceToType.WEST;
			break;
		}
		case NORTH:{
			this.faceTo = FaceToType.EAST;
			break;
		}
		
		}
	}
	/**
	 * place a robot in right position and face to right direction
	 * @param indexX
	 * @param indexY
	 * @param faceto
	 */
	public void place(int indexX, int indexY, FaceToType faceTo) throws RobotException {
		this.indexX = indexX;
		this.indexY = indexY;
		this.faceTo = faceTo;
	}
	/**
	 * get the X, Y of the position when the robot Move next in different direction
	 * @return int[0] will be X, int[1] will be Y
	 */
	public int[] getNextMove() {
		int[] nextPosition = new int[2];
		
		switch (this.faceTo) {
		case EAST:{
			nextPosition[0] = this.indexX+1;
			nextPosition[1] = this.indexY;
			
			break;
		}
		case WEST:{
			nextPosition[0] = this.indexX-1;
			nextPosition[1] = this.indexY;
			break;
		}
		case SOUTH:{
			nextPosition[0] = this.indexX;
			nextPosition[1] = this.indexY-1;
			break;
		}
		case NORTH:{
			nextPosition[0] = this.indexX;
			nextPosition[1] = this.indexY+1;
			break;
		}
		
		}
		return nextPosition;
	}
	/**
	 * set new FaceTo for the robot when he turns left
	 */
	public void left() {
		switch (this.faceTo) {
		case EAST:{
			this.faceTo = FaceToType.NORTH;
			break;
		}
		case WEST:{
			this.faceTo = FaceToType.SOUTH;
			break;
		}
		case SOUTH:{
			this.faceTo = FaceToType.EAST;
			break;
		}
		case NORTH:{
			this.faceTo = FaceToType.WEST;
			break;
		}
		
		}
	}
	
	@Override
	public String toString() {
		return this.indexX + ", " + this.indexY + ", " + this.faceTo;
	}

	@Override
	public FaceToType getFaceTo() {
		return this.faceTo;
	}

}

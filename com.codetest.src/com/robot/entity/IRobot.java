package com.robot.entity;

import com.robot.enums.FaceToType;

public interface IRobot {

	public void place(int indexX, int indexY, FaceToType faceTo);

	public void left();

	public void right();

	public int[] getNextMove();

	public FaceToType getFaceTo();

}

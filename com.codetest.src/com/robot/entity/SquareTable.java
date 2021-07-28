package com.robot.entity;
/**
 * This class is for creating a table with specific length and width
 * @author kelly
 *
 */
public class SquareTable implements ISquareTable {
	/**
	 * the length of the table
	 */
	private int length;
	/**
	 * the width of the table
	 */
	private int width;
	public SquareTable(int length, int width) {
		this.length = length;
		this.width = width;
	}
	@Override
	public int getLength() {
		return this.length;
	}
	@Override
	public int getWidth() {
		return this.width;
	}
	
	
}

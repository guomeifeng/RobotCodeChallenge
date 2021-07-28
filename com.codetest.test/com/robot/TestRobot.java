package com.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.robot.entity.IRobot;
import com.robot.entity.ISquareTable;
import com.robot.entity.Robot;
import com.robot.entity.SquareTable;
import com.robot.exception.RobotException;
import com.robot.exception.RobotMoveException;
import com.robot.exception.RobotPlaceException;
import com.robot.service.IRobotController;
import com.robot.service.RobotController;

public class TestRobot {
	@Test
	public void testRobotMoveNormal() {
	
		ISquareTable tabel = new SquareTable(5, 5);
		
		IRobot robot = new Robot();
		
		IRobotController controller = new RobotController(robot, tabel);
		try {
			controller.placeRobot("PLACE 1,2,EAST");
			controller.moveOrTurnRobot("MOVE");
			controller.moveOrTurnRobot("MOVE");
			controller.moveOrTurnRobot("LEFT");
			controller.moveOrTurnRobot("MOVE");
		} catch (RobotException e) {
			e.printStackTrace();
		}
		assertEquals("OUTPUT: 3, 3, NORTH", controller.reportRobot());
		
	}
	
	@Test
	public void testRobotMoveOverMaximumException() {
		
		ISquareTable tabel = new SquareTable(5, 5);
		
		IRobot robot = new Robot();
		
		IRobotController controller = new RobotController(robot, tabel);

		try {
			controller.placeRobot("PLACE 1,2,EAST");
			controller.moveOrTurnRobot("MOVE");
			controller.moveOrTurnRobot("MOVE");
			controller.moveOrTurnRobot("MOVE");
			controller.moveOrTurnRobot("MOVE");
			fail( "My method didn't throw when I expected it to" );
		} catch (RobotMoveException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRobotMoveToMinusException() {
		
		ISquareTable tabel = new SquareTable(5, 5);
		
		IRobot robot = new Robot();
		
		IRobotController controller = new RobotController(robot, tabel);

		try {
			controller.placeRobot("PLACE 1,2,WEST");
			controller.moveOrTurnRobot("MOVE");
			controller.moveOrTurnRobot("MOVE");
			fail( "My method didn't throw when I expected it to" );
		} catch (RobotMoveException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRobotPlaceException() {
		
		ISquareTable tabel = new SquareTable(5, 5);
		
		IRobot robot = new Robot();
		
		IRobotController controller = new RobotController(robot, tabel);

		try {
			controller.placeRobot("PLACE 6,2,EAST");
			fail( "My method didn't throw when I expected it to" );
		} catch (RobotPlaceException e) {
			e.printStackTrace();
		}
	}
}

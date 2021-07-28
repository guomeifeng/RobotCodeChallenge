package com.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.robot.entity.IRobot;
import com.robot.entity.ISquareTable;
import com.robot.entity.Robot;
import com.robot.entity.SquareTable;
import com.robot.exception.RobotMoveException;
import com.robot.exception.RobotPlaceException;
import com.robot.service.IRobotController;
import com.robot.service.RobotController;
import com.robot.util.LogHandler;

public class Application {
	private static Logger logger = Logger.getLogger(Application.class.getName());
	private static String PLACE_MESSAGE = "Place a Robot on the 5x5 area (PLACE X, Y, F) # to Exit:";
	private static String MOVE_MESSAGE = "Enter an Robot Action (MOVE, LEFT, RIGHT, REPORT) # to Exit: ";

	public static void main(String[] args) {

		try {
			LogHandler logHandler = new LogHandler();
			Logger.getLogger("a").getParent().addHandler(logHandler);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			ISquareTable table = new SquareTable(5, 5);
			IRobot robot = new Robot();
			
			IRobotController controller = new RobotController(robot, table);
			boolean isPlaced = false;

			do {
				String placeStr = controlFromConsole(reader, PLACE_MESSAGE);
				try {
					isPlaced = controller.placeRobot(placeStr);
					logger.info(placeStr);
				} catch (RobotPlaceException ex) {
					isPlaced = false;
					System.out.println(ex.getMessage());
					logger.info(ex.getMessage());
				}

			} while (!isPlaced);

			String moveStr = "";
			do {
				try {
					moveStr = controlFromConsole(reader, MOVE_MESSAGE);
					controller.moveOrTurnRobot(moveStr);
					logger.info(moveStr);
				} catch (RobotMoveException ex) {
					System.out.println(ex.getMessage());
					logger.info(ex.getMessage());
				}
			} while (!moveStr.equals("#"));
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String controlFromConsole(BufferedReader reader, String message) throws IOException {

		System.out.println(message);

		String actionStr = reader.readLine();
		if ("#".equals(actionStr)) {
			System.exit(0);
		}

		return actionStr;
	}

}

package com.saurabhraje.robot;

import org.junit.Test;

import com.saurabhraje.model.Position;

import junit.framework.TestCase;

public class ToyRobotTest extends TestCase {
	public ToyRobot robot;

	@Override
	protected void setUp() throws Exception {
		robot = new ToyRobot(5, 5);
	}

	@Test(expected = Test.None.class)
	public void testValidPlaceCommand() throws Exception {
		robot.processCommand("PLACE 2,3,NORTH");
		assertEquals(robot.getPosition().getX(), 2);
		assertEquals(robot.getPosition().getY(), 3);
	}

	@Test
	public void testInvalidPlaceCommand() {
		try {
			robot.processCommand("PLACE 5,5,WEST");
		} catch (Exception ex) {
			assertNotNull(ex);
			assertEquals(ex.getMessage(), "Invalid command");
		}
	}

	@Test(expected = Test.None.class)
	public void testValidMoveCommand() throws Exception {
		Position position = new Position(2, 3, 0);
		robot.firePlaceCommand(position);
		robot.processCommand("MOVE");
		assertEquals(robot.getPosition().getY(), 4);
	}

	@Test(expected = Test.None.class)
	public void testInvalidMoveCommand() throws Exception {
		// No exception should be thrown for an invalid move;
		Position position = new Position(2, 4, 0);
		robot.firePlaceCommand(position);
		robot.processCommand("MOVE");
	}

	@Test(expected = Test.None.class)
	public void testLeftCommand() throws Exception {
		Position position = new Position(2, 3, 0);
		robot.firePlaceCommand(position);
		robot.processCommand("LEFT");
		assertEquals(robot.getPosition().getFace(), 3);
	}

	@Test(expected = Test.None.class)
	public void testRightCommand() throws Exception {
		Position position = new Position(2, 3, 0);
		robot.firePlaceCommand(position);
		robot.processCommand("RIGHT");
		assertEquals(robot.getPosition().getFace(), 1);
	}
}

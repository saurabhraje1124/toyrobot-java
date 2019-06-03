package com.saurabhraje.robot;

import java.util.Arrays;

import com.saurabhraje.model.Position;

public class ToyRobot {
	private enum Command {
		PLACE, MOVE, LEFT, RIGHT, REPORT
	};

	private String[] directions = new String[] { "NORTH", "EAST", "SOUTH", "WEST" };

	private Position currentPosition = new Position();
	private boolean placeCommandFired = false;

	private int boardLength, boardWidth;

	public ToyRobot(int boardLength, int boardWidth) {
		this.boardLength = boardLength;
		this.boardWidth = boardWidth;
	}

	public void processCommand(String commandLine) throws Exception {
		try {
			System.out.println("Executing command: " + commandLine);
			String[] commandArr = commandLine.split("\\s+");
			if (commandArr.length <= 2) {
				if (null == Command.valueOf(commandArr[0])) {
					throw new Exception("No command found");
				} else {
					if (!placeCommandFired) {
						if (Command.valueOf(commandArr[0]) == Command.PLACE) {
							this.place(commandArr);
						}
					} else {
						switch (Command.valueOf(commandArr[0])) {
						case PLACE:
							this.place(commandArr);
							break;
						case REPORT:
							this.report();
							break;
						case MOVE:
							try {
								this.move(commandArr);
							} catch (Exception ex) {
								if (ex.getMessage().equals("Invalid MOVE")) {
									System.out.println(ex.getMessage());
								} else {
									throw ex;
								}
							}
							break;
						case LEFT:
						case RIGHT:
							this.turn(commandArr[0]);
							break;
						default:
							throw new Exception("Command " + commandLine + " is invalid");
						}
					}
				}
			} else {
				throw new Exception("Invalid command syntax");
			}
		} catch (Exception ex) {
			throw new Exception("Invalid command");
		}
	}

	private void report() {
		System.out.println("Current position X: " + this.currentPosition.getX() + " Y: " + this.currentPosition.getY()
				+ " Direction: " + directions[this.currentPosition.getFace()]);
	}

	private void place(String[] commandArr) throws Exception {
		if (commandArr.length == 2) {
			String[] positionCmd = commandArr[1].split(",");
			if (Integer.parseInt(positionCmd[0]) < this.boardLength
					&& Integer.parseInt(positionCmd[1]) < this.boardWidth
					&& Arrays.asList(directions).indexOf(positionCmd[2]) > -1) {
				placeCommandFired = true;
				this.currentPosition.set(Integer.parseInt(positionCmd[0]), Integer.parseInt(positionCmd[1]),
						Arrays.asList(directions).indexOf(positionCmd[2]));
			} else {
				throw new Exception("PLACE coordinates out of bounds.");
			}
		} else {
			throw new Exception("Invalid PLACE command syntax");
		}
	}

	private void move(String[] commandArr) throws Exception {
		if (commandArr.length == 1) {
			if (this.currentPosition.getX() > -1 && this.currentPosition.getY() > -1
					&& this.currentPosition.getFace() > -1) {
				switch (directions[this.currentPosition.getFace()]) {
				case "NORTH":
					if ((this.currentPosition.getY() + 1) == this.boardWidth) {
						throw new Exception("Invalid MOVE");
					} else {
						this.currentPosition.setY((this.currentPosition.getY() + 1));
					}
					break;
				case "EAST":
					if ((this.currentPosition.getX() + 1) == this.boardLength) {
						throw new Exception("Invalid MOVE");
					} else {
						this.currentPosition.setX((this.currentPosition.getX() + 1));
					}
					break;
				case "SOUTH":
					if (this.currentPosition.getY() == 0) {
						throw new Exception("Invalid MOVE");
					} else {
						this.currentPosition.setY((this.currentPosition.getY() - 1));
					}
					break;
				case "WEST":
					if (this.currentPosition.getX() == 0) {
						throw new Exception("Invalid MOVE");
					} else {
						this.currentPosition.setX((this.currentPosition.getX() - 1));
					}
					break;
				}
			} else {
				throw new Exception("Robot is at an invalid position.");
			}
		} else {
			throw new Exception("Invalid MOVE command syntax");
		}
	}

	public void turn(String direction) throws Exception {
		if (null != direction) {
			if (this.currentPosition.getX() > -1 && this.currentPosition.getY() > -1
					&& this.currentPosition.getFace() > -1) {
				switch (direction) {
				case "LEFT":
					if (this.currentPosition.getFace() == 0) {
						this.currentPosition.setFace((directions.length - 1));
					} else {
						this.currentPosition.setFace((this.currentPosition.getFace() - 1));
					}
					break;
				case "RIGHT":
					if (this.currentPosition.getFace() == (directions.length - 1)) {
						this.currentPosition.setFace(0);
					} else {
						this.currentPosition.setFace((this.currentPosition.getFace() + 1));
					}
					break;
				}
			} else {
				throw new Exception("Robot is at an invalid position.");
			}
		}
	}

	public Position getPosition() {
		return this.currentPosition;
	}

	public void setPosition(Position position) {
		this.currentPosition = position;
	}

	public boolean isPlaceCommandFired() {
		return this.placeCommandFired;
	}

	public void firePlaceCommand(Position position) {
		this.placeCommandFired = true;
		this.currentPosition = position;
	}

}

package com.saurabhraje.main;

import java.io.File;
import java.util.Scanner;

import com.saurabhraje.robot.ToyRobot;
import com.saurabhraje.utils.CommandLineArgs;

public class Main {

	final static int BOARD_LENGTH = 5;
	final static int BOARD_WIDTH = 5;

	public static void main(String[] args) throws Exception {
		ToyRobot robot = new ToyRobot(BOARD_LENGTH, BOARD_WIDTH);
		CommandLineArgs cmd = new CommandLineArgs(args);
		String commandFilePath = null;
		if (args.length > 0) {
			if (cmd.hasOption("-f")) {
				commandFilePath = cmd.valueOf("-f");
				if (null != commandFilePath) {
					try {
						File commandFile = new File(commandFilePath);
						Scanner scanner = new Scanner(commandFile);
						while (scanner.hasNextLine()) {
							robot.processCommand(scanner.nextLine());
						}
						scanner.close();
					} catch (Exception ex) {
						throw new Exception("Something went wrong in parsing the file. Please check the command file.");
					}
				} else {
					throw new Exception("commandFilePath not found");
				}
			} else {
				throw new Exception("-f option missing. Could not find command file.");
			}
		} else {
			throw new Exception("No command line arguments found. Could not find command file.");
		}
	}
}

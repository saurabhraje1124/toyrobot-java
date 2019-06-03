package com.saurabhraje.utils;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class CommandLineArgs {
	protected ArrayList arguments;
	
	public CommandLineArgs (String[] args) {
		parse(args);
	}
	
	public void parse(String[] args) {
		arguments = new ArrayList();
		for (int i = 0; i < args.length; i++) {
			arguments.add(args[i]);
		}
	}
	
	public boolean hasOption (String option) {
		boolean hasValue = false;
		for (int i = 0; i < arguments.size(); i++) {
			String str = (String) arguments.get(i);
			if (true == str.equalsIgnoreCase(option)) {
				hasValue = true;
				break;
			}
		}
		return hasValue;
	}
	
	public String valueOf (String option) {
		String value = null;
		for (int i = 0; i < arguments.size(); i++) {
			String str = (String) arguments.get(i);
			if ( true == str.equalsIgnoreCase(option) ) {
                value = (String)arguments.get(i+1);
                break;
            }
		}
		return value;
	}
}

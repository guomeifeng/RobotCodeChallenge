package com.robot.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;


public class LogHandler extends FileHandler {

	public LogHandler() throws SecurityException, IOException {
		super(getLogName(), true);
		this.setFormatter(new SimpleFormatter());
	}
    
    private static String getLogName() throws IOException {  
        String basePath = new File("").getAbsolutePath();
        return basePath +  "\\robot.log";
    }  
}
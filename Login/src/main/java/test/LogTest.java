package test;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogTest {

	public static void main(String[] args) throws SecurityException, IOException {
		// TODO Auto-generated method stub
		System.out.println("logtest");
		Logger aa = Logger.getLogger("log1");
		aa.setLevel(Level.INFO);
		
		//System.out.println(aa.getLevel().toString());
		Logger.getGlobal().info("日志测试");
		ConsoleHandler consoleHandler =new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        aa.addHandler(consoleHandler);
        FileHandler fileHandler = new FileHandler("C:/Users/admin/Desktop/testlog.log");
        fileHandler.setLevel(Level.INFO);
        aa.addHandler(fileHandler);
        aa.warning("warning");
		aa.info("info");
		aa.fine("fine");
	}

}

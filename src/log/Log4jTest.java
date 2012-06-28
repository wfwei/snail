package log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jTest {
	//public static Logger logger = Logger.getLogger(Log4jTest.class);//eaque to : Logger.getRootLogger();
	public static Logger logger = Logger.getLogger("log111.Log4jTest");
	public void log() {
		logger.debug("Debug info.");
		logger.info("Info info");
		logger.warn("Warn info");
		logger.error("Error info");
		logger.fatal("Fatal info");
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("src/log4j.properties");
		new Log4jTest().log();
		//new Log4jTest2().log();
	}
}

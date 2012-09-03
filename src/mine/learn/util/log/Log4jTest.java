package mine.learn.util.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jTest {
	public static Logger logger = Logger.getLogger(Log4jTest.class);// Logger.getLogger("Log4jTest1");//
																	// Logger.getRootLogger();

	public void log() {
		logger.debug("Debug info.");
		logger.info("Info info");
		logger.warn("Warn info");
		logger.error("Error info");
		logger.fatal("Fatal info");
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("resources/log4j.properties");
		new Log4jTest().log();
	}
}

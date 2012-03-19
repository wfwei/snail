package log;

import org.apache.log4j.Logger;

public class Log4jTest {
	static Logger log= Logger.getLogger(Log4jTest.class);
    public void log(){
       log.debug("Debug info.");
       log.info("Info info");
       log.warn("Warn info");
       log.error("Error info");
       log.fatal("Fatal info");
    }
    public static void main(String[] args) {
    	Log4jTest test = new Log4jTest();
       
    	test.log();
    }
}

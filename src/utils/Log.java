package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

	static{
		String date = Actions.getDate();
		System.setProperty("myconfig.accout", date+".log");
	}
	private static Logger log = LogManager.getLogger(Log.class);
	
//	public static Logger log;
//	public static void init() throws Exception, IOException{
//		ConfigurationSource source= new ConfigurationSource(new FileInputStream("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\miniblog\\document\\log4j2.xml"));
//		Configurator.initialize(null,source);
//		log = LogManager.getLogger(Log.class);
//	}

	public static void info(String data) {
		log.info(data);
	}

	public static void error(String data) {
		log.error(data);
	}

	public static void debug(String data) {
		log.debug(data);
	}

	public static void warn(String data) {
		log.warn(data);
	}

	public static void testStart() {
		log.info("----------Test Start----------");
	}

	public static void testEnd() {
		log.info("----------Test End----------\n");
	}

}

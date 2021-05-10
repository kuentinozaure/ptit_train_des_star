package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {

	private static LoggerUtil instance;
	
	private final String LogUri = "./src/main/java/logs/logs.txt";
	FileHandler fh;
	Logger logger;

	public LoggerUtil() throws SecurityException, IOException {
		logger = Logger.getLogger("Logs");
		fh = new FileHandler(LogUri);
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}
	
	public static LoggerUtil getInstance() throws SecurityException, IOException {
        if (instance == null) {
            instance = new LoggerUtil();
        }
        return instance;
    }

	public void WriteInfoLogInFile(String log) {
		logger.info(log);
	}

	public void WriteErrorLogInFile(String log) {
		logger.info(log);
	}
}
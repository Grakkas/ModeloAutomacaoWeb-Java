package util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class LoggerConfig {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void setupLogger() {
        try {
            LogManager.getLogManager().reset();
            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.CONFIG);
            ch.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(ch);

            FileHandler fh = new FileHandler("WebAutomationModelLog_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyy")) + ".log", true);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.CONFIG);
            LOGGER.addHandler(fh);
        } catch (IOException ignore) {
        }
    }
}

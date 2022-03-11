package mvh.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Class to handle logging information out to a log file as program operates
 * @author Jonathan Hudson
 * @version 1.0
 */
public final class Logger {

    /**
     * The PrintWriter to record log entries
     */
    private PrintWriter printWriter;

    /**
     * Constructs a logger (PrintWriter)
     * Prints error message to System Error and exits if log file cannot be found
     *
     * @param fileLog The log file
     */
    public Logger(File fileLog) {
        try {
            printWriter = new PrintWriter(fileLog);
        } catch (FileNotFoundException e) {
            System.err.printf("Unable to open log file %s!%n", fileLog.getAbsoluteFile());
            System.exit(1);
        }
    }

    /**
     * Stores the given Object into the log file (flushes immediately after)
     * The object is converted to a string when added to the file
     *
     * @param obj The object to store in the log file (note, a new line is added on the end)
     */
    public void log(Object obj) {
        printWriter.println(obj);
        printWriter.flush();
    }

}

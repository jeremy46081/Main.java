package mvh;

import mvh.util.Logger;

import java.io.File;
import java.util.Scanner;

/**
 * Simple menu so user can enter commands to continue or stop simulation
 * @author Jonathan Hudson
 * @version 1.0
 */
public final class Menu {

    /**
     * The scanner to access System.in input commands
     */
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * A Logger for logging program execution to an output file
     */
    private static Logger logger;

    /**
     * Should this program ignore future user input?
     */
    private static boolean ignore = false;

    /**
     * Set up the logger with a filename
     * @param fileLog The file to log to
     */
    public static void setup(File fileLog) {
        logger = new Logger(fileLog);

    }

    /**
     * Print the following object to the screen
     *
     * @param obj The object to print to screen (note, a new line is added on the end)
     */
    public static void println(Object obj) {
        if(logger == null){
            throw new RuntimeException("Cannot log until log file has been setup(File)!");
        }
        System.out.println(obj);
        logger.log(obj);
    }

    /**
     * Should the simulation (E)xit, (C)omplete, or anything else go step by step
     * (Note, if 'C' is selected the answer will always be true for rest of simulation
     *
     * @return 'E' is false, otherwise true
     */
    public static boolean continueSimulation() {
        if (!ignore) {
            System.out.println("(E)xit / (C)omplete/: Anything else to step once");
            String line = scanner.nextLine();
            if (line.equals("E")) {
                return false;
            } else if (line.equals("C")) {
                Menu.ignore = true;
            }
        }
        return true;
    }

    /**
     * Get a (Y)es/No answer
     * @return True if 'Y', otherwise False
     */
    public static boolean checkYes() {
        System.out.println("(Y)es: Anything else is No");
        return scanner.nextLine().equals("Y");
    }
}

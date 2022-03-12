package mvh;

import mvh.util.Reader;
import mvh.world.World;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Monsters vs Heroes
 * A simple 2D grid simulation. Load a defined world file and step by step resolve movement in grid to determine if
 * the heroes win, or the monster win.
 * @author Jonathan Hudson
 * @version 1.0
 */
public final class Main {


    /**
     * A program-wide random number generator
     */
    public static Random random;

    /**
     * Check arguments, read world, setup logger, and start simulation
     *
     * @param args Program arguments, Usage: Main &lt;world&gt; &lt;log&gt; &lt;seed&gt;
     */
    public static void main(String[] args) {
        //Check for 3 arguments
        checkArgument(args);

        //Get the two filenames
        File fileWorld = new File(args[0]);
        File fileLog = new File(args[1]);

        //Create the random number generator
        setupRNG(args[2]);

        //Check if files are accessible
        checkFiles(fileWorld, fileLog);

        //Set up Menu with logger and read world from file
        Menu.setup(fileLog);
        Menu.println("Arguments: "+ Arrays.toString(args));
        World world = Reader.loadWorld(fileWorld);

        //Run simulation
        runSimulation(world);
    }

    /**
     * Verify that program has the 3 command line arguments necessary
     *
     * @param args The program arguments
     */
    private static void checkArgument(String[] args) {
        if (args.length != 3) {
            System.err.println("Program requires 2 arguments!");
            System.err.println("Usage: Main <world> <log> <seed>");
            System.exit(1);
        }
    }

    /**
     * Setup random number generator
     *
     * @param seed The integer seed for the RNG
     */
    private static void setupRNG(String seed) {
        try {
            random = new Random(Integer.parseInt(seed));
        } catch (Exception e) {
            System.err.printf("Third argument %s should be integer seed!%n", seed);
            System.exit(1);
        }
    }

    /**
     * Verify if files exists and can be accessed properly
     * Create log file if it doesn't exist and require confirmation to overwrite past log files
     *
     * @param fileWorld The world file storing the game setup
     * @param fileLog   The log file for program output
     */
    private static void checkFiles(File fileWorld, File fileLog) {
        //Check world file
        if (!fileWorld.exists() || !fileWorld.isFile() || !fileWorld.canRead()) {
            System.err.printf("The world file %s does not exist!%n", fileWorld.getAbsoluteFile());
            System.exit(1);
        }
        //Check log file overwrite?
        if (fileLog.exists() && fileLog.isFile() && fileLog.canWrite()) {
            System.out.println("Overwrite log file?");
            if (Menu.checkYes()) {
                fileLog.delete();
            } else {
                System.err.println("Ending program instead of overwriting log file!");
                System.exit(0);
            }
        }
        //Create log file if the log file doesn't exist
        if (!fileLog.exists()) {
            try {
                fileLog.createNewFile();
            } catch (IOException e) {
                System.err.printf("Cannot create new log file %s%n!", fileLog.getAbsoluteFile());
                System.exit(0);
            }
        }
        //Check log file
        if (!fileLog.exists() || !fileLog.isFile() || !fileLog.canWrite()) {
            System.err.printf("The log file %s cannot be accessed!%n", fileLog.getAbsoluteFile());
            System.exit(1);
        }
        //We should trust both of our input files at this point
    }

    /**
     * Run the Monsters versus Heroes simulation
     *
     * @param world The loaded world state to simulate
     */
    private static void runSimulation(World world) {
        while (world.isActive()) {
            String message = world.gameString();
            Menu.println(message);
            if (Menu.continueSimulation()) {
                world.advanceSimulation();
            } else {
                world.endSimulation();
            }
        }
        String message = world.gameString();
        Menu.println(message);
    }
}

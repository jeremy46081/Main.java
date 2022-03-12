package mvh.enums;

import mvh.Main;

/**
 * Enumeration of directions in 2D grid
 * Helper class contains index adjustments if movement is wanted in that direction
 * @author Jonathan Hudson
 * @version 1.0
 */
public enum Direction {
    NORTHWEST(-1, -1), NORTH(-1, 0), NORTHEAST(-1, 1),
    WEST(0, -1), STAY(0, 0), EAST(0, 1),
    SOUTHWEST(1, -1), SOUTH(1, 0), SOUTHEAST(1, 1);
    /**
     * Row adjustment to move that direction
     */
    private final int rowChange;
    /**
     * Column adjustment to move that direction
     */
    private final int columnChange;

    /**
     * Each direction has a rowChange/col adjustment
     *
     * @param rowChange    The change of row that would indicate going this direction
     * @param columnChange The change of column that would indicate going this direction
     */
    Direction(int rowChange, int columnChange) {
        this.rowChange = rowChange;
        this.columnChange = columnChange;
    }

    /**
     * Access row adjustment required to move that direction
     *
     * @return The row adjustment to move that direction
     */
    public int getRowChange() {
        return rowChange;
    }

    /**
     * Access column adjustment required to move that direction
     *
     * @return The column adjustment to move that direction
     */
    public int getColumnChange() {
        return columnChange;
    }

    /**
     * Get a random direction from the set of 9 available options
     *
     * @return A random Direction from the available 9 directions
     */
    public static Direction getRandomDirection() {
        return Direction.values()[Main.random.nextInt(Direction.values().length)];
    }

    /**
     * Get direction that matches an i,j adjustment to current location
     *
     * @param rowChange    The rowChange change
     * @param columnChange The column change
     * @return A direction that matches the requested rowChange/col change
     */
    public static Direction getDirection(int rowChange, int columnChange) {
        if (rowChange < 0 && columnChange < 0) {
            return Direction.NORTHWEST;
        } else if (rowChange < 0 && columnChange == 0) {
            return Direction.NORTH;
        } else if (rowChange < 0) {
            return Direction.NORTHEAST;
        } else if (rowChange == 0 && columnChange < 0) {
            return Direction.WEST;
        } else if (rowChange == 0 && columnChange == 0) {
            return Direction.STAY;
        } else if (rowChange == 0) {
            return Direction.EAST;
        } else if (columnChange < 0) {
            return Direction.SOUTHWEST;
        } else if (columnChange == 0) {
            return Direction.SOUTH;
        } else {
            return Direction.SOUTHEAST;
        }
    }
    /**
     * Get list of directions that matches an i,j adjustment to current location
     *
     * @param rowChange    The rowChange change
     * @param columnChange The column change
     * @return A direction array that matches the requested rowChange/col change
     */
    public static Direction[] getDirections(int rowChange, int columnChange) {
        if (rowChange < 0 && columnChange < 0) {
            return new Direction[]{Direction.NORTHWEST,Direction.NORTH, Direction.WEST};
        } else if (rowChange < 0 && columnChange == 0) {
            return new Direction[]{Direction.NORTH,Direction.NORTHWEST, Direction.NORTHEAST};
        } else if (rowChange < 0) {
            return new Direction[]{Direction.NORTHEAST,Direction.NORTH, Direction.EAST};
        } else if (rowChange == 0 && columnChange < 0) {
            return new Direction[]{Direction.WEST,Direction.NORTHWEST, Direction.SOUTHWEST};
        } else if (rowChange == 0 && columnChange == 0) {
            return new Direction[]{Direction.STAY};
        } else if (rowChange == 0) {
            return new Direction[]{Direction.EAST,Direction.NORTHEAST, Direction.SOUTHEAST};
        } else if (columnChange < 0) {
            return new Direction[]{Direction.SOUTHWEST,Direction.SOUTH, Direction.WEST};
        } else if (columnChange == 0) {
            return new Direction[]{Direction.SOUTH,Direction.SOUTHWEST, Direction.SOUTHEAST};
        } else {
            return new Direction[]{Direction.SOUTHEAST,Direction.SOUTH, Direction.EAST};
        }
    }
}

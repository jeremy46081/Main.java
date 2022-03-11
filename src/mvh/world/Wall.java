package mvh.world;

import mvh.enums.Direction;
import mvh.enums.Symbol;

/**
 * A singleton class for Walls (As we only ever need one)
 * Currently only used to indicate parts of world that Monster/Heroes can not move to in the local view of the World
 * given to entities when they are deciding to attack or to move
 * @author Jonathan Hudson
 * @version 1.0
 */
public final class Wall extends Entity {
    /**
     * We only need one wall, so we'll re-use this one (a singleton) over and over
     */
    private static final Wall wall = new Wall();

    /**
     * So we hide the constructor
     */
    private Wall() {
        //A wall is always a # and has no health as it is dead
        //This wall will also have ID=0
        super(Symbol.WALL.getSymbol(), 0);
    }

    /**
     * If we want a wall we'll use the get Wall to re-use the same wall over and over
     *
     * @return The one single Wall that will ever exist can be accessed this way
     */
    public static Wall getWall() {
        return wall;
    }

    /**
     * Has no strength as it can't attack
     *
     * @return 0
     */
    @Override
    public int weaponStrength() {
        return 0;
    }

    /**
     * Has no defense as it can't defend
     *
     * @return 0
     */
    @Override
    public int armorStrength() {
        return 0;
    }

    /**
     * Will never move
     *
     * @param local The local view of the entity
     * @return null
     */
    @Override
    public Direction chooseMove(World local) {
        return null;
    }

    /**
     * Will never attack
     *
     * @param local The local view of the entity (immediate neighbors 3x3)
     * @return null
     */
    @Override
    public Direction attackWhere(World local) {
        return null;
    }

    /**
     * Can't be moved on top of
     *
     * @return false
     */
    @Override
    public boolean canMoveOnTopOf() {
        return false;
    }

    /**
     * Can't be attacked
     *
     * @return false
     */
    @Override
    public boolean canBeAttacked() {
        return false;
    }
}

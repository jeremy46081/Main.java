package mvh.world;

import mvh.enums.Direction;

/**
 * A World is made up of entities (this class itself does not exist, but extensions of it (child classes) will).
 * @author Jonathan Hudson
 * @version 1.0
 */
public abstract class Entity {

    /**
     * Entities are either ALIVE or DEAD
     * Some like Walls are always DEAD
     * Some like Monster/Heroes start ALIVE but may end up DEAD
     */
    private enum State {
        ALIVE, DEAD
    }

    /**
     * Entities have a state
     */
    private State state;
    /**
     * Entities all have a symbol for printing a map
     */
    private final char symbol;
    /**
     * Entities have health and at <=0 they will go from ALIVE to DEAD
     */
    private int health;
    /**
     * ID to help tell each entity apart so shared symbols don't become confusing
     */
    private final int id;

    /**
     * Allows us to generate unique IDs for each entity crated.
     */
    private static int counter = 1;
    /**
     * Walls will have ID 0
     */
    protected static final int WALL_ID = 0;

    /**
     * Create entity with given symbol and health (since class is abstract, only children can be made)
     *
     * @param symbol The symbol of entity
     * @param health The health of the entity
     */
    protected Entity(char symbol, int health) {
        this.symbol = symbol;
        if (health < 0) {
            throw new IllegalArgumentException("Health for entity must be > 0. Value given was " + health + "!");
        }
        this.health = health;
        this.state = State.ALIVE;
        //If health was too low (<=0) then update this entity to dead
        checkDead();
        //If we are a wall we have a set ID, otherwise we generate new ID
        if (this instanceof Wall) {
            this.id = WALL_ID;
        } else {
            this.id = counter;
            counter++;
        }
    }

    /**
     * Get symbol
     *
     * @return The symbol for map
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Get entities health
     *
     * @return The health of entity, should be >= 0 (0 is DEAD)
     */
    public int getHealth() {
        return health;
    }

    /**
     * Is entity ALIVE?
     *
     * @return True if state is alive (health should be > 0)
     */
    public boolean isAlive() {
        return state == State.ALIVE;
    }

    /**
     * Is entity DEAD?
     *
     * @return True if state is dead (health should be == 0)
     */
    public boolean isDead() {
        return !isAlive();
    }

    /**
     * Damage the health of entity for the given positive (or 0) amount
     * We will update state of ALIVE/DEAD if health falls to <= 0
     *
     * @param damage The damage to subtract from the entity
     */
    public void damage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage must be reported as positive (or zero) value (which is subtracted)!");
        }
        this.health = Math.max(0, this.health - damage);
        checkDead();
    }

    /**
     * Internal function to check if entity has moved from alive to dead
     */
    private void checkDead() {
        if (this.health <= 0) {
            this.state = State.DEAD;
        }
    }

    /**
     * Returns the weapon strength of the entity based on subtype
     *
     * @return Weapon strength of entity
     */
    public abstract int weaponStrength();

    /**
     * Returns the armor strength of the entity based on subtype
     *
     * @return Armor strength of entity
     */
    public abstract int armorStrength();

    /**
     * Where does this entity want to move based on local view of world (5x5)
     *
     * @param local The local view of the entity
     * @return The Direction the entity wants to move (presumption that entity already decided to not attack)
     */
    public abstract Direction chooseMove(World local);

    /**
     * Where does this entity want to attack based on local view of world (immediate neighbors 3x3)
     *
     * @param local The local view of the entity (immediate neighbors 3x3)
     * @return The Direction the entity wants to attack (null if not attacking)
     */
    public abstract Direction attackWhere(World local);

    /**
     * Can this entity be moved on top of
     *
     * @return Boolean if true
     */
    public abstract boolean canMoveOnTopOf();

    /**
     * Can this entity be attacked
     *
     * @return Boolean if true
     */
    public abstract boolean canBeAttacked();

    /**
     * String version of entity that contains nothing but the CLASS and ID
     *
     * @return "CLASS(ID)" form of entity
     */
    public String shortString() {
        return getClass().getSimpleName().substring(0, 4) + "(" + id + ")";
    }

    /**
     * String version of entity that contains CLASS(ID) SYMBOL HEALTH STATE
     *
     * @return "CLASS(ID) SYMBOL HEALTH STATE" form of entity
     */
    @Override
    public String toString() {
        return shortString() + "\t" + symbol + "\t" + health + "\t" + state;
    }

    /**
     * Reset the ID counter (for testing purposes only really)
     */
    public static void resetIDCounter() {
        counter = 1;
    }
}

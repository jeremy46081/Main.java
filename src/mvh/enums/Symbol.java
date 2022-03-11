package mvh.enums;

/**
 * Enumeration for common symbols to be used in game
 * @author Jonathan Hudson
 * @version 1.0
 */
public enum Symbol {
    FLOOR('.'), DEAD('$'), WALL('#');
    /**
     * The symbol used for the map
     */
    private final char symbol;

    /**
     * Constructor enum to store the symbol
     * @param symbol The symbol to be stored
     */
    Symbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * The symbol for the game map
     * @return Symbol for the game map
     */
    public char getSymbol() {
        return symbol;
    }
}

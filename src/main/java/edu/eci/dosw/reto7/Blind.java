package edu.eci.dosw.reto7;

/**
 * Represents a window blind
 * Position 0 means completely closed and position 100 means completely open
 */
public class Blind {
    private int position;

    /**
     * Changes the blind position
     * @param position new position from 0 to 100
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Returns the current blind position
     * @return current position
     */
    public int getPosition() {
        return position;
    }
}
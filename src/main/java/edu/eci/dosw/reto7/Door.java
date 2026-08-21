package edu.eci.dosw.reto7;

/**
 * Represents a door controlled by the remote.
 */
public class Door {

    private boolean open;

    /**
     * Opens the door.
     */
    public void open() {
        open = true;
    }

    /**
     * Closes the door.
     */
    public void close() {
        open = false;
    }

    /**
     * Indicates whether the door is open.
     *
     * @return true if the door is open
     */
    public boolean isOpen() {
        return open;
    }
}
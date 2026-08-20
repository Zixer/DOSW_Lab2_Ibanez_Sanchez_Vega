package edu.eci.dosw.reto7;

/**
 * Defines the operations that every command
 * in the remote control system must support.
 */
public interface Command {

    /**
     * Executes the command.
     */
    void execute();

    /**
     * Undoes the command and restores
     * the previous device state.
     */
    void undo();

    /**
     * Returns a description of the action.
     *
     * @return command description
     */
    String getDescription();

    /**
     * Returns the name of the affected device.
     *
     * @return device name
     */
    String getDeviceName();

    /**
     * Returns the user who executed the action.
     *
     * @return user name
     */
    String getUser();

    /**
     * Indicates whether the command was undone.
     *
     * @return true if the command was undone
     */
    boolean isUndone();
}
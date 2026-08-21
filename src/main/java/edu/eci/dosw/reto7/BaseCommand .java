package edu.eci.dosw.reto7;

/**
 * Base implementation for commands.
 *
 * Stores the user who executed the command
 * and whether the command has been undone.
 */
public abstract class BaseCommand implements Command{

    protected final String user;
    protected boolean undone;

    /**
     * Creates a command associated with a user.
     *
     * @param user user who executes the action
     */
    protected BaseCommand(String user) {
        this.user = user;
        this.undone = false;
    }

    /**
     * Returns the user who executed the command.
     *
     * @return user name
     */
    @Override
    public String getUser() {
        return user;
    }

    /**
     * Indicates whether the command was undone.
     *
     * @return true if undone
     */
    @Override
    public boolean isUndone() {
        return undone;
    }
}
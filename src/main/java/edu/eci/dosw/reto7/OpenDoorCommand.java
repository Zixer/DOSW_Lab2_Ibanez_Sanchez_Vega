package reto7;

/**
 * Command that opens a door
 */
public class OpenDoorCommand extends BaseCommand {
    private final Door door;
    private final String deviceName;
    private boolean previousState;

    /**
     * Creates a command for opening a door
     *
     * @param door door to control
     * @param deviceName name used for audit information
     * @param user user who executes the action
     */
    public OpenDoorCommand(Door door, String deviceName, String user) {
        super(user);
        this.door = door;
        this.deviceName = deviceName;
    }

    /**
     * Saves the previous door state and opens the door
     */
    @Override
    public void execute() {
        previousState = door.isOpen();
        door.open();
        undone = false;
    }

    /**
     * Restores the previous door state
     */
    @Override
    public void undo() {
        if (previousState) {
            door.open();
        } else {
            door.close();
        }
        undone = true;
    }

    /**
     * Returns the action description
     * @return command description
     */
    @Override
    public String getDescription() {
        return "Open door";
    }

    /**
     * Returns the affected device name
     * @return device name
     */
    @Override
    public String getDeviceName() {
        return deviceName;
    }
}
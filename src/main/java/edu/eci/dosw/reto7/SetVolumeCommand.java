package edu.eci.dosw.reto7;

/**
 * Command that changes the volume
 * of a music system.
 */
public class SetVolumeCommand extends BaseCommand {

    private final MusicSystem musicSystem;
    private final String deviceName;
    private final int newVolume;

    private int previousVolume;

    /**
     * Creates a command for changing the volume.
     *
     * @param musicSystem music system to control
     * @param deviceName name used for audit information
     * @param newVolume desired volume
     * @param user user who executes the action
     */
    public SetVolumeCommand(MusicSystem musicSystem, String deviceName, int newVolume, String user) {

        super(user);

        this.musicSystem = musicSystem;
        this.deviceName = deviceName;
        this.newVolume = newVolume;
    }

    /**
     * Saves the previous volume and applies
     * the new volume.
     */
    @Override
    public void execute() {

        previousVolume = musicSystem.getVolume();

        musicSystem.setVolume(newVolume);

        undone = false;
    }

    /**
     * Restores the volume that existed
     * before the command was executed.
     */
    @Override
    public void undo() {

        musicSystem.setVolume(previousVolume);

        undone = true;
    }

    /**
     * Returns the action description,
     * including the selected volume.
     *
     * @return command description
     */
    @Override
    public String getDescription() {

        return "Set volume to "+ newVolume;
    }

    /**
     * Returns the affected device name.
     *
     * @return device name
     */
    @Override
    public String getDeviceName() {
        return deviceName;
    }
}
package reto7;

/**
 * Command that turns a light on
 */
public class LightOnCommand extends BaseCommand {

    private final Light light;
    private final String deviceName;
    private boolean previousState;

    /**
     * Creates a command for turning on a light
     * @param light light to control
     * @param deviceName name used for audit information
     * @param user user who executes the action
     */
    public LightOnCommand(Light light,String deviceName,String user) {
        super(user);
        this.light = light;
        this.deviceName = deviceName;
    }

    /**
     * Saves the previous light state and turns the light on
     */
    @Override
    public void execute() {
        previousState = light.isOn();
        light.turnOn();
        undone = false;
    }

    /**
     * Restores the state that the light had before the command was executed
     */
    @Override
    public void undo() {
        if (previousState) {
            light.turnOn();
        } else {
            light.turnOff();
        }
        undone = true;
    }

    /**
     * Returns the action description
     * @return command description
     */
    @Override
    public String getDescription() {
        return "Turn light ON";
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
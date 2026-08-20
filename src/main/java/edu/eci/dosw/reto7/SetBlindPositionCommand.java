package edu.eci.dosw.reto7;

/*
Command that changes the position of a window blind
*/
public class SetBlindPositionCommand extends BaseCommand {

    private final Blind blind;
    private final String deviceName;
    private final int newPosition;
    private int previousPosition;

/* 
Creates a command for changing a blind position
@param blind blind to control
@param deviceName name used for audit information
@param newPosition desired position
@param user user who executes the action
*/
public SetBlindPositionCommand(Blind blind, String deviceName, int newPosition, String user) {

        super(user);
        this.blind = blind;
        this.deviceName = deviceName;
        this.newPosition = newPosition;
    }

/*
Saves the previous position and applies the new blind position.
*/
@Override
public void execute() {

        previousPosition =blind.getPosition();
        blind.setPosition(newPosition);
        undone = false;
    }

/*
Restores the previous blind position.
*/
@Override
public void undo() {
        blind.setPosition(previousPosition);
        undone = true;
    }

/* 
Returns the action description.
@return command description
*/
@Override
public String getDescription() {
        return "Set blind position to " + newPosition + "%";
    }

/*
Returns the affected device name.*
@return device name
*/
@Override
public String getDeviceName() {return deviceName;}
}
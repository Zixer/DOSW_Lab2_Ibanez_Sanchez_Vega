package edu.eci.dosw.reto7;

import java.util.ArrayList;
import java.util.List;

/*
Represents the remote control that executes commands and stores their complete history
Acts as the Invoker in the Command pattern
*/
public class RemoteControl {
    private final List<Command> history =new ArrayList<>();

/*
Executes a command and stores it in the action history
@param command command to execute
*/
public void executeCommand(
     Command command) {

        command.execute();
        history.add(command);

        System.out.println("Action executed: "+ command.getDescription());
        System.out.println("User: "+ command.getUser());
    }

/*
Undoes an individual command from the action history
@param index position of the command in the history
*/
public void undoCommand(int index) {

        if (index < 0|| index >= history.size()) {
            System.out.println("Invalid action number.");
            return;
        }
        Command command = history.get(index);
        if (command.isUndone()) {
            System.out.println("This action was already undone.");
            return;
        }

        command.undo();
        System.out.println("Action undone: "+ command.getDescription());
    }

/*
Returns the complete execution history
@return command history
*/
public List<Command> getHistory() {return history;}
}

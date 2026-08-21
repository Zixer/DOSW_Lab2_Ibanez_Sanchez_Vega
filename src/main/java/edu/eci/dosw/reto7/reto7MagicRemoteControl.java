package edu.eci.dosw.reto7;

import java.util.List;
import java.util.Scanner;

/*
Challenge 7 - Magic Remote Control.
Demonstrates the Command pattern by representing every device action as an independent command.
*/

public final class reto7MagicRemoteControl {

    private reto7MagicRemoteControl() {
    }

    /*
    Executes Challenge 7.
    Allows users to execute multiple actions, undo individual actions, view the complete history and inspect final device states.
    */
    public static void run() {

        Scanner scanner = new Scanner(System.in);
        RemoteControl remote = new RemoteControl();
        Light livingRoomLight = new Light();
        Door mainDoor = new Door();
        MusicSystem musicSystem = new MusicSystem();
        Blind livingRoomBlind = new Blind();

        boolean running = true;

        System.out.println("\n================================");
        System.out.println("THE MAGIC REMOTE CONTROL");
        System.out.println("================================");

        while (running) {
            showMenu();
            int option =scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> executeLightCommand(scanner, remote, livingRoomLight);
                case 2 -> executeDoorCommand(scanner, remote, mainDoor);
                case 3 -> executeVolumeCommand(scanner, remote, musicSystem);
                case 4 -> executeBlindCommand(scanner, remote, livingRoomBlind);
                case 5 -> undoAction(scanner, remote);
                case 6 ->showHistory(remote);
                case 0 -> running = false;

                default -> System.out.println("Invalid option.");
            }
        }

        showFinalSummary(remote, livingRoomLight, mainDoor, musicSystem, livingRoomBlind);
    }

    /* 
    Displays the main menu.
    */
    private static void showMenu() {

        System.out.println("\nSelect an option:");
        System.out.println("1. Turn light ON");
        System.out.println("2. Open door");
        System.out.println("3. Set music volume");
        System.out.println("4. Set blind position");
        System.out.println("5. Undo an action");
        System.out.println("6. View history");
        System.out.println("0. Finish");
    }

    /*
    Creates and executes a command for turning on the light.
    @param scanner scanner used for input
    @param remote remote control
    @param light light to control
    */
    private static void executeLightCommand(Scanner scanner, RemoteControl remote, Light light) {

        String user = readUser(scanner);
        Command command = new LightOnCommand(light, "Living Room Light", user);
        remote.executeCommand(command);
    }

    /*
    Creates and executes a command for opening the door.
    @param scanner scanner used for input
    @param remote remote control 
    @param door door to control
    */
    private static void executeDoorCommand(Scanner scanner, RemoteControl remote, Door door) {

        String user = readUser(scanner);
        Command command = new OpenDoorCommand(door, "Main Door", user);
        remote.executeCommand(command);
    }

    /*
    Creates and executes a volume command.
    @param scanner scanner used for input
    @param remote remote control
    @param musicSystem music system to control
    */
    private static void executeVolumeCommand(Scanner scanner, RemoteControl remote, MusicSystem musicSystem) {

        String user =readUser(scanner);
        System.out.print("Enter volume (0-100): ");

        int volume = scanner.nextInt();
        scanner.nextLine();

        Command command = new SetVolumeCommand(musicSystem, "Music System", volume, user);
        remote.executeCommand(command);
    }

    /*
    Creates and executes a command that changes the blind position.
    @param scanner scanner used for input
    @param remote remote control
    @param blind blind to control
    */
    private static void executeBlindCommand(Scanner scanner, RemoteControl remote, Blind blind) {

        String user = readUser(scanner);
        System.out.print("Enter blind position (0-100): ");

        int position = scanner.nextInt();
        scanner.nextLine();

        Command command = new SetBlindPositionCommand(blind, "Living Room Blind", position, user);
        remote.executeCommand(command);
    }

    /*
    Reads the name of the user who executes an action.
    @param scanner scanner used for input
    @return user name
    */
    private static String readUser(Scanner scanner) {
        System.out.print("Enter user name: ");
        return scanner.nextLine();
    }

    /*
    Allows the user to choose an individual command from the history and undo it. 
    @param scanner scanner used for input
    @param remote remote control
    */
    private static void undoAction(Scanner scanner, RemoteControl remote) {
        List<Command> history = remote.getHistory();

        if (history.isEmpty()) {
            System.out.println("There are no actions to undo.");
            return;
        }

        System.out.println("\nSelect the action to undo:");

        for (int i = 0;i < history.size(); i++) {
            Command command = history.get(i);

            System.out.println((i + 1) + ". " + command.getDescription() 
            + " - Device: " + command.getDeviceName()
            + " - User: " + command.getUser()
            + " - Status: " + (command.isUndone() ? "UNDONE": "ACTIVE")
            );
        }

        int action =scanner.nextInt();
        scanner.nextLine();
        remote.undoCommand(action - 1);
    }

    /*
    Displays the complete action history.
    @param remote remote control containing the history
    */
    private static void showHistory(RemoteControl remote) {
        List<Command> history = remote.getHistory();

        System.out.println("\n================================");
        System.out.println("ACTION HISTORY");
        System.out.println("================================");

        if (history.isEmpty()) {
            System.out.println("No actions have been executed.");
            return;
        }

        for (int i = 0;i < history.size(); i++) {
            Command command =history.get(i);

            System.out.println((i + 1)+ ". "+ command.getDescription());
            System.out.println("Device: "+ command.getDeviceName());
            System.out.println("User: " + command.getUser());
            System.out.println("Status: " + (command.isUndone() ? "UNDONE" : "ACTIVE"));
            System.out.println();
        }
    }

    /*
    Displays the complete command history and the final state of every device.
    @param remote remote control
    @param light light device
    @param door door device
    @param musicSystem music system
    @param blind blind device
    */
    private static void showFinalSummary(RemoteControl remote, Light light, Door door, MusicSystem musicSystem, Blind blind) {

        System.out.println("\n================================");
        System.out.println("FINAL SUMMARY");
        System.out.println("================================");

        showHistory(remote);

        System.out.println("FINAL DEVICE STATES");
        System.out.println("--------------------------------");
        System.out.println("Living Room Light: " + (light.isOn() ? "ON" : "OFF"));
        System.out.println("Main Door: " + (door.isOpen() ? "OPEN" : "CLOSED"));
        System.out.println("Music Volume: " + musicSystem.getVolume());
        System.out.println("Living Room Blind: " + blind.getPosition()+ "%");
    }
}
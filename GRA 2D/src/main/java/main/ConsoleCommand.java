package main;


public class ConsoleCommand {
    private final String command;

    public ConsoleCommand(String command) {
        this.command = command;
        if (command.equals("restart")){
            restartMap();
        }
    }


    void restartMap(){
        Player.worldMoveX = 0;
    }
}

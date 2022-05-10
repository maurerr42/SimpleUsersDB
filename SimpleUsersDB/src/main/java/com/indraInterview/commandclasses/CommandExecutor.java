package com.indraInterview.commandclasses;

import com.indraInterview.queue.CommandQueueWithLocks;
import com.indraInterview.utils.Utils;
import com.indraInterview.db.DBHandler;
import com.indraInterview.queue.CommandQueueBlocking;

public class CommandExecutor implements Runnable {

    private Integer id = -1;

    private DBHandler dbHandler = new DBHandler();
    private Boolean isRunning = true;

    public CommandExecutor(Integer id) {
        this.id = id;
    }

    @Override
    public void run() {
        Command command;

        while (isRunning) {
//            command = CommandQueueBlocking.readCommand();
            command = CommandQueueWithLocks.readCommand();

            Utils.printSystemMessage("Executing command: " + command.getCommand() + ", executor id: " + id);

            switch (command.getCommand()) {
                case "Add":
                    dbHandler.add(command.getUserId(), command.getUserGuid(), command.getUserName());
                    break;
                case "PrintAll":
                    dbHandler.printAll();
                    break;
                case "DeleteAll":
                    dbHandler.deleteAll();
                    break;
                default:
                    System.out.println("Unknown command read");
            }
        }
    }
}

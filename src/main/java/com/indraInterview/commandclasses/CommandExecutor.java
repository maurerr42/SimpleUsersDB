package com.indraInterview.commandclasses;

import com.indraInterview.queue.CommandQueueWithLocks;
import com.indraInterview.utils.Utils;
import com.indraInterview.db.DBHandler;

public class CommandExecutor implements Runnable {

    private Integer id = -1;

    public CommandExecutor(Integer id) {
        this.id = id;
    }

    @Override
    public void run() {
        DBHandler dbHandler = new DBHandler();
        Command command;

        while (true) {
//            command = CommandQueueBlocking.readCommand();
            command = CommandQueueWithLocks.readCommand(id);
            Utils.printExecutorMessage("Command read - " + command.getCommand(), id);

            switch (command.getCommand()) {
                case "Add":
                    dbHandler.add(command.getUserId(), command.getUserGuid(), command.getUserName());
                    break;
                case "PrintAll":
                    System.out.println("vvvv-printAll-vvvv\n" + dbHandler.printAll() + "\n^^^^-printAll-^^^^");
                    break;
                case "DeleteAll":
                    dbHandler.deleteAll();
                    break;
                default:
                    System.out.println("Unknown command read");
            }

            Utils.printExecutorMessage("Finished", id);
        }
    }
}

package com.indraInterview.commandclasses;

import com.indraInterview.queue.CommandQueueWithLocks;

public class CommandIssuer implements Runnable {

    private void addCommand(Command command) {
//      CommandQueueBlocking.addCommand(command);
        CommandQueueWithLocks.addCommand(command, -1);
    }

    private void runCommands() {
        addCommand(new Command("Add", 1, "a1", "Robert"));
        addCommand(new Command("Add", 2, "a2", "Martin"));
        addCommand(new Command("PrintAll"));
        addCommand(new Command("DeleteAll"));
        addCommand(new Command("PrintAll"));
    }

    @Override
    public void run() {
        runCommands();
    }
}

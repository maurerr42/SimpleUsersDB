package com.indraInterview.queue;

import com.indraInterview.commandclasses.Command;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CommandQueueBlocking {
    private static BlockingQueue<Command> commandQueue = new ArrayBlockingQueue<>(20);

    public static void addCommand(Command command) {
        try {
            commandQueue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Command readCommand() {
        Command result = null;

        try {
            result = commandQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}

package com.indraInterview.utils;

import com.indraInterview.commandclasses.Command;

import java.util.Queue;

public class Utils {

    private static Boolean isDBHandlerPrinting = false;
    private static Boolean isExecutorPrinting = false;
    private static Boolean isQueuePrinting = false;

    private static int sleepTime = 0;

    public static int userId = 1;

    public static void setSleepTime(int sleepTime) {
        Utils.sleepTime = sleepTime;
    }

    public static void setIsDBHandlerPrinting(Boolean isExecutorPrinting) {
        Utils.isDBHandlerPrinting = isExecutorPrinting;
    }

    public static void printDBHandlerMessage(String message) {
        if (isDBHandlerPrinting) System.out.println("DBHandler: " + message);
    }

    public static void setIsExecutorPrinting(Boolean isExecutorPrinting) {
        Utils.isExecutorPrinting = isExecutorPrinting;
    }

    public static void printExecutorMessage(String message, int executorId) {
        if (isExecutorPrinting) System.out.println("Executor(" + executorId + "): " + message);
    }

    public static void setIsQueuePrinting(Boolean isQueuePrinting) {
        Utils.isQueuePrinting = isQueuePrinting;
    }

    public static void printQueue(Queue<Command> queue, int id, Boolean isProducer) {
        if (isQueuePrinting) {
            StringBuilder printOutBuilder = new StringBuilder();
            printOutBuilder.append("Queue");
            if (isProducer) {
                printOutBuilder.append(" ([id: " + id + "]producer added): ");
            } else {
                printOutBuilder.append(" (consumer[id: " + id + "] took): ");
            }

            int i = 1;
            for (Command command : queue) {
                printOutBuilder.append(i + ": " + command.getCommand());
                if (i < queue.size()) {
                    printOutBuilder.append(" | ");
                } else {
                    printOutBuilder.append("\n");
                }

                i++;
            }

            if (queue.isEmpty()) printOutBuilder.append("\n");

            System.out.print(printOutBuilder.toString());

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

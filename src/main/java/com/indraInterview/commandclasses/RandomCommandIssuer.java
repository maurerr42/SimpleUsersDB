package com.indraInterview.commandclasses;

import com.indraInterview.queue.CommandQueueWithLocks;
import com.indraInterview.utils.Utils;

import java.util.Random;

public class RandomCommandIssuer implements Runnable {

    private Integer id = -1;

    Random rand = new Random();

    public RandomCommandIssuer(Integer id) {
        this.id = id;
    }

    private String getRandomUserGuid() {
        return String.valueOf(rand.nextInt(300));
    }

    private void addCommand(Command command) {
        CommandQueueWithLocks.addCommand(command, id);
    }

    @Override
    public void run() {
        while (true) {
            double randomNumber = Math.random() * 10;
            double lowerDecider = 7;
            double upperDecider = 9;
            if (lowerDecider > randomNumber) {
                addCommand(new Command("Add", Utils.userId++, getRandomUserGuid(), "Robert"));
            }
            if ((lowerDecider < randomNumber) && (upperDecider > randomNumber)) {
                addCommand(new Command("PrintAll"));
            }
            if (upperDecider < randomNumber) {
                addCommand(new Command("DeleteAll"));
            }
        }
    }
}

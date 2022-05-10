package com.indraInterview;

import com.indraInterview.commandclasses.CommandExecutor;
import com.indraInterview.commandclasses.CommandIssuer;
import com.indraInterview.commandclasses.RandomCommandIssuer;
import com.indraInterview.utils.Utils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Utils.setIsDBHandlerPrinting(true);
        Utils.setIsQueuePrinting(true);
        Utils.setIsExecutorPrinting(true);
        Utils.setSleepTime(500);

        CommandExecutor executor1 = new CommandExecutor(1);
        CommandExecutor executor2 = new CommandExecutor(2);
        CommandExecutor executor3 = new CommandExecutor(3);
        CommandExecutor executor4 = new CommandExecutor(4);
        CommandExecutor executor5 = new CommandExecutor(5);
        new Thread(executor1).start();
        new Thread(executor2).start();
        new Thread(executor3).start();
        new Thread(executor4).start();
        new Thread(executor5).start();

//        CommandIssuer issuer = new CommandIssuer();
//        new Thread(issuer).start();

        RandomCommandIssuer randomIssuer1 = new RandomCommandIssuer(1);
        RandomCommandIssuer randomIssuer2 = new RandomCommandIssuer(2);
        RandomCommandIssuer randomIssuer3 = new RandomCommandIssuer(3);
        RandomCommandIssuer randomIssuer4 = new RandomCommandIssuer(4);
        RandomCommandIssuer randomIssuer5 = new RandomCommandIssuer(5);
        new Thread(randomIssuer1).start();
        new Thread(randomIssuer2).start();
        new Thread(randomIssuer3).start();
        new Thread(randomIssuer4).start();
        new Thread(randomIssuer5).start();
    }
}

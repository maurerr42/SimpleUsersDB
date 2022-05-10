package com.indraInterview;

import com.indraInterview.commandclasses.CommandExecutor;
import com.indraInterview.commandclasses.CommandIssuer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CommandExecutor executor1 = new CommandExecutor(1);
        CommandExecutor executor2 = new CommandExecutor(2);
        new Thread(executor1).start();
        new Thread(executor2).start();

        CommandIssuer issuer = new CommandIssuer();
        new Thread(issuer).start();
    }
}

package com.indraInterview.utils;

public class Utils {
    private static Boolean systemMessages = false;

    public static void printSystemMessage(String message) {
        if (systemMessages) System.out.println(message);
    }
}

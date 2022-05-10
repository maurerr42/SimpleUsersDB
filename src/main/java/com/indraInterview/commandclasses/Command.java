package com.indraInterview.commandclasses;

public class Command {
    private String command;
    private int userId;
    private String userGuid;
    private String userName;

    public Command (String command) {
        this.command = command;
    }

    public Command (String command, int userId, String userGuid, String userName) {
        this.command = command;
        this.userId = userId;
        this.userGuid = userGuid;
        this.userName = userName;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

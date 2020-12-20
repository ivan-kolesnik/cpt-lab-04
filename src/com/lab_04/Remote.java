package com.lab_04;

import com.lab_04.command.Command;

import java.util.LinkedList;
import java.util.List;

public class Remote {
    private List<Command> commandList = new LinkedList<Command>();

    public void submit(Command command) {
        commandList.add(command);
        command.execute();
    }

    public void undo() {
        if (commandList.isEmpty()) {
            return;
        }

        Command command = commandList.get(commandList.size() - 1);
        command.undo();

        commandList.remove(command);
    }
}

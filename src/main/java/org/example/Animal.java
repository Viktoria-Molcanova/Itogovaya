package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Animal {
    private int id;
    private String name;
    private String type;
    private List<String> commands;

    public Animal(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.commands = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }
}
package edu.io.player;

import edu.io.Tool;

import java.util.EmptyStackException;
import java.util.Stack;

public class Shed {
    private final Stack<Tool> tools;

    public Shed() {
        this.tools = new Stack<>();
    }

    public boolean isEmpty() {
        return tools.isEmpty();
    }

    public void add(Tool tool) {
        if (tool == null) {
            throw new IllegalArgumentException("Cannot add a null tool to the shed.");
        }
        tools.push(tool);
    }

    public Tool getTool() throws EmptyStackException {
        if (tools.isEmpty()) {
            return new NoTool();
        }
        return tools.pop();
    }

    public void dropTool() throws EmptyStackException {
        tools.pop();
    }
}

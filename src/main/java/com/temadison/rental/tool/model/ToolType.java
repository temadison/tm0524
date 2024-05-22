package com.temadison.rental.tool.model;

public enum ToolType {
    CHAINSAW("Chainsaw"), JACKHAMMER("Jackhammer"), LADDER("Ladder");

    final String name;

    ToolType(String name) {
        this.name = name;
    }
}

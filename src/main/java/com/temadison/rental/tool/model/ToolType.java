package com.temadison.rental.tool.model;

public enum ToolType {
    CHAINSAW("Chainsaw"), JACKHAMMER("Jackhammer"), LADDER("Ladder");

    final String value;

    ToolType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}

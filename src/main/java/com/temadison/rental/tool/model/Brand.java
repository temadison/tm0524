package com.temadison.rental.tool.model;

public enum Brand {
    STIHL("Stihl"), DEWALT("DeWalt"), RIDGID("Ridgid"), WERNER("Werner");

    final String name;

    Brand(String name) {
        this.name = name;
    }
}

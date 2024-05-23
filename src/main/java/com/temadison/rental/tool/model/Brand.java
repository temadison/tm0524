package com.temadison.rental.tool.model;

public enum Brand {
    STIHL("Stihl"), DEWALT("DeWalt"), RIDGID("Ridgid"), WERNER("Werner");

    final String value;

    Brand(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}

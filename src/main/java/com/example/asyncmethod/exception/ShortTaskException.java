package com.example.asyncmethod.exception;

import lombok.Getter;

public class ShortTaskException extends Exception{
    @Getter
    private int number;

    public ShortTaskException(String message, int number) {
        super(message);
        this.number = number;
    }
}

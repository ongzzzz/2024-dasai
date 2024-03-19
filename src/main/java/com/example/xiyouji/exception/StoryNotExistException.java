package com.example.xiyouji.exception;

public class StoryNotExistException extends RuntimeException {

    private final static String message = "Story Not Exist";
    public StoryNotExistException() {
        super(message);
    }
}

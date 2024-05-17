package com.example.neftchi.exception;

public class NotFound extends Error{
    public NotFound() {
        throw new Error("Not found");
    }
}
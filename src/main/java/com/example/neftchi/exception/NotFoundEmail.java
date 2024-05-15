package com.example.neftchi.exception;

public class NotFoundEmail extends Error {
    public NotFoundEmail() {
        throw new Error("Not found email");
    }
}
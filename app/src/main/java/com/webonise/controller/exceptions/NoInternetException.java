package com.webonise.controller.exceptions;

public class NoInternetException extends Exception {
    public NoInternetException() {
        super("No Internet connection");
    }
}

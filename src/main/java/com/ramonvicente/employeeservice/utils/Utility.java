package com.ramonvicente.employeeservice.utils;

public class Utility {
    public static void checkArgument(boolean isTrue, String message) {
        if(isTrue) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkArgument(boolean isTrue) {
        if(isTrue) {
            throw new IllegalArgumentException();
        }
    }
}

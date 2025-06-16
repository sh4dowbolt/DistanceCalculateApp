package com.suraev.routeDestinationApp.util;

public class StringValidator {

    public static boolean isValidUTF8(String input) {
        if (input == null) {
            return false;
        }
        try {
            byte[] bytes = input.getBytes("UTF-8");
            String newString = new String(bytes, "UTF-8");
            return input.equals(newString);
        } catch (Exception e) {
            return false;
        }
    }
} 
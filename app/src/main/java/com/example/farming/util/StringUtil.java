package com.example.farming.util;

public class StringUtil {
    private StringUtil(){}

    public static final boolean isEmpty(String string) {
        if (string == null || string.trim().equals("")) return true;
        return false;
    }

}

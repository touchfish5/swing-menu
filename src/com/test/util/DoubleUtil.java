package com.test.util;


public class DoubleUtil {
    public static Boolean isDouble(String str) {
        Boolean flag = true;
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e){
            flag = false;
        }
        return flag;
    }
}

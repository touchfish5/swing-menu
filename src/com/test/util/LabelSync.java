package com.test.util;

import javax.swing.JLabel;

public class LabelSync {
    private static Object lock = new Object();

    public static void setText(String msg, JLabel jta) {
        synchronized (lock) {
            jta.setText(msg);
        }
    }
}

package com.test;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ping extends Thread {
    private String ping;
    private JTextArea result;
    private JLabel successCount, failCount;
    private Process pro;
    private JButton start;

    public Ping(String ping, JTextArea result, JLabel successCount, JLabel failCount, JButton start) {
        this.ping = ping;
        this.result = result;
        this.successCount = successCount;
        this.failCount = failCount;
        this.start = start;
    }

    @Override
    public void run() {
        super.run();
        StringBuilder resultstr = new StringBuilder();
        try {
            String str = null;
            this.pro = Runtime.getRuntime().exec(ping);
            BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream(), "GBK"));
            int s = 0, f = 0;
            while ((str = buf.readLine()) != null) {
                resultstr.append(str + "\n");
                if (str.contains("TTL=")) {
                    successCount.setText(String.format("%d", ++s));
                }
                if (str.contains("无法访问目标主机") || str.contains("传输失败")) {
                    failCount.setText(String.format("%d", ++f));
                }
                result.setText(resultstr + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            start.setText("开始");
        }
    }

    public void Stop() {
        pro.destroy();
    }
}

package com.test;

import com.test.util.FileUtil;
import com.test.util.LabelSync;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.zip.CRC32;

public class Progress extends Thread {
    private JProgressBar jProgressBar;
    private JLabel browseFilelabel;
    private File file = null;
    private String MD5 = null, SHA1 = null, CRC32 = null;
    private int[] arguments = null; // 参数计算的标识 1:该参数计算;0:给参数不需计算
    private StringBuilder stringBuilder;
    private JTextArea result;

    public Progress(JProgressBar jProgressBar, JLabel jLabel, JTextArea result, File file, int arguments[]) {
        this.jProgressBar = jProgressBar;
        this.browseFilelabel = jLabel;
        this.result = result;
        this.file = file;
        this.arguments = arguments;
    }

    public void run() {
        stringBuilder = new StringBuilder();
        stringBuilder.append("文件名称：" + file.getName() + "\n");
        stringBuilder.append("文件大小：" + FileUtil.getSize(file) + "\n");
        stringBuilder.append("修改时间：" + FileUtil.getDate(file) + "\n");
        long i = 0;
        long fileLen = file.length() * (arguments[0] + arguments[1] + arguments[2]);
        try {
            int len = 0;
            byte[] bytes = new byte[1024];
            // MD5
            stringBuilder.append("MD5：");
            result.setText(stringBuilder.toString());
            if (arguments[0] == 1) {
                FileInputStream fisMD5 = new FileInputStream(file);
                MessageDigest digestMD5 = MessageDigest.getInstance("MD5");

                while ((len = fisMD5.read(bytes, 0, 1024)) != -1) {
                    digestMD5.update(bytes);
                    i += len;
                    int value = (int) ((double) i / fileLen * 100);
                    LabelSync.setText(String.format("%d%%", value), browseFilelabel);
                    jProgressBar.setValue(value);
                }
                fisMD5.close();
                BigInteger bigIntegerMD5 = new BigInteger(1, digestMD5.digest());
                MD5 = bigIntegerMD5.toString(16);
                stringBuilder.append(MD5);
            }
            // SHA1
            stringBuilder.append("\nSHA1：");
            result.setText(stringBuilder.toString());
            if (arguments[1] == 1) {
                FileInputStream fisSHA1 = new FileInputStream(file);
                MessageDigest digestSHA1 = MessageDigest.getInstance("SHA1");
                while ((len = fisSHA1.read(bytes, 0, 1024)) != -1) {
                    digestSHA1.update(bytes);
                    i += len;
                    int value = (int) ((double) i / fileLen * 100);
                    LabelSync.setText(String.format("%d%%", value), browseFilelabel);
                    jProgressBar.setValue(value);
                }
                fisSHA1.close();
                BigInteger bigIntegerSHA1 = new BigInteger(1, digestSHA1.digest());
                SHA1 = bigIntegerSHA1.toString(16);
                stringBuilder.append(SHA1);
            }
            // CRC32
            stringBuilder.append("\nCRC32：");
            result.setText(stringBuilder.toString());
            if (arguments[2] == 1) {
                CRC32 crc32 = new CRC32();
                FileInputStream fis1 = new FileInputStream(file);
                while ((len = fis1.read(bytes, 0, 1024)) != -1) {
                    crc32.update(bytes);
                    i += len;
                    int value = (int) ((double) i / fileLen * 100);
                    LabelSync.setText(String.format("%d%%", value), browseFilelabel);
                    jProgressBar.setValue(value);
                }
                fis1.close();
                CRC32 = Long.toHexString(crc32.getValue()).toUpperCase();
                stringBuilder.append(CRC32);
            }
            result.setText(stringBuilder.toString());
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}

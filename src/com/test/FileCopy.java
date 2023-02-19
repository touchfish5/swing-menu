package com.test;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.CountDownLatch;

public class FileCopy extends Thread {
    private String origFile = null;
    private String destFile = null;
    private Long startIndex;
    private Long endIndex;
    private DefaultTableModel model;
    private int index;


    public FileCopy(int index, String origFile, String destFile, Long startIndex, Long endIndex, DefaultTableModel model) {
        this.origFile = origFile;
        this.destFile = destFile;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.model = model;
        this.index = index;
    }

    @Override
    public void run() {
        File file = new File(origFile);
        model.setValueAt(index + 1, index, 0);
        model.setValueAt(file.getName(), index, 1);
        model.setValueAt(endIndex - startIndex, index, 2);
        model.setValueAt(startIndex, index, 3);
        model.setValueAt(endIndex, index, 4);
        RandomAccessFile srcRas = null;
        RandomAccessFile destRas = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            srcRas = new RandomAccessFile(file, "rw");
            destRas = new RandomAccessFile(destFile, "rw");
            srcRas.seek(startIndex);
            destRas.seek(startIndex);

            inChannel = srcRas.getChannel();
            outChannel = destRas.getChannel();

            long total = (int) (endIndex - startIndex);
            int len = 0;
            long copyLen = 0;
            final int buflen = 1024 * 1024 * 8;
            while (true) {
                len = buflen;
                if (copyLen + len > total) {
                    len = (int) (total - copyLen);
                }
                inChannel.transferTo(startIndex + copyLen, len, outChannel);
                copyLen += len;
                model.setValueAt(copyLen * 100 / total + "%", index, 5);
                if (startIndex + copyLen >= endIndex) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inChannel) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != outChannel) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (srcRas != null) {
                try {
                    srcRas.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                srcRas = null;
            }
            if (destRas != null) {
                try {
                    destRas.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                destRas = null;
            }
        }
    }
}

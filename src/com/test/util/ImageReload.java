package com.test.util;

import com.test.functionPanel.ElectricPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageReload extends JPanel implements ActionListener {
    JLabel imageLabel = new JLabel();
    Timer timer = new Timer(100, this);
    int i = 0;
    public ImageReload() {
        setLayout(new BorderLayout());
        add(imageLabel);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        i = 0;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                i++;
                imageLabel.setIcon(new ImageIcon(("src/images/electricityMeter" + i % 5 + ".png")));
                if (i == 24) {
                    timer.stop();
//                    ElectricPanel.changeNewInfo();
                }
            }
        });
    }
}

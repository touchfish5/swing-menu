package com.test.functionPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VideoPanel extends JPanel{
    public VideoPanel() {
        super();
        initComponents();
        setListeners();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        VideoJFXPanel jfxPanel = new VideoJFXPanel();
        add(jfxPanel, BorderLayout.CENTER);
    }


    private void setListeners() {

    }

    private void initFont(Component...components) {
        for (Component c : components) {
            c.setFont(new Font("黑体", Font.PLAIN, 30));
        }
    }

    private void initButtonFont(Component ...components) {
        for (Component c : components) {
            c.setPreferredSize(new Dimension(80,40));
            c.setFont(new Font("黑体", Font.PLAIN, 25));
        }
    }
}

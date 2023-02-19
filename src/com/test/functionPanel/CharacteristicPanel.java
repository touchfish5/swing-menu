package com.test.functionPanel;

import javax.swing.*;
import java.awt.*;

public class CharacteristicPanel extends JPanel {
    private final Dimension labelDimension = new Dimension(100,30);
    private JLabel produceInfo = null;
    private JLabel activateInfo = null;
    private JLabel downloadInfo = null;
    private JButton button = null;
    public CharacteristicPanel() {
        super();
        initComponents();
        setListeners();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // north
        JPanel termPanel = new JPanel(new GridLayout(3, 1,0,30));
        termPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0), 30));
        JLabel produce = new JLabel("生产：");
        produce.setPreferredSize(labelDimension);
        produceInfo = new JLabel();
        JPanel producePanel = new JPanel(new BorderLayout());
        producePanel.add(produce, BorderLayout.WEST);
        producePanel.add(produceInfo, BorderLayout.CENTER);

        JLabel activate = new JLabel("激活：");
        activate.setPreferredSize(labelDimension);
        activateInfo = new JLabel();
        JPanel activatePanel = new JPanel(new BorderLayout());
        activatePanel.add(activate, BorderLayout.WEST);
        activatePanel.add(activateInfo, BorderLayout.CENTER);

        JLabel download = new JLabel("下载：");
        download.setPreferredSize(labelDimension);
        downloadInfo = new JLabel();
        JPanel downloadPanel = new JPanel(new BorderLayout());
        downloadPanel.add(download, BorderLayout.WEST);
        downloadPanel.add(downloadInfo, BorderLayout.CENTER);

        initFont(produce, activate, download);
        initFont(produceInfo, activateInfo, downloadInfo);
        termPanel.add(producePanel);
        termPanel.add(activatePanel);
        termPanel.add(downloadPanel);
        add(termPanel, BorderLayout.NORTH);

        // center
        button = new JButton("获取");
        initButtonFont(button);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0), 30));
        buttonPanel.add(button);
        add(buttonPanel, BorderLayout.CENTER);
    }
    private void setListeners() {
        button.addActionListener((e) -> {
            produceInfo.setText("已完成");
            activateInfo.setText("已完成");
            downloadInfo.setText("已完成");
        });
    }

    private void initFont(Component...components) {
        for (Component c : components) {
            c.setFont(new Font("黑体", Font.PLAIN, 25));
        }
    }

    private void initButtonFont(Component ...components) {
        for (Component c : components) {
            c.setPreferredSize(new Dimension(90,40));
            c.setFont(new Font("黑体", Font.PLAIN, 25));
        }
    }
}

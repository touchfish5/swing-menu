package com.test.functionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SizePanel extends JPanel implements ActionListener{
    private JLabel laserImage = null;
    private JLabel equipmentImage = null;
    private JButton button = new JButton("测量");
    private static JLabel info = null;
    private static JPanel laserPanel = null;
    Timer timer = new Timer(100, this);
    private final Dimension labelDimension = new Dimension(60,0);
    public SizePanel() {
        super();
        initComponents();
        setListeners();
    }
    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // north
        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0), 30));
        JLabel infoLabel = new JLabel("设备长*宽*高：");
        info = new JLabel("   *  *  (cm)");
        JLabel space = new JLabel();
        space.setPreferredSize(labelDimension);
        initFont(infoLabel, info);
        infoPanel.add(infoLabel);
        infoPanel.add(info);
        infoPanel.add(space);
        initButtonFont(button);
        infoPanel.add(button);
        add(infoPanel, BorderLayout.NORTH);

        // center
        JPanel equipmentPanel= new JPanel(new FlowLayout());
        Icon equipment = new ImageIcon("src/images/businessEquipment0.png");
        equipmentImage = new JLabel(equipment);
        equipmentPanel.add(equipmentImage);
        JLabel space1 = new JLabel();
        space1.setPreferredSize(new Dimension(50,0));
        add(equipmentPanel, BorderLayout.CENTER);


        // south
        JPanel southPanel = new JPanel(new BorderLayout());
        laserPanel = new JPanel(new FlowLayout());
        Icon laser = new ImageIcon("src/images/laserScanner0.png");
        laserImage = new JLabel(laser);
        laserPanel.add(space1);
        laserPanel.add(laserImage);
        JLabel space2 = new JLabel();
        space2.setPreferredSize(new Dimension(0,20));
        laserPanel.setVisible(false);
        southPanel.add(laserPanel);
        southPanel.add(space2, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void setListeners() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText("   *  *  (cm)");
                i=0;
                laserPanel.setVisible(true);
                timer.start();
            }
        });
    }
    private void initFont(Component ...components) {
        for (Component c : components) {
            c.setFont(new Font("黑体", Font.PLAIN, 30));
        }
    }
    private void initButtonFont(Component ...components) {
        for (Component c : components) {
            c.setPreferredSize(new Dimension(90,40));
            c.setFont(new Font("黑体", Font.PLAIN, 25));
        }
    }

    int i=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                laserImage.setIcon(new ImageIcon("src/images/laserScanner" + i % 3 + ".png"));
                equipmentImage.setIcon(new ImageIcon("src/images/businessEquipment" + i + ".png"));
                if (i == 22) {
                    laserImage.setIcon(new ImageIcon("src/images/laserScanner0.png"));
                    info.setText("80*60*30(cm)");
                    timer.stop();
                }
                i++;
            }
        });
    }
}

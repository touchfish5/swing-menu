package com.test.functionPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElectricPanel extends JPanel implements ActionListener{
    private JLabel meterImage = null;
    private final JButton button = new JButton("获取");
    private static JLabel info = null;
    private final Timer timer = new Timer(100, this);
    private int i = 0;
    private final Dimension labelDimension = new Dimension(60,0);
    public ElectricPanel() {
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
        JLabel infoLabel = new JLabel("工作电压/电流：");
        info = new JLabel("   V/  A/  Hz");
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
        JPanel meter = new JPanel(new FlowLayout());
        Icon electricityMeter3 = new ImageIcon("src/images/electricityMeter4.png");
        meterImage = new JLabel(electricityMeter3);
        meter.add(meterImage);
        add(meter, BorderLayout.CENTER);
    }

    private void setListeners() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setEnabled(false);
                i = 0;
                info.setText("   V/  A/  Hz");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                i++;
                meterImage.setIcon(new ImageIcon(("src/images/electricityMeter" + i % 5 + ".png")));
                if (i == 14) {
                    timer.stop();
                    info.setText("220V/10A/50Hz");
                    button.setEnabled(true);
                }
            }
        });
    }
}

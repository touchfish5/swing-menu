package com.test.functionPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParameterPanel extends JPanel {
    private final Dimension labelDimension = new Dimension(50,30);
    private JLabel b1Image = null;
    private JLabel b2Image = null;
    private JLabel b3Image = null;
    private final Icon redLight = new ImageIcon("src/images/redLight.png");
    private final Icon greenLight = new ImageIcon("src/images/greenLight.png");
    private final Icon yellowLight = new ImageIcon("src/images/yellowLight.png");
    private JButton state1Button = null;
    private JButton state2Button = null;
    private JButton state3Button = null;
    public ParameterPanel() {
        super();
        initComponents();
        setListeners();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // north
        JPanel termPanel = new JPanel(new GridLayout(3, 1,0,20));
        termPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0), 30));
        JLabel b1 = new JLabel("B1");
        b1.setPreferredSize(labelDimension);
        b1Image = new JLabel(redLight);
        JPanel b1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        b1Panel.add(b1);
        b1Panel.add(b1Image);
        JLabel b2 = new JLabel("B2");
        b2.setPreferredSize(labelDimension);
        b2Image = new JLabel(redLight);
        JPanel b2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        b2Panel.add(b2);
        b2Panel.add(b2Image);
        JLabel b3 = new JLabel("B3");
        b3.setPreferredSize(labelDimension);
        b3Image = new JLabel(redLight);
        JPanel b3Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        b3Panel.add(b3);
        b3Panel.add(b3Image);
        initFont(b1, b2, b3);
        termPanel.add(b1Panel);
        termPanel.add(b2Panel);
        termPanel.add(b3Panel);
        add(termPanel, BorderLayout.NORTH);

        // south
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0), 25));
        state1Button = new JButton("红");
        state2Button = new JButton("黄");
        state3Button = new JButton("绿");
        buttonPanel.add(state1Button);
        buttonPanel.add(state2Button);
        buttonPanel.add(state3Button);
        initButtonFont(state1Button, state2Button, state3Button);
        add(buttonPanel);
    }

    private void setListeners() {
        state1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b1Image.setIcon(redLight);
                b2Image.setIcon(redLight);
                b3Image.setIcon(redLight);
            }
        });
        state2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b1Image.setIcon(yellowLight);
                b2Image.setIcon(yellowLight);
                b3Image.setIcon(yellowLight);
            }
        });
        state3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b1Image.setIcon(greenLight);
                b2Image.setIcon(greenLight);
                b3Image.setIcon(greenLight);
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
            c.setPreferredSize(new Dimension(80,40));
            c.setFont(new Font("黑体", Font.PLAIN, 25));
        }
    }
}

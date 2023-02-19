package com.test;


import com.test.functionPanel.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class MeterFrame extends JFrame {
    private static MeterFrame ins = null;
    // top
    private JLabel lbTitle;

    // left
    private JTree jTree;

    // center
    private JPanel centerPanel;

    private MeterFrame() {
        super();
        initComponents();
        setListeners();
    }

    public static MeterFrame getIns() {
        if (null == ins) {
            ins = new MeterFrame();
        }
        return ins;
    }

    private void initComponents() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setSize(new Dimension((int) (dimension.getWidth() * 0.7), (int) (dimension.getHeight() * 0.75)));
        setResizable(false);
        setLayout(new BorderLayout(5, 5));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initTop();
        initLeft();
        initCenter();
    }

    private void initTop() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setLayout(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel topCenterPanel = new JPanel(new FlowLayout());
        // center
        lbTitle = new JLabel("仪表");
        lbTitle.setFont(new Font("黑体", 1, 50));
        topCenterPanel.add(lbTitle);

        topPanel.add(topCenterPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
    }

    private void initLeft() {
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setLayout(new BorderLayout(5, 5));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel leftTopPanel = new JPanel(new FlowLayout());
        // top
        DefaultMutableTreeNode function = new DefaultMutableTreeNode("功能");
        DefaultMutableTreeNode parameter = new DefaultMutableTreeNode("参数信息");
        DefaultMutableTreeNode electric = new DefaultMutableTreeNode("电压信息");
        DefaultMutableTreeNode size = new DefaultMutableTreeNode("物理尺寸");
        DefaultMutableTreeNode characteristic = new DefaultMutableTreeNode("特性参数");
        DefaultMutableTreeNode video = new DefaultMutableTreeNode("生产视频");

        function.add(parameter);
        function.add(electric);
        function.add(size);
        function.add(characteristic);
        function.add(video);
        // project.
        jTree = new JTree(function);
        leftTopPanel.add(jTree);
        jTree.setOpaque(false);
        jTree.setFont(new Font("黑体",Font.PLAIN, 20));

        leftPanel.add(leftTopPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
    }

    private void initCenter() {
        centerPanel = new JPanel(new BorderLayout());
        CharacteristicPanel characteristicPanel = new CharacteristicPanel();
        centerPanel.add(characteristicPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void setListeners() {
        // JTree菜单
        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                if (node.toString().equals("参数信息")) {
                    remove(centerPanel);
                    centerPanel = new JPanel(new BorderLayout());
                    ParameterPanel parameterPanel = new ParameterPanel();
                    centerPanel.add(parameterPanel);
                    add(centerPanel, BorderLayout.CENTER);
                    setVisible(true);
                }
                if (node.toString().equals("电压信息")) {
                    remove(centerPanel);
                    centerPanel = new JPanel(new BorderLayout());
                    ElectricPanel electricPanel = new ElectricPanel();
                    centerPanel.add(electricPanel);
                    add(centerPanel, BorderLayout.CENTER);
                    setVisible(true);
                }
                if (node.toString().equals("物理尺寸")) {
                    remove(centerPanel);
                    centerPanel = new JPanel(new BorderLayout());
                    SizePanel sizePanel = new SizePanel();
                    centerPanel.add(sizePanel);
                    add(centerPanel, BorderLayout.CENTER);
                    setVisible(true);
                }
                if (node.toString().equals("特性参数")) {
                    remove(centerPanel);
                    centerPanel = new JPanel(new BorderLayout());
                    CharacteristicPanel characteristicPanel = new CharacteristicPanel();
                    centerPanel.add(characteristicPanel);
                    add(centerPanel, BorderLayout.CENTER);
                    setVisible(true);
                }
                if (node.toString().equals("生产视频")) {
                    remove(centerPanel);
                    centerPanel = new JPanel(new BorderLayout());
                    VideoPanel videoPanel = new VideoPanel();
                    centerPanel.add(videoPanel);
                    add(centerPanel, BorderLayout.CENTER);
                    setVisible(true);
                }
            }
        });
    }
}

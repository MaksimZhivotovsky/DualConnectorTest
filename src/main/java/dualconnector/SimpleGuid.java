package dualconnector;

import javax.swing.*;
import java.awt.*;

public class SimpleGuid extends JFrame {
    private JButton button = new JButton("Press");
    private JTextField input = new JTextField("", 5);
    private JLabel label = new JLabel("Input");
    private JRadioButton radioButton1 = new JRadioButton("Select this");
    private JRadioButton radioButton2 = new JRadioButton("Select that");
    private JCheckBox checkBox = new JCheckBox("Check", false);

    public SimpleGuid() {
        super("Dual Connector Test");
        this.setBounds(800, 800, 1550, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
        container.add(label);
        container.add(input);

        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        container.add(radioButton1);
        radioButton1.setSelected(true);
        container.add(radioButton2);
        container.add(checkBox);
        container.add(button);
    }
}

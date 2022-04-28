package edu.hitsz.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TablePanel {
    private JPanel tablePanel;
    private JPanel topPanel;
    private JTable scoreTable;
    private JPanel bottomPanel;
    private JButton button;

/*    public static void main(String[] args) {
        JFrame frame = new JFrame("TablePanel");
        frame.setContentPane(new TablePanel().tablePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }*/

    public TablePanel() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    public JPanel getTablePanel() {
        return tablePanel;
    }
}

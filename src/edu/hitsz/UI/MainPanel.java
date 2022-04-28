package edu.hitsz.UI;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class MainPanel {
    private JPanel mainPanel;
    private JButton easyButton;
    private JButton hardButton;
    private JButton normalButton;
    private JCheckBox soundCheckBox;
    private JPanel buttonPanel;

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

/*    public static void main(String[] args) {
        JFrame frame = new JFrame("edu/hitsz/UI");
        frame.setContentPane(new MainPanel().mainPanel);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }*/

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MainPanel() {
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //改变背景图片
                try {
                    ImageManager.setBackgroundImage(ImageIO.read(new FileInputStream("src/images/bg.jpg")));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                mainPanel.setVisible(false);
                synchronized (Main.LOCK){
                    Main.LOCK.notify();
                }
            }
        });
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //改变背景图片
                try {
                    ImageManager.setBackgroundImage(ImageIO.read(new FileInputStream("src/images/bg3.jpg")));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                mainPanel.setVisible(false);
                synchronized (Main.LOCK){
                    Main.LOCK.notify();
                }
            }
        });
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //改变背景图片
                try {
                    ImageManager.setBackgroundImage(ImageIO.read(new FileInputStream("src/images/bg5.jpg")));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                mainPanel.setVisible(false);
                synchronized (Main.LOCK){
                    Main.LOCK.notify();
                }
            }
        });
        soundCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



    }
}

package edu.hitsz.UI;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.dao.DAOimpl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;

public class MainPanel {
    private JPanel mainPanel;
    private JButton easyButton;
    private JButton hardButton;
    private JButton normalButton;
    private JCheckBox soundCheckBox;
    private JPanel buttonPanel;
    private JPanel textPanel;
    private JFormattedTextField IDtextbar;
    private static String textUserID =  "username";



    /**
     * 检测用户在第一个界面是否输入了ID数据
     */
    private static boolean userTexted = false;


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

    public static String getTextUserID() {
        return textUserID;
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
                //简单模式
                TablePanel.setGameMode(0);
                mainPanel.setVisible(false);
                synchronized (Main.LOCK) {
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
                //普通模式
                TablePanel.setGameMode(1);
                mainPanel.setVisible(false);
                synchronized (Main.LOCK) {
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
                //困难模式
                TablePanel.setGameMode(2);
                mainPanel.setVisible(false);
                synchronized (Main.LOCK) {
                    Main.LOCK.notify();
                }
            }
        });
        soundCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        IDtextbar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                textUserID = IDtextbar.getText();
                userTexted = true;
            }
        });

    }

    public static boolean isUserTexted() {
        return userTexted;
    }
}

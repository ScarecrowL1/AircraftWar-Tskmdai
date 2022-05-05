package edu.hitsz.application;

import edu.hitsz.UI.MainPanel;
import edu.hitsz.UI.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public static final Object LOCK = new Object();

    public static int gameMode = 0;

    public static void main(String[] args) {


        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        JPanel mainPanel = new MainPanel().getMainPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //第一个界面，选择难度
        frame.setContentPane(mainPanel);
        frame.setVisible(true);

        synchronized (LOCK){
            while (mainPanel.isVisible()){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        frame.remove(mainPanel);

        Game game = new Game();
        if(gameMode == 0){
            game = new EasyGame();
        }
        if(gameMode == 1){
            game = new NomalGame();
        }
        if(gameMode == 2){
            game = new HardGame();
        }

        frame.setContentPane(game);
        frame.setVisible(true);
        game.action();

        synchronized (LOCK){
            while (!game.isGameOver()){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        frame.remove(game);
        JPanel table = new TablePanel().getTablePanel();
        frame.setContentPane(table);
        frame.setSize(1024, WINDOW_HEIGHT);
        frame.setVisible(true);

    }

    public static void setGameMode(int gameMode) {
        Main.gameMode = gameMode;
    }
}

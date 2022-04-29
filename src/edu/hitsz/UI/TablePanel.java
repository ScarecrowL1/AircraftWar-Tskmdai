package edu.hitsz.UI;

import edu.hitsz.application.Game;
import edu.hitsz.dao.DAOimpl;
import edu.hitsz.dao.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TablePanel {
    private JPanel tablePanel;
    private JPanel topPanel;
    private JTable scoreTable;
    private JPanel bottomPanel;
    private JButton deleteButton;
    private JScrollPane tableScroll;
    private JFormattedTextField retextBar;
    private JButton changeButton;
    private JPanel retextPanel;

    private int currentIndex;
    private boolean needSaved;
    private static int gameMode;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TablePanel");
        frame.setContentPane(new TablePanel().tablePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public TablePanel() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("排名");
        model.addColumn("ID");
        model.addColumn("分数");
        model.addColumn("用时(s)");
        model.addColumn("日期");
        scoreTable.setModel(model);

        DAOimpl dao = new DAOimpl();
        dao.doRead();
        dao.doAdd(Game.getCurrentScore(), Game.getCurrentTime(), MainPanel.getTextUserID());
        dao.doRank();

        List<UserData> userDataList = dao.getUserDataList();
        int rank = 0;
        //将数据库中数据填入表中
        for(UserData ud : userDataList){
            Object[] data = {++rank, ud.getID(), ud.getScore(), ud.getTime(), ud.getDateInfo()};
            model.addRow(data);
        }

        //找出最近一次保存的记录，方法是以日期作为索引
        String endTime = "0000-00-00 00:00:00";
        for(UserData userData : userDataList){
            if(userData.getDateInfo().compareTo(endTime)>0){
                endTime = userData.getDateInfo();
                currentIndex = userDataList.indexOf(userData);
            }
        }

        int res = JOptionPane.showConfirmDialog(
                null,
                "游戏结束！你的名次是第 "+(currentIndex+1)+" 名\n是否保存数据到本地？",
                "游戏结束",JOptionPane.YES_NO_OPTION);
        needSaved = (res == 0);

        if(needSaved && !MainPanel.isUserTexted()){
            JOptionPane.showMessageDialog(
                    null,
                    "发现你使用的是默认ID，如需保存并修改，请稍后在排行榜下输入栏修改",
                    "游戏结束",JOptionPane.INFORMATION_MESSAGE);
        }

        if(needSaved){
            dao.doSave();
        }


        tableScroll.setViewportView(scoreTable);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int deleteRow = scoreTable.getSelectedRow();
                if(deleteRow != -1){
                    //通过日期信息的唯一性来定位应删除的元素
                    String dateInfo = (String) scoreTable.getValueAt(deleteRow, 4);
                    dao.doDelete(dateInfo);
                    //重新排序并保存到本地
                    dao.doRank();
                    dao.doSave();
                    model.removeRow(deleteRow);
                    for (int i =  deleteRow; i < scoreTable.getRowCount(); i++){
                        int currRank = (int) scoreTable.getValueAt(i,0) - 1;
                        scoreTable.setValueAt(currRank, i, 0);
                    }
                }
            }
        });


        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = scoreTable.getSelectedRow();
                //如果用户不选中指定行就点击按钮，则开始自动修改
                //自动修改：找出最近一次保存的记录，方法是以日期作为索引
                if (index == -1) {
                    index = currentIndex;
                }
                userDataList.get(index).setID(retextBar.getText());
                scoreTable.setValueAt(retextBar.getText(), index, 1);
                dao.doSave();
            }
        });
    }


    public JPanel getTablePanel() {
        return tablePanel;
    }

    public static void setGameMode(int gameMode) {
        TablePanel.gameMode = gameMode;
    }
}

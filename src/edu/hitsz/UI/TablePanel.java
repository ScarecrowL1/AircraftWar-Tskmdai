package edu.hitsz.UI;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
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
    private JLabel tabelLabel;

    private int currentIndex;
    private boolean needSaved;

    private int gameMode = Main.gameMode;

/*    public static void main(String[] args) {
        JFrame frame = new JFrame("TablePanel");
        frame.setContentPane(new TablePanel().tablePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }*/

    public TablePanel() {

        DefaultTableModel model = new DefaultTableModel(){
            @Override
            //不能在界面直接修改数据
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        model.addColumn("排名");
        model.addColumn("ID");
        model.addColumn("分数");
        model.addColumn("用时(s)");
        model.addColumn("日期");
        scoreTable.setModel(model);
        String modeName;
        switch (gameMode){
            case 1 : modeName = "排行榜  (普通模式)";
                break;
            case 2 : modeName = "排行榜  (困难模式)";
                break;
            case 0 :
            default: modeName = "排行榜  (简单模式)";
        }

        tabelLabel.setText(modeName);

        DAOimpl dao = new DAOimpl();
        //从本地中获取历史数据
        dao.doRead(gameMode);
        //从Game中获取本次游戏的数据，进行添加
        dao.doAdd(Game.getCurrentScore(), Game.getCurrentTime(), MainPanel.getTextUserID());
        //对所有数据进行排行
        dao.doRank();
        dao.printToConsole(gameMode);

        List<UserData> userDataList = dao.getUserDataList();
        int rank = 0;
        //将数据库中数据填入表中
        for(UserData ud : userDataList){
            Object[] data = {++rank, ud.getID(), ud.getScore(), ud.getTime(), ud.getDateInfo()};
            model.addRow(data);
        }

        //找出最近一次保存的记录，方法是以日期作为索引
        //最近一次的记录即本次记录
        String endTime = "0000-00-00 00:00:00";
        for(UserData userData : userDataList){
            if(userData.getDateInfo().compareTo(endTime)>0){
                endTime = userData.getDateInfo();
                currentIndex = userDataList.indexOf(userData);
            }
        }

        //显示排名，currentIndex从0数，所以显示的时候+1
        int res = JOptionPane.showConfirmDialog(
                null,
                "游戏结束！你的名次是第 "+(currentIndex+1)+" 名\n" +
                        "分数："+Game.getCurrentScore()+"分\n" +
                        "是否保存数据到本地？",
                "游戏结束",JOptionPane.YES_NO_OPTION);
        //若点击“是”，needSaved = true
        needSaved = (res == 0);

        if(needSaved && !MainPanel.isUserTexted()){
            JOptionPane.showMessageDialog(
                    null,
                    "发现你使用的是默认ID，如需保存并修改，请稍后在排行榜下输入栏修改",
                    "提示",JOptionPane.INFORMATION_MESSAGE);
        }

        //若用户需要保存到本地，则保存到本地
        if(needSaved){
            dao.doSave(gameMode);
        }

        //设置滚动
        tableScroll.setViewportView(scoreTable);


        //对特定行的元素进行删除
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
                    if (needSaved) {
                        dao.doSave(gameMode);
                    }
                    model.removeRow(deleteRow);
                    //在表层显示上，再被删除元素一下的数据排名+1
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
                if (needSaved) {
                    dao.doSave(gameMode);
                }
            }
        });
    }


    public JPanel getTablePanel() {
        return tablePanel;
    }

}

package edu.hitsz.UI;

import edu.hitsz.dao.DAOimpl;
import edu.hitsz.dao.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TablePanel {
    private JPanel tablePanel;
    private JPanel topPanel;
    private JTable scoreTable;
    private JPanel bottomPanel;
    private JButton deleteButton;
    private JScrollPane tableScroll;

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
        List<UserData> userDataList = dao.getUserDataList();
        int rank = 0;
        //将数据库中数据填入表中
        for(UserData ud : userDataList){
            Object[] data = {++rank, ud.getID(), ud.getScore(), ud.getTime(), ud.getDateInfo()};
            model.addRow(data);
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
                }
            }
        });
    }


    public JPanel getTablePanel() {
        return tablePanel;
    }
}

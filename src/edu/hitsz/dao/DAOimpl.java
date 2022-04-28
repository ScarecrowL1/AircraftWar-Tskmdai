package edu.hitsz.dao;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DAOimpl implements ScoreDAO {

    private static List<UserData> userDataList = new LinkedList<>();
    String division = "     ";
    //统一分隔

    @Override
    public void doAdd(int score, double time, String ID) {
        UserData userData = new UserData(score, time, ID);
        //生成当次添加时的日期
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userData.setDateInfo(df.format(date));
        userDataList.add(userData);
    }

    @Override
    public void doSave(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./src/userdata.txt"));
            bw.write("*********************   排行榜    *************************");
            bw.newLine();
            bw.write("*********                                        *********");
            bw.newLine();
            bw.write("***** name ************ score *** time ******* date ******");
            bw.newLine();
            bw.write("____________________________________________________________");
            bw.newLine();
            int rank = 0;
            //rank排名，写入时依次赋予
            for (UserData ud : userDataList) {
                StringBuilder sb = new StringBuilder();
                //division为四个空格
                sb.append(++rank).append(division)
                        .append(ud.getID()).append(division)
                        .append(ud.getScore()).append(division)
                        .append(ud.getTime()).append(division)
                        .append(ud.getDateInfo()).append(division);
                bw.write(sb.toString());
                bw.newLine();
                bw.flush();
            }
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void doRead() {
        try {
            File file = new File("./src/userdata.txt");
            if (!file.isFile()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int linenumber = 0;
            while ((line=br.readLine())!=null){
                    //跳过前四行
                    if(linenumber < 4){
                        linenumber++;
                        continue;
                    }
                String[] strArray = line.split(division);
                String ID = strArray[1];
                int score = Integer.parseInt(strArray[2]);
                double time = Double.parseDouble(strArray[3]);
                String dateInfo = strArray[4];
                UserData ud = new UserData(score, time, ID);
                //给之前的数据赋上之前的数据
                ud.setDateInfo(dateInfo);

                //将构建好的userData加入到表中
                userDataList.add(ud);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void doRank() {
        Collections.sort(userDataList);
    }

    @Override
    public void printToConsole() {
        try {
            FileReader fileReader = new FileReader("./src/userdata.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line =bufferedReader.readLine();

            while (line!=null){
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public List<UserData> getUserDataList() {
        return userDataList;
    }
}

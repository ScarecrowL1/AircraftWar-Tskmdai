package edu.hitsz.application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserData implements Comparable<UserData>{
    public UserData(int score, double time) {
        setScore(score);
        setTime(time);
    }
    private String ID = "Kamisato Ayaka";
    private int score;
    private double time;
    private String dateInfo;

    public int getScore() {
        return score;
    }

    public double getTime() {
        return time;
    }

    public String getID() {
        return ID;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public int compareTo(UserData o) {
        return (int) (o.getScore()-score);
    }

    public String getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(String dateInfo) {
        this.dateInfo = dateInfo;
    }
}

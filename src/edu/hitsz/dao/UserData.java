package edu.hitsz.dao;

/**
 * 用户数据
 *
 * @author ScarecrowLi
 * @date 2022/04/28
 */
public class UserData implements Comparable<UserData>{
    public UserData(int score, double time) {
        setScore(score);
        setTime(time);
    }
    public UserData(int score, double time, String ID) {
        setScore(score);
        setTime(time);
        setID(ID);
    }
    private String ID;
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

    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * 按照分数比较
     *
     * @return int
     */
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

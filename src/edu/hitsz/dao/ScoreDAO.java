package edu.hitsz.dao;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据管理接口
 *
 * @author ScarecrowLi
 * @date 2022/04/28
 */
public interface ScoreDAO {
    /**
     * 添加本次数据
     *
     * @param score 分数
     * @param time  时间
     */
    void doAdd(int score, double time, String ID);

    /**
     * 保存到本地
     */
    void doSave();

    /**
     * 从本地中读取
     */
    void doRead();

    /**
     * 进行排名
     */
    void doRank();

    /**
     * 打印到控制台
     */
    void printToConsole();
}

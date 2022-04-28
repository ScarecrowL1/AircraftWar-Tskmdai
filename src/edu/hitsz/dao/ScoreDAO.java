package edu.hitsz.dao;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public interface ScoreDAO {
    void doAdd(int score, double time);
    void doSave();
    void doRead();
    void doRank();
    void printToConsole();
}

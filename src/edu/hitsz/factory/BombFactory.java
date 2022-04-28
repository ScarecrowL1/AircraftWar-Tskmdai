package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BombSupply;

/**
 * 炸弹工厂
 *
 * @author ScarecrowLi
 * @date 2022/04/28
 */
public class BombFactory implements PropFactory{
    @Override
    public AbstractProp createProp(int locationX, int locationY) {
        return new BombSupply(
                locationX,
                locationY,
                3,
                3);
    }
}

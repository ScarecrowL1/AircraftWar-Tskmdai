package edu.hitsz.prop;

public class BombFactory implements PropFactory{
    @Override
    public AbstractProp createProp(int locationX, int locationY) {
        return new BombSupply(
                locationX,
                locationY,
                3,
                1);
    }
}

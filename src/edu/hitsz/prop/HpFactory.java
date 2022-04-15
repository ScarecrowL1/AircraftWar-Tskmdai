package edu.hitsz.prop;

public class HpFactory implements PropFactory{

    @Override
    public AbstractProp createProp(int locationX, int locationY) {
        return new HpSupply(locationX, locationY, 3, 3);
    }

}


package edu.hitsz.prop;

public class FireFactory implements PropFactory{
    @Override
    public AbstractProp createProp(int locationX, int locationY) {
        return new FireSupply(locationX, locationY, 3, 1);
    }
}

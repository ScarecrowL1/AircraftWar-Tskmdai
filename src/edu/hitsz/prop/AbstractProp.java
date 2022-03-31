package edu.hitsz.prop;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

public abstract class AbstractProp extends AbstractFlyingObject {

    public AbstractProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    //道具移动
    //有出界判定

    @Override
    public void forward() {
        super.forward();

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }
}

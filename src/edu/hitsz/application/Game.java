package edu.hitsz.application;

import edu.hitsz.UI.MainPanel;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.dao.DAOimpl;
import edu.hitsz.factory.AircraftFactory;
import edu.hitsz.factory.BossFactory;
import edu.hitsz.factory.EliteFactory;
import edu.hitsz.factory.MobFactory;
import edu.hitsz.prop.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractProp> props;

    AircraftFactory aircraftFactory;
    //飞机工厂

    private int enemyMaxNumber = 5;

    private boolean gameOverFlag = false;
    private int score = 0;
    private int time = 0;
    private static boolean bgmStopFlag = false;

    public static boolean isBgmStopFlag() {
        return bgmStopFlag;
    }

    MusicThread mainBgm = new MusicThread("src/videos/bgm.wav"){
        @Override
        public void run() {
            while (!isGameOver()) {
                super.run();
            }
        }
    };

    private static int currentScore;
    private static double currentTime;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;

    private static boolean canBuildBoss = true;
    private int lastScore = 0;


    public Game() {
        heroAircraft = HeroAircraft.getHeroAircraft();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();

        mainBgm.start();

        //Scheduled 线程池，用于定时任务调度
        executorService = new ScheduledThreadPoolExecutor(1);

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {


        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber) {


                    //生成普通机
                    aircraftFactory = new MobFactory();
                    enemyAircrafts.add(aircraftFactory.createAircraft());

                    //开始决定是否生成精英机
                    double prElite = 0.4;
                    //prElite:产生精英敌机的概率
                    if(Math.random() < prElite){
                        aircraftFactory = new EliteFactory();
                        enemyAircrafts.add(aircraftFactory.createAircraft());
                    }
                    //Boss出现得分阈值，当此时分数大于上次记录时的分数，他们之差大于阈值时，将达成产生boss机的条件之一
                    int bossScoreThreshold = 200;
                    //初始化lastscore==0，每击败一次boss机，这个数值将记录击败boss机之前的分数
                    if(( (score - lastScore) > bossScoreThreshold) && canBuildBoss){
                        aircraftFactory = new BossFactory();
                        enemyAircrafts.add(aircraftFactory.createAircraft());
                        MusicThread bossBgm = new MusicThread("src/videos/bgm_boss.wav"){
                            @Override
                            public void run() {
                                do {
                                    super.run();
                                } while (!canBuildBoss);
                            }
                        };
                        bossBgm.setBoss(true);
                        bossBgm.start();
                        canBuildBoss = false;
                         //场上只能有一架Boss机,当boss机被击落时，canBuildBoss将会变为true
                    }
                }
                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //0325 道具移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();



            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                //存储分数数据用于排行榜接收
                currentScore = score;
                currentTime  = (time/1000.0);
                executorService.shutdown();
                mainBgm.setStopFlag(true);
                new MusicThread("src/videos/game_over.wav").start();
                bgmStopFlag = true;
                gameOverFlag = true;
                synchronized (Main.LOCK) {
                    Main.LOCK.notify();
                }
                System.out.println("Game Over!");
                //打印排行榜到控制台
            }
        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击 done
        for(AbstractAircraft enemy: enemyAircrafts){
            //0325
            enemyBullets.addAll(enemy.shoot());
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propsMoveAction(){
        for(AbstractProp prop : props){
            prop.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄 done
        for(BaseBullet bulletEnemy : enemyBullets){
            if(bulletEnemy.notValid()){
                continue;
            }
            if(heroAircraft.crash(bulletEnemy)){
                //0326  英雄机撞击敌机子弹，损失一定生命值
                heroAircraft.decreaseHp(bulletEnemy.getPower());
                bulletEnemy.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    // 播放敌机受击音效
                    new MusicThread("src/videos/bullet_hit.wav").start();
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        double prProp = 0.8;
                        //prProp为产生道具的概率
                        if(Math.random() < prProp){
                            props.addAll(enemyAircraft.dropProps());
                            //备注：将之前生成道具的代码功能转移到AbstractAircraft类中实现
                        }
                        //当被击毁敌机是Boss时，之后可以重新生成Boss
                        //此时记录lastscore，重新计算是否超过出现阈值
                        if(enemyAircraft instanceof Boss){
                            canBuildBoss = true;
                            lastScore = score;
                            //击败boss加40分
                            score += 40;
                        }
                        score += 10;
                    }
                }

                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for(AbstractProp prop : props){
            if(prop.notValid()) {
                continue;
            }
            if(heroAircraft.crash(prop)){
                //碰撞到炸弹道具
                if(prop instanceof BombSupply){

                    //将场上敌机子弹加入到观察者
                    for(BaseBullet bullet : enemyBullets){
                        if(bullet.notValid()){
                            continue;
                        }
                        ((BombSupply) prop).addBombObject((EnemyBullet)bullet);
                    }

                    //将场上敌机加入到观察者
                    for(AbstractAircraft aircraft : enemyAircrafts){
                        //对于boss机，不生效
                        if(aircraft.notValid() || (aircraft instanceof Boss)){
                            continue;
                        }
                        ((BombSupply) prop).addBombObject((EnemyAircraft)aircraft);
                        //爆炸敌机，+10分
                        score += 10 ;
                    }
                }
                //道具生效
                prop.activate(heroAircraft);
                //0408 将道具生效方法转移到道具类
                prop.vanish();
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
        //删除无效的道具
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);

        paintImageWithPositionRevised(g,props);
        //绘制道具




        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

    public boolean isGameOver() {
        return gameOverFlag;
    }

    public static int getCurrentScore() {
        return currentScore;
    }

    public static double getCurrentTime() {
        return currentTime;
    }

    public static boolean isCanBuildBoss() {
        return canBuildBoss;
    }
}

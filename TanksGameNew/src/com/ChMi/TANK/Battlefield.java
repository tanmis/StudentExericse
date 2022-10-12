package com.ChMi.TANK;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Battlefield extends Panel implements KeyListener ,Runnable {
    //写入一个空的计算器
    private Recode times = null;
    private MyTank tb = null;//主控坦克
    private Vector<Enemytanks> enemytanks = new Vector<>();//敌方坦克属性列表
    private int enemytanksize = 3;
    private Vector<BoomKill> boomKs ;//爆炸效果
    //爆炸效果图
    private Image image;
    private Image image1;
    private Image image2;
    public Battlefield(){
        //初始化计数器
        times = new Recode();
         boomKs = new Vector<>();//爆炸列表初始化 ,当敌人击中坦克加入爆炸效果
        image = Toolkit.getDefaultToolkit().createImage(Panel.class.getResource("/bomb_1.gif"));
        image1 = Toolkit.getDefaultToolkit().createImage(Panel.class.getResource("/bomb_2.gif"));
        image2 = Toolkit.getDefaultToolkit().createImage(Panel.class.getResource("/bomb_3.gif"));
        //初始化敌方坦克
        for (int i = 0; i < enemytanksize; i++) {
            enemytanks.add(new Enemytanks(100 * (i + 1),0));
            enemytanks.get(i).setDirection(1);
            new Thread(enemytanks.get(i)).start();
            Launch ENbullet = new Launch(enemytanks.get(i).getX() + 20 , enemytanks.get(i).getY() + 60,enemytanks.get(i).getDirection());
            enemytanks.get(i).bullet.add(ENbullet);
            new Thread(ENbullet).start();
            enemytanks.get(i).setEnemytanks(enemytanks);
        }
        tb = new MyTank(100,100);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        //绘制我方坦克
        if (tb.issurvive){
            Tankpa(tb.getX(),tb.getY(),g,tb.getDirection(),0);
        }

        //绘制敌方坦克
        for (int i = 0; i < enemytanks.size(); i++) {
            Enemytanks enemytanks = this.enemytanks.get(i);
            if (enemytanks.issurvive){
                Tankpa(enemytanks.getX(),enemytanks.getY(),g,enemytanks.getDirection(),1);
                for (int j = 0; j < enemytanks.bullet.size(); j++) {//绘制敌方子弹射击
                    Launch enbulletone = enemytanks.bullet.get(j);
                    if (enbulletone.issurvive == true&&enbulletone.issurvive){//判断敌人坦克是否存活
                        g.fill3DRect(enbulletone.getX(),enbulletone.getY(),2,2,false);
                    }
                    else {
                        enemytanks.bullet.remove(enbulletone);
                    }
            }



            }
        }
        //我方子弹发射绘制
//        if (tb.getLaunch() != null&& tb.getLaunch().issurvive == true){
//            g.fill3DRect(tb.getLaunch().getX(),tb.getLaunch().getY(),2,2,false);
//        }
        //多发子弹绘制
        for (int i = 0; i < tb.launches.size(); i++) {
            Launch launch = tb.launches.get(i);
            if (launch != null&& launch.issurvive == true){
                g.fill3DRect(launch.getX(),launch.getY(),2,2,false);
            }else {
                tb.launches.remove(launch);
            }

        }
//        绘制爆炸效果
        for (int i = 0; i < boomKs.size(); i++) {
            BoomKill boomone = boomKs.get(i);
            //判断生命值，且处理生命值
            if (boomone.issurvive != false){
                if (boomone.life > 6){
                    g.drawImage(image,boomone.x,boomone.y,60,60,this);
                }else if (boomone.life > 4){
                    g.drawImage(image1,boomone.x,boomone.y,this);
                }else {
                    g.drawImage(image2,boomone.x,boomone.y,this);
                }
                new Thread(boomone).start();

            }
            boomKs.remove(boomone);

        }
        showachivement(g);
    }
    //玩家成绩显示方块
    public void showachivement(Graphics g){
        g.setColor(Color.black);
        g.setFont(new Font("宋体",Font.BOLD,25));
        g.drawString("敌方坦克击毁数量",1034,200);
        g.setColor(Color.cyan);
        Tankpa(1032,300,g,2,1);
        g.setColor(Color.black);
        g.drawString(times.getTankskilling() + "",1100,330);


    }
    public void Tankpa(int x,int y,Graphics g,int direction,int type){
        switch (type){
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.pink);
                break;
        }
        //direction (0：向上，1：向下 ，2： 向左 3：向右）
        switch (direction){
            case 0:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+ 10 ,y + 10,20,40,false);
                g.fill3DRect(x + 30,y,10,60,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y,x+20,y+30);
                break;
            case 1:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+ 10 ,y + 10,20,40,false);
                g.fill3DRect(x + 30,y,10,60,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y + 30,x+20,y + 60);
               break;
            case 2:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+ 10 ,y + 10,40,20,false);
                g.fill3DRect(x ,y + 30,60,10,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y + 20,x - 10,y + 20);
                break;
            case 3:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+ 10 ,y + 10,40,20,false);
                g.fill3DRect(x ,y + 30,60,10,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y + 20,x+60,y + 20);
                break;



        }
    }
    //判断主控坦克是否击中敌方
    public void BombedEnemy(Launch cannonball ,Enemytanks emt){
        switch (emt.getDirection()){
            case 0:
            case 1:
                BoomKill boomKill1 = new BoomKill(emt.getX(), emt.getY());
                if (cannonball.getX() > emt.getX()&&cannonball.getX() < emt.getX()+40
                        &&cannonball.getY() > emt.getY()&&cannonball.getY()< emt.getY()+ 60){
                    cannonball.issurvive = false;
                    emt.issurvive = false;
                    //加入坦克列表
                    boomKs.add(boomKill1);
                    enemytanks.remove(emt);
                    //计数器计数坦克死亡数量
                    if (times!= null){
                        times.addtanksnum();
                    }

                }
                break;
            case 2:
            case 4:
                BoomKill boomKill2 = new BoomKill(emt.getX(), emt.getY());
                if (cannonball.getX()>=emt.getX()&&cannonball.getX() <=emt.getX()+60
                        &&cannonball.getY() >=emt.getY()&&cannonball.getY()<=emt.getY()+ 40){
                    cannonball.issurvive = false;
                    emt.issurvive = false;
                    boomKs.add(boomKill2);
                    enemytanks.remove(emt);
                    if (times!=null){
                        times.addtanksnum();
                    }

                }
                break;
        }

    }
    public void Mytankdestroyed(Launch cannonball ,MyTank emt){
        switch (emt.getDirection()){
            case 0:
            case 1:
                BoomKill boomKill1 = new BoomKill(emt.getX(), emt.getY());
                if (cannonball.getX() > emt.getX()&&cannonball.getX() < emt.getX()+40
                        &&cannonball.getY() > emt.getY()&&cannonball.getY()< emt.getY()+ 60){
                    cannonball.issurvive = false;
                    emt.issurvive = false;
                    //加入坦克列表
                    boomKs.add(boomKill1);
                }
                break;
            case 2:
            case 4:
                BoomKill boomKill2 = new BoomKill(emt.getX(), emt.getY());
                if (cannonball.getX()>=emt.getX()&&cannonball.getX() <=emt.getX()+60
                        &&cannonball.getY() >=emt.getY()&&cannonball.getY()<=emt.getY()+ 40){
                    cannonball.issurvive = false;
                    emt.issurvive = false;
                    boomKs.add(boomKill2);
                }
                break;
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        控制自己坦克行动
        //向上移动
        if (e.getKeyCode() == KeyEvent.VK_UP){
            if (tb.getY()  > 0){
                tb.setDirection(0);
                tb.moveup();
            }

        }
        //向下移动
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            if (tb.getY() + 60 < 750){
                tb.setDirection(1);
                tb.movedown();

            }

        }
        //向左移动
        else if (e.getKeyCode() ==  KeyEvent.VK_LEFT){
            if (tb.getX() > 0){
                tb.setDirection(2);
                tb.movelietf();
            }





        }
        //向右移动；
        else if (e.getKeyCode() == KeyEvent .VK_RIGHT){
            if (tb.getX() + 60 < 1000){
                tb.setDirection(3);
                tb.moveright();
            }

        }
        //如果按下j 发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J){
            //发射一颗子弹情况
//            if (tb.getLaunch() == null||!(tb.getLaunch().issurvive)){
//                tb.Shotpress();
//            }
            //发射多颗子弹
            tb.Shotpress();

        }
        repaint();


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (tb.issurvive&&tb.getLaunch() != null){
                for (int i = 0; i < enemytanks.size(); i++) {//检查我的子弹是否击中敌方坦克
                    Enemytanks enemytankone = this.enemytanks.get(i);
                    for (int j = 0; j < tb.launches.size(); j++) {
                        Launch launch = tb.launches.get(j);
                        BombedEnemy(launch,enemytankone);//调用方法

                    }
                }
            }
            //判断我方是否被击中
            for (int i = 0; i < enemytanks.size(); i++) {
                Enemytanks enemytankone = enemytanks.get(i);
                for (int j = 0; j < enemytankone.bullet.size(); j++) {
                    Launch launch = enemytankone.bullet.get(j);
                   Mytankdestroyed(launch,tb);
                }

            }

            this.repaint() ;
        }
    }
}

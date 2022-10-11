package com.chamifj.tankedz.Tankgme03;

import java.util.Vector;

public class Enemytanks extends Tank implements Runnable {
    //    子弹集合
    Vector<Launch> bullet = new Vector<>();
    Vector<Enemytanks> enemytanks = new Vector<>();
    boolean issurvive = true;

    public Enemytanks(int x, int y) {
        super(x, y);
    }

    public void setEnemytanks(Vector<Enemytanks> enemytanks) {
        this.enemytanks = enemytanks;
    }


    @Override
    public void run() {

        while (true) {
            if (issurvive && bullet.size() == 0) {
                Launch launchone = null;
                switch (getDirection()) {
                    case 0:
                        launchone = new Launch(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        launchone = new Launch(getX() + 20, getY() + 60, 1);
                        break;
                    case 2://向左
                        launchone = new Launch(getX(), getY() + 20, 1);
                        break;
                    case 3://向右
                        launchone = new Launch(getX() + 60, getY() + 20, 1);
                        break;
                }
                bullet.add(launchone);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                new Thread(launchone).start();
            }
//            自由移动控制
            switch (getDirection()) {
                case 0://向上移动
                    for (int i = 0; i < (int) (Math.random() * 31); i++) {
                        //行动条件判断
                        if (getY() + 10 > 0&&(!notaddtanks())) {
                            moveup();
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 1://向下移动

                    for (int i = 0; i < (int) (Math.random() * 31); i++) {
                        if (getY() + 60 < 750&&(!notaddtanks())) {
                            movedown();

                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2://向左移动

                    for (int i = 0; i < (int) (Math.random() * 31); i++) {
                        if (getX() + 10 > 0&&(!notaddtanks())) {
                            movelietf();

                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < (int) (Math.random() * 50); i++) {
                        if (getX() + 60 < 1000&&(!notaddtanks())) {
                            moveright();
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setDirection((int) (Math.random() * 4));
        }

    }

    //    判断坦克是否重叠，避免坦克重叠程序
    public Boolean notaddtanks() {
        for (int i = 0; i < enemytanks.size(); i++) {
            Enemytanks one = enemytanks.get(i);
            if (one != this) {
                switch (getDirection()) {
                    //向上
                    case 0:
                        switch (one.getDirection()) {
                            //敌方坦克向上方向判断
                            case 0:
                            case 1:
                                if (this.getX() >= one.getX() && this.getX() <= one.getX() + 40
                                        && this.getY() >= one.getY() && this.getY() <= one.getY() + 60) {
                                    return true;
                                }
                                break;
                            case 2:
                            case 3:
                                if (this.getX() + 40 >= one.getX() && this.getX() + 40 <= one.getX() + 60
                                        && this.getY() >= one.getY() && this.getY() <= one.getY() + 40) {
                                    return true;
                                }
                                break;
                        }
                        break;
                    case 1://向下
                        switch (one.getDirection()) {
                            //敌方坦克向下方向判断
                            case 0:
                            case 1:
                                if (this.getX() >= one.getX() && this.getX() <= one.getX() + 40
                                        && this.getY() + 60 >= one.getY() && this.getY() + 60 <= one.getY() + 60) {
                                    return true;
                                }
                                if (this.getX() + 40  >= one.getX() && this.getX() + 40<= one.getX() + 40
                                        && this.getY() + 60>= one.getY() && this.getY() + 60 <= one.getY() + 60) {
                                    return true;
                                }
                                break;
                            case 2:
                            case 3:
                                if (this.getX()  >= one.getX() && this.getX() <= one.getX() + 60
                                        && this.getY() + 60 >= one.getY() && this.getY() + 60 <= one.getY() + 40) {
                                    return true;
                                }
                                if (this.getX() + 40 >= one.getX() && this.getX() + 40 <= one.getX() + 60
                                        && this.getY() + 60 >= one.getY() && this.getY() + 60 <= one.getY() + 40) {
                                    return true;
                                }

                                break;
                        }
                        break;
                    case 2://go to left
                        switch (one.getDirection()) {
                            case 0:
                            case 1:
                                if (this.getX() >= one.getX() && this.getX() <= one.getX() + 40
                                        && this.getY() >= one.getY() && this.getY() <= one.getY() + 60) {
                                    return true;
                                }
                                if (this.getX()  >= one.getX() && this.getX()  <= one.getX() + 40
                                        && this.getY() + 40 >= one.getY() && this.getY() + 40 <= one.getY() + 60) {
                                    return true;
                                }
                                break;
                            case 2:
                            case 3:
                                if (this.getX() >= one.getX() && this.getX() <= one.getX() + 60
                                        && this.getY() >= one.getY() && this.getY() <= one.getY() + 40) {
                                    return true;
                                }
                                if (this.getX() >= one.getX() && this.getX()  <= one.getX() + 60
                                        && this.getY() + 40 >= one.getY() && this.getY() + 40 <= one.getY() + 40) {
                                    return true;
                                }

                        }
                        break;
                    case 3://go right
                        switch (one.getDirection()) {
                            //敌人坦克上下
                            case 0:
                            case 1:
                                if (this.getX() + 60 >= one.getX() && this.getX() +60<= one.getX() + 40
                                        && this.getY() >= one.getY() && this.getY() <= one.getY() + 60) {
                                    return true;
                                }
                                if (this.getX() + 60 >= one.getX() && this.getX() + 40 <= one.getX() + 40
                                        && this.getY() + 40 >= one.getY() && this.getY() + 60 <= one.getY() + 60) {
                                    return true;
                                }
                                break;
                            case 2:
                            case 3:
                                //左右
                                if (this.getX() + 60 >= one.getX() && this.getX() + 60<= one.getX() + 60
                                        && this.getY() >= one.getY() && this.getY() <= one.getY() + 40) {
                                    return true;
                                }
                                if (this.getX() + 60 >= one.getX() && this.getX() + 40 <= one.getX() + 60
                                        && this.getY() + 40 >= one.getY() && this.getY() + 60 <= one.getY() + 40) {
                                    return true;
                                }
                        }
                        break;
                }
            }



        }

return false;
    }
}



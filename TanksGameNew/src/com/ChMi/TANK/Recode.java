package com.ChMi.TANK;


import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Recode {
    private static final String tankfilladdress = "src:\\Tanksparatmeters";//写死地址方便读取
    private  int Tankskilling = 0 ;//坦克死亡数量
    private static BufferedWriter wput;//写入
    public void makedataRandW(){
        try {
            wput = new BufferedWriter(new FileWriter(tankfilladdress));
            wput.write(getTankskilling());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (wput != null){
                    wput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public  int getTankskilling() {
        return Tankskilling;
    }
    public  void setTankskilling(int tankskilling) {
        Tankskilling = tankskilling;
    }
    public void addtanksnum(){
        Tankskilling++;
    }
    public static void main(String[] args) {

    }
}

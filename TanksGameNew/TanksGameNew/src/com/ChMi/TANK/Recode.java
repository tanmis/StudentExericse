package com.ChMi.TANK;


import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Recode {
    private static  String tankfilladdress = null;//写死地址方便读取
    private static int Tankskilling = 0 ;//坦克死亡数量
    private static BufferedWriter wput = null;//写入
    public static void makedataRandW(){
        try {
            tankfilladdress = "E:\\appdesign\\example(all)\\java\\idea_java.projeck\\TanksGameNew\\src\\record.txt";
            wput = new BufferedWriter(new FileWriter(tankfilladdress));
            String s = Tankskilling + "";//转换
            wput.write(s);
            System.out.println(s);
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

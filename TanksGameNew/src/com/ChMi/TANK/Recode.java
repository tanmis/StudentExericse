package com.ChMi.TANK;


import org.junit.Test;

import java.io.*;
import java.util.Vector;

public class Recode {
    private static Vector<Node>nodes = new Vector<>();//辅助数据载入
    private static Vector<Enemytanks> enemytanks = new Vector<>();//得到敌方坦克数据
    private static  String tankfilladdress = "E:\\appdesign\\example(all)\\java\\idea_java.projeck\\TanksGameNew\\src\\record.txt";//写死地址方便读取
    private static int Tankskilling = 0 ;//坦克死亡数量
    private static BufferedWriter wput = null;//写入
    private static BufferedReader brout = null;
    private static int save;//临时数据
    //输出外部数据给于支持
    @Test
    public static Vector<Node> makedataWandR(){
        try {

            brout = new BufferedReader(new FileReader(tankfilladdress));
            save =Integer.parseInt( brout.readLine());//恢复死亡数据
            Tankskilling = save;
            String line = "";
            while ((line = brout.readLine())!=null){
                String [] xyz = line.split(" ");
                nodes.add(new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                brout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }
    //读取外部数据进行存储
    public static void makedataRandW(){
        try {
//            tankfilladdress = "E:\\appdesign\\example(all)\\java\\idea_java.projeck\\TanksGameNew\\src\\record.txt";
            wput = new BufferedWriter(new FileWriter(tankfilladdress));
            String s = Tankskilling + "";//转换
            wput.write(s +"\r\n");
//            tankfilladdress = "E:\\appdesign\\example(all)\\java\\idea_java.projeck\\TanksGameNew\\src\\oldtanksgame.txt";
            for (int i = 0; i < enemytanks.size(); i++) {
                Enemytanks one = Recode.enemytanks.get(i);
                if (one.issurvive){
                    wput.write(one.getX()+" "+one.getY()+" "+one.getDirection()+"\r\n");
                }

            }
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
    public static void setEnemytanks(Vector<Enemytanks> enemytanks) {
        Recode.enemytanks = enemytanks;
    }

    public  int getSave() {//临时数据存储点
        return save;
    }
}

package com.chater011.houmwork.java10;

public class Frock {
  private static int currentnum = 100000;
  private  static int seialnumber ;
    public Frock() {
        seialnumber = getCurrentnum();
    }

    public static int getCurrentnum() {
        return currentnum += 100 ;
    }

    public  int getSeialnumber() {
        return seialnumber ;
    }
}

package com.chater011.houmwork.java10;

public enum Color implements ues {
    RED(255,0,0),BLUE(0,255,0),
    BLACK(0,0,0),YELLOW(255,0,255),
    GREEN(0,0,255);
    private double redvalue;
    private double bluevalue;
    private double greenvalue;

    Color(double redvalue, double bluevalue, double greenvalue) {
        this.redvalue = redvalue;
        this.bluevalue = bluevalue;
        this.greenvalue = greenvalue;
    }

    @Override
    public void show() {
        System.out.println("red"+redvalue+"\t"+"blue"+bluevalue+"\t"+"green"+greenvalue);
    }


}

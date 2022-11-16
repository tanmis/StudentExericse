package com.chater011.houmwork.java10;

public class colortest {
    public static void main(String[] args) {
        Color color = Color.BLUE;
        color.show();
        switch (color){
            case RED:
                System.out.println("匹配到红色");
                break;
            case BLUE:
                System.out.println("蓝色");
                break;
            case BLACK:
                System.out.println("黑色");
                break;
                case YELLOW:
                    System.out.println("黄色");
                    break;
            default:
                System.out.println("没有匹配到");
                break;
        }
    }
}

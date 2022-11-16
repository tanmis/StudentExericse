package com.chater011.houmwork.abnormal;

public class test01 {
    public static void main(String[] args) {
        int num = 1;
        int n = 0;
        int sum;
        try {
            sum = num/n;
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("你好");
    }
}

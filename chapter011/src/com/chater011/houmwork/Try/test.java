package com.chater011.houmwork.Try;

public class test {
    public static void main(String[] args) {
        System.out.println();
    }
    static int fun(int x,int y,int z){
        return fun(x,fun(y,z));
    }
    static int fun(int x ,int y){
        if (x >y) return x;
        else return x;
    }
}

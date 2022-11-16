package com.chater011.houmwork.java10;

public class Showname {
    private final static String name = "王小明";
    public void a(){
        class b{
            final private static String name = "踢腿";
            public void show(){
                System.out.println("输出局部内部类 " +name +"\t"+ "全局变量"+Showname.this.name);
            }

        }
        new b().show();
//        System.out.println("输出局部内部类" + b.name);
//        System.out.println("全局变量"+name);
    }
}
class m{
    public static void main(String[] args) {
        new Showname().a();

    }
}

package com.chater011.houmwork;

public class Enumexrtcise {
    public static void main(String[] args) {
        week[] values = week.values();

        for (week week:values){
            System.out.println(week);
        }

    }
}
enum week{
    Monday("星期一"),Tuesday("星期二"),Wednesday("星期三"),Thursday("星期四"),Friday("星期五"),Saturday("星期六"),Sunday("星期天")
    ;
    private String name;

    week(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
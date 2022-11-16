package com.chater011.houmwork.abnormal;

import java.util.Scanner;

public class Test02 {
    public static void main(String[] args) {
        int key = - 1;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("请输入一个整数");
                String next = scanner.next();
                System.out.println("整数" + Integer.parseInt(next));
                key = 0;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + "错误，请输入整数");
            }

        }while (key == -1);

    }
}

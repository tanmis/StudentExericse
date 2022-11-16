package com.chater011.houmwork.java10;

public class PhoneTest {
    public static void main(String[] args) {
        CellPhone cellPhone = new CellPhone();
        cellPhone.work(new Calculator() {
            @Override
            public double operation(double n1, double n2) {
                return n1 + n2;
            }
        },10,8);
    }
}

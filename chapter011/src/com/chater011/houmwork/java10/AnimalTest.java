package com.chater011.houmwork.java10;

public class AnimalTest {
    public static void main(String[] args) {
        Animal animal [] = new Animal[2];
        animal[0] = new dog();
        animal[1] = new cat();
        for (int i = 0; i < 2; i++) {
            new AnimalTest().test1(animal[i]);
        }
    }
    public void test1(Animal animal){
        animal.Shout();
    }
}

package com.chater011.houmwork.java10;

public class Toolues {
    public void ues(Vehicle vehicle){
        vehicle.work();
    }
}
class person{
   private String name ="唐僧";
    public static void main(String[] args) {
        boat boat = new boat();
        cart cart = new cart();
        new Toolues().ues(boat);
        new Toolues().ues(cart);
    }
}

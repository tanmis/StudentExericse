package com.chater011.houmwork.java10;

import java.util.Scanner;

public class Car {
    private  double temperature;
    class AirConditioning{
          public double AI(){
           return temperature;
       }
       public final void Hair(){
           if (new AirConditioning().AI() > 40){
               System.out.println("吹冷气");
           }
           else if (new AirConditioning().AI() < 0){
               System.out.println("吹热气");
           }else {
               System.out.println("空调关闭");
           }
       }
   }
   public void ues(double temperature){
        this.temperature = temperature;
       AirConditioning airConditioning = new AirConditioning();
       airConditioning.Hair();

   }
}

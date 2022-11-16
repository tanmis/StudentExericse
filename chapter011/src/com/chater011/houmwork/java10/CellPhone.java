package com.chater011.houmwork.java10;

public class CellPhone {

   public void work(Calculator calculator,double n1,double n2){
       double results = calculator.operation(n1, n2);
       System.out.println("结果· " + results);
   }
}

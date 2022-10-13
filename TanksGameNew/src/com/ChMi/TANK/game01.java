package com.ChMi.TANK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class game01 extends JFrame {
    private int count = 0;
    private Battlefield gm = null;

    public static void main(String[] args) {
        game01 game01 = new game01();

    }
    public game01(){

        gm = new Battlefield();
        this.add(gm);
        new Thread(gm).start();
        this.addKeyListener(gm);
        this.setSize(1300,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用的这个
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              Recode.makedataRandW();
            }
        });

    }
}

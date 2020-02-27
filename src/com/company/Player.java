package com.company;

import java.util.Scanner;
import java.util.Vector;

public class Player {

    Player(int xPos, int yPos) {
        x = xPos;
        y = yPos;
    }

    Player(int xPos, int yPos, String Name,int[] mapLen) {
        x = xPos;
        y = yPos;
        name = Name;
        mapLength = mapLen;
    }

    public Vector<Street> streetsOwned = new Vector();
    Scanner input = new Scanner(System.in);
    public int x;
    public int y;
    int balance = 2000;
    public String name;

    int[] mapLength = new int[4];


    public void move(int distance) {
        int nextX = x + distance;
        int mapLen = this.mapLength[y];
        if (nextX >= mapLen) {
            int nextLength = y + 1 > 3 ? this.mapLength[0] : this.mapLength[y + 1];
            if (nextX - mapLen >= nextLength) {
                x = nextX - mapLen - nextLength;
                y += 2;
                if (y > 3) y -= 4;
            } else {
                x = nextX - mapLen;
                y++;
                if (y > 3) y = 0;
            }
        } else x += distance;
    }




}

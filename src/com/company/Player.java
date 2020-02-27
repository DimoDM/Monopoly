package com.company;

import java.util.Vector;

public class Player {

    Player(int xPos, int yPos){
        x = xPos;
        y = yPos;
    }
    Player(int xPos, int yPos, String Name){
        x = xPos;
        y = yPos;
        name = Name;
    }

    private Vector<Street> streetsOwned;
    public int x;
    public int y;
    public String name;
    Map space = new Map();

    void showCoordinates(){
        System.out.println("x: " + x + " y: " + y);
    }

    void move(int distance){
        int nextX = x + distance;
        int mapLength = space.map[y].length;
        if (nextX >= mapLength) {
            int nextLength = y + 1 > 3 ? space.map[0].length : space.map[y + 1].length;
            if (nextX - mapLength >= nextLength) {
                x = nextX - mapLength - nextLength;
                y += 2;
                if (y > 3) y -= 4;
            } else {
                x = nextX - mapLength;
                y ++;
                if (y > 3) y = 0;
            }
        }else x += distance;
    }

    void chooseAction(){

    }

    void buyStreet(){
        System.out.println("this street is now mine");
    }

    void printStreets(){

    }

    void payBills(){

    }


}

package com.company;

import java.util.Scanner;
import java.util.Vector;

public class Player {

    Player(int xPos, int yPos) {
        x = xPos;
        y = yPos;
    }

    Player(int xPos, int yPos, String Name, int[] mapLen) {
        x = xPos;
        y = yPos;
        name = Name;
        mapLength = mapLen;
        tag = Name.charAt(0);
    }

    public Vector<Street> streetsOwned = new Vector();
    Scanner input = new Scanner(System.in);
    public int x;
    public int y;
    public char tag;
    public boolean isInJail = false;
    public boolean isRentPaid = false;
    private int salary = 200;
    int balance = 1800;
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
                if (y > 3) {
                    y -= 4;
                    getSalary();
                }
            } else {
                x = nextX - mapLen;
                y++;
                if (y > 3) {
                    y = 0;
                    getSalary();
                }
            }
        } else x += distance;
        if (isGoingToJail()) goToJail();
    }


    public boolean haveMortgagedStreet() {
        for (int i = 0; i < streetsOwned.size(); i++) {
            if (streetsOwned.get(i).mortgaged) return true;
        }
        return false;
    }

    private boolean isGoingToJail() {
        return x == 0 && y == 3;
    }

    private void goToJail() {
        System.out.println("You will go to jail and pass one move");
        x = 0;
        y = 1;
        isInJail = true;
    }

    private void getSalary() {
        System.out.println("You reached start, so you'll get salary");
        balance += salary;
    }


}

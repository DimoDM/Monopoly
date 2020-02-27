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

    private Vector<Street> streetsOwned = new Vector();
    Scanner input = new Scanner(System.in);
    public int x;
    public int y;
    int balance = 2000;
    public String name;

    int[] mapLength = new int[4];

    void showCoordinates() {
        System.out.println("x: " + x + " y: " + y);
    }

    void move(int distance) {
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

    void chooseAction(Street street) {
        int optionFourFlag;
        if(street.hasStreet && street.ownedBy.equals("")) {
            System.out.print("Enter 3 for buying this street. Street's price is " + street.price + "$");
            optionFourFlag = 1;
        }
        else if(street.hasStreet && street.ownedBy != name) {
            System.out.println("Enter 3 to pay rent. Rent will cost " + street.rent[street.numOfHouses]);
            optionFourFlag = 2;
        }
        System.out.println("Enter 1 to sell street to the bank");
        //System.out.println("Enter 2 to build houses");

        
    }

    void doAction(int flag){
        int option = input.nextInt();
        switch (option) {

        }
    }

    void buyStreet(Street street) {
        balance -= street.price;
        System.out.println("player balance is " + balance + "$ now");
        streetsOwned.add(street);
    }


    void printStreets() {
        for (int i = 0; i < streetsOwned.size(); i++) {
            System.out.print((i + 1) + " ");
            streetsOwned.get(i).printStreet();
        }
    }

    void payBills() {

    }


}

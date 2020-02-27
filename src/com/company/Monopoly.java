package com.company;

import java.util.Scanner;

public class Monopoly {

    Monopoly() {
    }

    Player[] player;
    Map map1 = new Map();
    Scanner input = new Scanner(System.in);
    private int numOfPlayers;
    private int index = 0;
    boolean isRunning = true;

    public void init() {
        System.out.print("How many players will play? : ");
        numOfPlayers = input.nextInt();
        player = new Player[numOfPlayers];

        for (int i = 1; i <= numOfPlayers; i++) {
            System.out.print("Name of player " + i + " is: ");
            String name = input.next();
            player[i - 1] = new Player(10, 3, name);
        }

    }

    public void update() {
        System.out.println(player[index].name + " is on turn");

        for (int i = 0; i < numOfPlayers; i++) map1.map[player[i].y][player[i].x] = i + 1;
        map1.print();
        for (int i = 0; i < numOfPlayers; i++) map1.map[player[i].y][player[i].x] = 0;

        int dice = input.nextInt();
        player[index].move(dice);

        Street currentStreet = new Street();
        currentStreet = map1.streets[player[index].x][player[index].y];

        if(currentStreet.hasStreet && currentStreet.ownedBy.equals("")){
            player[index].buyStreet();
        }

        index++;
        if (index > numOfPlayers - 1) index = 0;
    }

}

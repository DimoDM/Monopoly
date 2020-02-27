package com.company;

import java.util.Scanner;

public class Monopoly {

    Monopoly() {
    }

    Player[] player;
    Map board = new Map();
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
            player[i - 1] = new Player(10, 3, name,
                    new int[]{board.map[0].length, board.map[1].length, board.map[2].length, board.map[3].length});
        }

    }

    public void update() {
        System.out.println(player[index].name + " is on turn");

        for (int i = 0; i < numOfPlayers; i++) board.map[player[i].y][player[i].x] = i + 1;
        board.print();
        for (int i = 0; i < numOfPlayers; i++) board.map[player[i].y][player[i].x] = 0;

        int dice = input.nextInt();
        player[index].move(dice);
        
        Street currentStreet;
        currentStreet = board.streets[player[index].y][player[index].x];
        player[index].chooseAction(currentStreet);

        actions(player[index]);


        index++;
        if (index > numOfPlayers - 1) index = 0;
    }

    private void actions(Player player) {
        
    }


}

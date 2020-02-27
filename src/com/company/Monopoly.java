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


        printMapAndPlayers();
        int dice = input.nextInt();
        player[index].move(dice);

        printMapAndPlayers();
        playerActions();


        System.out.println("-----------------------------------------");
        endTurn();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void printALotsOfRows() {
        System.out.println("\n\n\n\n");
    }

    private void printMapAndPlayers(){
        printALotsOfRows();
        for (int i = 0; i < numOfPlayers; i++) board.map[player[i].y][player[i].x] = i + 1;
        board.print();
        for (int i = 0; i < numOfPlayers; i++) board.map[player[i].y][player[i].x] = 0;
    }

    private void endTurn(){
        index++;
        if (index > numOfPlayers - 1) index = 0;
    }

    private void playerActions() {
        Street currentStreet;
        currentStreet = board.streets[player[index].y][player[index].x];

        if (currentStreet.ownedBy.equals("")) {
            chooseAction(currentStreet, player[index]);
        } else if (currentStreet.hasStreet) {
            int i = getIndexOfOwner(currentStreet);
            Street street = player[i].streetsOwned.get(player[i].streetsOwned.indexOf(currentStreet));
            chooseAction(street, player[index]);
        }
    }

    private void chooseAction(Street street, Player player) {
        System.out.println("Your balance is " + player.balance + "$");
        System.out.println("Enter 1 to mortgage street");
        System.out.println("Enter 2 to see all owned streets");

        int optionFlag = 0;
        if (street.hasStreet && street.ownedBy.equals("")) {
            System.out.println("Enter 3 for buying this street. Street's price is " + street.price + "$");
            optionFlag = 1;
        } else if (street.hasStreet && street.ownedBy != player.name) {
            System.out.println("Enter 3 to pay rent. Rent will cost " + street.rent[street.numOfHouses]);
            optionFlag = 2;
        }

        if(hasCompletedStreets())
        if (optionFlag == 0) System.out.println("Enter 3 to build houses");
        else System.out.println("Enter 4 to build houses");

        System.out.println("Enter 0 to end turn");
        doAction(optionFlag, street);
    }

    private void doAction(int flag, Street street) {
        int option = input.nextInt();
        switch (option) {
            case 1:
                mortgageStreet();
                playerActions();
                break;
            case 2:
                printStreets();
                playerActions();
                break;
            case 3:
                if (flag == 1) {
                    buyStreet(street);
                    playerActions();
                    break;
                }
                else if (flag == 2) {
                    payRent(street);
                    playerActions();
                    break;
                }
            case 4:
                buildHouses();
                playerActions();
                break;
            case 0:
                break;

        }
    }

    private int getIndexOfOwner(Street street) {
        for (int i = 0; i < numOfPlayers; i++) {
            if (street.ownedBy.equals(player[i].name)) {
                return i;
            }
        }
        return index;
    }

    private void mortgageStreet() {
        System.out.println("Choose street to mortgage");
        printStreets();
        System.out.println("0 exit");
        int choose = input.nextInt();
        choose--;
        if(choose == - 1) return;

        if (!player[index].streetsOwned.get(choose).mortgaged) {
            player[index].streetsOwned.get(choose).mortgaged = true;
            board.streets[player[index].streetsOwned.get(choose).y][player[index].streetsOwned.get(choose).x].mortgaged = true;
            player[index].balance += player[index].streetsOwned.get(choose).mortgage;
            System.out.println("You mortgaged this street successfully. Your balance is " + player[index].balance + "$");
        } else System.out.println("This street is already mortgaged.");
    }

    private void printStreets() {
        for (int i = 0; i < player[index].streetsOwned.size(); i++) {
            System.out.print((i + 1) + " ");
            player[index].streetsOwned.get(i).printStreet();
        }
    }

    private void buyStreet(Street street) {
        player[index].balance -= street.price;
        player[index].streetsOwned.add(street);
        board.streets[street.y][street.x].ownedBy = player[index].name;
        checkForCompletedStreets();
    }

    private void buildHouses() {

        int[] streets = new int[25];
        int indexForStreetsArr = 0;
        Street playerStreets;
        for (int i = 0; i < player[index].streetsOwned.size(); i++) {
            playerStreets = player[index].streetsOwned.get(i);
            if(playerStreets.fullStreet && !playerStreets.mortgaged) {

                streets[indexForStreetsArr] = i;
                System.out.print((indexForStreetsArr + 1) + " ");
                playerStreets.printStreet();
                indexForStreetsArr++;
            }
        }

        System.out.println("Choose street to build or 0 to exit: ");
        int choose = input.nextInt();
        choose--;
        if(choose == -1) return;
        System.out.println("How many houses you will build ?");
        int numOfHouses = input.nextInt();

        playerStreets = player[index].streetsOwned.get(streets[choose]);
        if(player[index].balance - playerStreets.housePrice * numOfHouses < 0){
            player[index].balance -= playerStreets.housePrice * numOfHouses;
            player[index].streetsOwned.get(streets[choose]).numOfHouses += numOfHouses;
            board.streets[playerStreets.y][playerStreets.x].numOfHouses += numOfHouses;
        }

    }

    private void checkForCompletedStreets() {
        String color = player[index].streetsOwned.lastElement().color;
        int counter = 1;
        int maxCounter = color.equals("Brown") || color.equals("blueDark") ? 2 : 3;
        for (int i = 0; i < player[index].streetsOwned.size() - 1; i++) {
            if(color.equals(player[index].streetsOwned.get(i).color)){
                counter++;
                if(counter == maxCounter) break;
            }
        }
        if(counter == maxCounter) {
            for (int i = 0; i < player[index].streetsOwned.size(); i++) {
                if(color.equals(player[index].streetsOwned.get(i).color)){
                    player[index].streetsOwned.get(i).fullStreet = true;
                }
            }
        }
    }

    private boolean hasCompletedStreets() {
        for (int i = 0; i < player[index].streetsOwned.size(); i++) {
            if(player[index].streetsOwned.get(i).fullStreet) return true;
        }
        return false;
    }

    private void payRent(Street street) {
        int i = getIndexOfOwner(street);
        player[i].balance += street.rent[street.numOfHouses];
        player[index].balance -= street.rent[street.numOfHouses];

    }


}

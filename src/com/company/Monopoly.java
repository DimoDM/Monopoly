package com.company;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Monopoly {


    Monopoly() {
    }

    Vector<Player> player = new Vector();
    Map board = new Map();
    Scanner input = new Scanner(System.in);
    private int numOfPlayers;
    private int index = 0;
    boolean isRunning = true;
    private int turn = 0;

    public void init() {
        System.out.print("How many players will play? : ");
        numOfPlayers = input.nextInt();

        for (int i = 1; i <= numOfPlayers; i++) {
            System.out.print("Name of player " + i + " is: ");
            String name = input.next();
            player.add(new Player(10, 3, name,
                    new int[]{board.map[0].length, board.map[1].length, board.map[2].length, board.map[3].length}));
        }

    }

    public void update() {
        checkForWinner();
        System.out.println(player.get(index).name + " is on turn");
/*
        if(!player.get(index).isInJail) {
            System.out.println("Press enter to roll dices");
            promptEnterKey();
            int dice = rollAndReturnDices();
            player.get(index).move(dice);
        }*/
        //else player.get(index).isInJail = false;

        int dice = input.nextInt();
        player.get(index).move(dice);

        printMapAndPlayers();
        playerActions();


        System.out.println("-----------------------------------------");

        endTurn();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void checkForWinner(){
        if(numOfPlayers == 1) {
            System.out.println(player.get(index).name + " is winner. Congratulation!");
            isRunning = false;
        }
    }


    private int rollAndReturnDices() {
        Random rand = new Random();
        int[] dices = {rand.nextInt(5) + 1, rand.nextInt(5) + 1};
        System.out.println("You roll " + dices[0] + " and " + dices[1] + ". You move " + (dices[0] + dices[1]) + " steps forward");
        if(dices[0] == dices[1]){
            System.out.println("You will be again");
            turn = -1;
        }
        return dices[0] + dices[1];
    }

    private void printALotsOfRows() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    private void printMapAndPlayers(){
        for (int i = 0; i < numOfPlayers; i++) board.map[player.get(i).y][player.get(i).x] = i + 1;
        board.print();
        for (int i = 0; i < numOfPlayers; i++) board.map[player.get(i).y][player.get(i).x] = 0;
    }

    private void endTurn(){
        printALotsOfRows();
        player.get(index).isRentPaid = false;
        index++;
        index += turn;
        turn = 0;
        if (index > numOfPlayers - 1) index = 0;
    }

    private void playerActions() {
        Street currentStreet;
        currentStreet = board.streets[player.get(index).y][player.get(index).x];

        if (currentStreet.ownedBy.equals("")) {
            chooseAction(currentStreet, player.get(index));
        } else if (currentStreet.hasStreet) {
            int i = getIndexOfOwner(currentStreet);
            Street street = player.get(i).streetsOwned.get(player.get(i).streetsOwned.indexOf(currentStreet));
            chooseAction(street, player.get(index));
        }
    }

    private void chooseAction(Street street, Player player) {
        System.out.println("Your balance is " + player.balance + "$");
        if(player.streetsOwned.size() == 0 && !street.hasStreet) {
            System.out.println("press enter to end your turn");
            promptEnterKey();
            return;
        }
        int currentOption = 1;
        System.out.println("Enter " + currentOption + " to mortgage street");
        currentOption++;
        System.out.println("Enter " + currentOption +" to see all owned streets");
        currentOption++;

        int optionFlag = 0;
        if (street.hasStreet && street.ownedBy.equals("")) {
            System.out.println("Enter " + currentOption + " for buying this street. Street's price is " + street.price + "$");
            optionFlag = 1;
            currentOption++;
        } else if (street.hasStreet && !street.ownedBy.equals(player.name) && !player.isRentPaid) {
            System.out.println("Enter " + currentOption + " to pay rent. Rent will cost " + street.rent[street.numOfHouses]);
            optionFlag = 2;
            currentOption++;
        }
        else this.player.get(index).isRentPaid = true;

        if(hasCompletedStreets()){
            System.out.println("Enter " + currentOption + " to build houses");
            currentOption++;
        }
        if(player.haveMortgagedStreet()){
            System.out.println("Enter " + currentOption + " to buy mortgaged street");
        }

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
                if(hasCompletedStreets()) buildHouses();
                else if(player.get(index).haveMortgagedStreet()) buyMortgagedStreet();
                playerActions();
                break;
            case 5:
                if(!hasCompletedStreets()) buyMortgagedStreet();
                playerActions();
                break;
            case 0:
                if(player.get(index).isRentPaid)
                break;
                else {
                    System.out.println("You must pay the rent first");
                    playerActions();
                }

        }
    }

    public static void promptEnterKey(){
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getIndexOfOwner(Street street) {
        for (int i = 0; i < numOfPlayers; i++) {
            if (street.ownedBy.equals(player.get(i).name)) {
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

        Street playerStreet = player.get(index).streetsOwned.get(choose);
        if (!player.get(index).streetsOwned.get(choose).mortgaged) {
            player.get(index).streetsOwned.get(choose).mortgaged = true;
            board.streets[playerStreet.y][playerStreet.x].mortgaged = true;
            player.get(index).balance += playerStreet.mortgage + (playerStreet.housePrice * playerStreet.numOfHouses);
            System.out.println("You mortgaged this street successfully. Your balance is " + player.get(index).balance + "$");
        } else System.out.println("This street is already mortgaged.");
    }

    private void buyMortgagedStreet(){
        int[] streets = new int[25];
        int indexForStreetsArr = 0;
        Street playerStreets;
        for (int i = 0; i < player.get(index).streetsOwned.size(); i++) {
            playerStreets = player.get(index).streetsOwned.get(i);
            if(playerStreets.mortgaged) {
                streets[indexForStreetsArr] = i;
                System.out.print((indexForStreetsArr + 1) + " ");
                playerStreets.printStreet();
                indexForStreetsArr++;
            }
        }

        System.out.println("Choose street to buy or 0 to exit: ");
        int choose = input.nextInt();
        choose--;
        if(choose == -1) return;

        playerStreets = player.get(index).streetsOwned.get(streets[choose]);
        if(!(player.get(index).balance - playerStreets.mortgage < 0)){
            player.get(index).balance -= playerStreets.mortgage;
            player.get(index).streetsOwned.get(streets[choose]).mortgaged = false;
            board.streets[playerStreets.y][playerStreets.x].mortgaged = false;
        } else System.out.println("You don't have enough money");


    }

    private void printStreets() {
        for (int i = 0; i < player.get(index).streetsOwned.size(); i++) {
            System.out.print((i + 1) + " ");
            player.get(index).streetsOwned.get(i).printStreet();
        }
        System.out.println("-------------------------------------------------------------------");
    }

    private void buyStreet(Street street) {
        if(player.get(index).balance < street.price) {
            System.out.println("You don't have enough money to buy this.");
            return;
        }
        player.get(index).balance -= street.price;
        player.get(index).streetsOwned.add(street);
        board.streets[street.y][street.x].ownedBy = player.get(index).name;
        checkForCompletedStreets();
    }

    private void buildHouses() {

        int[] streets = new int[25];
        int indexForStreetsArr = 0;
        Street playerStreets;
        for (int i = 0; i < player.get(index).streetsOwned.size(); i++) {
            playerStreets = player.get(index).streetsOwned.get(i);
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

        playerStreets = player.get(index).streetsOwned.get(streets[choose]);
        if(!(player.get(index).balance - playerStreets.housePrice * numOfHouses < 0)){
            player.get(index).balance -= playerStreets.housePrice * numOfHouses;
            player.get(index).streetsOwned.get(streets[choose]).numOfHouses += numOfHouses;
            board.streets[playerStreets.y][playerStreets.x].numOfHouses += numOfHouses;
        } else System.out.println("You don't have enough money");

    }

    private void checkForCompletedStreets() {
        String color = player.get(index).streetsOwned.lastElement().color;
        int counter = 1;
        int maxCounter = color.equals("Brown") || color.equals("blueDark") ? 2 : 3;
        for (int i = 0; i < player.get(index).streetsOwned.size() - 1; i++) {
            if(color.equals(player.get(index).streetsOwned.get(i).color)){
                counter++;
                if(counter == maxCounter) break;
            }
        }
        if(counter == maxCounter) {
            for (int i = 0; i < player.get(index).streetsOwned.size(); i++) {
                if(color.equals(player.get(index).streetsOwned.get(i).color)){
                    player.get(index).streetsOwned.get(i).fullStreet = true;
                }
            }
        }
    }

    private boolean hasCompletedStreets() {
        for (int i = 0; i < player.get(index).streetsOwned.size(); i++) {
            if(player.get(index).streetsOwned.get(i).fullStreet) return true;
        }
        return false;
    }

    private void payRent(Street street) {
        int i = getIndexOfOwner(street);
        player.get(i).balance += street.rent[street.numOfHouses];
        player.get(index).balance -= street.rent[street.numOfHouses];
        player.get(index).isRentPaid = true;
    }


}

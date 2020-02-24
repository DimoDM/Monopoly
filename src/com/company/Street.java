package com.company;

public class Street {

    Street(int xCoordinate, int yCoordinate, String color, int[] rent, int priceOfStreet){
        x = xCoordinate;
        y = yCoordinate;
        price = priceOfStreet;
        mortgage = priceOfStreet / 2;
        this.color = color;
        initHousePrice(color);
        initRent(rent);
        tag = color.charAt(0);
    }
    Street(int xCoordinate, int yCoordinate){
        x = xCoordinate;
        y = yCoordinate;
        color = " ";
        tag = ' ';
    }

    public int x;
    public int y;
    public int price;
    public int mortgage;
    public int housePrice;
    public int[] rent = new int[6];
    public char tag;
    public String color;
    public String ownedBy;

    private void initHousePrice(String col){
        switch (col){
            case "brown":
            case "blueLight":
                housePrice = 50;
                break;
            case "pink":
            case "orange":
                housePrice = 100;
                break;
            case "red":
            case "yellow":
                housePrice = 150;
                break;
            case "green":
            case "blueDark":
                housePrice = 200;
                break;
            default:
                housePrice = 0;
        }
    }
    private void initRent(int[] rent){
        for (int i = 0; i < rent.length; i++) this.rent[i] = rent[i];
    }

}

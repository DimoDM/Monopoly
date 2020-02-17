package com.company;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Monopoly game = new Monopoly();

        game.init();
        while (game.isRunning) {
            game.update();
        }

    }
}

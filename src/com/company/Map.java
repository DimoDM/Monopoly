package com.company;


public class Map {

    char[][] map = {
            {'0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}
    };


    void print() {
        System.out.print("  ");
        for (int i = 0; i < map[1].length; i++) {
            System.out.print(streets[1][i].tag + " ");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < map[1].length; i++) {
            System.out.print(map[1][i] + " ");
        }
        System.out.println();

        for (int i = 0; i < map[0].length; i++) {
            System.out.println(streets[0][streets[0].length - i - 1].tag + "|" + map[0][map[0].length - i - 1]
                    + "                   " + map[2][i] + "|" + streets[2][i].tag);
        }
        System.out.print("  ");
        for (int i = map[3].length - 1; i >= 0; i--) System.out.print(map[3][i] + " ");
        System.out.println();
        System.out.print("  ");
        for (int i = map[3].length - 1; i >= 0; i--) System.out.print(streets[3][i].tag + " ");
    }


    Street[][] streets = {
            {
                    new Street(0, 0,
                            "Brown", new int[]{2, 10, 30, 90, 160, 250}, 60),
                    new Street(1, 0),
                    new Street(2, 0,
                            "Brown", new int[]{4, 20, 60, 180, 320, 450}, 60),
                    new Street(3, 0),
                    new Street(4, 0),
                    new Street(5, 0,
                            "blueLight", new int[]{6, 30, 90, 270, 400, 550}, 100),
                    new Street(6, 0),
                    new Street(7, 0,
                            "blueLight", new int[]{6, 30, 90, 270, 400, 550}, 100),
                    new Street(8, 0,
                            "blueLight", new int[]{8, 40, 100, 300, 450, 600}, 120),
            },
            {
                    new Street(0, 1),
                    new Street(1, 1,
                            "pink", new int[]{10, 50, 150, 450, 625, 750}, 140),
                    new Street(2, 1),
                    new Street(3, 1,
                            "pink", new int[]{10, 50, 150, 450, 625, 750}, 140),
                    new Street(4, 1,
                            "pink", new int[]{12, 60, 180, 500, 700, 900}, 160),
                    new Street(5, 1),
                    new Street(6, 1,
                            "orange", new int[]{14, 70, 200, 550, 750, 950}, 180),
                    new Street(7, 1),
                    new Street(8, 1,
                            "orange", new int[]{14, 70, 200, 550, 750, 950}, 180),
                    new Street(9, 1,
                            "orange", new int[]{16, 80, 220, 600, 800, 1000}, 200),
                    new Street(10, 0),
            },
            {
                    new Street(0, 2,
                            "red", new int[]{18, 90, 250, 700, 875, 1050}, 220),
                    new Street(1, 2),
                    new Street(2, 2,
                            "red", new int[]{18, 90, 250, 700, 875, 1050}, 220),
                    new Street(3, 2,
                            "red", new int[]{20, 100, 300, 750, 925, 1100}, 240),
                    new Street(4, 2),
                    new Street(5, 2,
                            "yellow", new int[]{22, 110, 330, 800, 975, 1150}, 260),
                    new Street(6, 2,
                            "yellow", new int[]{22, 110, 330, 800, 975, 1150}, 260),
                    new Street(7, 2),
                    new Street(8, 2,
                            "yellow", new int[]{24, 120, 360, 850, 1025, 1200}, 280),
            },
            {
                    new Street(0, 3),
                    new Street(1, 3,
                            "green", new int[]{26, 130, 390, 900, 1100, 1275}, 300),
                    new Street(2, 3,
                            "green", new int[]{26, 130, 390, 900, 1100, 1275}, 300),
                    new Street(3, 3),
                    new Street(4, 3,
                            "green", new int[]{28, 150, 450, 1000, 1200, 1400}, 320),
                    new Street(5, 3),
                    new Street(6, 3),
                    new Street(7, 3,
                            "blueDark", new int[]{35, 175, 500, 1100, 1300, 1500}, 350),
                    new Street(8, 3),
                    new Street(9, 3,
                            "blueDark", new int[]{50, 200, 600, 1400, 1700, 2000}, 400),
                    new Street(10, 3),
            }
    };

}

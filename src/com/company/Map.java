package com.company;

import sun.security.pkcs11.wrapper.CK_RSA_PKCS_OAEP_PARAMS;

public class Map {

    int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    void print() {
        for (int i = 0; i < map[1].length; i++) {
            System.out.print(map[1][i] + " ");
        }
        System.out.println();

        for (int i = 0; i < map[0].length; i++) {
            System.out.println(map[0][map[0].length - i - 1] + "                   " + map[2][i]);
        }
        for (int i = map[3].length - 1; i >= 0; i--) System.out.print(map[3][i] + " ");
    }



}

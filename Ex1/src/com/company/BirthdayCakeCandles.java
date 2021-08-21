package com.company;

import java.util.Scanner;

public class BirthdayCakeCandles {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int candles[] = new int[n];
        int highest = 0;
        for (int candles_i = 0; candles_i < n; candles_i++) {
            candles[candles_i] = in.nextInt();
            if (candles[candles_i] > highest) {

                highest = candles[candles_i];
            }
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (highest == candles[i]) {
                total++;
            }
        }
        System.out.println(total);
    }
}

package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static Random random;
    static Map<Integer, Boolean> statistics;
    static int doorsCount;

    public static void main(String[] args) {
        random = new Random();
        statistics = new HashMap<>();
        doorsCount = 3;

        for (int i = 0; i < 1000; i++) {
            game(i);
        }

        int win = 0;
        for (Map.Entry<Integer, Boolean> entry: statistics.entrySet()){
            if (entry.getValue()){
                System.out.println(entry );
                win++;
            }
            System.out.println(entry);
        }
        System.out.println();
        System.out.println("Игрок выиграл " + win + " раз");
    }

    private static void game(int number) {
        int carDoor = random.nextInt(doorsCount);
        int choiceDoor = random.nextInt(doorsCount);
        int freeOpenDoor = -1;
        int newChoice = -1;

        for (int i = 0; i < doorsCount; i++) {
            if (i != carDoor && i != choiceDoor){
                freeOpenDoor = i;
            }
        }

        for (int i = 0; i < doorsCount; i++) {
            if (i != freeOpenDoor && i != choiceDoor){
                newChoice = i;
            }
        }

        statistics.put(number, carDoor == newChoice);
    }
}
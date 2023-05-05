package com.driver.utilities;

import java.util.Random;


public class Utilities {
    public static String compareString(String first, String second){
        int i = 0, j = 0;

        while(i<first.length() && j<second.length()){
            if(first.charAt(i)>second.charAt(j)) return second;
            else if(first.charAt(i)<second.charAt(j)) return first;
        }

        if(first.length()<second.length()) return second;

        return first;
    }



    public static String generateId() {
        final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int ID_LENGTH = 8;
        Random random = new Random();
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}

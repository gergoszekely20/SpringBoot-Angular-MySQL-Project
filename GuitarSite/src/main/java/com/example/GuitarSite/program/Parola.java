package com.example.GuitarSite.program;

import java.util.Date;
import java.util.Scanner;

public class Parola {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Introduceți șirul de caractere: ");
        String input = scanner.nextLine();


        System.out.println("Alegeți nivelul de securitate:");
        System.out.println("1. Parola slabă");
        System.out.println("2. Parola medie");
        System.out.println("3. Parola complexă");
        int securityLevel = scanner.nextInt();


        String encrypted = "";
        switch (securityLevel) {
            case 1:
                encrypted = encrypt(input, 1);
                break;
            case 2:
                encrypted = encrypt(input, 4);
                break;
            case 3:
                encrypted = encryptWithDate(input);
                break;
            default:
                System.out.println("Opțiune invalidă!");
                return;
        }


        System.out.println("Parola cifrată: " + encrypted);

        scanner.close();
    }


    public static String encrypt(String input, int offset) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char encryptedChar = (char) (c + offset);
                if ((Character.isLowerCase(c) && encryptedChar > 'z') ||
                        (Character.isUpperCase(c) && encryptedChar > 'Z')) {
                    encryptedChar -= 26;
                }
                encrypted.append(encryptedChar);
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }


    public static String encryptWithDate(String input) {
        Date date = new Date();
        int day = date.getDate();
        return encrypt(input, day);
    }


}

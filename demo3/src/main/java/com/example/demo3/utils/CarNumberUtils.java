package com.example.demo3.utils;

import java.util.Random;

//служебный класс, содержит методы для получения значений цифр и букв
public class CarNumberUtils {

    private String text = "АВЕКМНОРСТУХ";

    //получение случайной буквы
    public char getLetter() {
        Random r = new Random();
        char letter = text.charAt(r.nextInt(text.length()));
        return letter;
    }

    //получение случайного числа
    public int getNumber() {
        int numbers = (int) (Math.random() * 1000);
        return numbers;
    }

    //получение следующей возможной буквы
    public String getNext(String letter) {
        boolean isPossibless = true;
        String next = letter;
        while (isPossibless) {
            int charValue = next.charAt(0);
            next = String.valueOf((char) (charValue + 1));
            System.out.println(next);
            if (text.contains((next))) {
                isPossibless = false;
            }
        }
        return next;
    }

    //получение следующей буквы
    public String[] nextLetter(String[] letters) {
        if (!letters[2].equals("Х")) {
            letters[2] = getNext(letters[2]);
        } else {
            letters[2] = "А";
            if (!letters[1].equals("Х")) {
                letters[1] = getNext(letters[1]);
            } else {
                letters[1] = "А";
                letters[0] = getNext(letters[0]);
            }
        }
        return letters;
    }

}

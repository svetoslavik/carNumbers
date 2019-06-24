package com.example.demo3.utils;

import java.io.IOException;

//класс - генератор номера
public class GenerateNumber {

    CarNumberUtils utils = new CarNumberUtils();
    String result = null;
    private final String region = " 116 RUS";

    //генерируется номер для автомобиля
    public String generateNumber(int type) throws IOException {
        boolean isExists = true;
        if (type == 1) { //при рандомном методе выдачи номера
            while (isExists) {
                result = utils.getLetter() + "" + utils.getNumber() + utils.getLetter() + utils.getLetter() + region;
                //проверка существует ли уже данный номер
                isExists = LogUtils.fileContainsWord(result);
            }
        } else if (type == 2) { //при послдедовательном методе выдачи номера
            String logString = LogUtils.ReadLastLine();
            if (logString == null) {
                result = "A000AA" + region;
            } else {
                //получаем последний номер из лог файла
                String lastCarNumber = logString.substring(6, logString.length());
                String start = "";
                if (lastCarNumber.substring(1, 2).equals("0")) {
                    if (lastCarNumber.substring(2, 3).equals("0")) {
                        start = lastCarNumber.substring(0, 3);
                    } else {
                        start = lastCarNumber.substring(0, 2);
                    }
                } else {
                    start = lastCarNumber.substring(0, 1);
                }
                int lastNumber = Integer.parseInt(lastCarNumber.substring(1, 4));
                while (isExists) {
                    if (lastNumber < 999) {
                        //увеличиваем номер на 1
                        lastNumber++;
                        result = start + lastNumber + lastCarNumber.substring(4, lastCarNumber.length());
                    } else {
                        String[] mas = new String[10];
                        mas[0] = start;
                        mas[1] = lastCarNumber.substring(4, 5);
                        mas[2] = lastCarNumber.substring(5, 6);
                        //получение следующей по порядку возможной буквы
                        mas = utils.nextLetter(mas);
                        result = mas[0] + "000" + mas[1] + mas[2] + lastCarNumber.substring(6, lastCarNumber.length());
                    }
                    //проверка существует ли уже данный номер
                    isExists = LogUtils.fileContainsWord(result);
                }
            }
        }
        //записываем сгенерированный номер автомобиля
        LogUtils.writeLog(result);
        return result;
    }
}

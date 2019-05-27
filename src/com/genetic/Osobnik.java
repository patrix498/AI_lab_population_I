package com.genetic;

import java.util.Random;

public class Osobnik {
    String dna = "";
    int przystosowanie = 0;
    int udzial_w_populacji;

    public Osobnik(){
        String[] arr = {"1", "0"};
        Random rand = new Random();
        String temp;
        for (int i = 0; i < 20; i++) {
            dna += arr[rand.nextInt(arr.length)];
        }
    }

    public void fittness(){
        int temp = 0;
        for (char c:dna.toCharArray()) {
            if(c == '1'){
                temp++;
            }
        }
        przystosowanie = temp;
    }

    public void setUdzial_w_populacji(int suma){
        udzial_w_populacji = (int) (przystosowanie*1000.0/suma);//*1000 dla 100 osobników
    }
}

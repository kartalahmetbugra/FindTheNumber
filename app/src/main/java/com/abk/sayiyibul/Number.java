package com.abk.sayiyibul;

import java.util.Random;

public class Number {
    public int nmbr(){
        int sayi;
        Random random=new Random();
        sayi= random.nextInt(100);
        return sayi;
    }
}

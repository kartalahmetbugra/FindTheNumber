package com.abk.sayiyibul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Game extends AppCompatActivity {
    int sayac = 0;
    static int skor = 0;
    int sayi;
    int tahmin;
    String tahmin2;
    TextView wlc, score, syc;
    EditText guess;
    Intent sure;
    int time = 60000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        sure = getIntent();
        time = sure.getIntExtra("time", 60000);
        sayac();
        Number number = new Number();
        sayi = number.nmbr();
        skor = 0;



    }

    public void devam(View v) {
        sayac++;
        wlc = findViewById(R.id.wlc);
        guess = findViewById(R.id.guess);
        score = findViewById(R.id.basari);
        score.setText("Skorunuz: "+String.valueOf(skor));

        if (sayac == 2) {

            tahmin2 = guess.getText().toString();
            if (!TextUtils.isEmpty(tahmin2)) {
                tahmin = Integer.valueOf(tahmin2);
            } else {
                wlc.setText("tahmininizi giriniz");
            }

            if (sayi < tahmin) {
                wlc.setText("Sayıyı Azaltın");
                sayac = 0;
                guess.setText("");
                devam(v);
            } else if (sayi > tahmin) {
                wlc.setText("Sayıyı Arttırın");
                sayac = 0;
                guess.setText("");
                devam(v);
            } else {
                wlc.setText("Sayıyı Buldunuz");
                skor++;
                Number sayi3 = new Number();
                sayi = sayi3.nmbr();
                sayac = 0;
                guess.setText("");
                devam(v);
            }

        }

    }

    public void sayac() {
        syc = findViewById(R.id.syc);


        new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long l) {
                syc.setText("" + l * 1 / 1000);

            }

            @Override
            public void onFinish() {
                Intent back = new Intent(Game.this, MainActivity.class);
                finish();

                startActivity(back);

            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(Game.this, MainActivity.class);
        finish();
        startActivity(back);
    }
}
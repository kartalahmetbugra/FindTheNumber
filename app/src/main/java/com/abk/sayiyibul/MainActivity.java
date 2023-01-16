package com.abk.sayiyibul;


import static com.abk.sayiyibul.Game.skor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int maks;
    Intent game;
    TextView rekor;
    String rekordeger;
    CheckBox dk, birbckdk, ikidk;
    int time = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dk = findViewById(R.id.dakika);
        birbckdk = findViewById(R.id.birbck);
        ikidk = findViewById(R.id.ikidk);
        SharedPreferences shared = this.getPreferences(Context.MODE_PRIVATE);

        rekor = findViewById(R.id.txtrekor);
        rekordeger = String.valueOf(shared.getInt("MaksimumSkor", 0));
        maks = shared.getInt("MaksimumSkor", 0);
        rekor.setText("Rekorunuz: " + rekordeger);
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dk.isChecked()) {
                    birbckdk.setVisibility(View.INVISIBLE);
                    ikidk.setVisibility(View.INVISIBLE);
                    time = 60000;
                } else {
                    birbckdk.setVisibility(View.VISIBLE);
                    ikidk.setVisibility(View.VISIBLE);
                }

            }
        });
        birbckdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (birbckdk.isChecked()) {
                    dk.setVisibility(View.INVISIBLE);
                    ikidk.setVisibility(View.INVISIBLE);
                    time = 90000;
                } else {
                    dk.setVisibility(View.VISIBLE);
                    ikidk.setVisibility(View.VISIBLE);
                }
            }
        });
        ikidk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ikidk.isChecked()) {
                    dk.setVisibility(View.INVISIBLE);
                    birbckdk.setVisibility(View.INVISIBLE);
                    time = 120000;
                } else {
                    dk.setVisibility(View.VISIBLE);
                    birbckdk.setVisibility(View.VISIBLE);
                }
            }
        });


        if (skor > maks) {
            maks = skor;
            SharedPreferences.Editor editor = shared.edit();
            editor.putInt("MaksimumSkor", maks);
            editor.commit();
            rekor.setText("Rekorunuz: " + String.valueOf(maks));
            return;
        } else {
            return;
        }

    }

    public void start(View v) {
        if (dk.isChecked() || birbckdk.isChecked() || ikidk.isChecked()) {
            game = new Intent(MainActivity.this, Game.class);
            game.putExtra("time", time);
            finish();
            startActivity(game);
        } else
            Toast.makeText(getApplicationContext(), "Lütfen süreyi seçiniz", Toast.LENGTH_LONG).show();
    }

}
package com.example.mobil_programlama;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    private EditText editAdet, editMin, editMax;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        linearLayout = findViewById(R.id.linearLayout);
        editAdet = findViewById(R.id.editTextSayi);
        editMin = findViewById(R.id.editTextSayi1);
        editMax = findViewById(R.id.editTextSayi2);

        Button btn = findViewById(R.id.buttonOlustur);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adet = Integer.parseInt(editAdet.getText().toString());
                int min = Integer.parseInt(editMin.getText().toString());
                int max = Integer.parseInt(editMax.getText().toString());

                for (int i = 0; i < adet; i++) {
                    int randomValue = generateRandomValue(min, max);
                    addProgressBarAndText(randomValue);
                }
            }
        });
    }

    private int generateRandomValue(int min, int max) {
        return min + new Random().nextInt(max - min + 1);
    }

    private void addProgressBarAndText(int randomValue) {
        // ProgressBar'u oluştur
        ProgressBar progressBar = new ProgressBar(RandomActivity.this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100); // ProgressBar değeri 0 ile 100 arasında olmalı
        progressBar.setProgress(randomValue); // ProgressBar'a rastgele değeri set et

        // TextView oluştur ve ProgressBar'ın altına ekle
        TextView progressText = new TextView(RandomActivity.this);
        progressText.setText("Value: " + randomValue);

        // LinearLayout'a ProgressBar ve TextView'ı ekle
        linearLayout.addView(progressBar);
        linearLayout.addView(progressText);
    }
}

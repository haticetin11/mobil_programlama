package com.example.mobil_programlama;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobil_programlama.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConvertorActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private Button buttonDecimal;
    private TextView sonucDecimal;
    private Spinner spinnerDecimal;
    private Button buttonByte;
    private TextView sonucByte;
    private Spinner spinnerByte;
    private EditText editTextNumber2;
    private RadioButton radioButton;
    private RadioButton radioButton2;
    private TextView sonucCelcius;
    private Button buttonCelcius;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);

        // View elemanlarını tanımla
        initializeViews();

        // Decimal dönüşüm butonuna tıklanma olayını tanımla
        setDecimalButtonClickListener();

        // Decimal dönüşüm Spinner'ının seçilme olayını tanımla
        setDecimalSpinnerItemSelectedListener();

        // Byte dönüşüm butonuna tıklanma olayını tanımla
        setByteButtonClickListener();

        // Byte dönüşüm Spinner'ının seçilme olayını tanımla
        setByteSpinnerItemSelectedListener();

        // Celcius dönüşüm butonuna tıklanma olayını tanımla
        setCelciusButtonClickListener();

        // Celcius dönüşüm RadioButon'larının tıklanma olayını tanımla
        setRadioButtonClickListener();
    }

    // View elemanlarını başlatan fonksiyon
    private void initializeViews() {
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber2=findViewById(R.id.editTextNumber2);
        buttonDecimal = findViewById(R.id.button_decimal);
        sonucDecimal = findViewById(R.id.sonucDecimal);
        spinnerDecimal = findViewById(R.id.spinnerDecimal);
        buttonByte = findViewById(R.id.buttonByte);
        sonucByte = findViewById(R.id.sonucByte);
        spinnerByte = findViewById(R.id.spinner4);
        buttonCelcius = findViewById(R.id.buttonCelcius);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        sonucCelcius= findViewById(R.id.sonucCelcius);



        // Decimal dönüşüm için Spinner'ı başlat
        initializeDecimalSpinner();

        // Byte dönüşümü için Spinner'ı başlat
        initializeByteSpinner();
    }

    // Decimal dönüşüm için Spinner'ı başlatan fonksiyon
    private void initializeDecimalSpinner() {
        List<String> categories1 = new ArrayList<>();
        categories1.add("ikilik");
        categories1.add("sekizlik");
        categories1.add("onaltılık");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDecimal.setAdapter(dataAdapter1);
    }

    // Byte dönüşümü için Spinner'ı başlatan fonksiyon
    private void initializeByteSpinner() {
        List<String> categories2 = new ArrayList<>();
        categories2.add("kilo byte");
        categories2.add("byte");
        categories2.add("kibibyte");
        categories2.add("bit");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerByte.setAdapter(dataAdapter2);
    }

    // Decimal dönüşüm butonuna tıklanma olayını dinleyen fonksiyon
    private void setDecimalButtonClickListener() {
        buttonDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Decimal dönüşümü gerçekleştir
                convertDecimal();
            }
        });
    }

    // Decimal dönüşüm Spinner'ının seçilme olayını dinleyen fonksiyon
    private void setDecimalSpinnerItemSelectedListener() {
        spinnerDecimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Seçilen kategoriyi göster
                String selectedCategory = parentView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Seçilen Kategori (Decimal): " + selectedCategory, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Herhangi bir öğe seçilmediğinde yapılacak işlemler
            }
        });
    }

    // Byte dönüşüm butonuna tıklanma olayını dinleyen fonksiyon
    private void setByteButtonClickListener() {
        buttonByte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Byte dönüşümü gerçekleştir
                convertByte();
            }
        });
    }

    // Byte dönüşüm Spinner'ının seçilme olayını dinleyen fonksiyon
    private void setByteSpinnerItemSelectedListener() {
        spinnerByte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Seçilen kategoriyi göster
                String selectedCategory = parentView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Seçilen Kategori (Byte): " + selectedCategory, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Herhangi bir öğe seçilmediğinde yapılacak işlemler
            }
        });
    }

    // Decimal dönüşümü gerçekleştiren fonksiyon
    private void convertDecimal() {
        // Kullanıcının girdiği onluk sayıyı al
        String onlukSayiStr = editTextNumber.getText().toString();

        try {
            // Onluk sayıyı integer'a çevir
            int onlukSayi = Integer.parseInt(onlukSayiStr);

            // Spinner'dan seçilen kategori
            String selectedCategory = spinnerDecimal.getSelectedItem().toString();

            // Seçilen kategoriye göre dönüşüm yap
            String sonucMetni = "";
            switch (selectedCategory) {
                case "ikilik":
                    sonucMetni =   Integer.toBinaryString(onlukSayi);
                    break;
                case "sekizlik":
                    sonucMetni =  Integer.toOctalString(onlukSayi);
                    break;
                case "onaltılık":
                    sonucMetni = Integer.toHexString(onlukSayi);
                    break;
            }

            // Sonuçları TextView içinde göster
            sonucDecimal.setText(sonucMetni);
        } catch (NumberFormatException e) {
            // Hatalı giriş durumu
            sonucDecimal.setText("Geçersiz giriş! Lütfen bir onluk sayı girin.");
        }
    }

    // Byte dönüşümü gerçekleştiren fonksiyon
    private void convertByte() {
        // Kullanıcının girdiği Mega Byte'ı al
        String megaByteStr = editTextNumber.getText().toString();

        try {
            // Mega Byte'ı double'a çevir
            double megaByte = megaByteStr.isEmpty() ? 0 : Double.parseDouble(megaByteStr);

            // Seçilen kategoriye göre dönüşüm yap
            String selectedCategory = spinnerByte.getSelectedItem().toString();
            String resultText = "";

            switch (selectedCategory) {
                case "kilo byte":
                    resultText = "Sonuç: " + convertMegaByteToKiloByte(megaByte) + " KB";
                    break;
                case "byte":
                    resultText = "Sonuç: " + convertMegaByteToByte(megaByte) + " Bytes";
                    break;
                case "kibibyte":
                    resultText = "Sonuç: " + convertMegaByteToKibiByte(megaByte) + " KiB";
                    break;
                case "bit":
                    resultText = "Sonuç: " + convertMegaByteToBit(megaByte) + " bits";
                    break;
            }

            // Sonucu TextView'de göster
            sonucByte.setText(resultText);
        } catch (NumberFormatException e) {
            // Hatalı giriş durumu
            sonucByte.setText("Geçersiz giriş! Lütfen bir Mega Byte değeri girin.");
        }
    }

    // Celcius dönüşüm butonuna tıklanma olayını dinleyen fonksiyon
    private void setCelciusButtonClickListener() {
        buttonCelcius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Celcius dönüşümü gerçekleştir
                convertCelcius();
            }
        });
    }

    // Celcius dönüşüm RadioButon'larının tıklanma olayını dinleyen fonksiyon
    private void setRadioButtonClickListener() {
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fahrenheit seçildiğinde işlemler
                Toast.makeText(getApplicationContext(), "Seçilen Birim: Fahrenheit", Toast.LENGTH_SHORT).show();
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kelvin seçildiğinde işlemler
                Toast.makeText(getApplicationContext(), "Seçilen Birim: Kelvin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Celcius dönüşümü gerçekleştiren fonksiyon
    private void convertCelcius() {
        // Kullanıcının girdiği Celcius sıcaklığını al
        String celciusStr = editTextNumber2.getText().toString();

        try {
            // Celcius'u double'a çevir
            double celcius = celciusStr.isEmpty() ? 0 : Double.parseDouble(celciusStr);

            // Seçilen birime göre dönüşüm yap
            String resultText = "";
            if (radioButton.isChecked()) {
                // Fahrenheit seçildiyse
                double fahrenheitValue = convertCelciusToFahrenheit(celcius);
                resultText = "Sonuç: " + new DecimalFormat("#.##").format(fahrenheitValue) + " Fahrenheit";
            } else if (radioButton2.isChecked()) {
                // Kelvin seçildiyse
                double kelvinValue = convertCelciusToKelvin(celcius);
                resultText = "Sonuç: " + new DecimalFormat("#.##").format(kelvinValue) + " Kelvin";
            } else {
                // Herhangi bir birim seçilmediyse
                Toast.makeText(getApplicationContext(), "Lütfen bir birim seçin.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Sonucu TextView'de göster
            sonucCelcius.setText(resultText);
        } catch (NumberFormatException e) {
            // Hatalı giriş durumu
            sonucCelcius.setText("Geçersiz giriş! Lütfen bir sıcaklık değeri girin.");
        }
    }

    // Celcius'u Fahrenheit'a çeviren fonksiyon
    private double convertCelciusToFahrenheit(double celcius) {
        return (celcius * 9/5) + 32;
    }

    // Celcius'u Kelvin'e çeviren fonksiyon
    private double convertCelciusToKelvin(double celcius) {
        return celcius + 273.15;
    }


    // Mega Byte'ı Kilo Byte'a çeviren fonksiyon
    private String convertMegaByteToKiloByte(double megaByte) {
        double kiloByte = megaByte * 1024;
        return new DecimalFormat("#.##").format(kiloByte);
    }

    // Mega Byte'ı Byte'a çeviren fonksiyon
    private String convertMegaByteToByte(double megaByte) {
        double byteValue = megaByte * 1024 * 1024;
        return new DecimalFormat("#.##").format(byteValue);
    }

    // Mega Byte'ı Kibi Byte'a çeviren fonksiyon
    private String convertMegaByteToKibiByte(double megaByte) {
        double kibiByte = megaByte * 1000;
        return new DecimalFormat("#.##").format(kibiByte);
    }

    // Mega Byte'ı Bit'e çeviren fonksiyon
    private String convertMegaByteToBit(double megaByte) {
        double bitValue = megaByte * 8 * 1024 * 1024;
        return new DecimalFormat("#.##").format(bitValue);
    }
}

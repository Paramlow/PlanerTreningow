
package com.example.planertreningow;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Elementy interfejsu
    private ImageView zdjecieCwiczenia;
    private TextView nazwaCwiczenia;
    private TextView licznikSerii;
    private TextView sumaSerii;

    // Zmienne przechowujące liczbę serii
    private int aktualneSerie = 0;
    private int wszystkieSerie = 0;

    // Generator liczb losowych
    private final Random random = new Random();

    // Tablica zdjęć ćwiczeń
    private final int[] zdjecia = {
            R.drawable.cwiczenie1,
            R.drawable.cwiczenie2,
            R.drawable.cwiczenie3,
            R.drawable.cwiczenie4,
            R.drawable.cwiczenie5,
            R.drawable.cwiczenie6
    };

    // Tablica nazw ćwiczeń
    private final String[] cwiczenia = {
            "Przysiad",
            "Pompki",
            "Plank",
            "Wykroki",
            "Burpees",
            "Podciąganie"
    };

    // Wyświetlanie aktualnej liczby serii
    private void aktualizujLicznik() {
        licznikSerii.setText(String.valueOf(aktualneSerie));
    }

    // Wyświetlanie łącznej liczby serii
    private void aktualizujSume() {
        sumaSerii.setText("Łączna liczba serii: " + wszystkieSerie);
    }

    // Losowanie ćwiczenia i zmiana jego zdjęcia
    private void losujCwiczenie() {
        int indeks = random.nextInt(cwiczenia.length);

        zdjecieCwiczenia.setImageResource(zdjecia[indeks]);
        nazwaCwiczenia.setText(cwiczenia[indeks]);

        aktualneSerie = 0;
        aktualizujLicznik();
    }

    // Dodawanie jednej serii
    private void dodajSerie() {
        aktualneSerie++;
        aktualizujLicznik();
    }

    // Odejmowanie jednej serii
    private void odejmijSerie() {
        if (aktualneSerie > 0) {
            aktualneSerie--;
            aktualizujLicznik();
        }
    }

    // Zakończenie ćwiczenia i przejście do kolejnego
    private void nastepneCwiczenie() {
        wszystkieSerie += aktualneSerie;

        aktualizujSume();
        losujCwiczenie();
    }

    // Przywracanie ustawień początkowych
    private void resetujProgram() {
        aktualneSerie = 0;
        wszystkieSerie = 0;

        zdjecieCwiczenia.setImageResource(R.drawable.cwiczenie0);
        nazwaCwiczenia.setText("Losuj ćwiczenie");

        aktualizujLicznik();
        aktualizujSume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pobieranie elementów z XML
        zdjecieCwiczenia = findViewById(R.id.cwiczenieZdjecie);
        nazwaCwiczenia = findViewById(R.id.tekstCwiczenia);
        licznikSerii = findViewById(R.id.licznik);
        sumaSerii = findViewById(R.id.licznikSerii);

        Button losuj = findViewById(R.id.buttonLosuj);
        Button minus = findViewById(R.id.minus);
        Button plus = findViewById(R.id.plus);
        Button nastepne = findViewById(R.id.nastepneCwiczenie);
        Button reset = findViewById(R.id.resetuj);

        // Ustawienie początkowego stanu aplikacji
        resetujProgram();

        // Obsługa przycisku losowania
        losuj.setOnClickListener(v -> losujCwiczenie());

        // Obsługa przycisku plus
        plus.setOnClickListener(v -> dodajSerie());

        // Obsługa przycisku minus
        minus.setOnClickListener(v -> odejmijSerie());

        // Obsługa przejścia do następnego ćwiczenia
        nastepne.setOnClickListener(v -> nastepneCwiczenie());

        // Obsługa resetowania aplikacji
        reset.setOnClickListener(v -> resetujProgram());
    }
}

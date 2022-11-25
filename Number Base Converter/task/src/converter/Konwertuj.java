package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Konwertuj {
    static String znaki = "0123456789abcdefghijklmnopqrstuvwxyz";
    String KonwertujZDziesietnego(String liczby, int systemy) { // Konwersja z dziesietnego
        if (liczby.equals("0")) {
            return "0";
        }
        BigInteger liczba = new BigInteger(liczby);
        StringBuilder suma = new StringBuilder();
        while (!liczba.equals(BigInteger.valueOf(0))) { // Jezeli liczba nie jest rowna 0 to
            BigInteger bigReszta = liczba.remainder(BigInteger.valueOf(systemy)); // Reszta = reszta z dzielenia liczby przez system, np liczba 1500, system 12
            liczba = liczba.divide(BigInteger.valueOf(systemy)); //BigIntege jest immutable więc przypisujemy mu nową wartość liczba/systemy
            int reszta = bigReszta.intValue(); // reszta do inta
            suma.append(znaki.charAt(reszta)); // Dodajemy do Stringbuildera znak
        }
        return suma.reverse().toString(); // Odwracamy i jest wynik.
    }
    String KonwertujNaDziesietny(String liczba, int systemy) { // Konwersja na dziesietny
        BigInteger suma = new BigInteger(String.valueOf(0));
        BigInteger system = new BigInteger(String.valueOf(systemy)); // BigInteger system o wartosci int systemy
        for (int i = 0; i < liczba.length(); i++) {
            char a = liczba.charAt(i);
            BigInteger XD = new BigInteger(String.valueOf(znaki.indexOf(a))); // BigInteger zawiera zawartosc charu a, jezeli a = '5' to XD = "5"
            suma = suma.multiply(system).add(XD); // Mnozy sume przez system i dodaje zawartosc XD

        }
        return suma.toString();
    }
    String KonwertujPrzecinkoweNaDziesietny(String liczba, int systemy) { // Konwertuje przecinkowe na dziesietne
        BigDecimal suma = new BigDecimal(String.valueOf(0));
        for (int i = 0; i < liczba.length(); i++) {
            int znak = Character.getNumericValue(liczba.charAt(i)); // Pobiera char o indeksie i z liczba
            suma = suma.add(BigDecimal.valueOf(znak / Math.pow(systemy, (i+1)))).setScale(5, RoundingMode.HALF_DOWN);
            // Dodaje do sumy powyzszy int znak oraz dzieli przez potege systemu o i+1, na koncu zaokrągla do 5 miejsca po przecinku, do najblizszego sąsiada, jezeli liczby są równe to zaokrągla w góre
        }
        return suma.toString();
    }
    String KonwertujPrzecinkoweZDziesietnego(String liczba, int systemy) { // Konwersja z Przecinkowego na Dziesietny
        BigDecimal liczby = new BigDecimal(liczba);
        StringBuilder suma = new StringBuilder();
        while (suma.length() < 5) {
            int reszta = liczby.multiply(BigDecimal.valueOf(systemy)).intValue(); // reszta zawiera mnozenie liczby przez systemy np 0,27 * 26 = 7
            suma.append(Character.forDigit(reszta, systemy)); // Dodaje do Stringbuildera wartosc reszty np. reszta = 24 to dodaje "o"
            liczby = liczby.multiply(BigDecimal.valueOf(systemy)).remainder(BigDecimal.ONE).setScale(5, RoundingMode.HALF_DOWN);
            // Aktualizuje liczby o wartosc liczby * systemy i zaokrągla jak w powyższej metodzie
        }
        return suma.toString();
    }

}


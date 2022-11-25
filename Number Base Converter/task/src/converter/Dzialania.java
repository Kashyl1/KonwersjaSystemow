package converter;

import static converter.Main.scanner;

public class Dzialania {
    void dzialaj() {
        Konwertuj konwertuj = new Konwertuj();
        while (true) {
            System.out.println("Wpisz dwie liczby w formacie: {Obecny system} {Docelowy System} (Aby wyjść wpisz /exit)");
            String systemBiezacy = scanner.next();
            if (systemBiezacy.equals("/exit")) {
                System.exit(0);
            }
            String systemDocelowy = scanner.next();
            String liczba;

            boolean towarzysz = true;
                while (towarzysz) {
                    if (systemBiezacy.equals("/back")) {
                        towarzysz = false;
                    } else {
                        System.out.println("Wpisz liczbe w bazie, " + systemBiezacy + " by przekonwertować ją na baze " + systemDocelowy +
                                " (By powrócić wpisz /back)");
                        liczba = scanner.next();
                        if (liczba.equals("/back")) {
                            break;
                        } if (liczba.equals("0")) {
                            System.out.println("Wynik konwersji: 0");
                        }
                        else { // Jezeli liczba przecinkowa to
                            if (liczba.contains(".")) {
                                String[] oddzielaj = liczba.split("\\.");
                                String pelne = oddzielaj[0];
                                String przecinkowe = oddzielaj[1];
                                int system = Integer.parseInt(systemDocelowy);
                                String naDziesietnyCale = konwertuj.KonwertujNaDziesietny(pelne, Integer.parseInt(systemBiezacy));
                                String zDziesietnegoCale = konwertuj.KonwertujZDziesietnego(naDziesietnyCale, system);
                                String naDziesietnyPrzecinkowe = konwertuj.KonwertujPrzecinkoweNaDziesietny(przecinkowe, Integer.parseInt(systemBiezacy));
                                String zDziesietnegoPrzecinkowe = konwertuj.KonwertujPrzecinkoweZDziesietnego(naDziesietnyPrzecinkowe, system);
                                System.out.println("Wynik konwersji: " + zDziesietnegoCale + "." + zDziesietnegoPrzecinkowe);
                            }
                            else { // Jezeli calkowita to
                                int system = Integer.parseInt(systemDocelowy);
                                String naDziesietny = konwertuj.KonwertujNaDziesietny(liczba, Integer.parseInt(systemBiezacy));
                                System.out.println("Wynik konwersji: " + konwertuj.KonwertujZDziesietnego(naDziesietny, system));
                            }
                        }
                    }
                }

        }


    }
}

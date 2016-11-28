package WarcabyFX;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Warcaby {

    private int szachownica[][];                           //referencja tablicy int [][]
    private StringProperty stringProperties[][];           //referencja tablicy StringProperty[][]

    public int ruch[] = new int[2]; // tablica do przechowywania ruchów.Metoda onClick wybierzPionka(ActionEvent e) z Kontrolera, 
    //zapisuje tu wspó³rzêdne pionka. ruch[0] wiersz, ruch[1] kolumna,

    private int pionkiBialy;       //ilosc punktow gracza bialego i czarnego
    private int pionkiCzarny;

    private int sterowanieKolejnoscia;          //zmiena s³u¿y do obs³ugi rozgrywki w metodzie graj().
    private int aktualnyGracz;                  //zmiena okreœla który gracz aktualnie gra 2=bialy,1=czarny

    int aktualnaPozycjaX, aktualnaPozycjaY;
    int tabelaBicieWPionkiem[] = new int[10];

    private StringProperty aktualnyGraczProperties = new SimpleStringProperty(""); //zmiena slu¿y do obslugi labela 

    void nowaGra() {             //metoda wywo³ywana po uruchomieniu programu oraz zawsze po naciœniêciu przycisku "Nowa gra"
        //zeruje wszystkie zmienne oraz ustawia odpowiednio tablice odwzorowuj¹ce ustawienie 
        int x[][]
                = {{0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 2, 0, 2, 0, 2, 0},
                {0, 2, 0, 2, 0, 2, 0, 2},
                {2, 0, 2, 0, 2, 0, 2, 0}};

        StringProperty y[][] = {{new SimpleStringProperty(""), new SimpleStringProperty("1"),
            new SimpleStringProperty(""), new SimpleStringProperty("1"), new SimpleStringProperty(""),
            new SimpleStringProperty("1"), new SimpleStringProperty(""), new SimpleStringProperty("1")},
        {new SimpleStringProperty("1"), new SimpleStringProperty(""), new SimpleStringProperty("1"),
            new SimpleStringProperty(""), new SimpleStringProperty("1"), new SimpleStringProperty(""),
            new SimpleStringProperty("1"), new SimpleStringProperty("")},
        {new SimpleStringProperty(""), new SimpleStringProperty("1"), new SimpleStringProperty(""),
            new SimpleStringProperty("1"), new SimpleStringProperty(""),
            new SimpleStringProperty("1"), new SimpleStringProperty(""),
            new SimpleStringProperty("1")},
        {new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""),
            new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""),
            new SimpleStringProperty(""), new SimpleStringProperty("")},
        {new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""),
            new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""),
            new SimpleStringProperty(""), new SimpleStringProperty("")},
        {new SimpleStringProperty("2"), new SimpleStringProperty(""), new SimpleStringProperty("2"),
            new SimpleStringProperty(""), new SimpleStringProperty("2"), new SimpleStringProperty(""),
            new SimpleStringProperty("2"), new SimpleStringProperty("")},
        {new SimpleStringProperty(""), new SimpleStringProperty("2"), new SimpleStringProperty(""),
            new SimpleStringProperty("2"), new SimpleStringProperty(""),
            new SimpleStringProperty("2"), new SimpleStringProperty(""),
            new SimpleStringProperty("2")},
        {new SimpleStringProperty("2"), new SimpleStringProperty(""), new SimpleStringProperty("2"),
            new SimpleStringProperty(""), new SimpleStringProperty("2"), new SimpleStringProperty(""),
            new SimpleStringProperty("2"), new SimpleStringProperty("")}};

        pionkiCzarny = pionkiBialy = 0;

        setSzachownica(x);
        setStringProperties(y);
        sterowanieKolejnoscia = 1;
        aktualnyGracz = 2;

    }

    private void zmienGracza() {          //metoda ktora po udanym ruchu/biciu zmienia gracza za pooca aktualnyGracz
        if (aktualnyGracz == 1) {
            aktualnyGracz = 2;    //po udanym ruchu zmienia gracza ktory bedzie wykonywal nastepny
            aktualnyGraczProperties.setValue("Ruch gracza BIA£EGO");
        } else {
            aktualnyGracz = 1;
            aktualnyGraczProperties.setValue("Ruch gracza CZARNEGO");
        }

    }

    void graj() {                                              //g³ówna metoda zarz¹dzaj¹ca klejnoœci¹ wykonywania innych metod      
        switch (sterowanieKolejnoscia) {
            case 1:
                wybor();
                break;
            case 2:
                wprowadzenieRuchuPionka();
                break;
            case 3:
                jestBicieWielokrotnePionkiem();
                break;
            case 4:
                wprowadzenieRuchuKrolowej();
                break;
            case 5:
                jestKolejneBicieKrolowej2();

                break;

        }

    }

    void wybor() {

        aktualnaPozycjaX = ruch[0];
        aktualnaPozycjaY = ruch[1];

        if (this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY] != aktualnyGracz && this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY] != aktualnyGracz * 3) { //warunek sprawdza czy 
            //wybrany pionek nale¿y do gracza
        } else {

            if (this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY] == aktualnyGracz) {

                sterowanieKolejnoscia = 2;   //wprowadzenieRuchuPionka()

            } else {
                sterowanieKolejnoscia = 4;  //wprowadzenieRuchuKrolowej()

            }

        }
    }

    private void wprowadzenieRuchuPionka() {
        int gdzieRuszycWiersz = ruch[0];
        int gdzieRuszycKolumna = ruch[1];
        ruchPionka(gdzieRuszycWiersz, gdzieRuszycKolumna);

    }

    private void ruchPionka(int gdzieRuszycWiersz, int gdzieRuszycKolumna) {
        int pomoc2;
        if (aktualnyGracz == 1) {            //w zaleznosci od tego czy aktualnie rusza sie gracz 1 lub 2 ustawiamy na gore lub dol
            pomoc2 = 1;
        } else {
            pomoc2 = -1;
        }

        if (gdzieRuszycWiersz == aktualnaPozycjaX + pomoc2 && (gdzieRuszycKolumna == aktualnaPozycjaY - 1 || gdzieRuszycKolumna == aktualnaPozycjaY + 1)) {

            if (this.szachownica[gdzieRuszycWiersz][gdzieRuszycKolumna] == 0) {

                if (gdzieRuszycWiersz == 7 || gdzieRuszycWiersz == 0) {
                    this.szachownica[gdzieRuszycWiersz][gdzieRuszycKolumna] = (aktualnyGracz * 3); // sprawdza krolowa
                    stringProperties[gdzieRuszycWiersz][gdzieRuszycKolumna].setValue(Integer.toString(aktualnyGracz * 3));

                } else {
                    this.szachownica[gdzieRuszycWiersz][gdzieRuszycKolumna] = aktualnyGracz;
                    sterowanieKolejnoscia = 1;
                    stringProperties[gdzieRuszycWiersz][gdzieRuszycKolumna].setValue(Integer.toString(aktualnyGracz));
                    //przenosi pionka na puste pole, uaktualnie rowniez informacje w tabeli propertisow w celu powiadomienia gui                              
                }

                this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY] = 0;             //czysci pole z ktorego sie ruszlismy
                stringProperties[aktualnaPozycjaX][aktualnaPozycjaY].setValue("");    //taka sama czynnosc przekazana do propertisow

                zmienGracza();

            } else {          //pole zajete, wraca do 1 z metody graj();            
                sterowanieKolejnoscia = 1;
            }

        } else {

            biciePionkiem(gdzieRuszycWiersz, gdzieRuszycKolumna);
        }

    }

    public void biciePionkiem(int gdzieRuszycWiersz, int gdzieRuszycKolumna) {
        int pomoc2;

        if (((gdzieRuszycWiersz == aktualnaPozycjaX - 2) || (gdzieRuszycWiersz == aktualnaPozycjaX + 2))
                && (gdzieRuszycKolumna == aktualnaPozycjaY - 2 || gdzieRuszycKolumna == aktualnaPozycjaY + 2)) { // Sprawdza czy ruch jest wykonywany
            // o dwa pola w dó³ lub w góre i czy jest o dwa pola w lewo lub w prawo

            if (this.szachownica[gdzieRuszycWiersz][gdzieRuszycKolumna] == 0) { // jesli to pole jest wolne, to konntynuluje
                // jesli nie to wymusza kolejny nowy ruch

                int h; // zmienna ktora okresli czy badamy pole po lewej czy po
                // prawej
                if (aktualnaPozycjaY < gdzieRuszycKolumna) {
                    h = gdzieRuszycKolumna - 1;
                } else {
                    h = gdzieRuszycKolumna + 1;
                }

                if (aktualnaPozycjaX < gdzieRuszycWiersz) {
                    pomoc2 = 1;
                } else {
                    pomoc2 = -1;
                }

                if (this.szachownica[aktualnaPozycjaX + pomoc2][h] != aktualnyGracz && this.szachownica[aktualnaPozycjaX + pomoc2][h] != 0) {
                    // Sprawdza czy pole zawiera pionek przeciwnika i nie jest polem pustym

                    this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY] = 0; // jesli pole zawiera pionek
                    // przeciwnika to zeruje aktualnie zajmowane pole
                    stringProperties[aktualnaPozycjaX][aktualnaPozycjaY].setValue(""); //ta sama czynnoœæ w ablicy propertisow

                    this.szachownica[aktualnaPozycjaX + pomoc2][h] = 0;
                    stringProperties[aktualnaPozycjaX + pomoc2][h].setValue("");
                    // zeruje pole zajmowane przez przeciwnika

                    if (aktualnyGracz == 1 && gdzieRuszycWiersz == 7) {
                        this.szachownica[gdzieRuszycWiersz][gdzieRuszycKolumna] = (aktualnyGracz * 3);
                        stringProperties[gdzieRuszycWiersz][gdzieRuszycKolumna].setValue(Integer.toString(aktualnyGracz * 3));

                        //sprawdzenie warunku na krolowa
                    } else if (aktualnyGracz == 2 && gdzieRuszycWiersz == 0) {
                        this.szachownica[gdzieRuszycWiersz][gdzieRuszycKolumna] = (aktualnyGracz * 3);
                        stringProperties[gdzieRuszycWiersz][gdzieRuszycKolumna].setValue(Integer.toString(aktualnyGracz * 3));
                    } else {
                        this.szachownica[gdzieRuszycWiersz][gdzieRuszycKolumna] = aktualnyGracz;
                        stringProperties[gdzieRuszycWiersz][gdzieRuszycKolumna].setValue(Integer.toString(aktualnyGracz));
                    }

                    dodawanieZbic();
                    if (wygrana() == false) {

                        if (this.szachownica[gdzieRuszycWiersz][gdzieRuszycKolumna] == (aktualnyGracz * 3)) {

                            czyIstniejeKolejneBicieKrolowej(gdzieRuszycWiersz, gdzieRuszycKolumna);

                        } else {
                            czyJestBicieWielokrotnePionkiem(gdzieRuszycWiersz, gdzieRuszycKolumna); // jak w nazwie metody
                        }
                    } else {                                    //Niedozwolony ruch(bicie) wraca do 1 w metodzie graj
                        sterowanieKolejnoscia = 1;
                    }
                }

            } else {                                           ////Niedozwolony ruch(bicie) wraca do 1 w metodzie graj
                sterowanieKolejnoscia = 1;
            }
        } else {                                             ////Niedozwolony ruch(bicie) wraca do 1 w metodzie graj
            sterowanieKolejnoscia = 1;

        }

    }

    private void czyJestBicieWielokrotnePionkiem(int gdzieRuszycWiersz, int gdzieRuszycKolumna) { // sprawdza, czy mozliwe
        // jest kolejne bicie, wartosci startowe to punkt decelowy pionka po 1 biciu
        boolean a1 = false; // sprawdza czy mozliwe jest kolejne
        // bicie, jesli tak, wtedy uruchamia
        // ponownie metode 'bicie'
        tabelaBicieWPionkiem[8] = gdzieRuszycWiersz;
        tabelaBicieWPionkiem[9] = gdzieRuszycKolumna;
        tabelaBicieWPionkiem[0] = tabelaBicieWPionkiem[1] = tabelaBicieWPionkiem[2] = tabelaBicieWPionkiem[3]
                = tabelaBicieWPionkiem[4] = tabelaBicieWPionkiem[5] = tabelaBicieWPionkiem[6] = tabelaBicieWPionkiem[7] = 0;
        // sprawdza czy istnieje jesli tak to zmienia boola i
        // ustawia wartosci dx do sprawdzenia

        if (0 <= gdzieRuszycWiersz - 2 && 0 <= gdzieRuszycKolumna - 2) {
            // 1/4 warunek sprawdza, czy pole nie jest poza tablica(pola poza tab, nie maja zadnej wartosci...)
            if (this.szachownica[gdzieRuszycWiersz - 1][gdzieRuszycKolumna - 1] != aktualnyGracz
                    && this.szachownica[gdzieRuszycWiersz - 1][gdzieRuszycKolumna - 1] != aktualnyGracz * 3
                    && this.szachownica[gdzieRuszycWiersz - 1][gdzieRuszycKolumna - 1] != 0
                    && this.szachownica[gdzieRuszycWiersz - 2][gdzieRuszycKolumna - 2] == 0) { // sprawdza
                // 1/4 lini, czy mozna wykonac bicie, jesli tak nadaje wartosci tabelaBicieWPionkiem[0] i
                // tabelaBicieWPionkiem[1] od przyrownania i zmienia flage boola, ktora zacznie pentle while nizej
                a1 = true;
                tabelaBicieWPionkiem[0] = gdzieRuszycWiersz - 2;
                tabelaBicieWPionkiem[1] = gdzieRuszycKolumna - 2;
            }
        }
        if (gdzieRuszycWiersz + 2 < 8 && 0 <= gdzieRuszycKolumna - 2) { // linia
            // 2/4,opis warunku powyzej
            if (this.szachownica[gdzieRuszycWiersz + 1][gdzieRuszycKolumna - 1] != aktualnyGracz
                    && this.szachownica[gdzieRuszycWiersz + 1][gdzieRuszycKolumna - 1] != aktualnyGracz * 3
                    && this.szachownica[gdzieRuszycWiersz + 1][gdzieRuszycKolumna - 1] != 0
                    && this.szachownica[gdzieRuszycWiersz + 2][gdzieRuszycKolumna - 2] == 0) {
                a1 = true;
                tabelaBicieWPionkiem[2] = gdzieRuszycWiersz + 2;
                tabelaBicieWPionkiem[3] = gdzieRuszycKolumna - 2;
            }
        }
        if (gdzieRuszycWiersz + 2 < 8 && gdzieRuszycKolumna + 2 < 8) {
            if (this.szachownica[gdzieRuszycWiersz + 1][gdzieRuszycKolumna + 1] != aktualnyGracz
                    && this.szachownica[gdzieRuszycWiersz + 1][gdzieRuszycKolumna + 1] != aktualnyGracz * 3
                    && this.szachownica[gdzieRuszycWiersz + 1][gdzieRuszycKolumna + 1] != 0
                    && this.szachownica[gdzieRuszycWiersz + 2][gdzieRuszycKolumna + 2] == 0) {
                a1 = true;
                tabelaBicieWPionkiem[4] = gdzieRuszycWiersz + 2;
                tabelaBicieWPionkiem[5] = gdzieRuszycKolumna + 2;
            }
        }
        if (0 <= gdzieRuszycWiersz - 2 && gdzieRuszycKolumna + 2 < 8) {
            if (this.szachownica[gdzieRuszycWiersz - 1][gdzieRuszycKolumna + 1] != aktualnyGracz
                    && this.szachownica[gdzieRuszycWiersz - 1][gdzieRuszycKolumna + 1] != aktualnyGracz * 3
                    && this.szachownica[gdzieRuszycWiersz - 1][gdzieRuszycKolumna + 1] != 0
                    && this.szachownica[gdzieRuszycWiersz - 2][gdzieRuszycKolumna + 2] == 0) {
                a1 = true;
                tabelaBicieWPionkiem[6] = gdzieRuszycWiersz - 2;
                tabelaBicieWPionkiem[7] = gdzieRuszycKolumna + 2;
            }
        }

        if (a1) { // bool zmieniony, a wiec jest drugie bicie, wykonuje je i
            // blokuje jesli wprowadzisz zle wartosci

            sterowanieKolejnoscia = 3;

        } else {
            sterowanieKolejnoscia = 1;
            zmienGracza();

        }

    }

    private void jestBicieWielokrotnePionkiem() {

        // druga pentla sprawdza, czy dobre wartosci podalismy, do
        // ponownego bicia, wartosci d ktore zostaly nadane w if'ach
        // wyzej musza sie rownac ruchowi jaki chcemy wykonac
        //       wprowadzWartosci();
        int gdzieRuszycWiersz = ruch[0];
        int gdzieRuszycKolumna = ruch[1];
        if ((gdzieRuszycWiersz == tabelaBicieWPionkiem[0] || gdzieRuszycWiersz == tabelaBicieWPionkiem[2] || gdzieRuszycWiersz == tabelaBicieWPionkiem[4]
                || gdzieRuszycWiersz == tabelaBicieWPionkiem[6])
                && (gdzieRuszycKolumna == tabelaBicieWPionkiem[1] || gdzieRuszycKolumna == tabelaBicieWPionkiem[3] || gdzieRuszycKolumna == tabelaBicieWPionkiem[5]
                || gdzieRuszycKolumna == tabelaBicieWPionkiem[7])) {
            ruchPionka(tabelaBicieWPionkiem[8], tabelaBicieWPionkiem[9]);

        } else {                                                                     //Nieprawidlowy ruch!        
        }

    }

    private boolean wygrana() {
        if (this.pionkiBialy >= 12) {
            getAktualnyGraczProperties().setValue("WYGRA£ GRACZ BIALY !!!!!!");
            return true;

        } else if (this.pionkiCzarny >= 12) {
            getAktualnyGraczProperties().setValue("WYGRA£ GRACZ CZARNY !!!!!!");
            return true;

        } else {
            return false;
        }
    }

    private void wprowadzenieRuchuKrolowej() {
        int gdzieRuszycWiersz = ruch[0];
        int gdzieRuszycKolumna = ruch[1];
        ruchIBicieKrolowej(gdzieRuszycWiersz, gdzieRuszycKolumna);

    }

    private void ruchIBicieKrolowej(int gdzieRuszycWiersz, int gdzieRuszycKolumna) {

        boolean ruchKrolowa = true;
        boolean bicieKrolowa = false;
        int pomoc3 = -1;
        int pomoc4 = 0;
        int pomoc1, pomoc2;

        pomoc1 = aktualnaPozycjaX;
        pomoc2 = aktualnaPozycjaY;

        boolean sterowanie = true; // zmienna potrzebna do obslugi whileow w if
        // ponizej
        if (gdzieRuszycWiersz < aktualnaPozycjaX && gdzieRuszycKolumna < aktualnaPozycjaY) {
            while (sterowanie) { // w zaleznosci od zmiennych k i j sprawdza czy
                // ruch jest wykonywany w dozwolonej drodze

                pomoc1--;
                pomoc2--;
                if (pomoc1 > 7 || pomoc1 < 0 || pomoc2 < 0 || pomoc2 > 7) {

                    ruchKrolowa = false;
                    sterowanie = false;
                } else if (this.szachownica[pomoc1][pomoc2] != 0) {
                    if (pomoc3 == -1) {

                        if (this.szachownica[pomoc1][pomoc2] != aktualnyGracz && this.szachownica[pomoc1][pomoc2] != aktualnyGracz * 3
                                && pomoc1 != gdzieRuszycWiersz && pomoc2 != gdzieRuszycKolumna) {
                            bicieKrolowa = true;
                            pomoc3 = pomoc1;
                            pomoc4 = pomoc2;
                            ruchKrolowa = false;
                        }
                    } else {
                        bicieKrolowa = false;
                    }
                    ruchKrolowa = false;
                }
                if (pomoc1 == gdzieRuszycWiersz && pomoc2 == gdzieRuszycKolumna) {
                    sterowanie = false;
                }
            }
        } else if (gdzieRuszycWiersz < aktualnaPozycjaX && gdzieRuszycKolumna > aktualnaPozycjaY) {
            while (sterowanie) {

                pomoc1--;
                pomoc2++;
                if (pomoc1 > 7 || pomoc1 < 0 || pomoc2 < 0 || pomoc2 > 7) {

                    ruchKrolowa = false;
                    sterowanie = false;
                } else if (this.szachownica[pomoc1][pomoc2] != 0) {
                    if (pomoc3 == -1) {

                        if (this.szachownica[pomoc1][pomoc2] != aktualnyGracz && this.szachownica[pomoc1][pomoc2] != aktualnyGracz * 3
                                && pomoc1 != gdzieRuszycWiersz && pomoc2 != gdzieRuszycKolumna) {
                            bicieKrolowa = true;
                            pomoc3 = pomoc1;
                            pomoc4 = pomoc2;
                            ruchKrolowa = false;
                        }
                    } else {
                        bicieKrolowa = false;
                    }
                    ruchKrolowa = false;
                }
                if (pomoc1 == gdzieRuszycWiersz && pomoc2 == gdzieRuszycKolumna) {
                    sterowanie = false;
                }

            }

        } else if (gdzieRuszycWiersz > aktualnaPozycjaX && gdzieRuszycKolumna > aktualnaPozycjaY) {
            while (sterowanie) {
                pomoc1++;
                pomoc2++;
                if (pomoc1 > 7 || pomoc1 < 0 || pomoc2 < 0 || pomoc2 > 7) {

                    ruchKrolowa = false;
                    sterowanie = false;
                } else if (this.szachownica[pomoc1][pomoc2] != 0) {
                    if (pomoc3 == -1) {

                        if (this.szachownica[pomoc1][pomoc2] != aktualnyGracz && this.szachownica[pomoc1][pomoc2] != aktualnyGracz * 3
                                && pomoc1 != gdzieRuszycWiersz && pomoc2 != gdzieRuszycKolumna) {
                            bicieKrolowa = true;
                            pomoc3 = pomoc1;
                            pomoc4 = pomoc2;
                            ruchKrolowa = false;
                        }
                    } else {
                        bicieKrolowa = false;
                    }
                    ruchKrolowa = false;
                }
                if (pomoc1 == gdzieRuszycWiersz && pomoc2 == gdzieRuszycKolumna) {
                    sterowanie = false;
                }

            }
        } else if (gdzieRuszycWiersz > aktualnaPozycjaX && gdzieRuszycKolumna < aktualnaPozycjaY) {
            while (sterowanie) {

                pomoc1++;
                pomoc2--;
                if (pomoc1 > 7 || pomoc1 < 0 || pomoc2 < 0 || pomoc2 > 7) {

                    ruchKrolowa = false;
                    sterowanie = false;
                } else if (this.szachownica[pomoc1][pomoc2] != 0) {
                    if (pomoc3 == -1) {

                        if (this.szachownica[pomoc1][pomoc2] != aktualnyGracz && this.szachownica[pomoc1][pomoc2] != aktualnyGracz * 3
                                && pomoc1 != gdzieRuszycWiersz && pomoc2 != gdzieRuszycKolumna) {
                            bicieKrolowa = true;
                            pomoc3 = pomoc1;
                            pomoc4 = pomoc2;
                            ruchKrolowa = false;
                        }
                    } else {
                        bicieKrolowa = false;
                    }
                    ruchKrolowa = false;
                }
                if (pomoc1 == gdzieRuszycWiersz && pomoc2 == gdzieRuszycKolumna) {
                    sterowanie = false;
                }

            }
        } else {
            if (this.szachownica[pomoc1][pomoc2] != 0) {
                ruchKrolowa = false;

            }

        }

        if (ruchKrolowa) {
            this.szachownica[gdzieRuszycWiersz][gdzieRuszycKolumna] = this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY];
            stringProperties[gdzieRuszycWiersz][gdzieRuszycKolumna].setValue(Integer.toString(this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY]));
            this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY] = 0;

            stringProperties[aktualnaPozycjaX][aktualnaPozycjaY].setValue("");

            zmienGracza();
            sterowanieKolejnoscia = 1;

        } else if (bicieKrolowa) {
            this.szachownica[gdzieRuszycWiersz][gdzieRuszycKolumna] = this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY];
            stringProperties[gdzieRuszycWiersz][gdzieRuszycKolumna].setValue(Integer.toString(this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY]));
            this.szachownica[aktualnaPozycjaX][aktualnaPozycjaY] = 0;
            stringProperties[aktualnaPozycjaX][aktualnaPozycjaY].setValue("");
            this.szachownica[pomoc3][pomoc4] = 0;
            stringProperties[pomoc3][pomoc4].setValue("");

            dodawanieZbic();
            if (wygrana() == false) {         //jesli jest wygrana to nie sprawdza juz nastepnego warunku i zapobiega wyswietlaniu
                //komuniatu o nastepnym ruchy itp.
                czyIstniejeKolejneBicieKrolowej(gdzieRuszycWiersz, gdzieRuszycKolumna); // sprawdza,
            }// czy po biciu jest mozliwe kolejne bicie
        } else {                                  //B³êdny ruch

            sterowanieKolejnoscia = 1;

        }

    }

    private void czyIstniejeKolejneBicieKrolowej(int k, int l) { // wywoluje
        // metode "pentelka" zmieniajac pierwsze 2 parametry,
        boolean jest1, jest2, jest3, jest4;
        jest1 = pentelkaSprawdzajacaKolejneBicieKrolowej(1, 1, k, l); // dzieki
        // czemu robimy ruch w kazdym kierunku po skosie

        jest2 = pentelkaSprawdzajacaKolejneBicieKrolowej(-1, 1, k, l);

        jest3 = pentelkaSprawdzajacaKolejneBicieKrolowej(1, -1, k, l);

        jest4 = pentelkaSprawdzajacaKolejneBicieKrolowej(-1, -1, k, l);

        if (jest1 == true || jest2 == true || jest3 == true || jest4 == true) { //jesli kktores wywolanie zwrocilo true
            //to uznajemy ze jest bicie 
        } else {                           //jesli wszystkie byly false to konczymy          
            zmienGracza();
            sterowanieKolejnoscia = 1;

        }

    }

    private boolean pentelkaSprawdzajacaKolejneBicieKrolowej(int a, int b, int k1, int l1) { // sprawdza
        // czy na drodze jest pionek przeciwnika, jesli tak to sprawdza
        boolean jest = false;
        int j = l1, pomo = 0; // czy nastepne pole za pionkiem jest wolne, jesli
        // znowu tak, to wywoluje

        for (int i = k1; 0 <= i && i <= 7; i += a) { // metode krolowa z info,
            // ze mozliwe jest
            // kolejne bicie

            if (this.szachownica[i][j] == aktualnyGracz || this.szachownica[i][j] == aktualnyGracz * 3) {
                pomo++;
                if (pomo >= 2) {
                    i = 7;
                }
            }
            if (this.szachownica[i][j] != aktualnyGracz && this.szachownica[i][j] != aktualnyGracz * 3 && this.szachownica[i][j] != 0
                    && 0 < i && i < 7 && 0 < j && j < 7) {
                if (this.szachownica[i + a][j + b] == 0) {
                    jest = true;
                    jestKolejneBicieKrolowej(k1, l1);
                } else {
                    i = -10;
                }
            }
            if (0 < j && j < 7) {
                j += b;
            } else {
                i = -10;
            }
        }
        return jest;
    }

    private void jestKolejneBicieKrolowej(int k1, int l1) {
        aktualnaPozycjaX = k1;             //jest mozliwe bicie, zmienia sterowanie
        aktualnaPozycjaY = l1;
        sterowanieKolejnoscia = 5;
    }

    private void jestKolejneBicieKrolowej2() {
        int k2, l2;
        boolean y = true;
        int gdzieRuszycWiersz = ruch[0];
        int gdzieRuszycKolumna = ruch[1];

        k2 = gdzieRuszycWiersz - aktualnaPozycjaX;
        l2 = gdzieRuszycKolumna - aktualnaPozycjaY;
        if (0 < k2 && 0 < l2) { // ++
            y = sprawdzWartosciKolejnegoBiciaKrolowej(1, 1, aktualnaPozycjaX, aktualnaPozycjaY, gdzieRuszycKolumna, gdzieRuszycWiersz);
        } else if (0 < k2 && 0 > l2) { // +-
            y = sprawdzWartosciKolejnegoBiciaKrolowej(1, -1, aktualnaPozycjaX, aktualnaPozycjaY, gdzieRuszycKolumna, gdzieRuszycWiersz);
        } else if (0 > k2 && 0 < l2) { // -+
            y = sprawdzWartosciKolejnegoBiciaKrolowej(-1, 1, aktualnaPozycjaX, aktualnaPozycjaY, gdzieRuszycKolumna, gdzieRuszycWiersz);
        } else if (0 > k2 && 0 > l2) { // --
            y = sprawdzWartosciKolejnegoBiciaKrolowej(-1, -1, aktualnaPozycjaX, aktualnaPozycjaY, gdzieRuszycKolumna, gdzieRuszycWiersz);
        }
        if (y) {                     //Niepoprawny ruch, wykonaj ponownie, metoda jestKolejneBicieKrolowej2() bedzie uruchumiona od nowa
        } else {
            ruchIBicieKrolowej(aktualnaPozycjaX, aktualnaPozycjaY);
        }

    }

    private boolean sprawdzWartosciKolejnegoBiciaKrolowej(int a, int b, int k1, int j, int gdzieRuszycKolumna, int gdzieRuszycWiersz) {
        int c = 0, d = 0;
        for (int i = k1; 0 <= i && i <= 7; i += a) {
            if (this.szachownica[i][j] != 0) {
                d++;
            }
            if (this.szachownica[i][j] != aktualnyGracz && this.szachownica[i][j] != aktualnyGracz * 3 && this.szachownica[i][j] != 0) {
                c++;
            }
            if (i == gdzieRuszycWiersz && j == gdzieRuszycKolumna && 0 < c && c < 2 && d < 3) {
                return false;
            }
            if (i == gdzieRuszycWiersz) {
                i = -10;
            }
            j += b;
        }
        return true;
    }

    private void dodawanieZbic() { // dodaje pionki po ich zbiciu do
        // wyniku - uzywana po biciu pionka
        // i krolowej
        if (aktualnyGracz == 2) {
            this.pionkiBialy++;
        } else {
            this.pionkiCzarny++;
        }
    }

    public void setSzachownica(int[][] szachownica) {
        this.szachownica = szachownica;
    }

    public void setAktualnyGraczProperties(StringProperty aktualnyGraczProperties) {
        this.aktualnyGraczProperties = aktualnyGraczProperties;
    }

    public void setAktualnyGracz(int aktualnyGracz) {
        this.aktualnyGracz = aktualnyGracz;
    }

    public void setStringProperties(StringProperty[][] stringProperties) {
        this.stringProperties = stringProperties;
    }

    public StringProperty[][] getStringProperties() {
        return stringProperties;
    }

    public StringProperty getAktualnyGraczProperties() {
        return aktualnyGraczProperties;
    }

}

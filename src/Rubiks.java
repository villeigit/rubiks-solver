package src;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Virtuaalinen rubikin kuutio ja ratkaisija
 * 
 * @author Ville Ihalainen
 * @version 0.1
 */
public class Rubiks {

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {

        Kuutio kuutio = new Kuutio();
        kuutio.tulostaKuutio();
        System.out.println("");
        // kuutio.liikutaD();
        // kuutio.tulostaKuutio();

        Scanner lukija = new Scanner(System.in);
        while (true) {

            System.out.println("Kiertosuunnat myötäpäivään: F, B, U, D, R, L");
            System.out.println("Kiertosuunnat vastapäivään: f, b, u, d, r, l");
            System.out.println("Resetointi: UUSI");
            System.out.print("Anna kiertosuunta: ");

            String syote = lukija.nextLine();
            if (syote.equals("D")) {
                kuutio.liikutaD();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("d")) {
                kuutio.liikutaDi();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("U")) {
                kuutio.liikutaU();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("u")) {
                kuutio.liikutaUi();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("R")) {
                kuutio.liikutaR();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("r")) {
                kuutio.liikutaRi();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("L")) {
                kuutio.liikutaL();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("l")) {
                kuutio.liikutaLi();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("F")) {
                kuutio.liikutaF();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("f")) {
                kuutio.liikutaFi();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("B")) {
                kuutio.liikutaB();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("b")) {
                kuutio.liikutaB();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("UUSI")) {
                kuutio = new Kuutio();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("kulma")) {
                kuutio.ratkaiseAlareunaYlakulma();
                System.out.println("Ratkaistaan...");
                kuutio.tulostaKuutio();
                System.out.println("Valmis");
            }

            // TODO Testi Whitecrossin tarkastukseen, poista kun valmista
            if (syote.equals("wc")) {
                if (kuutio.tarkastaWhitecross()) {
                    System.out.println("Whitecross!");
                } else
                    System.out.println("Ei Whitecrossia");
            }
            // TODO Testi Alareunan tarkastukseen, poista kun valmista
            if (syote.equals("ala")) {
                if (kuutio.tarkastaAlareuna()) {
                    System.out.println("Alareuna!");
                } else
                    System.out.println("Ei alareunaa");
            }
            // TODO Testi Keskireunan tarkastukseen, poista kun valmista
            if (syote.equals("keski")) {
                if (kuutio.tarkastaKeskireuna()) {
                    System.out.println("Keskireuna!");
                } else
                    System.out.println("Ei keskireunaa");
            }
            // TODO Testi Yellowcrossin tarkastukseen, poista kun valmista
            if (syote.equals("keski")) {
                if (kuutio.tarkastaYellowcross()) {
                    System.out.println("Yellowcross!");
                } else
                    System.out.println("Ei Yellowcrossia");
            }
            // TODO Testi Yellowcrossin tarkastukseen, poista kun valmista
            if (syote.equals("kuutio")) {
                if (kuutio.tarkastaKuutio()) {
                    System.out.println("Oikein!");
                } else
                    System.out.println("Väärin");
            }

            // TODO Testi Yellowcrossin tarkastukseen, poista kun valmista
            if (syote.equals("ratkaise")) {
                System.out.println("Ratkaistaan...");
                kuutio.ratkaiseWhitecross();
                System.out.println("Valmis...");
                kuutio.tulostaKuutio();
                ;
            }
        }
    }

    /**
     * Kuutiolla on aina tila (kuution asento), joka on tallennettu 2-ulotteiseen
     * taulukkoon
     * Kuution metodeilla tilaa voidaan muuttaa tai tulostaa
     */

    static class Kuutio {

        private int[][] tila;

        // Luodaan värit
        // Declaring ANSI_RESET so that we can reset the color
        public static final String ANSI_RESET = "\u001B[0m";

        public static final String ANSI_YELLOW = "\u001B[43m";
        public static final String ANSI_BLUE = "\u001B[44m";
        public static final String ANSI_RED = "\u001B[41m";
        public static final String ANSI_GREEN = "\u001B[42m";
        public static final String ANSI_PURPLE = "\u001B[45m";
        public static final String ANSI_WHITE = "\u001B[47m";

        public Kuutio() {
            this.tila = luoKuutio();
        }

        /**
         * Luodaan halutun kokoinen kuutio
         * Numerot vastaavat värejä seuraavasti:
         * 0 = tyhjä
         * 1 = keltainen
         * 2 = sininen
         * 3 = punainen
         * 4 = vihreä
         * 5 = oranssi (ei onnistu ANSI:lla konsolista) --> violetti
         * 6 = valkoinen
         * 
         * Värit ja kuution asettelu vastaavat oikeaa rubikin kuutiota
         * Järjestys perustuu "Beginner's Method" -algoritmiin, jossa valkoinen sivu
         * pidetään aina alimpana ja keltainen ylöspäin
         * Tämä on yleinen periaate myös muissa ratkaisuun käytettävissä algoritmeissa
         * 
         * @param koko kuution sivun pituus
         */
        private int[][] luoKuutio() {
            int[][] kuutio = {
                    { 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0 },
                    { 5, 5, 5, 1, 1, 1, 3, 3, 3, 6, 6, 6 },
                    { 5, 5, 5, 1, 1, 1, 3, 3, 3, 6, 6, 6 },
                    { 5, 5, 5, 1, 1, 1, 3, 3, 3, 6, 6, 6 },
                    { 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0 }
            };
            return kuutio;
        }

        /**
         * Tulostetaan parametrina annettu kuutio
         * Tulostetaan numerot ja jos luku on olla(tyhjä) tulostetaan tyhjää
         * 
         * @param tulostettava tulostettava kuutio
         */
        public void tulostaKuutio() {
            for (int i = 0; i < this.tila.length; i++) {
                for (int j = 0; j < this.tila[i].length; j++) {
                    if (this.tila[i][j] == 0) {
                        System.out.print("   ");
                    } else {
                        tulostaVari(this.tila[i][j]);
                    }
                }
                System.out.println("");
            }
        }

        /**
         * Tulostetaan numerot värillisenä
         * Numerot vastaavat värejä seuraavasti:
         * 0 = tyhjä
         * 1 = keltainen
         * 2 = sininen
         * 3 = punainen
         * 4 = vihreä
         * 5 = oranssi (ei onnistu ANSI:lla konsolista) --> violetti
         * 6 = valkoinen
         * 
         * 
         */
        // TODO: Tulostus ei toimi Windowsin konsolissa
        public void tulostaVari(int numero) {
            if (numero == 1) {
                System.out.print(ANSI_YELLOW + " " + numero + " " + ANSI_RESET);
            }
            if (numero == 2) {
                System.out.print(ANSI_BLUE + " " + numero + " " + ANSI_RESET);
            }
            if (numero == 3) {
                System.out.print(ANSI_RED + " " + numero + " " + ANSI_RESET);
            }
            if (numero == 4) {
                System.out.print(ANSI_GREEN + " " + numero + " " + ANSI_RESET);
            }
            if (numero == 5) {
                System.out.print(ANSI_PURPLE + " " + numero + " " + ANSI_RESET);
            }
            if (numero == 6) {
                System.out.print(ANSI_WHITE + " " + numero + " " + ANSI_RESET);
            }
        }

        /**
         * Liikuttaa kuution alaosaa myötäpäivään yhden kierroksen
         * 
         * Vaikutukset riveillä:
         * [0] 3 4 5
         * [3] 0 8 9 10 11
         * [4] 0 8 9 11 (10 ei muutu koska keskellä)
         * [5] 0 8 9 10 11
         * [8] 3 4 5
         */
        public void liikutaD() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }

            // rivi 0 muutokset
            uusiTila[0][3] = this.tila[5][8];
            uusiTila[0][4] = this.tila[4][8];
            uusiTila[0][5] = this.tila[5][8];

            // rivi 3 muutokset
            uusiTila[3][0] = this.tila[0][5];
            uusiTila[3][8] = this.tila[8][5];
            uusiTila[3][9] = this.tila[5][9];
            uusiTila[3][10] = this.tila[4][9];
            uusiTila[3][11] = this.tila[3][9];

            // rivi 4 muutokset
            uusiTila[4][0] = this.tila[0][4];
            uusiTila[4][8] = this.tila[8][4];
            uusiTila[4][9] = this.tila[5][10];
            uusiTila[4][11] = this.tila[3][10];

            // rivi 5 muutokset
            uusiTila[5][0] = this.tila[0][3];
            uusiTila[5][8] = this.tila[8][3];
            uusiTila[5][9] = this.tila[5][11];
            uusiTila[5][10] = this.tila[4][11];
            uusiTila[5][11] = this.tila[3][11];

            // rivi 8 muutokset
            uusiTila[8][3] = this.tila[3][0];
            uusiTila[8][4] = this.tila[4][0];
            uusiTila[8][5] = this.tila[5][0];

            this.tila = uusiTila;
        }

        /**
         * Liikuttaa kuution yläosaa myötäpäivään yhden kierroksen
         *
         * Vaikutukset riveillä:
         * [2] 3 4 5
         * [3] 2 3 4 5 6
         * [4] 2 3 5 6 (4 ei muutu koska keskellä)
         * [5] 2 3 4 5 6
         * [6] 3 4 5
         */
        public void liikutaU() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }

            // rivi 2 muutokset
            uusiTila[2][3] = this.tila[5][2];
            uusiTila[2][4] = this.tila[4][2];
            uusiTila[2][5] = this.tila[3][2];

            // rivi 3 muutokset
            uusiTila[3][2] = this.tila[6][3];
            uusiTila[3][3] = this.tila[5][3];
            uusiTila[3][4] = this.tila[4][3];
            uusiTila[3][5] = this.tila[3][3];
            uusiTila[3][6] = this.tila[2][3];

            // rivi 4 muutokset
            uusiTila[4][2] = this.tila[6][4];
            uusiTila[4][3] = this.tila[5][4];
            uusiTila[4][5] = this.tila[3][4];
            uusiTila[4][6] = this.tila[2][4];

            // rivi 5 muutokset
            uusiTila[5][2] = this.tila[6][5];
            uusiTila[5][3] = this.tila[5][5];
            uusiTila[5][4] = this.tila[4][5];
            uusiTila[5][5] = this.tila[3][5];
            uusiTila[5][6] = this.tila[2][5];

            // rivi 6 muutokset
            uusiTila[6][3] = this.tila[5][6];
            uusiTila[6][4] = this.tila[4][6];
            uusiTila[6][5] = this.tila[3][6];

            this.tila = uusiTila;
        }

        /**
         * Liikuttaa kuution oikeaa puolta myötäpäivään yhden kierroksen
         * 
         * Vaikutukset riveillä:
         * [0] 5
         * [1] 5
         * [2] 5
         * [3] 5 6 7 8 9
         * [4] 5 6 - 8 9 10 11 (7 ei muutu, keskellä)
         * [5] 5 6 7 8 9
         * [6] 5
         * [7] 5
         * [8] 5
         */
        public void liikutaR() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }

            // rivi 0
            uusiTila[0][5] = this.tila[3][5];

            // rivi 1
            uusiTila[1][5] = this.tila[4][5];

            // rivi 2
            uusiTila[2][5] = this.tila[5][5];

            // rivi 3
            uusiTila[3][5] = this.tila[6][5];
            uusiTila[3][6] = this.tila[5][6];
            uusiTila[3][7] = this.tila[4][6];
            uusiTila[3][8] = this.tila[3][6];
            uusiTila[3][9] = this.tila[2][5];

            // rivi 4
            uusiTila[4][5] = this.tila[7][5];
            uusiTila[4][6] = this.tila[5][7];
            uusiTila[4][8] = this.tila[3][7];
            uusiTila[4][9] = this.tila[1][5];

            // rivi 5
            uusiTila[5][5] = this.tila[8][5];
            uusiTila[5][6] = this.tila[5][8];
            uusiTila[5][7] = this.tila[4][8];
            uusiTila[5][8] = this.tila[3][8];
            uusiTila[5][9] = this.tila[0][5];

            // rivi 6
            uusiTila[6][5] = this.tila[5][9];

            // rivi 7
            uusiTila[7][5] = this.tila[4][9];

            // rivi 8
            uusiTila[8][5] = this.tila[3][9];

            this.tila = uusiTila;
        }

        /**
         * Liikuttaa kuution vasenta puolta myötäpäivään yhden kierroksen
         * 
         * Vaikutukset riveillä:
         * [0] 3
         * [1] 3
         * [2] 3
         * [3] 0 1 2 3 11
         * [4] 0 - 2 3 11 (1 ei muutu, keskellä)
         * [5] 0 1 2 3 11
         * [7] 3
         * [8] 3
         */
        public void liikutaL() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }

            // rivi 0
            uusiTila[0][3] = this.tila[5][11];

            // rivi 1
            uusiTila[1][3] = this.tila[4][11];

            // rivi 2
            uusiTila[2][3] = this.tila[3][11];

            // rivi 3
            uusiTila[3][0] = this.tila[5][0];
            uusiTila[3][1] = this.tila[4][0];
            uusiTila[3][2] = this.tila[3][0];
            uusiTila[3][3] = this.tila[0][3];
            uusiTila[3][11] = this.tila[8][3];

            // rivi 4
            uusiTila[4][0] = this.tila[5][1];
            uusiTila[4][2] = this.tila[3][1];
            uusiTila[4][3] = this.tila[1][3];
            uusiTila[4][11] = this.tila[7][3];

            // rivi 5
            uusiTila[5][0] = this.tila[5][2];
            uusiTila[5][1] = this.tila[4][2];
            uusiTila[5][2] = this.tila[3][2];
            uusiTila[5][3] = this.tila[2][3];
            uusiTila[5][11] = this.tila[6][3];

            // rivi 6
            uusiTila[6][3] = this.tila[3][3];

            // rivi 7
            uusiTila[7][3] = this.tila[4][3];

            // rivi 8
            uusiTila[8][3] = this.tila[5][3];

            this.tila = uusiTila;
        }

        /**
         * Liikuttaa kuution etupuolta myötäpäivään yhden kierroksen
         * 
         * Vaikutukset riveillä:
         * [5] 0 1 2 3 4 5 6 7 8 9 10 11
         * [6] - - - 3 4 5
         * [7] - - - 3 - 5
         * [8] - - - 3 4 5
         */

        public void liikutaF() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }

            // rivi 5
            uusiTila[5][0] = this.tila[5][9];
            uusiTila[5][1] = this.tila[5][10];
            uusiTila[5][2] = this.tila[5][11];
            uusiTila[5][3] = this.tila[5][0];
            uusiTila[5][4] = this.tila[5][1];
            uusiTila[5][5] = this.tila[5][2];
            uusiTila[5][6] = this.tila[5][3];
            uusiTila[5][7] = this.tila[5][4];
            uusiTila[5][8] = this.tila[5][5];
            uusiTila[5][9] = this.tila[5][6];
            uusiTila[5][10] = this.tila[5][7];
            uusiTila[5][11] = this.tila[5][8];

            // rivi 6
            uusiTila[6][3] = this.tila[8][3];
            uusiTila[6][4] = this.tila[7][3];
            uusiTila[6][5] = this.tila[6][3];

            // rivi 7
            uusiTila[7][3] = this.tila[8][4];
            uusiTila[7][5] = this.tila[6][4];

            // rivi 8
            uusiTila[8][3] = this.tila[8][5];
            uusiTila[8][4] = this.tila[7][5];
            uusiTila[8][5] = this.tila[6][5];

            this.tila = uusiTila;
        }

        /**
         * Liikuttaa kuution etupuolta myötäpäivään yhden kierroksen
         * 
         * Vaikutukset riveillä:
         * [0] - - - 3 4 5
         * [1] - - - 3 - 5
         * [2] - - - 3 4 5
         * [3] 0 1 2 3 4 5 6 7 8 9 10 11
         */

        public void liikutaB() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }

            // rivi 0
            uusiTila[0][3] = this.tila[2][3];
            uusiTila[0][4] = this.tila[1][3];
            uusiTila[0][5] = this.tila[0][3];

            // rivi 1
            uusiTila[1][3] = this.tila[2][4];
            uusiTila[1][5] = this.tila[0][4];

            // rivi 2
            uusiTila[2][3] = this.tila[2][5];
            uusiTila[2][4] = this.tila[1][5];
            uusiTila[2][5] = this.tila[0][5];

            // rivi 3
            uusiTila[3][0] = this.tila[3][3];
            uusiTila[3][1] = this.tila[3][4];
            uusiTila[3][2] = this.tila[3][5];
            uusiTila[3][3] = this.tila[3][6];
            uusiTila[3][4] = this.tila[3][7];
            uusiTila[3][5] = this.tila[3][8];
            uusiTila[3][6] = this.tila[3][9];
            uusiTila[3][7] = this.tila[3][10];
            uusiTila[3][8] = this.tila[3][11];
            uusiTila[3][9] = this.tila[3][0];
            uusiTila[3][10] = this.tila[3][1];
            uusiTila[3][11] = this.tila[3][2];

            this.tila = uusiTila;
        }

        /**
         * Liikuttaa kuution etupuolta VASTApäivään yhden kierroksen
         * Toteutus kolmella myötäpäivään pyöräytyksellä
         */

        public void liikutaFi() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }
            liikutaF();
            liikutaF();
            liikutaF();
        }

        /**
         * Liikuttaa kuution takapuolta VASTApäivään yhden kierroksen
         * Toteutus kolmella myötäpäivään pyöräytyksellä
         */

        public void liikutaBi() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }
            liikutaB();
            liikutaB();
            liikutaB();
        }

        /**
         * Liikuttaa kuution oikeaa puolta VASTApäivään yhden kierroksen
         * Toteutus kolmella myötäpäivään pyöräytyksellä
         */

        public void liikutaRi() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }
            liikutaR();
            liikutaR();
            liikutaR();
        }

        /**
         * Liikuttaa kuution vasenta puolta VASTApäivään yhden kierroksen
         * Toteutus kolmella myötäpäivään pyöräytyksellä
         */

        public void liikutaLi() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }
            liikutaL();
            liikutaL();
            liikutaL();
        }

        /**
         * Liikuttaa kuution yläpuolta VASTApäivään yhden kierroksen
         * Toteutus kolmella myötäpäivään pyöräytyksellä
         */

        public void liikutaUi() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }
            liikutaU();
            liikutaU();
            liikutaU();
        }

        /**
         * Liikuttaa kuution alapuolta VASTApäivään yhden kierroksen
         * Toteutus kolmella myötäpäivään pyöräytyksellä
         */

        public void liikutaDi() {
            int[][] uusiTila = new int[tila.length][];
            for (int i = 0; i < tila.length; i++) {
                uusiTila[i] = tila[i].clone();
            }
            liikutaD();
            liikutaD();
            liikutaD();
        }

        /**
         * "Whitecross":in tarkastaminen
         * Whitecross = tilanne jossa kuution pohjassa on valkoinen risti ja ristin osat
         * ovat oikeilla sivuilla (sivut oikein kun sivun keskimmäinen pala ja
         * keskimmäinen alin pala ovat samat)
         * Tarkastaa pohjan ja sivujen toteutumisen ja jos ei toteudu palautaa FALSE
         * Jos toteutuu, palauttaa TRUE
         */
        public boolean tarkastaWhitecross() {

            // pohjan tarkastus
            if (!(tila[3][10] == 6 && tila[4][9] == 6 && tila[4][11] == 6 && tila[5][10] == 6)) {
                return false;
            }
            // sivujen tarkastus
            if (!(tila[0][4] == tila[1][4] && tila[4][0] == tila[4][1] && tila[4][7] == tila[4][8]
                    && tila[7][4] == tila[8][4])) {
                return false;
            }
            return true;
        }

        /**
         * Alareunan tilanteen tarkastus
         * Whitecrossia seuraavaa vaihe, jossa kuution pohja (valkoinen) ja kaikki
         * alimmat rivit sivuilta ovat oikein
         */
        public boolean tarkastaAlareuna() {

            // Whitecrossin tarkastus riittää pohjan tarkastamiseksi
            if (!tarkastaWhitecross()) {
                return false;
            }
            // 1 sivun tarkastus
            if (!(tila[0][3] == tila[0][4] && tila[0][4] == tila[0][5])) {
                return false;
            }
            // 2 sivun tarkastus
            if (!(tila[3][0] == tila[4][0] && tila[4][0] == tila[5][0])) {
                return false;
            }
            // 3 sivun tarkastus
            if (!(tila[3][11] == tila[4][11] && tila[4][11] == tila[5][11])) {
                return false;
            }
            // 4 sivun tarkastus
            if (!(tila[8][3] == tila[8][4] && tila[8][4] == tila[8][5])) {
                return false;
            }
            return true;
        }

        /**
         * Keskireunan tilanteen tarkastus
         * Alareunaa seuraavaa vaihe, jossa kuution pohja (valkoinen) ja kaksi alinta
         * riviä sivuilta ovat oikein
         */
        public boolean tarkastaKeskireuna() {

            // Tarkastetaan alareuna
            if (!tarkastaAlareuna()) {
                return false;
            }
            // 1 sivun tarkastus
            if (!(tila[1][3] == tila[1][4] && tila[1][4] == tila[1][5])) {
                return false;
            }
            // 2 sivun tarkastus
            if (!(tila[3][1] == tila[4][1] && tila[4][1] == tila[5][1])) {
                return false;
            }
            // 3 sivun tarkastus
            if (!(tila[3][7] == tila[4][7] && tila[4][7] == tila[5][7])) {
                return false;
            }
            // 4 sivun tarkastus
            if (!(tila[7][3] == tila[7][4] && tila[7][4] == tila[7][5])) {
                return false;
            }
            return true;
        }

        /**
         * "Yellowcrossin":in selvittäminen
         * Yellowcross = tilanne jossa kuution päällä on keltainen risti ja ristin osat
         * ovat oikeilla sivuilla (sivut oikein kun ylärivin keskimmäinen pala ja kaksi
         * alintariviä ovat samaa väriä
         * Tarkastaa keskirivin toteutumisen ja jos ei toteudu palautaa FALSE
         * Jos toteutuu, palauttaa TRUE
         */
        public boolean tarkastaYellowcross() {

            if (!tarkastaKeskireuna()) {
                return false;
            }

            // päällisen tarkastus
            if (!(tila[3][4] == 1 && tila[4][3] == 1 && tila[4][5] == 1 && tila[5][4] == 1)) {
                return false;
            }
            // sivujen tarkastus
            if (!(tila[2][4] == tila[1][4] && tila[4][2] == tila[4][1] && tila[4][6] == tila[4][7]
                    && tila[6][4] == tila[7][4])) {
                return false;
            }
            return true;
        }

        /**
         * Tarkastaa onko kuutio valmis/oikein
         * Jos ei toteudu palauttaa FALSE
         * Jos toteutuu, palauttaa TRUE
         */
        public boolean tarkastaKuutio() {
            if (!tarkastaYellowcross()) {
                return false;
            }

            // 1 sivun tarkastus
            if (!(tila[2][3] == tila[2][4] && tila[2][4] == tila[2][5])) {
                return false;
            }
            // 2 sivun tarkastus
            if (!(tila[3][2] == tila[4][2] && tila[4][2] == tila[5][2])) {
                return false;
            }
            // 3 sivun tarkastus
            if (!(tila[3][6] == tila[4][6] && tila[4][6] == tila[5][6])) {
                return false;
            }
            // 4 sivun tarkastus
            if (!(tila[6][3] == tila[6][4] && tila[6][4] == tila[6][5])) {
                return false;
            }
            return true;
        }

        /*
         * Whitecrossin ratkaisu
         * Silmukkaa toistaa osaratkaisuja siihen asti, että Whitecrossin tarkastus
         * menee läpi
         */
        public void ratkaiseWhitecross() {
            while (!(tarkastaWhitecross())) {
                ratkaiseWhitecrossYla();
                ratkaiseWhitecrossSivu();
                ratkaiseWhitecrossAla();
                this.tulostaKuutio();
            }
        }

        /*
         * Whitecross yläosan ratkaisu
         * Tämä vaihe siirtää kuution yläpuolella olevan valkoisen keskilaatan oikeaan
         * paikkaan kuution alapuolelle
         */
        public void ratkaiseWhitecrossYla() {

            // Tallennetaan tilat tekstimuotoon käsittelyn helpottamiseksi
            // Selite:
            // yla = ylhäällä oleva keskilaatta (ylhäältä katsottuna)
            // ylaSivu = edellistä laatan toinen sivu ylimmällä rivillä sivusta katsottuna

            int yla = tila[3][4];
            int oikea = tila[4][5];
            int ala = tila[5][4];
            int vasen = tila[4][3];
            int ylaSivu = tila[2][4];
            int oikeaSivu = tila[4][6];
            int alaSivu = tila[6][4];
            int vasenSivu = tila[4][2];

            // Sijainnissa "yla" on valkoinen laatta
            if (yla == 6) {
                if (ylaSivu == 5) { // jos laatan toinen sivu oranssi/violetti käännetään yläosaa kolmesti, jotta
                                    // voidaan kääntää laatta oikealle paikalleen
                    liikutaU();
                    liikutaU();
                    liikutaU();
                    kaannaWhitecrossYla(ylaSivu); // kääntöjen jälkeen laatta oikealle paikalleen
                }

                // vastaavat toimenpiteet vihreälle laatalle
                else if (ylaSivu == 4) {
                    kaannaWhitecrossYla(ylaSivu);
                } else if (ylaSivu == 3) {
                    liikutaU();
                    kaannaWhitecrossYla(ylaSivu);
                } else if (ylaSivu == 2) {
                    liikutaU();
                    liikutaU();
                    kaannaWhitecrossYla(ylaSivu);
                }

            }
            // Samat toimenpiteet, nyt valkoinen laatta on oikealla
            else if (oikea == 6) {
                if (oikeaSivu == 5) {
                    liikutaU();
                    liikutaU();
                    kaannaWhitecrossYla(oikeaSivu);
                } else if (oikeaSivu == 4) {
                    liikutaU();
                    liikutaU();
                    liikutaU();
                    kaannaWhitecrossYla(oikeaSivu);
                } else if (oikeaSivu == 3) {
                    kaannaWhitecrossYla(oikeaSivu);
                } else if (oikeaSivu == 2) {
                    liikutaU();
                    kaannaWhitecrossYla(oikeaSivu);
                }
            }
            // Valkoinen laatta alhaalla
            else if (ala == 6) {
                if (alaSivu == 5) {
                    liikutaU();
                    liikutaU();
                    liikutaU();
                    kaannaWhitecrossYla(alaSivu);
                } else if (alaSivu == 4) {
                    liikutaU();
                    liikutaU();
                    kaannaWhitecrossYla(alaSivu);
                } else if (alaSivu == 3) {
                    liikutaU();
                    kaannaWhitecrossYla(alaSivu);
                } else if (alaSivu == 2) {
                    kaannaWhitecrossYla(alaSivu);
                }
            }
            // Valkoinen laatta vasemmalla
            else if (vasen == 6) {
                if (vasenSivu == 5) {
                    kaannaWhitecrossYla(vasenSivu);
                } else if (vasenSivu == 4) {
                    liikutaU();
                    liikutaU();
                    liikutaU();
                    kaannaWhitecrossYla(vasenSivu);
                } else if (vasenSivu == 3) {
                    liikutaU();
                    liikutaU();
                    kaannaWhitecrossYla(vasenSivu);
                } else if (vasenSivu == 2) {
                    liikutaU();
                    kaannaWhitecrossYla(vasenSivu);
                }
            }

        }

        /*
         * Käännetään ylhäällä oleva valkoinen laatta vastakkaiselle puolelle
         */

        public void kaannaWhitecrossYla(int vari) {

            if (vari == 2) {
                liikutaF();
                liikutaF();
            }
            if (vari == 3) {
                liikutaR();
                liikutaR();
            }

            if (vari == 4) {
                liikutaB();
                liikutaB();
            }

            if (vari == 5) {
                liikutaL();
                liikutaL();
            }

        }

        /*
         * Ratkaistaan Whitecross tilanteessa, jossa valkoinen laatta on jollakin
         * kuution sivuista, keskimmäisellä rivillä reunassa.
         * HUOM. Tämä siirtää vain sivulla olevan laatan ylös, josta
         * "ratkaiseWhitecrossYla" hoitaa ratkaisun loppuun
         * 
         */
        public void ratkaiseWhitecrossSivu() {

            // Merkitään tilat lukemisen helpottamiseksi
            int etuVasen = tila[7][3];
            int etuOikea = tila[7][5];
            int vasenVasen = tila[3][1];
            int vasenOikea = tila[5][1];
            int takaVasen = tila[1][5];
            int takaOikea = tila[1][3];
            int oikeaVasen = tila[5][7];
            int oikeaOikea = tila[3][7];

            int etuKeski = tila[6][4];
            int vasenKeski = tila[4][2];
            int takaKeski = tila[2][4];
            int oikeaKeski = tila[4][6];

            // Etusivulla on valkoinen laatta vasemmassa reunassa, siirretään liikesarjalla
            // kuution yläsivulle
            if (etuVasen == 6) {
                liikutaLi();
                liikutaU();
                liikutaL();
            } else if (etuOikea == 6) {
                liikutaR();
                liikutaU();
                liikutaRi();
            } else if (vasenVasen == 6) {
                liikutaBi();
                liikutaU();
                liikutaB();
            } else if (vasenOikea == 6) {
                liikutaF();
                liikutaU();
                liikutaFi();
            } else if (takaVasen == 6) {
                liikutaRi();
                liikutaU();
                liikutaR();
            } else if (takaOikea == 6) {
                liikutaL();
                liikutaU();
                liikutaLi();
            } else if (oikeaVasen == 6) {
                liikutaFi();
                liikutaU();
                liikutaF();
            } else if (oikeaOikea == 6) {
                liikutaB();
                liikutaU();
                liikutaBi();
            } else if (etuKeski == 6) {
                liikutaF();
                liikutaR();
                liikutaFi();
            } else if (vasenKeski == 6) {
                liikutaL();
                liikutaF();
                liikutaLi();
            } else if (takaKeski == 6) {
                liikutaB();
                liikutaL();
                liikutaBi();
            } else if (oikeaKeski == 6) {
                liikutaR();
                liikutaB();
                liikutaRi();
            }
        }

        /*
         * Ratkaistaan Whitecross tilanteessa, jossa laatta on alapuolella, mutta
         * väärällä paikalla (sivun väri on väärä)
         * HUOM. Myös tässä jätetään varsinainen oikealle paikalle siirto metodille
         * "ratkaiseWhitecrossYla"
         */
        public void ratkaiseWhitecrossAla() {

            // Tarkastetaan onko kuution alasivulla paikassa [3][10] valkoinen laatta, jonka
            // toinen sivu on väärän värinen
            if (tila[3][10] == 6 && tila[0][4] != tila[1][4]) {
                // jos väärä väri sivulla. käännetään yläpuolelle
                liikutaB();
                liikutaB();
            }
            if (tila[4][9] == 6 && tila[4][7] != tila[4][8]) {
                liikutaR();
                liikutaR();
            }
            if (tila[5][10] == 6 && tila[7][4] != tila[8][4]) {
                liikutaF();
                liikutaF();
            }
            if (tila[4][11] == 6 && tila[4][1] != tila[4][0]) {
                liikutaL();
                liikutaL();
            }
        }

        public int sivuEtaisyys(int sivu, int kohde) {
            return Math.abs(kohde - sivu - 1);
        }
        
        /*
         * Kuution seuraava sivu (vastapäivään sivuttaissuunnassa)
         */
        public int seuraavaSivu(int nykyinen) {
            if (nykyinen == 5)
                return 2;
            else
                return nykyinen + 1;
        }

        /*
         * Kuution edellinen sivu (myötäpäivään sivuttaissuunnassa)
         */
        public int edellinenSivu(int nykyinen) {
            if (nykyinen == 2)
                return 5;
            else
                return nykyinen - 1;
        }

        /*
         * Kääntää kulmaa siten että valkoinen kulmalaatta saadaan oikealle paikalle
         * Huomioi parametrina saadun sivun ja puolen(vasen tai oikea), jonka
         * perusteella liikkeet tehdään oikeille sivuille
         */
        public void kulmaKaanto(int sivu, int puoli) {

            // Sininen puoli
            if (sivu == 2 && puoli == 1) {
                liikutaUi();
                liikutaLi();
                liikutaU();
                liikutaL();
            }
            if (sivu == 2 && puoli == 2) {
                liikutaU();
                liikutaR();
                liikutaUi();
                liikutaRi();
            }
            // Punainen puoli
            if (sivu == 3 && puoli == 1) {
                liikutaUi();
                liikutaFi();
                liikutaU();
                liikutaF();
            }
            if (sivu == 3 && puoli == 2) {
                liikutaU();
                liikutaB();
                liikutaUi();
                liikutaBi();
            }
            // Vihreä puoli
            if (sivu == 4 && puoli == 1) {
                liikutaUi();
                liikutaRi();
                liikutaU();
                liikutaR();
            }
            if (sivu == 4 && puoli == 2) {
                liikutaU();
                liikutaL();
                liikutaUi();
                liikutaLi();
            }
            // Oranssi (violetti) puoli
            if (sivu == 5 && puoli == 1) {
                liikutaUi();
                liikutaBi();
                liikutaU();
                liikutaB();
            }
            if (sivu == 5 && puoli == 2) {
                liikutaU();
                liikutaF();
                liikutaUi();
                liikutaFi();
            }
        }

        public void ratkaiseAlareunaYlakulma() {
            int etuVasen = tila[8][3];
            int etuOikea = tila[8][5];
            int vasenVasen = tila[3][0];
            int vasenOikea = tila[5][0];
            int takaVasen = tila[0][5];
            int takaOikea = tila[0][3];
            int oikeaVasen = tila[5][8];
            int oikeaOikea = tila[3][8];
            int etuKeski = tila[6][4];
            int vasenKeski = tila[4][2];
            int takaKeski = tila[2][4];
            int oikeaKeski = tila[4][6];

            if (etuVasen == 6) {
                int muisti = vasenOikea;
                int kaannot = 3 - sivuEtaisyys(2, muisti);
                for (int i = 0; i < kaannot; i++) {
                    liikutaU();
                }
                kulmaKaanto(seuraavaSivu(muisti), 1);
            }

            else if (vasenVasen == 6) {
                int muisti = takaOikea;
                int kaannot = 3 - sivuEtaisyys(5, muisti);
                for (int i = 0; i < kaannot; i++) {
                    liikutaU();
                }
                kulmaKaanto(seuraavaSivu(muisti), 1);
            }

            else if (takaVasen == 6) {
                int muisti = oikeaOikea;
                int kaannot = 3 - sivuEtaisyys(4, muisti);
                for (int i = 0; i < kaannot; i++) {
                    liikutaU();
                }
                kulmaKaanto(seuraavaSivu(muisti), 1);
            }

            else if (oikeaVasen == 6) {
                int muisti = etuOikea;
                int kaannot = 3 - sivuEtaisyys(3, muisti);
                for (int i = 0; i < kaannot; i++) {
                    liikutaU();
                }
                kulmaKaanto(seuraavaSivu(muisti), 1);
            }

            // Oikeat kulmat

            else if (etuOikea == 6) {
                int muisti = oikeaVasen;
                int kaannot = 3 + sivuEtaisyys(2, muisti);
                for (int i = 0; i < kaannot; i++) {
                    liikutaU();
                }
                kulmaKaanto(seuraavaSivu(muisti), 2);
            }

            else if (vasenOikea == 6) {
                int muisti = etuVasen;
                int kaannot = 3 + sivuEtaisyys(5, muisti);
                for (int i = 0; i < kaannot; i++) {
                    liikutaU();
                }
                kulmaKaanto(seuraavaSivu(muisti), 2);
            }

            else if (takaOikea == 6) {
                int muisti = vasenVasen;
                int kaannot = 3 + sivuEtaisyys(4, muisti);
                for (int i = 0; i < kaannot; i++) {
                    liikutaU();
                }
                kulmaKaanto(seuraavaSivu(muisti), 2);
            }

            else if (oikeaOikea == 6) {
                int muisti = takaVasen;
                int kaannot = 3 + sivuEtaisyys(3, muisti);
                for (int i = 0; i < kaannot; i++) {
                    liikutaU();
                }
                kulmaKaanto(seuraavaSivu(muisti), 2);
            }

        }
    }
}

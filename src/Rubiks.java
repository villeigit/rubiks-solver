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
            System.out.print("Anna kiertosuunta (D tai U): ");
            String syote = lukija.nextLine();
            if (syote.equals("D")) {
                kuutio.liikutaD();
                kuutio.tulostaKuutio();
                System.out.println();
            }
            if (syote.equals("U")) {
                kuutio.liikutaU();
                kuutio.tulostaKuutio();
                System.out.println();
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
         * Tämä on yleinen periaate myös muissa algoritmeissa
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
         */
        public void tulostaVari(int numero) {
            if (numero == 1) {
                System.out.print(ANSI_YELLOW +" "+ numero +" "+ ANSI_RESET);
            }
            if (numero == 2) {
                System.out.print(ANSI_BLUE +" "+ numero +" "+ ANSI_RESET);
            }
            if (numero == 3) {
                System.out.print(ANSI_RED +" "+ numero +" "+ ANSI_RESET);
            }
            if (numero == 4) {
                System.out.print(ANSI_GREEN +" "+ numero +" "+ ANSI_RESET);
            }
            if (numero == 5) {
                System.out.print(ANSI_PURPLE +" "+ numero +" "+ ANSI_RESET);
            }
            if (numero == 6) {
                System.out.print(ANSI_WHITE +" "+ numero +" "+ ANSI_RESET);
            }
        }

        /**
         * Liikuttaa kuution alaosaa myötäpäivään yhden kierroksen
         * 
         * Lähtöasetelma koordinaateissa:
         * 1 1
         * 0 1 2 3 4 5 6 7 8 9 0 1
         * 
         * 0 - - - 4 4 4
         * 1 - - - 4 4 4
         * 2 - - - 4 4 4
         * 3 5 5 5 1 1 1 3 3 3 6 6 6
         * 4 5 5 5 1 1 1 3 3 3 6 6 6
         * 5 5 5 5 1 1 1 3 3 3 6 6 6
         * 6 - - - 2 2 2
         * 7 - - - 2 2 2
         * 8 - - - 2 2 2
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
            uusiTila[0][3] = this.tila[3][8];
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
         * Lähtöasetelma koordinaateissa:
         * 1 1
         * - 0 1 2 3 4 5 6 7 8 9 0 1
         * 
         * 0 - - - 4 4 4
         * 1 - - - 4 4 4
         * 2 - - - 4 4 4
         * 3 5 5 5 1 1 1 3 3 3 6 6 6
         * 4 5 5 5 1 1 1 3 3 3 6 6 6
         * 5 5 5 5 1 1 1 3 3 3 6 6 6
         * 6 - - - 2 2 2
         * 7 - - - 2 2 2
         * 8 - - - 2 2 2
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
            uusiTila[2][3] = this.tila[3][6];
            uusiTila[2][4] = this.tila[4][6];
            uusiTila[2][5] = this.tila[5][6];

            // rivi 3 muutokset
            uusiTila[3][2] = this.tila[2][5];
            uusiTila[3][3] = this.tila[3][5];
            uusiTila[3][4] = this.tila[4][5];
            uusiTila[3][5] = this.tila[5][5];
            uusiTila[3][6] = this.tila[6][5];

            // rivi 4 muutokset
            uusiTila[4][2] = this.tila[2][4];
            uusiTila[4][3] = this.tila[3][4];
            uusiTila[4][5] = this.tila[5][4];
            uusiTila[4][6] = this.tila[6][4];

            // rivi 5 muutokset
            uusiTila[5][2] = this.tila[2][3];
            uusiTila[5][3] = this.tila[3][3];
            uusiTila[5][4] = this.tila[4][3];
            uusiTila[5][5] = this.tila[5][3];
            uusiTila[5][6] = this.tila[6][3];

            // rivi 6 muutokset
            uusiTila[6][3] = this.tila[3][2];
            uusiTila[6][4] = this.tila[4][2];
            uusiTila[6][5] = this.tila[5][2];

            this.tila = uusiTila;
        }
    }
}

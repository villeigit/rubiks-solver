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

        while (true) {
            System.out.print("Anna kiertosuunta (D): ");
            Scanner lukija = new Scanner(System.in);
            if (lukija.nextLine().equals("D")) {
                kuutio.liikutaD();
                kuutio.tulostaKuutio();
                System.out.println();
            }
        }
    }

    /**Kuutiolla on aina tila (kuution asento), joka on tallennettu 2-ulotteiseen taulukkoon
     * Kuution metodeilla tilaa voidaan muuttaa tai tulostaa
    */

    static class Kuutio {
        private int[][] tila;

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
         * 5 = oranssi
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
                        System.out.print("  ");
                    } else {
                        System.out.print(this.tila[i][j] + " ");
                    }
                }
                System.out.println("");
            }
        }

        /**
         * Liikuttaa kuution alaosaa myötäpäivään yhden kierroksen
         * 
         * Lähtöasetelma koordinaateissa:
         * 1 1
         * 0 1 2 3 4 5 6 7 8 9 0 1
         * 
         * 0 4 4 4
         * 1 4 4 4
         * 2 4 4 4
         * 3 5 5 5 1 1 1 3 3 3 6 6 6
         * 4 5 5 5 1 1 1 3 3 3 6 6 6
         * 5 5 5 5 1 1 1 3 3 3 6 6 6
         * 6 2 2 2
         * 7 2 2 2
         * 8 2 2 2
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

            // int[][] uusiTila = this.tila.clone();

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
    }
}

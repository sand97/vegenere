package com.company;

import java.util.Scanner;

public class Main {

    /**
     * Stocke les lettre du tableau de vegenere.
     * le tableau vas contenir 26 lignes et 26 colones d'ou sa taille de 26 (0-25)
     */
    private static char table[][] = new char[26][26];


    /**
     * utiliser pour la construction de la table de vegenere
     */
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";


    /**
     * Fonction d'entre du programe (première fonction executer)
     *
     * @param args argument ne nous aident pas dans le cas d'espece
     */
    public static void main(String[] args) {
        // write your code here
        initTable();
        char replay = 'o';
        do {
            System.out.println("Saisisser e pour encrypter ou d pour decrypter");
            Scanner sc = new Scanner(System.in);
            //on recupere le premier des carractere saisi par l'utilisateur
            String response = sc.nextLine();
            if(response.length() > 0){
                char carac = response.charAt(0);
                if(carac == 'e'){
                    System.out.println("Saisi le message en clair");
                    // on recupere le message a crypter et on le met en majuscule
                    String msg = sc.nextLine().toUpperCase();

                    System.out.println("Saisi la clef de chiffrement !");

                    String key = sc.nextLine().toUpperCase();

                    String msgCrypt = encrypt(key, msg);

                    System.out.println("Le message crypter est : " + msgCrypt);
                }else if(carac == 'd'){
                    System.out.println("Saisi le message en crypter");
                    // on recupere le message a crypter et on le met en majuscule
                    String msg = sc.nextLine().toUpperCase();

                    System.out.println("Saisi la clef de chiffrement !");

                    String key = sc.nextLine().toUpperCase();

                    String msgdeCrypt = decrypt(key, msg);
                    System.out.println("Le message decrypter est : " + msgdeCrypt);
                }else{
                    System.out.println("Saisi incorrect veuiller recommencer");
                }
            }
            else{
                System.out.println("Saisi incorrect veuiller recommencer");
            }
            System.out.println("Souhaiter vous recommencer ? taper o");
            response = sc.nextLine();
            if(response.length() > 0 ){
                replay = response.charAt(0);
            }
        } while (replay == 'o');
        System.out.println("Aurevori et a bientot !");
    }


    /**
     * permet de cree le tableau de venenere.
     * effacer les deux commentaires ( // ) pour afficher la table
     */
    private static void initTable() {
        for (int i = 0; i < 26; i++) {
            //System.out.println();
            for (int j = 0; j < 26; j++) {
                table[i][j] = alphabet.charAt(i + j);
                //  System.out.print(table[i][j]);
            }
        }
    }

    /**
     * Fonction appeler pour le cryptage
     *
     * @param key      cle de chiffrement
     * @param msgClear message en clair a chiffer
     * @return message chiffre
     */
    private static String encrypt(String key, String msgClear) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < msgClear.length(); i++) {
            /*
             * si cest un carractere special ( ',.:[ ) on l'ajoute directement
             */
            if (isSpecialChar(msgClear.charAt(i))) {
                result.append(msgClear.charAt(i));
            } else {
                /*
                 * sinon on recupere sa position dans la table de vegenere
                 * i % key.lenght() : i modulo la taille de la clef
                 *
                 */
                result.append(getLetterEncrypt(key.charAt(i % key.length()), msgClear.charAt(i)));
            }
        }
        return result.toString();
    }

    /**
     * Fonction appeler pour le cryptage
     *
     * @param key      cle de dechiffrement
     * @param msgCrypt message crypter a dechiffer
     * @return message en clair
     */
    private static String decrypt(String key, String msgCrypt) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < msgCrypt.length(); i++) {
            if (isSpecialChar(msgCrypt.charAt(i))) {
                result.append(msgCrypt.charAt(i));
            } else {
                result.append(getLetterDecrypt(key.charAt(i % key.length()), msgCrypt.charAt(i)));
            }
        }
        return result.toString();
    }

    /**
     * Permet de recuperer un lettre dans le tableau pour encrypter
     *
     * @param one char lettre de la ligne corespondant (cle)
     * @param two char lettre de la colone corespondant (msg)
     */
    private static char getLetterEncrypt(char one, char two) {
        int col = -1, line = -1;
        for (int i = 0; i < 26; i++) {
            if (alphabet.charAt(i) == one) {
                line = i;
            }
            if (alphabet.charAt(i) == two) {
                col = i;
            }
        }
        if (col != -1 && line != -1) {
            return table[line][col];
        } else {
            return ' ';
        }
    }
    /**
     * Permet de recuperer une lettre
     *
     * @param one char lettre de la cle
     * @param two char lettre dans le tableau
     */
    private static char getLetterDecrypt(char one, char two) {
        int line = -1;
        //on retrouve la position de la lettre de la cle dans l'alphabet
        for (int i = 0; i < 26; i++){
            if(alphabet.charAt(i) == one){
                line = i;
            }
        }
        //on retrouve la lettre de msg en clair
        for (int i = 0; i < 26; i++){
            if(table[line][i] == two){
                return alphabet.charAt(i);
            }
        }
        return ' ';
    }

    private static boolean isSpecialChar(char c) {
        // on  parcour les 26 lettre de l'alphabet
        for (int i = 0; i < 26; i++) {
            if (alphabet.charAt(i) == c) {
                return false;
            }
        }
        return true;
    }
}

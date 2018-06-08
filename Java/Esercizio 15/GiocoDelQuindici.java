package esercizio_15;

import puzzleboard.PuzzleBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.util.EventListener;

public class GiocoDelQuindici {

    int[] tasselli; // 1,6,2,3,
                    // 13, 0 ,7,4,
                    // 8,5,11,15,
                    // 14,9,10,12

    GiocoDelQuindici(int[] tasselli) {
        this.tasselli = tasselli;
        PuzzleBoard gui = new PuzzleBoard( 4);
        int cont = 0;
        for (int i=1; i<=4; i=i+1) {
            for (int j=1; j<=4; j=j+1) {
                gui.setNumber(i, j, tasselli[cont]);
                cont = cont+1;
            }
        }
        while (true) {
            int k = gui.get();
            if(puoEssereSpostato(k)) {
                spostaTassello(k);
                cont = 0;
                for (int i=1; i<=4; i=i+1) {
                    for (int j=1; j<=4; j=j+1) {
                        gui.setNumber(i, j, tasselli[cont]);
                        cont = cont+1;
                    }
                }
                System.out.println(configurazione());
            } else {
                System.out.println("Mossa errata!");
            }
            //gui.display
        }
    }

    public boolean tasselliOrdinati () {
        for (int i=0; i<tasselli.length-2; i++) {
            if (tasselli[i] > tasselli[i+1]) {
                return false;
            }
        }
        return true;
    }
    public boolean puoEssereSpostato (int tassello) {
        int posZero = -1;
        int posTassello = -1;
        for (int i=0; i<tasselli.length; i++){
            if(tasselli[i] == 0) {
                posZero = i;
            } else if(tasselli[i] == tassello) {
                posTassello = i;
            }
        }
        if ((posTassello == posZero-1) || (posTassello == posZero+1)) {
            return true;
        }
        else if ((posTassello + 4 == posZero) || (posTassello-4 == posZero)) {
            return true;
        } else return false;
    }



    public String configurazione() {
        String configurazione = "[ " + tasselli[0];
        for (int i=1; i<tasselli.length; i++) {
            configurazione += (", " + String.valueOf(tasselli[i]));
        }
        return configurazione + " ]";
    }


    public void spostaTassello(int tassello) {
        if (puoEssereSpostato(tassello)) {
            int posZero = -1;
            int posTassello = -1;
            for (int i=0; i<tasselli.length; i++){
                if(tasselli[i] == 0) {
                    posZero = i;
                } else if(tasselli[i] == tassello) {
                    posTassello = i;
                }
            }
            if (posTassello - 1 == posZero) {  //Sinistra
                tasselli[posTassello-1] = tassello;
                tasselli[posTassello] = 0;
            }
            else if (posTassello + 1 == posZero) {  //Destra
                tasselli[posTassello+1] = tassello;
                tasselli[posTassello] = 0;
            }
            else if (posTassello - 4 == posZero) {  //Sotto
                tasselli[posTassello-4] = tassello;
                tasselli[posTassello] = 0;
            }
            else if (posTassello + 4 == posZero) {  //Sopra
                tasselli[posTassello+4] = tassello;
                tasselli[posTassello] = 0;
            }
        }
    }


}

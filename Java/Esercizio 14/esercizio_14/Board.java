package esercizio_14;

import esercizio_11.IntSList;

public class Board {

    final int dimensione;
    int numeroRegine;
    IntSList righe, colonne, ascDestra, ascSinistra;
    String descrizione = "";

    Board(int n){
        dimensione = n;
        righe = new IntSList();
        colonne = new IntSList();
        ascDestra = new IntSList();
        ascSinistra = new IntSList();
    }

    public Board(int dimensione, int numeroRegine, IntSList righe, IntSList colonne, IntSList ascDestra, IntSList ascSinistra, String descrizione) {
        this.dimensione = dimensione;
        this.numeroRegine = numeroRegine;
        this.righe = righe;
        this.colonne = colonne;
        this.ascDestra = ascDestra;
        this.ascSinistra = ascSinistra;
        this.descrizione = descrizione;

    }

    public int size() {
        return dimensione;
    }

    public int queensOn(){
        return numeroRegine;
    }

    public boolean underAttack(int i, int j){

        for(int cont = 0; cont<righe.length(); cont++){
            if ((i == righe.listRef(cont)) ||
                    (j == colonne.listRef(cont)) ||
                    (i-j == ascDestra.listRef(cont)) ||
                    (i+j == ascSinistra.listRef(cont))){
                return true;
            }
        }
        return false;
    }

    public esercizio_13.Board addQueen(int i, int j) {
        String descrizione = "";
        for (int z = 0; z<righe.length(); z++){
            descrizione = descrizione + colonne.listRef(z) + 96 + righe.listRef(z) + ' ';
        }

        return new esercizio_13.Board(dimensione, numeroRegine + 1, righe.cons(i), colonne.cons(j), ascDestra.cons(i - j), ascSinistra.cons(i + j), descrizione);
    }

    public String arrangement() {

        String ris = "<"+dimensione+", "+numeroRegine+", "+righe.toString()+", "+
                colonne.toString()+", "+
                ascSinistra.toString()+", "+
                ascDestra.toString()+", " + descrizione + ">";
        return ris;
    }

}

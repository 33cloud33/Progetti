package esercizio_13;

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

    public Board(int dimensione, int numeroRegine, IntSList righe, IntSList colonne, IntSList ascDestra, IntSList ascSinistra) {
        this.dimensione = dimensione;
        this.numeroRegine = numeroRegine;
        this.righe = righe;
        this.colonne = colonne;
        this.ascDestra = ascDestra;
        this.ascSinistra = ascSinistra;
    }

    public int size() {
        return dimensione;
    }

    public int queensOn(){
        return numeroRegine;
    }

    public boolean underAttack(int i, int j){

        for(int cont=0; cont<righe.length(); cont++){
            if ((i == righe.listRef(cont)) ||
                    (j == colonne.listRef(cont)) ||
                    (i-j == ascDestra.listRef(cont)) ||
                    (i+j == ascSinistra.listRef(cont))){
                return true;
            }
        }
        return false;
    }

    public Board addQueen(int i, int j){
        /*
        righe = righe.cons(i);
        colonne = colonne.cons(j);
        ascDestra = ascDestra.cons(i-j);
        ascSinistra = ascSinistra.cons(i+j);
        */


        /*Fai un for che va da 0 fino alla lunghezza di righe.length(). Colonne e gli sommi 96. Dopo gli stampi righe.listRef(i)
         * A questo punto fai uno spazio e basta.
         *
         * */
        for (int z=0; z<righe.length(); z++){
            descrizione = descrizione + colonne.listRef(z) + 96 + righe.listRef(z) + ' ';
        }

        return new Board(dimensione,numeroRegine+1, righe.cons(i), colonne.cons(j),ascDestra.cons(i-j),ascSinistra.cons(i+j));
    }

    public String arrangement() {
        String ris = "<"+dimensione+", "+numeroRegine+", "+righe.toString()+", "+
                colonne.toString()+", "+
                ascSinistra.toString()+", "+
                ascDestra.toString()+", " + descrizione + ">";
        return ris;
    }

}

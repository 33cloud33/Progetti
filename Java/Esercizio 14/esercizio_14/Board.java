package esercizio_14;

public class Board {

    final IntSList righe;
    final IntSList colonne;
    private final int dimensione;
    private final IntSList ascDestra;
    private final IntSList ascSinistra;
    private int numeroRegine;
    String descrizione = "";

    Board(int n) {
        dimensione = n;
        righe = new IntSList();
        colonne = new IntSList();
        ascDestra = new IntSList();
        ascSinistra = new IntSList();
    }

    private Board(int dimensione, int numeroRegine, IntSList righe, IntSList colonne, IntSList ascDestra, IntSList ascSinistra, String descrizione) {
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

        for (int cont = 0; cont < righe.length(); cont++) {
            if ((i == righe.listRef(cont)) ||
                    (j == colonne.listRef(cont)) ||
                    (i-j == ascDestra.listRef(cont)) ||
                    (i+j == ascSinistra.listRef(cont))){
                return true;
            }
        }
        return false;
    }

    public Board addQueen(int i, int j) {
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
        String descrizione = "";
        for (int z = 0; z < righe.length(); z++) {
            descrizione = descrizione + ((char) (colonne.listRef(z) + 96)) + righe.listRef(z) + ' ';
        }

        return new Board(dimensione, numeroRegine + 1, righe.cons(i), colonne.cons(j), ascDestra.cons(i - j), ascSinistra.cons(i + j), descrizione);
    }

    public String arrangement() {

        return "<" + dimensione + ", " + numeroRegine + ", " + righe.toString() + ", " +
                colonne.toString() + ", " +
                ascSinistra.toString() + ", " +
                ascDestra.toString()+", " + descrizione + ">";
    }

}

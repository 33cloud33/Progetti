package esercizio_11;

/** Questo è l'esercizio 11 (parte 1)
 *  Classe esercizio_11.StringSList
 */
class StringSList {
    public static final StringSList NULL_STRINGLIST = new StringSList();

    private final boolean empty;             // oggetti immutabili:
    private final String first;                 // variabili di istanza "final"
    private final StringSList rest;

    public static void main(String[] args) {
        new StringSList();
    }

    public StringSList(){
        empty = true;
        first = "";                             // valore irrilevante in questo caso
        rest = null;
    }

    private StringSList(String e, StringSList il) {
        empty = false;
        first = e;
        rest = il;
    }

    private boolean isNull() {                // verifica se una lista e' vuota
        return ( empty );
    }

    public String car() {                       // primo elemento di una lista
        return first;                          // si assume: lista non vuota
    }


    public StringSList cdr() {                  // resto di una lista
        return rest;                           // si assume: lista non vuota
    }

    public StringSList cons( String e ) {          // costruzione di nuove liste
        return new StringSList( e, this );
    }

    private int length() {                    // lunghezza di una lista
        if ( isNull() ) {
            return 0;
        } else {
            return 1 + cdr().length();
        }
    }

    private String listRef(int k) {            // elemento in posizione k
        if ( k == 0 ) {
            return car();
        } else {
            return ( cdr().listRef(k-1) );
        }
    }

    private boolean equals(StringSList il) {   // contronto di liste
        if ( isNull() || il.isNull() ) {
            return ( isNull() && il.isNull() );
        } else if (car().equals(il.car())) {
            return cdr().equals( il.cdr() );
        } else {
            return false;
        }
    }

    public StringSList append(StringSList il ) {  // fusione di liste
        if ( isNull() ) {
            return il;
        } else {
            return ( cdr().append(il) ).cons( car() );
        }
    }

    public StringSList reverse() {              // rovesciamento di una lista
        return reverseRec( new StringSList() );
    }

    private StringSList reverseRec(StringSList re ) {

        if ( isNull() ) {                      // metodo di supporto: private!
            return re;
        } else {
            // return cdr().reverseRec( new esercizio_11.esercizio_12.esercizio_13.esercizio_14.IntSList(car(),re) );
            return cdr().reverseRec( re.cons(car()) );
        }
    }

    public String toString() {               // ridefinizione del metodo generale
        if ( empty ) {
            return "()";
        } else if ( rest.isNull() ) {
            return "(" + first + ")";
        } else {
            String rep = "(" + first;
            StringSList r = rest;
            while ( !r.isNull() ) {
                rep = rep + ", " + r.car();
                r = r.cdr();
            }
            return ( rep + ")" );
        }
    }
}

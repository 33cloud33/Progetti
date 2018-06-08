package esercizio_12;

public class IntSList {
    public static final esercizio_11.IntSList NULL_INTLIST = new esercizio_11.IntSList();

    private final boolean empty;
    private final int first;
    private final esercizio_11.IntSList rest;

    public IntSList() {            // null
        empty = true;
        first = 0;
        rest = null;
    }

    public IntSList(int n, esercizio_11.IntSList s) { //cons
        empty = false;
        first = n;
        rest = s;
    }

    public boolean isNull() {      // null?
        return empty;
    }

    public int car() {             // car
        return first;
    }

    public esercizio_11.IntSList cdr() {        // cdr
        return rest;
    }

    public esercizio_11.IntSList cons(int n) {  // cons
        return (new esercizio_11.IntSList(n, this));
    }

    public String toString() {
        if (isNull()) {
            return "()";
        } else {            String s = "(" + car();
            esercizio_11.IntSList x = cdr();
            while (!x.isNull()) {
                s = s + " " + x.car();
                x = x.cdr();
            }
            s = s + ")";
            return s;
        }
    }

    public boolean equals(esercizio_11.IntSList s) {
        if (isNull() || s.isNull()) {
            return (isNull() && s.isNull());
        } else if (car() == s.car()) {
            return (cdr().equals(s.cdr()));
        }else {
            return false;
        }
    }

    public esercizio_11.IntSList append(esercizio_11.IntSList s) {
        if (isNull()) {
            return s;
        }else{
            return (cdr().append(s).cons(car()));
        }
    }

    public esercizio_11.IntSList reverse() {
        return revRec (NULL_INTLIST);
    }

    private esercizio_11.IntSList revRec(esercizio_11.IntSList s) {
        if (isNull()) {
            return s;
        } else {
            return (cdr().revRec(s.cons(car())));
        }
    }

    public int length(){
        if (isNull()){
            return 0;
        } else {
            return (1 + cdr().length());
        }
    }
}
package esercizio_12;

public class IntSList {
    public static final IntSList NULL_INTLIST = new IntSList();

    private final boolean empty;
    private final int first;
    private final IntSList rest;

    public IntSList() {            // null
        empty = true;
        first = 0;
        rest = null;
    }

    private IntSList(int n, IntSList s) { //cons
        empty = false;
        first = n;
        rest = s;
    }

    private boolean isNull() {      // null?
        return empty;
    }

    public int car() {             // car
        return first;
    }

    public IntSList cdr() {        // cdr
        return rest;
    }

    public IntSList cons(int n) {  // cons
        return (new IntSList(n, this));
    }

    public String toString() {
        if (isNull()) {
            return "()";
        } else {            String s = "(" + car();
            IntSList x = cdr();
            while (!x.isNull()) {
                s = s + " " + x.car();
                x = x.cdr();
            }
            s = s + ")";
            return s;
        }
    }

    private boolean equals(IntSList s) {
        if (isNull() || s.isNull()) {
            return (isNull() && s.isNull());
        } else if (car() == s.car()) {
            return (cdr().equals(s.cdr()));
        }else {
            return false;
        }
    }

    private IntSList append(IntSList s) {
        if (isNull()) {
            return s;
        }else{
            return (cdr().append(s).cons(car()));
        }
    }

    public IntSList reverse() {
        return revRec (NULL_INTLIST);
    }

    private IntSList revRec(IntSList s) {
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
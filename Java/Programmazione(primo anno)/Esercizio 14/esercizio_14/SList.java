package esercizio_14;
/*
 * Classe problema_13.SList<T>: Scheme-like Lists of T (generics)
 *
 * Definizione di una classe in Java per realizzare oggetti
 * assimilabili alle liste in Scheme, limitatamente al caso
 * di liste con elementi di tipo omogeneo.
 *
 * Le liste create sono "immutabili".
 *
 * Ultimo aggiornamento: 10/04/2018
 */


import java.util.function.Function;


public class SList<T> {                    // Scheme-Like Lists of T


  // ----- Costante lista vuota: non puo' essere definita staticamente:

  // public static final problema_13.SList<T> NULL_LIST = new problema_13.SList<T>();
  
  
  // ----- Rappresentazione interna di una lista: private!
  
  // private final boolean empty;          // ridondante
  private final T first;                   // oggetti immutabili:
  private final SList<T> rest;             // variabili di istanza "final"
  
  
  // ----- Operazioni di base sulle liste, mutuate da Scheme
  
  public SList() {                         // creazione di una lista vuota
                                           // Scheme: null
    // empty = true;
    first = null;                          // valore irrilevante in questo caso
    rest = null;
  }

  private SList(T e, SList<T> tl) {       // creazione di una lista non vuota:
                                           // Scheme: cons
    // empty = false;
    first = e;
    rest = tl;
  }


  boolean isNull() {                // verifica se una lista e' vuota
                                           // Scheme: null?
    return ( first == null );              // empty
  }


  T car() {                         // primo elemento di una lista
                                           // Scheme: car
    return first;                          // si assume: lista non vuota
  }


  SList<T> cdr() {                  // resto di una lista
                                           // Scheme: cdr
    return rest;                           // si assume: lista non vuota
  }
  
  
  // ----- Realizzazione alternativa (sostanzialmente equivalente) del "cons"
  
  public SList<T> cons( T e ) {            // costruzione di nuove liste
                                           // Scheme: cons
    return new SList<T>( e, this );
  }
  
  
  // ----- Operazioni aggiuntive, definite in termini dei precedenti metodi
  
  public int length() {                    // lunghezza di una lista
                                           // Scheme: length
    if ( isNull() ) {
      return 0;
    } else {
      return ( 1 + cdr().length() );
    }
  }
  
  
  public T listRef( int k ) {              // elemento in posizione k
                                           // Scheme: list-ref
    if ( k == 0 ) {
      return car();
    } else {
      return ( cdr().listRef(k-1) );
    }
  }


  boolean equals(SList<T> tl) {   // contronto di liste
                                           // Scheme: equal?
    if ( isNull() || tl.isNull() ) {
      return ( isNull() && tl.isNull() );
    } else if ( car().equals(tl.car()) ) {
      return cdr().equals( tl.cdr() );
    } else {
      return false;
    }
  }
  
  public boolean equals( Object tl ) {     // metodo invocato in generale!
  
    return equals( (SList<T>) tl );
  }


  SList<T> append(SList<T> tl) {  // fusione di liste
                                           // Scheme: append
    if ( isNull() ) {
      return tl;
    } else {
      // return new problema_13.SList<T>( car(), cdr().append(il) );
      return ( cdr().append(tl) ).cons( car() );
    }
  }
  
  
  public SList<T> reverse() {              // rovesciamento di una lista
                                           // Scheme: reverse
    return reverseRec( new SList<T>() );
  }
  
  private SList<T> reverseRec( SList<T> re ) {
  
    if ( isNull() ) {                      // metodo di supporto: private!
      return re;
    } else {
      // return cdr().reverseRec( new problema_13.SList<T>(car(),re) );
      return cdr().reverseRec( re.cons(car()) );
    }
  }
  
  
  // ----- Metodo con argomento procedurale - Scheme: map
  
  //       f : T -> Object (oggetti di qualunque tipo)
  //       lista restituita di tipo problema_13.SList<Object>

  SList<Object> map(Function<T, Object> f) {
  
    if ( isNull() ) {
      return new SList<Object>();
    } else {
      // return new problema_13.SList<Object>( f.apply(car()), cdr().map(f) );
      return ( cdr().map(f) ).cons( f.apply(car()) );
    }
  }
  
  
  // ----- Rappresentazione testuale (String) di una lista
  
  public String toString() {               // ridefinizione del metodo generale
                                           // per la visualizzazione testuale
    if ( isNull() ) {
      return "()";
    } else if ( rest.isNull() ) {
      return "(" + first + ")";
    } else {
      String rep = "(" + first;
      SList<T> r = rest;
      while ( !r.isNull() ) {
        rep = rep + ", " + r.car();
        r = r.cdr();
      }
      return ( rep + ")" );
    }
  }


}  // class problema_13.SList<T>


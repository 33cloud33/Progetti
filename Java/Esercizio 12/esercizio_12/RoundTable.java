package esercizio_12;/*
 * Classe esercizio_12.RoundTable:
 *
 * Modello alla base del problema di Giuseppe Flavio
 * (rivisto in termini di cavalieri attorno al tavolo)
 *
 * Gli oggetti creati sono "immutabili".
 *
 * Ultimo aggiornamento: 10/04/2018
 */


public class RoundTable {


  // ----- Rappresentazione interna del modello: private!

  //private final esercizio_11.esercizio_12.esercizio_13.esercizio_14.IntSList knights;          // lista dei cavalieri (numerati)
  private final int numeroCompl;           // numero complessivo dei cavalieri a tavola
  private final int lunghezza;                   // lunghezza = lunghezza prima lista
  private final IntSList knightsBefore;    // MAI vuota
  private final IntSList knightsAfter;     // pu� essere svuotata
  
  // ----- Costruttore pubblico
  
  public RoundTable( int n ) {             // creazione di una tavola
                                           // con n cavalieri
   // knights = range( 1, n );
    numeroCompl = n;
    lunghezza = n;
    knightsBefore = range(1, n);
    knightsAfter = new IntSList();
  }
  
  
  // ----- Costruttore non pubblico di supporto
  
  private RoundTable( IntSList knsBefore, IntSList knsAfter, int n ) {
    
    //knights = kns;
    numeroCompl = n;
    knightsBefore = knsBefore;
    knightsAfter = knsAfter;
    lunghezza = knsBefore.length();
    
  }

  
  // ----- Metodi del protocollo: acquisizione di informazioni sulla configurazione
  
  public int numberOfKnightsIn() {         // numero di cavalieri a tavola
  
    return numeroCompl;
  }
  

  public int knightWithJugIn() {           // cavaliere con la brocca di sidro
  
    return knightsBefore.car();
  }
  
  
  // ----- Metodi del protocollo: configurazione successiva a una mossa
  
  public RoundTable afterNextKnightQuits() {
    if(lunghezza == 1) {
      IntSList knsb = knightsAfter.cons(knightsBefore.car()).reverse().cdr();
      IntSList knsa = IntSList.NULL_INTLIST;
      return new RoundTable(knsb, knsa, numeroCompl-1);
    } else if (lunghezza == 2) {
      IntSList knsb = knightsAfter.cons(knightsBefore.car()).reverse();
      IntSList knsa = IntSList.NULL_INTLIST;
      return new RoundTable(knsb, knsa, numeroCompl-1);
    } else {
      IntSList knsb = knightsBefore.cdr().cdr();
      IntSList knsa = knightsAfter.cons(knightsBefore.car());
      return new RoundTable(knsb, knsa, numeroCompl-1);
    }
  }
  
  
  // ----- Procedura interna di supporto (private!)
  
  private static IntSList range( int inf, int sup ) {
  
    if ( inf > sup ) {
      return IntSList.NULL_INTLIST;
    } else {
      return range( inf+1, sup ).cons( inf );
    }
  }
  /*
  public esercizio_11.esercizio_12.esercizio_13.esercizio_14.IntSList secondaLista(){
    if(lunghezza <= 2){
      return esercizio_11.esercizio_12.esercizio_13.esercizio_14.IntSList.NULL_INTLIST;
    }else{
      return knightsAfter.cons(knightsBefore.car());
    }
  }  
  */

}  // class esercizio_12.RoundTable


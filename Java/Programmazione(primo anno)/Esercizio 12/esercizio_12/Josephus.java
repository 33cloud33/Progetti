package esercizio_12;/*
/*
 * Modulo Josephus:
 *
 * Programma per risolvere il problema di Giuseppe Flavio
 * (metodi statici))
 *
 * Ultimo aggiornamento: 10/04/2018
 */
public class Josephus {


    private static int josephus(int n) {

    return josephusRec( new RoundTable(n) );
  }

  private static int josephusRec( RoundTable rt ) {

    if ( rt.numberOfKnightsIn() > 1 ) {

        return josephusRec( rt.afterNextKnightQuits() );
    } else {

        return rt.knightWithJugIn();
    }
  }


    public static void main( String args[] ) {

        int n = 10;
        //Soluzione 10
        System.out.println("La soluzione se abbiamo " + n + " cavalieri è " + josephus(n));
        //Soluzione per da 1 a 10
        System.out.println("Soluzioni da 1 cavaliere fino a " + n + " cavalieri");
    for ( int k=1; k<=n; k=k+1 ) {
        System.out.println(k + "    " + josephus(k));
    }
  }


}  // class Josephus
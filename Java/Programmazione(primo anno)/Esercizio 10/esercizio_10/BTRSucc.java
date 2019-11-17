package esercizio_10;

/** Questa è l'esercitazione sulla codifica (parte 1)
 *  Il programma restituisce il numero successivo in codifica BTR
 *
 */

public class BTRSucc {

    public static void main(String[] args) {
        BTRSucc prova = new BTRSucc();
        System.out.println(prova.btrSucc("+--+.+"));
    }

    private String btrSucc(String btrStart) {
        int n = btrStart.length();
        char lsb = btrStart.charAt(n - 1);
        if (n == 1) {
            if (lsb == '+'){
                return "+-";
            } else {
                return "+";
            }
        } else {
            String pre = btrStart.substring(0, n - 1);
            if (lsb == '+'){
                return btrSucc(pre) + "-";
            } else {
                return (lsb == '-') ? pre + "." : pre + "+";
            }
        }
    }
}

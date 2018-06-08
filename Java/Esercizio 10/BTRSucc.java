/** Questa è l'esercitazione sulla codifica (parte 1)
 *  Il programma restituisce il numero successivo in codifica BTR
 *
 */

public class BTRSucc {

    int n;
    char lsb;

    public static void main(String[] args) {
        BTRSucc prova = new BTRSucc();
        System.out.println(prova.btrSucc("+--+.+"));
    }

    public String btrSucc(String btrStart){
        n = btrStart.length();
        lsb = btrStart.charAt(n-1);
        if (n == 1) {
            if (lsb == '+'){
                return "+-";
            } else {
                return "+";
            }
        } else {
            String pre = btrStart.substring(0, n-1);
            if (lsb == '+'){
                return btrSucc(pre) + "-";
            } else {
                return (lsb == '-') ? pre + "." : pre + "+";
            }
        }
    }
}

package esercizio_10;

/** Questa è l'esercitazione sulla codifica (parte 2)
 *  Il programma gestisce la codifica di un numero binario in complemento a uno
 */

public class OnesComplement {

    public static void main(String[] args) {
        new OnesComplement();
    }

    private OnesComplement() {
        System.out.println(onesComplement("1111000010"));
    }

    private String bitComplement(String bit) {
        return (bit.equals("0")) ? "1" : "0";
    }

    private String onesComplement(String bin) {
        if (bin.equals("")){
            return "";
        } else {
            String s1 = "";
            for (int i=0; i<bin.length(); i++){
                s1 = s1 + bitComplement(bin.substring(i, i+1));
            }
            return s1;
        }
    }
}

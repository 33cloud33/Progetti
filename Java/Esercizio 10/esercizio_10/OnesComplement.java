package esercizio_10;

/** Questa è l'esercitazione sulla codifica (parte 2)
 *  Il programma gestisce la codifica di un numero binario in complemento a uno
 */

public class OnesComplement {

    public static void main(String[] args) {
        new OnesComplement();
    }

    OnesComplement(){
        System.out.println(onesComplement("1111000010"));
    }

    public String bitComplement(String bit){
        if (bit.equals("0")){
            return "1";
        } else return "0";
    }

    public String onesComplement(String bin){

        if (bin.equals("")){
            return "";
        } else {
            String s1 = new String();
            for (int i=0; i<bin.length(); i++){
                s1 = s1 + bitComplement(bin.substring(i, i+1));
            }
            return s1;
        }
    }
}

package llis_v2;

public class LLIS {

    public static void main(String[] args){
        int[] lista0 = {54, 52, 42, 33, 14, 40, 37, 61, 53, 1} ;
        int[] lista1 = {5, 4, 3, 2, 1};
        int[] lista2 = {47, 38, 39, 25, 44} ;
        int[] lista3 = {27, 90, 7, 29, 49, 8, 53, 1, 28, 6} ;
        int[] lista4 = {9, 46, 54, 71, 60, 47, 0, 32, 25, 61} ;
        int[] lista5 = {1,4,2,3} ;

        //Memoization
        System.out.println("Memoization:");
        System.out.println("Risultato: " + LLISMem.llis(lista0));
        System.out.println("Risultato: " + LLISMem.llis(lista1));
        System.out.println("Risultato: " + LLISMem.llis(lista2));
        System.out.println("Risultato: " + LLISMem.llis(lista3));
        System.out.println("Risultato: " + LLISMem.llis(lista4));
        System.out.println("Risultato: " + LLISMem.llis(lista5));
        //Programmazione Dinamica
        System.out.println("Programmazione dinamica:");
        System.out.println("Risultato: " + LLISDynamic.llis(lista0));
        System.out.println("Risultato: " + LLISDynamic.llis(lista1));
        System.out.println("Risultato: " + LLISDynamic.llis(lista2));
        System.out.println("Risultato: " + LLISDynamic.llis(lista3));
        System.out.println("Risultato: " + LLISDynamic.llis(lista4));
        System.out.println("Risultato: " + LLISDynamic.llis(lista5));
    }
}
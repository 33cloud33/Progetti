public class InsertionSort {

    /**
     * Controlla se l'array è ordinato e inoltre stampa il risultato
     * @param arr1          Array iniziale da ordinare
     */

    public static void insertionSort(int[] arr1){
        int temp = 0;
        for (int i = 1; i < arr1.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(arr1[j] < arr1[j-1]){
                    arr1 = change(arr1, temp, j);
                }
            }
        }
        printArr(arr1);
    }

    /**
     * Scambia di posizione 2 valori di un array.
     *
     * @param arr1          Array con valori da scambiare
     * @param temp          Variabile che permette lo scambio di posizione
     * @param j             Puntatore elementi array
     * @return              Array modificato con valori scambiati
     */
    private static int[] change(int[] arr1, int temp, int j){
        temp = arr1[j];
        arr1[j] = arr1[j-1];
        arr1[j-1] = temp;
        return arr1;
    }

    /**
     * Stampa l'array passato come parametro
     *
     * @param arr1           Array da stampare
     */
    private static void printArr(int[] arr1){
        for(int i:arr1){
            System.out.print(i);
            System.out.print(" ");
        }
    }

}

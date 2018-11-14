package algorithmChooser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SelectAlgorithm {

    private final int[] vettoreDaOrdinare = new int[]{13, 12, 1, 41, 5, 86, 88, 21};
    private final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private final ListaAlgoritmi[] tuttiGliAlgoritmi = ListaAlgoritmi.values();

    void run() throws IOException, ArrayIndexOutOfBoundsException, NumberFormatException {
        this.visualizzaMenu();
        int selezione = Integer.parseInt(buffer.readLine()) - 1;
        ordinaVettore(tuttiGliAlgoritmi[selezione], vettoreDaOrdinare);
    }

    private void visualizzaMenu() {
        System.out.println("Quale algoritmo vuoi utilizzare?");
        System.out.println("1 - InsertionSort");
        System.out.println("2 - SelectionSort");
        System.out.println("3 - MergeSort");
        System.out.println("4 - HeapSort");
        System.out.println("5 - QuickSort");
    }

    private void ordinaVettore(ListaAlgoritmi algoritmo, int[] vettoreDaOrdinare) {
        System.out.println("Il vettore ordinato con " + algoritmo.name() + " è: ");
        switch (algoritmo) {
            case INSERTIONSORT:
                InsertionSort.insertionSort(vettoreDaOrdinare);
                break;
            case SELECTIONSORT:
                SelectionSort.selectionSort(vettoreDaOrdinare);
                break;
            case MERGESORT:
                MergeSort.mergeSort(vettoreDaOrdinare);
                break;
            case HEAPSORT:
                HeapSort.heapSort(vettoreDaOrdinare);
                break;
            case QUICKSORT:
                QuickSort.quickSort(vettoreDaOrdinare);
                break;
        }
    }


}

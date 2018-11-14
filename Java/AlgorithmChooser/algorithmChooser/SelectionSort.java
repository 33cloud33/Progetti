package algorithmChooser;

class SelectionSort {

    public static void selectionSort(int[] A) {
        int posMin = 0;
        for (int pos1 = 0; pos1 < A.length; pos1++) {
            int min = A[pos1];
            for (int pos2 = pos1; pos2 < A.length; pos2++) {
                if (min >= A[pos2]) {
                    min = A[pos2];
                    posMin = pos2;
                }
            }
            A[posMin] = A[pos1];
            A[pos1] = min;
        }
        printResult(A);
    }

    private static void printResult(int A[]) {
        for (int aA : A) {
            System.out.println(aA);
        }
    }
}

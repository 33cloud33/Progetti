package src.Algoritmi;

public class
MergeSort {
    public static void main(String[] args) {
        new MergeSort().MergeSort(new int[]{10,3,1,7,8,4,11,13,2,17},0,10);
        //new SelectionSort(new int[]{9,8,7,6,5,4,3,2,1});
        //new SelectionSort(new int[]{1,2,3,4,5,6,7,8,9});

    }

    /**
     * Divide in 2 l'array e li passa a Merge ricorsivamente
     * @param A        array di interi NON ORDINATO
     * @param p        puntatore che parte dall'inizio dell'array
     * @param q        puntatore che parte dalla fine
     */
    public void MergeSort(int[] A, int p, int q){
        if(p<q){
            int r=(p+q)/2;
            MergeSort(A, p, r);
            MergeSort(A,r+1,q);
            Merge(A,p,q,r);

        }
    }

    /**
     * Esegue l'operazione di ordinamento su un array dato
     * @param A         array da ordinare
     * @param p         puntatore che parte dall'inizio dell'array
     * @param q         puntatore che parte dalla fine dell'array
     * @param r         puntatore che si trova a metà
     * @return          ritorna l'array ordinato
     */

    public int[] Merge(int[] A, int p, int q, int r) {
        int[]B = new int[A.length];
        System.arraycopy(A, 0,  B, A.length, A.length);
        int i = p;
        int j = r+1;
        for(int k=p; k<q; k++){
            if(i<=r && (j<=q || A[i] <= A[j])){
                    B[k]=A[i];
                    i=i+1;
                }else{
                    B[k]=A[j];
                    j=j+1;

            }
        }
        return B;
    }


}

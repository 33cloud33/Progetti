package llis_v2;

import java.util.ArrayList;
import java.util.Iterator;

class LLISDynamic {

    /**
     * Programma con tecnica programmazione dinamica
     */

    public static int llis(int s[]){
        int n=s.length;
        int [][]h = new int[n+1][n+1];
        int []sn = new int[n+1];
        /*Creazione nuova lista*/
        for(int i=0;i<n;i++){
            sn[i] = s[i];
        }
        /*Ciclo for che parte da n e arriva a 0*/
        for(int i=n;i>=0;i--){
            for (int j=n;j>=0;j--){
                if(i == n){
                    h[i][j] = 0;
                }else if(sn[i]<=sn[j]){
                    h[i][j] = h[i+1][j];
                }else{
                    h[i][j] = Math.max(1+h[i+1][i], h[i+1][j]);
                }
            }
        }
        /* Stampa la matrice */
        for (int i=0; i<n; i++) {
            for (int j=0; j<=n; j++) {
                System.out.print(h[i][j] + " ");
            }
            System.out.println();
        }

        // Crea un ArrayList con una delle soluzioni
        lista(h, sn);


        return h[0][n];
    }

    private static ArrayList lista(int[][] h, int[] s){
        int sol = h[0][h.length-1];

        ArrayList listaSol = new ArrayList();
        for (int i=0; i<h.length; i++) {
            if (h[i][i] == sol-1){
                listaSol.add(s[i]);
                sol = sol - 1;
            }
        }
        Iterator ciclo = listaSol.iterator();
        while(ciclo.hasNext()) {
            System.out.println(ciclo.next());
        }
        return listaSol;
    }

}

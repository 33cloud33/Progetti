class LLISMem {

    /**
     * Programma con tecnica memoization
     */

    public static int llis(int[] s){
        int n = s.length;
        int[] sn = new int[n+1];
        for(int i=0;i<n;i++){
            sn[i] = s[i];
        }
        int[][] h = new int[n+1][n+1];
        return llisRec(sn, 0, n ,h);
    }
    private static int llisRec( int[] s, int i, int j, int [][] h) {
        final int n = s.length-1;

        if(h[i][j] == 0){
            if(i == n){
                h[i][j] = 0;
            }else if(s[i]<=s[j]){
                h[i][j] = llisRec (s, i+1, j, h);
            }else{
                h[i][j] = Math.max(1+llisRec(s,i+1,i,h),llisRec(s,i+1,j,h));
            }
        }
        return h[i][j];
    }

}

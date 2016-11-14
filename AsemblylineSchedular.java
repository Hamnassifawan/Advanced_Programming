import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by User on 11/13/2016.
 */
public class AsemblylineSchedular {
    public int NUM_LINE = 3;
    public int NUM_STATION = 6;
    public int T1[] = new int[6];
    public int T2[] = new int[6];
    public int T3[] = new int[6];





    public int Assemblyline_DP(int[][] a, int[][] t, int[] e, int[] x) {

        T1[0] = e[0] + a[0][0]; // time taken to leave first station in line 1
        T2[0] = e[1] + a[1][0]; // time taken to leave first station in line 2
        T3[0] = e[2] + a[2][0]; // time taken to leave first station in line 3

        // Fill tables T1[] and T2[] using the above given recursive relations
        for (int i = 1; i < NUM_STATION; ++i) {
            T1[i] = min3(T1[i - 1] + a[0][i], T2[i - 1] + t[1][i] + a[0][i],T3[i-1]+a[2][i]+t[4][i]);
            T2[i] = min3((T2[i - 1] + a[1][i]), (T1[i - 1] + t[0][i] + a[1][i]), (T3[i - 1] + a[1][i] + t[3][i]));
            T3[i] = min3(T3[i - 1] + a[2][i], T2[i - 1] + t[2][i] + a[2][i],t[5][i]+T1[i-1]+a[0][i]);

        }

        // Consider exit times and retutn minimum
        return min3(T1[NUM_STATION - 1] + x[0], T2[NUM_STATION - 1] + x[1],T3[NUM_STATION - 1] + x[2]);
    }

    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    public static int min3(int a, int b, int c) {
        int min = a;
        if (min > b) min = b;
        if (min > c) min = c;
        return min;
    }

    public int Assemblyline_R_part(int[][] a, int[][] t, int[] e, int[] x, int n, int j){
        if(n == 0){
            return e[j] + a[j][0];
        }

        int T1 = Integer.MAX_VALUE;
        int T2 = Integer.MAX_VALUE;
        int T3 = Integer.MAX_VALUE;
        if(j == 0){//T1[i] = min3(T1[i - 1] + a[0][i], T2[i - 1] + t[1][i] + a[0][i],T3[i-1]+a[2][i]+t[4][i]);
            T1 =min3(Assemblyline_R_part(a, t, e, x, n-1, 0) + a[0][n],
                    Assemblyline_R_part(a, t, e, x, n-1, 1) + t[1][n] + a[0][n],Assemblyline_R_part(a, t, e, x, n-1, 2) + t[4][n] + a[2][n]);

        }else if(j == 1){//min3((T2[i - 1] + a[1][i]), (T1[i - 1] + t[0][i] + a[1][i]), (T3[i - 1] + a[1][i] + t[3][i]));
            T2 = min3(Assemblyline_R_part(a, t, e, x, n-1, 1) + a[1][n],
                    Assemblyline_R_part(a, t, e, x, n-1, 0) + t[0][n] + a[1][n],
                    Assemblyline_R_part(a, t, e, x, n-1, 2) + t[3][n] + a[1][n]);

        }else if(j == 2){//min3(T3[i - 1] + a[2][i], T2[i - 1] + t[2][i] + a[2][i],t[5][i]+T1[i-1]+a[0][i]);
            T3 =min3(Assemblyline_R_part(a, t, e, x, n-1, 2) + a[2][n],
                    Assemblyline_R_part(a, t, e, x, n-1, 1) + t[2][n] + a[2][n],Assemblyline_R_part(a, t, e, x, n-1, 0) + t[5][n] + a[0][n]);


        }

        return min3(T1,T2,T3);
    }
    public int Assemblyline_R(int[][] a, int[][] t, int[] e, int[] x){
        int n = a[0].length-1;
        return min3(Assemblyline_R_part(a,t, e, x, n, 0) + x[0],
                Assemblyline_R_part(a,t, e, x, n, 1) + x[1],Assemblyline_R_part(a,t, e, x, n, 2) + x[2]);
    }


}

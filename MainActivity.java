import java.util.function.Function;

/**
 * Created by User on 11/13/2016.
 */
public class MainActivity {

    public static void main(String[] args) {

        int A[][] = {{3, 5, 3, 4,4,4}, {8, 2, 6, 4,5,7}, {5, 5, 3, 5,6,2}};
        int T[][] = {{0,2,3,1,3,4}, {0,2,1,2,2,1}, {0,2,3,4,4,1},{0,1,1,2,3,3},{0,2,2,1,1,3},{0,1,2,1,2,1}};
        int E[] = {2, 1,1};
        int X[] = {3,1,1};
        AsemblylineSchedular ass = new AsemblylineSchedular();
        System.out.println("*********************Dynamic Programming**************************************");
        System.out.println("The least amount of time should be 26 for the above values:Answer->"+ass.Assemblyline_DP(A, T, E, X));
        System.out.println("*********************Recursion**************************************");
        System.out.println("The least amount of time should be 26 for the above values:Answer->"+ass.Assemblyline_R(A, T, E, X));


    }

}

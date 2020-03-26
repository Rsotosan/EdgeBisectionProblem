import model.Bisection;
import model.Graph;

import java.util.ArrayList;
import java.util.HashSet;

public class Evaluator {
    public static int evaluate(Graph g, Bisection b){
        int cutSize = 0;
        int[][] matrix = g.getAdjMatrix();
        HashSet<Integer> v1 = b.getVertexList1();
        HashSet<Integer> v2 = b.getVertexList2();
        for(int i=0; i<matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1) {
                    if ((v1.contains(i) && v2.contains(j)) || (v1.contains(j) && v2.contains(i))) {
                        cutSize++;
                    }
                }
            }
        }
        return cutSize;
    }

    public static int evaluateByList(Graph g, Bisection b){
        int cutSize = 0;
        ArrayList<Integer>[] list = g.getAdjList();
        HashSet<Integer> v1 = b.getVertexList1();
        HashSet<Integer> v2 = b.getVertexList2();
        for(int i=0; i<g.getAdjList().length; i++){
            ArrayList<Integer> l = list[i];
            if(l!=null) {
                for (int j : l) {
                    if ((v1.contains(i) && v2.contains(j)) || (v1.contains(j) && v2.contains(i))) {
                        cutSize++;
                    }
                }
            }
        }
        return cutSize;
    }
}

import model.Bisection;
import model.Graph;

public class Evaluator {
    public static int evaluate(Graph g, Bisection b){
        int cutSize = 0;
        int[][] matrix = g.getMatrix();
        int[] v1 = b.getVertexList1();
        int[] v2 = b.getVertexList2();
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                if(matrix[i][j]==1){
                    if(!((v1[i]==0 && v1[j]==0) || (v2[i]==0 && v2[j]==0))){
                        cutSize++;
                    }
                }
            }
        }
        return cutSize;
    }
}

package model;

import java.util.Arrays;

public class Bisection {
    private int[] vertexList1;
    private int[] vertexList2;

    public void newSolution(int[] v1, int[] v2){
        this.vertexList1 = Arrays.copyOf(v1, v1.length);
        this.vertexList2 = Arrays.copyOf(v2, v2.length);
    }

    public int[] getVertexList1() {
        return vertexList1;
    }

    public int[] getVertexList2() {
        return vertexList2;
    }

    @Override
    public String toString() {
        return "Bisection{" +
                "vertexList1=" + Arrays.toString(vertexList1) +
                ", vertexList2=" + Arrays.toString(vertexList2) +
                '}';
    }
}

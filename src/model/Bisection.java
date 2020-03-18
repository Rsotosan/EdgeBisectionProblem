package model;

import java.util.HashSet;

public class Bisection {
    private HashSet<Integer> vertexList1;
    private HashSet<Integer> vertexList2;

    public void newSolution(HashSet<Integer> v1, HashSet<Integer> v2){
        vertexList1 = (HashSet) v1.clone();
        this.vertexList2 = (HashSet) v2.clone();
    }

    public HashSet<Integer> getVertexList1() {
        return vertexList1;
    }

    public HashSet<Integer> getVertexList2() {
        return vertexList2;
    }

    @Override
    public String toString() {
        return "Bisection{" +
                "vertexList1=" + vertexList1 +
                ", vertexList2=" + vertexList2 +
                '}';
    }
}

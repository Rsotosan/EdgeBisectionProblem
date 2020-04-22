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

    public boolean swap(int vertex1, int vertex2){
        if(vertexList1.contains(vertex1) && vertexList2.contains(vertex2)){
            vertexList1.remove(vertex1);
            vertexList1.add(vertex2);
            vertexList2.remove(vertex2);
            vertexList2.add(vertex1);
        } else if (vertexList2.contains(vertex1) && vertexList1.contains(vertex2)){
            vertexList1.remove(vertex2);
            vertexList1.add(vertex1);
            vertexList2.remove(vertex1);
            vertexList2.add(vertex2);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bisection{" +
                "vertexList1=" + vertexList1 +
                ", vertexList2=" + vertexList2 +
                '}';
    }
}

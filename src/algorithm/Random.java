package algorithm;

import model.Bisection;
import model.Graph;

import java.util.HashSet;
import java.util.stream.Collectors;

public class Random implements Algorithm{

    public Graph graph;

    public Random(Graph graph){
        this.graph = graph;
    }

    public Bisection generateSolution(){
        Bisection bisection = new Bisection();
        int[][] m = graph.getMatrix();
        HashSet<Integer> v1 = new HashSet<Integer>();
        //LinkedList<Integer> random = new Random().ints(0, m.length).distinct().limit((m.length / 2)).sorted().boxed().collect(Collectors.toCollection(LinkedList<Integer>::new));
        HashSet<Integer> v2 = new java.util.Random().ints(0, m.length).distinct().limit((m.length / 2)).sorted().boxed().collect(Collectors.toCollection(HashSet<Integer>::new));
        for(int i=0; i<m.length; i++){
            if(!v2.contains(i)){
                v1.add(i);
            }
        }
        bisection.newSolution(v1, v2);
        return bisection;
    }
}

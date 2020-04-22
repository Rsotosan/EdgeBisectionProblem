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
        HashSet<Integer> v1 = new HashSet<Integer>();
        HashSet<Integer> v2 = new java.util.Random().ints(0, graph.order()).distinct().limit((graph.order() / 2)).sorted().boxed().collect(Collectors.toCollection(HashSet<Integer>::new));
        for(int i=0; i<graph.order(); i++){
            if(!v2.contains(i)){
                v1.add(i);
            }
        }
        bisection.newSolution(v1, v2);
        return bisection;
    }
}

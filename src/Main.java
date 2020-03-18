import model.Bisection;
import model.Graph;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 100; i++) {
            testEvaluator();
        }
    }

    public static void testEvaluator() throws IOException {
        Graph graph = new Graph();

        graph.readGraph(new File("resources\\testGraph5V.txt"));
        //graph.readGraph(new File("resources\\hb\\nos1.mtx.rnd"));
        //graph.readGraph(new File("resources\\hb\\nos3.mtx.rnd"));

        Bisection bisection = generateRandomBisection(graph);
        System.out.println(bisection.toString());
        System.out.println(Evaluator.evaluate(graph, bisection));
    }

    public static Bisection generateRandomBisection(Graph g){
        Bisection bisection = new Bisection();
        int[][] m = g.getMatrix();
        HashSet<Integer> v1 = new HashSet<Integer>();
        //LinkedList<Integer> random = new Random().ints(0, m.length).distinct().limit((m.length / 2)).sorted().boxed().collect(Collectors.toCollection(LinkedList<Integer>::new));
        HashSet<Integer> v2 = new Random().ints(0, m.length).distinct().limit((m.length / 2)).sorted().boxed().collect(Collectors.toCollection(HashSet<Integer>::new));
        for(int i=0; i<m.length; i++){
            if(!v2.contains(i)){
                v1.add(i);
            }
        }
        bisection.newSolution(v1, v2);
        return bisection;
    }
}

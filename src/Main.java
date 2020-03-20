import algorithm.Algorithm;
import algorithm.Grasp;
import algorithm.Random;
import model.Bisection;
import model.Graph;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10000; i++) {
            evaluator();
            //randomEvaluator();
            //graspEvaluator();
        }
    }

    public static void evaluator() throws IOException {
        Graph graph = new Graph();
        graph.readGraph(new File("resources\\testGraph5V.txt"));
        //graph.readGraph(new File("resources\\hb\\nos1.mtx.rnd"));
        //graph.readGraph(new File("resources\\hb\\nos3.mtx.rnd"));

        Grasp grasp = new Grasp(graph);
        Algorithm algorithm;

        algorithm = new Grasp(graph);
        //algorithm = new Random(graph);

        Bisection bisection = algorithm.generateSolution();
        //System.out.println(bisection.toString());
        System.out.println(Evaluator.evaluate(graph, bisection));
    }
}

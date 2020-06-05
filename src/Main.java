import Evaluator.Evaluator;
import constructive.Constructive;
import constructive.Grasp;
import constructive.Random;
import localSearch.LocalSearch;

import localSearch.TabuSearch;
import model.Bisection;
import model.Graph;
import Evaluator.Evaluator;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        //graph.readGraph(new File("resources\\testGraph5V.txt"));
        //graph.readGraph(new File("resources\\hb\\nos1.mtx.rnd"));
        graph.readGraph(new File("resources\\hb\\bcsstk06.mtx.rnd"));
        //graph.readGraph(new File("resources\\hb\\nos3.mtx.rnd"));
        for (int i = 0; i < 100; i++) {
                evaluator(graph);
        }
    }

    public static void evaluator(Graph graph) throws IOException {
        Grasp grasp = new Grasp(graph);
        Constructive constructive;

        constructive = new Grasp(graph);
        //constructive = new Random(graph);

        Bisection bisection = constructive.generateSolution();
        Bisection bisection2 = new Bisection();
        bisection2.newSolution(bisection.getVertexList1(),bisection.getVertexList2());
        //System.out.println(bisection.toString());
        //System.out.println(Evaluator.evaluate(graph, bisection));
        //System.out.println(Evaluator.Evaluator.evaluateByList(graph, bisection));
        System.out.println("Constructive solution=" + Evaluator.evaluate(graph, bisection));

        LocalSearch localSearch = new LocalSearch(graph, bisection);
        bisection = localSearch.search();
        //System.out.println(bisection.toString());
        System.out.println("Local search solution=" + Evaluator.evaluate(graph, bisection));

        TabuSearch tabuSearch = new TabuSearch(graph, bisection2);
        bisection2 = tabuSearch.search(5);
        //System.out.println(bisection.toString());
        System.out.println("Tabu search solution=" + Evaluator.evaluate(graph, bisection2));
        System.out.println("------------------------------");
    }
}


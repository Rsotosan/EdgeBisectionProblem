package algorithm;

import model.Bisection;
import model.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class Grasp implements Algorithm{
    public Graph graph;

    public Grasp(Graph graph){
        this.graph = graph;
    }

    public Bisection generateSolution(){
        //Define and initialize necessary variables
        Bisection solution = new Bisection();
        int minCost = 0;
        int maxCost = 0;
        double th = 0;
        double alpha = 0.5;
        HashSet<Integer> vertexList1 = new HashSet<Integer>();
        HashSet<Integer> vertexList2 = new HashSet<Integer>();

        //Define and initialize candidate list
        LinkedList<Candidate> candidates = new LinkedList<Candidate>();
        for (int i = 0; i<graph.order(); i++){
            candidates.add(new Candidate(i));
        }
        //First cost evaluate of candidates
        evaluateCost(vertexList1, candidates);
        //Iteration for a solution
        while(vertexList1.size() < graph.order() / 2){
            minCost = candidates.getLast().getCost();
            maxCost = candidates.getFirst().getCost();
            th = maxCost - alpha * (maxCost - minCost);

            //improvable?
            int rcl = 0;
            while(rcl < candidates.size() && candidates.get(rcl).getCost() >= th){
                rcl++;
            }

            //Get a random candidate
            Random r = new Random();
            Candidate randomCandidate = candidates.remove(r.nextInt(rcl));
            vertexList1.add(randomCandidate.getId());

            //Reevaluate cost of partial candidate list
            evaluateCost(vertexList1, candidates);
        }
        for(int i = 0; i<graph.order(); i++){
            if(!vertexList1.contains(i)){
                vertexList2.add(i);
            }
        }
        solution.newSolution(vertexList1, vertexList2);
        return solution;
    }

    private void evaluateCost(HashSet<Integer> partialSolution, LinkedList<Candidate> candidates){
        for (Candidate c: candidates) {
            int cost = 0;
            for (Integer id: partialSolution){
                if(graph.adjacent(c.getId(), id)) cost ++;
                if(graph.adjacent(id, c.getId())) cost ++;
            }
            c.setCost(cost);
        }
        candidates.sort((Candidate c1, Candidate c2) -> c2.getCost() - c1.getCost());
    }
}

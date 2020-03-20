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
        for (int i=0; i<graph.getMatrix().length; i++){
            candidates.add(new Candidate(i));
        }
        //First cost evaluate of candidates
        evaluateCost(vertexList1, candidates);
        //Iteration for a solution
        while(vertexList1.size() < graph.getMatrix().length / 2){
            minCost = candidates.getLast().getCost();
            maxCost = candidates.getFirst().getCost();
            th = maxCost - alpha * (maxCost - minCost);

            //Mejorable
            int rcl = 0;
            while(rcl < candidates.size() && candidates.get(rcl).getCost() >= th){
                rcl++;
            }
            Random r = new Random();
            Candidate randomCandidate = candidates.remove(r.nextInt(rcl));
            vertexList1.add(randomCandidate.getId());
            evaluateCost(vertexList1, candidates);
        }
        for(int i=0; i<graph.getMatrix().length; i++){
            if(!vertexList1.contains(i)){
                vertexList2.add(i);
            }
        }
        solution.newSolution(vertexList1, vertexList2);
        return solution;
    }

    private void evaluateCost(HashSet<Integer> partialSolution, LinkedList<Candidate> candidates){
        int[][] matrix = graph.getMatrix();
        for (Candidate c: candidates) {
            int cost = 0;
            for (Integer id: partialSolution){
                for(int i = 0; i<matrix.length; i++){
                    if (matrix[c.getId()][i] == 1) cost++;
                }
                for(int i = 0; i<matrix.length; i++){
                    if (matrix[i][c.getId()] == 1) cost++;
                }
            }
            c.setCost(cost);
        }
        candidates.sort((Candidate c1, Candidate c2) -> c2.getCost() - c1.getCost());
    }
}

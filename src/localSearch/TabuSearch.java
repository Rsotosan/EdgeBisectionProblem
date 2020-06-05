package localSearch;

import Evaluator.Evaluator;
import model.Bisection;
import model.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TabuSearch {
    private Graph graph;
    private Bisection bisection;

    public TabuSearch(Graph graph, Bisection bisection) {
        this.graph = graph;
        this.bisection = bisection;
    }

    public Bisection search(int tenure){
        LinkedList<Node> nodeQueue = new LinkedList<>();
        ArrayList<Node> nodes1 = new ArrayList<>();
        ArrayList<Node> nodes2 = new ArrayList<>();
        int costChange;
        int minCostChange = Integer.MAX_VALUE;
        int maxIterations = 5;
        int iterationCounter = 0;
        Node potentialNode1 = null;
        Node potentialNode2 = null;
        Bisection potentialSolution = null;
        int potentialCost = 0;
        for(int v: bisection.getVertexList1()){
            nodes1.add(new Node(v));
        }
        for(int v: bisection.getVertexList2()){
            nodes2.add(new Node(v));
        }
        boolean noImprove = false;
        while(!noImprove) {
            sortNodes(nodes1, nodes2);
            noImprove = true;
            for (Node n1 : nodes1) {
                if(!nodeQueue.contains(n1)) {
                    int externalCostV1 = n1.getCost();
                    int ownCostV1 = 0;
                    for (Node auxN1 : nodes1) {
                        if (n1.getId() != auxN1.getId() && graph.adjacent(n1.getId(), auxN1.getId())) ownCostV1++;
                        if (n1.getId() != auxN1.getId() && graph.adjacent(auxN1.getId(), n1.getId())) ownCostV1++;
                    }
                    for (Node n2 : nodes2) {
                        if(!nodeQueue.contains(n2)){
                            int externalCostV2 = n2.getCost();
                            int ownCostV2 = 0;
                            for (Node auxN2 : nodes2) {
                                if (n2.getId() != auxN2.getId() && graph.adjacent(n2.getId(), auxN2.getId())) ownCostV2++;
                                if (n2.getId() != auxN2.getId() && graph.adjacent(auxN2.getId(), n2.getId())) ownCostV2++;
                            }
                            if (graph.adjacent(n1.getId(), n2.getId())) {
                                externalCostV1--;
                                externalCostV2--;
                            }
                            if (graph.adjacent(n2.getId(), n1.getId())) {
                                externalCostV1--;
                                externalCostV2--;
                            }
                            costChange = (ownCostV1 + ownCostV2) - (externalCostV1 + externalCostV2);
                            if (costChange < 0) {
                                swapNodes(n1, n2, nodes1, nodes2);
                                addNodes(nodeQueue, n1, n2, tenure);
                                noImprove = false;
                                break;
                            } else if(costChange < minCostChange) {
                                potentialNode1 = n1;
                                potentialNode2 = n2;
                            }
                        }
                    }
                    if (!noImprove) {
                        break;
                    }
                }
            }
            if(noImprove && iterationCounter < maxIterations) {
                if(potentialSolution == null){
                    potentialSolution = new Bisection();
                    potentialSolution.newSolution(bisection.getVertexList1(), bisection.getVertexList2());
                    potentialCost = Evaluator.evaluate(graph,potentialSolution);
                }
                swapNodes(potentialNode1, potentialNode2, nodes1, nodes2);
                addNodes(nodeQueue, potentialNode1, potentialNode2, tenure);
                if(Evaluator.evaluate(graph,bisection) < potentialCost){
                    potentialSolution = new Bisection();
                    potentialSolution.newSolution(bisection.getVertexList1(), bisection.getVertexList2());
                    potentialCost = Evaluator.evaluate(graph,potentialSolution);
                    iterationCounter = 0;
                }
                noImprove = false;
                iterationCounter++;
            }
        }
        if(potentialCost < Evaluator.evaluate(graph,bisection)){
            bisection = potentialSolution;
        }
        return bisection;
    }



    private void sortNodes(ArrayList<Node> nodes1, ArrayList<Node> nodes2){
        evaluateCost(nodes1,nodes2);
        nodes1.sort((Node n1, Node n2) -> n2.getCost() - n1.getCost());
        nodes2.sort((Node n1, Node n2) -> n2.getCost() - n1.getCost());
    }

    private void evaluateCost(ArrayList<Node> nodes1, ArrayList<Node> nodes2){
        for (Node n1: nodes1) {
            n1.setCost(0);
        }
        for(Node n2: nodes2){
            n2.setCost(0);
        }
        for (Node n1: nodes1) {
            for(Node n2: nodes2){
                if(graph.adjacent(n1.getId(), n2.getId())){
                    n1.setCost(n1.getCost() + 1);
                    n2.setCost(n2.getCost() + 1);
                }
                if(graph.adjacent(n2.getId(), n1.getId())){
                    n1.setCost(n1.getCost() + 1);
                    n2.setCost(n2.getCost() + 1);
                }
            }
        }
    }

    private void swapNodes(Node n1, Node n2, ArrayList<Node> nodes1 ,ArrayList<Node> nodes2){
        bisection.swap(n1.getId(),n2.getId());
        if(nodes1.contains(n1)){
            nodes1.remove(n1);
            nodes1.add(n2);
            nodes2.remove(n2);
            nodes2.add(n1);
        } else {
            nodes1.remove(n2);
            nodes1.add(n1);
            nodes2.remove(n1);
            nodes2.add(n2);
        }
    }

    private void addNodes(LinkedList<Node> nodeQueue, Node n1, Node n2, int t){
        if(nodeQueue.size() >= t){
            nodeQueue.pollLast();
            nodeQueue.pollLast();
        }
        nodeQueue.addFirst(n1);
        nodeQueue.addFirst(n2);
    }
}

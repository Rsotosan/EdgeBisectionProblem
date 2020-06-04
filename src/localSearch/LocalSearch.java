package localSearch;

import Evaluator.Evaluator;
import model.Bisection;
import model.Graph;

import java.util.ArrayList;

public class LocalSearch {
    private Graph graph;
    private Bisection bisection;

    public LocalSearch(Graph graph, Bisection bisection) {
        this.graph = graph;
        this.bisection = bisection;
    }

    public Bisection search(){
        ArrayList<Node> nodes1 = new ArrayList<Node>();
        ArrayList<Node> nodes2 = new ArrayList<Node>();
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
                int externalCostV1 = n1.getCost();
                int ownCostV1 = 0;
                for (Node auxN1 : nodes1) {
                    if (n1.getId() != auxN1.getId() && graph.adjacent(n1.getId(), auxN1.getId())) ownCostV1++;
                    if (n1.getId() != auxN1.getId() && graph.adjacent(auxN1.getId(), n1.getId())) ownCostV1++;
                }
                for (Node n2 : nodes2) {
                    int externalCostV2 = n2.getCost();
                    int ownCostV2 = 0;
                    for (Node auxN2 : nodes2) {
                        if (n2.getId() != auxN2.getId() && graph.adjacent(n2.getId(), auxN2.getId())) ownCostV2++;
                        if (n2.getId() != auxN2.getId() && graph.adjacent(auxN2.getId(), n2.getId())) ownCostV2++;
                    }
                    if (graph.adjacent(n1.getId(), n2.getId())){
                        externalCostV1--;
                        externalCostV2--;
                    }
                    if (graph.adjacent(n2.getId(), n1.getId())){
                        externalCostV1--;
                        externalCostV2--;
                    }
                    if (ownCostV1 + ownCostV2 < externalCostV1 + externalCostV2) {
                        swapNodes(n1, n2, nodes1, nodes2);
                        noImprove = false;
                        break;
                    }
                }
                if(!noImprove){
                    break;
                }
            }
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
}

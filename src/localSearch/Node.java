package localSearch;

public class Node {
    private int id;
    private int cost;

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", cost=" + cost +
                '}';
    }
}

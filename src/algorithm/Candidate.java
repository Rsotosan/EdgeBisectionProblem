package algorithm;

public class Candidate {
    private int id;
    private int cost;

    public Candidate(int id) {
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

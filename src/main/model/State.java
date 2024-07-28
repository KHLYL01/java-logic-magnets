package main.model;

public class State implements Comparable<State>{
    private  State parent;
    private BoardModel boardModel;
    private int cost;
    private int heuristic;

    public State(State parent, BoardModel boardModel)
    {
        this.parent = parent;
        this.boardModel = boardModel;
    }

    public State(State parent, BoardModel boardModel, int cost) {
        this.parent = parent;
        this.boardModel = boardModel;
        this.cost = cost;
    }

    public State(State parent, BoardModel boardModel, int cost , int heuristic) {
        this.parent = parent;
        this.boardModel = boardModel;
        this.cost = cost;
        this.heuristic = heuristic;
    }


    public boolean hasPrevious()
    {
        return this.parent != null;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void setBoardModel(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    public int getCost() {
        return cost;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(State o) {
        return this.cost - o.cost;
    }

    @Override
    public String toString() {
        return "State{" +
//                "parent= " + parent +
                "cost=" + cost +
                '}';
    }
}



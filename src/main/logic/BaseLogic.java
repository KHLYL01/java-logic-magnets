package main.logic;

import main.action.Actions;
import main.model.BoardModel;
import main.model.State;

import java.util.ArrayList;

public class BaseLogic {
    public ArrayList<State> VisitedList = new ArrayList<>();

    ArrayList<BoardModel> solution = new ArrayList<>();

    public boolean isVisited(BoardModel boardModel)
    {
        for (State state : VisitedList)
        {
            if (Actions.Equals(state.getBoardModel(), boardModel))
                return true;
        }
        return false;
    }

    protected void checkSolve(State solve)
    {
        if (solve != null) {
            System.out.println("====== Successfully Solved ======");
        }else {
            System.out.println("NO SOLUTION FOUND.");
            return;
        }
        while (solve.hasPrevious()) {
            solution.add(solve.getBoardModel());
            solve = solve.getParent();
        }


        for (int i = solution.size() - 1; i >= 0; i--) {
            Actions.printBoard(solution.get(i));
        }

        System.out.println();

        System.out.println("Visited States Count: " + VisitedList.size());
        System.out.println("Node Depth: " + solution.size());
    }
}

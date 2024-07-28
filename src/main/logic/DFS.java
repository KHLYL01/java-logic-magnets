package main.logic;

import main.action.Actions;
import main.model.BoardModel;
import main.model.State;

import java.util.Stack;

public class DFS extends BaseLogic{
    private Stack<State> stackStates = new Stack<State>();

    public DFS(BoardModel board) {
        System.out.println("Solving ...");

        Actions.printBoard(board);

        State solve = SolveDFS(new State(null, board));

        this.checkSolve(solve);
    }


    // Algorithms must be like that

    State SolveDFS(State state) {
        stackStates.push(state);

        while (stackStates.size() > 0) {
            State node = stackStates.pop();

            VisitedList.add(node);

            if (Actions.isFinish(node.getBoardModel())) {
                return node;
            }
            for (BoardModel child : Actions.GetNextStates(node.getBoardModel())) {
                if (!isVisited(child)) {
                    stackStates.push(new State(node, child));
                }
            }
        }
        return null;
    }
}

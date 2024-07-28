package main.logic;

import main.action.Actions;
import main.model.BoardModel;
import main.model.State;

import java.util.PriorityQueue;
import java.util.Queue;

public class UCS extends BaseLogic{

    private Queue<State> queueStates = new PriorityQueue<>();

    public UCS(BoardModel board) {

        Actions.printBoard(board);

        System.out.println("loading ...");

        State solve = SolveUCS(new State(null, board,0));

        this.checkSolve(solve);
    }

    State SolveUCS(State state) {
        queueStates.add(state);

        if (Actions.isFinish(state.getBoardModel())){
            return state;
        }
        while (queueStates.size() > 0) {

            State node = queueStates.poll();
            VisitedList.add(node);

            if (Actions.isFinish(node.getBoardModel())){
                return node;
            }

            for (BoardModel child : Actions.GetNextStates(node.getBoardModel())) {
                if (!isVisited(child)) {
                    // cost for REPELLENT is 1
                    // cost for ATTRACT is 2
                    State state1 = new State(node, child,node.getCost() + child.getCost());
                        queueStates.add(state1);
                }
            }
        }
        return null;
    }
}

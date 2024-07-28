package main.logic;

import main.action.Actions;
import main.model.BoardModel;
import main.model.State;

import java.util.LinkedList;
import java.util.Queue;

public class BFS extends BaseLogic {

    private Queue<State> queueStates = new LinkedList<>();

    public BFS(BoardModel board) {

        Actions.printBoard(board);

        System.out.println("loading ...");

        State solve = SolveBFS(new State(null, board));

        this.checkSolve(solve);
    }

//    int i = 0;
    State SolveBFS(State state) {
        queueStates.add(state);


//        System.out.println(i++);

        if (Actions.isFinish(state.getBoardModel())){
            return state;
        }

        while (queueStates.size() > 0) {
            State node = queueStates.poll();
//            System.out.println(i++);
            VisitedList.add(node);

            if (Actions.isFinish(node.getBoardModel())){
                return node;
            }

            for (BoardModel child : Actions.GetNextStates(node.getBoardModel())) {
                if (!isVisited(child)) {
                    State state1 = new State(node, child);
//                    if (!Actions.isFinish(child)){
                        queueStates.add(state1);
//                    }
//                    else {
//                        return state1;
//                    }
                }
            }
        }
        return null;
    }
}


/*

State SolveBFS(State state)
    {
        queueStates.add(state);

        if (Actions.isFinish(state.getBoardModel()))
            return state;

        while (queueStates.size() > 0)
        {
            State node = queueStates.poll();

            VisitedList.add(node);

            for (BoardModel child : Actions.GetNextStates(node.getBoardModel())) {
                if (!isVisited(child)) {

                    State state1 = new State(node, child);

                    if (!Actions.isFinish(child))
                        queueStates.add(state1);
                    else
                        return state1;
                }
            }
        }
        return null;
    }
}

 */

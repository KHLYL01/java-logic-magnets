package main.logic;

import main.action.Actions;
import main.model.BoardModel;
import main.model.State;

import java.util.PriorityQueue;
import java.util.Queue;

public class HillClimbing extends BaseLogic {


    public HillClimbing(BoardModel board) {

        Actions.printBoard(board);

        System.out.println("loading ...");

        State solve = SolveHillClimbing(new State(null, board, 0, 0));

        this.checkSolve(solve);
    }

    State SolveHillClimbing(State state) {

        if (Actions.isFinish(state.getBoardModel())) {
            return state;
        }
        while (true) {
            int h = state.getHeuristic();

            BoardModel game = null;


            for (BoardModel child : Actions.GetNextStates(state.getBoardModel())) {
                if (child.getHeuristic() > h) {
                    h = child.getHeuristic();
                    game = child;
                }
            }
            State state1 = new State(state, game, 0, h);
            Actions.printBoard(state1.getBoardModel());
            if (state1.getBoardModel() == null){
                return null;
            }

            if (Actions.isFinish(state1.getBoardModel())) {
                return state1;
            }
            state = state1;
        }
    }
}

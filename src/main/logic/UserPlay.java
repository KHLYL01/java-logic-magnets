package main.logic;

import main.action.Actions;
import main.model.BoardModel;
import main.model.PositionModel;

import java.util.ArrayList;
import java.util.Scanner;

public class UserPlay {

    Scanner in = new Scanner(System.in);
    private BoardModel board;
    private int x;
    private int y;
    private PositionModel current;
    private PositionModel target;

    public UserPlay(BoardModel board) {
        this.board = board;
        startPlaying();
    }

    private void startPlaying() {
        int i = 0 ;

        while (true) {
            // Print Board
            Actions.printBoard(board);

            // Game Finish
            if (Actions.isFinish(board)) {
                System.out.println("================GAME FINISH================");
                return;
            }

            // Game over
            if (i == board.getNumberOfMove()) {
                System.out.println("================GAME OVER================");
                System.exit(0);
            }

            System.out.println("Number of Move: "+ (board.getNumberOfMove() - i));
            // Check Cell Can Be Move
            ArrayList<PositionModel> positions = Actions.checkCurrentCell(board);
            System.out.println(positions);

            System.out.println("Enter position (x,y):");
            System.out.print("Enter x:");
            x = in.nextInt();
            System.out.print("Enter y:");
            y = in.nextInt();
            current = new PositionModel(x, y);
            boolean isMove = false;
            for (PositionModel position : positions) {
                if (position.equal(current)) {
                    isMove = true;
                    break;
                }
            }
            if (!isMove){
                System.out.println("==========================================================");
                System.out.println("invalid position input please enter one of this positions:\n"+positions);
                continue;
            }
            // check Possible Move
            System.out.println(Actions.checkTargetMoves(board));

            System.out.println("Enter position (x,y):");
            System.out.print("Enter x:");
            x = in.nextInt();
            System.out.print("Enter y:");
            y = in.nextInt();

            target = new PositionModel(x, y);

            board = Actions.Move(board, current, target);
            i++;
        }
    }
}

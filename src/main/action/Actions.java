package main.action;

import main.model.BoardModel;
import main.model.CellModel;
import main.model.CellType;
import main.model.PositionModel;

import java.util.ArrayList;

public class Actions {


    public static void printBoard(BoardModel board) {
        System.out.println(board);
    }

    public static boolean isFinish(BoardModel board) {
        for (CellModel[] cell : board.getCells()) {
            for (CellModel cellModel : cell) {
                switch (cellModel.getCellType()) {
                    case STONE, SOLVE, REPELLENT, ATTRACT -> {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static ArrayList<PositionModel> checkTargetMoves(BoardModel board) {
        ArrayList<PositionModel> targetMove = new ArrayList<>();
        for (CellModel[] cell : board.getCells()) {
            for (CellModel cellModel : cell) {
                switch (cellModel.getCellType()) {
                    case NONE, SOLVE -> targetMove.add(cellModel.getPositionModel());
                }
            }
        }
        return targetMove;
    }

    public static ArrayList<PositionModel> checkCurrentCell(BoardModel board) {
        ArrayList<PositionModel> currentCell = new ArrayList<>();
        for (CellModel[] cell : board.getCells()) {
            for (CellModel cellModel : cell) {
                switch (cellModel.getCellType()) {
                    case ATTRACT, REPELLENT, SOLVE_ATTRACT, SOLVE_REPELLENT -> currentCell.add(cellModel.getPositionModel());
                }
            }
        }
        return currentCell;
    }

    public static ArrayList<BoardModel> GetNextStates(BoardModel boardModel) {
        ArrayList<BoardModel> nextStates = new ArrayList<>(); // Store States

        ArrayList<PositionModel> currentCell = checkCurrentCell(boardModel); // Get All Cell able positions

        ArrayList<PositionModel> targetMoves = checkTargetMoves(boardModel); // Get All Move able positions

        // Move to all positions
        for (PositionModel current : currentCell) {
            for (PositionModel target : targetMoves) {
                nextStates.add(Move(boardModel, current, target));
            }
        }
        return nextStates;
    }

    public static BoardModel Move(BoardModel board, PositionModel current, PositionModel target) {

        BoardModel copyBoard = new BoardModel(board);
        CellModel[][] cells = copyBoard.getCells();

        CellModel currentCell = null;
        CellModel targetCell = null;

        for (CellModel[] cellRow : cells) {
            for (CellModel cell : cellRow) {
                if (cell.getPositionModel().equal(current)) {
                    currentCell = cell;
                }
                if (cell.getPositionModel().equal(target)) {
                    targetCell = cell;
                }
            }
        }
        // change type of target cell
        switch (targetCell.getCellType()) {
            case SOLVE -> {
                switch (currentCell.getCellType()) {
                    case ATTRACT, SOLVE_ATTRACT -> targetCell.setCellType(CellType.SOLVE_ATTRACT);
                    case REPELLENT, SOLVE_REPELLENT -> targetCell.setCellType(CellType.SOLVE_REPELLENT);
                }
            }
            case NONE -> {
                switch (currentCell.getCellType()) {
                    case SOLVE_ATTRACT -> targetCell.setCellType(CellType.ATTRACT);
                    case SOLVE_REPELLENT -> targetCell.setCellType(CellType.REPELLENT);
                    default -> targetCell.setCellType(currentCell.getCellType());
                }
            }
        }

        // add cost to copy board
        switch (currentCell.getCellType()) {
            case REPELLENT, SOLVE_REPELLENT -> copyBoard.setCost(1);
            case ATTRACT, SOLVE_ATTRACT -> copyBoard.setCost(2);
        }

        // change type of current cell
        switch (currentCell.getCellType()) {
            case REPELLENT, ATTRACT -> currentCell.setCellType(CellType.NONE);
            case SOLVE_REPELLENT, SOLVE_ATTRACT -> currentCell.setCellType(CellType.SOLVE);
        }

        // get the affected cell
        ArrayList<CellModel> rowCellRight = new ArrayList<>();
        ArrayList<CellModel> rowCellLeft = new ArrayList<>();
        ArrayList<CellModel> columnCellDown = new ArrayList<>();
        ArrayList<CellModel> columnCellUp = new ArrayList<>();

        for (CellModel[] cellRow : cells) {
            for (CellModel cell : cellRow) {
                if (cell.getPositionModel().equal(target)) {
                    continue;
                }
                if (cell.getPositionModel().equalX(target)) {
                    if (cell.getPositionModel().getY() > target.getY()) {
                        rowCellRight.add(cell);
                    } else {
                        rowCellLeft.add(cell);
                    }
                }
                if (cell.getPositionModel().equalY(target)) {
                    if (cell.getPositionModel().getX() > target.getX()) {
                        columnCellDown.add(cell);
                    } else {
                        columnCellUp.add(cell);
                    }
                }
            }
        }

        // heuristics
        int heuristic1 = 0;
        int heuristic2 = 0;

                // change type of affected cell
        switch (targetCell.getCellType()) {
            case REPELLENT, SOLVE_REPELLENT -> {
                heuristic1 = MoveOnAction.leftOrUpMoveRepellent(rowCellLeft) + MoveOnAction.rightOrDownMoveRepellent(rowCellRight) + MoveOnAction.leftOrUpMoveRepellent(columnCellUp) + MoveOnAction.rightOrDownMoveRepellent(columnCellDown);
            }
            case ATTRACT, SOLVE_ATTRACT -> {
                heuristic2 = MoveOnAction.leftOrUpMoveAttract(rowCellLeft)+
                MoveOnAction.rightOrDownMoveAttract(rowCellRight)+
                MoveOnAction.leftOrUpMoveAttract(columnCellUp)+
                MoveOnAction.rightOrDownMoveAttract(columnCellDown);
            }
        }
        copyBoard.setHeuristic(heuristic1+ heuristic2);
        return copyBoard;
    }

    public static boolean Equals(BoardModel boardModel1, BoardModel boardModel2) {
        return boardModel1.equals(boardModel2);
    }
}

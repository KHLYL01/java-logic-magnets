package main.model;

import java.util.Arrays;

public class BoardModel {

    private int row;
    private int column;
    public CellModel[][] cells;
    private int numberOfMove;
    private int cost;
    private int heuristic;

    public BoardModel(int numberOfMove,int row, int column, CellModel[][] cells) {
        this.row = row;
        this.column = column;
        this.cells = cells;
        this.numberOfMove = numberOfMove;
    }

    public BoardModel(BoardModel board) {
        this.row = board.row;
        this.column = board.column;
        this.numberOfMove = board.numberOfMove;
        this.cells = new CellModel[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                try {
                    this.cells[i][j] = (CellModel) board.cells[i][j].clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public CellModel[][] getCells() {
        return cells;
    }

    public void setCells(CellModel[][] cells) {
        this.cells = cells;
    }

    public int getNumberOfMove() {
        return numberOfMove;
    }

    public void setNumberOfMove(int numberOfMove) {
        this.numberOfMove = numberOfMove;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public boolean equals(BoardModel boardModel) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (cells[i][j].getCellType() != boardModel.cells[i][j].getCellType()) {
                    return false;
                }
            }
        }
        return true;
    }

    public String printPositions() {
        String output = "";
        for (CellModel[] cellRow : cells) {
            for (CellModel cell : cellRow) {
                output += cell.getPositionModel();
            }
            output += "\n";
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "";
        for (CellModel[] cell : cells) {
            for (CellModel cellModel : cell) {
                output += cellModel + " ";
            }
            output += '\n';
        }
        return output;
    }
}

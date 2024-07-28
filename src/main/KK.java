package main;

import java.util.*;

public class KK {

    public static void main(String[] args) throws CloneNotSupportedException {

        while (true) {
            Scanner s = new Scanner(System.in);
            System.out.print("chose level from 1 to 16: ");
            int i = s.nextInt();
            if(i > 16){
                System.out.println("invalid input");
                continue;
            }
            i -= 1;
            System.out.println("1.User Play\n" +
                    "2.BFS\n" +
                    "3.UCS\n" +
                    "4.Hill Climbing\n" +
                    "5.A*\n");
            int j = s.nextInt();

            long current = System.currentTimeMillis();
            switch (j) {
                case 1 -> new UserPlay(GameLevels.levels.get(i));
                case 2 -> new BFS(GameLevels.levels.get(i));
                case 3 -> new UCS(GameLevels.levels.get(i));
                case 4 -> new HillClimbing(GameLevels.levels.get(i));
                case 5 -> new A8(GameLevels.levels.get(i));
                default -> {
                    System.out.println("invalid input");
                    continue;
                }
            }
            long now = System.currentTimeMillis();
            System.out.println("total time: " + (double) (now - current) / 1000 + "s\n");
        }
    }


}

// Position Model
class PositionModel implements Cloneable{
    private int x;
    private int y;

    public PositionModel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PositionModel(main.model.PositionModel position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public boolean equal(main.model.PositionModel position){
        return position.getX() == this.x && position.getY() == this.y;
    }

    public boolean equalX(main.model.PositionModel position){
        return position.getX() == this.x;
    }

    public boolean equalY(main.model.PositionModel position){
        return position.getY() == this.y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "(" + x +","+ y + ")";
    }
}

// enum type
enum CellType {
    NONE,BL0CK,STONE,SOLVE,ATTRACT,REPELLENT,SOLVE_STONE,SOLVE_ATTRACT,SOLVE_REPELLENT
}

// cell Model
class CellModel implements Cloneable{

    private main.model.CellType cellType;
    private main.model.PositionModel positionModel;

    public CellModel(main.model.CellType cellType, main.model.PositionModel positionModel) {
        this.cellType = cellType;
        this.positionModel = positionModel;
    }

    public main.model.CellType getCellType() {
        return cellType;
    }

    public void setCellType(main.model.CellType cellType) {
        this.cellType = cellType;
    }

    public main.model.PositionModel getPositionModel() {
        return positionModel;
    }

    public void setPositionModel(main.model.PositionModel positionModel) {
        this.positionModel = positionModel;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        main.model.CellModel cellClone = (main.model.CellModel) super.clone();
        cellClone.setCellType(cellType);
        cellClone.setPositionModel((main.model.PositionModel) cellClone.getPositionModel().clone());
        return cellClone;
    }

    @Override
    public String toString() {
        return switch (getCellType()){
            case NONE -> "_ ";
            case BL0CK -> "  ";
            case STONE -> "T ";
            case SOLVE -> "S ";
            case ATTRACT -> "A ";
            case REPELLENT -> "R ";
            case SOLVE_STONE -> "ST";
            case SOLVE_ATTRACT -> "SA";
            case SOLVE_REPELLENT -> "SR";
        };
    }
}

// game board model
class BoardModel {

    private int row;
    private int column;
    public main.model.CellModel[][] cells;
    private int numberOfMove;
    private int cost;
    private int heuristic;

    public BoardModel(int numberOfMove,int row, int column, main.model.CellModel[][] cells) {
        this.row = row;
        this.column = column;
        this.cells = cells;
        this.numberOfMove = numberOfMove;
    }

    public BoardModel(main.model.BoardModel board) {
        this.row = board.getRow();
        this.column = board.getColumn();
        this.numberOfMove = board.getNumberOfMove();
        this.cells = new main.model.CellModel[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                try {
                    this.cells[i][j] = (main.model.CellModel) board.cells[i][j].clone();
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

    public main.model.CellModel[][] getCells() {
        return cells;
    }

    public void setCells(main.model.CellModel[][] cells) {
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

    public boolean equals(main.model.BoardModel boardModel) {
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
        for (main.model.CellModel[] cellRow : cells) {
            for (main.model.CellModel cell : cellRow) {
                output += cell.getPositionModel();
            }
            output += "\n";
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "";
        for (main.model.CellModel[] cell : cells) {
            for (main.model.CellModel cellModel : cell) {
                output += cellModel + " ";
            }
            output += '\n';
        }
        return output;
    }
}

// State Class
class State implements Comparable<main.model.State>{
    private main.model.State parent;
    private main.model.BoardModel boardModel;
    private int cost;
    private int heuristic;

    public State(main.model.State parent, main.model.BoardModel boardModel)
    {
        this.parent = parent;
        this.boardModel = boardModel;
    }

    public State(main.model.State parent, main.model.BoardModel boardModel, int cost) {
        this.parent = parent;
        this.boardModel = boardModel;
        this.cost = cost;
    }

    public State(main.model.State parent, main.model.BoardModel boardModel, int cost , int heuristic) {
        this.parent = parent;
        this.boardModel = boardModel;
        this.cost = cost;
        this.heuristic = heuristic;
    }


    public boolean hasPrevious()
    {
        return this.parent != null;
    }

    public main.model.State getParent() {
        return parent;
    }

    public void setParent(main.model.State parent) {
        this.parent = parent;
    }

    public main.model.BoardModel getBoardModel() {
        return boardModel;
    }

    public void setBoardModel(main.model.BoardModel boardModel) {
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
    public int compareTo(main.model.State o) {
        return this.cost - o.getCost();
    }

    @Override
    public String toString() {
        return "State{" +
//                "parent= " + parent +
                "cost=" + cost +
                '}';
    }
}

// Game Level
class GameLevels {

    public static ArrayList<main.model.BoardModel> levels = new ArrayList<>();

    static {
        main.model.CellModel[][] level1= {
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,3)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,3)),},
                {new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,3)),}
        };
        main.model.BoardModel boardModel = new main.model.BoardModel(2,3,4,level1);
        levels.add(boardModel);

        main.model.CellModel[][] level2= {
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,4)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,4)),},
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(2,3)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,4)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(3,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,4)),},
                {new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(4,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(4,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,4)),},
        };
        levels.add(new main.model.BoardModel(2,5,5,level2));

        main.model.CellModel[][] level3= {
                {new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,3)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,3)),},
                {new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,3)),}
        };
        levels.add(new main.model.BoardModel(2,3,4,level3));

        main.model.CellModel[][] level4= {
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,2)),},
                {new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,2)),},
                {new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,2)),},
                {new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,2)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(4,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,2)),}
        };
        levels.add(new main.model.BoardModel(2,5,3,level4));

        main.model.CellModel[][] level5= {
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,2)),},
                {new main.model.CellModel(main.model.CellType.SOLVE_STONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.SOLVE_STONE,new main.model.PositionModel(1,2)),},
                {new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(2,2)),},
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,2)),},
        };
        levels.add(new main.model.BoardModel(2,4,3,level5));

        main.model.CellModel[][] level6= {
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,4)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,4)),},
                {new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,4)),}
        };
        levels.add(new main.model.BoardModel(2,3,5,level6));

        main.model.CellModel[][] level7= {
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,3)),},
                {new main.model.CellModel(main.model.CellType.SOLVE_STONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,3)),},
                {new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,3)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.SOLVE_STONE,new main.model.PositionModel(3,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,3)),},
                {new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(4,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(4,1)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(4,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(4,3)),},
        };
        levels.add(new main.model.BoardModel(2,5,4,level7));

        main.model.CellModel[][] level8= {
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,3)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,3)),},
                {new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,3)),}
        };
        levels.add(new main.model.BoardModel(2,3,4,level8));

        main.model.CellModel[][] level9= {
                {new main.model.CellModel(main.model.CellType.REPELLENT, new main.model.PositionModel(0, 0)), new main.model.CellModel(main.model.CellType.SOLVE, new main.model.PositionModel(0, 1)), new main.model.CellModel(main.model.CellType.NONE, new main.model.PositionModel(0, 2)), new main.model.CellModel(main.model.CellType.SOLVE_STONE, new main.model.PositionModel(0, 3)), new main.model.CellModel(main.model.CellType.NONE, new main.model.PositionModel(0, 4)), new main.model.CellModel(main.model.CellType.STONE, new main.model.PositionModel(0, 5)),new main.model.CellModel(main.model.CellType.SOLVE, new main.model.PositionModel(0, 6)),},
        };
        levels.add(new main.model.BoardModel(2,1,7,level9));

        main.model.CellModel[][] level10= {
                {new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,3)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,3)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(2,3)),},
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(3,3)),},
        };
        levels.add(new main.model.BoardModel(2,4,4,level10));

        main.model.CellModel[][] level11= {
                {new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,3)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,4)),},
                {new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.ATTRACT,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,3)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,4)),},
        };
        levels.add(new main.model.BoardModel(2,2,5,level11));

        main.model.CellModel[][] level12= {
                {new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(0,3)),},
                {new main.model.CellModel(main.model.CellType.SOLVE_STONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,3)),},
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,3)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.ATTRACT,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,3)),},
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(4,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(4,2)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(4,3)),},
        };
        levels.add(new main.model.BoardModel(2,5,4,level12));

        main.model.CellModel[][] level13= {
                {new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,3)),new main.model.CellModel(main.model.CellType.SOLVE_STONE,new main.model.PositionModel(0,4)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,5)),},
                {new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,3)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,4)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,5)),},
                {new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.ATTRACT,new main.model.PositionModel(2,3)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(2,4)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(2,5)),}
        };
        levels.add(new main.model.BoardModel(2,3,6,level13));

        main.model.CellModel[][] level14= {
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,3)),},
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,3)),},
                {new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,3)),},
                {new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,2)),new main.model.CellModel(main.model.CellType.ATTRACT,new main.model.PositionModel(3,3)),},
        };
        levels.add(new main.model.BoardModel(2,4,4,level14));

        main.model.CellModel[][] level15= {
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,4)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,3)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,4)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.ATTRACT,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,3)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,4)),}
        };
        levels.add(new main.model.BoardModel(2,3,5,level15));

        main.model.CellModel[][] level16= {
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,3)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,4)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,4)),},
                {new main.model.CellModel(main.model.CellType.ATTRACT,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,3)),new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(2,4)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(3,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,4)),},
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(4,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(4,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,4)),},
        };
        levels.add(new main.model.BoardModel(2,5,5,level16));

        main.model.CellModel[][] level17= {
                {new main.model.CellModel(main.model.CellType.ATTRACT,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,3)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,3)),},
                {new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,3)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,2)),new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(3,3)),},
        };
        levels.add(new main.model.BoardModel(2,4,4,level17));

        //==============================================================

        main.model.CellModel[][] myLevel1= {
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,2)),},
                {new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(1,2)),},
                {new main.model.CellModel(main.model.CellType.SOLVE_STONE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.SOLVE_STONE,new main.model.PositionModel(2,2)),},
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(3,2)),},
        };
        levels.add(new main.model.BoardModel(2,4,3,myLevel1));

        main.model.CellModel[][] myLevel2= {
                {new main.model.CellModel(main.model.CellType.SOLVE, new main.model.PositionModel(0, 0)), new main.model.CellModel(main.model.CellType.SOLVE_STONE, new main.model.PositionModel(0, 1)), new main.model.CellModel(main.model.CellType.NONE, new main.model.PositionModel(0, 2)), new main.model.CellModel(main.model.CellType.STONE, new main.model.PositionModel(0, 3)), new main.model.CellModel(main.model.CellType.NONE, new main.model.PositionModel(0, 4)), new main.model.CellModel(main.model.CellType.SOLVE_REPELLENT, new main.model.PositionModel(0, 5)),new main.model.CellModel(main.model.CellType.STONE, new main.model.PositionModel(0, 6)),new main.model.CellModel(main.model.CellType.NONE, new main.model.PositionModel(0, 7)),new main.model.CellModel(main.model.CellType.SOLVE_STONE, new main.model.PositionModel(0, 8)),new main.model.CellModel(main.model.CellType.SOLVE, new main.model.PositionModel(0, 9)),},
        };
        levels.add(new main.model.BoardModel(2,1,10,myLevel2));

        main.model.CellModel[][] myLevel3= {
                {new main.model.CellModel(main.model.CellType.STONE, new main.model.PositionModel(0, 0)), new main.model.CellModel(main.model.CellType.SOLVE, new main.model.PositionModel(0, 1)), new main.model.CellModel(main.model.CellType.STONE, new main.model.PositionModel(0, 2)), new main.model.CellModel(main.model.CellType.BL0CK, new main.model.PositionModel(0, 3)), new main.model.CellModel(main.model.CellType.ATTRACT, new main.model.PositionModel(0, 4)), new main.model.CellModel(main.model.CellType.NONE, new main.model.PositionModel(0, 5)),},
        };
        levels.add(new main.model.BoardModel(2,1,6,myLevel3));

        main.model.CellModel[][] myLevel4= {
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,2)),},
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,2)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(2,2)),},
                {new main.model.CellModel(main.model.CellType.ATTRACT,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,2)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,0)),new main.model.CellModel(main.model.CellType.SOLVE_STONE,new main.model.PositionModel(4,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,2)),},
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(5,0)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(5,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(5,2)),},
        };
        levels.add(new main.model.BoardModel(2,6,3,myLevel4));

        main.model.CellModel[][] myLevel5= {
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(0,2)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(0,3)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(0,4)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(1,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(1,3)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,4)),},
                {new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(2,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(2,4)),},
                {new main.model.CellModel(main.model.CellType.ATTRACT,new main.model.PositionModel(3,0)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,1)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(3,2)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(3,3)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(3,4)),},
                {new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(4,0)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(4,1)),new main.model.CellModel(main.model.CellType.STONE,new main.model.PositionModel(4,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(4,4)),},
                {new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(5,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(5,1)),new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(5,2)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(5,3)),new main.model.CellModel(main.model.CellType.NONE,new main.model.PositionModel(5,4)),},
        };
        levels.add(new main.model.BoardModel(2,6,5,myLevel5));

        main.model.CellModel[][] myLevel6= {
                {new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,0)),new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(0,1)),new main.model.CellModel(main.model.CellType.SOLVE,new main.model.PositionModel(0,2)),},
                {new main.model.CellModel(main.model.CellType.REPELLENT,new main.model.PositionModel(1,0)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,1)),new main.model.CellModel(main.model.CellType.BL0CK,new main.model.PositionModel(1,2)),},
        };
        levels.add(new main.model.BoardModel(2,2,3,myLevel6));
    }
}

// actions
class Actions {


    public static void printBoard(main.model.BoardModel board) {
        System.out.println(board);
    }

    public static boolean isFinish(main.model.BoardModel board) {
        for (main.model.CellModel[] cell : board.getCells()) {
            for (main.model.CellModel cellModel : cell) {
                switch (cellModel.getCellType()) {
                    case STONE, SOLVE, REPELLENT, ATTRACT -> {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static ArrayList<main.model.PositionModel> checkTargetMoves(main.model.BoardModel board) {
        ArrayList<main.model.PositionModel> targetMove = new ArrayList<>();
        for (main.model.CellModel[] cell : board.getCells()) {
            for (main.model.CellModel cellModel : cell) {
                switch (cellModel.getCellType()) {
                    case NONE, SOLVE -> targetMove.add(cellModel.getPositionModel());
                }
            }
        }
        return targetMove;
    }

    public static ArrayList<main.model.PositionModel> checkCurrentCell(main.model.BoardModel board) {
        ArrayList<main.model.PositionModel> currentCell = new ArrayList<>();
        for (main.model.CellModel[] cell : board.getCells()) {
            for (main.model.CellModel cellModel : cell) {
                switch (cellModel.getCellType()) {
                    case ATTRACT, REPELLENT, SOLVE_ATTRACT, SOLVE_REPELLENT -> currentCell.add(cellModel.getPositionModel());
                }
            }
        }
        return currentCell;
    }

    public static ArrayList<main.model.BoardModel> GetNextStates(main.model.BoardModel boardModel) {
        ArrayList<main.model.BoardModel> nextStates = new ArrayList<>(); // Store States

        ArrayList<main.model.PositionModel> currentCell = checkCurrentCell(boardModel); // Get All Cell able positions

        ArrayList<main.model.PositionModel> targetMoves = checkTargetMoves(boardModel); // Get All Move able positions

        // Move to all positions
        for (main.model.PositionModel current : currentCell) {
            for (main.model.PositionModel target : targetMoves) {
                nextStates.add(Move(boardModel, current, target));
            }
        }
        return nextStates;
    }

    public static main.model.BoardModel Move(main.model.BoardModel board, main.model.PositionModel current, main.model.PositionModel target) {

        main.model.BoardModel copyBoard = new main.model.BoardModel(board);
        main.model.CellModel[][] cells = copyBoard.getCells();

        main.model.CellModel currentCell = null;
        main.model.CellModel targetCell = null;

        for (main.model.CellModel[] cellRow : cells) {
            for (main.model.CellModel cell : cellRow) {
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
                    case ATTRACT, SOLVE_ATTRACT -> targetCell.setCellType(main.model.CellType.SOLVE_ATTRACT);
                    case REPELLENT, SOLVE_REPELLENT -> targetCell.setCellType(main.model.CellType.SOLVE_REPELLENT);
                }
            }
            case NONE -> {
                switch (currentCell.getCellType()) {
                    case SOLVE_ATTRACT -> targetCell.setCellType(main.model.CellType.ATTRACT);
                    case SOLVE_REPELLENT -> targetCell.setCellType(main.model.CellType.REPELLENT);
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
            case REPELLENT, ATTRACT -> currentCell.setCellType(main.model.CellType.NONE);
            case SOLVE_REPELLENT, SOLVE_ATTRACT -> currentCell.setCellType(main.model.CellType.SOLVE);
        }

        // get the affected cell
        ArrayList<main.model.CellModel> rowCellRight = new ArrayList<>();
        ArrayList<main.model.CellModel> rowCellLeft = new ArrayList<>();
        ArrayList<main.model.CellModel> columnCellDown = new ArrayList<>();
        ArrayList<main.model.CellModel> columnCellUp = new ArrayList<>();

        for (main.model.CellModel[] cellRow : cells) {
            for (main.model.CellModel cell : cellRow) {
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

    public static boolean Equals(main.model.BoardModel boardModel1, main.model.BoardModel boardModel2) {
        return boardModel1.equals(boardModel2);
    }
}

// Move on Actions to do move

class MoveOnAction {

    public static ArrayList<main.model.CellModel> affectedCell;
    public static main.model.CellModel cell;

    public static int leftOrUpMoveRepellent(ArrayList<main.model.CellModel> cells) {
        int heuristic = 0;
        affectedCell = new ArrayList<>();
        stop:
        for (int i = cells.size() - 1; i >= 0; i--) {
            affectedCell.add(cells.get(i));
            switch (cells.get(i).getCellType()) {
                case STONE, SOLVE_STONE, ATTRACT, SOLVE_ATTRACT, REPELLENT, SOLVE_REPELLENT -> {
                    heuristic++;
                    if (i == 0) {
                        break stop;
                    }
                    switch (cells.get(i - 1).getCellType()) {
                        case NONE, SOLVE, BL0CK -> {
                            affectedCell.add(cells.get(i - 1));
                            break stop;
                        }
                    }
                }
            }
        }
        Collections.reverse(affectedCell);
        for (int i = 1; i < affectedCell.size(); i++) {
            main.model.CellModel cell = affectedCell.get(i);
            switch (cell.getCellType()) {
                case STONE, SOLVE_STONE, ATTRACT, SOLVE_ATTRACT, REPELLENT, SOLVE_REPELLENT -> {
                    main.model.CellModel neighborCell = affectedCell.get(i - 1);
                    if (neighborCell.getCellType() == main.model.CellType.BL0CK) {
                        continue;
                    }
                    // change type of neighbor cell
                    doEffect(cell,neighborCell);
                }
            }
        }
        return heuristic;
    }


    public static int rightOrDownMoveRepellent(ArrayList<main.model.CellModel> cells) {
        int heuristic = 0;
        affectedCell = new ArrayList<>();
        stop:
        for (int i = 0; i < cells.size(); i++) {
            affectedCell.add(cells.get(i));
            switch (cells.get(i).getCellType()) {
                case STONE, SOLVE_STONE, ATTRACT, SOLVE_ATTRACT, REPELLENT, SOLVE_REPELLENT -> {
                    heuristic++;
                    if (i == cells.size()-1) {
                        break stop;
                    }
                    switch (cells.get(i + 1).getCellType()) {
                        case NONE, SOLVE, BL0CK -> {
                            affectedCell.add(cells.get(i + 1));
                            break stop;
                        }
                    }
                }
            }
        }
        // Right x
        for (int i = affectedCell.size() - 2; i >= 0; i--) {
            main.model.CellModel cell = affectedCell.get(i);
            switch (cell.getCellType()) {
                case STONE, SOLVE_STONE, ATTRACT, SOLVE_ATTRACT, REPELLENT, SOLVE_REPELLENT ->{
                    main.model.CellModel neighborCell = affectedCell.get(i + 1);
                    if (neighborCell.getCellType() == main.model.CellType.BL0CK) {
                        continue;
                    }
                    doEffect(cell,neighborCell);
                }
            }
        }
        return heuristic;
    }

    public static int rightOrDownMoveAttract(ArrayList<main.model.CellModel> cells) {
        int heuristic = 0 ;
        for (int i = 1; i < cells.size(); i++) {
            cell = cells.get(i);
            switch (cell.getCellType()) {
                case STONE, SOLVE_STONE, ATTRACT, SOLVE_ATTRACT, REPELLENT, SOLVE_REPELLENT -> {
                    heuristic++;
                    main.model.CellModel neighborCell = cells.get(i - 1);
                    if (neighborCell.getCellType() == main.model.CellType.BL0CK) {
                        continue;
                    }
                    doEffect(cell,neighborCell);
                }
            }
        }
        return heuristic;
    }

    public static int leftOrUpMoveAttract(ArrayList<main.model.CellModel> cells) {
        int heuristic = 0;
        for (int i = cells.size() - 2; i >= 0; i--) {
            cell = cells.get(i);
            switch (cell.getCellType()) {
                case STONE, SOLVE_STONE, ATTRACT, SOLVE_ATTRACT, REPELLENT, SOLVE_REPELLENT -> {
                    heuristic++;
                    main.model.CellModel neighborCell = cells.get(i + 1);
                    if (neighborCell.getCellType() == main.model.CellType.BL0CK) {
                        continue;
                    }
                    doEffect(cell,neighborCell);
                }
            }
        }
        return heuristic;
    }

    // effect method
    private static void doEffect(main.model.CellModel cell , main.model.CellModel neighborCell) {
        // change type of neighbor cell
        switch (neighborCell.getCellType()) {
            case SOLVE -> {
                switch (cell.getCellType()) {
                    case STONE, SOLVE_STONE -> neighborCell.setCellType(main.model.CellType.SOLVE_STONE);
                    case REPELLENT, SOLVE_REPELLENT -> neighborCell.setCellType(main.model.CellType.SOLVE_REPELLENT);
                    case ATTRACT, SOLVE_ATTRACT -> neighborCell.setCellType(main.model.CellType.SOLVE_ATTRACT);
                }
            }
            case NONE -> {
                switch (cell.getCellType()) {
                    case STONE,REPELLENT,ATTRACT -> neighborCell.setCellType(cell.getCellType());
                    case SOLVE_STONE -> neighborCell.setCellType(main.model.CellType.STONE);
                    case SOLVE_REPELLENT -> neighborCell.setCellType(main.model.CellType.REPELLENT);
                    case SOLVE_ATTRACT -> neighborCell.setCellType(main.model.CellType.ATTRACT);
                }
            }
        }
        // change type of current cell
        switch (cell.getCellType()) {
            case STONE,REPELLENT,ATTRACT -> cell.setCellType(main.model.CellType.NONE);
            case SOLVE_STONE,SOLVE_REPELLENT,SOLVE_ATTRACT -> cell.setCellType(main.model.CellType.SOLVE);
        }
    }
}
// User Play
class UserPlay {

    Scanner in = new Scanner(System.in);
    private main.model.BoardModel board;
    private int x;
    private int y;
    private main.model.PositionModel current;
    private main.model.PositionModel target;

    public UserPlay(main.model.BoardModel board) {
        this.board = board;
        startPlaying();
    }

    private void startPlaying() {
        int i = 0 ;

        while (true) {
            // Print Board
            main.action.Actions.printBoard(board);

            // Game Finish
            if (main.action.Actions.isFinish(board)) {
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
            ArrayList<main.model.PositionModel> positions = main.action.Actions.checkCurrentCell(board);
            System.out.println(positions);

            System.out.println("Enter position (x,y):");
            System.out.print("Enter x:");
            x = in.nextInt();
            System.out.print("Enter y:");
            y = in.nextInt();
            current = new main.model.PositionModel(x, y);
            boolean isMove = false;
            for (main.model.PositionModel position : positions) {
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
            System.out.println(main.action.Actions.checkTargetMoves(board));

            System.out.println("Enter position (x,y):");
            System.out.print("Enter x:");
            x = in.nextInt();
            System.out.print("Enter y:");
            y = in.nextInt();

            target = new main.model.PositionModel(x, y);

            board = main.action.Actions.Move(board, current, target);
            i++;
        }
    }
}


// Logic
// Base Logic contains visited node list and other method
class BaseLogic {
    ArrayList<main.model.State> VisitedList = new ArrayList<>();

    ArrayList<main.model.BoardModel> solution = new ArrayList<>();

    public boolean isVisited(main.model.BoardModel boardModel)
    {
        for (main.model.State state : VisitedList)
        {
            if (main.action.Actions.Equals(state.getBoardModel(), boardModel))
                return true;
        }
        return false;
    }

    protected void checkSolve(main.model.State solve)
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
            main.action.Actions.printBoard(solution.get(i));
        }

        System.out.println();

        System.out.println("Visited States Count: " + VisitedList.size());
        System.out.println("Node Depth: " + solution.size());
    }
}

//BFS
class BFS extends main.logic.BaseLogic {

    private Queue<main.model.State> queueStates = new LinkedList<>();

    public BFS(main.model.BoardModel board) {

        main.action.Actions.printBoard(board);

        System.out.println("loading ...");

        main.model.State solve = SolveBFS(new main.model.State(null, board));

        this.checkSolve(solve);
    }

    //    int i = 0;
    main.model.State SolveBFS(main.model.State state) {
        queueStates.add(state);


//        System.out.println(i++);

        if (main.action.Actions.isFinish(state.getBoardModel())){
            return state;
        }

        while (queueStates.size() > 0) {
            main.model.State node = queueStates.poll();
//            System.out.println(i++);
            VisitedList.add(node);

            if (main.action.Actions.isFinish(node.getBoardModel())){
                return node;
            }

            for (main.model.BoardModel child : main.action.Actions.GetNextStates(node.getBoardModel())) {
                if (!isVisited(child)) {
                    main.model.State state1 = new main.model.State(node, child);
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
// UCS
class UCS extends main.logic.BaseLogic {

    private Queue<main.model.State> queueStates = new PriorityQueue<>();

    public UCS(main.model.BoardModel board) {

        main.action.Actions.printBoard(board);

        System.out.println("loading ...");

        main.model.State solve = SolveUCS(new main.model.State(null, board,0));

        this.checkSolve(solve);
    }

    main.model.State SolveUCS(main.model.State state) {
        queueStates.add(state);

        if (main.action.Actions.isFinish(state.getBoardModel())){
            return state;
        }
        while (queueStates.size() > 0) {

            main.model.State node = queueStates.poll();
            VisitedList.add(node);

            if (main.action.Actions.isFinish(node.getBoardModel())){
                return node;
            }

            for (main.model.BoardModel child : main.action.Actions.GetNextStates(node.getBoardModel())) {
                if (!isVisited(child)) {
                    // cost for REPELLENT is 1
                    // cost for ATTRACT is 2
                    main.model.State state1 = new main.model.State(node, child,node.getCost() + child.getCost());
                    queueStates.add(state1);
                }
            }
        }
        return null;
    }
}

//Hill Climbing
class HillClimbing extends main.logic.BaseLogic {


    public HillClimbing(main.model.BoardModel board) {

        main.action.Actions.printBoard(board);

        System.out.println("loading ...");

        main.model.State solve = SolveHillClimbing(new main.model.State(null, board, 0, 0));

        this.checkSolve(solve);
    }

    main.model.State SolveHillClimbing(main.model.State state) {

        if (main.action.Actions.isFinish(state.getBoardModel())) {
            return state;
        }
        while (true) {
            int h = state.getHeuristic();

            main.model.BoardModel game = null;


            for (main.model.BoardModel child : main.action.Actions.GetNextStates(state.getBoardModel())) {
                if (child.getHeuristic() > h) {
                    h = child.getHeuristic();
                    game = child;
                }
            }
            main.model.State state1 = new main.model.State(state, game, 0, h);
            main.action.Actions.printBoard(state1.getBoardModel());
            if (state1.getBoardModel() == null){
                return null;
            }

            if (main.action.Actions.isFinish(state1.getBoardModel())) {
                return state1;
            }
            state = state1;
        }
    }
}

// A Stare
class A8 extends main.logic.BaseLogic {

    private Queue<main.model.State> queueStates = new PriorityQueue<>();

    public A8(main.model.BoardModel board) {

        main.action.Actions.printBoard(board);

        System.out.println("loading ...");

        main.model.State solve = SolveA8(new main.model.State(null, board,0,0));

        this.checkSolve(solve);
    }

    main.model.State SolveA8(main.model.State state) {
        queueStates.add(state);

        if (main.action.Actions.isFinish(state.getBoardModel())){
            return state;
        }
        while (queueStates.size() > 0) {

            main.model.State node = queueStates.poll();
            VisitedList.add(node);

            if (main.action.Actions.isFinish(node.getBoardModel())){
                return node;
            }

            for (main.model.BoardModel child : main.action.Actions.GetNextStates(node.getBoardModel())) {
                if (!isVisited(child)) {
                    // cost for REPELLENT is 1
                    // cost for ATTRACT is 2
                    main.model.State state1 = new main.model.State(node, child,node.getCost() + child.getCost() - child.getHeuristic() , child.getHeuristic());
                    queueStates.add(state1);
                }
            }
        }
        return null;
    }
}











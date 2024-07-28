package main.action;

import main.model.CellModel;
import main.model.CellType;

import java.util.ArrayList;
import java.util.Collections;

public class MoveOnAction {

    public static ArrayList<CellModel> affectedCell;
    public static CellModel cell;

    public static int leftOrUpMoveRepellent(ArrayList<CellModel> cells) {
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
            CellModel cell = affectedCell.get(i);
            switch (cell.getCellType()) {
                case STONE, SOLVE_STONE, ATTRACT, SOLVE_ATTRACT, REPELLENT, SOLVE_REPELLENT -> {
                    CellModel neighborCell = affectedCell.get(i - 1);
                    if (neighborCell.getCellType() == CellType.BL0CK) {
                        continue;
                    }
                    // change type of neighbor cell
                    doEffect(cell,neighborCell);
                }
            }
        }
        return heuristic;
    }


    public static int rightOrDownMoveRepellent(ArrayList<CellModel> cells) {
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
            CellModel cell = affectedCell.get(i);
            switch (cell.getCellType()) {
                case STONE, SOLVE_STONE, ATTRACT, SOLVE_ATTRACT, REPELLENT, SOLVE_REPELLENT ->{
                    CellModel neighborCell = affectedCell.get(i + 1);
                    if (neighborCell.getCellType() == CellType.BL0CK) {
                        continue;
                    }
                    doEffect(cell,neighborCell);
                }
            }
        }
        return heuristic;
    }

    public static int rightOrDownMoveAttract(ArrayList<CellModel> cells) {
        int heuristic = 0 ;
        for (int i = 1; i < cells.size(); i++) {
            cell = cells.get(i);
            switch (cell.getCellType()) {
                case STONE, SOLVE_STONE, ATTRACT, SOLVE_ATTRACT, REPELLENT, SOLVE_REPELLENT -> {
                    heuristic++;
                    CellModel neighborCell = cells.get(i - 1);
                    if (neighborCell.getCellType() == CellType.BL0CK) {
                        continue;
                    }
                    doEffect(cell,neighborCell);
                }
            }
        }
        return heuristic;
    }

    public static int leftOrUpMoveAttract(ArrayList<CellModel> cells) {
        int heuristic = 0;
        for (int i = cells.size() - 2; i >= 0; i--) {
            cell = cells.get(i);
            switch (cell.getCellType()) {
                case STONE, SOLVE_STONE, ATTRACT, SOLVE_ATTRACT, REPELLENT, SOLVE_REPELLENT -> {
                    heuristic++;
                    CellModel neighborCell = cells.get(i + 1);
                    if (neighborCell.getCellType() == CellType.BL0CK) {
                        continue;
                    }
                    doEffect(cell,neighborCell);
                }
            }
        }
        return heuristic;
    }

    // effect method
    private static void doEffect(CellModel cell , CellModel neighborCell) {
        // change type of neighbor cell
        switch (neighborCell.getCellType()) {
            case SOLVE -> {
                switch (cell.getCellType()) {
                    case STONE, SOLVE_STONE -> neighborCell.setCellType(CellType.SOLVE_STONE);
                    case REPELLENT, SOLVE_REPELLENT -> neighborCell.setCellType(CellType.SOLVE_REPELLENT);
                    case ATTRACT, SOLVE_ATTRACT -> neighborCell.setCellType(CellType.SOLVE_ATTRACT);
                }
            }
            case NONE -> {
                switch (cell.getCellType()) {
                    case STONE,REPELLENT,ATTRACT -> neighborCell.setCellType(cell.getCellType());
                    case SOLVE_STONE -> neighborCell.setCellType(CellType.STONE);
                    case SOLVE_REPELLENT -> neighborCell.setCellType(CellType.REPELLENT);
                    case SOLVE_ATTRACT -> neighborCell.setCellType(CellType.ATTRACT);
                }
            }
        }
        // change type of current cell
        switch (cell.getCellType()) {
            case STONE,REPELLENT,ATTRACT -> cell.setCellType(CellType.NONE);
            case SOLVE_STONE,SOLVE_REPELLENT,SOLVE_ATTRACT -> cell.setCellType(CellType.SOLVE);
        }
    }
}

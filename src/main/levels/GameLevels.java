package main.levels;

import main.model.BoardModel;
import main.model.CellModel;
import main.model.CellType;
import main.model.PositionModel;

import java.util.ArrayList;

public class GameLevels {

    public static ArrayList<BoardModel> levels = new ArrayList<>();

    static {
        CellModel[][] level1= {
                {new CellModel(CellType.NONE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.NONE,new PositionModel(0,2)),new CellModel(CellType.NONE,new PositionModel(0,3)),},
                {new CellModel(CellType.NONE,new PositionModel(1,0)),new CellModel(CellType.SOLVE,new PositionModel(1,1)),new CellModel(CellType.STONE,new PositionModel(1,2)),new CellModel(CellType.SOLVE,new PositionModel(1,3)),},
                {new CellModel(CellType.REPELLENT,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.NONE,new PositionModel(2,2)),new CellModel(CellType.NONE,new PositionModel(2,3)),}
        };
        BoardModel boardModel = new BoardModel(2,3,4,level1);
        levels.add(boardModel);

        CellModel[][] level2= {
                {new CellModel(CellType.NONE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.SOLVE,new PositionModel(0,2)),new CellModel(CellType.NONE,new PositionModel(0,3)),new CellModel(CellType.NONE,new PositionModel(0,4)),},
                {new CellModel(CellType.NONE,new PositionModel(1,0)),new CellModel(CellType.NONE,new PositionModel(1,1)),new CellModel(CellType.STONE,new PositionModel(1,2)),new CellModel(CellType.NONE,new PositionModel(1,3)),new CellModel(CellType.NONE,new PositionModel(1,4)),},
                {new CellModel(CellType.SOLVE,new PositionModel(2,0)),new CellModel(CellType.STONE,new PositionModel(2,1)),new CellModel(CellType.SOLVE,new PositionModel(2,2)),new CellModel(CellType.STONE,new PositionModel(2,3)),new CellModel(CellType.SOLVE,new PositionModel(2,4)),},
                {new CellModel(CellType.NONE,new PositionModel(3,0)),new CellModel(CellType.NONE,new PositionModel(3,1)),new CellModel(CellType.STONE,new PositionModel(3,2)),new CellModel(CellType.NONE,new PositionModel(3,3)),new CellModel(CellType.NONE,new PositionModel(3,4)),},
                {new CellModel(CellType.REPELLENT,new PositionModel(4,0)),new CellModel(CellType.NONE,new PositionModel(4,1)),new CellModel(CellType.SOLVE,new PositionModel(4,2)),new CellModel(CellType.NONE,new PositionModel(4,3)),new CellModel(CellType.NONE,new PositionModel(4,4)),},
        };
        levels.add(new BoardModel(2,5,5,level2));

        CellModel[][] level3= {
                {new CellModel(CellType.BL0CK,new PositionModel(0,0)),new CellModel(CellType.BL0CK,new PositionModel(0,1)),new CellModel(CellType.BL0CK,new PositionModel(0,2)),new CellModel(CellType.SOLVE,new PositionModel(0,3)),},
                {new CellModel(CellType.NONE,new PositionModel(1,0)),new CellModel(CellType.NONE,new PositionModel(1,1)),new CellModel(CellType.STONE,new PositionModel(1,2)),new CellModel(CellType.NONE,new PositionModel(1,3)),},
                {new CellModel(CellType.REPELLENT,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.NONE,new PositionModel(2,2)),new CellModel(CellType.SOLVE,new PositionModel(2,3)),}
        };
        levels.add(new BoardModel(2,3,4,level3));

        CellModel[][] level4= {
                {new CellModel(CellType.SOLVE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.SOLVE,new PositionModel(0,2)),},
                {new CellModel(CellType.BL0CK,new PositionModel(1,0)),new CellModel(CellType.STONE,new PositionModel(1,1)),new CellModel(CellType.NONE,new PositionModel(1,2)),},
                {new CellModel(CellType.REPELLENT,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.NONE,new PositionModel(2,2)),},
                {new CellModel(CellType.BL0CK,new PositionModel(3,0)),new CellModel(CellType.STONE,new PositionModel(3,1)),new CellModel(CellType.NONE,new PositionModel(3,2)),},
                {new CellModel(CellType.NONE,new PositionModel(4,0)),new CellModel(CellType.SOLVE,new PositionModel(4,1)),new CellModel(CellType.NONE,new PositionModel(4,2)),}
        };
        levels.add(new BoardModel(2,5,3,level4));

        CellModel[][] level5= {
                {new CellModel(CellType.SOLVE,new PositionModel(0,0)),new CellModel(CellType.BL0CK,new PositionModel(0,1)),new CellModel(CellType.SOLVE,new PositionModel(0,2)),},
                {new CellModel(CellType.SOLVE_STONE,new PositionModel(1,0)),new CellModel(CellType.BL0CK,new PositionModel(1,1)),new CellModel(CellType.SOLVE_STONE,new PositionModel(1,2)),},
                {new CellModel(CellType.STONE,new PositionModel(2,0)),new CellModel(CellType.BL0CK,new PositionModel(2,1)),new CellModel(CellType.STONE,new PositionModel(2,2)),},
                {new CellModel(CellType.SOLVE,new PositionModel(3,0)),new CellModel(CellType.REPELLENT,new PositionModel(3,1)),new CellModel(CellType.NONE,new PositionModel(3,2)),},
        };
        levels.add(new BoardModel(2,4,3,level5));

        CellModel[][] level6= {
                {new CellModel(CellType.NONE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.NONE,new PositionModel(0,2)),new CellModel(CellType.SOLVE,new PositionModel(0,3)),new CellModel(CellType.NONE,new PositionModel(0,4)),},
                {new CellModel(CellType.NONE,new PositionModel(1,0)),new CellModel(CellType.STONE,new PositionModel(1,1)),new CellModel(CellType.SOLVE,new PositionModel(1,2)),new CellModel(CellType.STONE,new PositionModel(1,3)),new CellModel(CellType.NONE,new PositionModel(1,4)),},
                {new CellModel(CellType.REPELLENT,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.NONE,new PositionModel(2,2)),new CellModel(CellType.SOLVE,new PositionModel(2,3)),new CellModel(CellType.NONE,new PositionModel(2,4)),}
        };
        levels.add(new BoardModel(2,3,5,level6));

        CellModel[][] level7= {
                {new CellModel(CellType.SOLVE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.NONE,new PositionModel(0,2)),new CellModel(CellType.NONE,new PositionModel(0,3)),},
                {new CellModel(CellType.SOLVE_STONE,new PositionModel(1,0)),new CellModel(CellType.NONE,new PositionModel(1,1)),new CellModel(CellType.NONE,new PositionModel(1,2)),new CellModel(CellType.NONE,new PositionModel(1,3)),},
                {new CellModel(CellType.STONE,new PositionModel(2,0)),new CellModel(CellType.REPELLENT,new PositionModel(2,1)),new CellModel(CellType.NONE,new PositionModel(2,2)),new CellModel(CellType.SOLVE,new PositionModel(2,3)),},
                {new CellModel(CellType.NONE,new PositionModel(3,0)),new CellModel(CellType.STONE,new PositionModel(3,1)),new CellModel(CellType.SOLVE_STONE,new PositionModel(3,2)),new CellModel(CellType.NONE,new PositionModel(3,3)),},
                {new CellModel(CellType.BL0CK,new PositionModel(4,0)),new CellModel(CellType.BL0CK,new PositionModel(4,1)),new CellModel(CellType.BL0CK,new PositionModel(4,2)),new CellModel(CellType.SOLVE,new PositionModel(4,3)),},
        };
        levels.add(new BoardModel(2,5,4,level7));

        CellModel[][] level8= {
                {new CellModel(CellType.SOLVE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.SOLVE,new PositionModel(0,2)),new CellModel(CellType.NONE,new PositionModel(0,3)),},
                {new CellModel(CellType.NONE,new PositionModel(1,0)),new CellModel(CellType.STONE,new PositionModel(1,1)),new CellModel(CellType.STONE,new PositionModel(1,2)),new CellModel(CellType.NONE,new PositionModel(1,3)),},
                {new CellModel(CellType.REPELLENT,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.SOLVE,new PositionModel(2,2)),new CellModel(CellType.NONE,new PositionModel(2,3)),}
        };
        levels.add(new BoardModel(2,3,4,level8));

        CellModel[][] level9= {
                {new CellModel(CellType.REPELLENT, new PositionModel(0, 0)), new CellModel(CellType.SOLVE, new PositionModel(0, 1)), new CellModel(CellType.NONE, new PositionModel(0, 2)), new CellModel(CellType.SOLVE_STONE, new PositionModel(0, 3)), new CellModel(CellType.NONE, new PositionModel(0, 4)), new CellModel(CellType.STONE, new PositionModel(0, 5)),new CellModel(CellType.SOLVE, new PositionModel(0, 6)),},
        };
        levels.add(new BoardModel(2,1,7,level9));

        CellModel[][] level10= {
                {new CellModel(CellType.REPELLENT,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.NONE,new PositionModel(0,2)),new CellModel(CellType.NONE,new PositionModel(0,3)),},
                {new CellModel(CellType.NONE,new PositionModel(1,0)),new CellModel(CellType.SOLVE,new PositionModel(1,1)),new CellModel(CellType.NONE,new PositionModel(1,2)),new CellModel(CellType.SOLVE,new PositionModel(1,3)),},
                {new CellModel(CellType.NONE,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.STONE,new PositionModel(2,2)),new CellModel(CellType.STONE,new PositionModel(2,3)),},
                {new CellModel(CellType.SOLVE,new PositionModel(3,0)),new CellModel(CellType.STONE,new PositionModel(3,1)),new CellModel(CellType.NONE,new PositionModel(3,2)),new CellModel(CellType.SOLVE,new PositionModel(3,3)),},
        };
        levels.add(new BoardModel(2,4,4,level10));

        CellModel[][] level11= {
                {new CellModel(CellType.STONE,new PositionModel(0,0)),new CellModel(CellType.SOLVE,new PositionModel(0,1)),new CellModel(CellType.SOLVE,new PositionModel(0,2)),new CellModel(CellType.SOLVE,new PositionModel(0,3)),new CellModel(CellType.STONE,new PositionModel(0,4)),},
                {new CellModel(CellType.BL0CK,new PositionModel(1,0)),new CellModel(CellType.BL0CK,new PositionModel(1,1)),new CellModel(CellType.ATTRACT,new PositionModel(1,2)),new CellModel(CellType.BL0CK,new PositionModel(1,3)),new CellModel(CellType.BL0CK,new PositionModel(1,4)),},
        };
        levels.add(new BoardModel(2,2,5,level11));

        CellModel[][] level12= {
                {new CellModel(CellType.STONE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.BL0CK,new PositionModel(0,2)),new CellModel(CellType.BL0CK,new PositionModel(0,3)),},
                {new CellModel(CellType.SOLVE_STONE,new PositionModel(1,0)),new CellModel(CellType.NONE,new PositionModel(1,1)),new CellModel(CellType.BL0CK,new PositionModel(1,2)),new CellModel(CellType.BL0CK,new PositionModel(1,3)),},
                {new CellModel(CellType.SOLVE,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.NONE,new PositionModel(2,2)),new CellModel(CellType.NONE,new PositionModel(2,3)),},
                {new CellModel(CellType.NONE,new PositionModel(3,0)),new CellModel(CellType.ATTRACT,new PositionModel(3,1)),new CellModel(CellType.NONE,new PositionModel(3,2)),new CellModel(CellType.NONE,new PositionModel(3,3)),},
                {new CellModel(CellType.SOLVE,new PositionModel(4,0)),new CellModel(CellType.NONE,new PositionModel(4,1)),new CellModel(CellType.SOLVE,new PositionModel(4,2)),new CellModel(CellType.STONE,new PositionModel(4,3)),},
        };
        levels.add(new BoardModel(2,5,4,level12));

        CellModel[][] level13= {
                {new CellModel(CellType.STONE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.NONE,new PositionModel(0,2)),new CellModel(CellType.SOLVE,new PositionModel(0,3)),new CellModel(CellType.SOLVE_STONE,new PositionModel(0,4)),new CellModel(CellType.STONE,new PositionModel(0,5)),},
                {new CellModel(CellType.BL0CK,new PositionModel(1,0)),new CellModel(CellType.SOLVE,new PositionModel(1,1)),new CellModel(CellType.NONE,new PositionModel(1,2)),new CellModel(CellType.NONE,new PositionModel(1,3)),new CellModel(CellType.BL0CK,new PositionModel(1,4)),new CellModel(CellType.BL0CK,new PositionModel(1,5)),},
                {new CellModel(CellType.BL0CK,new PositionModel(2,0)),new CellModel(CellType.SOLVE,new PositionModel(2,1)),new CellModel(CellType.NONE,new PositionModel(2,2)),new CellModel(CellType.ATTRACT,new PositionModel(2,3)),new CellModel(CellType.BL0CK,new PositionModel(2,4)),new CellModel(CellType.BL0CK,new PositionModel(2,5)),}
        };
        levels.add(new BoardModel(2,3,6,level13));

        CellModel[][] level14= {
                {new CellModel(CellType.NONE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.NONE,new PositionModel(0,2)),new CellModel(CellType.STONE,new PositionModel(0,3)),},
                {new CellModel(CellType.SOLVE,new PositionModel(1,0)),new CellModel(CellType.NONE,new PositionModel(1,1)),new CellModel(CellType.SOLVE,new PositionModel(1,2)),new CellModel(CellType.NONE,new PositionModel(1,3)),},
                {new CellModel(CellType.STONE,new PositionModel(2,0)),new CellModel(CellType.SOLVE,new PositionModel(2,1)),new CellModel(CellType.SOLVE,new PositionModel(2,2)),new CellModel(CellType.NONE,new PositionModel(2,3)),},
                {new CellModel(CellType.STONE,new PositionModel(3,0)),new CellModel(CellType.NONE,new PositionModel(3,1)),new CellModel(CellType.NONE,new PositionModel(3,2)),new CellModel(CellType.ATTRACT,new PositionModel(3,3)),},
        };
        levels.add(new BoardModel(2,4,4,level14));

        CellModel[][] level15= {
                {new CellModel(CellType.SOLVE,new PositionModel(0,0)),new CellModel(CellType.STONE,new PositionModel(0,1)),new CellModel(CellType.SOLVE,new PositionModel(0,2)),new CellModel(CellType.STONE,new PositionModel(0,3)),new CellModel(CellType.NONE,new PositionModel(0,4)),},
                {new CellModel(CellType.NONE,new PositionModel(1,0)),new CellModel(CellType.NONE,new PositionModel(1,1)),new CellModel(CellType.REPELLENT,new PositionModel(1,2)),new CellModel(CellType.NONE,new PositionModel(1,3)),new CellModel(CellType.SOLVE,new PositionModel(1,4)),},
                {new CellModel(CellType.NONE,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.ATTRACT,new PositionModel(2,2)),new CellModel(CellType.NONE,new PositionModel(2,3)),new CellModel(CellType.SOLVE,new PositionModel(2,4)),}
        };
        levels.add(new BoardModel(2,3,5,level15));

        CellModel[][] level16= {
                {new CellModel(CellType.NONE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.NONE,new PositionModel(0,2)),new CellModel(CellType.SOLVE,new PositionModel(0,3)),new CellModel(CellType.SOLVE,new PositionModel(0,4)),},
                {new CellModel(CellType.NONE,new PositionModel(1,0)),new CellModel(CellType.NONE,new PositionModel(1,1)),new CellModel(CellType.STONE,new PositionModel(1,2)),new CellModel(CellType.NONE,new PositionModel(1,3)),new CellModel(CellType.NONE,new PositionModel(1,4)),},
                {new CellModel(CellType.ATTRACT,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.NONE,new PositionModel(2,2)),new CellModel(CellType.NONE,new PositionModel(2,3)),new CellModel(CellType.REPELLENT,new PositionModel(2,4)),},
                {new CellModel(CellType.NONE,new PositionModel(3,0)),new CellModel(CellType.NONE,new PositionModel(3,1)),new CellModel(CellType.STONE,new PositionModel(3,2)),new CellModel(CellType.NONE,new PositionModel(3,3)),new CellModel(CellType.NONE,new PositionModel(3,4)),},
                {new CellModel(CellType.SOLVE,new PositionModel(4,0)),new CellModel(CellType.NONE,new PositionModel(4,1)),new CellModel(CellType.NONE,new PositionModel(4,2)),new CellModel(CellType.SOLVE,new PositionModel(4,3)),new CellModel(CellType.NONE,new PositionModel(4,4)),},
        };
        levels.add(new BoardModel(2,5,5,level16));

        CellModel[][] level17= {
                {new CellModel(CellType.ATTRACT,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.STONE,new PositionModel(0,2)),new CellModel(CellType.NONE,new PositionModel(0,3)),},
                {new CellModel(CellType.NONE,new PositionModel(1,0)),new CellModel(CellType.SOLVE,new PositionModel(1,1)),new CellModel(CellType.NONE,new PositionModel(1,2)),new CellModel(CellType.SOLVE,new PositionModel(1,3)),},
                {new CellModel(CellType.STONE,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.SOLVE,new PositionModel(2,2)),new CellModel(CellType.NONE,new PositionModel(2,3)),},
                {new CellModel(CellType.NONE,new PositionModel(3,0)),new CellModel(CellType.SOLVE,new PositionModel(3,1)),new CellModel(CellType.NONE,new PositionModel(3,2)),new CellModel(CellType.REPELLENT,new PositionModel(3,3)),},
        };
        levels.add(new BoardModel(2,4,4,level17));

        //==============================================================
        
        CellModel[][] myLevel1= {
                {new CellModel(CellType.NONE,new PositionModel(0,0)),new CellModel(CellType.REPELLENT,new PositionModel(0,1)),new CellModel(CellType.SOLVE,new PositionModel(0,2)),},
                {new CellModel(CellType.STONE,new PositionModel(1,0)),new CellModel(CellType.BL0CK,new PositionModel(1,1)),new CellModel(CellType.STONE,new PositionModel(1,2)),},
                {new CellModel(CellType.SOLVE_STONE,new PositionModel(2,0)),new CellModel(CellType.BL0CK,new PositionModel(2,1)),new CellModel(CellType.SOLVE_STONE,new PositionModel(2,2)),},
                {new CellModel(CellType.SOLVE,new PositionModel(3,0)),new CellModel(CellType.BL0CK,new PositionModel(3,1)),new CellModel(CellType.SOLVE,new PositionModel(3,2)),},
        };
        levels.add(new BoardModel(2,4,3,myLevel1));

        CellModel[][] myLevel2= {
                {new CellModel(CellType.SOLVE, new PositionModel(0, 0)), new CellModel(CellType.SOLVE_STONE, new PositionModel(0, 1)), new CellModel(CellType.NONE, new PositionModel(0, 2)), new CellModel(CellType.STONE, new PositionModel(0, 3)), new CellModel(CellType.NONE, new PositionModel(0, 4)), new CellModel(CellType.SOLVE_REPELLENT, new PositionModel(0, 5)),new CellModel(CellType.STONE, new PositionModel(0, 6)),new CellModel(CellType.NONE, new PositionModel(0, 7)),new CellModel(CellType.SOLVE_STONE, new PositionModel(0, 8)),new CellModel(CellType.SOLVE, new PositionModel(0, 9)),},
        };
        levels.add(new BoardModel(2,1,10,myLevel2));

        CellModel[][] myLevel3= {
                {new CellModel(CellType.STONE, new PositionModel(0, 0)), new CellModel(CellType.SOLVE, new PositionModel(0, 1)), new CellModel(CellType.STONE, new PositionModel(0, 2)), new CellModel(CellType.BL0CK, new PositionModel(0, 3)), new CellModel(CellType.ATTRACT, new PositionModel(0, 4)), new CellModel(CellType.NONE, new PositionModel(0, 5)),},
        };
        levels.add(new BoardModel(2,1,6,myLevel3));

        CellModel[][] myLevel4= {
                {new CellModel(CellType.NONE,new PositionModel(0,0)),new CellModel(CellType.STONE,new PositionModel(0,1)),new CellModel(CellType.NONE,new PositionModel(0,2)),},
                {new CellModel(CellType.SOLVE,new PositionModel(1,0)),new CellModel(CellType.NONE,new PositionModel(1,1)),new CellModel(CellType.SOLVE,new PositionModel(1,2)),},
                {new CellModel(CellType.NONE,new PositionModel(2,0)),new CellModel(CellType.SOLVE,new PositionModel(2,1)),new CellModel(CellType.REPELLENT,new PositionModel(2,2)),},
                {new CellModel(CellType.ATTRACT,new PositionModel(3,0)),new CellModel(CellType.NONE,new PositionModel(3,1)),new CellModel(CellType.NONE,new PositionModel(3,2)),},
                {new CellModel(CellType.NONE,new PositionModel(4,0)),new CellModel(CellType.SOLVE_STONE,new PositionModel(4,1)),new CellModel(CellType.NONE,new PositionModel(4,2)),},
                {new CellModel(CellType.SOLVE,new PositionModel(5,0)),new CellModel(CellType.STONE,new PositionModel(5,1)),new CellModel(CellType.NONE,new PositionModel(5,2)),},
        };
        levels.add(new BoardModel(2,6,3,myLevel4));

        CellModel[][] myLevel5= {
                {new CellModel(CellType.NONE,new PositionModel(0,0)),new CellModel(CellType.NONE,new PositionModel(0,1)),new CellModel(CellType.STONE,new PositionModel(0,2)),new CellModel(CellType.BL0CK,new PositionModel(0,3)),new CellModel(CellType.BL0CK,new PositionModel(0,4)),},
                {new CellModel(CellType.NONE,new PositionModel(1,0)),new CellModel(CellType.SOLVE,new PositionModel(1,1)),new CellModel(CellType.NONE,new PositionModel(1,2)),new CellModel(CellType.SOLVE,new PositionModel(1,3)),new CellModel(CellType.BL0CK,new PositionModel(1,4)),},
                {new CellModel(CellType.NONE,new PositionModel(2,0)),new CellModel(CellType.NONE,new PositionModel(2,1)),new CellModel(CellType.SOLVE,new PositionModel(2,2)),new CellModel(CellType.NONE,new PositionModel(2,3)),new CellModel(CellType.NONE,new PositionModel(2,4)),},
                {new CellModel(CellType.ATTRACT,new PositionModel(3,0)),new CellModel(CellType.NONE,new PositionModel(3,1)),new CellModel(CellType.NONE,new PositionModel(3,2)),new CellModel(CellType.SOLVE,new PositionModel(3,3)),new CellModel(CellType.STONE,new PositionModel(3,4)),},
                {new CellModel(CellType.BL0CK,new PositionModel(4,0)),new CellModel(CellType.SOLVE,new PositionModel(4,1)),new CellModel(CellType.STONE,new PositionModel(4,2)),new CellModel(CellType.NONE,new PositionModel(4,3)),new CellModel(CellType.NONE,new PositionModel(4,4)),},
                {new CellModel(CellType.BL0CK,new PositionModel(5,0)),new CellModel(CellType.BL0CK,new PositionModel(5,1)),new CellModel(CellType.REPELLENT,new PositionModel(5,2)),new CellModel(CellType.NONE,new PositionModel(5,3)),new CellModel(CellType.NONE,new PositionModel(5,4)),},
        };
        levels.add(new BoardModel(2,6,5,myLevel5));

        CellModel[][] myLevel6= {
                {new CellModel(CellType.SOLVE,new PositionModel(0,0)),new CellModel(CellType.REPELLENT,new PositionModel(0,1)),new CellModel(CellType.SOLVE,new PositionModel(0,2)),},
                {new CellModel(CellType.REPELLENT,new PositionModel(1,0)),new CellModel(CellType.BL0CK,new PositionModel(1,1)),new CellModel(CellType.BL0CK,new PositionModel(1,2)),},
          };
        levels.add(new BoardModel(2,2,3,myLevel6));
        }
}

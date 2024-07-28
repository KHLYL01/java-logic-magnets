package main.model;

public class CellModel implements Cloneable{

    private CellType cellType;
    private PositionModel positionModel;

    public CellModel(CellType cellType, PositionModel positionModel) {
        this.cellType = cellType;
        this.positionModel = positionModel;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public PositionModel getPositionModel() {
        return positionModel;
    }

    public void setPositionModel(PositionModel positionModel) {
        this.positionModel = positionModel;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CellModel cellClone = (CellModel) super.clone();
        cellClone.setCellType(cellType);
        cellClone.setPositionModel((PositionModel) cellClone.getPositionModel().clone());
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

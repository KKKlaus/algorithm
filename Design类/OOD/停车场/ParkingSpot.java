package 停车场;

public abstract class ParkingSpot {

    private boolean free;
    private Vehicle vehicle;
    private final ParkingEnum.ParkingSpotType type;
    private int level, row, col;

    public ParkingSpot(int level, int row, int col, ParkingEnum.ParkingSpotType type) {
        this.level = level;
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.free = false;
    }

    public boolean isFree() {
        return this.free;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

class CompactSpot extends ParkingSpot{

    public CompactSpot(int level, int row, int col) {
        super(level, row, col, ParkingEnum.ParkingSpotType.COMPACT);
    }
}

class LargeSpot extends ParkingSpot{

    public LargeSpot(int level, int row, int col) {
        super(level, row, col, ParkingEnum.ParkingSpotType.LARGE);
    }
}

class ReservedSpot extends ParkingSpot{

    public ReservedSpot(int level, int row, int col) {
        super(level, row, col, ParkingEnum.ParkingSpotType.RESERVED);
    }
}

class ElectricSpot extends ParkingSpot{

    public ElectricSpot(int level, int row, int col) {
        super(level, row, col, ParkingEnum.ParkingSpotType.ELECTRIC);
    }
}
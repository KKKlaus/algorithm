package 停车场;

import java.util.HashMap;

public class ParkingLot {

    private final int maxCarCount;
    private final int maxElectricCount;
    private final int maxMotorCount;
    private final int maxTruckCount;

    private int level;
    private int row;
    private int col;

    HashMap<Integer, Integer> levelToSpaceMap;
    HashMap<Integer, ParkingSpot[][]> levelToSpotsMap;

    private static ParkingLot parkingLot = null;

    // private constructor to restrict for singleton
    private ParkingLot() {
        // 这些数据应该从database读
        this.maxCarCount = 10; // = dynamoDB.get(carCount);
        this.maxElectricCount = 10;
        this.maxMotorCount = 10;
        this.maxTruckCount = 10;
        this.level = 3;
        this.row = 10;
        this.col = 10;
        this.levelToSpaceMap = new HashMap<>();
        for (int i = 1; i <= level; i++) {
            levelToSpaceMap.put(i, row * col);
        }
        this.levelToSpotsMap = new HashMap<>();
        for (int i = 1; i <= level; i++) {
            levelToSpotsMap.put(i, new ParkingSpot[row][col]);
        }
    }

    // static method to get the singleton instance of ParkingLot
    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

    public void arrangeSpots(ParkingSpot spot) {
        ParkingSpot[][] matrix = this.levelToSpotsMap.get(level);
        matrix[spot.getRow()][spot.getCol()] = spot;
    }

    public void parkVehicle(Vehicle vehicle, int[] position) {
        int level = position[0], row = position[1], col = position[2];
        ParkingSpot spot = levelToSpotsMap.get(level)[row][col];
        spot.assignVehicle(vehicle);
        this.levelToSpaceMap.put(level, this.levelToSpaceMap.get(level) - 1);
    }

    public int getSpaceByLevel(int level) {
        return this.levelToSpaceMap.get(level);
    }

    public boolean isParkingFull() {
        for (int i = 1; i <= this.level; i++) {
            if (this.levelToSpaceMap.containsKey(i)) {
                if(this.levelToSpaceMap.get(i) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        parkingLot.arrangeSpots(new CompactSpot(1, 1, 1));
    }
}

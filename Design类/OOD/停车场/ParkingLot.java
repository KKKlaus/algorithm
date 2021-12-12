package 停车场;

/*
Design a parking lot using object-oriented principles.
*/
// give a vehicle, return the available spot(s)

//the parking lot has multiple levels, each level has multiple rows of spots
//the parking lot can park motorcycles, cars, and buses
//the parking lot chas motorcycle spots, compact spots, and large spots
//a motorcycle could park in any spots
//a car can park in either a single compact spot or a single large spot
//a bus can park in five large spots that are consecutive and within the same row, it cannot park in small spots

//关键是Level类的实现

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    Level[] levels;
    private static final int NUM_OF_LEVEl = 5;

    public ParkingLot() {
        levels = new Level[NUM_OF_LEVEl];
        // for 循环init level
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        for (int i = 0; i < levels.length; i++) {
            ParkingSpot spot = levels[i].assignVehicle(vehicle);
            if (spot != null) return spot;
        }
        return null;
    }
}


class Level {
    private int floorNumber;
    private ParkingSpot[] spots;
    private int availableSpots;
    private static final int NUM_OF_ROW = 10;

    public Level(int floorNumber, int availableSpots) {
        this.floorNumber = floorNumber;
        this.availableSpots = availableSpots;
        // init spots : design by map / owner
    }


    // if bus, return the first spot
    public ParkingSpot assignVehicle(Vehicle vehicle) {
        if (availableSpots < vehicle.spotsNeeded) return null;

        // find parkingSpot
        int spotNumber = findParkingSpot(vehicle);

        // park the vehicle: set parkingSpot/vehicle
        parkVehicle(spotNumber, vehicle);

        return spotNumber == -1 ? null : spots[spotNumber];
    }

    private int findParkingSpot(Vehicle vehicle) {
        for (int i = 0; i < spots.length; i++) {
            if (!spots[i].isFree) continue;
            if (vehicle.vehicleType == Vehicle.VehicleType.BUS) {
                if (vehicle.isFitInSpot(spots[i]) && isFiveConsecutive(i)) return i;
            } else {
                if (vehicle.isFitInSpot(spots[i])) return i;
            }
        }
        return -1;
    }

    private void parkVehicle(int spotNumber, Vehicle vehicle) {
        availableSpots -= vehicle.spotsNeeded;
        for (int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i++) {
            vehicle.parkingSpots.add(spots[spotNumber]);
            spots[spotNumber].isFree = false;
        }
    }

    private boolean isFiveConsecutive(int start) {
        for (int i = start + 1; i < start + 5; i++) {
            if (spots[i].getSpotType() != ParkingSpot.SpotType.LARGE) return false;
            if (spots[i].getRow() != spots[i - 1].getRow()) return false;
        }
        return true;
    }
}

class ParkingSpot {
    private int level;
    private int row;
    private Vehicle vehicle;
    private SpotType spotType;
    public boolean isFree = true;

    enum SpotType {
        MOTOR,
        COMPACT,
        LARGE
    }

    public SpotType getSpotType() {
        return this.spotType;
    }

    public int getRow() {
        return this.row;
    }

}

abstract class Vehicle {
    protected String plate;
    VehicleType vehicleType;
    protected int spotsNeeded;
    protected ArrayList<ParkingSpot> parkingSpots;

    enum VehicleType {
        MOTOR,
        CAR,
        BUS
    }

    public abstract boolean isFitInSpot(ParkingSpot spot);
}

class Motor extends Vehicle{

    Motor() {
        super();
        this.spotsNeeded = 1;
        this.vehicleType = VehicleType.MOTOR;
    }

    @Override
    public boolean isFitInSpot(ParkingSpot spot) {
        return true;
    }
}

class Car extends Vehicle {

    Car() {
        super();
        this.spotsNeeded = 1;
        this.vehicleType = VehicleType.CAR;
    }

    @Override
    public boolean isFitInSpot(ParkingSpot spot) {
        return spot.getSpotType() == ParkingSpot.SpotType.COMPACT || spot.getSpotType() == ParkingSpot.SpotType.LARGE;
    }
}

class Bus extends Vehicle {

    Bus() {
        super();
        this.spotsNeeded = 5;
        this.vehicleType = VehicleType.BUS;
    }

    @Override
    public boolean isFitInSpot(ParkingSpot spot) {
        return spot.getSpotType() == ParkingSpot.SpotType.LARGE;
    }
}


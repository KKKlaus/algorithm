package 停车场;

// Vehicle类
public abstract class Vehicle {

    private String plate;
    private ParkingEnum.VehicleType type;

    public Vehicle(ParkingEnum.VehicleType type) {
        this.type = type;
    }
}

class Car extends Vehicle{

    public Car() {
        super(ParkingEnum.VehicleType.CAR);
    }
}

class MOTOR extends Vehicle{

    public MOTOR() {
        super(ParkingEnum.VehicleType.MOTOR);
    }
}

class TRUCK extends Vehicle{

    public TRUCK() {
        super(ParkingEnum.VehicleType.TRUCK);
    }
}

class ELECTRIC extends Vehicle{

    public ELECTRIC() {
        super(ParkingEnum.VehicleType.ELECTRIC);
    }
}
import java.util.UUID;

public abstract class Vehicle {
    int fuelVolume;
    String vehicleNum;
    int nowSpeed;
    String nowState;

    final private String uuid = UUID.randomUUID().toString();

    public Vehicle() {
        this.fuelVolume = 100;
        this.nowSpeed = 0;
        this.nowState = "주차 중";
        this.vehicleNum = uuid;
    }
    public abstract void takePassenger();

    public abstract void changeSpeed(int changeSpeed);

    public abstract void startDriving();

    public int nowFuel(){
        return fuelVolume;
    }

}

import java.util.Scanner;

public class Taxi extends Vehicle implements addFarePerDistance, payment {
    String taxiNum;
    String destination;
    int basicDistance;
    int toDestinationDistance;
    int baseFare;
    int farePerDistance;
    int fare;

    public Taxi() {
        this.taxiNum = vehicleNum;
        this.basicDistance = 20;
        this.baseFare = 3000;
        this.farePerDistance = 100;
    }

    @Override
    public void takePassenger() {
        if (nowState.equals("일반")) {
            nowState = "운행 중";
        } else System.out.println("탑승 불가");

        System.out.println("목적지 입력");                  //목적지 입력
        Scanner scDestination = new Scanner(System.in);
        this.destination = scDestination.next();

        System.out.println("목적지 까지 거리 입력");         //목적지 까지 거리 입력
        Scanner scToDestinationDistance = new Scanner(System.in);
        this.toDestinationDistance = scToDestinationDistance.nextInt();
    }

    @Override
    public void changeSpeed(int changeSpeed) {
        // 변경할 속도를 입력 받아 현재 속도에 추가 하거나 빼기
        this.nowSpeed = nowSpeed + changeSpeed;
        System.out.println("현재 속도 : " + nowSpeed);
    }

    @Override
    public void startDriving() {
        // 운행 시작전 주유상태를 체크 하고 주유량이 10 이상이어야 운행 가능
        if (nowFuel() > 10) {
            System.out.println("운행 시작합니다.");
            nowState = "일반";
        } else System.out.println("주유량을 확인해 주세요.");
    }

    @Override
    public void addFarePerDistance() {
        if (toDestinationDistance > basicDistance) {
            fare = baseFare + farePerDistance * (toDestinationDistance - basicDistance); //기본요금에 거리당 요금 추가
        } else fare = baseFare;
    }

    @Override
    public void payment() {
        System.out.println("최종 요금 : " + fare + "원 입니다.");
        nowState = "일반";
    }
}

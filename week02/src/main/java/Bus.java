import java.util.Scanner;

public class Bus extends Vehicle implements changeState,driving {
    int maxPassenger;
    int nowPassenger;
    int fee;
    String busNum;

    public Bus() {
        this.busNum = vehicleNum;
        this.nowState = "운행";
        this.maxPassenger = 100;
        this.nowPassenger = 0;
        this.fee = 1000;
    }

    @Override
    public void takePassenger() {           //scanner 인원 받자
        System.out.println("탑승인원 :");
        Scanner sc = new Scanner(System.in);
        int takePassenger = sc.nextInt();
        // 승객 탑승은 '최대 승객수'이하까지 가능하며 '운행 중'인 상태의 버스만 가능
        if (nowState.equals("운행 중")) {
            if (nowPassenger + takePassenger > maxPassenger) {
                System.out.println("다 못 탑니다.");
                this.nowPassenger = maxPassenger;
            } else {
                // 탑승시 현재 승객수가 증가
                nowPassenger += takePassenger;
                System.out.println("현재 탑승 인원 : " + nowPassenger);
            }
        } else
            System.out.println("아직 탑승 못 합니다.");
    }

    @Override
    public void changeSpeed(int changeSpeed) {
        // 주유 상태를 체크하고 주유량이 10 이상이어야 운행
        if (nowFuel() < 10) {
            System.out.println("주유량을 확인해 주세요");
            nowState = "차고지 행";
            return;
        }
        // 변경할 속도를 입력 받아 현재 속도에 추가 하거나 빼기
        this.nowSpeed += changeSpeed;
        System.out.println("현재 속도 : " + nowSpeed);
    }

    @Override
    public void startDriving() {
        nowState = "운행 중";
    }

    @Override
    public void changeState() {
        //운행 종료, 연료가 0인 상태일때 차고지행
        if (fuelVolume <= 0 || nowState.equals("운행 종료")) {
            nowState = "차고지 행";
        }
        //연료가 10이하 "주유필요" 출력
        if (fuelVolume < 10) {
            System.out.println("주유가 필요하다");
        }
    }

    @Override
    public void driving() {
        fuelVolume -= 10;
        System.out.println("현재 주유량 : " + fuelVolume);
    }
}

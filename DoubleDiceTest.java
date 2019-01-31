package e_OOP;

public class DoubleDiceTest {
	public static void main(String[] args) {
		//throwDice호출하면 총 이동거리를 반환(2, 4나오면 6칸 이동)
		//반환은 최종 이동거리 한 번만
		DoubleDice dd = new DoubleDice();
		int result = dd.throwDice();
		System.out.println(result);

	}
}

class DoubleDice{
	//전역변수 없음
	//메서드는 하나만
	//재귀호출을 이용해서
	//메서드명은 throwDice
	//인스턴스 메서드
	//던지고 비교하고 여기서 전부 다
	//랜덤값이 나왔을 때는 뭐가 나왔나 한번쯤 보여주기
	int throwDice(){
		
			int distance = 0;
			int dice1 = (int)(Math.random()*6+1);
			int dice2 = (int)(Math.random()*6+1);
			
			if(dice1!=dice2){
				System.out.println("["+dice1+","+dice2+"]");
			} else if(dice1==dice2){
				System.out.println("["+dice1+","+dice2+"]");
				distance = throwDice();
			}
			distance += (dice1 + dice2);
			return distance;
			

		
		
	}
	
}
package e_OOP;

public class DoubleDiceTest {
	public static void main(String[] args) {
		
		DoubleDice dd = new DoubleDice();
		int result = dd.throwDice();
		System.out.println(result);

	}
}

class DoubleDice{
	
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
			
			/*
			굳이 덧붙일 거 없는 코드
			if(one == two){
				result += throdDice();
			}
			*/
		
	}
	
}
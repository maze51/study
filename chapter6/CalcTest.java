package e_OOP;
import java.util.*;

public class CalcTest {
	public static void main(String[] args) {
		// 1. 숫자를 입력받는다.
//		Scanner scn = new Scanner(System.in);
//		System.out.print("숫자를 입력해주세요.>");
//		int number = scn.nextInt();
		
		// 2. 문자열을 입력받는다(부호).
//		Scanner scn2 = new Scanner(System.in);
//		System.out.print("부호를 입력해주세요.>");
//		String s = scn2.next();
		
		// 3. 숫자를 입력받는다.
//		Scanner scn3 = new Scanner(System.in);
//		System.out.print("숫자를 입력해주세요.>");
//		int number2 = scn3.nextInt();
		
		// 4. 입력받은 부호가 +이면 add메서드 -이면 sub메서드 *이면 multiply메서드 /이면 divide메서드
		//    를 호출하여 연산결과를 출력해주세요
//		Calc k = new Calc();
//		if(s.equals("+")){
//			int result = k.add(number, number2);
//			System.out.println(result);
//		} else if(s.equals("-")){
//			int result = k.sub(number, number2);
//			System.out.println(result);
//		} else if(s.equals("*")){
//			int result = k.multiply(number, number2);
//			System.out.println(result);
//		} else if(s.equals("/")){
//			int result = k.divide(number, number2);
//			System.out.println(result);
//		}
		
		// 5. 1~4번을 무한 반복하게 만들어주고 2에서 입력한 부호가 사칙연산이 아닌 경우 연산을 종료해주세요
		do{
			
			Scanner scn = new Scanner(System.in);
			System.out.print("숫자를 입력해주세요.> ");
			int number = scn.nextInt();
			
			Scanner scn2 = new Scanner(System.in);
			System.out.print("부호를 입력해주세요.> ");
			String s = scn2.next();
			
			if(!(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))){
				System.out.println("연산을 종료합니다.");
				break;
			}
			
			Scanner scn3 = new Scanner(System.in);
			System.out.print("숫자를 입력해주세요.> ");
			int number2 = scn3.nextInt();
			
			Calc k = new Calc();
			if(s.equals("+")){
				int result = k.add(number, number2);
				System.out.println("연산결과는 "+result+"입니다");
			} else if(s.equals("-")){
				int result = k.sub(number, number2);
				System.out.println("연산결과는 "+result+"입니다");
			} else if(s.equals("*")){
				int result = k.multiply(number, number2);
				System.out.println("연산결과는 "+result+"입니다");
			} else if(s.equals("/")){
				int result = k.divide(number, number2);
				System.out.println("연산결과는 "+result+"입니다");
			}
			
		}while(true);
		
		
		
		
		
		// (숫자부호숫자 - 다음부터는 부호숫자부호숫자. C누르면 0으로. 이외의 수를 누르면 종료되도록)
		
		// 메서드 개수는 제한없음
	}
}


class Calc{ // 계산기 - 사칙연산만
	// 메서드 네 개
	
	int add(int a, int b){
		int result = a + b;
		return result;
	}
	
	int sub(int a, int b){
		int result = a - b;
		return result;
	}
	
	int multiply(int a, int b){
		int result = a * b;
		return result;
	}
	
	int divide(int a, int b){
		int result = a / b;
		return result;
	}
}
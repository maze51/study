package e_OOP;

public class PrintCall {
	public static void main(String[] args) {
		
		CallTest first = new CallTest();
		int r = first.add();
		System.out.println(r);
		
		int r2 = first.add(5);
		System.out.println(r2);
		
		long r3 = first.add(10L);		// 여기서 int값을 넣으면 2번으로 간다. 반드시 타입에 맞는 값을 써야.
		System.out.println(r3);
		
		int r4 = first.add(20, 20);
		System.out.println(r4);
		// 5번 x
		long r6 = first.add(100L, 100L);
		System.out.println(r6);
		
		long r7 = first.add(50, 500L);
		System.out.println(r7);
		
		
	}
}

class CallTest{
	
	int a1 = 3;
	int b1 = 5;
	
	long a2 = 6L;
	long b2 = 8L;
	
	//1. a1과 b1의 합을 반환하는 메서드를 만들어주세요 add		하나 코딩하고 하나 호출해서 결과값 확인
	int add(){
		int resulta = a1 + b1;
		return resulta;
	}
	
	//2. int형 매개변수 하나를 받아서 b1과의 합계를 반환하는 메서드를 만들어주세요 add
	int add(int a){
		int resultb = a + b1;
		return resultb;
	}
	
	//3. long형 매개변수 하나와 a2의 합계를 반환하는 메서드를 만들어주세요 add
	long add(long a){
		long resultc = a + a2;
		return resultc;
	}
	
	//4. int형 매개변수 두개를 받아서 a1, b1 그리고 매개변수 두개의 합계를 반환하는 메서드를 만들어주세요 add
	int add(int a, int b){
		int resultd = a + b + a1 + b1;
		return resultd; 
	}
	
	//5. int형 매개변수 두개를 받아서 a2, b2 그리고 매개변수 두개의 합계를 반환하는 메서드를 만들어주세요 add
//	long add(int a, int b){
//		long resulte = a + b + a2 + b2;
//		return resulte;
//	}
//	메서드 선언부에서 리턴타입은 메서드 오버로딩과 상관없다. 리턴타입 빼면 매개변수 개수와 타입이 같다.
//	그러므로 4, 5번 메서드는 같은 메서드
	
	//6. long형 매개변수 두개를 받아서 매개변수의 합을 반환하는 메서드를 만들어주세요 add
	long add(long a2, long b2){
		long resultf = a2 + b2;	//지역변수라 전역변수와 같은 이름을 쓸 수 있다. 호출하면? 메서드 안에 있는 놈-지역변수-이 우선.
		return resultf;
	}
	
	//7. int형 매개변수 하나와 long형 매개변수 하나를 받아서 그 합을 반환하는 메서드를 만들어주세요 add
	long add(int a, long b){
		long resultg = a + b;
		return resultg;
	}
	
	
	
}



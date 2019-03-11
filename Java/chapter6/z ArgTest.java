package argumentTest;
/*
	가변형 인수 ==> 메서드의 매개변수 개수가 실행될 때마다 다를 수 있을 때 사용한다.

*/

public class ArgTest {
	// 메서드 호출시마다 데이터 개수가 달라진다면?
	// 배열을 이용한 가변형 인수 구현 메서드		(or 컬렉션 사용)
	
	public int test(int[] a){
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}
	
	// 가변형 인수를 이용한 메서드
	public int sumArg(int...a){	// 가변형 인수 형식) 자료형 이름...변수명
		// 이 가변형 변수는 메서드 안에서는 "배열"로 처리된다.
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}
	
	// 주의) 일반 변수와 가변형 변수를 같이 사용할 경우에는 가변형 변수를 맨 뒤에 배치해야 한다.
	
	/*
	public void testArg(int...num, int num2){
		
	}
	*/
	public void testArg (int num2, int...num){ 
		// 순서를 바꾸면 오류X. 왜? 앞이 가변형이면 몇 개를 앞에 넣고 뒤쪽에 하나 넣을지 판정불가.
		// 반면 아래는 맨 앞 하나를 앞에 넣고, 나머지를 뒤에 몰아주면 그만
	}
	
	public static void main(String[] args) {
		ArgTest arg = new ArgTest();
		int[] data = {10, 20, 30}; // 1. 배열을 미리 만들고 데이터도 초기화해서 넣어준다
		
		int result = arg.test(data);
		System.out.println("result = " + result);
		
		result = arg.test(new int[] {100,200,300,400,500}); // 2. 따로 선언하지 않고 바로 넣는다. 이때는 new int[]를 생략할 수 없다
		System.out.println("result = " + result);
		
		//int result = arg.test(1,2,3,4); // 이렇게만 넣어도 잘 돌아가게 하고 싶다면? 가변형 인수를 이용한 메서드를 만들어 활용하면 된다
		int result2 = arg.sumArg(1,2,3,4);
		System.out.println("result2 = " + result2);
		
		result2 = arg.sumArg(10,20,30,40,50,60,70,80,90,100);
		System.out.println("result2 = " + result2);
		
		
		
		
		
	}

}

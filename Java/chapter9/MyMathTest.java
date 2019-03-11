package i_javaLang;
import static java.lang.Math.*; // 모든 Math멤버들. 이렇게 써주면 Math.없이도 사용가능. 단 클래스메서드만

public class MyMathTest {
	public static void main(String[] args) {


		double d = 25.123114;
		
		// 반올림
		System.out.println(round(d));
		
		// 올림
		System.out.println(ceil(d));
		
		// 버림
		System.out.println(floor(d));
		
		// 제곱
		System.out.println(pow(3,4));
		
		// 루트
		System.out.println(sqrt(16));
		
		//1. 포인트2 객체 두개를 만드세요
		
		Point2 p1 = new Point2(1,1);
		Point2 p2 = new Point2(2,2);
		
		//2. Point2클래스에 getDistance메서드를 만든 후
		//	 인자값으로 넘겨준 Point2와의 거리를 반환해주세요. 소수점 셋째 자리에서 반올림해서 두번째 자리까지 나타내도록
		
		
		System.out.println(p1.getDistance(p2));
		
		
		
		
	}
}


class Point2 {
	int x;
	int y;
	
	public Point2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	double getDistance(Point2 p1){ // 다음 점만 가져오면 두 점 사이의 거리를 구할 수 있다. 그래서 매개변수가 1개
		double distance = sqrt(pow(p1.x-x,2) + pow(p1.y-y,2));
		float result = round(distance*100)/100.0f;
		return result;
	}
	
	
	
	
	
	
	
}
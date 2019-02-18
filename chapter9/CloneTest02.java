package i_javaLang;

import java.util.Arrays;

public class CloneTest02 {
	public static void main(String[] args) {
		Point[] arr = {new Point(100, 100),new Point(200, 200),new Point(200, 100)};
		Point[] arrClone = arr.clone();
		
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(arrClone));
		
		arr[0].x = 10; // 안의 요소를 하나하나 다 하는 게 아니라, 변수가 가진 값만을 복사하기 때문에 얕은 복사.
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(arrClone));
		
	}
}


class Point {
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
}
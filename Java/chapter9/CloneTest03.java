package i_javaLang;

public class CloneTest03 {
	public static void main(String[] args) {
		Circle cr = new Circle();
//		Circle c2 = (Circle)(cr.shallowCopy());
		Circle c2 = (Circle)(cr.deepCopy());
		System.out.println(cr);
		System.out.println(c2);
		
		cr.r = 20; // 값만 복사하기 때문에 안 바뀜
		cr.p.x = 3; // 주소를 복사하기 때문에 같이 바뀜
		cr.p.y = 5;
		System.out.println(cr);
		System.out.println(c2);
	}
}

class Circle implements Cloneable {
	//1. 점 p, 반지름 r
	Point p;
	int r;
	//2. 매개변수가 두개인 생성자
	Circle(Point p, int r){
		this.p = p;
		this.r = r;
	}
	//3. 기본 생성자
	// 단, 매개변수가 두개인 생성자를 이용하여 100,100 200으로 
	Circle(){
		this(new Point(100,100), 200);
	}
	@Override
	public String toString() {
		return "Circle [p=" + p + ", r=" + r + "]";
	}
	
//	protected Object shallowCopy() {
//		Object obj=null;
//		try {
//			obj = super.clone();
//		} catch (CloneNotSupportedException e) {
//			e.printStackTrace();
//		}
//		return obj;
//	}
	protected Object deepCopy() {
		Object obj=null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		//주소는 다르게, 값은 들고가도록 - Point만 갈아끼우면 해결된다
//		Point p1 = new Point(p.x,p.y);
//		((Circle)obj).p	= p1;
		((Circle)obj).p	= new Point(p.x,p.y);
		
		return obj;
	}
	
}

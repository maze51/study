package f_OOP2;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;


public class DrawShape extends Frame{
	public static void main(String[] args) {
		String str = "가";
		DrawShape ds = new DrawShape(str); // 이런식으로 생성자 활용이 가능
		
		
	}
	
		//ds.paint(new Graphics()); - 자동 생성됨
	
	@Override
	public void paint(Graphics g) {
		//1. Circle객체를 만들어 주세요
		//   단 매개변수가 두개인 생성자를 이용해주세요
		Point p1 = new Point(100,100);
		Circle c1 = new Circle(p1,100);
		//2. g.drawOval()을 이용하여 원을 그려주세요
		g.drawOval(c1.center.x, c1.center.y, c1.r*2, c1.r*2);
		//3. Triangle객체를 만들어주세요
		//   단, 매개변수가 하나인 생성자를 이용해주세요
		//   점의 좌표는 100,100 200,200 100,200
		Point p2 = new Point(100,100); // p2는? paint메서드의 지역변수
		Point p3 = new Point(200,200);
		Point p4 = new Point(100,200);
		Point[] pa = new Point[3]; // 방을 다 채우려면? 우선 넣을 포인트 세 개가 필요. 위에 만들어줌.
		pa[0] = p2;
		pa[1] = p3;
		pa[2] = p4;
		
		Triangle tri = new Triangle(pa);
		
//		Triangle tg = new Triangle(p2,p3,p4); // 매개변수 세개짜리로 객체를 만든다면? 이런 식으로 가능. 생성자는 만들어 놓고 이런 식으로 사용하면 됨
		
		//4. g.drawLine()3개를 이용하여 삼각형을 그려주세요
		
		g.drawLine(tri.p[0].x, tri.p[0].y, tri.p[1].x, tri.p[1].y); // tri주소 p밖에 없고 생성자 2개. 처음에는 p=null		pa의 주소. 방 3개 [0]번방에 pa[0]의 주소. x=100, y=100 [1] [2]
		g.drawLine(tri.p[0].x, tri.p[0].y, tri.p[2].x, tri.p[2].y);
		g.drawLine(tri.p[1].x, tri.p[1].y, tri.p[2].x, tri.p[2].y);
		//5. JVM그려오세요
		//   Frame이나 Graphics는 사용하는 것만 적으면 됨
	}
	
	public DrawShape() {
		super("타이틀명"); // 생성자 내에서 부모에 있는 생성자 호출 - 여기서는 Frame의 생성자. Frame의 타이틀을 바꿔준다
		setSize(500, 500);
		setVisible(true);
		setBackground(Color.MAGENTA);
		setResizable(false);
		
	}
	
	 DrawShape(String title){
		super(title);
		setSize(500, 500);
		setVisible(true);
		setBackground(Color.MAGENTA);
	}
}
/**
 * 점을 관리하기 위한 클래스
 * @author PC10
 * (클래스, 메서드에는 독주석, 변수에는 한줄주석)
 */

class Point{
	int x; // 점의 x좌표
	int y; // 점의 y좌표
	Point(){
		this(0,0); // 기본좌표를 바꾸고 싶을 때 사용
	}
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Circle{
	//1. 점 하나를 저장할 수 있는 변수를 선언해주세요 center
	Point center;
	//2. 정수값 반지름을 저장할 수 있는 변수를 선언해주세요 r
	int r;
	//3. 매개변수가 두개인 생성자를 만들어주세요
	//   넘어온 인자값으로 center와 r을 초기화해주세요
	Circle(Point center, int r){
		this.center = center;
		this.r = r;
	}
	//4. 기본생성자를 만들어주세요
	//   단, 매개변수가 두개인 생성자를 이용하여 점은 100,100 반지름은 200으로 만들어주세요
	Circle(){
		this(new Point(100,100), 200); // Point의 주소를 줄 방법은? 생성해서 주는 것 
		
	}
	
	
}

class Triangle{
	//1. 점 3개를 저장할 수 있는 변수를 선언해주세요 p
	Point[] p;
	//2. 매개변수가 하나인 생성자를 만들어주세요
	//   넘어온 인자값으로 p를 초기화해주세요
	
	Triangle(Point[] p){
		this.p = p;
	}
	
	//3. 매개변수가 세개인 생성자를 만들어주세요
	//   넘어온 인자값으로 p를 초기화해주세요
	Triangle(Point center1, Point center2, Point center3){
		p = new Point[3]; // 세 매개변수를 받을 배열의 크기 지정
		p[0] = center1; // p의 0번방을 첫번째 매개변수(첫번째 점)로 초기화		center1의 주소를 [0]에,...
		p[1] = center2;
		p[2] = center3;
		}
	}
	

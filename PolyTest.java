package f_OOP2;

public class PolyTest {
	public static void main(String[] args) {
		//1. FireCar 3대를 저장할 수 있는 변수 fc를 선언하고 생성해주세요
//		FireCar[] fc = new FireCar[3];
//		fc[0] = new FireCar();
//		fc[1] = new FireCar();
//		fc[2] = new FireCar();
		
		FireCar fc1 = new FireCar();
		FireCar fc2 = new FireCar();
		FireCar fc3 = new FireCar();
		
		FireCar[] fc = new FireCar[]{fc1, fc2, fc3};
		//2. PoliceCar 2대를 저장할 수 있는 변수 pc를 선언하고 생성해주세요
//		PoliceCar[] pc = new PoliceCar[2];
//		pc[0] = new PoliceCar();
//		pc[1] = new PoliceCar();
		
		PoliceCar pc1 = new PoliceCar();
		PoliceCar pc2 = new PoliceCar();
		
		PoliceCar[] pc = new PoliceCar[]{pc1, pc2};
		
		//3. Ambulance 4대를 저장할 수 있는 변수 ab를 선언하고 생성해주세요
//		Ambulance[] ab = new Ambulance[4];
//		ab[0] = new Ambulance();
//		ab[1] = new Ambulance();
//		ab[2] = new Ambulance();
//		ab[3] = new Ambulance();
		Ambulance ab1 = new Ambulance();
		Ambulance ab2 = new Ambulance();
		Ambulance ab3 = new Ambulance();
		Ambulance ab4 = new Ambulance();
		
		Ambulance[] ab = new Ambulance[]{ab1,ab2,ab3,ab4};
		
		
		Car[] c = new Car[]{fc1,fc2,fc3,pc1,pc2,ab1,ab2,ab3,ab4}; // up-casting		작은 타입은 큰 타입에 바로 넣을 수 있다.
		Car cc = fc1; // 이것도 up-casting
		
		FireCar fc5 = (FireCar)c[2]; // c[2]는? 인스턴스(fc3)의 주소를 갖고 있을 뿐. new해서 만든 게 아니라서. '주소는 가능하지만 인스턴스 자체를 바꿀 순 없다'
		// down-casting		큰 타입을 작은 타입에 그냥 넣을 수 없다. 형변환이 필요
		
		FireCar fc7 = (FireCar) new Car(); // 문법상으로는 문제 없으나 실행불가. 왜? 자손타입의 참조변수로 부모타입의 인스턴스를 참조할 수 없다.
		
	}
}

class Car{
	int door;
	int wheel;
	
	void drive(){
		System.out.println("달려");
	}
	
	void stop(){
		System.out.println("멈춰");
	}
	
	void fix(Car c){ // 매개변수는 = new FireCar()		매개변수의 다형성
		System.out.println();
	}
	
}

class FireCar extends Car{
	void water(){
		System.out.println("물뿌려");
	}
}

class PoliceCar extends Car{
	void lock(){
		System.out.println("못나가");
	}
}

class Ambulance extends Car{
	void aed(){
		System.out.println("살아나라");
	}
}
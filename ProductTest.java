package f_OOP2;

import java.util.Vector;

public class ProductTest {
	public static void main(String[] args) {
		Tv t1 = new Tv("LG Curved", 800);
		CoffeeMachine cf = new CoffeeMachine("네스프레소", 80);
		BodyFriend bf = new BodyFriend("바디프렌드", 700);
		
		Buyer b = new Buyer("박서경", 10000);
		
		b.buy(t1);
		b.buy(cf);
		b.buy(bf);
		
		System.out.println(b.item);
		b.refund(t1);
		b.summary();
	}
}

class Product{
	String name;
	int price;
	int mileage;
	
	
	
	Product(String name, int price){
		this.name = name;
		this.price = price;
		mileage = price/100;
	}
	
}

class Tv extends Product{
	Tv(String name, int price){
		super(name, price);
	}

	@Override
	public String toString() {
		return "Tv";
	}
	
	
}
class BodyFriend extends Product{
	BodyFriend(String name, int price){
		super(name, price);
	}

	@Override
	public String toString() {
		return "BodyFriend";
	}
}


class CoffeeMachine extends Product{
	CoffeeMachine(String name, int price){
		super(name, price);
	}

	@Override
	public String toString() {
		return "CoffeeMachine";
	}
}

class Buyer{
	String name;
	int money;
	int mileage;
	
	Vector item = new Vector(); // 구매내역 저장할 곳.  저장타입 Object 
	
	public Buyer(String name, int money) {
		this.name = name;
		this.money = money;
	}
	
	void buy(Product p){ // 매개변수의 다형성이 있으므로 하나만 딱 만들어 놓으면 된다
		if(money < p.price){
			System.out.println("나가");
			return;	// 반환타입이 void일 때 return을 이렇게 활용할 수 있다
		}
		
		money -= p.price;
		mileage += p.mileage;
		item.add(p);
		System.out.println("아이고 고객님~ "+ p +"를 구매해주셔서 감사합니다."); // 참조변수의 타입에 영향받지 않는 것은 인스턴스 메서드 뿐
		// Tv객체에서 넘어옴. 오버라이드된 toString()이 있음. toString()을 Tv것을 쓸 수밖에 없다. 때문에 Tv가 찍힌다.
		
		
	}
	
	// refund 반품 메서드 만들기. 내가 산 것만 반품할 수 있어야. 반품할 물건이 없으면 반품과정 시작 불가.
	void refund(Product p){
//		if(item.isEmpty()==true){
//			return;
//		}
		for(int i=0;i<item.size();i++){
			if((Product)item.get(i)==p){
				item.remove(i);
				money += p.price;
				mileage -= p.mileage;
			}
			
		} // end of for
		return;
	} // end of refund
	// summary 영수증.
	void summary(){
		int a = 0;
		System.out.println("\t영   수   증");
		System.out.println();
		if(item.isEmpty() == true) {
			System.out.println("구매하신 물품이 없습니다.\n");
			System.out.println("고객님의 남은 돈은 "+money+"만원이고");
			System.out.println("마일리지는 "+mileage+"만점입니다.");
			System.out.println("이용해 주셔서 감사합니다.");
		} else {
			System.out.println("구매물품");
			for(int i=0;i<item.size();i++){
				Product m = (Product)item.get(i);
				System.out.println(m.name+"\t"+"\t"+m.price);
				a+=m.price;
			}
			System.out.println("총합"+"\t"+"\t"+a);
			System.out.println("고객님의 남은 돈은 "+money+"만원이고");
			System.out.println("마일리지는 "+mileage+"만점입니다.");
			System.out.println("이용해 주셔서 감사합니다.");
		}
	}
	
	
	/*
			영  수  증
			
		구매물품 (없으면 "구매하신 물품이 없습니다." 남은 돈/마일리지는 예전것 그대로)
		  Tv    			800만원
		  CoffeeMachine		70만원
		  BodyFriend		700만원
		  총합				1570만원
		  
	고객님의 남은 돈은 8430만원이고
			마일리지는 157만점 입니다.
	이용해 주셔서 감사합니다.
	 */
	
}
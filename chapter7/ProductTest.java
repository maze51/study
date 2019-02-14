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
		System.out.println();
		b.refund(t1);
		System.out.println();
		b.summary();
		
		Buyers bs = new Buyers();
		bs.list();
		System.out.println(bs.returnName().get(1).money);
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
	
	Vector<Product> item = new Vector<Product>(); // 구매내역 저장할 곳.  저장타입 Object	<> 제네릭. 타입이라고 생각하기 
	
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
	
	// refund 반품		내가 산 것만 반품할 수 있어야. 반품할 물건이 없으면 반품과정 시작 불가.
	void refund(Product p){
		// 1. 물건을 가지고 있는지 확인
		if(item.isEmpty()) {
			System.out.println("구매하신 물품이 존재하지 않습니다.");
			return;
		}
		// 2. 물건을 반품
		if(item.remove(p)){ // 물품이 삭제됐으면 지우고 true 물품이 없어서 삭제되지 않았다면 안지우고 false
			//반품성공
			money += p.price;
			mileage -= p.mileage;
			System.out.println(p+"가 반품되었습니다");
		} else {
			//구매 물품 중 반품할 물품이 없음
			System.out.println("함마가져와라");
		} // 메서드를 알면 이쪽이 더 쉽다
	}
	// 다른방법
//	for(int i=0;i<item.size();i++) {
//		if(p == item.get(i)){
//			item.remove(i); // 인덱스 삭제. 삭제된 녀석의 객체를 다시 반환. object 타입.
//			money += p.price;
//			mileage -= p.mileage;
//			System.out.println(p+"가 반품되었습니다");
//			return;
//		} else {
//			System.out.println("함마가져와라");
//		}
//	} // 0번째가 삭제되면 뒷번호들을 하나씩 땡겨서 배열에 배치. 때문에 한단계 건너뛰어서 지워짐. return을 넣어줘야
	
	
	
//	void refund(Product p){
//		if(item.isEmpty()==true){
//			System.out.println("반품할 물건이 없습니다.");
//			return;
//		}
//		for(int i=0;i<item.size();i++){
//			if((Product)item.get(i)==p){
//				item.remove(i);
//				money += p.price;
//				mileage -= p.mileage;
//				System.out.println(p+"가 반품되었습니다");
//			}
//			
//		} // end of for
//		return;
//	} // end of refund
	
	// summary 영수증
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
				Product m = (Product)item.get(i); // .price가 안나오는 이유? cast연산자보다 .의 우선순위가 더 높다. 그래서 price가 없는 것
				//Product m = ((Product)item.get(i)).price 하면 나온다.
				//<>붙여주면 get의 반환타입이 정해준 타입으로 바뀜
				System.out.println(m.name+"\t"+"\t"+m.price);
				a+=m.price;
			}
			System.out.println("총합"+"\t"+"\t"+a);
			System.out.println("고객님의 남은 돈은 "+money+"만원이고");
			System.out.println("마일리지는 "+mileage+"만점입니다.");
			System.out.println("이용해 주셔서 감사합니다.");
		}
	}
}

class Buyers{ // 사람 추가
	String name;
	int money;
	
	Vector<Buyer> buyer = new Vector<Buyer>(); // 이러면 Buyer타입만 넣을 수 있다
	
	{
		Buyer b1 = new Buyer("이씨", 100);
		buyer.add(b1);
		
		Buyer b2 = new Buyer("김씨", 100);
		buyer.add(b2);
		
		Buyer b3 = new Buyer("박씨", 100);
		buyer.add(b3);
		
		Buyer b4 = new Buyer("이영씨", 200);
		buyer.add(b4);
	}
	
	// 사람 추가 메서드
	boolean addBuyer(Buyer i){ // 사람을 받기, 이름&돈을 받기
		boolean result = buyer.add(i);
		System.out.println(i+"님이 회원목록에 추가되었습니다.");
		return result; // 인서트됐으면 성공했다고 알려주고, 안됐으면 실패했다고 알려주기. 그래서 boolean을 쓰는 것
	}

	// 사람 삭제하는 메서드. 이름을 받아 해당번째를 삭제
//	void remove(Buyer i){ // 사람을 받기, 이름만 받기
//		for(int j=0;j<buyer.size();j++){
//			if(buyer.get(j)==i){
//				buyer.remove(i);
//				System.out.println(i+"님이 회원목록에서 제거되었습니다.");
//				return;
//			}
//		}
//		
//	}
	// 삭제메서드
	Buyer removeBuyer(String name){
		Buyer b = null;
		for(int i=0;i<buyer.size();i++){
			if(name == buyer.get(i).name){ // ==보다 .equals를 이용하는 것이 좋다.
				b = buyer.remove(i); // 삭제가 정상적으로 됐다고 알려주기 위한 Buyer타입 반환값.
			}
		}
		return b; // 그런 사람 없으면 null값을 반환함. 그러면? 위에서 null값을 받아 "그런 사람 없음"이라고 찍어주면 OK
	}
	
	// 변경하는 메서드. 돈 충전. 이름을 찾아
//	void change(Buyer h, int i){
//		for(int j=0;j<buyer.size();j++){
//			if(buyer.get(j)==h){
//				buyer.get(j).money += i;
//				System.out.println(i+"만원이 충전되었습니다.");
//			}
//		}
//	}
	//변경메서드
	void chargeMoney(String name, int money){
		for(int i=0;i<buyer.size();i++){
			if(name.equals(buyer.get(i).name)){	// 벡터의 get()의 요소에 접근하는 방법 숙지!
				// 앞에는 null이 아닐 확률이 높은 녀석(여기서는 name). 뒤에는 null일지 아닐지 상관없다.
				// 왜? 앞이 null이면 equals 실행하기도 전에 바로 터진다. 뒤가 null이면 비교하고 같은 게 없다고 뜬다. 
				buyer.get(i).money += money;
			}
		}
	}
	
	// 사람목록을 출력하는 메서드
	// 이름   가진돈   마일리지
	void list(){
		System.out.println("이름\t가진돈\t마일리지");
		for(int i=0;i<buyer.size();i++){
			Buyer k = buyer.get(i);
			System.out.println(k.name+"\t"+k.money+"\t"+k.mileage);
		}
	}
	
	// 검색결과. 이씨인 사람'들'만 모아 반환(출력 아님)하는 메서드
	Vector<Buyer> returnName(){
		Vector<Buyer> lee = new Vector<Buyer>();
		for(int i=0;i<buyer.size();i++){
			if(buyer.get(i).name.charAt(0)=='이'){
				lee.add(buyer.get(i));
			}
		}
		return lee;
	}// 내부에서 이씨들 저장할 Vector선언, 반환타입은 Buyer타입의 Vector
	
	// 끝나면 buy, refund, summary 전부 buyers로 옮겨서 다시 세팅 (buy는 매개변수가 두 개로 늘어남)
	
	
	
	
	
	
	
	
	
}
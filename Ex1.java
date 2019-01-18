
public class Ex1 {
	public static void main(String[] args) {
		
		int num = -375982345;
		System.out.println(num > 0 ? "양수" : num < 0 ? "음수" : 0);
				
		
		int x = 2;
		int y = 5;
		char c = 'A';
		
		System.out.println(1 + x << 33);
		System.out.println(y >= 5 || x < 0 && x > 2);
		System.out.println(y += 10 - x++);
		System.out.println(x+=2);
		System.out.println( !('A' <= c && c <='Z') );
		System.out.println('C'-c);
		System.out.println('5'-'0');
		System.out.println(c+1);
		System.out.println(++c);
		System.out.println(c++);
		System.out.println(c);
		
		
		int uu = 10;
		int hh = 50;
//		System.out.println("--절취선--"+ ++uu);		// 11
//		System.out.println(hh += 1000);			// 1050
//		System.out.println(uu+hh);				// 1061
//		System.out.println("test:"+ ++uu);		// 12
//		System.out.println(hh += 1000);			// 2050
//		System.out.println(uu+hh);				// 2062
//		System.out.println("HH값:  "+hh);		// 2050
//		System.out.println(hh -= 1000 - hh++);	// 2050 -= -1050	hh = 2050 - -1050	3100
//		System.out.println("HH값:  "+hh);		// 3100
//		System.out.println("test:"+ hh + 100);	// String과 더해서 죄다 String화!
//		System.out.println("test:"+ uu + hh);	// 이것도!
				
	}

}

package i_javaLang;

public class StringBufferTest01 {
	public static void main(String[] args) {
		
		/*
		String text = "a";
		text += "a"; 
		text += "a"; 
		text += "a"; 
		text += "a"; 
			.
			.
			.
			.
		
		String text = "a";
		text = new StringBuffer(text).append("a").toString();
		text = new StringBuffer(text).append("a").toString();
		text = new StringBuffer(text).append("a").toString();
			.
			.
			.
			.
		
		 */
		
//		long start = System.currentTimeMillis();
//		String text = "a";
//		for (int i = 0; i < 300000; i++) {
//			text += "a";
//		}
//		long end = System.currentTimeMillis();
//		System.out.println(end - start);
		
//		long start = System.currentTimeMillis();
//		StringBuffer text = new StringBuffer("a");	// 동기화를 보장한다. 때문에 속도가 느려진다
//		for (int i = 0; i < 30_000_000; i++) {
//			text.append("a");
//		}
//		long end = System.currentTimeMillis();
//		
//		System.out.println(end - start);
		
		long start = System.currentTimeMillis();
		StringBuilder text = new StringBuilder("a"); // 동기화를 보장하지 않는다(비동기방식)
		//더 빠르지만 제약조건이 있다. 사용은 StringBuffer가 훨씬 많다
		for (int i = 0; i < 30_000_000; i++) {
			text.append("a");
		}
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		
		
		
		
		
		
		
		
		
		
	}
}

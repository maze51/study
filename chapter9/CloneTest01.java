package i_javaLang;

public class CloneTest01 {
	public static void main(String[] args) {
		CloneEx ce = new CloneEx(3, 5);
		CloneEx ceCopy = (CloneEx)ce.clone();
		System.out.println(ce);
		System.out.println(ceCopy);
		
		ce.x = 10;
		System.out.println(ce); //10,5
		System.out.println(ceCopy); //3,5
	}
}

class CloneEx implements Cloneable { // 반드시 implements해야 사용할 수 있다.
	int x;
	int y;
	
	public CloneEx(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "CloneEx [x=" + x + ", y=" + y + "]";
	}
//	@Override
//		protected Object clone() throws CloneNotSupportedException {
//			return super.clone();
//		}
	@Override
		protected Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
			return obj;
		}
	
}
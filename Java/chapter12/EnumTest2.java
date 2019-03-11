package enumTest;

public class EnumTest2 {
	// 열거형 상수에 다른 값을 설정하는 방법(상수에 부가적인 값을 더 지정하고 싶다면?)
	// 데이터 값을 정해줄 경우에는 생성자를 만들어서 괄호 속의 값이 변수에 저장되도록 한다.
	public enum Season{
		봄("3월부터 5월까지"),
		여름("6월부터 8월까지"),
		가을("9월부터 11월까지"),
		겨울("12월부터 2월까지");
		
		private String span;
		
		// 생성자(괄호 속에 뭐가 들어있느냐? 만 따진다) ==> 열겨형의 생성자는 묵시적으로 private이다. 굳이 안 써도 자동으로 붙여준다
		Season(String span){
			this.span = span;
		}
		
		public String getSpan(){ // private이므로 꺼내 쓰기 위한 getter도 만들어 줘야
			return span;
		}
	}

	public static void main(String[] args) {
		
	}

}

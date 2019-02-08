package y_exam;

public class Exam_06 {
	public static void main(String[] args) {
		
		/*
		[6-1] 다음과 같은 멤버변수를 갖는 SutdaCard 클래스를 정의하시오 .
		class SutdaCard {
			int num;
			boolean isKwang;
		}
		 */
		
		/*
		[6-2] 문제 6-1에서 정의한 SutdaCard클래스에 두 개의 생성자와 info()를 추가해서 실행   
		결과와 같은 결과를 얻도록 하시오.
		
		class Exercise6_2 {
			public static void main(String args[]) {
				SutdaCard card1 = new SutdaCard(3, false);
				SutdaCard card2 = new SutdaCard();
				System.out.println(card1.info());
				System.out.println(card2.info());
			}
		}
		class SutdaCard {
			int num;
			boolean isKwang;
			
			SutdaCard(int num, boolean isKwang) {
				this.num = num;
				this.isKwang = isKwang;
			}
	
			SutdaCard() {
				this(1, true);
			}
			
			String info(){
				return = num + (isKwang ? "K" : ""); 
			}
		}
		 */
		
		/*
		[6-3] 다음과 같은 멤버변수를 갖는 Student클래스를 정의하시오.
		class Student {
			String name;
			int ban;
			int no;
			int kor;
			int eng;
			int math;
	}
		 */
		
		/*
		[6-4] 문제6-3에서 정의한 Student클래스에 다음과 같이 정의된 두 개의 메서드
				getTotal()과 getAverage()를 추가하시오.
		
		String name;
		int ban;
		int no;
		int kor;
		int eng;
		int math;
		
		int getTotal(){
			return kor + eng + math;
		}
		
		float getAverage(){
			return (int)(getTotal()/3f*10+0.5f)/10f;
		}
		 */
		
		/*
		[6-5] 다음과 같은 실행결과를 얻도록 Student클래스에 생성자와 info()를 추가하시오.
		class Student {
	
			String name;
			int ban;
			int no;
			int kor;
			int eng;
			int math;
			
			Student(String name, int ban, int no, int kor, int eng, int math){
				this.name = name;
				this.ban = ban;
				this.no = no;
				this.kor = kor;
				this.eng = eng;
				this.math = math;
			}
			
			int sum(){
				return kor+eng+math;
			}
			
			float avg(){
				return (int)(sum()/3f * 10 + 0.5f)/10f;
				
			}
			 String info(){
				return name+","+ban+","+no+","+kor+","+eng+","+math+","+sum()+","+avg();
			}
		}
		
		 */
		
		/*
		[6-6] 두 점의 거리를 계산하는 getDistance()를 완성하시오.
		static double getDistance(int x, int y, int x1, int y1) {
			return Math.sqrt((x1 - x)*(x1 - x)+(y1 - y)*(y1 - y));
		}
		
		public static void main(String args[]) {
			System.out.println(getDistance(1,1,2,2));
		}
		 */
		
		/*
		[6-7] 문제6-6에서 작성한 클래스메서드 getDistance()를 MyPoint클래스의 인스턴스메서드로 정의하시오.
		class MyPoint {
			int x;
			int y;
			
			MyPoint(int x, int y) {
				this.x = x;
				this.y = y;
			}
			
			double getDistance(int a, int b){
				return Math.sqrt((a - x)*(a - x)+(b - y)*(b - y));
			}
		}
		class Ex6_2 {
			public static void main(String args[]) {
				MyPoint p = new MyPoint(1,1);
				
				// p와 (2,2)의 거리를 구한다.
				System.out.println(p.getDistance(2,2));
			}
		}
		 */
		
		/*
		[6-8] 다음의 코드에 정의된 변수들을 종류별로 구분해서 적으시오.
		class PlayingCard {
			int kind;
			int num;
			static int width;
			static int height;
			PlayingCard(int k, int n) {
				kind = k;
				num = n;
			}
			public static void main(String args[]) {
				PlayingCard card = new PlayingCard(1,1);
			}
		}
		
		클래스변수(static변수): width, height 
		인스턴스변수: PlayingCard 클래스의 kind, num
		지역변수: PlayingCard 생성자의 매개변수 int k, n 과 main()의 지역변수 card
		 */
		
		/*
		[6-9]다음은 컴퓨터 게임의 병사(marine)를 클래스로 정의한 것이다. 이 클래스의 멤버중에 static을 붙여야 하는 것은 어떤 것들이고 그 이유는 무엇인가?(단, 모든 병사의 공격력과 방어력은 같아야 한다.)
		
		class Marine {
			int x=0, y=0; // Marine의 위치좌표(x,y)
			int hp = 60; // 현재 체력
			int weapon = 6; // 공격력
			int armor = 0; // 방어력
			void weaponUp() {
				weapon++;
			}
			void armorUp() {
				armor++;
			}
			void move(int x, int y) {
				this.x = x;
				this.y = y;
			}
		}
		
		weapon과 armor	공격력과 방어력값은 모든 병사에 동일한 값으로 적용되어야 할 수치이기 때문
		+ weaponUp(), armorUp()		클래스변수만 활용하는 메서드는 클래스메서드로 지정하는 것이 바람직
		 */
		
		/*
		[6-10] 다음 중 생성자에 대한 설명으로 옳지 않은 것은? (모두 고르시오)
			a. 모든 생성자의 이름은 클래스의 이름과 동일해야한다.
			b. 생성자는 객체를 생성하기 위한 것이다.
			c. 클래스에는 생성자가 반드시 하나 이상 있어야 한다.
			d. 생성자가 없는 클래스는 컴파일러가 기본 생성자를 추가한다.
			e. 생성자는 오버로딩 할 수 없다.
		
		b. 생성자는 객체를 초기화하기 위한 것이다. 객체의 생성은 new연산자
		e. 생성자도 오버로딩할 수 있다
		 */
		
		/*
		[6-11] 다음 중 this에 대한 설명으로 맞지 않은 것은? (모두 고르시오)
			a. 객체 자신을 가리키는 참조변수이다.
			b. 클래스 내에서라면 어디서든 사용할 수 있다.
			c. 지역변수와 인스턴스변수를 구별할 때 사용한다.
			d. 클래스 메서드 내에서는 사용할 수 없다.
		
		b. 클래스친구들에는 사용할 수 없다.
		- this는 모든 인스턴스메서드에 숨겨진 채로 존재하는 지역변수
		 */
		
		/*
		[6-12] 다음 중 오버로딩이 성립하기 위한 조건이 아닌 것은? (모두 고르시오)
			a. 메서드의 이름이 같아야 한다.
			b. 매개변수의 개수나 타입이 달라야 한다.
			c. 리턴타입이 달라야 한다.
			d. 매개변수의 이름이 달라야 한다.
			
		c. 리턴타입은 오버로딩 조건과 관계없다
		d. 매개변수의 이름은 오버로딩 조건과 관계없다
		 */
		
		/*
		[6-13] 다음 중 아래의 add메서드를 올바르게 오버로딩 한 것은? (모두 고르시오)
			long add(int a, int b) { return a+b;}
			
			a. long add(int x, int y) { return x+y;}
			b. long add(long a, long b) { return a+b;}
			c. int add(byte a, byte b) { return a+b;}
			d. int add(long a, int b) { return (int)(a+b);}
		
		b, c, d 모두 메서드의 이름이 같고 매개변수 타입이 다르다
		 */
		
		/*
		[6-14] 다음 중 초기화에 대한 설명으로 옳지 않은 것은? (모두 고르시오)
		
			a.멤버변수는 자동 초기화되므로 초기화하지 않고도 값을 참조할 수 있다.
			b.지역변수는 사용하기 전에 반드시 초기화해야 한다.
			c.초기화 블럭보다 생성자가 먼저 수행된다.
			d.명시적 초기화를 제일 우선적으로 고려해야 한다.
			e.클래스변수보다 인스턴스변수가 먼저 초기화된다.
		
		
		c. 초기화블럭이 생성자보다 먼저 수행된다
		e. 클래스변수 초기화가 먼저. 클래스변수는 클래스가 Method Area에 처음 로딩될 때 자동 초기화된다.
		 */
		
		/*
		[6-15] 다음중 인스턴스변수의 초기화 순서가 올바른 것은?
			a. 기본값-명시적초기화-초기화블럭-생성자
			b. 기본값-명시적초기화-생성자-초기화블럭
			c. 기본값-초기화블럭-명시적초기화-생성자
			d. 기본값-초기화블럭-생성자-명시적초기화
		
		a가 올바름
		 */
		
		/*
		[6-16] 다음 중 지역변수에 대한 설명으로 옳지 않은 것은? (모두 고르시오)
			a. 자동 초기화되므로 별도의 초기화가 필요없다.
			b. 지역변수가 선언된 메서드가 종료되면 지역변수도 함께 소멸된다.
			c. 매서드의 매개변수로 선언된 변수도 지역변수이다.
			d. 클래스변수나 인스턴스변수보다 메모리 부담이 적다.
			e. 힙(heap)영역에 생성되며 가비지 컬렉터에 의해 소멸된다.
			
		a. 자동 초기화되지 않는다
		e. 지역변수는 콜스택에 생성되며 메서드가 작업을 마치고 사라질 때 소멸된다
		      문제의 내용은 인스턴스변수에 대한 설명
		 */
		
		/*
		[6-17] 호출스택이 다음과 같은 상황일 때 옳지 않은 설명은? (모두 고르시오)
			println
			method1
			method2
			main
			
			a. 제일 먼저 호출스택에 저장된 것은 main메서드이다.
			b. println메서드를 제외한 나머지 메서드들은 모두 종료된 상태이다.
			c. method2메서드를 호출한 것은 main메서드이다.
			d. println메서드가 종료되면 method1메서드가 수행을 재개한다.
			e. main-method2-method1-println의 순서로 호출되었다.
			f. 현재 실행중인 메서드는 println 뿐이다.
		
		b. 종료된 것이 아니라, 작동을 멈추고 콜스택 상단의 메서드 수행이 끝나길 기다리고 있다
		 */
		
		/*
		[6-18] 다음의 코드를 컴파일하면 에러가 발생한다. 컴파일 에러가 발생하는 라인과 그 이유를 설명하시오.
		
		class MemberCall {
		int iv = 10;
		static int cv = 20;
		
		int iv2 = cv;
		static int cv2 = iv; // 라인 A
		
		static void staticMethod1() {
			System.out.println(cv);
			System.out.println(iv); // 라인 B
		}
		
		void instanceMethod1() {
			System.out.println(cv);
			System.out.println(iv); // 라인 C
		}
		
		static void staticMethod2() {
			staticMethod1();
			instanceMethod1(); // 라인 D
		}
		void instanceMethod2() {
			staticMethod1(); // 라인 E
			instanceMethod1();
			}
		}
		
		b, d에서 에러. 클래스메서드는 인스턴스변수/메서드를 불러올 수 없다
		a도 에러. 클래스변수 초기화에 객체 생성 없이 인스턴스변수를 사용할 수 없다.
		 */
		
		/*
		[6-19] 다음 코드의 실행 결과를 예측하여 적으시오.
		
		class Exercise6_19
		{
			public static void change(String str) {
				str += "456";
			}
			public static void main(String[] args)
			{
				String str = "ABC123";
				System.out.println(str);
				change(str);
				System.out.println("After change:"+str);
			}
		}
		
		abc123, After change:abc123
		abc123456이 아닌 이유? main의 지역변수 str과 change()의 지역변수 str은 이름이 같고 abc123이라는
		같은 문자를 저장하고 있다 해도 다른 변수이다. change()에서 456이 붙은 str은 change()가
		끝나면 참조변수가 사라지고, 나중에 gc에 의해 정리된다.
		
		 */
		
		/*
		[6-20] 다음과 같이 정의된 메서드를 작성하고 테스트하시오.
		
		//shuffle메서드 작성
		static int[] shuffle(int[] arr) {
			
			if(arr == null || arr.length == 0){
				return arr;
			}
			
			for(int i=0;i<arr.length;i++){
				int ran = (int)(Math.random()*arr.length);
				
			
				int tmp = arr[i];
				arr[i] = arr[ran];
				arr[ran] = tmp;
			}
			return arr;
		}
		public static void main(String[] args)
		{
				int[] original = {1,2,3,4,5,6,7,8,9};
				System.out.println(java.util.Arrays.toString(original));
				int[] result = shuffle(original);
				System.out.println(java.util.Arrays.toString(result));
			}
		}
		 */
		
		/*
		[6-21] Tv클래스를 주어진 로직대로 완성하시오. 완성한 후에 실행해서 주어진 실행결과
				와 일치하는지 확인하라.
		
		package y_exam;

		class MyTv {
			boolean isPowerOn;
			int channel;
			int volume;
			final int MAX_VOLUME = 100;
			final int MIN_VOLUME = 0;
			final int MAX_CHANNEL = 100;
			final int MIN_CHANNEL = 1;
			void turnOnOff() {
				isPowerOn = !isPowerOn;
			}
			void volumeUp() {
				if(volume<MAX_VOLUME){
					volume++;
				}
			}
			void volumeDown() {
				if(volume>MIN_VOLUME){
					volume--;
				}
			}
			void channelUp() {
				if(channel==MAX_CHANNEL){
					channel=MIN_CHANNEL;
				} else {
					channel++;
				}
			}
			void channelDown() {
				if(channel==MIN_CHANNEL){
					channel=MAX_CHANNEL;
				} else {
					channel--;
				}
			}
		} // class MyTv
		class Ex6_2 {
			public static void main(String args[]) {
				MyTv t = new MyTv();
				t.channel = 100;
				t.volume = 0;
				System.out.println("CH:"+t.channel+", VOL:"+ t.volume);
				t.channelDown();
				t.volumeDown();
				System.out.println("CH:"+t.channel+", VOL:"+ t.volume);
				t.volume = 100;
				t.channelUp();
				t.volumeUp();
				System.out.println("CH:"+t.channel+", VOL:"+ t.volume);
			}
		}
		 */
		
		/*
		[6-22] 다음과 같이 정의된 메서드를 작성하고 테스트하시오.
		class Ex6_2 {
	
			static boolean isNumber(String str){
				boolean a=false;
				if(str==null || str.equals("")){
					return a;
				} else {
					for(int i=0;i<str.length();i++){
						char ch = str.charAt(i);
						if(ch < '0' && ch > '9'){
							a=false;
						} else {
							a=true;
						}
					}
				}
				return a;
			}
			public static void main(String[] args) {
				String str = "123";
				System.out.println(str+"는 숫자입니까? "+isNumber(str));
				str = "1234o";
				System.out.println(str+"는 숫자입니까? "+isNumber(str));
			}
		}
		 */
		
		/*
		[6-23] 다음과 같이 정의된 메서드를 작성하고 테스트하시오.
		class Ex6_2{
	
			static int max(int[] arr){
				int a = 0;
				
				
				if(arr==null || arr.length==0){
					a = -999999;
				} else {
					a = arr[0];
					for(int i=1;i<arr.length;i++){
						if(arr[i]>a){
							a = arr[i];
						}
					}
				}
				return a;
			}
			
			public static void main(String[] args)
			{
				int[] data = {3,2,9,4,7};
				System.out.println(java.util.Arrays.toString(data));
				System.out.println("최대값:"+max(data));
				System.out.println("최대값:"+max(null));
				System.out.println("최대값:"+max(new int[]{})); // 크기가 0인 배열
			}
		}
		 */
		
		/*
		[6-24] 다음과 같이 정의된 메서드를 작성하고 테스트하시오.
		class Ex6_2
		{
			
			static int abs(int value){
				int result = value > 0 ? value : -value;
				return result;
			}
			
			public static void main(String[] args)
			{
				int value = 5;
				System.out.println(value+"의 절대값:"+abs(value));
				value = -10;
				System.out.println(value+"의 절대값:"+abs(value));
			}
		}
		 */
	}
}

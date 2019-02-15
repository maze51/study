package y_exam;

public class Exam_07 {
	public static void main(String[] args) {
		/*
		[7-1]
		배열 StudaCard를 적절히 초기화 하시오
			SutdaDeck() {
				int z = 0;
				for(int h=0;h<2;h++){
					for(int i=0;i<CARD_NUM/2;i++){
						cards[z] = new SutdaCard(i+1, false);
						if(z==0){
							cards[z].isKwang = true;
						} else if(z==2){
							cards[z].isKwang = true;
						} else if(z==7){
							cards[z].isKwang = true;
						}
						z++;
					} // end of for1
				} // end of for2
			} // end of 생성자
		*/
		
		/*
		[7-2]
		(1) shuffle() 메서드 작성
		void shuffle(){
			for(int i=0;i<cards.length;i++){
				int j = (int)(Math.random()*cards.length);
								
				SutdaCard tmp = cards[i];
				cards[i] = cards[j];
				cards[j] = tmp;
			}
		}
		
		(2) pick(int index) 메서드 작성
		SutdaCard pick(int index){
			return cards[index];
		}
		
		(3) pick() 메서드 작성
		SutdaCard pick(){
			int i = (int)(Math.random()*cards.length);
			return cards[i];
		}
		*/
		
		/*
		[7-3]
		오버라이딩의 정의: 상속받은 메서드의 내용을 재정의하는 것
		필요성: 자손 클래스는 조상 클래스와 비슷하면서도 다른 기능을 수행하므로 필요 시 상속받은 메서드의 내용을 바꿔 쓰는 것이 효율적이다
		*/
		
		/*
		[7-4]
		옳은 것
		a, b
		
		옳지 않은 것
		c. 접근 제어자는 조상 메서드와 같거나 넓은 범위로 변경 가능
		d. 예외는 조상 클래스의 메서드보다 많이 선언할 수 없다
		 */
		
		/*
		[7-5]
		자손 클래스의 생성자 구현부에는 부모 클래스의 생성자를 호출하는 super()가 기본적으로 포함되어 있다.
		하지만 부모 클래스의 생성자 Product에 매개변수가 설정되어 있으므로(매개변수 없는 기본 생성자가 없으므로) 오류가 발생한다
		에러를 수정하려면
		1. Tv클래스 생성자 구현부에 super(1000) 처럼 int형 인자값을 설정해 준다.
		2. Product 클래스의 기본 생성자 Product() {}를 추가해 준다.
		 */
		
		/*
		[7-6]
		자손 클래스의 생성자에서 조상 클래스의 생성자를 호출해야 하는 이유는?
		자손 클래스의 인스턴스는 조상 클래스의 멤버들을 사용할 수 있다.
		이 때 조상 클래스의 멤버들을 사용하려면 해당 멤버들의 초기화 작업이 먼저 이뤄져야 한다.
		때문에 자손 클래스의 생성자에서 조상 클래스의 생성자를 맨 먼저 호출해 초기화 작업을 수행한다.  
		 */
		
		/*
		[7-7]
		호출되는 생성자의 순서
		1. Child()
		2. Child(int x)
		3. Parent()
		4. Parent(int x)
		
		실행결과
		x=200
		 */
		
		/*
		[7-8]
		정답: a. public-protected-(default)-private
		 */
		
		/*
		[7-9]
		옳은 것
		a, b, d
		
		옳지 않은 것
		c. 메서드 - 오버라이딩을 할 수 없다
		 */
		
		/*
		[7-10]
		(1) 외부에서 접근할 수 없도록 제어자를 붙인다
		private boolean isPowerOn;
		private int channel;
		private int volume;
		
		(2) 멤버변수들의 값을 어디서나 읽고 변경할 수 있도록 getter, setter메서드 추가
		void setChannel(int channel){
			this.channel = channel;
		}
		
		int getChannel(){
			return channel;
		}
		
		void setVolume(int volume){
			this.volume = volume;
		}
		
		int getVolume(){
			return volume;
		}
		 */
		
		/*
		[7-11]
		(1) 이전 채널의 값을 저장할 인스턴스변수 prevChannel을 선언한다
		int prevChannel;
		(2) setChannel 메서드를 활용해 이전 채널의 값을 prevChannel에 저장한다
		void setChannel(int channel){
			prevChannel = this.channel;
			this.channel = channel;
		}
		(3) gotoPrevChannel()을 만들고 지역변수 i를 선언한다.
		i를 중간 저장소로 활용해 변수 channel과 prevChannel의 값을 맞바꾼다.
		void gotoPrevChannel(){
			int i = this.channel;
			this.channel = prevChannel;
			prevChannel = i;
		}
		 */
		
		/*
		[7-12]
		옳은 것
		a, b, d, e
		옳지 않은 것
		c. 지역변수에는 접근 제어자를 사용할 수 없다
		 */
		
		/*
		[7-13]
		Math 클래스는 상수와 클래스메서드만으로 구성되어 있어 굳이 인스턴스화할 의미가 없다.
		때문에 생성자의 접근 제어자를 private으로 설정해 인스턴스 생성을 사전 차단한다. 
		 */
		
		/*
		[7-14]
		변수를 상수로 변경해 한 번 값이 지정되면 변경할 수 없도록 한다.
		final int NUM;
		final boolean IS_KWANG;
		 */
		
		/*
		[7-15]
		
		 */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

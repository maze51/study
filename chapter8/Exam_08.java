package y_exam;

public class Exam_08 {
	public static void main(String[] args) {
		
		/*
		[8-1]
		예외처리의 정의: 프로그램 실행 시 발생할 수 있는 예기치 못한 예외의 발생에 대비한 코드를 작성하는 것
		예외처리의 목적: 예외의 발생으로 인한 실행 중인 프로그램의 갑작스런 비정상적 종료를 막고,
					정상 실행 상태를 유지할 수 있도록 하는 것 
		 */
		
		/*
		[8-2]
		옳은 것
		a, b, c
		
		옳지 않은 것
		d. method1 메서드가 method2 메서드를 호출한 것
		 */
		
		/*
		[8-3]
		정상적으로 오버라이딩 된 것
		a, b, c
		오버라이딩이 잘못된 것
		d, e : 오버라이딩할 때, 조상 클래스의 메서드보다 많은 예외를 선언할 수 없다
		 */
		
		/*
		[8-4]
		예외를 잘 처리한 것
		a, b, d, e, f
		예외를 잘못 처리한 것
		c. Exception을 처리하는 catch블럭은 모든 catch블럭 중 맨 마지막에 있어야 한다
		 */
		
		/*
		[8-5]
		1 
		3 
		5 
		1 
		2 
		5 
		6 
		
		1 예외가 발생하여 println(2)는 실행되지 않는다
		3 ArithmeticException은 RuntimeException의 자손이므로 
		RuntimeException r을 매개변수로 하는 catch블럭에서 처리된다
		5 catch(RuntimeException r)블럭에서 return을 만나 메서드가 종료되기 전,
		finally블럭을 수행한다 
		1 
		2 예외가 발생하지 않아 println(2)가 실행되고 finally블럭으로 넘어간다
		5 finally 블럭 수행
		6 try-catch문을 빠져나가 println(6)이 실행됨
		 */
		
		/*
		[8-6]
		3 
		5 
		
		3 method2()를 호출하여 NullPointerException이 발생하지만 method1()의
		try-catch문에서 catch할 수 없어 finally블럭만 수행된다
		5 main()의 try-catch문에서는 모든 예외를 catch할 수 있는 Exception e가
		매개변수로 지정되어 있어 catch블럭이 수행된다
		 */
		
		/*
		[8-7]
		1
		
		try문의 println(1)이 실행되고 System.exit(0)을 만나 JVM이 종료된다.
		아래의 어떤 문장도 실행되지 않는다
		 */
		
		/*
		[8-8]
		input = new Scanner(System.in).nextInt(); 행 아래에 try-catch문 삽입
		try {
			input = new Scanner(System.in).nextInt();
		} catch(Exception e) {
			System.out.println("유효하지 않은 값입니다. 다시 값을 입력해주세요.");
			continue;
		}
		 */
		
		/*
		[8-9]
		class UnsupportedFuctionException extends RuntimeException {
			private final int ERR_CODE;
			UnsupportedFuctionException(String msg, int errCode) {
				super(msg);
				ERR_CODE = errCode;
			}
			UnsupportedFuctionException(String msg) {
				this(msg, 100);
			}
			public int getErrCode() {
				return ERR_CODE;
			}
			public String getMessage() {
				return "["+getErrCode()+"]" + super.getMessage();
			}
		}
		 */
		
		/*
		[8-10]
		2
		4
		7
		
		2 예외가 catch되어 println(2)가 실행된다.
		4 println(2) 다음줄에서 throw로 예외를 다시 발생시킨다.
		finally블럭이 수행되어 4를 출력
		7 main()으로 돌아와 println(7)이 수행된다
		 */
	}
}

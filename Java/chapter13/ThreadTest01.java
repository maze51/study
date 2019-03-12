package basic;

public class ThreadTest01 {

	public static void main(String[] args) {
		// 싱글 쓰레드 프로그램
		for (int i = 1; i < 201; i++) {
			System.out.print("*");
		}
		System.out.println();
		
		for (int i = 1; i < 201; i++) {
			System.out.print("$");
		}
		System.out.println();
	}

}
/*
프로세스와 쓰레드
프로세스의 범위가 더 넓다.

프로세스: 현재 실행중인 프로그램. 독립적으로 실행된 상태. 
OS에서 실행될 환경을 받아야 한다+메모리 공간을 할당받아야.
기타 통신포트, 이미지,...등의 자원 확보가 필요하다 -> OS가 해줌

멀티태스킹: 동시에 여러 프로세스가 실행중인 상태

한 프로그램 안에서 동시에 독립적인 여러 작업이 이뤄지도록 하려면? -> 쓰레드
기본적으로 멀티태스킹과 비슷한 개념.

ppt 1.11의 원들은 쓰레드 인스턴스 자체
*/
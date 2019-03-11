package basic;

import java.util.LinkedList;

public class StackQueueTest {

	public static void main(String[] args) {
		/*
		Stack ==> 후입선출(LIFO)의 자료 구조
		Queue ==> 선입선출(FIFO)의 자료 구조
		
		Stack과 Queue는 LinkedList를 이용하여 사용할 수 있다. 때문에 LinkedList에는 Stack, Queue를 사용하기 위한 명령어가 추가되어 있다
		
		데이터와 데이터 사이의 삭제, 끼워넣기가 잦은 작업이라면 LinkedList가 보다 효율적이다
		(이유는? ArrayList내부에서는 배열로 데이터를 처리한다. 때문에 대규모 데이터에서 앞쪽 데이터가 삭제되면 그 뒤 모든 데이터의 위치가 바뀐다. 이 과정에서 속도가 느려질 여지가 있다)
		단 (ArrayList에 있는)Index가 LinkedList에는 없기 때문에 탐색 속도가 상대적으로 느려질 여지가 있다. 
		
		
		Stack에서 사용되는 명령
		1. 자료 추가 : push(추가할데이터)
		2. 자료 출력 : pop() => 자료를 꺼내온 후 꺼내온 자료를 Stack에서 지운다.
		3. 자료 확인 : peek() => 삭제없이 자료를 꺼내온다. 검색만 하고 싶을 때 사용.
		*/
		LinkedList<String> stack = new LinkedList<String>();
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("강감찬");
		stack.push("이순신");
		System.out.println("현재 Stack => " + stack);
		
		String data = stack.pop();
		System.out.println("꺼내온 자료 : " + data);
		System.out.println("꺼내온 자료 : " + stack.pop());
		System.out.println("현재 Stack => " + stack);
		
		stack.push("성춘향");
		System.out.println("현재 Stack => " + stack);
		System.out.println("꺼내온 자료 : " + stack.pop());
		System.out.println("현재 Stack => " + stack);
		System.out.println("꺼내온 자료(peek) : " + stack.peek());
		System.out.println("현재 Stack => " + stack);
		System.out.println("--------------------------------------");
		System.out.println();
		
		/*
		Queue명령
		1. 자료 입력 : offer(추가할데이터)
		2. 자료 출력 : poll() => 자료를 Queue에서 꺼내온 후 꺼내온 자료를 삭제한다.
		3. 자료 확인 : peek() Stack의 peek과 같은 기능
		 */
		LinkedList<String> queue = new LinkedList<String>();
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("강감찬");
		queue.offer("이순신");
		
		System.out.println("현재 Queue => " + queue);
		String temp = queue.poll();
		System.out.println("꺼내온 자료 : " + temp);
		System.out.println("꺼내온 자료 : " + queue.poll());
		System.out.println("현재 Queue => " + queue);
		
		queue.offer("성춘향");
		System.out.println("현재 Queue => " + queue);
		System.out.println("꺼내온 자료 : " + queue.poll());
		System.out.println("현재 Queue => " + queue);
		System.out.println("꺼내온 자료(peek) : " + queue.peek());
		System.out.println("현재 Queue => " + queue);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

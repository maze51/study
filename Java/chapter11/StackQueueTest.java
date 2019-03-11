package j_collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class StackQueueTest {
	public static void main(String[] args) {
		List<String> list1 = new Stack<String>();
//		list1.p // list의 공통 메서드만 저장하고 있다.
		
		Stack<String> list2 = new Stack<String>();
		list2.push("0");
		list2.push("1");
		list2.push("2");
		System.out.println("===================== stack ====================");
		
		while(!list2.empty()){ // item이 하나도 없으면 true를 반환한다
			System.out.println(list2.pop()); // 2,1,0 순서로 나온다. 스택 구조상
		}
		
		System.out.println("===================== queue ====================");
		Queue<String> que = new LinkedList<String>(); // 이렇게 하면 queue것(메서드)만 쓸 수 있다.
		que.offer("0");
		que.offer("1");
		que.offer("2");
		
		while(!que.isEmpty()){
			System.out.println(que.poll());
		}
		// List형태를 많이 쓴다 - 다형성 활용이 잦다
		
		
		
		
		
		
		
		
		
		
	}
}

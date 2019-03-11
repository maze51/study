package j_collection;

public class CollectionBasic {
	public static void main(String[] args) {
		/*
		1. Collection Framework
		  1) 의미
		    - Collection은 다수의 데이터 
		    - Framework는 표준화된 프로그래밍 방식
		    
		  2) 핵심 interface
		    - List
		    	: 순서가 있다.
		    	: 데이터의 중복을 허용한다.
		    		ex) 대기자 명단
		    	구현체: ArrayList, LinkedList, Stack, Vector
		    - Set
		    	: 순서가 없다.
		    	: 데이터의 중복을 비허용한다.
		    		ex) 양의 정수의 집합, 소수의 집합
		    	: HashSet, TreeSet
		    - Map<key,value>
		    	: 순서가 없다.
		    	: 키와 값의 쌍으로 이루어진 데이터의 집합
		    	: key는 중복을 비허용한다.
		    	  value는 중복을 허용한다.
		    		ex) 우편번호, 지역번호
		    	구현체: HashMap, HashTable, TreeMap, Properties
		 
		2. List
		  - Vector와 ArrayList
		  	: Vector는 동기화를 보장한다.
		  	: Object배열을 이용해서 데이터를 "순차적"으로 저장한다.
		  	
		  - LinkedList
		 	: 배열의 단점을 보완하기 위해 만들어짐
		 	: 자신과 연결된 다음 요소에 대한 주소값과 데이터로 이루어짐
		 	
		  - DoubleLinkedList
		  	: 자신과 연결된 이전 요소와 다음 요소에 대한 주소값과 데이터로 이루어짐
		  	
		3. Stack과 Queue
		  - Stack : LIFO
		  	: 저장 push()
		  	: 추출 pop()
		  	: 맨 위 객체만 반환 peek()
		  	: 주어진 객체를 찾기 search()
		  	
		  - Queue : FIFO
		  	: 저장 offer()
		  	: 추출 poll()
		 
		4. Iterator, ListIterator
		  - 컬렉션에서 저장된 요소들을 읽어오는 방식
		
		5. Set
		  - Set인터페이스를 구현한 가장 대표적인 클래스는 HashSet
		  - add메서드나 addAll메서드를 사용하는데 이미 저장된 요소가 있다면 중복추가에 실패하게 된다(false를 반환).
		  - HashSet은 내부적으로 HashMap으로 이루어져 있다.
		  
		6. Map
		  - HashTable의 최신버전이 HashMap이므로 HashMap을 쓰자
		  - Map의 특징: key, value를 묶어 하나의 데이터로 저장한다.
		  - Map<Object,Object>타입이지만 key의 경우 String으로 통일해서 많이 사용한다.
		  - key는 유일해야 한다.
		
		
		인터페이스타입으로 해놔야 컬렉션끼리 형변환이 가능하다. 그래서 ArrayList를 쓰더라도 List타입으로 선언해서 사용하기도 한다.
		 */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}
}

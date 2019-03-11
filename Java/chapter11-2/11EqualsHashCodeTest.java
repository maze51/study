package basic;

import java.util.HashSet;
import java.util.Set;

public class EqualsHashCodeTest {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("일지매");
		
		Person p2 = new Person();
		p2.setId(1);
		p2.setName("일지매");
		
		System.out.println(p1.equals(p2));
		System.out.println(p1 == p2); // equals()도 내부에서 이런 식으로 비교한다. 결과도 동일. 하지만 아래와 같이 equals를 오버라이딩 한 후에는 서로 다른 결과가 나온다
		
		System.out.println("p1의 hashCode : " + p1.hashCode());
		System.out.println("p2의 hashCode : " + p2.hashCode());
		
		Set<Person> set = new HashSet<Person>();
		set.add(p1);
		set.add(p2);
		System.out.println(set.size());
	}
}
/*
	- equals()메서드는 두 객체의 내용이 같은지를 비교하는 연산자 (동등성, equality)
	- hashCode()메서드는 두 객체가 같은지를 비교하는 연산자 (동일성, identity)
	
	- HashTable이나 HashSet, HashMap과 같은 객체들을 사용하는 경우
	   객체의 의미상의 동등성 비교를 위해 hashCode()메서드를 호출한다.
	   그러므로, 객체가 같은지 여부를 결정하려면 hashCode()메서드를 재정의해야 한다.
	   
	** equals()와 hashCode()에 관련된 규칙
		1. equals()를 Override하면 반드시 hashCode()도 Override해야 한다.
		
		2. equals()메서드는 기본적으로 '=='연산자를 이용하여 객체를 비교한다.
			따라서 equals()를 재정의하지 않으면 절대 그 유형의 두 객체가 같은 것으로 간주될 수 없다.
			
		3. a.equals(b)가 true이면 a.hashCode() == b.hashCode()도 성립해야 한다.
			하지만 a.hashCode() == b.hashCode()가 성립해도 a.equals(b)가 반드시 true인 것은 아니다.
			
		4. hashCode()에서는 기본적으로(재정의하지 않으면) Heap에 있는 각 객체에 대한
			메모리 주소의 매핑 정보를 기반으로 한 정수값을 만들어서 반환한다.
			
			이 메서드에서 사용하는 '해싱 알고리즘'에서 서로 다른 객체들에 대해 같은 hashCode값이 만들어질 수 있기 때문에
			객체가 같지 않아도 hashCode는 같을 수 있다.

 */


class Person{
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
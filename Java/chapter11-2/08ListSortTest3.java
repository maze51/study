package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
	문제) 학번(int), 이름(String), 국어점수(int), 영어점수(int), 수학점수(int), 총점(int), 등수(int)를 멤버로 갖는  Student클래스를 만든다.
	이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수 만 매개변수로 받아서 처리한다.
	이 Student 객체들은 List에 저장하여 관리한다.
	
	List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 기능을 포함시킨다.
	
	그리고, 외부 정렬 기준을 이용하여 총점의 역순으로 정렬하도록 하고
	만약 총점이 같으면 이름의 오름차순으로 정렬되도록 한다.
	
	(등수는 일단 구하기 x)

*/
public class ListSortTest3 {

	// 등수를 구하는 메서드
	public void setRanking(List<Student> stdList) {
		
		for (Student std : stdList) { // 기준 데이터를 찾는 반복문
			int rank = 1; // 초기값은 1등으로 설정한다.
			for(Student std2 : stdList) { // 비교를 진행하는 반복문
				if(std.getTotal() < std2.getTotal()) {
					rank++;
				}
			}
			std.setRank(rank); // 구한 등수를 Student객체에 저장한다.
		}
	}
	public static void main(String[] args) {
		List<Student> sList = new LinkedList<Student>();
		sList.add(new Student(2014, "가가가", 60, 90, 26));
		sList.add(new Student(2000, "나나나", 55, 40, 63));
		sList.add(new Student(1951, "다다다", 88, 66, 88));
		sList.add(new Student(2200, "라라라", 57, 91, 75));
		sList.add(new Student(2500, "마마마", 70, 30, 63));
		sList.add(new Student(2551, "바바", 40, 30, 90));
		sList.add(new Student(6000, "사사", 70, 31, 90));
		sList.add(new Student(1001, "아아아", 70, 30, 100));
		
		// 등수를 구하는 메서드 호출하기
//		ListSortTest3 stdTest = new ListSortTest3();
//		stdTest.setRanking(sList);
		new ListSortTest3().setRanking(sList); // 한 번만 호출하면 되니 한줄로 줄이기
		
		System.out.println("정렬 전");
		for(Student s : sList){
			System.out.println(s);
		}
		System.out.println("---------------------------------------------");
		
		Collections.sort(sList); // 기본 정렬 기준으로 정렬한다(학번의 오름차순)
		System.out.println("학번의 오름차순 정렬 후");
		for(Student s : sList){
			System.out.println(s);
		}
		System.out.println("---------------------------------------------");
		// 외부 정렬 기준으로 정렬한다
		Collections.sort(sList, new SortNumDest()); // 총점의 역순과 이름의 오름차순 정렬
		
		System.out.println("총점의 역순과 이름의 오름차순 정렬후");
		for(Student s : sList){
			System.out.println(s);
		}
	}

}

class Student implements Comparable<Student>{
	private int num;
	private String name;
	private int korScore;
	private int engScore;
	private int mathScore;
	private int total;
	private int rank;
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", korScore="
				+ korScore + ", engScore=" + engScore + ", mathScore="
				+ mathScore + ", total=" + total + ", rank=" + rank + "]";
	}

	public Student(int num, String name, int korScore, int engScore,
			int mathScore) {
		super();
		this.num = num;
		this.name = name;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
		this.total = korScore + engScore + mathScore;
		
	}
	
	// 학번의 오름차순 정렬 기준 정하기
	@Override
	public int compareTo(Student num) {
		return Integer.compare(this.num, num.getNum());
	}

}
//총점의 역순으로 정렬하도록 하고, 만약 총점이 같으면 이름의 오름차순으로 정렬되도록 설정.
class SortNumDest implements Comparator<Student> {

	@Override
	public int compare(Student std1, Student std2) {
//		return new Integer(total1.getTotal()).compareTo(total1.getTotal()) * -1;
		if(std1.getTotal() == std2.getTotal()){
			return std1.getName().compareTo(std2.getName()); // 동점일 때는 이름으로 오름차순정렬
		} else {
			return new Integer(std1.getTotal()).compareTo(std2.getTotal() * -1); // 총점으로 정수형 객체 생성
		}
	}
	
}

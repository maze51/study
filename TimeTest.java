package f_OOP2;

public class TimeTest {
	public static void main(String[] args) {
		TimeVO t = new TimeVO();
		t.setHour(30);
		System.out.println(t.getHour()); // 6
		
		t.setMinute(140);
		System.out.println(t.getHour()+" : "+t.getMinute()); // 8: 20
		
		t.setSeconds(10000);
		System.out.println(t.getHour()+" : "+t.getMinute()+" : "+t.getSeconds()); // 11 : 6 : 40
		
	} // 메서드 재활용시 편하게 가능
} // 최종적으로 각 한줄씩이면 가능. 2단계 다 풀고 보다 효율적으로 만들 수 있을까 생각해 보기.

class TimeVO{
	private int hour;
	private int minute;
	private int seconds;
	
	void setHour(int hour){
		this.hour = hour%24;
				
	}
	
	int getHour(){
		return hour;
	}
	
	void setMinute(int minute){
		this.hour += minute/60;
		this.minute = minute%60;

	}
	
	int getMinute(){
		return minute;
	}
	
	void setSeconds(int seconds){
		setMinute(minute += seconds/60);
		this.seconds =seconds%60;
		
	}
	
	int getSeconds(){
		return seconds;
	}
}






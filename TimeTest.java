package f_OOP2;

public class TimeTest {
	public static void main(String[] args) {
		TimeVO t = new TimeVO();
		t.setHour(21);
		System.out.println(t.getHour()); // 6
		
		t.setMinute(140);
		System.out.println(t.getHour()+" : "+t.getMinute()); // 8: 20
		
		t.setSeconds(10000);
		System.out.println(t.getHour()+" : "+t.getMinute()+" : "+t.getSeconds()); // 11 : 6 : 40
		
	}
}

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
			
//			hour += minute/60; // hour가 24를 넘을 수 있다.
//			if(hour>23){ 		//해결방안
//				setHour(hour);
//			} else {
//				this.minute = minute;
//			}
			setHour(hour += minute/60);
			this.minute = minute%60;
//		this.hour += minute/60;
//		this.minute = minute%60;

	}
	
	int getMinute(){
		return minute;
	}
	
	void setSeconds(int seconds){
			// 3600으로 나눈 몫만큼 시간 추가
//			hour += seconds/3600;
			// 60으로 나눈 몫만큼 분에 추가
//			minute += seconds/60;		// 둘 다 갖고 있는것이 setMinute
			
		setMinute(minute += seconds/60);		
		this.seconds = seconds%60;
		
	}
	
	int getSeconds(){
		return seconds;
	}
}






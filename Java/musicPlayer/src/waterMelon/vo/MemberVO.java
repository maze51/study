package waterMelon.vo;
/**
 * 회원 한 명의 정보를 다룰 수 있는 클래스
 * @author 1team
 *
 */
public class MemberVO {
	private String mem_mail;		// 회원이메일(PK)
	private String mem_pw;			// 회원 비밀번호
	private String mem_name;		// 회원 이름
	private String mem_bir;			// 회원 생년월일
	private String mem_nn;			// 회원 닉네임
	private int	mem_money;			// 회원 보유금액
	private String mem_period;		// 회원 이용가능기간
	private int mem_level;			// 회원 유효여부(현재회원/탈퇴회원)
	
	
	public int getMem_level() {
		return mem_level;
	}
	public void setMem_level(int mem_level) {
		this.mem_level = mem_level;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_bir() {
		return mem_bir;
	}
	public void setMem_bir(String mem_bir) {
		this.mem_bir = mem_bir;
	}
	public String getMem_nn() {
		return mem_nn;
	}
	public void setMem_nn(String mem_nn) {
		this.mem_nn = mem_nn;
	}
	public int getMem_money() {
		return mem_money;
	}
	public void setMem_money(int mem_money) {
		this.mem_money = mem_money;
	}
	public String getMem_period() {
		return mem_period;
	}
	public void setMem_period(String mem_period) {
		this.mem_period = mem_period;
	}

	
}

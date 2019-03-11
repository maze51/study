package waterMelon.vo;

/**
 * 회원별 이용권 구매 관리
 * @author 1team
 *
 */

public class TicketVO {
	private String tc_cd;		// 구매코드(PK)
	private String tc_date; 		// 구매일자
	private String tcm_cd; 		// 이용권코드(FK)
	private String mem_mail; 	// 회원이메일(FK)
	
	public String getTc_cd() {
		return tc_cd;
	}
	public void setTc_cd(String tc_cd) {
		this.tc_cd = tc_cd;
	}
	public String getTc_date() {
		return tc_date;
	}
	public void setTc_date(String tc_date) {
		this.tc_date = tc_date;
	}
	public String getTcm_cd() {
		return tcm_cd;
	}
	public void setTcm_cd(String tcm_cd) {
		this.tcm_cd = tcm_cd;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	
}

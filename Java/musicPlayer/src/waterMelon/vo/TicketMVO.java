package waterMelon.vo;

/**
 * 	이용권관리(등록 및 수정)
 * 	@author 1team
 *
 */

public class TicketMVO {		
	private String tcm_cd;			// 이용권코드(PK)
	private String tcm_name;			// 이용권이름
	private int tcm_price;			// 이용권가격
	private String tcm_period;		// 이용권기간
	private int	tcm_use;				// 이용권사용유무
	
	
	public String getTcm_cd() {
		return tcm_cd;
	}
	public void setTcm_cd(String tcm_cd) {
		this.tcm_cd = tcm_cd;
	}
	public String getTcm_name() {
		return tcm_name;
	}
	public void setTcm_name(String tcm_name) {
		this.tcm_name = tcm_name;
	}
	public int getTcm_price() {
		return tcm_price;
	}
	public void setTcm_price(int tcm_price) {
		this.tcm_price = tcm_price;
	}
	public String getTcm_period() {
		return tcm_period;
	}
	public void setTcm_period(String tcm_period) {
		this.tcm_period = tcm_period;
	}
	public int getTcm_use() {
		return tcm_use;
	}
	public void setTcm_use(int tcm_use) {
		this.tcm_use = tcm_use;
	}
	
}

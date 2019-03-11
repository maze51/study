package waterMelon.vo;

/**
 * 회원별 금액충전관리
 * @author 1team
 */
public class ChargeVO {
	private String cha_cd;			// 회원별충전코드(PK)
	private String cha_date;			// 충전한 날짜
	private String char_price_cd;	// 충전금액코드(FK)
	private String mem_mail; 		// 회원 이메일(FK)
	
	public String getCha_cd() {
		return cha_cd;
	}
	public void setCha_cd(String cha_cd) {
		this.cha_cd = cha_cd;
	}
	public String getCha_date() {
		return cha_date;
	}
	public void setCha_date(String cha_date) {
		this.cha_date = cha_date;
	}
	public String getChar_price_cd() {
		return char_price_cd;
	}
	public void setChar_price_cd(String char_price_cd) {
		this.char_price_cd = char_price_cd;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	
	

}

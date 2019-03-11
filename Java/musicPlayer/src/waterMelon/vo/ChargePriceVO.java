package waterMelon.vo;

/**
 * 충전금액관리
 * @author 1team
 *
 */
public class ChargePriceVO {
	private String char_price_cd;	//충전금액코드(PK)
	private int char_price;		//충전금액
	
	public String getChar_price_cd() {
		return char_price_cd;
	}
	public void setChar_price_cd(String char_price_cd) {
		this.char_price_cd = char_price_cd;
	}
	public int getChar_price() {
		return char_price;
	}
	public void setChar_price(int char_price) {
		this.char_price = char_price;
	}

}

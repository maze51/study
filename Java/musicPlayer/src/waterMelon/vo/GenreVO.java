package waterMelon.vo;

/**
 * 음악장르관리
 * @author 1team
 *
 */

public class GenreVO {
	private String gr_cd;		// 장르코드(PK)
	private String gr_name;		// 장르이름
	private int gr_use;			// 장르사용유무
	
	public int getGr_use() {
		return gr_use;
	}
	public void setGr_use(int gr_use) {
		this.gr_use = gr_use;
	}
	public String getGr_cd() {
		return gr_cd;
	}
	public void setGr_cd(String gr_cd) {
		this.gr_cd = gr_cd;
	}
	public String getGr_name() {
		return gr_name;
	}
	public void setGr_name(String gr_name) {
		this.gr_name = gr_name;
	}
}

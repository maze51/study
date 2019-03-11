package waterMelon.vo;

/**
 * MYLIST 폴더 관리
 * @author 1team
 *
 */
public class MyListVO {
	private String ml_cd;		// MYLIST 폴더 코드(PK)
	private String ml_name;		// MYLIST 폴더명
	private String mem_mail;	// 회원이메일(FK)
	private int ml_use;			// 폴더 유효여부
	
	public int getMl_use() {
		return ml_use;
	}
	public void setMl_use(int ml_use) {
		this.ml_use = ml_use;
	}
	public String getMl_cd() {
		return ml_cd;
	}
	public void setMl_cd(String ml_cd) {
		this.ml_cd = ml_cd;
	}
	public String getMl_name() {
		return ml_name;
	}
	public void setMl_name(String ml_name) {
		this.ml_name = ml_name;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}

}

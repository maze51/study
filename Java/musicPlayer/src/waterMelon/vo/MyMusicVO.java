package waterMelon.vo;

/**
 * MYLIST 내의 폴더별 음악 리스트
 * @author 1team
 *
 */

public class MyMusicVO {
	private String mm_cd;		// MyMusic 코드(PK)
	private String ml_cd; 		// MYLIST 폴더코드(FK)
	private String music_cd;	// 음악코드(FK)
	private int mm_use;			// 음악 유효여부
	
	public String getMm_cd() {
		return mm_cd;
	}
	public void setMm_cd(String mm_cd) {
		this.mm_cd = mm_cd;
	}
	public String getMl_cd() {
		return ml_cd;
	}
	public void setMl_cd(String ml_cd) {
		this.ml_cd = ml_cd;
	}
	public String getMusic_cd() {
		return music_cd;
	}
	public void setMusic_cd(String music_cd) {
		this.music_cd = music_cd;
	}
	public int getMm_use() {
		return mm_use;
	}
	public void setMm_use(int mm_use) {
		this.mm_use = mm_use;
	}
}

package waterMelon.vo;

/**
 *	음악 한곡을 관리
 * 	@author 1team
 */

public class MusicVO {
	private String music_cd;		// 음악코드(PK)
	private String music_name;		// 곡명
	private String music_artist;	// 아티스트
	private String music_date;		// 발매일
	private String music_album;		// 앨범명
	private String music_cps;		// 작곡가
	private String music_lyr;		// 작사가
	private int music_cnt;			// 재생횟수 
	private String gr_cd;			// 음악장르코드(FK)
	private int music_use;			// 음악사용유무
	
	public String getMusic_cd() {
		return music_cd;
	}
	public void setMusic_cd(String music_cd) {
		this.music_cd = music_cd;
	}
	public String getMusic_name() {
		return music_name;
	}
	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	public String getMusic_artist() {
		return music_artist;
	}
	public void setMusic_artist(String music_artist) {
		this.music_artist = music_artist;
	}
	public String getMusic_date() {
		return music_date;
	}
	public void setMusic_date(String music_date) {
		this.music_date = music_date;
	}
	public String getMusic_album() {
		return music_album;
	}
	public void setMusic_album(String music_album) {
		this.music_album = music_album;
	}
	public String getMusic_cps() {
		return music_cps;
	}
	public void setMusic_cps(String music_cps) {
		this.music_cps = music_cps;
	}
	public String getMusic_lyr() {
		return music_lyr;
	}
	public void setMusic_lyr(String music_lyr) {
		this.music_lyr = music_lyr;
	}
	public int getMusic_cnt() {
		return music_cnt;
	}
	public void setMusic_cnt(int music_cnt) {
		this.music_cnt = music_cnt;
	}
	public String getGr_cd() {
		return gr_cd;
	}
	public void setGr_cd(String gr_cd) {
		this.gr_cd = gr_cd;
	}
	public int getMusic_use() {
		return music_use;
	}
	public void setMusic_use(int music_use) {
		this.music_use = music_use;
	}

}

package waterMelon.service;

import java.util.List;
import java.util.Map;

import waterMelon.db.DBClass;
import waterMelon.vo.ChargePriceVO;
import waterMelon.vo.ChargeVO;
import waterMelon.vo.GenreVO;
import waterMelon.vo.MemberVO;
import waterMelon.vo.MusicVO;
import waterMelon.vo.MyListVO;
import waterMelon.vo.TicketMVO;
import waterMelon.vo.TicketVO;

public class IServiceImpl implements IService{

	private DBClass db = new DBClass();
	
	/**
	 * 아이디와 비번이 일치하는 회원의 정보를 가져오는 메서드
	 * @param mem_id 회원아이디
	 * @param mem_pw 회원비밀번호
	 * @return  MemberVO 아이디와 비번이 일치하는 한 명의 정보
	 */
	@Override
	public MemberVO getMemberInfo(Map<String, String> params) {
		MemberVO result = db.getMemberInfo(params);
		return result;
	}
	
	/**
	 * 	회원가입에서 입력한 정보를 담아 db로 가져가기위한 메서드
	 * 	@author park seo kyoung
	 */
	@Override
	public boolean addMemInfo(MemberVO mv) {
		return db.addMemInfo(mv);
	}
	
	/**
	 * db에 있는 이메일과 비교하기위한 메서드 
	 * @author park seo kyoung
	 */
	@Override
	public boolean checkEmail(String email) {
		return db.checkEmail(email);
	}
	
	

	/**
	 * 	db에 저장된 충전 금액 리스트를 가져오기위한 메서드
	 * 	@author park seo kyoung
	 */
	@Override
	public List<ChargePriceVO> allChargePrice() {
		return db.allChargePrice();
	}
	
	/**
	 * 
	 * 	@author park seo kyoung
	 */
//	@Override
//	public ChargeVO cashChargeInfo(Map<String, String> charInfo) {
//		return db.cashChargeInfo();
//	}
	
	
	/**
	 * 	실제 캐시를 충전 정보를 저장한 것들을 db로 가져가기 위한 메서드
	 * 	@author park seo kyoung
	 */
	@Override
	public boolean addCash(ChargeVO charInfo) {
		return db.addCash(charInfo);
	}

	@Override
	public void addMemMoeny(ChargeVO charInfo) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 *  회원의 보유캐시를 추가 해주기 위한 캐시 정보가 담긴 메서드
	 * 	@author park seo kyoung
	 */
	@Override
	public boolean addMemMoeny(String mem_mail, int money) {
		return db.addMemMoeny(mem_mail, money);
	}
	
	/**
	 * 	구매내역을 불러오기위한 메서드
	 * 	@author park seo kyoung
	 */
	@Override
	public List<ChargeVO> chargeList() {
		return db.chargeList();
	}

	/**
	 *  비밀번호를 재설정하기 위한 정보가 담긴 메서드
	 *	@author park seo kyoung
	 */
	@Override
	public boolean modifyPw(String str, String mem_mail) {
		return db.modifyPw(str, mem_mail);
	}
	
	/**
	 *  이름을 재설정하기 위한 정보가 담긴 메서드
	 *	@author park seo kyoung
	 */
	@Override
	public boolean modifyName(String str, String mem_mail) {
		return db.modifyName(str, mem_mail);
	}

	/**
	 *  생년월일을 재설정하기 위한 정보가 담긴 메서드
	 *	@author park seo kyoung
	 */
	@Override
	public boolean modifyBir(String str, String mem_mail) {
		return db.modifyBir(str, mem_mail);
	}

	/**
	 *  닉네임을 재설정하기 위한 정보가 담긴 메서드
	 *	@author park seo kyoung
	 */
	@Override
	public boolean modifyNn(String str, String mem_mail) {
		return db.modifyNn(str, mem_mail);
	}


	/**
	 *  회원탈퇴
	 *	@author park seo kyoung
	 */
	@Override
	public void memberLeave(String mem_mail) {
		db.memberLeave(mem_mail);
	}
	
	/**
	 * 모든 회원 조회
	 * @author park seo kyoung
	 */
	@Override
	public List<MemberVO> allMemberList() {
		return db.allMemberList();
	}

	/**
	 * 모든 회원의 캐시 충전 내역 조회
	 * @author park seo kyoung
	 */
	@Override
	public List<ChargeVO> allChargeList() {
		return db.allChargeList();
	}

	/**
	 * 캐시충전 금액 리스트를 추가하는 메서드
	 * @author park seo kyoung
	 */
	@Override
	public boolean addCashPrice(Map<String, Object> addCashPrice) {
		return db.addCashPrice(addCashPrice);
	}

	/**
	 * 캐시충전 금액 리스트를 수정하는 메서드
	 * @author park seo kyoung
	 */
	@Override
	public boolean modifyCashPrice(int input, String chPrice) {
		return db.modifyCashPrice(input, chPrice);
	}

	/**
	 * 캐시충전 금액 리스트를 삭제하는 메서드
	 * @author park seo kyoung
	 */
	@Override
	public boolean delCashPrice(ChargePriceVO charPrice) {	
		return db.delCashPrice(charPrice);
	}

	/**
	 * 해당 년도의 매출 검색
	 * @author park seo kyoung
	 */
	@Override
	public int yearSales(String salse) {
		return db.yearSales(salse);
	}

	/**
	 * 해당 년도의 해당 월 매출 검색
	 * @author park seo kyoung
	 */
	@Override
	public int monthSales(String salseY, String salseM) {
		return db.monthSales(salseY, salseM);
	}

//--------------------------------------------서경 IserviceImpl끝---------------------------------
//--------------------------------------------민하 IserviceImpl시작---------------------------------
	/**
	 * 워터멜론에서 제공하는 이용권의 리스트를 출력하는 메서드 
	 * @return
	 */
	@Override
	public List<TicketMVO> getTicketInfo() {
		List<TicketMVO> result = db.TicketInfo();
		return result;
	}
	
	/**
	 * 이용권의 정보를 담아서 db로 가져가는 메서드
	 * @param params
	 * @return
	 */
	@Override
	public boolean addMyticket(Map<String, String> params) {
		boolean result = db.addMyticket(params);
		return result;
	}
	
	/**
	 * 사용자가 구매한 이용권의 정보를 가져오는 메서드
	 * @param mem_mail
	 * @return
	 */
	@Override
	public List<TicketMVO> myTicketInfo(String mem_mail) {
		List<TicketMVO> result = db.myticketInfo(mem_mail);
		return result;
	}
	
	
	
	/**
	 * 관리자가 사용자가 구매한 이용권명을 보기 위한 메서드
	 * @param mem_mail
	 * @return
	 */
	@Override
	public List<TicketMVO> admintk(String mem_mail) {
		List<TicketMVO> result = db.admintk(mem_mail);
		return result;
	}

	/**
	 *관리자가 사용자가 구매한 이용권 정보중 ticketVO의 정보를 보기위한 메서드
	 * @param mem_mail
	 * @return 
	 */
	@Override
	public List<TicketVO> admintk2(String mem_mail) {
		List<TicketVO> result = db.admintk2(mem_mail);
		return result;
	}
	
	/**
	 * 관리자가 새로 만든 이용권을 db로 보내주는 메서드
	 * @param ticketM
	 */
	@Override
	public void addTicketM(TicketMVO ticketM) {
		db.addTicketM(ticketM);
		
	}
	
	/**
	 * 관리자가 이용권을 삭제하는 메서드
	 * @param tcm_cd
	 */
	@Override
	public void tkDelete(String tcm_cd) {
		db.tkDelete(tcm_cd);
		
	}
	
		
	/**
	 * 관리자가 이용권의 이용권가격을 수정하는 메서드
	 * @param tcm_cd, tcm_price
	 * @return
	 */
	@Override
	public boolean tkpriceUpdate(String tcm_cd, int tcm_price) {
		boolean result = db.tkpriceUpdate(tcm_cd, tcm_price);
		return result;
	}


	/**
	 * 관리자가 이용권의 이용권사용유무를 수정하는 메서드
	 * @param tcm_cd, tcm_use
	 * @return
	 */
	@Override
	public boolean tkuseUpdate(String tcm_cd, int tcm_use) {
		boolean result = db.tkuseUpdate(tcm_cd, tcm_use);
		return result;
	}
	
	/**
	 * 관리자가 이용권의 이용권기간을 수정하는 메서드
	 * @param tcm_cd, tcm_period
	 * @return
	 */
	@Override
	public boolean tkperiodUpdate(String tcm_cd, String tcm_period) {
		boolean result = db.tkperiodUpdate(tcm_cd, tcm_period);
		return result;
	}
	
	/**
	 * 관리자가 이용권의 이용권명을 수정하는 메서드
	 * @param tcm_cd, tcm_name
	 * @return
	 */
	@Override
	public boolean tknameUpdate(String tcm_cd, String tcm_name) {
		boolean result = db.tknameUpdate(tcm_cd, tcm_name);
		return result;
	}
	
	/**
	 * 관리자가 이용권의 이용권코드를 수정하는 메서드
	 * @param tcm_cd, tcm_name
	 * @return
	 */
	@Override
	public boolean tkcdUpdate(String tcm_cd, String tcm_cd2) {
		boolean result = db.tkcdUpdate(tcm_cd, tcm_cd2);
		return result;
	}
	
	/**
	 * 워터멜론에 가입한 회원의 정보를 가져오는 메서드
	 * @return
	 */
	@Override
	public List<MemberVO> memberPrint() {
		List<MemberVO> result = db.memberPrint();
		return result;
	}

	/**
	 * 사용자의 이용권 남은기간을 알기위한 메서드
	 * @param mem_mail
	 * @return
	 */
	@Override
	public List<TicketVO> myTicketInfo2(String mem_mail) {
		List<TicketVO> result = db.myTicketInfo2(mem_mail);
		return result;
	}

//--------------------------------------------민하 IserviceImpl끝---------------------------------
//--------------------------------------------영춘 IserviceImpl시작---------------------------------
	/**
	 * 최신리스트를 가지고 오는 메서드
	 * @return
	 */
	@Override
	public List<MusicVO> getMusicRecent() {
		List<MusicVO> result = db.getMusicRecent();
		return result;
	}
	/**
	 * 장르리스트를 가지고 오는 메서드
	 * @return
	 */
	@Override
	public List<GenreVO> getGenreInfo() {
		List<GenreVO> result = db.getGenreInfo();
		return result;
	}
	/**
	 * 장르별 설정된 음악을 가지고 오는 메서드
	 * @return
	 */
	@Override
	public List<MusicVO> getGenreMusic(String gr_cd) {
		List<MusicVO> result = db.getGenreMusic(gr_cd);
		return result;
	}
	/**
	 * 음악검색 키워드를 넘기고 결과를 가져오는 메서드
	 * @param select
	 * @return
	 */
	@Override
	public List<MusicVO> searchMusic(String select) {
		List<MusicVO> result = db.searchMusic(select);
		return result;
	}
	/**
	 * 회원이메일을 받아 그 회원의 유효한 마이리스트 폴더 목록을 반환
	 * @param mem_mail
	 * @return
	 */
	@Override
	public List<MyListVO> getMyListInfo(String mem_mail) {
		List<MyListVO> result = db.getMyListInfo(mem_mail);
		return result;
	}
	/**
	 * 음악을 마이리스트에 저장
	 * @param params
	 * @return
	 */
	@Override
	public boolean addMyList(Map<Integer, String> params) {
		boolean result = db.addMyList(params);
		return result;
	}
	/**
	 * 재생횟수 정렬된 음악차트
	 * @return
	 */
	@Override
	public List<MusicVO> bestChart() {
		List<MusicVO> result = db.bestChart();
		return result;
	}
	/**
	 * 재생횟수를 db의 MusicList에 추가
	 * @param play
	 * @return
	 */
	@Override
	public boolean setMusicCount(MusicVO play) {
		boolean result = db.setMusicCount(play);
		return result;
	}
	/**
	 * 입력받은 장르코드가 존재하는지 체크
	 * @param gr_cd
	 * @return
	 */
	@Override
	public boolean grcdCheck(String gr_cd) {
		boolean result = db.grcdCheck(gr_cd);
		return result;
	}
	/**
	 * 입력받은 음악정보를 MusicList에 추가
	 * @param muv
	 * @return
	 */
	@Override
	public boolean addMusicInfo(MusicVO muv) {
		boolean result = db.addMusicInfo(muv);
		return result;
	}
	/**
	 * 삭제 여부가 체크된 모든 음악 리스트
	 * @return
	 */
	@Override
	public List<MusicVO> getMusicList() {
		List<MusicVO> result = db.getMusicList();
		return result;
	}
	/**
	 * 음악코드가 유효한가 체크
	 * @param music_cd
	 * @return
	 */
	@Override
	public boolean mlcdCheck(String music_cd) {
		boolean result = db.mlcdCheck(music_cd);
		return result;
	}
	/**
	 * 음악코드로 음악을 찾아 곡명 수정
	 * @param params
	 * @return
	 */
	@Override
	public boolean musicNameChange(Map<String, String> params) {
		boolean result = db.musicNameChange(params);
		return result;
	}
	/**
	 * 음악코드로 음악을 찾아 아티스트 수정
	 * @param params
	 * @return
	 */
	@Override
	public boolean musicArtistChange(Map<String, String> params) {
		boolean result = db.musicArtistChange(params);
		return result;
	}
	/**
	 * 음악코드로 음악을 찾아 발매일 수정
	 * @param params
	 * @return
	 */
	@Override
	public boolean musicDateChange(Map<String, String> params) {
		boolean result = db.musicDateChange(params);
		return result;
	}
	/**
	 * 음악코드로 음악을 찾아 앨범명 수정
	 * @param params
	 * @return
	 */
	@Override
	public boolean musicAlbumChange(Map<String, String> params) {
		boolean result = db.musicAlbumChange(params);
		return result;
	}
	/**
	 * 음악코드로 음악을 찾아 작곡가 수정
	 * @param params
	 * @return
	 */
	@Override
	public boolean musicCpsChange(Map<String, String> params) {
		boolean result = db.musicCpsChange(params);
		return result;
	}
	/**
	 * 음악코드로 음악을 찾아 작사가 수정
	 * @param params
	 * @return
	 */
	@Override
	public boolean musicLyrChange(Map<String, String> params) {
		boolean result = db.musicLyrChange(params);
		return result;
	}
	/**
	 * 음악코드로 음악을 찾아 장르코드 수정
	 * @param params
	 * @return
	 */
	@Override
	public boolean grcdChange(Map<String, String> params) {
		boolean result = db.grcdChange(params);
		return result;
	}
	/**
	 * 음악코드로 음악을 찾아 삭제
	 * @param music_cd
	 * @return
	 */
	@Override
	public boolean deleteMusic(String music_cd) {
		boolean result = db.deleteMusic(music_cd);
		return result;
	}
	/**
	 * 새로운 장르 생성
	 * @param genre_name
	 * @return
	 */
	@Override
	public boolean genreAdd(String genre_name) {
		boolean result = db.genreAdd(genre_name);
		return result;
	}
	/**
	 * 장르명 수정
	 * @param params
	 * @return
	 */
	@Override
	public boolean grNameChange(Map<String, String> params) {
		boolean result = db.grNameChange(params);
		return result;
	}
	/**
	 * 삭제할 장르에 해당하는 곡이 있는지 체크
	 * @param gr_cd
	 * @return
	 */
	@Override
	public boolean checkGenreMusic(String gr_cd) {
		boolean result = db.checkGenreMusic(gr_cd);
		return result;
	}
	/**
	 * 장르 삭제
	 * @param gr_cd
	 * @return
	 */
	@Override
	public boolean genreDelete(String gr_cd) {
		boolean result = db.genreDelete(gr_cd);
		return result;
	}
	/**
	 * 마이리스트 폴더코드를 매개변수로 폴더 안의 음악을 출력. 삭제여부 체크
	 * @param ml_cd
	 * @return
	 */
	@Override
	public List<MusicVO> folderMusicList(String ml_cd) {
		List<MusicVO> result = db.folderMusicList(ml_cd);
		return result;
	}
	/**
	 * 마이리스트 폴더코드를 찾아 폴더명을 수정
	 * @param params
	 * @return
	 */
	@Override
	public boolean folderModify(Map<String, String> params) {
		boolean result = db.folderModify(params);
		return result;
	}
	/**
	 * 마이리스트 폴더코드와 음악코드가 일치하는 마이리스트 폴더 내 음악을 찾아 삭제
	 * @param params
	 * @return
	 */
	@Override
	public boolean musicDel(Map<String, String> params) {
		boolean result = db.musicDel(params);
		return result;
	}
	/**
	 * 마이리스트 폴더 추가
	 * @param params
	 * @return
	 */
	@Override
	public boolean myListAdd(Map<String, String> params) {
		boolean result = db.myListAdd(params);
		return result;
	}
	/**
	 * 마이리스트 폴더 삭제
	 * @param ml_cd
	 * @return
	 */
	@Override
	public boolean folderDelete(String ml_cd) {
		boolean result = db.folderDelete(ml_cd);
		return result;
	}
	/**
	 * 이용권 소지 유무 체크
	 * @param mem_mail
	 * @return
	 */
	@Override
	public boolean getTicketCorrect(String mem_mail) {
		boolean result = db.getTicketCorrect(mem_mail);
		return result;
	}

	@Override
	public int cashDeduction(Map<String, Object> price) {
		return db.cashDeduction(price);
	}

	@Override
	public int cashConfirmList(String mem_mail) {
		return db.cashConfirmList(mem_mail);
	}
}

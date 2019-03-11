package waterMelon.service;

import java.util.List;
import java.util.Map;

import waterMelon.vo.ChargePriceVO;
import waterMelon.vo.ChargeVO;
import waterMelon.vo.GenreVO;
import waterMelon.vo.MemberVO;
import waterMelon.vo.MusicVO;
import waterMelon.vo.MyListVO;
import waterMelon.vo.TicketMVO;
import waterMelon.vo.TicketVO;

public interface IService {

	/**
	 * 아이디와 비번이 일치하는 회원의 정보를 가져오는 메서드
	 * @param mem_id 회원이메일
	 * @param mem_pw 회원비밀번호
	 * @return  MemberVO 아이디와 비번이 일치하는 한 명의 정보
	 */
	MemberVO getMemberInfo(Map<String, String> params);
	
	/**
	 * 회원가입에서 작성한 리스트를 MemberVO로 저장
	 * @param mv
	 * @return 
	 */
	boolean addMemInfo(MemberVO mv);
	
	/**
	 * 회원가입시 이메일 체크
	 * @param email
	 * @return MemberVO 이메일 정보와 중복되는 정보가 있는지 없는지 확인.
	 */
	boolean checkEmail(String email);

	
/* park seo kyoung - interface start */	
	
	/**
	 * 	충전금액리스트를 가져옴
	 * 	@param	charInfo
	 * 	@author 	park seo kyoung
	 * 	@return
	 */
	List<ChargePriceVO> allChargePrice();

	
	/**
	 * 	실제 캐시를 충전 정보를 저장, 충전이 됐는지 안됐는지를 확인해야하기 때문에 true, false
	 * 	@param 	chargeInfo
	 * 	@author 	park seo kyoung
	 * 	@return charInfo
	 */
	boolean addCash(ChargeVO charInfo);

	/**
	 * 충전된 캐시금액을 회원의 보유금액에 더해주는 메서드
	 * @author 	park seo kyoung
	 * @param charInfo
	 */
	void addMemMoeny(ChargeVO charInfo);

	/**
	 * 어떤 회원의 캐시정보인지 담고있는 메서드
	 * @author 	park seo kyoung
	 * @param mem_mail
	 * @param money
	 */
	boolean addMemMoeny(String mem_mail, int money);

	/**
	 * 충전내역 리스트를 가져오는 메서드
	 * @author 	park seo kyoung
	 * @return
	 */
	List<ChargeVO> chargeList();

	/**
	 * 비밀번호를 재설정하기 위해 정보를 저장한 메서드
	 * @param input
	 * @param mem_name
	 * @return
	 */
	boolean modifyPw(String str, String mem_mail);
	
	/**
	 * 이름을 수정하기 위해 정보를 저장한 메서드
	 * @param str
	 * @param mem_mail
	 * @return
	 */
	boolean modifyName(String str, String mem_mail);

	/**
	 * 생년월일을 수정하기 위해 정보를 저장한 메서드
	 * @param str
	 * @param mem_mail
	 * @return
	 */
	boolean modifyBir(String str, String mem_mail);

	/**
	 * 닉네임을 수정하기 위해 정보를 저장한 메서드
	 * @param str
	 * @param mem_mail
	 * @return
	 */
	boolean modifyNn(String str, String mem_mail);

	

	/**
	 * 회원탈퇴를 위한 정보를 저장한 메서드
	 * @param mem_mail
	 * @return
	 */
	void memberLeave(String mem_mail);
	

	/**
	 * 모든 회원 리스트를 가져오기 위한 메서드
	 * @return
	 */
	List<MemberVO> allMemberList();

	/**
	 * 모든 회원의 캐시 충전 리스트를 가져오기 위한 메서드
	 * @return
	 */
	List<ChargeVO> allChargeList();

	/**
	 * 캐시충전 금액을 추가하기위한 메서드
	 * @author park seo kyoung
	 * @param addCashPrice
	 * @return
	 */
	boolean addCashPrice(Map<String, Object> addCashPrice);

	/**
	 * 캐시충전 금액을 수정하기위한 메서드
	 * @author park seo kyoung
	 * @param input
	 * @param chPrice
	 * @return
	 */
	boolean modifyCashPrice(int input, String chPrice);

	/**
	 * 캐시충전 금액을 삭제하기위한 메서드
	 * @author park seo kyoung
	 * @param charPrice
	 * @return
	 */
	boolean delCashPrice(ChargePriceVO charPrice);

	/**
	 * 해당년도의 매출을 가져오기 위한 메서드
	 * @author park seo kyoung
	 * @param salse
	 * @return
	 */
	int yearSales(String salse);

	/**
	 * 해당년도의 해당 월 매출을 가져오기 위한 메서드
	 * @author park seo kyoung
	 * @param salse
	 * @return
	 */
	int monthSales(String salseY, String salseM);


//--------------------------------------------서경 Iservice끝---------------------------------
//--------------------------------------------민하 Iservice시작---------------------------------

/**
	 * 워터멜론에서 제공하는 이용권의 리스트를 출력하는 메서드 
	 * @return
	 */
	List<TicketMVO> getTicketInfo();

	/**
	 * 이용권의 정보를 담아서 db로 가져가는 메서드
	 * @param params
	 * @return
	 */
	boolean addMyticket(Map<String, String> params);
	
	/**
	 * 사용자가 구매한 이용권의 정보를 가져오는 메서드
	 * @param mem_mail
	 * @return
	 */
	List<TicketMVO> myTicketInfo(String mem_mail);
	
	/**
	 * 관리자가 사용자가 구매한 이용권을 보기 위한 메서드
	 * @param mem_mail
	 * @return
	 */
	List<TicketMVO> admintk(String mem_mail);
	
	/**
	 * 관리자가 사용자가 구매한 이용권 정보중 ticketVO의 정보를 보기위한 메서드
	 * @param mem_mail
	 * @return
	 */
	List<TicketVO> admintk2(String mem_mail);
	
	/**
	 * 관리자가 새로 만든 이용권을 db로 보내주는 메서드
	 * @param ticketM
	 */
	void addTicketM(TicketMVO ticketM);
	
	/**
	 * 관리자가 이용권을 삭제하는 메서드
	 * @param tcm_cd
	 */
	void tkDelete(String tcm_cd);
	
	/**
	 * 관리자가 이용권의 이용권가격을 수정하는 메서드
	 * @param tkU
	 * @return
	 */
	boolean tkpriceUpdate(String tcm_cd, int tcm_price);
	
	/**
	 * 관리자가 이용권의 이용권사용유무를 수정하는 메서드
	 * @param tkU
	 * @return
	 */
	boolean tkuseUpdate(String tcm_cd, int tcm_use);
	
	/**
	 * 관리자가 이용권의 이용권기간을 수정하는 메서드
	 * @param tkU
	 * @return
	 */
	boolean tkperiodUpdate(String tcm_cd, String tcm_period);
	
	/**
	 * 관리자가 이용권의 이용권명을 수정하는 메서드
	 * @param tkU
	 * @return
	 */
	boolean tknameUpdate(String tcm_cd, String tcm_name);
	
	/**
	 * 관리자가 이용권의 이용권코드를 수정하는 메서드
	 * @param tkU
	 * @return
	 */
	boolean tkcdUpdate(String tcm_cd, String tcm_cd2);
	
	/**
	 * 워터멜론에 가입한 회원의 정보를 가져오는 메서드
	 * @return
	 */
	List<MemberVO> memberPrint();
	
	/**
	 * 사용자의 이용권 남은기간을 알기위한 메서드
	 * @param mem_mail
	 * @return
	 */
	List<TicketVO> myTicketInfo2(String mem_mail);
	
//--------------------------------------------민하 Iservice끝---------------------------------
//--------------------------------------------영춘 Iservice시작---------------------------------

	/**
	 * 최신리스트를 가지고 오는 메서드
	 * @return
	 */
	List<MusicVO> getMusicRecent();

	/**
	 * 장르리스트를 가지고 오는 메서드
	 * @return
	 */
	List<GenreVO> getGenreInfo();

	/**
	 * 장르별 설정된 음악을 가지고 오는 메서드
	 * @return
	 */
	List<MusicVO> getGenreMusic(String gr_cd);

	/**
	 * 음악검색 키워드를 넘기고 결과를 가져오는 메서드
	 * @param select
	 * @return
	 */
	List<MusicVO> searchMusic(String select);
	
	/**
	 * 회원이메일을 받아 그 회원의 유효한 마이리스트 폴더 목록을 반환
	 * @param mem_mail
	 * @return
	 */
	List<MyListVO> getMyListInfo(String mem_mail);
	
	/**
	 * 음악을 마이리스트에 저장
	 * @param params
	 * @return
	 */
	boolean addMyList(Map<Integer, String> params);
	
	/**
	 * 재생횟수 정렬된 음악차트
	 * @return
	 */
	List<MusicVO> bestChart();
	
	/**
	 * 재생횟수를 db의 MusicList에 추가
	 * @param play
	 * @return
	 */
	boolean setMusicCount(MusicVO play);
	
	/**
	 * 입력받은 장르코드가 존재하는지 체크
	 * @param gr_cd
	 * @return
	 */
	boolean grcdCheck(String gr_cd);
	
	/**
	 * 입력받은 음악정보를 MusicList에 추가
	 * @param muv
	 * @return
	 */
	boolean addMusicInfo(MusicVO muv);
	
	/**
	 * 삭제 여부가 체크된 모든 음악 리스트
	 * @return
	 */
	List<MusicVO> getMusicList();
	
	/**
	 * 음악코드가 유효한가 체크
	 * @param music_cd
	 * @return
	 */
	boolean mlcdCheck(String music_cd);
	
	/**
	 * 음악코드로 음악을 찾아 곡명 수정
	 * @param params
	 * @return
	 */
	boolean musicNameChange(Map<String, String> params);
	
	/**
	 * 음악코드로 음악을 찾아 아티스트 수정
	 * @param params
	 * @return
	 */
	boolean musicArtistChange(Map<String, String> params);
	
	/**
	 * 음악코드로 음악을 찾아 발매일 수정
	 * @param params
	 * @return
	 */
	boolean musicDateChange(Map<String, String> params);
	
	/**
	 * 음악코드로 음악을 찾아 앨범명 수정
	 * @param params
	 * @return
	 */
	boolean musicAlbumChange(Map<String, String> params);
	
	/**
	 * 음악코드로 음악을 찾아 작곡가 수정
	 * @param params
	 * @return
	 */
	boolean musicCpsChange(Map<String, String> params);
	
	/**
	 * 음악코드로 음악을 찾아 작사가 수정
	 * @param params
	 * @return
	 */
	boolean musicLyrChange(Map<String, String> params);
	
	/**
	 * 음악코드로 음악을 찾아 장르코드 수정
	 * @param params
	 * @return
	 */
	boolean grcdChange(Map<String, String> params);
	
	/**
	 * 음악코드로 음악을 찾아 삭제
	 * @param music_cd
	 * @return
	 */
	boolean deleteMusic(String music_cd);
	
	/**
	 * 새로운 장르 생성
	 * @param genre_name
	 * @return
	 */
	boolean genreAdd(String genre_name);
	
	/**
	 * 장르명 수정
	 * @param params
	 * @return
	 */
	boolean grNameChange(Map<String, String> params);
	
	/**
	 * 삭제할 장르에 해당하는 곡이 있는지 체크
	 * @param gr_cd
	 * @return
	 */
	boolean checkGenreMusic(String gr_cd);
	
	/**
	 * 장르 삭제
	 * @param gr_cd
	 * @return
	 */
	boolean genreDelete(String gr_cd);
	
	/**
	 * 마이리스트 폴더코드를 매개변수로 폴더 안의 음악을 출력. 삭제여부 체크
	 * @param ml_cd
	 * @return
	 */
	List<MusicVO> folderMusicList(String ml_cd);
	
	/**
	 * 마이리스트 폴더코드를 찾아 폴더명을 수정
	 * @param params
	 * @return
	 */
	boolean folderModify(Map<String, String> params);
	
	/**
	 * 마이리스트 폴더코드와 음악코드가 일치하는 마이리스트 폴더 내 음악을 찾아 삭제
	 * @param params
	 * @return
	 */
	boolean musicDel(Map<String, String> params);
	
	/**
	 * 마이리스트 폴더 추가
	 * @param params
	 * @return
	 */
	boolean myListAdd(Map<String, String> params);
	
	/**
	 * 마이리스트 폴더 삭제
	 * @param ml_cd
	 * @return
	 */
	boolean folderDelete(String ml_cd);
	/**
	 * 이용권 소지 유무 체크
	 * @param mem_mail
	 * @return
	 */
	boolean getTicketCorrect(String mem_mail);

	/**
	 * 이용권 구매시 보유 캐시에서 차감하는 메서드
	 * @param tcm_cd
	 * @return 
	 */
	int cashDeduction(Map<String, Object> price);

	/**
	 * 캐시충전 리스트 확인 메서드 - 사용자
	 * @param mem_mail
	 * @return
	 */
	int cashConfirmList(String mem_mail);
	
}
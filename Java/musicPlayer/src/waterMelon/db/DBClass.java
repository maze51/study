package waterMelon.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import waterMelon.vo.ChargePriceVO;
import waterMelon.vo.ChargeVO;
import waterMelon.vo.GenreVO;
import waterMelon.vo.MemberVO;
import waterMelon.vo.MusicVO;
import waterMelon.vo.MyListVO;
import waterMelon.vo.MyMusicVO;
import waterMelon.vo.TicketMVO;
import waterMelon.vo.TicketVO;

/**
 * 데이터베이스를 저장하기 위한 클래스
 * @author 1team
 *
 */
public class DBClass {

	private List<MemberVO> memberList = new ArrayList<MemberVO>();				// 회원을 관리할 수 있는 VO
	private List<ChargePriceVO> charPriceList = new ArrayList<ChargePriceVO>(); 	// 충전금액을 관리할 수 있는 VO
	private List<ChargeVO> chargeList = new ArrayList<ChargeVO>();				// 금액충전내역을 관리할 수 있는 VO
	
	private List<GenreVO> genreList = new ArrayList<GenreVO>();					// 장르를 관리할 수 있는 VO
	private List<MusicVO> musicList = new ArrayList<MusicVO>();					// 음악을 관리할 수 있는 VO
	
	private List<MyListVO> myListList = new ArrayList<MyListVO>();				// MYLIST 폴더를 관리할 수 있는 VO
	private List<MyMusicVO> myMusicList = new ArrayList<MyMusicVO>();			// MYLIST 폴더 안에 음악을 관리할 수 있는 VO
	
	private List<TicketMVO> ticketMList = new ArrayList<TicketMVO>();			// 이용권을 관리할 수 있는 VO
	private List<TicketVO> ticketList = new ArrayList<TicketVO>();				// 이용권구매를 관리할 수 있는 VO
	

	// memberList 초기화 시작
	{
		MemberVO mv1 = new MemberVO();
		mv1.setMem_mail("admin");
		mv1.setMem_pw("0000");
		mv1.setMem_name("관리자");
		mv1.setMem_bir("19910401");
		mv1.setMem_nn("관리자");
		mv1.setMem_level(0);
		

		MemberVO mv2 = new MemberVO();
		mv2.setMem_mail("test@naver.com");
		mv2.setMem_pw("1234");
		mv2.setMem_name("김은대");
		mv2.setMem_bir("19700505");
		mv2.setMem_nn("김은대");
		mv2.setMem_money(0);
		mv2.setMem_period("");
		mv2.setMem_level(1);

		MemberVO mv3 = new MemberVO();
		mv3.setMem_mail("love@naver.com");
		mv3.setMem_pw("7777");
		mv3.setMem_name("신용환");
		mv3.setMem_bir("19761123");
		mv3.setMem_money(0);
		mv2.setMem_period("");
		mv3.setMem_nn("에스프레소");
		mv3.setMem_level(1);

		MemberVO mv4 = new MemberVO();
		mv4.setMem_mail("abc@naver.com");
		mv4.setMem_pw("0000");
		mv4.setMem_name("박보검");
		mv4.setMem_bir("19761123");
		mv4.setMem_money(0);
		mv4.setMem_period("");
		mv4.setMem_nn("byebye");
		mv4.setMem_level(2);

		memberList.add(mv1);
		memberList.add(mv2);
		memberList.add(mv3);
		memberList.add(mv4);
	}
	// chargeList 초기화 시작
	
	//	private String cha_cd;			// 회원별충전코드(PK)
	//	private String cha_date;			// 충전한 날짜
	//	private String char_price_cd;	// 충전금액코드(FK)
	//	private String mem_mail; 		// 회원 이메일(FK)
	{
		ChargeVO cgv1 = new ChargeVO();
		cgv1.setCha_cd("n1");
		cgv1.setCha_date("2019-02-26");
		cgv1.setChar_price_cd("ch2");
		cgv1.setMem_mail("test@naver.com");
		
		ChargeVO cgv2 = new ChargeVO();
		cgv2.setCha_cd("n2");
		cgv2.setCha_date("2019-02-23");
		cgv2.setChar_price_cd("ch3");
		cgv2.setMem_mail("love@naver.com");
		
		ChargeVO cgv3 = new ChargeVO();
		cgv3.setCha_cd("n3");
		cgv3.setCha_date("2019-02-28");
		cgv3.setChar_price_cd("ch3");
		cgv3.setMem_mail("test@naver.com");
		
		ChargeVO cgv4 = new ChargeVO();
		cgv4.setCha_cd("n3");
		cgv4.setCha_date("2017-03-22");
		cgv4.setChar_price_cd("ch3");
		cgv4.setMem_mail("test@naver.com");
		
		chargeList.add(cgv1);
		chargeList.add(cgv2);
		chargeList.add(cgv3);
		chargeList.add(cgv4);
	}
	
	// charPriceList 초기화 시작
	{
		ChargePriceVO cpv1 = new ChargePriceVO();
		cpv1.setChar_price_cd("ch1");
		cpv1.setChar_price(3000);
		
		charPriceList.add(cpv1);
		
		ChargePriceVO cpv2 = new ChargePriceVO();
		cpv2.setChar_price_cd("ch2");
		cpv2.setChar_price(5000);
		
		charPriceList.add(cpv2);

		ChargePriceVO cpv3 = new ChargePriceVO();
		cpv3.setChar_price_cd("ch3");
		cpv3.setChar_price(10000);
		
		charPriceList.add(cpv3);
	}
	
	// genreList 초기화 시작
	{
		GenreVO gr1 = new GenreVO();
		gr1.setGr_cd("G01");
		gr1.setGr_name("발라드");
		gr1.setGr_use(0);

		GenreVO gr2 = new GenreVO();
		gr2.setGr_cd("G02");
		gr2.setGr_name("댄스");
		gr2.setGr_use(0);

		GenreVO gr3 = new GenreVO();
		gr3.setGr_cd("G03");
		gr3.setGr_name("랩/힙합");
		gr3.setGr_use(0);

		GenreVO gr4 = new GenreVO();
		gr4.setGr_cd("G04");
		gr4.setGr_name("R&B/Soul");
		gr4.setGr_use(0);

		GenreVO gr5 = new GenreVO();
		gr5.setGr_cd("G05");
		gr5.setGr_name("인디음악");
		gr5.setGr_use(0);

		GenreVO gr6 = new GenreVO();
		gr6.setGr_cd("G06");
		gr6.setGr_name("록/메탈");
		gr6.setGr_use(0);

		GenreVO gr7 = new GenreVO();
		gr7.setGr_cd("G07");
		gr7.setGr_name("트로트");
		gr7.setGr_use(0);

		GenreVO gr8 = new GenreVO();
		gr8.setGr_cd("G08");
		gr8.setGr_name("포크/블루스");
		gr8.setGr_use(0);

		GenreVO gr9 = new GenreVO();
		gr9.setGr_cd("G09");
		gr9.setGr_name("POP");
		gr9.setGr_use(0);

		GenreVO gr10 = new GenreVO();
		gr10.setGr_cd("G10");
		gr10.setGr_name("J-POP");
		gr10.setGr_use(0);

		genreList.add(gr1);
		genreList.add(gr2);
		genreList.add(gr3);
		genreList.add(gr4);
		genreList.add(gr5);
		genreList.add(gr6);
		genreList.add(gr7);
		genreList.add(gr8);
		genreList.add(gr9);
		genreList.add(gr10);
	}

	// musicList 초기화 시작
	{
		MusicVO msv1 = new MusicVO();
		msv1.setMusic_cd("a1");
		msv1.setMusic_name("쉽다");
		msv1.setMusic_artist("SF9");
		msv1.setMusic_date("2017-04-18");
		msv1.setMusic_album("Breaking Sensation");
		msv1.setMusic_cps("한성호");
		msv1.setMusic_lyr("한성호");
		msv1.setMusic_cnt(3);
		msv1.setGr_cd("G02");
		msv1.setMusic_use(0);

		MusicVO msv2 = new MusicVO();
		msv2.setMusic_cd("a2");
		msv2.setMusic_name("Blue");
		msv2.setMusic_artist("볼빨간사춘기");
		msv2.setMusic_date("2017-09-28");
		msv2.setMusic_album("Red Diary Page.01");
		msv2.setMusic_cps("안지영");
		msv2.setMusic_lyr("안지영");
		msv2.setMusic_cnt(18);
		msv2.setGr_cd("G08");
		msv2.setMusic_use(0);

		MusicVO msv3 = new MusicVO();
		msv3.setMusic_cd("a3");
		msv3.setMusic_name("멍청이");
		msv3.setMusic_artist("화사(Hwa Sa)");
		msv3.setMusic_date("2019-02-21");
		msv3.setMusic_album("멍청이(twit)");
		msv3.setMusic_cps("김도훈");
		msv3.setMusic_lyr("김도훈");
		msv3.setMusic_cnt(10);
		msv3.setGr_cd("G02");
		msv3.setMusic_use(0);

		MusicVO msv4 = new MusicVO();
		msv4.setMusic_cd("a4");
		msv4.setMusic_name("SAPPY");
		msv4.setMusic_artist("레드벨벳");
		msv4.setMusic_date("2019-01-11");
		msv4.setMusic_album("SAPPY");
		msv4.setMusic_cps("MEG.ME");
		msv4.setMusic_lyr("Maria Marcus");
		msv4.setMusic_cnt(35);
		msv4.setGr_cd("G10");
		msv4.setMusic_use(0);

		MusicVO msv5 = new MusicVO();
		msv5.setMusic_cd("a5");
		msv5.setMusic_name("7 rings");
		msv5.setMusic_artist("Ariana Grande");
		msv5.setMusic_date("2019-01-18");
		msv5.setMusic_album("thank u, next");
		msv5.setMusic_cps("Ariana Grande");
		msv5.setMusic_lyr("Ariana Grande");
		msv5.setMusic_cnt(15);
		msv5.setGr_cd("G09");
		msv5.setMusic_use(0);

		MusicVO msv6 = new MusicVO();
		msv6.setMusic_cd("a6");
		msv6.setMusic_name("Cereal");
		msv6.setMusic_artist("Crush");
		msv6.setMusic_date("2018-07-13");
		msv6.setMusic_album("wonderlost");
		msv6.setMusic_cps("Cereal");
		msv6.setMusic_lyr("Cereal");
		msv6.setMusic_cnt(34);
		msv6.setGr_cd("G04");
		msv6.setMusic_use(0);

		MusicVO msv7 = new MusicVO();
		msv7.setMusic_cd("a7");
		msv7.setMusic_name("풀어");
		msv7.setMusic_artist("DEAN");
		msv7.setMusic_date("2015-11-05");
		msv7.setMusic_album("130 mood:TRBL");
		msv7.setMusic_cps("ZICO");
		msv7.setMusic_lyr("Deanfluenza");
		msv7.setMusic_cnt(4);
		msv7.setGr_cd("G04");
		msv7.setMusic_use(0);

		MusicVO msv8 = new MusicVO();
		msv8.setMusic_cd("a8");
		msv8.setMusic_name("신용재");
		msv8.setMusic_artist("하은(라코스테남)");
		msv8.setMusic_date("2018-11-06");
		msv8.setMusic_album("신용재");
		msv8.setMusic_cps("하은(라코스테남)");
		msv8.setMusic_lyr("VIP");
		msv8.setMusic_cnt(1);
		msv8.setGr_cd("G01");
		msv8.setMusic_use(0);

		MusicVO msv9 = new MusicVO();
		msv9.setMusic_cd("a9");
		msv9.setMusic_name("아낙네");
		msv9.setMusic_artist("MINO(송민호)");
		msv9.setMusic_date("2018-11-26");
		msv9.setMusic_album("XX");
		msv9.setMusic_cps("MINO(송민호)");
		msv9.setMusic_lyr("MINO(송민호)");
		msv9.setMusic_cnt(4);
		msv9.setGr_cd("G02");
		msv9.setMusic_use(0);
		
		MusicVO msv10 = new MusicVO();
		msv10.setMusic_cd("a10");
		msv10.setMusic_name("이 노래가 클럽에서 나온다면");
		msv10.setMusic_artist("우디(Woody)");
		msv10.setMusic_date("2019-01-23");
		msv10.setMusic_album("이 노래가 클럽에서 나온다면");
		msv10.setMusic_cps("우디(Woody)");
		msv10.setMusic_lyr("우디(Woody)");
		msv10.setMusic_cnt(1);
		msv10.setGr_cd("G03");
		msv10.setMusic_use(0);

		MusicVO msv11 = new MusicVO();
		msv11.setMusic_cd("a11");
		msv11.setMusic_name("니가하면");
		msv11.setMusic_artist("GOT7(갓세븐)");
		msv11.setMusic_date("2015-09-29");
		msv11.setMusic_album("MAD");
		msv11.setMusic_cps("블랙아이드 필승");
		msv11.setMusic_lyr("Sam Lewis");
		msv11.setMusic_cnt(19);
		msv11.setGr_cd("G02");
		msv11.setMusic_use(0);
		
		MusicVO msv12 = new MusicVO();
		msv12.setMusic_cd("a12");
		msv12.setMusic_name("숙녀(淑女)");
		msv12.setMusic_artist("유빈(Yubin)");
		msv12.setMusic_date("2018-06-05");
		msv12.setMusic_album("都市女子");
		msv12.setMusic_cps("Dr.JO");
		msv12.setMusic_lyr("Dr.JO");
		msv12.setMusic_cnt(2);
		msv12.setGr_cd("G02");
		msv12.setMusic_use(0);
		
		musicList.add(msv1);
		musicList.add(msv2);
		musicList.add(msv3);
		musicList.add(msv4);
		musicList.add(msv5);
		musicList.add(msv6);
		musicList.add(msv7);
		musicList.add(msv8);
		musicList.add(msv9);
		musicList.add(msv10);
		musicList.add(msv11);
		musicList.add(msv12);

	}
	
	// myListList(마이리스트 목록) 초기화 시작

	{
		MyListVO mlv1 = new MyListVO();
		mlv1.setMl_cd("e1");
		mlv1.setMl_name("또공이가좋아하는노래");
		mlv1.setMem_mail("vvvvvv@gmail.com");
		mlv1.setMl_use(0);
		
		MyListVO mlv2 = new MyListVO();
		mlv2.setMl_cd("e2");
		mlv2.setMl_name("test의 첫번째 리스트");
		mlv2.setMem_mail("test@naver.com");
		mlv2.setMl_use(0);
		
		MyListVO mlv3 = new MyListVO();
		mlv3.setMl_cd("e3");
		mlv3.setMl_name("test의 두번째 리스트");
		mlv3.setMem_mail("test@naver.com");
		mlv3.setMl_use(0);
		
		myListList.add(mlv1);
		myListList.add(mlv2);
		myListList.add(mlv3);
	}

	// myMusicList(마이리스트의 곡 목록) 초기화 시작

	{
		MyMusicVO mmv1 = new MyMusicVO();
		mmv1.setMm_cd("f1"); // MyMusic코드
		mmv1.setMl_cd("e1"); // 마이리스트 폴더코드
		mmv1.setMusic_cd("a2"); // 음악코드
		mmv1.setMm_use(0); // 유효성검사용
		
		MyMusicVO mmv2 = new MyMusicVO();
		mmv2.setMm_cd("f2");
		mmv2.setMl_cd("e2");
		mmv2.setMusic_cd("a2");
		mmv2.setMm_use(0);
		
		MyMusicVO mmv3 = new MyMusicVO();
		mmv3.setMm_cd("f2");
		mmv3.setMl_cd("e2");
		mmv3.setMusic_cd("a10");
		mmv3.setMm_use(0);
		
		MyMusicVO mmv4 = new MyMusicVO();
		mmv4.setMm_cd("f2");
		mmv4.setMl_cd("e2");
		mmv4.setMusic_cd("a7");
		mmv4.setMm_use(0);
		
		MyMusicVO mmv5 = new MyMusicVO();
		mmv5.setMm_cd("f3");
		mmv5.setMl_cd("e3");
		mmv5.setMusic_cd("a2");
		mmv5.setMm_use(0);
		
		myMusicList.add(mmv1);
		myMusicList.add(mmv2);
		myMusicList.add(mmv3);
		myMusicList.add(mmv4);
		myMusicList.add(mmv5);
	}
	
	// ticketMList 초기화 시작
	{
		TicketMVO tmv1 = new TicketMVO();
		tmv1.setTcm_cd("tk01");
		tmv1.setTcm_name("스트리밍 무제한 듣기 1개월권");
		tmv1.setTcm_price(1000);
		tmv1.setTcm_period("30일");
		tmv1.setTcm_use(1); // 0이면 사용안함, 1이면 사용
		
		ticketMList.add(tmv1);
	}
	
	// ticketList 초기화 시작
	{
		TicketVO tv1 = new TicketVO();
		tv1.setTc_cd("d00");
		tv1.setTc_date("2018/12/31");
		tv1.setTcm_cd("tk01");
		tv1.setMem_mail("vvvvvv@gmail.com");
		
		ticketList.add(tv1);
	}
	
//---------------------------------------------------------------------------------------------------
//--------------------------------------------db 메서드영역 시작----------------------------------------------
//-----------------------------------서경 시작---------------------------------------------
	/**
	 * 	로그인 시 이메일과 비밀번호를 확인하는 메서드
	 * 	@author 	1team
	 * 	@param 	params
	 * 	@return mv
	 */
	public MemberVO getMemberInfo(Map<String, String> params){
		MemberVO mv = null;
		String mem_mail = params.get("mem_mail");
		String mem_pw = params.get("mem_pw");

		for (int i = 0; i < memberList.size(); i++) {
			if(memberList.get(i).getMem_mail().equals(mem_mail) && memberList.get(i).getMem_pw().equals(mem_pw)){
				mv = memberList.get(i);
				break;
			}
		}
		return mv;
	}
	
	/**
	 * 	회원가입시 입력한 이메일을 memberList의 값들과 비교하기위한 메서드
	 * 	@param 	email
	 * 	@author park seo kyoung
	 * 	@return 	result
	 */
	public boolean checkEmail(String email) {
		boolean result = false;
		for (int i = 0; i < memberList.size(); i++) {
			if (email.equals(memberList.get(i).getMem_mail())) {
				result = false;
				break;
			} else {
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * 	회원가입시 받은 모든 정보를 memberList에 추가
	 * 	@param 	mv
	 *  @author park seo kyoung
	 * 	@return 	memberAdd
	 */
	public boolean addMemInfo(MemberVO mv) {
		boolean memberAdd = memberList.add(mv);
		return memberAdd;
	}
	
	
	/**
	 *	캐시충전 금액리스트를 불러오기위한 메서드
	 *	@author 	park seo kyoung
	 * 	@return	charPriceList
	 */
	public List<ChargePriceVO> allChargePrice() {
		return charPriceList;
	}
	
	
	/**
	 * 	캐시충전시 받은 모든 정보를 chargeList에 추가하는 메서드
	 * 	@param 	chargeInfo
	 * 	@author park seo kyoung
	 * 	@return chargeAdd
	 */
	
	public boolean addCash(ChargeVO charInfo) {
		boolean chargeAdd = chargeList.add(charInfo);
		return chargeAdd;
	}
	
	/**
	 * 캐시충전리스트를 불러오는 메서드
	 * @return chargeList
	 */
	public List<ChargeVO> chargeList() {
		return chargeList;
	}
	
	/**
	 * 충전된 금액을 실제 회원정보 보유금액에 추가해주는 메서드
	 * @param 	mem_mail
	 * @param 	money
	 * @return 	true
	 */
	public boolean addMemMoeny(String mem_mail, int money) {
		for (int i = 0; i < memberList.size(); i++) {
			if(mem_mail.equals(memberList.get(i).getMem_mail())){
				memberList.get(i).setMem_money(money + memberList.get(i).getMem_money());
			}
		}
		return true;
	}

	/**
	 * 비밀번호를 재설정하는 메서드(UPDATE)
	 * @param str
	 * @param mem_mail
	 * @return
	 */
	public boolean modifyPw(String str, String mem_mail) {
		for (int i = 0; i < memberList.size(); i++) {
			if(mem_mail.equals(memberList.get(i).getMem_mail())){
				memberList.get(i).setMem_pw(str);
			}
		}
		return true;
	}

	/**
	 * 이름을 재설정하는 메서드(UPDATE)
	 * @param str
	 * @param mem_mail
	 * @return
	 */
	public boolean modifyName(String str, String mem_mail) {
		for (int i = 0; i < memberList.size(); i++) {
			if(mem_mail.equals(memberList.get(i).getMem_mail())){
				memberList.get(i).setMem_name(str);
			}
		}
		return true;
	}

	/**
	 * 생년월일을 재설정하는 메서드(UPDATE)
	 * @param str
	 * @param mem_mail
	 * @return
	 */
	public boolean modifyBir(String str, String mem_mail) {
		for (int i = 0; i < memberList.size(); i++) {
			if(mem_mail.equals(memberList.get(i).getMem_mail())){
				memberList.get(i).setMem_bir(str);
			}
		}
		return true;
	}

	/**
	 * 닉네임을 재설정하는 메서드(UPDATE)
	 * @param str
	 * @param mem_mail
	 * @return
	 */
	public boolean modifyNn(String str, String mem_mail) {
		for (int i = 0; i < memberList.size(); i++) {
			if(mem_mail.equals(memberList.get(i).getMem_mail())){
				memberList.get(i).setMem_nn(str);
			}
		}
		return true;
	}

	
	
	/**
	 * 회원탈퇴시 멤버의 레벨을 변경(UPDATE)
	 * @param mem_mail
	 * @return
	 */
	public void memberLeave(String mem_mail) {
		for(int i = 0; i<memberList.size(); i++){
			if(mem_mail.equals(memberList.get(i).getMem_mail()) && memberList.get(i).getMem_level() == 1){
				memberList.get(i).setMem_level(2);
			}
		}	
	}
	
	/**
	 * 회원 전체 리스트를 불러오기위한 메서드
	 * @return
	 * 같은 기능 메서드 있음: memberPrint() 민하
	 */
	public List<MemberVO> allMemberList() {
		return memberList;
	}

	/**
	 * 회원 전체의 캐시 충전 리스트를 불러오기 위한 메서드
	 * @return
	 */
	public List<ChargeVO> allChargeList() {
		return chargeList;
	}

	/**
	 * 캐시충전 금액 등록
	 * @param addCashPrice
	 * @return
	 */
	public boolean addCashPrice(Map<String, Object> addCashPrice) {
		
		String char_price_cd = (String) addCashPrice.get("char_price_cd");
		Integer char_price = (Integer) addCashPrice.get("char_price");
		
		ChargePriceVO CashPriceAdd = new ChargePriceVO();
		CashPriceAdd.setChar_price(char_price);
		CashPriceAdd.setChar_price_cd(char_price_cd);

		return charPriceList.add(CashPriceAdd);
	}


	/**
	 * 캐시충전 금액 수정
	 * @param input
	 * @param chPrice
	 * @return
	 */
	public boolean modifyCashPrice(int input, String chPrice) {
		for(int i = 0; i < charPriceList.size(); i++){
			if(chPrice.equals(charPriceList.get(i).getChar_price_cd())){
				charPriceList.get(i).setChar_price(input);
			}
		}
		return true;
	}
	
	/**
	 * 캐시충전 금액 삭제
	 * @param input
	 * @param chPrice
	 * @return
	 */

	public boolean delCashPrice(ChargePriceVO charPrice) {
		for(int i = 0; i < charPriceList.size(); i++){
			if(charPrice.equals(charPriceList.get(i))){
				charPriceList.remove(i);
			}
		}
		return true;
	}

	/**
	 * 년도별 매출의 합
	 * @param salse
	 * @return 
	 */
	public int yearSales(String salse) {
		int total = 0;
		for(int i = 0; i < chargeList.size(); i++){
			String dateYear = chargeList.get(i).getCha_date().substring(0, 4);
			if(salse.equals(dateYear)){
				String chaCode = chargeList.get(i).getChar_price_cd();
				for(int j = 0; j < charPriceList.size(); j++){
					if(chaCode.equals(charPriceList.get(j).getChar_price_cd())){
						int chaPrice = charPriceList.get(j).getChar_price();
						total += chaPrice; 
					}
				}
			}
		}
		return total;
	}

	/**
	 * 년도별 달의 매출의 합
	 * @param salse
	 * @return 
	 */
	public int monthSales(String salseY, String salseM) {
		int total = 0;
		for(int i = 0; i < chargeList.size(); i++){
			String dateYear = chargeList.get(i).getCha_date().substring(0, 4);
			String dateMonth = chargeList.get(i).getCha_date().substring(5, 7);
			if(salseY.equals(dateYear) && salseM.equals(dateMonth)){
				String chaCode = chargeList.get(i).getChar_price_cd();
				for(int j = 0; j < charPriceList.size(); j++){
					if(chaCode.equals(charPriceList.get(j).getChar_price_cd())){
						int chaPrice = charPriceList.get(j).getChar_price();
						total += chaPrice; 
					}
				}
			}
		}
		return total;
	}
	

//-----------------------------------서경 끝---------------------------------------------
//------------------------------------------------------------------------------------
//-----------------------------------민하 시작---------------------------------------------
/**
	 * 워터멜론에서 보유한 이용권을 보여줌
	 * @author 유민하
	 * @return 이용권 선택
	 */
	public List<TicketMVO> TicketInfo(){
		return ticketMList;
	}
	
	/**
	 * 구매한 이용권을 회원에게 등록하는 메서드
	 * @author 유민하
	 * @param params
	 * @return
	 */
	public boolean addMyticket(Map<String, String> params) {
		boolean tk = false;
		String tc_date = params.get("tc_date");
		String tcm_cd = params.get("tcm_cd");
		String mem_mail = params.get("mem_mail");
		
		TicketVO tkList = new TicketVO();
		
		int z = ticketList.size(); 
		
		for (int i = 0; i < z+1; i++) {
			//위의 String 값을 넣어줄 새 TicketVO 객체를 만든다
			StringBuffer a = new StringBuffer("f");
			//위에서 새로 만든 객체에 VO의 내용들을 넣어준다
			tkList.setTc_cd(a.append(i).toString());
		}
		tkList.setTc_date(tc_date);
		tkList.setTcm_cd(tcm_cd);
		tkList.setMem_mail(mem_mail);
		//다 넣어줬으면 TicketVO들이 들어가있는 List에 이 객체의 주소를 넣어준다(넣어주는데 성공하면 true라는 반환값이 나온다)
		tk = ticketList.add(tkList);
		return tk;
	}
	
	/**
	 * 사용자가 구매한 이용권의 정보를 가져오는 메서드
	 * @param mem_mail
	 * @return
	 */
	public List<TicketMVO> myticketInfo(String mem_mail) {
		List<TicketMVO> myTicket = new ArrayList<TicketMVO>();
		String tc_date = null;
		String tcm_cd = null;
		
		//1. 회원의 mail로 구매한 이용권 검색
		for (int i = 0; i < ticketList.size(); i++) {
			if (mem_mail.equals(ticketList.get(i).getMem_mail())) {
				tcm_cd = ticketList.get(i).getTcm_cd();
				tc_date = ticketList.get(i).getTc_date();
				for (int j = 0; j < ticketMList.size(); j++) {
					if (tcm_cd.equals(ticketMList.get(j).getTcm_cd())) {
						TicketMVO ticket =new TicketMVO();
						ticket.setTcm_name(ticketMList.get(j).getTcm_name());
						ticket.setTcm_price(ticketMList.get(j).getTcm_price());
						ticket.setTcm_period(tc_date);//구매일을 가지고오기위해 같은타입을 담는 이용기간에 넣어옴
						myTicket.add(ticket);
					}
				}
			}
		}
		return myTicket;
	}
	
	/**
	 * 관리자가 사용자가 구매한 이용권정보를 보기 위한 메서드
	 * @param mem_mail
	 * @return
	 */
	public List<TicketMVO> admintk(String mem_mail) {
		List<TicketMVO> adminTicket = new ArrayList<TicketMVO>();
		String tcm_cd = null;
		
		for (int i = 0; i < ticketList.size(); i++) {
			if (mem_mail.equals(ticketList.get(i).getMem_mail())) {
				tcm_cd = ticketList.get(i).getTcm_cd();
				for (int j = 0; j < ticketMList.size(); j++) {
					if (tcm_cd.equals(ticketMList.get(j).getTcm_cd())) {
						TicketMVO admintk = new TicketMVO();
						admintk.setTcm_name(ticketMList.get(j).getTcm_name());
						admintk.setTcm_price(ticketMList.get(j).getTcm_price());
						admintk.setTcm_period(ticketMList.get(j).getTcm_period());
						adminTicket.add(admintk);
					}
					
				}
			}
		}
		return adminTicket;
	}
	/**
	 *관리자가 사용자가 구매한 이용권 정보중 ticketVO의 정보를 보기위한 메서드
	 * @param mem_mail
	 * @return 
	 */
	public List<TicketVO> admintk2(String mem_mail) {
		List<TicketVO> adminTicket = new ArrayList<TicketVO>();
		
		for (int i = 0; i < ticketList.size(); i++) {
			if (mem_mail.equals(ticketList.get(i).getMem_mail())) {
				TicketVO admintk2 = new TicketVO();
				admintk2.setTc_date(ticketList.get(i).getTc_date());
				adminTicket.add(admintk2);
			}
		}
		return adminTicket;
	}
	
	/**
	 * 관리자가 새로 만든 이용권을 db로 보내주는 메서드
	 * @param ticketM
	 */
	public void addTicketM(TicketMVO ticketM) {
		ticketMList.add(ticketM);		
	}
	
	/**
	 * 관리자가 이용권을 삭제하는 메서드
	 * @param tcm_cd
	 */
	public void tkDelete(String tcm_cd) {
		for (int i = 0; i < ticketMList.size(); i++) {
			if (tcm_cd.equals(ticketMList.get(i).getTcm_cd())) {
				ticketMList.get(i).setTcm_use(0);
			}
		}
		
	}
	
	
	/**
	 * 관리자가 이용권의 이용권가격을 수정하는 메서드
	 * @param tkU
	 * @return
	 */
	public boolean tkpriceUpdate(String tcm_cd, int tcm_price) {
		for (int i = 0; i < ticketMList.size(); i++) {
			if (tcm_cd.equals(ticketMList.get(i).getTcm_cd())) {
				ticketMList.get(i).setTcm_price(tcm_price);
				break;
			}
		}
		return true;
	}
	
	/**
	 * 관리자가 이용권의 이용권사용유무를 수정하는 메서드
	 * @param tkU
	 * @return
	 */
	public boolean tkuseUpdate(String tcm_cd, int tcm_use) {
		for (int i = 0; i < ticketMList.size(); i++) {
			if (tcm_cd.equals(ticketMList.get(i).getTcm_cd())) {
				ticketMList.get(i).setTcm_use(tcm_use);
				break;
			}
		}
		return true;
	}
	
	//관리자가 이용권의 이용권기간을 수정하는 메서드
	public boolean tkperiodUpdate(String tcm_cd, String tcm_period) {
		for (int i = 0; i < ticketMList.size(); i++) {
			if (tcm_cd.equals(ticketMList.get(i).getTcm_cd())) {
				ticketMList.get(i).setTcm_period(tcm_period);
				break;
			}
		}
		return true;
	}
	
	//관리자가 이용권의 이용권명을 수정하는 메서드
	public boolean tknameUpdate(String tcm_cd, String tcm_name) {
		for (int i = 0; i < ticketMList.size(); i++) {
			if (tcm_cd.equals(ticketMList.get(i).getTcm_cd())) {
				ticketMList.get(i).setTcm_name(tcm_name);
				break;
			}
		}
		return true;
	}
	
	//관리자가 이용권의 이용권코드를 수정하는 메서드
	public boolean tkcdUpdate(String tcm_cd, String tcm_cd2) {
		for (int i = 0; i < ticketMList.size(); i++) {
			if (tcm_cd.equals(ticketMList.get(i).getTcm_cd())) {
				ticketMList.get(i).setTcm_cd(tcm_cd2);
				break;
			}
		}
		return true;
	}
	
	//사용자의 이용권 남은기간을 알기위한 메서드
	public List<TicketVO> myTicketInfo2(String mem_mail) {
		List<TicketVO> tkDate = new ArrayList<TicketVO>();
		String tcm_cd = null;
		for (int i = 0; i < ticketList.size(); i++) {
			if (mem_mail.equals(ticketList.get(i).getMem_mail())) {
				tcm_cd = ticketList.get(i).getTcm_cd();
				TicketVO admintk = new TicketVO();
				admintk.setTc_date(ticketList.get(i).getTc_date());
				for (int j = 0; j < ticketMList.size(); j++) {
					if (tcm_cd.equals(ticketMList.get(j).getTcm_cd())) {
						admintk.setTc_cd(ticketMList.get(j).getTcm_period());
						tkDate.add(admintk);
					}
				}
			}
		}
		return tkDate;
	}	
	
	//워터멜론에 가입한 회원의 정보를 볼 수 있는 메서드
	public List<MemberVO> memberPrint() {
		return memberList;
	}
//-----------------------------------민하 끝---------------------------------------------
//------------------------------------------------------------------------------------
//-----------------------------------영춘 시작--------------------------------------------
	/**
	 * 검색 키워드를 받아 음악을 검색하고 결과값을 반환하는 메서드
	 * @author 영춘
	 * @param select
	 * @return List<MusicVO> searchResult
	 */
	public List<MusicVO> searchMusic(String select) {
		List<MusicVO> searchResult = new ArrayList<MusicVO>();
		for (int i = 0; i < getMusicList().size(); i++) {
			if(getMusicList().get(i).getMusic_name().contains(select)){
				searchResult.add(getMusicList().get(i));
			} else if(getMusicList().get(i).getMusic_artist().contains(select)) {
				searchResult.add(getMusicList().get(i));
			} else if(getMusicList().get(i).getMusic_album().contains(select)) {
				searchResult.add(getMusicList().get(i));
			} 
		}
		return searchResult;
	}
	/**
	 * 장르 리스트를 반환하는 메서드
	 * @author 영춘
	 * @return List<GenreVO> genreResult
	 */
	public List<GenreVO> getGenreInfo() {
		List<GenreVO> genreResult = new ArrayList<GenreVO>();
		for (int j = 0; j < genreList.size(); j++) {
			if(genreList.get(j).getGr_use()==0){ // 장르의 삭제 여부 체크
				genreResult.add(genreList.get(j));
			}
		}
		return genreResult;
	}
	/**
	 * 장르코드를 받아 해당 장르의 음악을 반환하는 메서드
	 * @author 영춘
	 * @param gr_cd
	 * @return List<MusicVO> genreResult
	 */
	public List<MusicVO> getGenreMusic(String gr_cd) {
		List<MusicVO> genreResult = new ArrayList<MusicVO>();
		
		for (int i = 0; i < getMusicList().size(); i++) {
			if(getMusicList().get(i).getGr_cd().contains(gr_cd)){
				genreResult.add(getMusicList().get(i));
			}
		}
		return genreResult;
	}
	/**
	 * 회원이메일을 받아 그 회원의 유효한 마이리스트 폴더 목록을 반환하는 메서드
	 * @author 영춘
	 * @param mem_mail
	 * @return List<MyListVO> myListResult
	 */
	public List<MyListVO> getMyListInfo(String mem_mail) {
		List<MyListVO> myListResult = new ArrayList<MyListVO>();
		
		for (int i = 0; i < myListList.size(); i++) {
			if(myListList.get(i).getMem_mail().equals(mem_mail)&&myListList.get(i).getMl_use()==0){
				myListResult.add(myListList.get(i));
			}
		}
		return myListResult;
	}
	
	/**
	 * 음악을 마이리스트에 저장하는 메서드
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean addMyList(Map<Integer, String> params) {
		String mlcd = params.get(0); // 생성할 폴더코드
		String[] musicCd = new String[params.size()-1];
		boolean right = false;
		for (int i = 0; i < musicCd.length; i++) {
			musicCd[i] = params.get(i+1); // 저장할 음악코드들을 String[]로 옮긴다
		}
		int z = myMusicList.size();
		
		for (int i = z+1; i < z+1+musicCd.length; i++) {
			MyMusicVO mm = new MyMusicVO();
			StringBuffer a = new StringBuffer("f");
			mm.setMm_cd(a.append(i).toString()); // 양식에 맞게 마이뮤직코드를 넣어준다(숫자가 늘어난다)
			mm.setMl_cd(mlcd); // 가져온 마이리스트 폴더코드를 넣어준다(반복시에도 유지)
			mm.setMusic_cd(musicCd[i-z-1]); // 가져온 뮤직코드를 넣어준다
			mm.setMm_use(0); // 현재 유효하다는 표시(1번은 삭제된 것)
			right = myMusicList.add(mm);
		}
		
		//MyMusic코드를 양식에 맞게 신규 생성. 새로 만들어진 곳에 생성할 폴더코드 부여. 0번자리 음악코드 넣기.
		//이하 반복. MyMusic코드는 증가, 폴더코드는 유지, i번자리 음악코드 넣기
		
		return right;
	}
	/**
	 * 재생횟수 정렬된 음악차트
	 * @author 영춘
	 * @return List<MusicVO> chartList
	 */
	public List<MusicVO> bestChart() {
		List<MusicVO> chartList = new ArrayList<MusicVO>(); // 결과를 저장할 List
		Map<String, Integer> map = new HashMap<String, Integer>(); // 정렬에 사용될 재생횟수와 음악코드를 담을 HashMap
		
		for (int i = 0; i < getMusicList().size(); i++) {
			map.put(getMusicList().get(i).getMusic_cd(), getMusicList().get(i).getMusic_cnt());
		}
		
		Iterator go = sortByValue(map).iterator(); // 재생횟수 순으로 정렬된 음악코드가 go에 저장
		// musicList를 검색해서 음악코드가 일치하면 저장한다
		String[] arr = new String[10];
		
		for (int i = 0;i<arr.length; i++) {
			arr[i] = (String)go.next();
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < getMusicList().size(); j++) {
				if(getMusicList().get(j).getMusic_cd().equals(arr[i])){
					chartList.add(getMusicList().get(j));
				}
			}
		}
		
		return chartList;
	}
	/**
	 * 재생횟수 정렬
	 * @author 영춘
	 * @param map
	 * @return
	 */
	public static List sortByValue(final Map map) {

		List<String> list = new ArrayList();
		list.addAll(map.keySet());

		Collections.sort(list, new Comparator() {

			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				return ((Comparable) v2).compareTo(v1);

			}
		});

//		Collections.reverse(list); // 주석시 오름차순

		return list;

	}
	/**
	 * 최신곡 정렬된 음악리스트
	 * @author 영춘
	 * @return List<MusicVO> recentList
	 */
	public List<MusicVO> getMusicRecent() {
		List<MusicVO> recentList = new ArrayList<MusicVO>(); // 결과 저장
		Map<String, String> mapd = new HashMap<String, String>(); // 정렬에 사용될 날짜와 음악코드를 담을 HashMap
		
		for (int i = 0; i < getMusicList().size(); i++) {
			mapd.put(getMusicList().get(i).getMusic_cd(), getMusicList().get(i).getMusic_date());
		}
		
		Iterator sbd = sortByDate(mapd).iterator(); // 날짜순으로 정렬된 음악코드가 저장됨
		
		String[] arr = new String[5];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (String)sbd.next();
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < getMusicList().size(); j++) {
				if(getMusicList().get(j).getMusic_cd().equals(arr[i])){
					recentList.add(getMusicList().get(j));
				}
			}
		}
		return recentList;
	}
	
	/**
	 * 발매날짜순 음악 정렬
	 * @author 영춘
	 * @param map
	 * @return list
	 */
	public static List sortByDate(final Map mapd) {

		List<String> list = new ArrayList();
		list.addAll(mapd.keySet());

		Collections.sort(list, new Comparator() {

			public int compare(Object o1, Object o2) {
				Object v1 = mapd.get(o1);
				Object v2 = mapd.get(o2);
				return ((Comparable) v2).compareTo(v1);

			}
		});
		return list;
	}
	
	/**
	 * 재생횟수를 db의 MusicList에 추가
	 * @author 영춘
	 * @param List<MusicVO> play
	 * @return
	 */
	public boolean setMusicCount(MusicVO play) {
		for (int i = 0; i < musicList.size(); i++) {
			if(play.getMusic_cd().equals(musicList.get(i).getMusic_cd())) {
				musicList.get(i).setMusic_cnt(musicList.get(i).getMusic_cnt()+1);
			}
		}
		return false;
	}
	
	/**
	 * 입력받은 장르코드가 존재하는지 체크
	 * @author 영춘
	 * @param gr_cd
	 * @return
	 */
	public boolean grcdCheck(String gr_cd) {
		boolean right = false;
		for (int i = 0; i < getGenreInfo().size(); i++) {
			if(getGenreInfo().get(i).getGr_cd().equals(gr_cd)) { // 장르 유효성 검사는 getGenreInfo()에서 이미 수행함
				right = true;
			}
		}
		return right;
	}
	
	/**
	 * 입력받은 음악정보를 MusicList에 추가
	 * @author 영춘
	 * @param muv
	 * @return
	 */
	public boolean addMusicInfo(MusicVO muv) {
		
		boolean right = false;
		int z = musicList.size()+1;
		
		MusicVO nm = new MusicVO();
		StringBuffer a = new StringBuffer("a");
		nm.setMusic_cd(a.append(z).toString());
		nm.setMusic_name(muv.getMusic_name());
		nm.setMusic_artist(muv.getMusic_artist());
		nm.setMusic_date(muv.getMusic_date());
		nm.setMusic_album(muv.getMusic_album());
		nm.setMusic_cps(muv.getMusic_cps());
		nm.setMusic_lyr(muv.getMusic_lyr());
		nm.setMusic_cnt(0);
		nm.setGr_cd(muv.getGr_cd());
		nm.setMusic_use(0);
		
		right = musicList.add(nm);
		
		return right;
	}
	
	/**
	 * 삭제 여부가 체크된 모든 음악 리스트 출력
	 * @author 영춘
	 * @return List<MusicVO> mList
	 */
	public List<MusicVO> getMusicList() {
		List<MusicVO> mList = new ArrayList<MusicVO>();
		for (int i = 0; i < musicList.size(); i++) {
			if(musicList.get(i).getMusic_use()==0){ // 음악의 삭제 여부 체크
				mList.add(musicList.get(i));
			}
		}
		return mList;
	}
	
	/**
	 * 음악코드가 유효한가 체크
	 * @author 영춘
	 * @param music_cd
	 * @return
	 */
	public boolean mlcdCheck(String music_cd) {
		boolean right = false;
		for (int i = 0; i < getMusicList().size(); i++) {
			if(getMusicList().get(i).getMusic_cd().equals(music_cd)) {
				right = true;
			}
		}
		return right;
	}
	
	/**
	 * 음악코드로 음악을 찾아 곡명 수정
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean musicNameChange(Map<String, String> params) {
		String music_cd = params.get("music_cd");
		String music_name = params.get("music_name");
		boolean right = false;
		
		for (int i = 0; i < musicList.size(); i++) {
			if(musicList.get(i).getMusic_cd().equals(music_cd)){
				musicList.get(i).setMusic_name(music_name);
				right = true;
			} 
		}
		return right;
	}
	/**
	 * 음악코드로 음악을 찾아 아티스트 수정
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean musicArtistChange(Map<String, String> params) {
		String music_cd = params.get("music_cd");
		String music_artist = params.get("music_artist");
		boolean right = false;
		
		for (int i = 0; i < musicList.size(); i++) {
			if(musicList.get(i).getMusic_cd().equals(music_cd)){
				musicList.get(i).setMusic_artist(music_artist);
				right = true;
			} 
		}
		return right;
	}
	/**
	 * 음악코드로 음악을 찾아 발매일 수정
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean musicDateChange(Map<String, String> params) {
		String music_cd = params.get("music_cd");
		String music_date = params.get("music_date");
		boolean right = false;
		
		for (int i = 0; i < musicList.size(); i++) {
			if(musicList.get(i).getMusic_cd().equals(music_cd)){
				musicList.get(i).setMusic_date(music_date);
				right = true;
			} 
		}
		return right;
	}
	/**
	 * 음악코드로 음악을 찾아 앨범명 수정
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean musicAlbumChange(Map<String, String> params) {
		String music_cd = params.get("music_cd");
		String music_album = params.get("music_album");
		boolean right = false;
		
		for (int i = 0; i < musicList.size(); i++) {
			if(musicList.get(i).getMusic_cd().equals(music_cd)){
				musicList.get(i).setMusic_album(music_album);
				right = true;
			} 
		}
		return right;
	}
	/**
	 * 음악코드로 음악을 찾아 작곡가 수정
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean musicCpsChange(Map<String, String> params) {
		String music_cd = params.get("music_cd");
		String music_cps = params.get("music_cps");
		boolean right = false;
		
		for (int i = 0; i < musicList.size(); i++) {
			if(musicList.get(i).getMusic_cd().equals(music_cd)){
				musicList.get(i).setMusic_cps(music_cps);
				right = true;
			}
		}
		return right;
	}
	/**
	 * 음악코드로 음악을 찾아 작사가 수정
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean musicLyrChange(Map<String, String> params) {
		String music_cd = params.get("music_cd");
		String music_lyr = params.get("music_lyr");
		boolean right = false;
		
		for (int i = 0; i < musicList.size(); i++) {
			if(musicList.get(i).getMusic_cd().equals(music_cd)){
				musicList.get(i).setMusic_lyr(music_lyr);
				right = true;
			} 
		}
		return right;
	}
	/**
	 * 음악코드로 음악을 찾아 장르코드 수정
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean grcdChange(Map<String, String> params) {
		String music_cd = params.get("music_cd");
		String gr_cd = params.get("gr_cd");
		boolean right = false;
		
		for (int i = 0; i < musicList.size(); i++) {
			if(musicList.get(i).getMusic_cd().equals(music_cd)){
				musicList.get(i).setGr_cd(gr_cd);
				right = true;
			}
		}
		return right;
	}
	/**
	 * 음악코드로 음악을 찾아 삭제
	 * @author 영춘
	 * @param music_cd
	 * @return
	 */
	public boolean deleteMusic(String music_cd) {
		boolean right = false;
		for (int i = 0; i < musicList.size(); i++) {
			if(musicList.get(i).getMusic_cd().equals(music_cd)){
				musicList.get(i).setMusic_use(1);
				right = true;
			} 
		}
		return right;
	}
//-------------------------장르관리--------------------
	/**
	 * 새로운 장르 생성
	 * @author 영춘
	 * @param genre_name
	 * @return
	 */
	public boolean genreAdd(String genre_name) {
		boolean right = false;
		//장르명 중복체크
		for (int i = 0; i < genreList.size(); i++) {
			if(genreList.get(i).getGr_name().equals(genre_name)){
				return right;
			} 
		}

		GenreVO ng = new GenreVO();
		StringBuffer a = new StringBuffer("G");
		ng.setGr_cd(a.append(genreList.size()+1).toString());
		ng.setGr_name(genre_name);
		ng.setGr_use(0);
		
		right = genreList.add(ng);

		return right;
	}
	/**
	 * 장르명 수정
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean grNameChange(Map<String, String> params) {
		// 장르코드에 해당하는 장르명을 새 값으로 바꿔준다
		String gr_cd = params.get("gr_cd");
		String gr_name = params.get("gr_name");
		boolean right = false;

		//장르명 중복체크
		for (int i = 0; i < genreList.size(); i++) {
			if(genreList.get(i).getGr_name().equals(gr_name)){
				return right;
			}
		}
		//장르명 변경
		for (int i = 0; i < genreList.size(); i++) {
			if(genreList.get(i).getGr_cd().equals(gr_cd)){
				genreList.get(i).setGr_name(gr_name);
				right = true;
			}
		}
		return right;
	}
	
	/**
	 * 삭제할 장르에 해당하는 곡이 있는지 체크
	 * @author 영춘
	 * @param gr_cd
	 * @return
	 */
	public boolean checkGenreMusic(String gr_cd) {
		boolean right = true;
		
		for (int i = 0; i < getMusicList().size(); i++) {
			if(getMusicList().get(i).getGr_cd().equals(gr_cd)){
				right = true;
				return right;
			} else {
				right = false;
			}
		}
		return right;
	}
	
	/**
	 * 장르 삭제
	 * @author 영춘
	 * @param gr_cd
	 * @return
	 */
	public boolean genreDelete(String gr_cd) {
		boolean right = false;
		
		for (int i = 0; i < genreList.size(); i++) {
			if(genreList.get(i).getGr_cd().equals(gr_cd)){
				genreList.get(i).setGr_use(1);
				right = true;
			} 
		}
		return right;
	}
	//-------------------------마이리스트관리--------------------
	/**
	 * 마이리스트 폴더코드를 매개변수로 폴더 안의 음악을 출력. 삭제여부 체크
	 * @author 영춘
	 * @param ml_cd
	 * @return List<MusicVO> music
	 */
	public List<MusicVO> folderMusicList(String ml_cd) {
		List<MusicVO> music = new ArrayList<MusicVO>(); // 최종결과저장
		
		String[] musicCode = new String[myMusicList.size()];
		
		for (int i = 0; i < myMusicList.size(); i++) {
			// 폴더코드가 일치하면 음악코드를 뽑아 배열에 저장한다 + 삭제여부 체크 부분
			if(myMusicList.get(i).getMl_cd().equals(ml_cd)&&myMusicList.get(i).getMm_use()==0){
				musicCode[i] = myMusicList.get(i).getMusic_cd();
			}
		}
		for (int i = 0; i < musicCode.length; i++) {
			// 음악코드 배열값과 일치하는 음악리스트 값을 music리스트로 옮긴다
			if(musicCode[i]!=null){
				for (int j = 0; j < getMusicList().size(); j++) {
					if(musicCode[i].equals(getMusicList().get(j).getMusic_cd())){
						music.add(getMusicList().get(j));
					} else {
						continue;
					}
				}
			}
		}
		return music;
	}
	
	/**
	 * 마이리스트 폴더코드를 찾아 폴더명을 수정
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean folderModify(Map<String, String> params) {
		String ml_cd = params.get("ml_cd");
		String folder_name = params.get("folder_name");
		boolean right = false;
		
		for (int i = 0; i < myListList.size(); i++) {
			if(myListList.get(i).getMl_cd().equals(ml_cd)){
				myListList.get(i).setMl_name(folder_name);
				right = true;
			}
		}
		return right;
	}
	/**
	 * 폴더코드와 음악코드가 일치하는 마이리스트 폴더 내 음악을 찾아 삭제
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean musicDel(Map<String, String> params) {
		String ml_cd = params.get("ml_cd");
		String music_cd = params.get("music_cd");
		boolean right = false;
		
		for (int i = 0; i < myMusicList.size(); i++) {
			if(myMusicList.get(i).getMl_cd().equals(ml_cd) && myMusicList.get(i).getMusic_cd().equals(music_cd)){
				myMusicList.get(i).setMm_use(1);
				right = true;
			}
		}
		return right;
	}
	
	/**
	 * 마이리스트 폴더 추가
	 * @author 영춘
	 * @param params
	 * @return
	 */
	public boolean myListAdd(Map<String, String> params) {
		//ml_cd는 자동생성. ml_name과 mem_mail은 입력받은 값
		String folderName = params.get("folderName");
		String mem_mail = params.get("mem_mail");
		boolean right = false;
		
		MyListVO ml = new MyListVO();
		StringBuffer a = new StringBuffer("e");
		ml.setMl_cd(a.append(myListList.size()+1).toString());
		ml.setMl_name(folderName);
		ml.setMem_mail(mem_mail);
		ml.setMl_use(0);
		right = myListList.add(ml);
		
		return right;
	}
	
	/**
	 * 마이리스트 폴더 삭제
	 * @author 영춘
	 * @param ml_cd
	 * @return
	 */
	public boolean folderDelete(String ml_cd) {
		boolean right = false;

		for (int i = 0; i < myListList.size(); i++) {
			if(myListList.get(i).getMl_cd().equals(ml_cd)){
				myListList.get(i).setMl_use(1);
				right = true;
			}
		}
		return right;
	}
	/**
	 * 이용권 소지 유무 체크
	 * @param mem_mail
	 * @return
	 */
	public boolean getTicketCorrect(String mem_mail) {
		boolean right = false;
		for (int i = 0; i < ticketList.size(); i++) {
			if(mem_mail.equals(ticketList.get(i).getMem_mail())){
				right = true;
			}
		}
		
		return right;
	}

	/**
	 * 이용권 구매시 보유 캐시에서 이용권 금액 차감
	 * @param tcm_cd
	 * @return 
	 */
	public int cashDeduction(Map<String, Object> price) {
		String tcm_cd = (String) price.get("tcm_cd");
		String mem_mail = (String) price.get("mem_mail");
		int payPrice = 0;
		int p = 0;
		
		MemberVO memvo = new MemberVO();
		
		for(int j = 0; j < ticketMList.size(); j++){
			if(tcm_cd.equals(ticketMList.get(j).getTcm_cd())){
				p = ticketMList.get(j).getTcm_price();
				//System.out.println(p+"::: 가져온금액");
			}
		}
	
		for(int k = 0; k < memberList.size(); k++){
			if(mem_mail.equals(memberList.get(k).getMem_mail())){
				memvo.setMem_money(memberList.get(k).getMem_money());
				payPrice = memvo.getMem_money() - p;
				memberList.get(k).setMem_money(payPrice);
				//System.out.println(payPrice+"::: 뺀 금액");
			}
		}
		
		
		memvo.setMem_money(payPrice);
		return payPrice;
	}

	/**
	 * 캐시 충전 리스트 - 사용자
	 * @param mem_mail
	 * @return
	 */
	public int cashConfirmList(String mem_mail) {	
		int total = 0;
		for(int i = 0; i < chargeList.size(); i++){
			if(mem_mail.equals(chargeList.get(i).getMem_mail())){
				String chaCode = chargeList.get(i).getChar_price_cd();
				for(int j = 0; j < charPriceList.size(); j++){
					if(chaCode.equals(charPriceList.get(j).getChar_price_cd())){
						int chaPrice = charPriceList.get(j).getChar_price();
						total += chaPrice; 
					}
				}
			}
		}
		return total;
	}
}
	
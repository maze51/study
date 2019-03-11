package waterMelon.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import waterMelon.service.IService;
import waterMelon.service.IServiceImpl;
import waterMelon.vo.ChargePriceVO;
import waterMelon.vo.ChargeVO;
import waterMelon.vo.GenreVO;
import waterMelon.vo.MemberVO;
import waterMelon.vo.MusicVO;
import waterMelon.vo.MyListVO;
import waterMelon.vo.TicketMVO;
import waterMelon.vo.TicketVO;

/**
 * console에 출력하거나 입력받기 위한 클래스
 * @author 1team
 * @since 2019.02.21
 */
public class ViewClass{

	private IService service = new IServiceImpl();
	private MemberVO memberInfo = null;
	private int cashDeduction = 0;

	/**
	 *  프로그램 시작 메서드
	 *  @author 1team
	 *  @since 2019.02.21
	 */
	public void startMethod() {
		while(true){
			System.out.println("────────────── WATERMELON ──────────────");

			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");

			Scanner sc = new Scanner(System.in);

			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (select) {
			case 1:
				login();
				break;

			case 2:
				join();
				break;

			default:
				numSelectErr();
			}
		}
	}

	/**
	 * 회원가입을 위한 메서드
	 * @since 2019.02.21
	 * @author 1team
	 */
	private void join() {
		System.out.println("────────────── 회원가입 ──────────────");
		String mail = addMail();
		String pw = addPw();
		String name = addName();
		String bir = addBir();
		String nn = addNn();

		MemberVO mv = new MemberVO();

		mv.setMem_mail(mail);
		mv.setMem_pw(pw);
		mv.setMem_name(name);
		mv.setMem_bir(bir);
		mv.setMem_nn(nn);
		mv.setMem_level(1);

		System.out.println("회원 등록이 완료되었어요! 와라메론 플레이어를 즐겨 BOA요");
		service.addMemInfo(mv);
	}
	/**
	 * 이메일아이디입력
	 * @return
	 */
	private String addMail() {	
		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			System.out.println("사용하실 이메일을 입력해주세요.");
			str = sc.next();
			Pattern regex = Pattern.compile(RegEx.regMail);
			Matcher match = regex.matcher(str);
			regEx = match.matches();
			if(regEx == true){
				if(service.checkEmail(str)){
					break;
				}else{
					System.out.println("이미 등록된 이메일 입니다.");
					continue;
				}
			}else{
				System.out.println("이메일 형식에 맞지 않습니다.");
			}
		}
		return str;
	}

	/**
	 * 비밀번호입력
	 * @return
	 */
	private String addPw() {
		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			System.out.println("비밀번호를 입력해 주세요.");
			System.out.println("영문(대문자 포함), 숫자, 특수문자 조합, 9~12자리");
			str = sc.next();
			Pattern regex = Pattern.compile(RegEx.regPW);
			Matcher match = regex.matcher(str);
			regEx = match.matches();
			if(regEx == true){
				break;
			}else{
				System.out.println("비밀번호 형식에 맞지 않습니다.");
			}
		}
		return str;
	}

	/**
	 * 이름입력
	 * @return
	 */
	private String addName() {
		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			System.out.println("이름을 입력해 주세요.");
			str = sc.next();
			Pattern regex = Pattern.compile(RegEx.regName);
			Matcher match = regex.matcher(str);
			regEx = match.matches();

			if(regEx == true){
				break;
			}else{
				System.out.println("형식에 맞지 않는 이름입니다. 다시 입력해주세요.");
			}
		}
		return str;
	}

	/**
	 * 생년월일입력
	 * @return
	 */

	private String addBir() {
		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			System.out.println("생년월일을 입력해주세요. ex) 720324");
			str = sc.next();
			Pattern regex = Pattern.compile(RegEx.regBir);
			Matcher match = regex.matcher(str);
			regEx = match.matches();

			if(regEx == true){
				break;
			}else{
				System.out.println("생년월일 형식과 맞지 않습니다.");
			}
		}
		return str;
	}

	/**
	 * 닉네임입력	
	 * @return
	 */

	//특문 제외 2자 ~ 20자
	private String addNn() {
		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			System.out.println("사용하실 닉네임을 입력해주세요. 한글만 2자 ~ 20자");
			str = sc.next();
			Pattern regex = Pattern.compile(RegEx.regNn);
			Matcher match = regex.matcher(str);
			regEx = match.matches();
			if(regEx == true){
				break;
			}else{
				System.out.println("형식에 맞지 않습니다. 다시 입력해 주세요.");
			}
		}
		return str;
	}


	/**
	 * 로그인을 위한 메서드
	 * @author 1team
	 * @since 2019.02.21
	 */
	private void login() {
		while(true){
			System.out.println("────────────── 로그인 ──────────────");
			String mem_mail = getMemberID();
			String mem_pw = getMemberPW();


			Map<String, String> params = new HashMap<String, String>();
			params.put("mem_mail", mem_mail);
			params.put("mem_pw", mem_pw);

			MemberVO result = service.getMemberInfo(params);

			if(result==null){
				System.out.println("해당 회원이 존재하지 않습니다.");
			} else {
				memberInfo = result;
				if(memberInfo.getMem_level() == 0){
					System.out.println(result.getMem_name()+"님 환영합니다.");
					managerMenu();
					break;
				}else if(memberInfo.getMem_level() == 1){
					System.out.println(result.getMem_name()+"님 환영합니다.");
					memberMenu();
					break;
				}else{
					System.out.println("────────────────────────────");
					System.out.println("탈퇴한 회원입니다.");
					continue;
				}	
			}
		}
	}

	/**
	 * 로그인시 아이디를 입력받는 메서드
	 * @author 1team
	 * @since 2019.02.21
	 * @return
	 */
	private String getMemberID() {
		Scanner sc = new Scanner(System.in);
		System.out.println("회원 이메일을 입력해 주세요.");
		String mem_mail = sc.next();
		return mem_mail;
	}

	/**
	 * 로그인시 비밀번호를 입력받는 메서드
	 * @author 1team
	 * @since 2019.02.21
	 * @return
	 */
	private String getMemberPW() {
		Scanner sc = new Scanner(System.in);
		System.out.println("회원 비밀번호를 입력해주세요");
		String mem_pw = sc.next();
		return mem_pw;
	}

	//로그인&회원가입 끝------------------------------------------------------------------------------------
	/* MEMBER START */

	/**
	 * 사용자 메뉴
	 * @author 1team
	 * @since 2019.02.21
	 */
	private void memberMenu() {
		while(true){
			System.out.println("────────────── 사용자메뉴 ──────────────");
			System.out.println("	1. 음악리스트");
			System.out.println("	2. 음악검색");
			System.out.println("	3. MYLIST");
			System.out.println("	4. 캐시충전");
			System.out.println("	5. 이용권구매");
			System.out.println("	6. 회원정보조회");
			System.out.println("	7. 로그아웃");
			System.out.println("─────────────────────────────────────");
			System.out.println("번호를 입력해 주세요");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch(select){
			case 1:
				musicList();
				break;
			case 2:
				musicSearch();
				break;
			case 3:
				myList(memberInfo.getMem_mail());
				break;
			case 4:
				cashCharge();
				break;
			case 5:
				ticketBuy();
				break;
			case 6:
				memberInfo();
				break;
			case 7:
				logout();
				break;
			default:
				numSelectErr();
			}
		}

	}
	/**
	 * 차트, 최신곡, 장르별 음악을 이용하기 위한 메서드
	 * @author 영춘
	 * @since 2019.02.21
	 */
	private void musicList() {
		while(true){
			System.out.println("───────MusicList─────────");
			System.out.println("	1. 워터멜론차트");
			System.out.println("	2. 최신음악");
			System.out.println("	3. 장르별음악");
			System.out.println("	4. 뒤로가기");
			System.out.println("─────────────────────────");
			System.out.print("번호를 입력해주세요>");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}
			switch(select){
			case 1:
				wmChart();
				break;
			case 2:
				recentMusic();
				break;
			case 3:
				genreMusic();
				break;
			case 4:
				memberMenu();
				break;
			default:
				numSelectErr();
				continue;
			}
		}
	}

	/**
	 * 워터멜론차트를 보여주기 위한 메서드
	 * @author 영춘
	 * @since 2019.02.21
	 */
	private void wmChart() {
		while(true){
			System.out.println("───────WaterMelonChart─────────");
			List<MusicVO> chart = service.bestChart();
			for (int i = 0; i < chart.size(); i++) {
				System.out.println(i+1+". "+chart.get(i).getMusic_name()+"\t\t"+chart.get(i).getMusic_artist()+"\t\t"+chart.get(i).getMusic_album()+"\t\t"+chart.get(i).getMusic_cnt());
			}

			System.out.println("	1. 전체재생");
			System.out.println("	2. MYLIST로 담기");
			System.out.println("	3. 선택듣기/담기");
			System.out.println("	4. 뒤로가기");
			System.out.print("번호를 입력해주세요>");

			//SQL
			//워터멜론차트 중에서  많이 플레이 된 순으로 순위를 매겨서 불러오기위한 메서드
			//size를써서 순서 매겨서 출력, musicvo에서 곡명,아티스트,앨범명 순으로 출력

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch(select){
			case 1:
				playMusic(chart);
				break;
			case 2:
				addMyList(chart);
				break;
			case 3:
				selectMusic(chart);
				break;
			case 4:
				musicList();
				break;
			default:
				numSelectErr();
				continue;
			}
		}
	}
	/**
	 * 최신곡을 보여주기 위한 메서드
	 * @author 영춘
	 * @since 2019.02.24
	 */
	private void recentMusic() {
		while(true){
			System.out.println("───────최신곡─────────");

			List<MusicVO> musicRecent = service.getMusicRecent();

			for (int i = 0; i < musicRecent.size(); i++) {
				System.out.println(i+1+". "+musicRecent.get(i).getMusic_name()+"\t\t"+musicRecent.get(i).getMusic_artist()+"\t\t"+musicRecent.get(i).getMusic_album()+"\t\t"+musicRecent.get(i).getMusic_date());
			}

			System.out.println("	1. 전체재생");
			System.out.println("	2. MYLIST에 담기");
			System.out.println("	3. 선택듣기/담기");
			System.out.println("	4. 뒤로가기");
			System.out.print("번호를 입력해주세요>");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch(select){
			case 1:
				playMusic(musicRecent);
				break;
			case 2:
				addMyList(musicRecent);
				break;
			case 3:
				selectMusic(musicRecent);
				break;
			case 4:
				musicList();
				break;
			default:
				numSelectErr();
				continue;
			}
		}
	}
	/**
	 * 모든 장르를 출력하고, 한 장르를 선택받는 메서드
	 * @author 영춘
	 * @since 2019.02.24
	 */
	private void genreMusic() {
		while(true){
			List<GenreVO> genreInfo = service.getGenreInfo();
			System.out.println("───────장르별 음악─────────");
			for (int i = 0; i < genreInfo.size(); i++) {
				System.out.println(i+1+". "+genreInfo.get(i).getGr_name());
			}
			System.out.println(genreInfo.size()+1+". 뒤로가기");

			System.out.print("번호를 입력해주세요>");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}
			switch(select-1){

			default:
				if(select-1 > genreInfo.size()) {
					System.out.println("다시 입력해주세요.");
					continue;
				} else if(select==genreInfo.size()+1){
					return;
				} else {
					genreList(select-1);
				}
			}
		}
	}
	/**
	 * 선택한 장르별 곡을 보여주기 위한 메서드
	 * @author 영춘
	 * @since 2019.02.24
	 * @param int number
	 */
	private void genreList(int select) {
		while(true){
			// SQL
			// select넘어온 int값으로 장르명을 찾아 db로. db에서 장르명이 일치하는 곡을 검색하여 돌아온다 
			List<GenreVO> genreInfo = service.getGenreInfo(); // 위에서 긁어온 장르List전체
			String grCd = genreInfo.get(select).getGr_cd(); // 해당 장르코드를 저장할 변수
			List<MusicVO> seletedGenreM = service.getGenreMusic(grCd);

			System.out.println("───────"+genreInfo.get(select).getGr_name()+"─────────");

			for (int i = 0; i < seletedGenreM.size(); i++) {
				System.out.println(seletedGenreM.get(i).getMusic_name()+"\t\t"+seletedGenreM.get(i).getMusic_artist()+"\t\t"+seletedGenreM.get(i).getMusic_album());
			}
			System.out.println();
			System.out.println("	1. 전체재생");
			System.out.println("	2. MYLIST로 담기");
			System.out.println("	3. 선택듣기/담기");
			System.out.println("	4. 뒤로가기");
			System.out.print("번호를 입력해주세요>");

			Scanner sc = new Scanner(System.in);
			int select1 = 0;
			try {
				select1 = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch(select1){
			case 1:
				playMusic(seletedGenreM);
				break;
			case 2:
				addMyList(seletedGenreM);	// 전체담기는 MYLIST에 담기로 바꾸기
				break;
			case 3:
				selectMusic(seletedGenreM);
				break;
			case 4:
				return;
			default:
				numSelectErr();
				continue;
			}
		}
	}
	//사용자메뉴1 뮤직리스트 끝------------------------------------------------------------------------------------
	/**
	 * 음악 검색을 위한 메서드
	 * @author 영춘
	 * @since 2019.02.24
	 */
	private void musicSearch() {
		while(true){
			System.out.println("───────음악검색─────────");
			System.out.println("곡명, 아티스트, 앨범으로 검색하실 수 있습니다");
			System.out.println("검색어를 입력해주세요");
			System.out.println("이전 메뉴로 돌아가려면 1번을 입력해주세요");

			Scanner sc = new Scanner(System.in);
			String select = null; // 입력된 검색어를 저장할 변수
			select = sc.next();

			if(select.equals("1")){
				return;
			}
			//SQL
			List<MusicVO> musicSearch = service.searchMusic(select);
			if(musicSearch.size()==0){
				System.out.println("검색 결과가 없습니다.");
				continue;

			} else {
				System.out.println("───────검색결과─────────");
				for (int i = 0; i < musicSearch.size(); i++) {
					System.out.println(musicSearch.get(i).getMusic_name()+"\t\t"+musicSearch.get(i).getMusic_artist()+"\t\t"+musicSearch.get(i).getMusic_album());
				}
			}

			System.out.println("	1. 전체재생");
			System.out.println("	2. MYLIST로 담기");
			System.out.println("	3. 선택듣기/담기");
			System.out.println("	4. 다시검색");
			System.out.println("	5. 사용자메뉴로 돌아가기");
			System.out.print("번호를 입력해주세요>");

			Scanner sc1 = new Scanner(System.in);
			int select1 = 0;
			try {
				select1 = sc1.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
			}

			switch(select1){
			case 1:
				playMusic(musicSearch);
				break;
			case 2:
				addMyList(musicSearch);
				break;
			case 3:
				selectMusic(musicSearch);
				break;
			case 4:
				continue;
			case 5:
				return;
			default:
				numSelectErr();
				
			}
		} // end of while
	}

	/**
	 * 리스트에서 특정 음악을 선택해 듣거나 리스트에 담는 메서드
	 * @author 영춘
	 * @since 2019.02.24
	 */
	private void selectMusic(List<MusicVO> selectedMusic) {
		while(true){
			for (int i = 0; i < selectedMusic.size(); i++) { // 넘어온 음악 리스트를 쭉 띄워준다
				System.out.println(i+1+". "+selectedMusic.get(i).getMusic_name()+"\t\t"+selectedMusic.get(i).getMusic_artist()+"\t\t"+selectedMusic.get(i).getMusic_album());
			}
			System.out.println(selectedMusic.size()+1+". 뒤로가기");

			System.out.println("음악을 선택해주세요. 여러 음악을 선택한다면 쉼표(,)로 구분해주세요");

			Scanner sc = new Scanner(System.in);
			String select = sc.next();

			boolean result = false; //정규식으로 미리 걸러 예외 차단하기

			Matcher select2 = RegEx.selectMusic.matcher(select);
			result = select2.matches();
			if(result == true){
				String[] arr = select.split(","); // 검색어를 쉼표 단위로 분리해 저장할 변수

				if(arr.length==1&&Integer.parseInt(arr[0])==selectedMusic.size()+1){ // 뒤로가기 처리용
					return;
				}
				int[] iarr = new int[arr.length]; // String[]을 int[]로 변환해 저장할 변수

				for (int i = 0; i < arr.length; i++) {
					iarr[i] = Integer.parseInt(arr[i]); // 검색어를 숫자로 변환해 int[]배열 각 방에 저장
				}

				List<MusicVO> selectResult = new ArrayList<MusicVO>(); // 입력받은 검색어로 추출된 음악을 저장할 List
				for (int i = 0; i < iarr.length; i++) {
					try {
						selectResult.add(selectedMusic.get(iarr[i]-1));
					} catch (Exception e) {
						System.out.println("없는 곡입니다. 이전 메뉴로 돌아갑니다.");
						return;
					}
				}

				System.out.println("곡이 선택되었습니다");
				while(true){
					System.out.println("1. 선택듣기");
					System.out.println("2. My리스트에 담기");
					System.out.println("3. 뒤로가기");

					Scanner sc1 = new Scanner(System.in);
					int select1 = 0;
					try {
						select1 = sc1.nextInt();
					} catch (Exception e) {
						System.out.println("다시 입력해주세요.");
						continue;
					}

					switch(select1){
					case 1:
						playMusic(selectResult);
						break;
					case 2:
						addMyList(selectResult);
						break;
					case 3:
						return;
					default:
						numSelectErr();
						continue;
					}
				} // end of innerWhile
			} else {
				System.out.println("검색어 조건과 맞지 않습니다. 다시 입력하세요.");
				continue;
			}
		} // end of outerWhile
	} // end of selectMusic

	/**
	 * 음악을 My리스트에 저장하기 위한 메서드
	 * @author 영춘
	 * @since 2019.02.24
	 * @param List<MusicVO> a
	 */
	private void addMyList(List<MusicVO> goList) {
		while(true){
			System.out.println("───────"+memberInfo.getMem_name()+"의 마이리스트───────");
			List<MyListVO> result = service.getMyListInfo(memberInfo.getMem_mail());

			for (int i = 0; i < result.size(); i++) {
				System.out.println(i+1+". "+result.get(i).getMl_name()+"\t");
			}
			System.out.println("어떤 폴더에 담으시겠습니까?");

			System.out.println(result.size()+1+". 뒤로가기");
			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch(select-1) {
			default:
				if(select-1 > result.size() || select-1 < 0) { // 폴더 크기보다 입력값이 크거나 0이하의 값이 입력되면 다시
					System.out.println("다시 입력해주세요.");
					continue;
				} else if (select==result.size()+1) { // 넣었던 뒤로가기 번호에 해당
					return;
				} else {
					String[] musicCd = new String[goList.size()]; // 음악코드를 넣을 배열
					String mlcd = result.get(select-1).getMl_cd(); // 번호에 해당하는 폴더코드를 추출한다.
					for (int i = 0; i < goList.size(); i++) {
						musicCd[i] = goList.get(i).getMusic_cd(); // goList의 음악코드를 추출한다.
					}
					Map<Integer, String> params = new HashMap<Integer, String>();
					params.put(0, mlcd);
					for (int i = 1; i < musicCd.length+1; i++) {
						params.put(i, musicCd[i-1]);
					}
					boolean result1 = service.addMyList(params);
					if(result1){
						System.out.println("MYLIST에 담았습니다");
					} else {
						System.out.println("MYLIST에 담지 못했습니다");
					}
				} // end of else
			}
		}
	}

	/**
	 * 재생을 위한 메서드
	 * @author 영춘
	 * @since 2019.02.24
	 * @param List<MusicVO> playList
	 */
	private void playMusic(List<MusicVO> play) {

		if(service.getTicketCorrect(memberInfo.getMem_mail())){ // 이용권 보유 여부 체크

		} else {
			System.out.println("이용권이 없으면 재생할 수 없습니다");
			return;
		}

		boolean pause = true; // 재생-일시정지 유무 변경
		boolean repeat = false; // 전체반복 유무 변경
		MusicVO nowPlay = play.get(0); // 현재 재생곡이 저장되는 장소
		int nowPlayPos = 0; // 현재 재생곡의 List play에서의 위치
		//재생횟수 count 추가: nowPlayPos 숫자가 변경될 때 play에서 그 위치의 행을 들고 간다
		service.setMusicCount(play.get(nowPlayPos));

		while(true){
			System.out.println("<<Now Playing>>\n"+nowPlay.getMusic_name()+"\t\t"+nowPlay.getMusic_artist());
			if(pause){
				System.out.print("▶");
			} else {
				System.out.print("||");
			}
			if(repeat){
				System.out.println("\t전체반복중");
			} else {
				System.out.println();
			}
			//반복설정X일 때 맨 앞, 맨 뒤 곡은 넘어가지 않는다
			//전체반복 선택시 list의 맨 앞, 맨 뒤 곡에서 이전곡/다음곡을 선택하면 해당 위치로 이동하도록 지정

			System.out.println("────────────────");
			System.out.println("	1. 재생/일시정지");
			System.out.println("	2. 이전곡");
			System.out.println("	3. 다음곡");
			System.out.println("	4. 전체반복/전체반복끄기");
			System.out.println("	5. 플레이리스트보기");
			System.out.println("	6. 메뉴로 돌아가기");
			System.out.println("────────────────");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
			}

			switch(select){
			case 1:
				pause = !pause;
				break;
			case 2:
				if(repeat==true && nowPlayPos==0) { // 전체반복설정 & 리스트 맨 앞
					nowPlayPos=play.size()-1;
					nowPlay = play.get(nowPlayPos);
					service.setMusicCount(play.get(nowPlayPos));
					continue;
				} else if(nowPlayPos==0) { // 반복설정 X & 리스트 맨 앞
					System.out.println("이전곡이 없습니다.");
					continue;
				}
				if(play.size()==1) { // 한 곡만 있을 때
					service.setMusicCount(play.get(nowPlayPos));
					break;
				}
				nowPlayPos -= 1;
				service.setMusicCount(play.get(nowPlayPos));
				nowPlay = play.get(nowPlayPos);
				continue;

			case 3:
				if(repeat==true && nowPlayPos==play.size()-1) { // 전체반복설정&리스트 맨 끝
					nowPlayPos=0;
					nowPlay = play.get(nowPlayPos);
					service.setMusicCount(play.get(nowPlayPos));
					continue;
				} else if (nowPlayPos==play.size()-1) { // 반복설정 X & 리스트 맨 끝
					System.out.println("다음곡이 없습니다.");
					continue;
				}
				if(play.size()==1) { // 한 곡만 있을 때
					service.setMusicCount(play.get(nowPlayPos));
					break;
				}
				nowPlayPos += 1;
				nowPlay = play.get(nowPlayPos);
				service.setMusicCount(play.get(nowPlayPos));
				continue;
			case 4:
				repeat = !repeat;
				break;
			case 5:
				System.out.println("───────플레이리스트───────");
				for (int j = 0; j < play.size(); j++) {
					System.out.println(play.get(j).getMusic_name()+"\t\t"+play.get(j).getMusic_artist()+"\t\t"+play.get(j).getMusic_album());
				}
				System.out.println();
				break;
			case 6:
				return;
			default:
				numSelectErr();
				continue;
			}
		}
	}
	//사용자메뉴2 음악검색&음악재생 끝------------------------------------------------------------------------------------
	/**
	 *	MYLIST 주 메뉴
	 * 	@author 영춘
	 *  @since	2019.02.24
	 */
	private void myList(String mem_mail) {
		while(true){
			System.out.println("───────MYLIST─────────");
			System.out.println("1. MYLIST 폴더목록보기");
			System.out.println("2. MYLIST 폴더추가");
			System.out.println("3. MYLIST 폴더삭제");
			System.out.println("4. 메뉴로 가기");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (select) {
			case 1:
				myListFolder(mem_mail);
				break;
			case 2:
				myListAdd(mem_mail);
				break;
			case 3:
				folderDelCheck(mem_mail);
				break;
			case 4:
				return;
			default:
				numSelectErr();
				continue;
			}
		}
	}


	/**
	 *	MYLIST 폴더 목록 출력
	 * 	@author 영춘
	 *  @since	2019.02.24
	 */
	private void myListFolder(String mem_mail) {
		while(true){
			System.out.println("───────"+memberInfo.getMem_name()+"의 마이리스트───────");
			List<MyListVO> myListFolder = service.getMyListInfo(mem_mail);

			for (int i = 0; i < myListFolder.size(); i++) {
				System.out.println(i+1+". "+myListFolder.get(i).getMl_name()+"\t");
			}

			System.out.println(myListFolder.size()+1+". 뒤로가기");
			System.out.println("폴더를 선택해 주세요.");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (select-1) {
			default:
				if(select-1 > myListFolder.size() || select-1 < 0) { // 폴더 크기보다 입력값이 크거나 0이하의 값이 입력되면 다시
					System.out.println("다시 입력해주세요.");
					continue;
				} else if (select==myListFolder.size()+1) { // 넣었던 뒤로가기 번호에 해당
					return;
				} else {
					myFolder(myListFolder.get(select-1).getMl_cd()); // 선택한 마이리스트 폴더코드를 들고 이동
				}
			}
		}
	}

	/**
	 *	MYLIST 폴더 선택 후 하위메뉴
	 * 	@author 영춘
	 *  @since	2019.02.24
	 */
	private void myFolder(String ml_cd) {
		while(true){
			//매개변수로 받은 폴더코드를 갖고 가서 폴더에 해당하는 음악을 뿌려준다
			List<MusicVO> folderMusicList = service.folderMusicList(ml_cd); 

			//		System.out.println("───────폴더명─────────");
			if(folderMusicList.size()==0){
				System.out.println("폴더에 음악이 없습니다.");
			} else {
				for (int i = 0; i < folderMusicList.size(); i++) {
					System.out.println(folderMusicList.get(i).getMusic_name()+"\t\t"+folderMusicList.get(i).getMusic_artist()+"\t\t"+folderMusicList.get(i).getMusic_album());
				}
			}

			System.out.println("──────────────────────────────");
			System.out.println("1. 전체듣기");
			System.out.println("2. 선택듣기/담기");
			System.out.println("3. 폴더명 수정");
			System.out.println("4. 음악삭제");
			System.out.println("5. MYLIST 폴더목록으로 가기");
			System.out.println("──────────────────────────────");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (select) {
			case 1:
				if(folderMusicList.size()==0){
					System.out.println("재생할 음악이 없습니다.");
					break;
				}
				playMusic(folderMusicList);
				break;
			case 2:
				if(folderMusicList.size()==0){
					System.out.println("재생할 음악이 없습니다.");
					break;
				}
				selectMusic(folderMusicList);
				break;
			case 3:
				folderModify(ml_cd);
				break;
			case 4:
				musicCheck(ml_cd);
				break;
			case 5:
				return;
			default:
				numSelectErr();
				continue;
			}
		}
	}

	/**
	 *	MYLIST 폴더명 수정
	 * 	@author 영춘
	 *  @since	2019.02.24
	 */
	private void folderModify(String ml_cd) {
		System.out.println("───────폴더명 수정─────────");
		System.out.println("수정할 폴더명을 적어주세요.");

		Scanner sc = new Scanner(System.in);
		String folder_name = sc.next();

		// 폴더코드와 입력받은 폴더명을 들고간다
		Map<String, String> params = new HashMap<String, String>();
		params.put("ml_cd", ml_cd);
		params.put("folder_name", folder_name);

		// 폴더코드를 찾아 폴더명을 수정한다
		boolean result = service.folderModify(params);

		if(result) {
			System.out.println("폴더명을 수정하였습니다.");
			return;
		} else {
			System.out.println("수정에 실패했습니다.");
			return;
		}
	}

	/**
	 *	MYLIST에서 삭제할 음악을 확인하는 메서드
	 * 	@author 영춘
	 *  @since	2019.02.24
	 */
	private void musicCheck(String ml_cd) {
		while(true){
			System.out.println("───────음악삭제─────────");

			//폴더의 삭제 여부가 체크된 음악목록을 띄워주고 선택지를 받는다
			List<MusicVO> folderMusicList = service.folderMusicList(ml_cd); 

			//System.out.println("───────폴더명─────────");
			for (int i = 0; i < folderMusicList.size(); i++) {
				System.out.println(i+1+". "+folderMusicList.get(i).getMusic_name()+"\t"+folderMusicList.get(i).getMusic_artist()+"\t"+folderMusicList.get(i).getMusic_album());
			}
			System.out.println("삭제할 음악을 선택하세요.");
			System.out.println(folderMusicList.size()+1+". 뒤로가기");

			// switch default 활용
			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch(select-1) {
			default:
				if(select-1 > folderMusicList.size() || select-1 < 0) { // 폴더 크기보다 입력값이 크거나 0이하의 값이 입력되면 다시
					System.out.println("다시 입력해주세요.");
					continue;
				}  else if (select==folderMusicList.size()+1) { // 넣었던 뒤로가기 번호에 해당
					return;
				} else {
					musicDel(ml_cd, folderMusicList.get(select-1).getMusic_cd()); // 삭제할 음악코드를 넘겨준다
				}
			}
		}
	}

	/**
	 * 마이리스트에서 음악을 삭제하는 메서드
	 * @author 영춘
	 * @param ml_cd1
	 */
	void musicDel(String ml_cd, String music_cd){
		//폴더코드와 음악코드를 들고 db가서 mm_use를 바꿔준다
		Map<String, String> params = new HashMap<String, String>();
		params.put("ml_cd", ml_cd);
		params.put("music_cd", music_cd);

		boolean result = service.musicDel(params);

		if(result) {
			System.out.println("음악을 삭제하였습니다.");
			return;
		} else {
			System.out.println("삭제에 실패했습니다.");
			return;
		}
	}

	/**
	 *	MYLIST 폴더 추가
	 * 	@author 영춘
	 *  @since	2019.02.24
	 */
	private void myListAdd(String mem_mail) {
		Scanner sc = new Scanner(System.in);
		System.out.println("───────폴더추가─────────");
		System.out.println("추가할 폴더의 이름을 입력하세요.");
		System.out.println("이전 메뉴로 돌아가시려면 'b'를 입력해주세요");
		//폴더 코드는 자동생성. 폴더이름은 여기서 입력받기. 메일은 매개변수에서 가져오기
		String folderName = sc.next();

		if(folderName.equals("b")){
			return;
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("folderName", folderName);
		params.put("mem_mail", mem_mail);

		boolean result = service.myListAdd(params);

		if(result) {
			System.out.println("폴더 추가가 완료되었습니다.");
			return;
		} else {
			System.out.println("폴더 추가에 실패했습니다.");
			return;
		}
	}

	/**
	 *	MYLIST에서 삭제할 폴더 확인
	 * 	@author 영춘
	 *  @since	2019.02.24
	 */
	private void folderDelCheck(String mem_mail){
		//mem_mail에 맞는 폴더목록을 띄워준다
		while(true){
			System.out.println("───────"+memberInfo.getMem_name()+"의 마이리스트───────");
			List<MyListVO> myListFolder = service.getMyListInfo(mem_mail);

			for (int i = 0; i < myListFolder.size(); i++) {
				System.out.println(i+1+". "+myListFolder.get(i).getMl_name()+"\t");
			}

			System.out.println("폴더를 선택해 주세요.");
			System.out.println(myListFolder.size()+1+". 뒤로가기");
			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (select-1) {
			default:
				if(select-1 > myListFolder.size() || select-1 < 0) { // 폴더 크기보다 입력값이 크거나 0이하의 값이 입력되면 다시
					System.out.println("다시 입력해주세요.");
					continue;
				} else if (select==myListFolder.size()+1) { // 넣었던 뒤로가기 번호에 해당
					return;
				} else {
					folderDelete(myListFolder.get(select-1).getMl_cd()); // 선택한 마이리스트 폴더코드와 매개변수를 들고 이동
				}
			}
		}
	}

	/**
	 * 마이리스트 폴더를 삭제
	 * @author 영춘
	 */

	private void folderDelete(String ml_cd) {
		boolean result = service.folderDelete(ml_cd);

		if(result) {
			System.out.println("폴더 삭제가 완료되었습니다.");
			return;
		} else {
			System.out.println("폴더 삭제에 실패했습니다.");
			return;
		}
	}
	//사용자메뉴3 마이리스트 끝------------------------------------------------------------------------------------
	/**
	 *	캐시를 충전할 수 있는 메서드
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.24
	 */
	private void cashCharge() {
		while(true){
			System.out.println("------------------- 캐시충전 -------------------");	
			System.out.println("현재 "+memberInfo.getMem_nn()+"님의 보유캐시는 "+memberInfo.getMem_money()+"원 입니다.");
			System.out.println("충전 금액을 선택해 주세요.");

			List<ChargePriceVO> chargePrice = service.allChargePrice(); //ChargePriceVO의 리스트를 가져옴

			int cnt = 0; //번호증가
			for (int i = 0; i < chargePrice.size(); i++) {
				System.out.println((cnt+1)+"."+ service.allChargePrice().get(i).getChar_price()+"원");
				cnt++;
			}
			System.out.println(chargePrice.size()+1+"."+"뒤로가기");

			Scanner sc = new Scanner(System.in);		
			try {
				cnt = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
			}


			switch (cnt) {
			default:
				if(cnt > chargePrice.size()){
					if(chargePrice.size()+1 == cnt){
						return;
					}
					numSelectErr();
				}else{
					ChargePriceVO charPrice = service.allChargePrice().get(cnt-1);
					System.out.println(cnt+"."+charPrice.getChar_price()+"원을 충전하시겠습니까?");
					chargeSelect(charPrice.getChar_price_cd());
				}
				break;
			}

		}

	}


	/**
	 *	캐시충전 금액 선택 후 충전 여부 선택
	 * 	@author 	park seo kyoung
	 * 	@param 	char_price_cd 
	 *  @since	2019.02.24
	 */
	private void chargeSelect(String char_price_cd) {
		while(true){
			//회원 이메일, 충전금액, 충전일 
			ChargeVO charInfo = new ChargeVO();
			List<ChargePriceVO> chargePrice = service.allChargePrice();

			int money = 0;
			int cha_ch = 0;

			for(int i = 0; i < chargePrice.size(); i++){
				if(char_price_cd.equals(chargePrice.get(i).getChar_price_cd())){
					money = chargePrice.get(i).getChar_price();
					cha_ch = chargePrice.size()+i;
				}
			}

			charInfo.setCha_cd("n"+cha_ch);
			charInfo.setMem_mail(memberInfo.getMem_mail());
			charInfo.setChar_price_cd(char_price_cd);
			charInfo.setCha_date(setCha_date());

			System.out.println("1. 충전할래요!");
			System.out.println("2. 다시생각해볼래요!");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;

			}

			switch (select) {
			case 1:
				if (service.addCash(charInfo)) {
					service.addMemMoeny(memberInfo.getMem_mail(), money);
					System.out.println(money+"원 충전이 완료 되었습니다.");
					System.out.println("현재 "+memberInfo.getMem_nn()+"님의 보유캐시는 "+memberInfo.getMem_money()+"원 입니다.");
					return;
				} else {
					System.out.println("캐시 충전에 실패하였습니다.");
					return;
				}

				//break;
			case 2:
				return;
			default:
				numSelectErr();
				continue;
			}
		}
	}

	/**
	 * 충전날짜생성
	 * @return
	 */
	private String setCha_date() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String toDay = date.format(new Date());
		return toDay;
	}
	//사용자메뉴4 캐시충전 끝------------------------------------------------------------------------------------

	/**
	 * 이용권을 구매할 수 있는 메서드
	 * @author	유민하
	 * @since	2019.02.24
	 */
	private void ticketBuy() {
		while(true){
			System.out.println("───────이용권구매─────────");
			System.out.println("이용권을 선택해 주세요.");
			// 워터멜론에서 제공하는 이용권 목록을 보여줌
			List<TicketMVO> ticketPrint = service.getTicketInfo();
			int cnt = 0;
			for (int i = 0; i < ticketPrint.size(); i++) {
				if (ticketPrint.get(i).getTcm_use()==1) {
					System.out.println((cnt+1)+"."+ticketPrint.get(i).getTcm_name()+ticketPrint.get(i).getTcm_period()+ticketPrint.get(i).getTcm_price()+"원");
					cnt++;
				}
			}
			System.out.println(cnt+1+". 뒤로가기");//항상 이용권 다음에 뒤로가기가 올 수 있도록 설정해줌

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (select) {
			default:
				if (select>ticketPrint.size()+1) {
					System.out.println("번호를 다시 눌러주세요");
				} else if(select==ticketPrint.size()+1){
					return;
				} else {
					TicketMVO selectTicket = service.getTicketInfo().get(select-1);
					System.out.println("캐쉬에서"+selectTicket.getTcm_price()+"원이 차감됩니다. 그래도 구매하시겠습니까?");
					buySelect(selectTicket.getTcm_cd());
				}
				break;
			}
		}
	}

	/**
	 *	이용권 구매 확인
	 * 	@author	유민하
	 *	@param	Tcm_cd
	 *  @since	2019.02.24
	 */
	private void buySelect(String Tcm_cd) {
		while(true){
			System.out.println("1. 구매할래요!");
			System.out.println("2. 다시생각해볼래요!");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}


			switch (select) {
			case 1:
				TicketMVO selectTicket = service.getTicketInfo().get(select-1);
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				String tc_date = date.format(new Date()); //이용권구매일
				String tcm_cd = Tcm_cd; //이용권코드
				String mem_mail = memberInfo.getMem_mail(); //회원이메일
				//db에서 tc_cd추가하기

				//추가: 여기서 체크해서 잔액 부족하면 막기
				if(memberInfo.getMem_money() < selectTicket.getTcm_price()){
					System.out.println("캐시 잔액이 부족합니다");
					return;
				}

				Map<String, String> params = new HashMap<String, String>();

				params.put("tc_date", tc_date);
				params.put("tcm_cd", tcm_cd);
				params.put("mem_mail", mem_mail);

				Map<String, Object> price = new HashMap<String, Object>();
				price.put("tcm_cd", tcm_cd);
				price.put("mem_mail", mem_mail);
				cashDeduction = service.cashDeduction(price);

				if(service.addMyticket(params)){
					if(memberInfo.getMem_money() >= selectTicket.getTcm_price()){
						System.out.println("구매에 성공하였습니다. 현재 캐시 잔액은"+cashDeduction+"원 입니다.");
						memberMenu();
					}
				}else{
					System.out.println("구매에 실패하였습니다.");
				}

				return;
			case 2:
				System.out.println("뒤로 돌아갑니다.");
				return;
			default:
				numSelectErr();
			}
		}	
	}
	//사용자메뉴5 이용권구매 끝------------------------------------------------------------------------------------

	/**
	 *	회원정보조회
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.24
	 */
	private void memberInfo() {
		while(true){
			System.out.println("────────────────── 회원정보조회 ──────────────────");
			System.out.println("회원님의 정보를 보호하기 위해 비밀번호를 한번 더 확인합니다.");
			System.out.println("이전 메뉴로 돌아가려면 1번을 입력해주세요");

			Scanner sc = new Scanner(System.in);
			String input = null;
			try {
				input = sc.next();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}
			if(input.equals("1")){
				return;
			}
			if(memberInfo.getMem_pw().equals(input)){
				memberInfoSelect();
			}else{
				System.out.println("비밀번호가 맞지 않습니다. 다시 입력해주세요.");
			}
		}
	}

	/**
	 *	회원정보조회 메뉴
	 * 	@author 	park seo kyoung
	 * 	@param char_price_cd 
	 *  @since	2019.02.24
	 */
	private void memberInfoSelect() {
		while(true){
			System.out.println("────────────────── 회원정보메뉴 ──────────────────");
			System.out.println("1. 회원정보수정");
			System.out.println("2. 캐시확인");
			System.out.println("3. 이용권확인");
			System.out.println("4. 회원탈퇴");
			System.out.println("5. 뒤로가기");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}

			switch (select) {
			case 1:
				memInfoModify();
				break;
			case 2:
				cashConfirm(memberInfo.getMem_mail());
				break;
			case 3:
				ticketConfirm();
				break;
			case 4:
				memCancel();
				break;
			case 5:
				return;
			default:
				numSelectErr();
			}
		}
	}


	/**
	 *	회원정보수정
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.27
	 */
	private void memInfoModify() {
		while(true){
			System.out.println("────────────────── 회원정보수정 ──────────────────");
			System.out.println("회원님께서 수정할 회원정보 번호를 선택해 주세요.");
			System.out.println("1. 비밀번호 수정");
			System.out.println("2. 이름 수정");
			System.out.println("3. 생년월일 수정");
			System.out.println("4. 닉네임 수정");
			System.out.println("5. 회원정보 메뉴로 돌아가기");

			Scanner sc = new Scanner(System.in);
			int select = sc.nextInt();

			switch (select) {
			case 1:		
				memModifyPW();
				break;
			case 2:		
				memModifyName();
				break;
			case 3:
				memModifyBir();
				break;
			case 4:
				memModifyNn();
				break;
			case 5:	
				return;
			default:
				numSelectErr();
				continue;
			}
		}
	}

	/**
	 * 회원정보수정 - 비밀번호 수정
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.27
	 */
	private void memModifyPW() {
		System.out.println("────────────────── 비밀번호재설정 ──────────────────");
		System.out.println("비밀번호를 입력해 주세요.");
		System.out.println("영문(대문자 포함), 숫자, 특수문자 조합, 9~12자리");

		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			System.out.println("사용하실 비밀번호를 입력해주세요.");
			str = sc.next();
			regEx = Pattern.matches(RegEx.regPW, str);
			if(regEx == true){
				break;
			}else{
				System.out.println("비밀번호 형식에 맞지 않습니다.");
			}
		}

		if(service.modifyPw(str, memberInfo.getMem_mail())){
			System.out.println("비밀번호 재설정을 완료하였습니다.");
		}else{
			System.out.println("비밀번호를 재설정하지 못했습니다.");
		}
	}

	/**
	 * 회원정보수정 - 이름 수정
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.27
	 */
	private void memModifyName() {
		System.out.println("────────────────── 이름수정 ──────────────────");
		System.out.println("수정할 이름을 입력해 주세요. 한글 2~4자");

		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			str = sc.next();
			regEx = Pattern.matches(RegEx.regName, str);
			if(regEx == true){
				break;
			}else{
				System.out.println("이름 형식에 맞지 않습니다.");
			}
		}

		if(service.modifyName(str, memberInfo.getMem_mail())){
			System.out.println("이름을 수정하였습니다.");
		}else{
			System.out.println("이름을 수정하지 못했습니다.");
		}
	}

	/**
	 * 회원정보수정 - 생년월일수정
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.27
	 */
	private void memModifyBir() {
		System.out.println("────────────────── 생년월일수정 ──────────────────");
		System.out.println("수정할 생년월일을 입력해 주세요. ex)950520");

		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			str = sc.next();
			regEx = Pattern.matches(RegEx.regBir, str);
			if(regEx == true){
				break;
			}else{
				System.out.println("생년월일 형식에 맞지 않습니다.");
			}
		}

		if(service.modifyBir(str, memberInfo.getMem_mail())){
			System.out.println("생년월일을 수정하였습니다.");
		}else{
			System.out.println("생년월일을 수정하지 못했습니다.");
		}
	}

	/**
	 * 회원정보수정 - 닉네임 수정
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.27
	 */
	private void memModifyNn() {
		System.out.println("────────────────── 닉네임수정 ──────────────────");
		System.out.println("수정할 닉네임을 입력해 주세요. 한글 2~20자");

		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			str = sc.next();
			regEx = Pattern.matches(RegEx.regNn, str);
			if(regEx == true){
				break;
			}else{
				System.out.println("닉네임 형식에 맞지 않습니다.");
			}
		}

		if(service.modifyNn(str, memberInfo.getMem_mail())){
			System.out.println("닉네임을 수정하였습니다.");
		}else{
			System.out.println("닉네임을 수정하지 못했습니다.");
		}
	}

	/**
	 *	충전한 캐시 금액과, 충전 내역이 출력.
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.24
	 */
	private void cashConfirm(String mem_mail) {
		while(true){		

			int chMoeny = service.cashConfirmList(mem_mail);

			List<ChargeVO> chargeList = service.chargeList();
			List<ChargePriceVO> chargePrice = service.allChargePrice();	

			System.out.println("---------------- 나의캐시확인 ----------------");
			int totalCash = cashDeduction+chMoeny;
			System.out.println(memberInfo.getMem_nn()+"님의 현재 보유캐시는 "+memberInfo.getMem_money()+"원 입니다.");
			System.out.println(memberInfo.getMem_nn()+"님의 총 충전캐시는 "+totalCash+"원 입니다.\n");

			System.out.println("---------------- 캐시충전내역 ----------------");
			System.out.println("\t캐시금액\t\t충전일");

			if(chMoeny != 0){
				for(int i = 0; i < chargeList.size(); i++){
					if(mem_mail.equals(chargeList.get(i).getMem_mail())){
						String chaCode = chargeList.get(i).getChar_price_cd();
						for(int j = 0; j < chargePrice.size(); j++){
							if(chaCode.equals(chargePrice.get(j).getChar_price_cd())){
								int money = chargePrice.get(j).getChar_price();
								String charDate = chargeList.get(i).getCha_date();
								System.out.println("\t"+money+"\t\t"+charDate);
							}
						}
					}
				}
				System.out.println("-------------------------------------------");
				System.out.println("\t총합계\t\t"+chMoeny);
			}else{
				System.out.println("캐시 충전 내역이 없습니다.");
			}


			System.out.println("—————————————————————");
			System.out.println("1. 회원정보메뉴로 돌아가기");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (select) {
			case 1:	
				return;
			default:
				numSelectErr();
			}
		}
	}
	/**
	 *	현재 보유하고 있는 이용권과 이용권 구매 내역이 출력
	 * 	@author	유민하
	 *  @since	2019.02.24
	 */
	private void ticketConfirm() {
		while(true){

			System.out.println("───────이용권구매내역─────────");
			System.out.println("이용권\t구매금액\t구매일");
			//나의 이용권구매내역 출력
			List<TicketMVO> myTicket = service.myTicketInfo(memberInfo.getMem_mail());
			for (int i = 0; i < myTicket.size(); i++) {
				System.out.println(myTicket.get(i).getTcm_name()+"\t"+myTicket.get(i).getTcm_price()+"\t"+myTicket.get(i).getTcm_period());
			}

			System.out.println("1. 회원정보메뉴로 돌아가기");
			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (select) {
			case 1:	
				return;
			default:
				numSelectErr();
			}
		}
	}

	/**
	 *	회원탈퇴
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.24
	 */
	private void memCancel() {
		while(true){
			System.out.println("────────────────── 회원탈퇴 ──────────────────");
			System.out.println("정말 탈퇴하시겠습니까?");
			System.out.println("1. 네. 탈퇴할래요.");
			System.out.println("2. 아니요. 와라메론플레이어는 제인생의 최고 플레이어예요.");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (select) {
			case 1:	
				service.memberLeave(memberInfo.getMem_mail());
				System.out.println("이제는 우리가 헤어져야 할시간 다음에 또만나용~");
				startMethod();
				break;
			case 2:		
				System.out.println("탁월한 선택이군요?");
				return;
			default:
				numSelectErr();
				return;
			}
		}
	}
	//사용자메뉴6 회원정보조회 끝------------------------------------------------------------------------------------

	/**
	 *	로그아웃
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.24
	 */
	private void logout() {
		while(true){
			System.out.println("────────────────── 로그아웃 ──────────────────");
			System.out.println("로그아웃 하시겠습니까?");
			System.out.println("1. 로그아웃");
			System.out.println("2. 사용자메뉴로 돌아가기");

			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (select) {
			case 1:
				System.out.println("로그아웃 되었습니다. 초기화면으로 이동합니다.");
				memberInfo = null;
				startMethod();
				break;
			case 2:
				return;
			default:
				numSelectErr();
				continue;
			}
		}
	}
	//사용자메뉴7 로그아웃 끝------------------------------------------------------------------------------------
	//관리자메뉴 시작------------------------------------------------------------------------------------
	/**
	 * 관리자메뉴
	 * @author 유민하
	 */
	private void managerMenu() {
		while(true){
			System.out.println("─────────────────────────");
			System.out.println("   1. 음악관리");
			System.out.println("   2. 회원관리");
			System.out.println("   3. 캐시충전내역");
			System.out.println("   4. 캐시충전금액관리");
			System.out.println("   5. 이용권구매내역");
			System.out.println("   6. 이용권관리");
			System.out.println("   7. 매출관리");
			System.out.println("   8. 로그아웃");
			System.out.println("─────────────────────────");
			System.out.print("번호를 입력해주세요>");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("번호를 입력해주세요.");
				continue;
			}
			switch(select){
			case 1:
				musicManage();
				break;
			case 2:
				memManage();
				break;
			case 3:
				cashCargeList();
				break;
			case 4:
				cashChargePrice();
				break;
			case 5:
				ticketBuyList();
				break;
			case 6:
				ticketMange();
				break;
			case 7:
				saleManage();
				break;
			case 8:
				admLogout();
				break;
			default:
				numSelectErr();

			}
		}
	}

	/**
	 * 음악관리를 위한 메서드
	 * @author 영춘
	 */
	private void musicManage() {
		while(true){
			System.out.println("────────음악관리───────────");
			System.out.println("   1. 음악등록");
			System.out.println("   2. 음악수정");
			System.out.println("   3. 음악삭제");
			System.out.println("   4. 장르관리");
			System.out.println("   5. 뒤로가기");
			System.out.println("─────────────────────────");
			System.out.print("번호를 입력해주세요>");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("번호를 입력해주세요.");
				continue;
			}
			switch(select){
			case 1:
				musicAdd();
				break;
			case 2:
				musicChange();
				break;
			case 3:
				musicDelete();
				break;
			case 4:
				genreManage();
				break;
			case 5:
				return;
			default:
				numSelectErr();
				continue;
			}
		}
	}

	/**
	 * 음악관리에서 음악등록을 위한 메서드
	 * @author 영춘
	 */
	private void musicAdd() {

		String mName = musicNameAdd();
		String mArtist = musicArtistAdd();
		String mDate = musicDateAdd();
		String mAlbum = musicAlbumAdd();
		String mCps = musicCpsAdd();
		String mLyr = muscicLyrAdd();
		String gCd = grcdAdd();

		MusicVO muv = new MusicVO();

		muv.setMusic_name(mName);
		muv.setMusic_artist(mArtist);
		muv.setMusic_date(mDate);
		muv.setMusic_album(mAlbum);
		muv.setMusic_cps(mCps);
		muv.setMusic_lyr(mLyr);
		muv.setGr_cd(gCd);

		boolean result = service.addMusicInfo(muv);

		if(result) {
			System.out.println("음악을 등록했습니다.");
		} else {
			System.out.println("등록에 실패했습니다.");
		}
	}
	/**
	 * 추가할 음악의 곡명을 입력하는 메서드
	 * @author 유민하
	 */
	private String musicNameAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 음악의 곡명을 입력하세요");
		String music_name = sc.next();
		return music_name;
	}
	/**
	 * 추가할 음악의 아티스트를 입력하는 메서드
	 * @author 유민하
	 */
	private String musicArtistAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 음악의 아티스트를 입력하세요");
		String music_artist = sc.next();
		return music_artist;
	}
	/**
	 * 추가할 음악의 발매일을 입력하는 메서드
	 * @author 영춘
	 */
	private String musicDateAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 음악의 발매일을 입력하세요(예: 2019-09-03)");
		boolean result = false;
		while(true){
			String music_date = sc.next();
			Matcher select = RegEx.musicDate.matcher(music_date);
			result = select.matches();

			if(result == true) {
				return music_date;
			} else {
				System.out.println("다시 입력해주세요");
				continue;
			}
		}
	}
	/**
	 * 추가할 음악의 앨범을 입력하는 메서드
	 * @author 유민하
	 */
	private String musicAlbumAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 음악의 앨범명을 입력하세요");
		String music_album = sc.next();
		return music_album;
	}
	/**
	 * 추가할 음악의 작곡가를 입력하는 메서드
	 * @author 유민하
	 */
	private String musicCpsAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 음악의 작곡가를 입력하세요");
		String music_cps = sc.next();
		return music_cps;
	}
	/**
	 * 추가할 음악의 작사가를 입력하는 메서드
	 * @author 유민하
	 */
	private String muscicLyrAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 음악의 작사가를 입력하세요");
		String music_lyr = sc.next();
		return music_lyr;
	}
	/**
	 * 추가할 음악의 장르코드를 입력하는 메서드
	 * @author 영춘
	 */
	private String grcdAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 음악의 장르코드를 입력하세요(예: G10)");
		while(true){
			String gr_cd = sc.next();
			boolean result = service.grcdCheck(gr_cd);

			if(result){
				return gr_cd;
			} else {
				System.out.println("다시 입력해주세요");
				continue;
			}
			//넘어가서 존재하는 장르코드인가 체크
			//boolean을 반환받아 true면 return, false면 "다시 입력해주세요"
		}
	}
	/**
	 * 음악관리에서 음악수정을 위한 메서드
	 * @author 영춘
	 */
	private void musicChange() {
		while(true){
			System.out.println("────────음악리스트──────────");
			List<MusicVO> musicAList = service.getMusicList();

			for (int i = 0; i < musicAList.size(); i++) {
				System.out.println(i+1+". "+musicAList.get(i).getMusic_cd()+"\t"+musicAList.get(i).getMusic_name()+"\t"+musicAList.get(i).getMusic_artist()+"\t"+musicAList.get(i).getMusic_album());
			}

			System.out.println("─────────────────────────");
			System.out.println("수정할 음악의 음악코드를 입력해주세요");
			System.out.println("이전 메뉴로 돌아가려면 1번을 입력해주세요");

			Scanner sc = new Scanner(System.in);

			String music_cd = sc.next();

			if(music_cd.equals("1")){
				return;
			}
			boolean result = service.mlcdCheck(music_cd); // 음악코드 유효성검사

			if(result){
				adminMusicChange(music_cd);
			} else {
				System.out.println("다시 입력해주세요");
				continue;
			}
		}

	}
	/**
	 * 음악수정 시 하위 메뉴를 띄워주기 위한 메서드
	 * @author 영춘 
	 * @param music_cd
	 */
	private void adminMusicChange(String music_cd) {
		while(true){
			System.out.println("──────────음악수정─────────");
			System.out.println("   1. 곡명수정");
			System.out.println("   2. 아티스트수정");
			System.out.println("   3. 발매일수정");
			System.out.println("   4. 앨범명수정");
			System.out.println("   5. 작곡가수정");
			System.out.println("   6. 작사가수정");
			System.out.println("   7. 장르코드수정");
			System.out.println("   8. 뒤로가기");
			System.out.println("─────────────────────────");
			System.out.print("번호를 입력해주세요>");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("번호를 입력해주세요.");
			}

			switch(select){
			case 1:
				musicNameChange(music_cd);
				break;
			case 2:
				musicArtistChange(music_cd);
				break;
			case 3:
				musicDateChange(music_cd);
				break;
			case 4:
				musicAlbumChange(music_cd);
				break;
			case 5:
				musicCpsChange(music_cd);
				break;
			case 6:
				musicLyrChange(music_cd);
				break;
			case 7:
				grcdChange(music_cd);
				break;
			case 8:
				return;
			default:
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}
	}
	/**
	 * 음악관리에서 입력받은 음악코드의 곡명을 수정하는 메서드
	 * @author 영춘
	 * 
	 */
	private void musicNameChange(String music_cd) {

		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 곡명을 입력하세요");
		String music_name = sc.next();

		Map<String, String> params = new HashMap<String, String>();
		params.put("music_cd", music_cd);
		params.put("music_name", music_name);

		boolean result = service.musicNameChange(params);

		if(result) {
			System.out.println("수정이 완료됐습니다.");
			return;
		} else {
			System.out.println("수정에 실패했습니다.");
			return;
		}

	}
	/**
	 * 음악관리에서 입력받은 음악코드의 아티스트를 수정하는 메서드
	 * @author 영춘
	 * 
	 */
	private void musicArtistChange(String music_cd) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 아티스트를 입력하세요");
		String music_artist = sc.next();

		Map<String, String> params = new HashMap<String, String>();
		params.put("music_cd", music_cd);
		params.put("music_artist", music_artist);

		boolean result = service.musicArtistChange(params);

		if(result) {
			System.out.println("수정이 완료됐습니다.");
			return;
		} else {
			System.out.println("수정에 실패했습니다.");
			return;
		}
	}

	/**
	 * 음악관리에서 입력받은 음악코드의 발매일을 수정하는 메서드
	 * @author 영춘
	 * 
	 */
	private void musicDateChange(String music_cd) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 발매일을 입력하세요(예: 2019-09-03)");
		boolean res = false;
		while(true){
			String music_date = sc.next();
			Matcher select = RegEx.musicDate.matcher(music_date);
			res = select.matches();

			if(res == true) {
				Map<String, String> params = new HashMap<String, String>();
				params.put("music_cd", music_cd);
				params.put("music_date", music_date);

				boolean result = service.musicDateChange(params);

				if(result) {
					System.out.println("수정이 완료됐습니다.");
					return;
				} else {
					System.out.println("수정에 실패했습니다.");
					return;
				}
			} else {
				System.out.println("다시 입력해주세요");
				continue;
			}
		}
	}
	/**
	 * 음악관리에서 입력받은 음악코드의 앨범명을 수정하는 메서드
	 * @author 영춘
	 *
	 */
	private void musicAlbumChange(String music_cd) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 앨범명을 입력하세요");
		String music_album = sc.next();

		Map<String, String> params = new HashMap<String, String>();
		params.put("music_cd", music_cd);
		params.put("music_album", music_album);

		boolean result = service.musicAlbumChange(params);

		if(result) {
			System.out.println("수정이 완료됐습니다.");
			return;
		} else {
			System.out.println("수정에 실패했습니다.");
			return;
		}
	}
	/**
	 * 음악관리에서 입력받은 음악코드의 작곡가를 수정하는 메서드
	 * @author 영춘
	 * 
	 */
	private void musicCpsChange(String music_cd) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 작곡가를 입력하세요");
		String music_cps = sc.next();

		Map<String, String> params = new HashMap<String, String>();
		params.put("music_cd", music_cd);
		params.put("music_cps", music_cps);

		boolean result = service.musicCpsChange(params);

		if(result) {
			System.out.println("수정이 완료됐습니다.");
			return;
		} else {
			System.out.println("수정에 실패했습니다.");
			return;
		}
	}

	/**
	 * 음악관리에서 입력받은 음악코드의 작사가를 수정하는 메서드
	 * @author 영춘
	 * @return
	 * 
	 */
	private void musicLyrChange(String music_cd) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 작사가를 입력하세요");
		String music_lyr = sc.next();

		Map<String, String> params = new HashMap<String, String>();
		params.put("music_cd", music_cd);
		params.put("music_lyr", music_lyr);

		boolean result = service.musicLyrChange(params);

		if(result) {
			System.out.println("수정이 완료됐습니다.");
			return;
		} else {
			System.out.println("수정에 실패했습니다.");
			return;
		}
	}
	/**
	 * 음악관리에서 입력받은 음악코드의 장르코드를 수정하는 메서드
	 * @author 영춘
	 * @return
	 * 
	 */
	private void grcdChange(String music_cd) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 장르코드를 입력하세요");
		while(true){
			String gr_cd = sc.next();
			boolean result = service.grcdCheck(gr_cd);

			if(result){
				Map<String, String> params = new HashMap<String, String>();
				params.put("music_cd", music_cd);
				params.put("gr_cd", gr_cd);

				boolean result1 = service.grcdChange(params);

				if(result1) {
					System.out.println("수정이 완료됐습니다.");
					return;
				} else {
					System.out.println("수정에 실패했습니다.");
					return;
				}
			} else {
				System.out.println("다시 입력해주세요");
				continue;
			}
		}
	}
	/**
	 * 음악삭제시 삭제할 음악코드를 입력받는 메서드
	 * @author 영춘
	 */
	private void musicDelete() {
		while(true){
			System.out.println("────────음악리스트──────────");
			List<MusicVO> musicAList = service.getMusicList();

			for (int i = 0; i < musicAList.size(); i++) {
				System.out.println(i+1+". "+musicAList.get(i).getMusic_cd()+"\t"+musicAList.get(i).getMusic_name()+"\t"+musicAList.get(i).getMusic_artist()+"\t"+musicAList.get(i).getMusic_album());
			}
			System.out.println("─────────────────────────");
			System.out.println("삭제할 음악의 음악코드를 입력해주세요");
			System.out.println("이전 메뉴로 돌아가려면 1번을 입력해주세요");

			Scanner sc = new Scanner(System.in);

			String music_cd = sc.next();
			if(music_cd.equals("1")){
				return;
			}
			boolean result = service.mlcdCheck(music_cd); // 음악코드 유효성검사

			if(result){
				adminMusicDelete(music_cd);

			} else {
				System.out.println("다시 입력해주세요");

			}
		}
	}
	/**
	 * 입력받은 음악코드에 맞는 음악을 삭제하는 메서드
	 * @author 영춘
	 * @param music_cd
	 */
	private void adminMusicDelete(String music_cd) {

		System.out.println("음악을 삭제하시겠습니까?");
		System.out.println("1. 삭제");
		System.out.println("2. 취소");

		Scanner sc = new Scanner(System.in);
		int select = 0;
		try {
			select = sc.nextInt();
		} catch (Exception e) {
			System.out.println("다시 입력해주세요.");

		}

		switch(select) {
		case 1:
			boolean result = service.deleteMusic(music_cd);
			if(result){
				System.out.println("삭제를 완료했습니다.");
				break;
			} else {
				System.out.println("삭제에 실패했습니다.");
				break;
			}
		case 2:
			return;
		}
	}

	/**
	 * 장르관리를 위한 메서드
	 * @author 영춘
	 */
	private void genreManage() {
		while(true){
			System.out.println("───────장르관리─────────────");
			System.out.println("   1. 장르등록");
			System.out.println("   2. 장르수정");
			System.out.println("   3. 장르삭제");
			System.out.println("   4. 뒤로가기");
			System.out.println("─────────────────────────");
			System.out.print("번호를 입력해주세요>");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("번호를 입력해주세요.");
				continue;
			}

			switch(select){
			case 1:
				genreAdd();
				break;
			case 2:
				genreChange();
				break;
			case 3:
				genreDeleteCheck();
				break;
			case 4:
				return;
			default:
				numSelectErr();
				continue;
			}
		}
	}
	/**
	 * 장르관리에서 장르등록을 위한 메서드
	 * @author 영춘
	 */
	private void genreAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 장르명을 입력하세요");
		String genre_name = sc.next();

		boolean result = service.genreAdd(genre_name);
		//장르명을 입력받는다. 코드는 자동생성. db가서 추가 전에 중복체크 돌리기 
		if(result){
			System.out.println("장르가 등록되었습니다.");
			return;
		} else {
			System.out.println("장르 등록에 실패했습니다.");
			return;
		}
	}
	/**
	 * 장르관리에서 수정할 장르를 선택하는 메서드
	 * @author 영춘
	 */
	private void genreChange() {
		while(true){
			System.out.println("────────전체리스트──────────");
			List<GenreVO> gi = service.getGenreInfo();

			for (int i = 0; i < gi.size(); i++) {
				System.out.println(i+1+". "+gi.get(i).getGr_cd()+"\t"+gi.get(i).getGr_name());
			}

			System.out.println("─────────────────────────");
			System.out.println("수정할 장르코드를 입력해주세요");
			System.out.println("이전 메뉴로 돌아가려면 1번을 입력해주세요");

			Scanner sc = new Scanner(System.in);

			String gr_cd = sc.next();
			if(gr_cd.equals("1")){
				return;
			}
			boolean result = service.grcdCheck(gr_cd); // 장르코드 유효성검사

			if(result) {
				grNameChange(gr_cd);
			} else {
				System.out.println("다시 입력해주세요");
				continue;
			}
		}
	}
	/**
	 * 입력받은 코드에 해당하는 장르명을 수정하는 메서드
	 * @author 영춘
	 * @param music_cd
	 */

	private void grNameChange(String gr_cd) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 장르명을 입력하세요");
		String gr_name = sc.next();

		Map<String, String> params = new HashMap<String, String>();
		params.put("gr_cd", gr_cd);
		params.put("gr_name", gr_name);

		boolean result = service.grNameChange(params);

		if(result) {
			System.out.println("수정이 완료됐습니다.");
			return;
		} else {
			System.out.println("수정에 실패했습니다.");
			return;
		}
	}

	/**
	 * 장르관리에서 장르삭제를 위한 메서드
	 * @author 영춘
	 */
	private void genreDeleteCheck() {
		while(true){
			System.out.println("────────전체리스트──────────");
			List<GenreVO> gi = service.getGenreInfo();

			for (int i = 0; i < gi.size(); i++) {
				System.out.println(i+1+". "+gi.get(i).getGr_cd()+"\t"+gi.get(i).getGr_name());
			}

			System.out.println("─────────────────────────");
			System.out.println("삭제할 장르코드를 입력해주세요");
			System.out.println("이전 메뉴로 돌아가려면 1번을 입력해주세요");

			Scanner sc = new Scanner(System.in);

			String gr_cd = sc.next();
			if(gr_cd.equals("1")){
				return;
			}
			boolean result = service.grcdCheck(gr_cd); // 장르코드 유효성검사

			if(result) {
				genreDelete(gr_cd);
			} else {
				System.out.println("다시 입력해주세요");
				continue;
			}
		}

	}
	/**
	 * 해당 장르의 곡이 있는지 체크하고 장르를 삭제하는 메서드
	 * @param gr_cd
	 */
	private void genreDelete(String gr_cd) {
		boolean check = service.checkGenreMusic(gr_cd); // 삭제할 장르에 해당하는 곡이 있는지 체크
		boolean result = false;

		if(check){
			System.out.println("해당 장르의 곡이 존재하여 장르를 삭제할 수 없습니다.");
			System.out.println("해당곡의 장르를 변경한 후 삭제해 주세요.");
			return;
		} else {
			result = service.genreDelete(gr_cd);
		}

		if(result) {
			System.out.println("장르가 삭제되었습니다.");
			return;
		} else {
			System.out.println("삭제에 실패했습니다.");
			return;
		}
	}
	//관리자메뉴1 음악관리 끝------------------------------------------------------------------------------------
	/**
	 * 회원관리를 위한 메서드
	 * @author park seo kyoung
	 */
	private void memManage() {
		System.out.println("──────────────── 회원관리 ────────────────");
		System.out.println("관리할 회원의 이메일아이디를 입력해주세요\n");

		//전체 멤버리스트 중에 레벨이 1,2인 멤버 리스트 불러오기 탈퇴한 회원은 탈퇴 회원이라고 출력
		List<MemberVO> allMemberList = service.allMemberList();
		String mem_mail = null;

		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("이메일\t\t이름\t\t생년월일\t\t닉네임\t\t보유금액\t\t이용가능기간\t탈퇴유무");
		System.out.println("============================================================================================================");
		for(int i = 0; i < allMemberList.size(); i++){
			if(allMemberList.get(i).getMem_level() != 0){
				String status = null;
				if(allMemberList.get(i).getMem_level() == 2){
					status = "탈퇴회원";	
				}else{
					status = "";
				}
				System.out.println(allMemberList.get(i).getMem_mail()+"\t"+allMemberList.get(i).getMem_name()
						+"\t\t"+allMemberList.get(i).getMem_bir()+"\t"+allMemberList.get(i).getMem_nn()
						+"\t\t"+allMemberList.get(i).getMem_money()+"원\t\t"+allMemberList.get(i).getMem_period()
						+"까지\t\t"+status);

			}
		}

		Scanner sc = new Scanner(System.in);
		String input = null;
		boolean regEx = false;

		while(true){
			System.out.println("이전 메뉴로 돌아가려면 1번을 입력해주세요.");
			
			System.out.println("검색하실  이메일을 입력해주세요.");
			input = sc.next();
			if(input.equals("1")){
				return;
			}
			Pattern regex = Pattern.compile(RegEx.regMail);
			Matcher match = regex.matcher(input);
			regEx = match.matches();
			if(regEx == true){
				for(int i = 0; i < allMemberList.size(); i++){
					if(allMemberList.get(i).getMem_mail().equals(input)){
						if(allMemberList.get(i).getMem_level() == 2){
							System.out.println("이미 탈퇴한 회원 입니다.");
							continue;
						}
						mem_mail = input;
						adminMemManage(mem_mail);
					}
				}	
			}else{
				System.out.println("이메일 형식에 맞지 않습니다.");
			}
		}
	}

	/**
	 * 관리자 회원관리 메뉴 메서드
	 * @author park seo kyoung
	 * @param mem_mail
	 */
	private void adminMemManage(String mem_mail) {
		while(true){
			System.out.println("────────────── 회원정보관리 ──────────────");
			System.out.println("사용하실 번호를 입력해 주세요.");
			System.out.println("1. 회원정보수정");
			System.out.println("2. 회원탈퇴");
			System.out.println("3. 관리자메뉴로 돌아가기");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}

			switch(select){
			case 1:
				memChange(mem_mail);
				break;
			case 2:
				memDelete(mem_mail);
				break;
			case 3:
				managerMenu();
			default:
				numSelectErr();
				return;
			}
		}
	}

	/**
	 * 회원정보 수정을 위한 메서드
	 * @author park seo kyoung
	 * @param mem_mail
	 */
	private void memChange(String mem_mail) {
		while(true){
			System.out.println("────────────── 회원정보수정 ──────────────");
			System.out.println("수정하실 번호를 입력해 주세요.");
			System.out.println("1. 이름수정");
			System.out.println("2. 비밀번호수정");
			System.out.println("3. 생일수정");
			System.out.println("4. 닉네임수정");
			System.out.println("5. 회원정보관리로 돌아가기");	

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("번호를 입력해주세요.");
			}

			switch(select){
			case 1:
				memnameChange(mem_mail);
				break;
			case 2:
				mempwChange(mem_mail);
				break;
			case 3:
				membirChange(mem_mail);
				break;
			case 4:
				memnnChange(mem_mail);
				break;
			case 5:
				return;
			default:
				numSelectErr();
				return;
			}
		}
	}

	/**
	 * 관리자 - 회원 이름 수정 메서드
	 * @author park seo kyoung 
	 * @param mem_mail
	 * @return
	 */
	private void memnameChange(String mem_mail) {
		System.out.println("────────────── 이름수정 ──────────────");
		System.out.println("수정할 이름을 입력해 주세요. 한글 2~4자");

		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			str = sc.next();
			regEx = Pattern.matches(RegEx.regName, str);
			if(regEx == true){
				break;
			}else{
				System.out.println("이름 형식에 맞지 않습니다.");
			}
		}

		if(service.modifyName(str, mem_mail)){
			System.out.println("이름을 수정하였습니다.");
		}else{
			System.out.println("이름을 수정하지 못했습니다.");
		}
	}

	/**
	 * 관리자 - 회원 비밀번호 수정 메서드
	 * @author park seo kyoung 
	 * @param mem_mail
	 * @return
	 */
	private void mempwChange(String mem_mail) {
		System.out.println("────────────── 비밀번호재설정 ──────────────");
		System.out.println("비밀번호를 입력해 주세요.");
		System.out.println("영문(대문자 포함), 숫자, 특수문자 조합, 9~12자리");

		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			System.out.println("사용하실 비밀번호를 입력해주세요.");
			str = sc.next();
			regEx = Pattern.matches(RegEx.regPW, str);
			if(regEx == true){
				break;
			}else{
				System.out.println("비밀번호 형식에 맞지 않습니다.");
			}
		}

		if(service.modifyPw(str, mem_mail)){
			System.out.println("비밀번호 재설정을 완료하였습니다.");
		}else{
			System.out.println("비밀번호를 재설정하지 못했습니다.");
		}
	}

	/**
	 * 관리자 - 회원 생년월일 수정 메서드
	 * @author park seo kyoung 
	 * @param mem_mail
	 * @return
	 */
	private void membirChange(String mem_mail) {
		System.out.println("────────────── 생년월일수정 ──────────────");
		System.out.println("수정할 생년월일을 입력해 주세요. ex)950520");

		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			str = sc.next();
			regEx = Pattern.matches(RegEx.regBir, str);
			if(regEx == true){
				break;
			}else{
				System.out.println("생년월일 형식에 맞지 않습니다.");
			}
		}

		if(service.modifyBir(str, mem_mail)){
			System.out.println("생년월일을 수정하였습니다.");
		}else{
			System.out.println("생년월일을 수정하지 못했습니다.");
		}
	}

	/**
	 * 관리자 - 회원 닉네임 수정 메서드
	 * @author park seo kyoung 
	 * @param mem_mail
	 * @return
	 */
	private void memnnChange(String mem_mail) {
		System.out.println("────────────── 닉네임수정 ──────────────");
		System.out.println("수정할 닉네임을 입력해 주세요. 한글 2~20자");

		Scanner sc = new Scanner(System.in);
		String str;
		boolean regEx = false;

		while(true){
			str = sc.next();
			regEx = Pattern.matches(RegEx.regNn, str);
			if(regEx == true){
				break;
			}else{
				System.out.println("닉네임 형식에 맞지 않습니다.");
			}
		}

		if(service.modifyNn(str, mem_mail)){
			System.out.println("닉네임을 수정하였습니다.");
		}else{
			System.out.println("닉네임을 수정하지 못했습니다.");
		}
	}

	/**
	 * 회원탈퇴를 위한 메서드
	 * @author park seo kyoung
	 * @param mem_mail
	 */
	private void memDelete(String mem_mail) {
		while(true){
			System.out.println("────────────── 회원탈퇴 ──────────────");
			System.out.println("회원정보를 삭제하시겠습니까? ");
			System.out.println("1. 회원정보를 삭제합니다.");
			System.out.println("2. 관리자메뉴로 돌아가기");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}


			switch (select) {
			case 1:
				service.memberLeave(mem_mail);
				System.out.println("해당 회원의 정보가 삭제되었습니다.");
				memManage();
				break;
			case 2:
				return;
			default:
				numSelectErr();
				return;
			}
		}
	}

	//관리자메뉴2 회원관리 끝------------------------------------------------------------------------------------
	/**
	 * 캐시충전조회를 위한 메서드
	 * @author park seo kyoung
	 */
	private void cashCargeList() {
		while(true){
			System.out.println("────────────── 캐시충전조회 ──────────────");
			System.out.println("조회하실 회원의 이메일아이디를 입력해주세요");
			System.out.println("관리자 메뉴로 돌아가려면 'b'를 입력해주세요");

			List<MemberVO> allMemberList = service.allMemberList();

			String mem_mail = null;

			System.out.println("----------------------------------------------------------------");
			System.out.println("이메일\t\t\t회원이름\t\t보유캐시\t\t탈퇴회원");
			System.out.println("================================================================");

			for(int i = 0; i < allMemberList.size(); i++){
				if(allMemberList.get(i).getMem_level() != 0){
					String status = null;
					if(allMemberList.get(i).getMem_level() == 2){
						status = "탈퇴회원";	
					}else{
						status = "";
					}
					System.out.println(allMemberList.get(i).getMem_mail()+"\t\t"
							+allMemberList.get(i).getMem_name()+"\t\t"+allMemberList.get(i).getMem_money()
							+"\t\t"+status);
				}
			}

			Scanner sc = new Scanner(System.in);
			String input = sc.next();

			if(input.equals("b")){
				return;
			}

			for(int i = 0; i < allMemberList.size(); i++){
				if(allMemberList.get(i).getMem_mail().equals(input)){
					mem_mail = input;
					adminChargeList(mem_mail);
				}
			}
		}
	}

	/**
	 * 관리자 - 회원별 캐시 충전 리스트
	 * @param mem_mail
	 */
	private void adminChargeList(String mem_mail) {
		while(true){
			System.out.println("────────────── 캐시충전내역 ──────────────");
			System.out.println("1. 캐시충전조회로 돌아가기\n");

			List<MemberVO> allMemberList = service.allMemberList();
			List<ChargeVO> allChargeList = service.allChargeList();
			List<ChargePriceVO> allChargePrice = service.allChargePrice();

			String mem_name = null;
			String char_price_cd = null;
			int money = 0;
			int listMoney = 0;
			int newMoney = 0;
			int price = 0;

			// 회원정보 리스트에서 매개변수의 메일과 회원리스트의 메일이 같으면 이름과 보유캐시를 변수에 저장  
			for(int i = 0; i < allMemberList.size(); i++){
				if(mem_mail.equals(allMemberList.get(i).getMem_mail())){
					mem_name = allMemberList.get(i).getMem_name();
					money = allMemberList.get(i).getMem_money();
				}
			}


			System.out.println(mem_name+"님의 캐시충전내역 입니다");
			System.out.println("-----------------------------------------");
			System.out.println("\t캐시충전금액\t캐시충전일");
			System.out.println("=========================================");	

			//다시
			for(int i = 0; i < allChargeList.size(); i++){
				if(mem_mail.equals(allChargeList.get(i).getMem_mail())){
					for(int j = 0; j < allChargePrice.size(); j++){
						if(allChargeList.get(i).getChar_price_cd().equals(allChargePrice.get(j).getChar_price_cd())){
							listMoney = allChargePrice.get(j).getChar_price();
							newMoney += listMoney;
							System.out.println("\t"+listMoney+"원"+"\t\t"+allChargeList.get(i).getCha_date());
						}
					}
				}	
				//System.out.println("캐시 충전 내역이 없습니다.");
			}
			System.out.println("-----------------------------------------");
			System.out.println(mem_name+"님의 총 충전 캐시는["+(newMoney)+"]입니다.");


			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}

			switch(select){
			case 1:
				return;
			default:
				numSelectErr();
			}
		}
	}
	//관리자메뉴3 회원충전내역 끝------------------------------------------------------------------------------------
	/**
	 * 캐시 충전시 충전할 금액 관리
	 */
	private void cashChargePrice() {
		while(true){
			System.out.println("────────────── 충전금액관리 ──────────────");
			System.out.println("사용하실 번호를 입력해 주세요.");
			System.out.println("1. 등록된 금액 확인");
			System.out.println("2. 캐시충전 금액등록");
			System.out.println("3. 캐시충전 금액수정");
			System.out.println("4. 캐시충전 금액삭제");
			System.out.println("5. 관리자메뉴로 돌아가기");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}

			switch (select) {
			case 1:
				CashPriceList();
				break;
			case 2:
				addCashPrice();
				break;
			case 3:
				modifyCashPrice();
				break;
			case 4:
				delCashPrice();
				break;
			case 5:
				return;
			default:
				numSelectErr();
			}
		}
	}

	private void CashPriceList() {
		System.out.println("────────────── 등록된 금액 리스트 ──────────────");
		List<ChargePriceVO> allChargePrice = service.allChargePrice();
		System.out.println("\t금액코드\t\t금액");
		System.out.println("─────────────────────────────────────────");
		for(int i = 0; i < allChargePrice.size(); i++){		
			System.out.println("\t"+allChargePrice.get(i).getChar_price_cd()+"\t\t금액 : "+allChargePrice.get(i).getChar_price());
		}
	}

	/**
	 * 캐시충전 금액을 등록하기 위한 메서드
	 * @author park seo kyoung
	 */
	private void addCashPrice() {
		System.out.println("────────────── 캐시충전 금액등록 ──────────────");

		String char_price_cd = newPriceCode();	//충전금액코드(PK)
		Integer char_price = inputPrice();		//충전금액

		Map<String, Object> addCashPrice = new HashMap<String, Object>();

		addCashPrice.put("char_price_cd", char_price_cd);
		addCashPrice.put("char_price", char_price);

		if(service.addCashPrice(addCashPrice)){
			System.out.println("금액 등록에 성공하였습니다.");
		}else{
			System.out.println("금액 등록에 실패하였습니다.");
		}

	}

	/**
	 * 캐시충전 금액등록 - 코드 생성
	 * @return
	 */
	private String newPriceCode() {
		List<ChargePriceVO> allChargePrice = service.allChargePrice();
		String cp_cd = null;
		StringBuffer a = new StringBuffer("ch");

		for(int i = 0; i < allChargePrice.size(); i++){
			cp_cd = a.append(allChargePrice.size()+1).toString();
		}

		return cp_cd;
	}

	/**
	 * 캐시충전 금액등록 - 금액 입력
	 * @return
	 */
	private int inputPrice() {
		while(true){
			System.out.println("등록할 금액을 입력해주세요.");
			Scanner sc = new Scanner(System.in);
			int newPrice = 0;

			try {
				newPrice = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}	
			return newPrice;
		}
	}

	/**
	 * 수정할 금액 선택
	 * @author park seo kyoung
	 */
	private void modifyCashPrice() {
		while(true){
			System.out.println("────────────── 수정할 금액 선택 ──────────────");
			System.out.println("수정할 금액을 선택해 주세요.\n");

			List<ChargePriceVO> allChargePrice = service.allChargePrice();
			System.out.println("번호\t금액코드\t\t금액");
			System.out.println("─────────────────────────────────────────");
			for(int i = 0; i < allChargePrice.size(); i++){		
				System.out.println(""+(i+1)+"\t"+allChargePrice.get(i).getChar_price_cd()+"\t\t금액 : "+allChargePrice.get(i).getChar_price());
			}

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}	

			switch (input) {
			default:
				if(input > allChargePrice.size()){
					if(allChargePrice.size()+1 == input){
						numSelectErr();
						return;
					}
					numSelectErr();
				}else{
					ChargePriceVO charPrice = service.allChargePrice().get(input-1);
					System.out.println(input+"번 금액을 수정하시겠습니까?");
					modifyPrice(charPrice.getChar_price_cd());	
				}	
				break;
			}
		}
	}

	/**
	 * 캐시충전 금액수정 - 금액 수정
	 * @return
	 */
	private void modifyPrice(String chPrice) {
		System.out.println("1. 수정할래요!");
		System.out.println("2. 아니요. 돌아갈래요!");

		Scanner sc = new Scanner(System.in);
		int select = 0;

		try {
			select = sc.nextInt();
		} catch (Exception e) {
			System.out.println("숫자만 입력해 주세요.");
		}

		switch (select) {
		case 1:
			System.out.println("금액을 수정해주세요. 현재 금액 코드 : "+chPrice);			

			int input = 0;

			while(true){
				try {
					input = sc.nextInt();
				} catch (Exception e) {
					System.out.println("숫자만 입력해 주세요.");
					continue;
				}

				if(service.modifyCashPrice(input, chPrice)){
					System.out.println("금액 수정에 성공하였습니다.");
					cashChargePrice();
					break;
				}else{
					System.out.println("금액 수정에 실패하였습니다.");
				}		
			}
			break;
		case 2:
			return;
		default:
			numSelectErr();
		}
	}

	/**
	 * 캐시충전 금액삭제 - 금액 삭제
	 * @return
	 */
	private void delCashPrice() {		
		while(true){
			System.out.println("────────────── 캐시충전 금액삭제 ──────────────");
			System.out.println("삭제할 금액을 선택해 주세요.\n");

			List<ChargePriceVO> allChargePrice = service.allChargePrice();
			System.out.println("번호\t금액코드\t\t금액");
			System.out.println("─────────────────────────────────────────");
			for(int i = 0; i < allChargePrice.size(); i++){		
				System.out.println(""+(i+1)+"\t"+allChargePrice.get(i).getChar_price_cd()+"\t\t금액 : "+allChargePrice.get(i).getChar_price());
			}

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}	

			switch (input) {
			default:
				if(input > allChargePrice.size()){
					if(allChargePrice.size()+1 == input){
						numSelectErr();
						return;
					}
					numSelectErr();
				}else{
					ChargePriceVO charPrice = service.allChargePrice().get(input-1);
					System.out.println(input+"번 금액을 삭제하시겠습니까?");
					delPrice(charPrice);	
				}	
				break;
			}
		}

	}

	/**
	 * 캐시충전 금액삭제 - 금액 삭제
	 * @return
	 */
	private void delPrice(ChargePriceVO charPrice) {
		while(true){
			System.out.println("1. 삭제할래요!");
			System.out.println("2. 아니요. 돌아갈래요!");

			Scanner sc = new Scanner(System.in);

			int input = 0;
			try {
				input = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}

			switch (input) {
			case 1:
				if(service.delCashPrice(charPrice)){
					System.out.println("삭제를 완료하였습니다.");
					cashChargePrice();
					break;
				}else{
					System.out.println("삭제 실패하였습니다.");
				}
				break;
			case 2:
				return;
			default:
				numSelectErr();
			}
		}
	}
	//관리자메뉴4 캐시충전금액관리 끝------------------------------------------------------------------------------------
	/**
	 * 이용권구매내역을 위한 메서드
	 * @author 유민하
	 */
	private void ticketBuyList() {
		System.out.println("────────이용권구매내역────────");
		List<MemberVO> memberPrint = service.memberPrint();
		//		List<TicketMVO> ticketmPrint = service.getTicketInfo(memberPrint.get(i).getMem_mail());
		for (int i = 0; i < memberPrint.size(); i++) {
			System.out.println(memberPrint.get(i).getMem_name()+"\t"+memberPrint.get(i).getMem_mail()+"\t");
		}
		System.out.println("─────────────────────────");
		System.out.print("확인할 회원의 이메일아이디를 입력해주세요");

		Scanner sc = new Scanner(System.in);
		String mem_mail = sc.next();

		adminticketBuyList(mem_mail);

	}
	/**
	 * 관리자 이용권구매내역 메서드
	 * @author 유민하
	 * @param mem_mail
	 */
	private void adminticketBuyList(String mem_mail) {
		while(true){		
			System.out.println("────────이용권정보출력────────");
			List<TicketMVO> admintk = null;
			List<TicketVO> admintk2 = null;

			admintk = service.admintk(mem_mail);
			admintk2 = service.admintk2(mem_mail);

			for (int i = 0; i < admintk.size(); i++) {
				System.out.println(admintk.get(i).getTcm_name()+"\t"+admintk2.get(i).getTc_date()+"\t"+admintk.get(i).getTcm_price());
			}
			System.out.println("1. 뒤로가기");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("번호를 입력해주세요.");
				continue;
			}

			switch (select) {
			case 1:
				return;

			default:
				System.out.println("나가시려면 1번을 눌러주세요");
				break;
			}
		}
	}
	//관리자메뉴5 이용권구매내역 끝------------------------------------------------------------------------------------
	/**
	 * 이용권 관리를 위한 메서드
	 * @author 유민하
	 */
	private void ticketMange() {
		while(true){
			System.out.println("─────────이용권관리─────────");
			System.out.println("   1. 이용권추가");
			System.out.println("   2. 이용권수정");
			System.out.println("   3. 이용권삭제");
			System.out.println("   4. 뒤로가기");
			System.out.println("─────────────────────────");
			System.out.print("번호를 입력해주세요>");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("번호를 입력해주세요.");
				continue;
			}

			switch(select){
			case 1:
				ticketAdd();
				break;
			case 2:
				ticketChange();
				break;
			case 3:
				ticketDelete();
				break;
			case 4:
				return;
			default:
				numSelectErr();
				break;
			}
		}
	}

	/**
	 * 이용권삭제를 위한 메서드
	 * @author 유민하
	 */
	private void ticketDelete() {
		while(true){
			System.out.println("────────전체리스트──────────");
			//이용권 정보를 보여주는 곳
			List<TicketMVO> ticketPrint = service.getTicketInfo();
			int cnt = 0;
			for (int i = 0; i < ticketPrint.size(); i++) {
				if (ticketPrint.get(i).getTcm_use()==1) {
					System.out.println((cnt+1)+"."+ticketPrint.get(i).getTcm_cd()+ticketPrint.get(i).getTcm_name()+ticketPrint.get(i).getTcm_period()+ticketPrint.get(i).getTcm_price()+"원");
					cnt++;
				}
			}
			System.out.println("─────────────────────────");
			System.out.print("삭제할 이용권정보의 이용권코드를 입력해주세요");

			String tcm_cd = null;
			Scanner sc = new Scanner(System.in);
			boolean regEx = false;
			tcm_cd = sc.next();
			regEx = Pattern.matches(RegEx.regticketCode, tcm_cd);
			if (regEx == true) {
			} else {
				System.out.println("코드 형식에 맞지 않습니다. 다시 입력해주세요.");
				continue;
			}
			for (int i = 0; i < ticketPrint.size(); i++) {
				if (tcm_cd.equals(ticketPrint.get(i).getTcm_cd())) {
					adminticketmDelete(tcm_cd);
					return;
				}
			}
		}
	}

	/**
	 * 이용권관리에서 입력받은 이용권코드의 정보를 삭제하는 메서드
	 * @author 유민하
	 * @param tcm_cd
	 */
	private void adminticketmDelete(String tcm_cd) {
		service.tkDelete(tcm_cd);
		System.out.println("삭제를 완료했습니다.");
	}

	/**
	 * 이용권수정을 위한 메서드
	 * @author 유민하
	 */
	private void ticketChange() {

		while(true){
			System.out.println("────────전체리스트──────────");
			//이용권 정보를 보여주는 곳
			List<TicketMVO> ticketPrint = service.getTicketInfo();
			int cnt = 0;
			for (int i = 0; i < ticketPrint.size(); i++) {
				if (ticketPrint.get(i).getTcm_use()==1) {
					System.out.println((cnt+1)+"."+ticketPrint.get(i).getTcm_cd()+ticketPrint.get(i).getTcm_name()+ticketPrint.get(i).getTcm_period()+ticketPrint.get(i).getTcm_price()+"원");
					cnt++;
				}
			}
			System.out.println("─────────────────────────");
			System.out.print("수정할 이용권정보의 이용권코드를 입력해주세요");

			String tcm_cd = null;
			Scanner sc = new Scanner(System.in);
			boolean regEx = false;
			tcm_cd = sc.next();
			regEx = Pattern.matches(RegEx.regticketCode, tcm_cd);
			if (regEx == true) {
			} else {
				System.out.println("코드 형식에 맞지 않습니다. 다시 입력해주세요.");
				continue;
			}
			for (int i = 0; i < ticketPrint.size(); i++) {
				if (tcm_cd.equals(ticketPrint.get(i).getTcm_cd())) {
					adminticketChange(tcm_cd);
					return;
				}
			}
		}
	}

	/**
	 * 이용권관리에서 입력받은 이용권코드의 수정목록을 보는 메서드
	 * @param tcm_cd
	 */
	private void adminticketChange(String tcm_cd) {
		while(true){
			System.out.println("──────────이용권수정─────────");
			System.out.println("   1. 이용권코드수정");
			System.out.println("   2. 이용권명수정");
			System.out.println("   3. 이용권가격수정");
			System.out.println("   4. 이용권기간수정");
			System.out.println("   5. 사용유무수정");
			System.out.println("   6. 뒤로가기");
			System.out.println("─────────────────────────");
			System.out.print("번호를 입력해주세요>");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("번호를 입력해주세요.");
				continue;
			}

			switch(select){
			case 1:
				tcmcdChange(tcm_cd);
				break;
			case 2:
				tcmnameChange(tcm_cd);
				break;
			case 3:
				tcmpriceChange(tcm_cd);
				break;
			case 4:
				tcmperiodChange(tcm_cd);
				break;
			case 5:
				tcmuseChange(tcm_cd);
				break;
			case 6:
				return;
			default:
				numSelectErr();
				break;
			}
		}
	}

	/**
	 * 이용권수정을 위한 이용권사용여부수정 메서드
	 * @author 유민하
	 * @param tcm_cd
	 */
	private void tcmuseChange(String tcm_cd) {
		System.out.println("수정할 이용권 사용유무를 입력하세요");
		System.out.println("0이면 사용안함, 1이면 사용");

		Scanner sc = new Scanner(System.in);
		int tcm_use = sc.nextInt();

		if (service.tkuseUpdate(tcm_cd, tcm_use)) {
			System.out.println("수정이 완료됐습니다.");
		} else {
			System.out.println("수정에 실패했습니다.");
		}

	}

	/**
	 * 이용권수정을 위한 이용권가격수정 메서드
	 * @author 유민하
	 * @param tcm_cd
	 */
	private void tcmpriceChange(String tcm_cd) {
		System.out.println("수정할 이용권가격을 입력하세요");
		Scanner sc = new Scanner(System.in);
		int tcm_price = sc.nextInt();

		if (service.tkpriceUpdate(tcm_cd, tcm_price)) {
			System.out.println("수정이 완료됐습니다.");
		} else {
			System.out.println("수정에 실패했습니다.");
		}

	}

	/**
	 * 이용권수정을 위한 이용권기간수정 메서드
	 * @author 유민하
	 * @param tcm_cd
	 */
	private void tcmperiodChange(String tcm_cd) {
		System.out.println("수정할 이용권기간을 입력하세요");
		Scanner sc = new Scanner(System.in);
		String tcm_period = sc.next();

		if (service.tkperiodUpdate(tcm_cd, tcm_period)) {
			System.out.println("수정이 완료됐습니다.");
		} else {
			System.out.println("수정에 실패했습니다.");
		}

	}

	/**
	 * 이용권수정을 위한 이용권명수정 메서드
	 * @author 유민하
	 * @param tcm_cd
	 */
	private void tcmnameChange(String tcm_cd) {
		System.out.println("수정할 이용권명을 입력하세요");
		Scanner sc = new Scanner(System.in);
		String tcm_name = sc.next();

		if (service.tknameUpdate(tcm_cd, tcm_name)) {
			System.out.println("수정이 완료됐습니다.");
		} else {
			System.out.println("수정에 실패했습니다.");
		}

	}

	/**
	 * 이용권수정을 위한 이용권코드수정 메서드
	 * @author 유민하
	 * @param tcm_cd
	 */
	private void tcmcdChange(String tcm_cd) {
		List<TicketMVO> TicketInfo = service.getTicketInfo();
		Scanner sc = new Scanner(System.in);

		while(true){
			System.out.println("수정할 이용권코드를 입력하세요");
			System.out.println("tk+숫자를 2자리까지 입력해주세요");

			String tcm_cd2 = sc.next();

			boolean regEx = false;
			regEx = Pattern.matches(RegEx.regticketCode, tcm_cd2);

			if(regEx == true){
				for (int i = 0; i < TicketInfo.size(); i++) {
					if (tcm_cd2.equals(TicketInfo.get(i).getTcm_cd())) {
						System.out.println("해당코드가 이미 존재합니다.");
						break;
					}else if(service.tkcdUpdate(tcm_cd, tcm_cd2)){
						System.out.println("수정이 완료됐습니다.");
						return;
					} else{
						System.out.println("수정에 실패했습니다.");
					}
				}
			}
		}
	}

	/**
	 * 이용권추가를 위한 메서드
	 * @author 유민하
	 */
	private void ticketAdd() {
		System.out.println("────────전체리스트──────────");
		//이용권 정보를 보여주는 곳
		List<TicketMVO> ticketPrint = service.getTicketInfo();
		int cnt = 0;
		for (int i = 0; i < ticketPrint.size(); i++) {
			if (ticketPrint.get(i).getTcm_use()==1) {
				System.out.println((cnt+1)+"."+ticketPrint.get(i).getTcm_cd()+ticketPrint.get(i).getTcm_name()+ticketPrint.get(i).getTcm_period()+ticketPrint.get(i).getTcm_price()+"원");
				cnt++;
			}
		}
		System.out.println("─────────────────────────");

		String tcm_cd = tcmcdAdd(); //이용권코드
		String tcm_name = tcmnameAdd(); // 이용권명
		int tcm_price = tcmpriceAdd(); // 이용권가격
		String tcm_period = tcmperiodAdd(); // 이용권기간
		int tcm_use = tcmuseAdd(); //이용권사용유무


		TicketMVO ticketM = new TicketMVO();
		ticketM.setTcm_cd(tcm_cd);
		ticketM.setTcm_name(tcm_name);
		ticketM.setTcm_price(tcm_price);
		ticketM.setTcm_period(tcm_period);
		ticketM.setTcm_use(tcm_use);

		service.addTicketM(ticketM);		
		System.out.println("이용권이 추가됐습니다.");
	}

	/**
	 * 이용권추가를 위한 이용권사용유무 입력 메서드
	 * @author 유민하
	 * @return
	 */
	private int tcmuseAdd() {
		while(true){
			System.out.println("추가할 이용권사용유무를 입력하세요");
			System.out.println("사용 : 1 / 미사용 : 0");
			Scanner sc = new Scanner(System.in);
			int tcm_use = 0;

			try {
				tcm_use = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (tcm_use) {
			case 1:
				return tcm_use;				
			case 2:
				return tcm_use;				
			default:
				System.out.println("0과 1중에 골라주세요");
				break;
			}
		}
	}

	/**
	 * 이용권추가를 위한 이용권가격 입력 메서드
	 * @author 유민하
	 * @return
	 */
	private int tcmpriceAdd() {
		while(true){
			Scanner sc = new Scanner(System.in);
			System.out.println("추가할 이용권가격을 입력하세요");
			int tcm_price = 0;

			try {
				tcm_price = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			return tcm_price;
		}
	}

	/**
	 * 이용권추가를 위한 이용권기간 입력 메서드
	 * @author 유민하
	 * @return
	 */
	private String tcmperiodAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 이용권기간을 입력하세요");
		String tcm_period = sc.next();

		return tcm_period;
	}

	/**
	 * 이용권추가를 위한 이용권명 입력 메서드
	 * @author 유민하
	 * @return
	 */
	private String tcmnameAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 이용권명을 입력하세요");
		String tcm_name = sc.next();

		return tcm_name;
	}

	/**
	 * 이용권추가를 위한 이용권코드 입력 메서드
	 * @author 유민하
	 * @return
	 */
	private String tcmcdAdd() {
		List<TicketMVO> TicketInfo = service.getTicketInfo();
		String tcm_cd = null;

		while(true){
			Scanner sc = new Scanner(System.in);
			System.out.println("추가할 이용권코드를 입력하세요");
			System.out.println("tk+숫자를 2자리까지 입력해주세요");

			tcm_cd = sc.next();

			boolean regEx = false;
			regEx = Pattern.matches(RegEx.regticketCode, tcm_cd);

			if(regEx == true){
				for (int i = 0; i < TicketInfo.size(); i++) {
					if (tcm_cd.equals(TicketInfo.get(i).getTcm_cd())) {
						System.out.println("해당코드가 이미 존재합니다.");
						break;
					} else {
						return tcm_cd;
					}
				}
			}else{
				System.out.println("코드 형식에 맞지 않습니다.");
			}
		}
	}
	//관리자메뉴6 이용권관리 끝------------------------------------------------------------------------------------
	/**
	 * 매출관리를 위한 메서드
	 * @author park seo kyoung
	 */
	private void saleManage(){
		while(true){
			System.out.println("────────────── 매출관리 ──────────────");
			System.out.println("1. 캐시충전 월별매출");
			System.out.println("2. 캐시충전 년도별 매출");
			System.out.println("3. 관리자메뉴로 돌아가기");

			Scanner sc = new Scanner(System.in);

			int input = 0;
			try {
				input = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}

			switch (input) {
			case 1:
				monthSales();
				break;
			case 2:
				yearsSales();
				break;
			case 3:
				return;
			default:
				numSelectErr();
			}
		}	
	}

	/**
	 * 관리자 매출관리 - 월별 
	 * @author park seo kyoung
	 */
	private void monthSales() {
		System.out.println("────────────── 캐시충전 년도별 매출 ──────────────");


		Scanner sc = new Scanner(System.in);	
		String salseY = null;
		String salseM = null;
		boolean regEx = false;

		System.out.println("출력하고 싶은 매출의 년도를 숫자로 입력하세요. ex) 2019");
		while(true){					
			salseY = sc.next();
			regEx = Pattern.matches(RegEx.regYear, salseY);
			if(regEx == true){
				break;
			}else{
				System.out.println("숫자로 네글자만 입력하세요.");
			}
		}

		System.out.println("출력하고 싶은 매출의 월을 숫자로 입력하세요. ex) 03");
		while(true){					
			salseM = sc.next();
			regEx = Pattern.matches(RegEx.regMonth, salseM);
			if(regEx == true){
				break;
			}else{
				System.out.println("숫자로 두글자만 입력하세요.");
			}
		}

		int monthSales = service.monthSales(salseY, salseM);
		List<ChargePriceVO> allChargePrice = service.allChargePrice();
		List<ChargeVO> allChargeList = service.allChargeList();

		System.out.println("──────────────"+ salseY +"년"+salseM+"월의 매출 ──────────────");
		System.out.println("\t충전일\t\t 충전금액");

		int total = 0;

		if(monthSales != 0){
			for(int i = 0; i < allChargeList.size(); i++){		
				String year = allChargeList.get(i).getCha_date();
				String strYear = year.substring(0, 4);
				String strMonth = year.substring(5, 7);

				if(salseY.equals(strYear) && salseM.equals(strMonth)){
					String chaCode = allChargeList.get(i).getChar_price_cd();
					for(int j = 0; j < allChargePrice.size(); j++){
						if(chaCode.equals(allChargePrice.get(j).getChar_price_cd())){
							int chaPrice = allChargePrice.get(j).getChar_price();
							System.out.println("\t"+year+"\t"+chaPrice);
							total += chaPrice; 
						}
					}
				}
			}
		}else{
			System.out.println("\t\t해당년도 매출 없음");
		}

		System.out.println("─────────────────────────────────────────────────");
		System.out.println("\t총계\t\t"+monthSales);
	}

	/**
	 * 관리자 매출관리 - 년도별 
	 * @author park seo kyoung
	 */
	private void yearsSales() {	
		System.out.println("────────────── 캐시충전 년도별 매출 ──────────────");
		System.out.println("출력하고 싶은 매출의 년도를 숫자로 입력하세요. ex) 2019");

		Scanner sc = new Scanner(System.in);	
		String salse = null;
		boolean regEx = false;

		while(true){					
			salse = sc.next();
			regEx = Pattern.matches(RegEx.regYear, salse);
			if(regEx == true){
				break;
			}else{
				System.out.println("숫자로 네글자만 입력하세요.");
			}
		}	

		int yearSales = service.yearSales(salse);
		List<ChargePriceVO> allChargePrice = service.allChargePrice();
		List<ChargeVO> allChargeList = service.allChargeList();

		System.out.println("──────────────"+ salse +"년의 매출 ──────────────");
		System.out.println("\t충전일\t\t 충전금액");

		int total = 0;

		if(yearSales != 0){
			for(int i = 0; i < allChargeList.size(); i++){		
				String year = allChargeList.get(i).getCha_date();
				String strYear = year.substring(0, 4);

				if(salse.equals(strYear)){
					String chaCode = allChargeList.get(i).getChar_price_cd();
					for(int j = 0; j < allChargePrice.size(); j++){
						if(chaCode.equals(allChargePrice.get(j).getChar_price_cd())){
							int chaPrice = allChargePrice.get(j).getChar_price();
							System.out.println("\t"+year+"\t"+chaPrice);
							total += chaPrice; 
						}
					}
				}
			}
		}else{
			System.out.println("\t\t해당년도 매출 없음");
		}

		System.out.println("──────────────────────────────────────────");
		System.out.println("\t총계\t\t"+yearSales);
	}
	//관리자메뉴7 매출관리 끝------------------------------------------------------------------------------------
	/**
	 * 관리자 로그아웃
	 * @author park seo kyoung
	 */
	private void admLogout() {
		while(true){
			System.out.println("────────────── 관리자 로그아웃 ──────────────");
			System.out.println("로그아웃 하시겠습니까?");
			System.out.println("1. 로그아웃 할래요!");
			System.out.println("2. 아니요 더 있고 싶어요!");

			Scanner sc = new Scanner(System.in);
			int select = 0;

			try {
				select = sc.nextInt();
			} catch (Exception e) {
				numSelectErr();
				continue;
			}

			switch (select) {
			case 1:
				System.out.println("로그아웃 되었습니다. 초기화면으로 이동합니다.");
				memberInfo = null;
				startMethod();
				break;
			case 2:
				return;
			default:
				numSelectErr();
			}
		}
	}
	//관리자메뉴8 로그아웃 끝------------------------------------------------------------------------------------
	/**
	 *	메뉴에 있지 않은 번호를 눌렀을 때 
	 * 	@author 	park seo kyoung
	 *  @since	2019.02.24
	 */
	private void numSelectErr() {
		System.out.println("☆★현재 있는 번호에서 선택하세요★☆");		
	}
} // end of class
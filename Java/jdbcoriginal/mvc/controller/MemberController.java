package mvc.controller;

import java.util.List;
import java.util.Scanner;

import mvc.service.IMemberService;
import mvc.service.MemberServiceImpl;
import mvc.vo.MemberVO;

public class MemberController {
	Scanner scan = new Scanner(System.in);
	private IMemberService service;

	public MemberController() {
		service = new MemberServiceImpl();
	}

	public static void main(String[] args) {
		new MemberController().startMember();
	}

	// 메뉴를 출력하는 메서드
	public int displayMenu(){
		System.out.println();
		System.out.println("=== 작업 선택 (MVC) ===");
		System.out.println("1. 회원정보 입력");
		System.out.println("2. 회원정보 삭제");
		System.out.println("3. 회원정보 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("==============");
		System.out.print("작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}

	// 작업을 시작하는 메서드
	public void startMember(){
		while(true){
			int choice = displayMenu();
			switch(choice){
			case 1: // 입력
				insertMember();
				break;
			case 2: // 삭제
				deleteMember();
				break;
			case 3: // 수정
				updateMember();
				break;
			case 4: // 검색
				displayAllMember();
				break;
			case 0: // 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 선택하세요");
			}
		}
	}

	// 회원 정보를 추가(입력)하는 메서드
	private void insertMember() {

		System.out.println("추가할 회원 정보를 입력하세요.");
		boolean chk = false;
		String memId;
		do {
			System.out.print("회원 ID >> ");
			memId = scan.next();
			chk = service.getMember(memId);

			if(chk==true){
				System.out.println("입력한 회원 ID" + memId + "은(는) 이미 등록된 ID입니다. 다시 입력하세요");
			}
		} while (chk==true);

		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();

		scan.nextLine(); // 버퍼 내용 비우기
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();

		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);

		int cnt = service.insertMember(memVo);

		if(cnt>0){
			System.out.println(memId + "회원의 정보를 추가했습니다");
		} 
	}

	// 전체 회원 정보를 출력하는 메서드
	private void displayAllMember() {
		System.out.println();
		System.out.println("==========================================");
		System.out.println("회원ID\t회원이름\t전화번호\t\t주소");
		System.out.println("==========================================");
		List<MemberVO> memList = service.getAllMember();
		for(MemberVO memVo : memList){
			String memId = memVo.getMem_id();
			String memName = memVo.getMem_name();
			String memTel = memVo.getMem_tel();
			String memAddr = memVo.getMem_addr();
			System.out.println( memId + "    " + memName + "     " + memTel + "     " + memAddr);
		}
		System.out.println("==========================================");
		System.out.println("출력 작업 끝");
	}

	// 회원 정보를 삭제하는 메서드
	private void deleteMember() {

		System.out.print("삭제할 회원의 ID를 입력하세요");
		String memId = scan.next();

		int cnt = service.deleteMember(memId);

		if(cnt>0){
			System.out.println(memId + "를(을) 삭제했습니다.");
		} else {
			System.out.println(memId + " 회원이 없거나 삭제에 실패했습니다.");
		}
	}

	// 회원 정보를 수정하는 메서드
	private void updateMember() {

		System.out.print("수정할 회원 ID >> ");
		String memId = scan.next();
		boolean chk = service.getMember(memId);

		if(chk==false){ // 대상회원이 없을 때
			System.out.println("입력한 회원 ID" + memId + "은(는) 없는 회원입니다. 수정 작업 종료");
			return;
		}

		// 대상회원이 있을 때
		System.out.println("새로운 이름 >> ");
		String memName = scan.next();
		System.out.println("새로운 전화번호 >> ");
		String memTel = scan.next();
		scan.nextLine();
		System.out.println("새로운 주소 >> ");
		String memAddr = scan.nextLine();

		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);

		int cnt = service.updateMember(memVo);

		if(cnt>0){
			System.out.println(memId + " 회원의 회원정보를 수정했습니다.");
		} else {
			System.out.println("수정작업 실패");
		}
	}
}

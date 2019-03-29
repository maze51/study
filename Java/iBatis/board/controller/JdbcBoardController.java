package board.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import util.BuildedSqlMapClient;
import board.service.IJdbcBoardService;
import board.service.JdbcBoardServiceImpl;
import board.vo.JdbcBoardVO;

public class JdbcBoardController {
	private Scanner scan;
	private IJdbcBoardService service;
	
	// 생성자
	public JdbcBoardController() {
		// 서비스 가는 길을 뚫기 위해 이렇게 한다
		service = JdbcBoardServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
	}
	
	// 게시판 목록을 보여주고 메뉴를 나타내며 사용자가 입력한 번호를 반환하는 메서드
		public int displayMenu(String jBoardTitle){
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println(" No           제 목          작성자       조회수");
			System.out.println("-------------------------------------------------");
			
			if(jBoardTitle==null){
				jBoardTitle = "";
			}
			List<JdbcBoardVO> boardList = service.getSearchBoardList(jBoardTitle);
			
			if(boardList == null || boardList.size()==0){
				System.out.println("  출력할 게시물이 하나도 없습니다. ");
			}else{
				for(JdbcBoardVO jBoardVo : boardList){
					System.out.println( jBoardVo.getBoard_no() + "    " 
								+ jBoardVo.getBoard_title() + "    "
								+ jBoardVo.getBoard_writer() + "    "
								+ jBoardVo.getBoard_cnt());
				}
			}
			System.out.println("-------------------------------------------------");
			System.out.println("메뉴 : 1. 새글작성   2. 게시글보기   3. 검색   0. 작업끝.");
			System.out.print("작업 선택 >> ");
			int num = scan.nextInt();
			return num;
			
		}
		
	// 게시판을 시작하는 메서드
	public void boardStart(){
		String jBoardTitle = null;
		int choice = -1; // 관련없는 값으로 초기화해서 시작. 걸러낼 때 필요하다.
		
		while(true){
			if(choice!=3){
				jBoardTitle = null; // 3번이 아닐 때는 초기화해준다. 남아있는 기존 입력값 삭제용.
			}
			choice = displayMenu(jBoardTitle);
			switch(choice){
				case 1 :  // 새글 작성
					insertBoard();
					break;
				case 2 :  // 게시글 보기
					viewBoard();
					break;
				case 3 :  // 검색
					jBoardTitle = searchBoard(); // 검색한 제목을 지역변수로 옮긴다
					break;
				case 0 :  // 작업 끝.
					System.out.println("게시판 프로그램 종료...");
					return;
				default : 
					System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}
	
	// 게시글의 내용을 보여주는 메서드
	public void viewBoard(){
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();
		
		JdbcBoardVO jBoardVO = service.getBoard(boardNo); // JdbcBoardServiceImpl 참조
		
		if(jBoardVO==null){
			System.out.println(boardNo + "번의 게시글이 존재하지 않습니다.");
			return;
		}
		
		System.out.println();
		System.out.println(boardNo + "번글 내용");
		System.out.println("------------------------------------------");
		System.out.println(" - 제  목 : " + jBoardVO.getBoard_title());
		System.out.println(" - 작성자 : " + jBoardVO.getBoard_writer());
		System.out.println(" - 내  용 : " + jBoardVO.getBoard_content());
		System.out.println(" - 작성일 : " + jBoardVO.getBoard_date());
		System.out.println(" - 조회수 : " + jBoardVO.getBoard_cnt());
		System.out.println("------------------------------------------");
		System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print("작업선택 >> ");
		int choice = scan.nextInt();
		
		switch(choice){
			case 1 :   	// 수정
				updateBoard(boardNo);
				break;
			case 2 :	// 삭제
				deleteBoard(boardNo);
				break;
			case 3 :
				return;
		}
		
	}
	
	// 게시글을 수정하는 메서드
	public void updateBoard(int boardNo){
		System.out.println();
		scan.nextLine();  // 비우기
		System.out.println("수정 작업하기");
		System.out.println("-------------------------------------");
		System.out.print("- 제 목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 내 용 : ");
		String content = scan.nextLine();
		
		JdbcBoardVO jBoardVo = new JdbcBoardVO();
		jBoardVo.setBoard_no(boardNo);
		jBoardVo.setBoard_title(title);
		jBoardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(jBoardVo);
		
		if(cnt>0){
			System.out.println(boardNo + "번글이 수정되었습다.");
		}else{
			System.out.println(boardNo + "번글 수정 실패~~~");
		}
	}
	
	// 게시글을 삭제하는 메서드
	public void deleteBoard(int boardNo){
		int cnt = service.deleteBoard(boardNo);
		if(cnt>0){
			System.out.println(boardNo + "번글이 삭제되었습니다.");
		}else{
			System.out.println(boardNo + "번글 삭제 실패!!");
		}
	}
	
	// 새글을 작성하는 메서드
	public void insertBoard(){
		System.out.println();
		scan.nextLine();  // 버퍼 비우기
		System.out.println("  새글 작성하기");
		System.out.println("------------------------------------");
		System.out.print(" - 제  목 : ");
		String title = scan.nextLine();
		
		System.out.print(" - 작성자 : ");
		String writer = scan.nextLine();
		
		System.out.print(" - 내  용 : ");
		String content = scan.nextLine();
		
		// 입력받은 내용을 VO객체에 담는다.
		JdbcBoardVO jBoardVo = new JdbcBoardVO();
		jBoardVo.setBoard_title(title);
		jBoardVo.setBoard_writer(writer);
		jBoardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(jBoardVo);
		
		if(cnt>0){
			System.out.println("새글이 추가되었습니다.");
		}else{
			System.out.println("새글 추가 실패~~");
		}
	}
	
	// 게시글을 검색할 제목을 입력 받아 반환하는 메서드
	public String searchBoard(){
		System.out.println();
		scan.nextLine(); // 버퍼 비우기
		System.out.println("검색 작업");
		System.out.println("------------------------------------");
		System.out.print(" - 검색할 제목 입력 : ");
		String jBoardTitle = scan.nextLine();
		
		return jBoardTitle;
	}
	

}










package board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;

public class BoardController {
	private IBoardService service;
	Scanner scan = new Scanner(System.in);

	public BoardController() {
		service = new BoardServiceImpl();
	}

	public static void main(String[] args) {		// main
		new BoardController().startBoard();
	}

	// 작업을 시작하는 메서드
	private void startBoard() {
		while(true){
			int choice = displayMenu();
			switch(choice){
			case 1:
				insertPost();
				break;
			case 2:
				viewPost();
				break;
			case 3:
				searchPost();
				break;
			case 0:
				System.out.println("게시판 프로그램 종료");
				System.exit(0);
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}
		}
		/*
		String boardTitle = null;
		int choice = -1; // 왜 -1? 메뉴에 없는 값으로 초기화하기 위해
		while(true){
			if(choice!=3){
				boardTitle = null;
			}
			choice = displayMenu(boardTitle);
			switch(choice){
			case 1:
				insertPost();
				break;
			case 2:
				viewPost();
				break;
			case 3:
				boardTitle = searchPost();
				break;
			case 0:
				System.out.println("게시판 프로그램 종료");
				System.exit(0);
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}
		}
		 */
		
	}

	// 게시물과 메뉴를 출력하는 메서드
	public int displayMenu(){
		List<BoardVO> showList = new ArrayList<BoardVO>();
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		showList = service.getAllPost();
		if(showList.size()==0){
			System.out.println("게시글이 하나도 없습니다.");
		} else {
			System.out.println("No    제  목       작성자       조회수");
			System.out.println("-------------------------------------------------------------");
			for (int i = 0; i < showList.size(); i++) {
				System.out.print(showList.get(i).getBoardNo() + "     ");
				System.out.print(showList.get(i).getBoardTitle() + "     ");
				System.out.print(showList.get(i).getBoardWriter() + "     ");
				System.out.println(showList.get(i).getBoardCnt());
			}
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >> ");
		int select = scan.nextInt();

		return select;
		/*
		public int displayMenu(String boardTitle){
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println("No    제  목       작성자       조회수");
		System.out.println("-------------------------------------------------------------");
		if(boardTitle==null){
			boardTitle = "";
		}
		List<BoardVO> showList = service.searchPost(boardTitle);

		if(showList == null || showList.size() == 0){
			System.out.println("출력할 게시물이 없습니다.");
		} else {
			for(BoardVO boardVo : showList){
				System.out.println(boardVo.getBoard_no() + "    "
				+ boardVo.getBoard_title() + "    "
				+ boardVo.getBoard_writer() + "     "
				+ boardVo.getBoard_cnt());
			}
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >> ");
		int num = scan.nextInt();
		return num;
		 */
	} 

	// 게시글 제목을 검색하는 메서드
	private void searchPost() {
		List<BoardVO> searchList = new ArrayList<BoardVO>();
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		scan.nextLine();
		String keyword = scan.nextLine();

		if(keyword==null || keyword==""){
			searchList = service.getAllPost();
		} else {
			searchList = service.searchPost(keyword);
		}
		System.out.println("-------------------------------------------------------------");
		if(searchList.size()==0){
			System.out.println("게시글이 하나도 없습니다.");
		} else {
			System.out.println("No    제  목       작성자       조회수");
			System.out.println("-------------------------------------------------------------");
			for (int i = 0; i < searchList.size(); i++) {
				System.out.print(searchList.get(i).getBoardNo() + "     ");
				System.out.print(searchList.get(i).getBoardTitle() + "     ");
				System.out.print(searchList.get(i).getBoardWriter() + "     ");
				System.out.println(searchList.get(i).getBoardCnt());
			}
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >> ");
		int select = scan.nextInt();
		switch(select){
		case 1:
			insertPost();
			break;
		case 2:
			viewPost();
			break;
		case 3:
			searchPost();
			break;
		case 0:
			System.out.println("게시판 프로그램 종료");
			System.exit(0);
		default:
			System.out.println("잘못 입력했습니다. 다시 입력하세요.");
		}
		/*
		//게시글을 검색할 제목을 입력받아 반환하는 메서드
		public String searchPost(){
			System.out.println("검색 작업");
			System.out.println("--------------------------------------------");
			System.out.print("- 검색할 제목 입력 : ");
			scan.nextLine();
			String boardTitle = scan.nextLine();
			
			return boardTitle;
		}
		 */
	}

	// 새로운 게시글을 작성하는 메서드
	private void insertPost() {
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		scan.nextLine(); // 버퍼 비우기
		String title = scan.nextLine();

		System.out.print("- 작성자 : ");
		String writer = scan.nextLine();

		System.out.print("- 내  용 : ");
		String content = scan.nextLine();
		System.out.println();

		// 입력받은 내용을 VO객체에 담는다.
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(title);
		bv.setBoardWriter(writer);
		bv.setBoardContent(content);

		int cnt = service.insertPost(bv);
		if(cnt > 0){
			System.out.println("새글이 추가되었습니다.");
		} else {
			System.out.println("새글 추가 실패");
		}
	}

	// 게시글을 선택받아 내용을 확인하는 메서드
	private void viewPost() {
		List<BoardVO> rList = new ArrayList<BoardVO>();

		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int select = scan.nextInt();
		while(true){
			rList = service.getSelectedPost(select);

			System.out.println();
			System.out.println(" " + select + "번글 내용");
			System.out.println("------------------------------------------------------------");
			System.out.print("- 제  목 : ");
			System.out.println(rList.get(0).getBoardTitle());
			System.out.print("- 작성자 : ");
			System.out.println(rList.get(0).getBoardWriter());
			System.out.print("- 내  용 : ");
			System.out.println(rList.get(0).getBoardContent());
			System.out.print("- 작성일 : ");
			System.out.println(rList.get(0).getBoardDate());
			System.out.print("- 조회수 : ");
			System.out.println(rList.get(0).getBoardCnt());
			System.out.println("------------------------------------------------------------");
			System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
			System.out.print("작업선택 >> ");

			int select2 = scan.nextInt();
			switch(select2){
			case 1:
				updatePost(select);
				break;
			case 2:
				deletePost(select);
				break;
			case 3:
				return;
			default :
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}
		}
		/*
		BoardVO boardVo = service.getBoard(boardNo);
		
		if(boardVo==null){
			System.out.println(boardNo + "번의 게시물이 존재하지 않습니다.");
			return;
		}
		 */
	}

	// 게시글을 삭제하는 메서드
	private void deletePost(int select) {

		int cnt = service.deletePost(select);

		if(cnt > 0){
			System.out.println(select + "번글이 삭제되었습니다.");
		} else {
			System.out.println("게시글 삭제 실패");
		}
	}

	// 게시글의 제목과 내용을 수정하는 메서드
	private void updatePost(int select) {
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");

		System.out.print("- 제  목 : ");
		scan.nextLine();
		String title = scan.nextLine();

		System.out.print("- 내  용 : ");
		String content = scan.nextLine();
		System.out.println();
		
		
		BoardVO boardVo = new BoardVO();
		boardVo.setBoardNo(select);
		boardVo.setBoardTitle(title);
		boardVo.setBoardContent(content);
		
		int cnt = service.updatePost(boardVo);
		if(cnt > 0){
			System.out.println(select + "번글이 수정되었습니다.");
		} else {
			System.out.println(select + "번글이 수정 실패");
		}
		
		if(cnt > 0){
			System.out.println(select + "번글이 수정되었습니다.");
		}
	}

}

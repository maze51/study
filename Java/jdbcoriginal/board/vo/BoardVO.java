package board.vo;
/*
테이블 만든 정보가 저장된 테이블이 따로 있다(cols)

select 'private ' || decode(lower(data_type), 'number', 'int ', 'String ') ||
    lower(column_name) || ';'
from cols
where lower(table_name) = 'jdbc_board';

이렇게 sql명령어를 주면 아래와 같이 출력되고

private int board_no;
private String board_title;
private String board_writer;
private String board_date;
private int board_cnt;
private String board_content;

VO테이블을 한방에 만들 수 있다.
 */
public class BoardVO {
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardDate;
	private int boardCnt;
	private String boardContent;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
		
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public int getBoardCnt() {
		return boardCnt;
	}
	public void setBoardCnt(int boardCnt) {
		this.boardCnt = boardCnt;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
}

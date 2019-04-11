package board.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import board.service.IJdbcBoardService;
import board.service.JdbcBoardServiceImpl;
import board.vo.JdbcBoardVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AlertUtil;

public class ShowPostController {
	
	private IJdbcBoardService service = JdbcBoardServiceImpl.getInstance();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label writerLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TextField readTitle;

    @FXML
    private TextArea readContent;

    @FXML
    private Button readModifyB;

    @FXML
    private Button readDeleteB;

    @FXML
    private Button readListViewB;
    
    // 게시글 수정
    @FXML
    void readModify(ActionEvent event) {
    	readTitle.setEditable(true);
        readContent.setEditable(true);
        try {
			Parent modify = FXMLLoader.load(getClass().getResource("ModifyPost.fxml"));
			Scene scene = new Scene(modify);
			Stage modifyStage = (Stage) readListViewB.getScene().getWindow();
			modifyStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    // 게시글 삭제
    @FXML
    void readDelete(ActionEvent event) {
    	int cnt = service.deleteBoard(ListController.selectItem.getBoard_no());
    	if(cnt>0) {
    		AlertUtil.information("안내", "작업 완료", "게시글 삭제 완료");
    	} else {
    		AlertUtil.error("안내", "작업 실패", "게시글 삭제 실패");
    	}
    	try {
			Parent list = FXMLLoader.load(getClass().getResource("List.fxml"));
			Scene scene = new Scene(list);
			Stage listStage = (Stage) readListViewB.getScene().getWindow();
			listStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    // 리스트로 돌아가기
    @FXML
    void readListView(ActionEvent event) {
    	Parent list;
		try {
			list = FXMLLoader.load(getClass().getResource("List.fxml"));
			Scene scene = new Scene(list);
			Stage listStage = (Stage) readListViewB.getScene().getWindow();
			listStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert writerLabel != null : "fx:id=\"writerLabel\" was not injected: check your FXML file 'ShowPost.fxml'.";
        assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'ShowPost.fxml'.";
        assert readTitle != null : "fx:id=\"readTitle\" was not injected: check your FXML file 'ShowPost.fxml'.";
        assert readContent != null : "fx:id=\"readContent\" was not injected: check your FXML file 'ShowPost.fxml'.";
        assert readModifyB != null : "fx:id=\"readModifyB\" was not injected: check your FXML file 'ShowPost.fxml'.";
        assert readDeleteB != null : "fx:id=\"readDeleteB\" was not injected: check your FXML file 'ShowPost.fxml'.";
        assert readListViewB != null : "fx:id=\"readListViewB\" was not injected: check your FXML file 'ShowPost.fxml'.";
        
        JdbcBoardVO showItem = ListController.getSelectItem();
        showItem = service.getBoard(showItem.getBoard_no()); // 테이블에서 선택한 게시글 번호로 DB에서 게시글 정보를 가져온다
        
        writerLabel.setText(showItem.getBoard_writer());
        dateLabel.setText(showItem.getBoard_date());
        readTitle.setText(showItem.getBoard_title());
        readContent.setText(showItem.getBoard_content());
        
        readTitle.setEditable(false);
        readContent.setEditable(false);
    }
}

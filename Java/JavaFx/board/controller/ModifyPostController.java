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

public class ModifyPostController {
	
	private IJdbcBoardService service = JdbcBoardServiceImpl.getInstance();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label writerLabelM;

    @FXML
    private Label dateLabelM;

    @FXML
    private TextField modifyTitle;

    @FXML
    private TextArea modifyContent;

    @FXML
    private Button modifyModifyB;

    @FXML
    private Button modifyCancelB;
    
    // 수정 취소 버튼
    @FXML
    void modifyCancel(ActionEvent event) {
    	try {
    		Parent showPost = FXMLLoader.load(getClass().getResource("ShowPost.fxml"));
    		Scene scene = new Scene(showPost);
    		Stage showPostStage = (Stage) modifyCancelB.getScene().getWindow(); // 현재 윈도우 가져오기
    		showPostStage.setScene(scene);
    		
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    }
    
    // 수정 버튼
    @FXML
    void modifyModify(ActionEvent event) {
    	JdbcBoardVO modifyItem = new JdbcBoardVO();
    	modifyItem.setBoard_title(modifyTitle.getText());
    	modifyItem.setBoard_content(modifyContent.getText());
    	modifyItem.setBoard_no(ListController.selectItem.getBoard_no());
    	
    	int result = service.updateBoard(modifyItem);
    	
    	if(result>0) {
    		AlertUtil.information("안내", "작업 완료", "게시글 수정 완료");
    	} else {
    		AlertUtil.error("안내", "작업 실패", "게시글 수정 실패");
    	}
    	
    	try {
			Parent list = FXMLLoader.load(getClass().getResource("List.fxml"));
			Scene scene = new Scene(list);
			Stage listStage = (Stage) modifyModifyB.getScene().getWindow();
			listStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void initialize() {
        assert writerLabelM != null : "fx:id=\"writerLabelM\" was not injected: check your FXML file 'ModifyPost.fxml'.";
        assert dateLabelM != null : "fx:id=\"dateLabelM\" was not injected: check your FXML file 'ModifyPost.fxml'.";
        assert modifyTitle != null : "fx:id=\"modifyTitle\" was not injected: check your FXML file 'ModifyPost.fxml'.";
        assert modifyContent != null : "fx:id=\"modifyContent\" was not injected: check your FXML file 'ModifyPost.fxml'.";
        assert modifyModifyB != null : "fx:id=\"modifyModifyB\" was not injected: check your FXML file 'ModifyPost.fxml'.";
        assert modifyCancelB != null : "fx:id=\"modifyCancelB\" was not injected: check your FXML file 'ModifyPost.fxml'.";
        
        JdbcBoardVO showItem = ListController.getSelectItem();
        writerLabelM.setText(showItem.getBoard_writer());
        dateLabelM.setText(showItem.getBoard_date());
        modifyTitle.setText(showItem.getBoard_title());
        modifyContent.setText(showItem.getBoard_content());
    }
}

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AlertUtil;

public class NewPostController {
	
	private IJdbcBoardService service = JdbcBoardServiceImpl.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField newWriter;

    @FXML
    private TextField newTitle;

    @FXML
    private TextArea newContent;

    @FXML
    private Button newSaveB;

    @FXML
    private Button newCancelB;

    @FXML
    void newCancel(ActionEvent event) {
    	Parent list;
		try {
			list = FXMLLoader.load(getClass().getResource("List.fxml"));
			Scene scene = new Scene(list);
			Stage listStage = (Stage) newCancelB.getScene().getWindow();
			listStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void newSave(ActionEvent event) {
    	JdbcBoardVO jBoardVo = new JdbcBoardVO();
    	jBoardVo.setBoard_title(newTitle.getText());
    	jBoardVo.setBoard_writer(newWriter.getText());
    	jBoardVo.setBoard_content(newContent.getText());
    	
    	int cnt = service.insertBoard(jBoardVo);
    	if(cnt>0) {
    		AlertUtil.information("새글쓰기", "게시물 등록", "게시물 작성 완료");
    	}
    	
    	Parent list;
		try {
			list = FXMLLoader.load(getClass().getResource("List.fxml"));
			Scene scene = new Scene(list);
			Stage listStage = (Stage) newCancelB.getScene().getWindow();
			listStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void initialize() {
        assert newWriter != null : "fx:id=\"newWriter\" was not injected: check your FXML file 'NewPost.fxml'.";
        assert newTitle != null : "fx:id=\"newTitle\" was not injected: check your FXML file 'NewPost.fxml'.";
        assert newContent != null : "fx:id=\"newContent\" was not injected: check your FXML file 'NewPost.fxml'.";
        assert newSaveB != null : "fx:id=\"newSaveB\" was not injected: check your FXML file 'NewPost.fxml'.";
        assert newCancelB != null : "fx:id=\"newCancelB\" was not injected: check your FXML file 'NewPost.fxml'.";

    }
}

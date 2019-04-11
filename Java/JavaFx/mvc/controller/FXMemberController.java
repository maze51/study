package mvc.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mvc.service.IMemberService;
import mvc.service.MemberServiceImpl;
import mvc.vo.MemberVO;
import util.AlertUtil;

public class FXMemberController {
		boolean addAction = false;
		
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField textId;

	    @FXML
	    private TextField textName;

	    @FXML
	    private TextField textTel;

	    @FXML
	    private TextField textAddr;

	    @FXML
	    private Button addB;

	    @FXML
	    private Button modifyB;

	    @FXML
	    private Button deleteB;

	    @FXML
	    private Button confirmB;

	    @FXML
	    private Button cancelB;

	    @FXML
	    private TableView<MemberVO> table;

	    @FXML
	    private TableColumn<MemberVO, String> tableId;

	    @FXML
	    private TableColumn<MemberVO, String> tableName;

	    @FXML
	    private TableColumn<MemberVO, String> tableTel;

	    @FXML
	    private TableColumn<MemberVO, String> tableAddr;

	    @FXML
	    void add(ActionEvent event) { // 추가
	    	textId.setEditable(true); // 편집 가능 상태
	    	textName.setEditable(true);
	    	textTel.setEditable(true);
	    	textAddr.setEditable(true);
	    	
	    	confirmB.setDisable(false); // 확인, 취소버튼 활성화
	    	cancelB.setDisable(false);
	    	
	    	addB.setDisable(true); // 추가, 수정, 삭제버튼, 테이블뷰 비활성화
	    	modifyB.setDisable(true);
	    	deleteB.setDisable(true);
	    	table.setDisable(true);
	    	
	        textId.clear();	// TextField들의 내용 지우기
	        textName.clear();
	        textTel.clear();
	        textAddr.clear();
	        
	        textId.requestFocus(); // id입력할 곳에 포커스 주기
	        
	        addAction = true;
	    }

	    @FXML
	    void cancel(ActionEvent event) { // 취소
	    	textId.setEditable(false); // 편집 불가능 상태
	    	textName.setEditable(false);
	    	textTel.setEditable(false);
	    	textAddr.setEditable(false);
	    	
	    	confirmB.setDisable(true);
	        cancelB.setDisable(true);
	        
	        addB.setDisable(false);
	        modifyB.setDisable(false);
	        deleteB.setDisable(false);
	        table.setDisable(false);
	        
	        textId.clear();
	        textName.clear();
	        textTel.clear();
	        textAddr.clear();
	        
	        addAction = false;
	    }

	    @FXML
	    void confirm(ActionEvent event) { // 확인
	    	if(addAction) {
	    		boolean idCheck = service.getMember(textId.getText());
	    		if(idCheck) {
	    			AlertUtil.error("에러", "중복", "입력한 회원 id는 이미 존재합니다");
	    			return;
	    		}
	    		if(textId.getText().isEmpty() || textName.getText().isEmpty()
	    			|| textTel.getText().isEmpty() || textAddr.getText().isEmpty()) {
	    			AlertUtil.error("에러", "입력값 없음", "빈 항목이 있습니다");
	    			return;
	    		}
	    		
	    		MemberVO member = new MemberVO();
	    		member.setMem_id(textId.getText());
	    		member.setMem_name(textName.getText());
	    		member.setMem_tel(textTel.getText());
	    		member.setMem_addr(textAddr.getText());
	    		
	    		int result = service.insertMember(member);
	    		if(result>0) {
	    			AlertUtil.information("확인", "추가 작업 완료", "회원 추가가 완료되었습니다");
	    		}
	    		addAction = false;
	    	} else if(!addAction){
	    		MemberVO member = table.getSelectionModel().getSelectedItem();
	    		if(textName.getText().isEmpty() || textTel.getText().isEmpty()
	    			|| textAddr.getText().isEmpty()) {
	    			AlertUtil.error("에러", "입력값 없음", "빈 항목이 있습니다");
	    			return;
	    		}
	    		
	    		member.setMem_name(textName.getText());
	    		member.setMem_tel(textTel.getText());
	    		member.setMem_addr(textAddr.getText());
	    		int result = service.updateMember(member);
	    		if(result>0) {
	    			AlertUtil.information("확인", "수정 작업 완료", "회원정보 수정이 완료되었습니다");
	    		}
	    	}
	    	confirmB.setDisable(true);
	        cancelB.setDisable(true);
	        addB.setDisable(false);
	        modifyB.setDisable(false);
	        deleteB.setDisable(false);
	        table.setDisable(false);
	        textId.clear();
	        textName.clear();
	        textTel.clear();
	        textAddr.clear();
	        
	        // data.add(member); // db가지 않고 테이블에 신규 데이터 뿌려주기
	        ArrayList<MemberVO> memList = (ArrayList<MemberVO>) service.getAllMember();
	        data = FXCollections.observableArrayList(memList);
	        table.setItems(data);
	        addAction = false;
	    }

	    @FXML
	    void delete(ActionEvent event) { // 삭제
	    	if(table.getSelectionModel().getSelectedItem() == null) {
	    		AlertUtil.error("에러", "삭제할 회원 없음", "삭제할 회원을 선택해주세요");
	    		return;
	    	}
	    	
	    	if(!AlertUtil.confirmation("확인", "회원정보 삭제",  textName.getText()+ "회원을 삭제하시겠습니까?")) {
	    		return;
	    	}
	    	
	    	int result = service.deleteMember(textId.getText());
			if(result>0) { // DB 자료의 삭제 성공
    			AlertUtil.information("확인", "삭제 작업 완료", "회원 삭제가 완료되었습니다");
    		}
			
			confirmB.setDisable(true);
	        cancelB.setDisable(true);
	        addB.setDisable(false);
	        modifyB.setDisable(false);
	        deleteB.setDisable(false);
	        table.setDisable(false);
	        textId.clear();
	        textName.clear();
	        textTel.clear();
	        textAddr.clear();
	        ArrayList<MemberVO> memList = (ArrayList<MemberVO>) service.getAllMember();
	        data = FXCollections.observableArrayList(memList);
	        table.setItems(data);
	    }
	    
	    @FXML
	    void modify(ActionEvent event) { // 수정
	    	if(table.getSelectionModel().getSelectedIndex() == -1) {
	    		AlertUtil.error("에러", "수정할 회원 없음", "수정할 회원을 선택해주세요");
	    		return;
	    	}
	    	textName.setEditable(true);
	    	textTel.setEditable(true);
	    	textAddr.setEditable(true);
	    	
	    	confirmB.setDisable(false);
	        cancelB.setDisable(false);
	        
	        table.setDisable(true);
	        addB.setDisable(true);
	        modifyB.setDisable(true);
	        deleteB.setDisable(true);
	        
	        textName.requestFocus();
	    }

	    @FXML
	    void showInfo(MouseEvent event) { // 테이블 클릭
	    	MemberVO member = table.getSelectionModel().getSelectedItem();
	    	if(member != null) {
	    		textId.setText(member.getMem_id());
	    		textName.setText(member.getMem_name());
	    		textTel.setText(member.getMem_tel());
	    		textAddr.setText(member.getMem_addr());
	    	}
	    }

    
    private IMemberService service;
    private ObservableList<MemberVO> data;
    
    @FXML
    void initialize() {
    	
    	 // MemberService객체 생성
        service = MemberServiceImpl.getInstance();
        
        // 각 컬럼과 VO객체의 멤버변수 매칭
        tableId.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
        tableTel.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
        tableAddr.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
        
        // db자료 가져오기
        ArrayList<MemberVO> memList = (ArrayList<MemberVO>) service.getAllMember();
        
        // 가져온 자료를 ObservableList에 담는다.
        data = FXCollections.observableArrayList(memList);
        
        table.setItems(data);
        
        confirmB.setDisable(true);
        cancelB.setDisable(true);
        
        
        assert textId != null : "fx:id=\"textId\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert textName != null : "fx:id=\"textName\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert textTel != null : "fx:id=\"textTel\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert textAddr != null : "fx:id=\"textAddr\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert addB != null : "fx:id=\"addB\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert modifyB != null : "fx:id=\"modifyB\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert deleteB != null : "fx:id=\"deleteB\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert confirmB != null : "fx:id=\"confirmB\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert cancelB != null : "fx:id=\"cancelB\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert tableId != null : "fx:id=\"tableId\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert tableName != null : "fx:id=\"tableName\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert tableTel != null : "fx:id=\"tableTel\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        assert tableAddr != null : "fx:id=\"tableAddr\" was not injected: check your FXML file 'FXMemberMVC.fxml'.";
        
       
    }
}

package student.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import student.vo.StudentVO;

public class SudentPieChartFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;

    @FXML
    private PieChart stdPieChart;
    
    // 차트로 보여줄 데이터가 저장될 VO객체 변수
    private StudentVO stdVo;
    
    public StudentVO getStdVo() {
		return stdVo;
	}

	public void setStdVo(StudentVO stdVo) {
		this.stdVo = stdVo;
	}
	// StudentVO객체의 값을 이용하여 PieChart를 그려주는 메서드
	public void showChart() {
		stdPieChart.setTitle(stdVo.getStd_name() + "학생의 성적분포");
		stdPieChart.setData(FXCollections.observableArrayList(
			new PieChart.Data("국어", stdVo.getStd_kor()),
			new PieChart.Data("영어", stdVo.getStd_eng()),
			new PieChart.Data("수학", stdVo.getStd_mat())
		));
		
	}
	
	
	@FXML
    void btnCloseClick(ActionEvent event) {
		Stage thisForm = (Stage) btnClose.getScene().getWindow();
		thisForm.close();
    }

    @FXML
    void initialize() {
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'SudentPieChartForm.fxml'.";
        assert stdPieChart != null : "fx:id=\"stdPieChart\" was not injected: check your FXML file 'SudentPieChartForm.fxml'.";

    }
}

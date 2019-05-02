package student.client;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import student.vo.StudentVO;

public class BarChartForm extends Pane{
	
	// 생성자
	public BarChartForm(ObservableList<StudentVO> stdList) {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
		
		// 국어점수
		XYChart.Series<String, Number> serKor = new XYChart.Series<String, Number>();
		serKor.setName("국어");
		for (StudentVO stdVo : stdList) {
			serKor.getData().add(
					new Data<String, Number>(stdVo.getStd_name(), stdVo.getStd_kor())
			);
		}
		
		// 영어점수
		XYChart.Series<String, Number> serEng = new XYChart.Series<String, Number>();
		serEng.setName("영어");
		for (StudentVO stdVo : stdList) {
			serEng.getData().add(
					new Data<String, Number>(stdVo.getStd_name(), stdVo.getStd_eng())
			);
		}
		
		// 수학점수
		XYChart.Series<String, Number> serMat = new XYChart.Series<String, Number>();
		serMat.setName("수학");
		for (StudentVO stdVo : stdList) {
			serMat.getData().add(
					new Data<String, Number>(stdVo.getStd_name(), stdVo.getStd_mat())
			);
		}
		
		// 생성한 Series들을 BarChart에 추가
		barChart.getData().addAll(serKor, serEng, serMat);
		
		Button btnClose = new Button("닫기");
		// 닫기 버튼 이벤트 처리
		btnClose.setOnAction(e->{
			Stage thisForm = (Stage) btnClose.getScene().getWindow();
			thisForm.close();
		});
		
		VBox vbox = new VBox(20);
		vbox.setPadding(new Insets(20));
		vbox.getChildren().addAll(barChart, btnClose);
		vbox.setAlignment(Pos.CENTER);
		
		this.getChildren().add(vbox); // this는 현재 컨테이너
		
	}
}

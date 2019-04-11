package basic.chartTest;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		// PieChart에 나타날 데이터 세팅
		ObservableList<PieChart.Data> pieData =
			FXCollections.observableArrayList(
				new PieChart.Data("포도", 700),	
				new PieChart.Data("사과", 900),	
				new PieChart.Data("딸기", 100),	
				new PieChart.Data("복숭아", 200),	
				new PieChart.Data("배", 800)	
			);
		
		//PieChart  chart = new PieChart(pieData);
		PieChart  chart = new PieChart();
		chart.setData(pieData);
		
		chart.setTitle("과일 가격 변동");
		chart.setLabelLineLength(30);
		chart.setLegendSide(Side.TOP);
		
		Scene scene = new Scene(chart);
		primaryStage.setTitle("pieChart 연습");
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

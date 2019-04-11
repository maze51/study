package basic.childWinTest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SMMainController {
	private Stage SMMainStage;
	
	public Stage getSMMainStage() {
		return SMMainStage;
	}

	public void setSMMainStage(Stage sMMainStage) {
		SMMainStage = sMMainStage;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Score> scoreTable;

    @FXML
    private TableColumn<Score, String> nameCol;

    @FXML
    private TableColumn<Score, Integer> korCol;

    @FXML
    private TableColumn<Score, Integer> mathCol;

    @FXML
    private TableColumn<Score, Integer> engCol;

    @FXML
    private Button add;

    @FXML
    private Button toBG;
    
    static Stage childStage;
    
    // 학생별 막대그래프 버튼 클릭시
    @FXML
    void toBarGraph(ActionEvent event) {
    	Stage barStage = new Stage();
    	BorderPane root = new BorderPane();
    	HBox hbox = new HBox(20);
    	root.setPrefSize(800, 600);
    	Button btn1 = new Button("닫기");
    	
    	btn1.setOnAction( e -> {
    		barStage.close();
		});
    	
    	CategoryAxis xAxis = new CategoryAxis(); // X축 역할
    	NumberAxis yAxis = new NumberAxis(); // Y축 역할
    	
    	BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
    	
    	
    	XYChart.Series<String, Number> series1 = new XYChart.Series<>();
    	for(int i = 0 ; i < dataList.size() ; i++) {
    		series1.setName("국어");
    		series1.getData().add(new XYChart.Data<>(dataList.get(i).getName(), dataList.get(i).getKorScore()));
    	}
    	
    	XYChart.Series<String, Number> series2 = new XYChart.Series<>();
    	for(int i = 0 ; i < dataList.size() ; i++) {
    		series2.setName("수학");
    		series2.getData().add(new XYChart.Data<>(dataList.get(i).getName(), dataList.get(i).getMathScore()));
    	}
    	
    	XYChart.Series<String, Number> series3 = new XYChart.Series<>();
    	for(int i = 0 ; i < dataList.size() ; i++) {
    		series3.setName("영어");
    		series3.getData().add(new XYChart.Data<>(dataList.get(i).getName(), dataList.get(i).getEngScore()));
    	}
    	
    	bc.getData().addAll(series1, series2, series3);
		bc.setBarGap(7);
		bc.setPrefSize(800, 550);
		
		hbox.getChildren().add(btn1);
		hbox.setAlignment(Pos.CENTER);
		hbox.setMaxHeight(40);
		hbox.setPrefHeight(40);
    	root.setTop(bc);
    	root.setBottom(hbox);
		
		Scene scene = new Scene(root);
		barStage.setTitle("막대 그래프");
		barStage.setScene(scene);
		barStage.show();
		
    }
    
    // 추가 버튼 클릭시
    @FXML
    void toChild(ActionEvent event) {
    	try {
    		childStage = new Stage(StageStyle.DECORATED);
    		childStage.initModality(Modality.WINDOW_MODAL);
    		childStage.initOwner(SMMainStage);
    		
    		Parent childRoot = FXMLLoader.load(getClass().getResource("SMChild.fxml"));
    		Scene scene = new Scene(childRoot);
    		
    		childStage.setTitle("추가");
    		childStage.setScene(scene);
    		childStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    // 테이블뷰 클릭시
    @FXML
    void toPieGraph(MouseEvent event) {
    	Stage pieStage = new Stage();
    	BorderPane root = new BorderPane();
    	HBox hbox = new HBox(20);
    	root.setPrefSize(500, 400);
    	Button btn1 = new Button("닫기");
    	
    	btn1.setOnAction( e -> {
			pieStage.close();
		});
    	
    	Score select = scoreTable.getSelectionModel().getSelectedItem();
    	
    	PieChart chart = new PieChart();
    	if(select !=null) {
    		ObservableList<PieChart.Data> pData = FXCollections.observableArrayList(
        			new PieChart.Data("국어", select.getKorScore()),
        			new PieChart.Data("수학", select.getMathScore()),
        			new PieChart.Data("영어", select.getEngScore()));
    		chart.setData(pData);
    	
    	hbox.getChildren().add(btn1);
		hbox.setAlignment(Pos.CENTER);
		hbox.setMaxHeight(40);
		hbox.setPrefHeight(40);
		root.setTop(chart);
    	root.setBottom(hbox);
		
    	Scene scene = new Scene(root);
    	pieStage.setTitle("파이 그래프");
    	pieStage.setScene(scene);
    	pieStage.show();
    	scoreTable.getSelectionModel().clearSelection();
    	}
    	
    }
    
    private static ObservableList<Score> dataList;
    
    public static ObservableList<Score> getDataList() {
		return dataList;
	}

	@FXML
    void initialize() {
        assert scoreTable != null : "fx:id=\"scoreTable\" was not injected: check your FXML file 'SMMain.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'SMMain.fxml'.";
        assert korCol != null : "fx:id=\"korCol\" was not injected: check your FXML file 'SMMain.fxml'.";
        assert mathCol != null : "fx:id=\"mathCol\" was not injected: check your FXML file 'SMMain.fxml'.";
        assert engCol != null : "fx:id=\"engCol\" was not injected: check your FXML file 'SMMain.fxml'.";
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'SMMain.fxml'.";
        assert toBG != null : "fx:id=\"toBG\" was not injected: check your FXML file 'SMMain.fxml'.";
        
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        korCol.setCellValueFactory(new PropertyValueFactory<>("korScore"));
        mathCol.setCellValueFactory(new PropertyValueFactory<>("mathScore"));
        engCol.setCellValueFactory(new PropertyValueFactory<>("engScore"));
        
        dataList = FXCollections.observableArrayList(
        		new Score("홍길동", 40, 50, 33),
        		new Score("강감찬", 70, 69, 55),
        		new Score("이순신", 88, 79, 66),
        		new Score("이몽룡", 48, 83, 90)
        		);
        
        scoreTable.setItems(dataList);
        
    }
}
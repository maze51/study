package basic.paginationTest;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TablePaginationTest extends Application {
	private int rowsPerPage = 10; // 한 화면에 보여줄 데이터(레코드) 수
	private TableView<Sample> table;
	private List<Sample> data = createData();
	
	// 연습용 데이터를 만들어 주는 메서드 (나중에는 db에서 가져오면 ok)
	private List<Sample> createData(){
		List<Sample> data = new ArrayList<Sample>();
		
		for (int i = 0; i < 123; i++) {
			data.add(new Sample(i, "홍길동" + i, "대전시_" + i));
		}
		return data;
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		table = new TableView<>();
		TableColumn<Sample, Integer> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Sample, String> nameCol = new TableColumn<>("이  름");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Sample, String> addrCol = new TableColumn<>("주  소");
		addrCol.setCellValueFactory(new PropertyValueFactory<>("addr"));
		
		table.getColumns().addAll(idCol, nameCol, addrCol);
		table.setItems(FXCollections.observableArrayList(data));
		
		// Pagination 객체 생성 및 초기화
		// 방법2 ==> 생성자에 전체 페이지수를 지정해서 객체 생성
		//Pagination pp = new Pagination((int)Math.ceil( (double)data.size()/rowsPerPage));
		
		// 방법3 ==> 생성자에 전체 페이지수와 처음 선택된 페이지 index값을 지정해서 객체 생성 
		Pagination pp2 = new Pagination(
				(int)Math.ceil( (double)data.size()/rowsPerPage), 0); // 0은 처음 선택될 페이지가 첫번째 페이지일 경우
		
		// 방법1
		Pagination pagination = new Pagination(); // 객체 생성
		// 전체 페이지수 설정
		pagination.setPageCount((int)Math.ceil( (double)data.size()/rowsPerPage) ); 
		// Math.ceil(4.1); ==> 5.0 (괄호 속 값보다 같거나 큰 정수를 취한다)
		pagination.setCurrentPageIndex(0); // 처음 선택될 페이지의 index값
		pagination.setMaxPageIndicatorCount(10); // 한 화면에 보여줄 Pagination의 페이지 수. 기본값은 10, 보통 쓰는 것도 10
		changeTableView(0); // 1페이지(0번index) 데이터를 TableView에 세팅
		
		// Pagination의 페이지 번호를 변경했을 때 이벤트 처리
		pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, 
					Number oldValue, Number newValue) {
				changeTableView(newValue.intValue()); // 선택한 페이지의 index값을 인수값으로
													// changeTableView()메서드 호출
				
			}
		});
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		root.setCenter(table);
		root.setBottom(pagination);
		
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("Pagination 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Pagination에서 선택한 페이지의 index값을 매개변수로 받아서
	// 해당 페이지에 맞는 데이터를 TableView에 세팅하는 메서드
	// 1페이지 ==> index값이 0, 2페이지 ==> index값이 1,...
	public void changeTableView(int index) {
		// subList(시작, 종료) 시작위치부터 종료위치 이전까지의 데이터를 뽑아온다
		// 0~9 ==> index가 0일 때
		// 10~19 ==> 1일 때
		// 20~29 ==> 2일 때
		// ...
		// 110~119 ==> 11
		// 120~122 ==> 12(실제 데이터는 122까지인데 index값은 129까지 가서 오류 발생) - 아래처럼 하면 해결가능
		int fromIndex = index * rowsPerPage; // 시작위치
		int toIndex = Math.min(fromIndex + rowsPerPage, data.size()); // 종료위치
		
		// 시작위치부터 종료위치 이전까지의 데이터를 가져와
		// TableView에 세팅한다.
		table.setItems(FXCollections.observableArrayList(
				data.subList(fromIndex, toIndex)
		));
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public class Sample{
		private int id;
		private String name;
		private String addr;
		
		
		public Sample() {
			super();
		}
		
		public Sample(int id, String name, String addr) {
			super();
			this.id = id;
			this.name = name;
			this.addr = addr;
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		
		
	}
}



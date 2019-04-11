package basic.controls;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.AlertUtil;

public class TableViewTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		/*
		ObservableList<Member> data = FXCollections.observableArrayList(
				new Member("홍길동", "gildong", 33, "1234-5678", "대전"), 
				new Member("홍길서", "gilwest", 43, "1234-3333", "서울"),
				new Member("홍길남", "gilsouth", 23, "1234-8888", "경북"), 
				new Member("홍길북", "gilnorth", 63, "1234-7777", "광주") 
				);
		 */
		List<Member> memList = new ArrayList<TableViewTest.Member>();
		memList.add(new Member("홍길동", "gildong", 33, "1234-5678", "대전"));
		memList.add(new Member("홍길서", "gilwest", 43, "1234-3333", "서울"));
		memList.add(new Member("홍길남", "gilsouth", 23, "1234-8888", "경북"));
		memList.add(new Member("홍길북", "gilnorth", 63, "1234-7777", "광주"));
		
		ObservableList<Member> data = FXCollections.observableArrayList(memList);
		// 데이터가 담겨진 리스트 자체로 observableList에 담을 수 있다.
		
		
		BorderPane root = new BorderPane();
		/*
		TableView<Member> table = new TableView<Member>();
		table.setItems(data);
		 */
		TableView<Member> table = new TableView<Member>(data);

		TableColumn<Member, String> korNameCol = new TableColumn<Member, String>("한글이름");

		// 현재의 컬럼과 VO객체의 멤버변수를 연결한다. (korName ==> 멤버변수명)
		korNameCol.setCellValueFactory(new PropertyValueFactory<>("korName"));

		TableColumn<Member, String> engNameCol = new TableColumn<Member, String>("영문이름");
		engNameCol.setCellValueFactory(new PropertyValueFactory<>("engName"));

		TableColumn<Member, String> nameCol = new TableColumn<Member, String>("이  름");

		// '이름'컬럼에 '한글이름'컬럼과 '영문이름'컬럼을 포함시킨다
		// nameCol.getColumns().setAll(korNameCol, engNameCol); // 방법1
		nameCol.getColumns().addAll(korNameCol, engNameCol); // 방법2

		TableColumn<Member, Integer> ageCol = new TableColumn<Member, Integer>("나이");
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		//		ageCol.setPrefWidth(150);

		TableColumn<Member, String> telCol = new TableColumn<Member, String>("전화번호");
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));

		TableColumn<Member, String> addrCol = new TableColumn<Member, String>("주소");
		addrCol.setCellValueFactory(new PropertyValueFactory<>("addr"));

		// 각 컬럼을 만든 후 만들어진 컬럼들을 TableView에 추가한다.
		table.getColumns().addAll(nameCol, ageCol, telCol, addrCol);

		//-----------------------------------------------------------------
		GridPane grid = new GridPane();

		Text txt1 = new Text("한글이름"); // label과 비슷한 역할 
		Text txt2 = new Text("영문이름");
		Text txt3 = new Text("나  이");
		Text txt4 = new Text("전화번호");
		Text txt5 = new Text("주  소");

		TextField tfKorName = new TextField();
		TextField tfEngName = new TextField();
		TextField tfAge = new TextField();
		TextField tfTel = new TextField();
		TextField tfAddr = new TextField();

		grid.add(txt1, 1, 1);
		grid.add(txt2, 2, 1);
		grid.add(txt3, 3, 1);
		grid.add(txt4, 4, 1);
		grid.add(txt5, 5, 1);

		grid.add(tfKorName, 1, 2);
		grid.add(tfEngName, 2, 2);
		grid.add(tfAge, 3, 2);
		grid.add(tfTel, 4, 2);
		grid.add(tfAddr, 5, 2);

		grid.setVgap(10);
		grid.setHgap(10);
		//------------------------------------------------------------------
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(5));

		Button btnAdd = new Button("추가");
		btnAdd.setOnAction(e->{
			// 입력되지 않은 데이터가 있는지 검사
			if(tfKorName.getText().isEmpty()) {
				AlertUtil.warning("경고", "입력오류", "한글이름을 입력하세요");
				tfKorName.requestFocus();
				return;
			}
			if(tfEngName.getText().isEmpty() || tfAge.getText().isEmpty() || // 원래는 전부 개별적으로 검사할 필요가 있다
					tfTel.getText().isEmpty() || tfAddr.getText().isEmpty()) {
				AlertUtil.warning("경고", "입력오류", "누락된 데이터가 있습니다");
				return;
			}

			// TableView와 연결된 List에 데이터를 추가하면 된다.
			data.add(new Member(tfKorName.getText(), tfEngName.getText(), 
					Integer.parseInt(tfAge.getText()), tfTel.getText(), tfAddr.getText()));

			AlertUtil.information("확인", "작업성공", tfKorName.getText() + "씨의 정보가 추가되었습니다");

		});
		Button btnEdit = new Button("수정");
		btnEdit.setOnAction(e->{
			// TableView에서 선택한 데이터가 있는지 여부 검사
			if(table.getSelectionModel().isEmpty()) {
				AlertUtil.warning("경고", "자료선택 오류", "수정할 데이터가 없습니다");
				return;
			}
			
			if(tfKorName.getText().isEmpty() || tfEngName.getText().isEmpty() 
					|| tfAge.getText().isEmpty() || tfTel.getText().isEmpty() || tfAddr.getText().isEmpty()) {
				AlertUtil.warning("경고", "입력오류", "누락된 데이터가 있습니다");
				return;
			}
			
			// set(인덱스, 수정할데이터)
			
			// TableView에서 현재 선택된 데이터의 index구하기
			int index = table.getSelectionModel().getSelectedIndex();
			data.set(index, new Member(tfKorName.getText(), tfEngName.getText(), 
					Integer.parseInt(tfAge.getText()), tfTel.getText(), tfAddr.getText())
					);
			AlertUtil.information("확인", "수정작업 성공", tfKorName.getText() + "씨의 정보가 수정되었습니다");
			
		});

		Button btnDel = new Button("삭제");
		btnDel.setOnAction(e->{
			if(table.getSelectionModel().isEmpty()) {
				AlertUtil.warning("경고", "자료선택 오류", "삭제할 데이터를 선택하세요");
				return;
			}
			int index = table.getSelectionModel().getSelectedIndex();
			// 삭제하기
			// 방법1 ==> 데이터가 저장된 List에서 가져오기
			//String name = data.get(index).getKorName();
			
			// 방법2 ==> TableView에서 직접 찾기
			String name = table.getSelectionModel().getSelectedItem().getKorName();
			
			data.remove(index);
			AlertUtil.information("확인", "삭제작업 성공", name + "씨의 정보가 삭제되었습니다");
			
		});
		
		vbox.getChildren().addAll(btnAdd, btnEdit, btnDel);

		//-------------------------------------------------------------------
		// TableView를 클릭했을 때 처리
		table.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// 현재 선택한 줄(row)의 데이터 가져오기
				Member m = table.getSelectionModel().getSelectedItem();
				if(m!=null) {
					tfKorName.setText(m.getKorName());
					tfEngName.setText(m.getEngName());
					tfAge.setText(String.valueOf(m.getAge())); // "" + m.getAge()도 가능. 어쨋든 문자열로 바꿔 넣기
					tfTel.setText(m.getTel());
					tfAddr.setText(m.getAddr());
				}

			}
		});
		
		// disable속성 ==> 해당 컨트롤을 활성화 또는 비활성화 시킨다. 
//		btnAdd.setDisable(true);  // 비활성화 ==> 비활성화된 컨트롤은 사용할 수 없다.
//		btnAdd.setDisable(false); // 활성화
		
		// editable속성 ==> 데이터 편집여부를 결정한다.
		tfKorName.setEditable(false); // 읽기 전용 ==> 데이터 수정 불가
		tfKorName.setEditable(true);  // 편집 가능
		
		root.setPadding(new Insets(10));
		root.setCenter(table);
		root.setBottom(grid);
		root.setRight(vbox);

		Scene scene = new Scene(root, 600, 400);
		primaryStage.setTitle("TableView 연습");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);


	}

	// VO클래스 작성 -- inner 클래스
	public class Member{
		private String korName;
		private String engName;
		private int age;
		private String tel;
		private String addr;

		// 기본 생성자
		public Member() {
			super();
		}

		// 초기화하는 생성자
		public Member(String korName, String engName, int age, String tel, String addr) {
			super();
			this.korName = korName;
			this.engName = engName;
			this.age = age;
			this.tel = tel;
			this.addr = addr;
		}

		public String getKorName() {
			return korName;
		}

		public void setKorName(String korName) {
			this.korName = korName;
		}

		public String getEngName() {
			return engName;
		}

		public void setEngName(String engName) {
			this.engName = engName;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}
	}

}
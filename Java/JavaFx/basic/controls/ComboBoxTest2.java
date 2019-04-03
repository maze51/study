package basic.controls;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
// 그냥 setItems로 설정하면 주소만 나온다. ListViewTest때 처리한 것과 같은 방법으로 처리해줘야.
public class ComboBoxTest2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox vbox = new VBox(10);
		ComboBox<MyFriend> combo = new ComboBox<MyFriend>();
		TextArea taResult = new TextArea();
		
		ObservableList<MyFriend> list = FXCollections.observableArrayList(
			new MyFriend("aaa", "홍길동", "010-1111-1111", "대전"),
			new MyFriend("bbb", "일지매", "010-2222-1234", "서울"),
			new MyFriend("ccc", "성춘향", "010-3333-4211", "경남"),
			new MyFriend("ddd", "이몽룡", "010-4444-5433", "대구"),
			new MyFriend("eee", "변학도", "010-5555-6666", "세종"),
			new MyFriend("fff", "이순신", "010-6666-6453", "제주"),
			new MyFriend("ggg", "강감찬", "010-7777-1111", "강원")
			);
		combo.setItems(list);
		
		// 콤보박스의 목록이 보이는 곳의 내용 변경하기
		// 화면에 나타나는 셀의 내용을 변경한다.
		combo.setCellFactory(new Callback<ListView<MyFriend>, ListCell<MyFriend>>() {
			
			@Override
			public ListCell<MyFriend> call(ListView<MyFriend> param) {
				return new ListCell<MyFriend>() {
					@Override
					protected void updateItem(MyFriend item, boolean empty) { // 빈곳에서 ctrl space로 찾기
						super.updateItem(item, empty);
						if(item==null || empty==true) {
							setText(null); // id 이름은 문자열이라 setText를 쓴다
						} else {
							setText(item.getId() + "[" + item.getName() + "]");
						}
					}
				};
			}
		});
		//============================================
		
		// 콤보박스에서 항목을 선택하면 선택된 내용이 콤보박스의 버튼 영역에 나타난다.
		// 이 부분의 내용도 변경해 줘야 한다
		combo.setButtonCell(new ListCell<MyFriend>() {
			@Override
			protected void updateItem(MyFriend item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null || empty==true) {
					setText(null); // id 이름은 문자열이라 setText를 쓴다
				} else {
					setText(item.getId() + "[" + item.getName() + "]");
				}
			}
		});
		
		// 콤보박스를 클릭했을 때 이벤트 처리
		combo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// 현재 선택된 값 가져오기
				//MyFriend selData = combo.getValue(); // 방법 1
				MyFriend selData = combo.getSelectionModel().getSelectedItem();
				
				if(selData!=null) {
					taResult.setText("ID : " + selData.getId() + "\n");
					taResult.appendText("이름 : " + selData.getName() + "\n");
					taResult.appendText("전화 : " + selData.getTel() + "\n");
					taResult.appendText("주소 : " + selData.getAddr() + "\n");
				}
			}
		});
		
		vbox.getChildren().addAll(combo, taResult);
		
		Scene scene = new Scene(vbox, 400, 300);
		primaryStage.setTitle("콤보박스에 객체 설정하기");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

// VO역할을 할 클래스 작성
class MyFriend{
	private String id;
	private String name;
	private String tel;
	private String addr;
	
	// 기본 생성자
	public MyFriend() {
		super();
	}

	// 초기화하는 생성자
	public MyFriend(String id, String name, String tel, String addr) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
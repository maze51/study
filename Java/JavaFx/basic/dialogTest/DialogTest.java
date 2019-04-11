package basic.dialogTest;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		HBox root = new HBox(10);
		root.setPadding(new Insets(10));
		
		Button btnFileOpen = new Button("Open FileChooser 실행");
		btnFileOpen.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			
			// 열 파일 종류 설정
			fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
				new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.ogg"),
				new ExtensionFilter("All Files", "*.*")
			);
			
			// 창을 열었을 때 보여줄 폴더(디렉토리) 설정
			fileChooser.setInitialDirectory(new File("d:/D_Other"));
			
			File selectedFile = fileChooser.showOpenDialog(primaryStage); // 부모창 객체를 매개변수로 넘겨준다. 열 창의 기준이 될 부모객체가 primaryStage다
			if(selectedFile!=null) {
				System.out.println("선택한 파일 : " + selectedFile.getPath());
			}
		});
		
		Button btnFileSave = new Button("Save FileChooser 실행");
		
		btnFileSave.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			
			// 저장할 파일 종류 설정
			fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
				new ExtensionFilter("All Files", "*.*")
			);
			
			// 창을 열었을 때 보여줄 폴더(디렉토리) 설정
			fileChooser.setInitialDirectory(new File("d:/D_Other"));
			
			File selectedFile = fileChooser.showSaveDialog(primaryStage);
			if(selectedFile!=null) {
				System.out.println("선택한 파일 : " + selectedFile.getPath());
			}
			
		});
		
		Button btnDirectory = new Button("DirectoryChooser 실행");
		
		btnDirectory.setOnAction(e->{
			DirectoryChooser dir = new DirectoryChooser();
			
			dir.setInitialDirectory(new File("d:/D_Other"));
			
			File selectedDir = dir.showDialog(primaryStage);
			if(selectedDir!=null) {
				System.out.println("선택한 디렉토리 : " + selectedDir.getPath());
			}
			
		});
		
		Button btnPopup = new Button("Popup 실행");
		btnPopup.setOnAction(e->{
			Popup popup = new Popup();
			
			HBox popRoot = new HBox();
			popRoot.setAlignment(Pos.CENTER_LEFT);
			popRoot.setStyle("-fx-background-color:black; -fx-background-radius: 20;");
			
			ImageView imgView = new ImageView();
			imgView.setImage(new Image(
				getClass().getResourceAsStream("../../images/ok.png")
			));
			imgView.setFitWidth(30); // ImageView의 크기 설정
			imgView.setFitHeight(30);
			
			// 이미지뷰를 클릭했을 때
			imgView.setOnMouseClicked(evt->{
				popup.hide(); // Popup창 닫기
			});
			
			Label lblMessage = new Label();
			lblMessage.setText("메시지가 왔습니다.");
			lblMessage.setStyle("-fx-text-fill:white;");
//			lblMessage.setTextFill(Color.WHITE);
			HBox.setMargin(lblMessage, new Insets(0, 5, 0, 5));
			
			popRoot.getChildren().addAll(imgView, lblMessage);
			
			popup.getContent().add(popRoot);
			popup.setAutoHide(true); // Popup영역 이외의 좌표를 클릭했을 때도 닫힌다.
			
			popup.show(primaryStage); // 부모창을 매개변수에 지정한다.
		});
		
		
		Button btnCustom = new Button("사용자 작성 Dialog 실행");
		btnCustom.setOnAction(e->{
			try {
				// 새창을 나타내기 위해 Stage객체를 생성한다.
				Stage dialog = new Stage();
				
				dialog.initStyle(StageStyle.DECORATED); // 기본 모습
//				dialog.initStyle(StageStyle.UNDECORATED); // 장식X
//				dialog.initStyle(StageStyle.UTILITY); // 최소한의 플랫폼 장식
//				dialog.initStyle(StageStyle.UNIFIED); // 클라이언트 영역과 장식 사이의 경계를 제거
				
				dialog.initOwner(primaryStage); // 부모창 설정
//				dialog.initModality(Modality.NONE); // 모달리스창으로 설정.
				// 모달창: 자식창이 열려 있으면 열린 동안 부모창 사용 불가.	모달리스창: 별개로 작동가능한 창
				dialog.initModality(Modality.WINDOW_MODAL); // 모달창으로 설정.
				
				dialog.setTitle("자식창연습");
				
				Parent childRoot = FXMLLoader.load(
					getClass().getResource("customDialog.fxml")
				);
				Scene scene = new Scene(childRoot);
				dialog.setScene(scene);
				dialog.setResizable(false); // 창 크기 변경 불가
				
				dialog.show();
				
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
		});
		
		
		root.getChildren().addAll(btnFileOpen, btnFileSave, btnDirectory, btnPopup, btnCustom);
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("dialog 연습");
		primaryStage.setScene(scene);
		primaryStage.setHeight(400);
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}


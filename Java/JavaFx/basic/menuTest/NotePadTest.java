package basic.menuTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class NotePadTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		TextArea textArea = new TextArea();
		MenuBar menuBar = new MenuBar();
		
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);
		root.setCenter(textArea);
		
		Menu fileMenu = new Menu("파일");
		MenuItem newMenuItem = new MenuItem("새로만들기");
		MenuItem openMenuItem = new MenuItem("열기");
		MenuItem saveMenuItem = new MenuItem("다른이름으로 저장");
		MenuItem exitMenuItem = new MenuItem("끝내기");
		
		// 파일 열기
		openMenuItem.setOnAction(e->{
			String UTF8_BOM = "\uFEFF";
			FileChooser fileChooser = new FileChooser();
			
			fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*")
			);
			
			fileChooser.setInitialDirectory(new File("C:\\Users\\PC10\\Desktop"));
			
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			
			if(selectedFile!=null) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(selectedFile));
					String str="";
					StringBuffer strr = new StringBuffer();
//					long startTime = System.currentTimeMillis();
					while(br.ready()) {
						strr.append(br.readLine());
						strr.append("\n");
//						str += br.readLine() + "\n";
					}
					str = strr.toString();
					if(str.startsWith(UTF8_BOM)) {
						str=str.substring(1);
					}
					textArea.setText(str);
					primaryStage.setTitle(selectedFile.getName());
//					long endTime = System.currentTimeMillis();
//					System.out.println("경과시간 : " + (endTime-startTime));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		// 다른 이름으로 저장
		saveMenuItem.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			
			fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*")
			);
			
			fileChooser.setInitialDirectory(new File("C:\\Users\\PC10\\Desktop"));
			
			File selectedFile = fileChooser.showSaveDialog(primaryStage);
			
			if(selectedFile!=null) {
				try {
					String str = textArea.getText();
					FileWriter fw = new FileWriter(selectedFile);
					BufferedWriter br = new BufferedWriter(fw);
					br.write(str.replaceAll("\n", "\r\n"));
					br.flush();
					br.close();
//					FileWriter writer = null;
//					writer = new FileWriter(selectedFile);
//					writer.write(txtA.getText().replaceAll("\n", "\r\n"));
//					writer.close();
					
					primaryStage.setTitle(selectedFile.getName());
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}
		});
		
		// 새로 만들기
		newMenuItem.setOnAction(e->{
			textArea.clear();
			primaryStage.setTitle("NoName.txt");
		});
		
		// 끝내기
		exitMenuItem.setOnAction(e->{
			Platform.exit();
		});
		
		fileMenu.getItems().addAll(newMenuItem, openMenuItem, 
				saveMenuItem, new SeparatorMenuItem(), exitMenuItem);
		
		menuBar.getMenus().addAll(fileMenu);
		
		Scene scene = new Scene(root, 800, 800);
		primaryStage.setTitle("NoName.txt");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

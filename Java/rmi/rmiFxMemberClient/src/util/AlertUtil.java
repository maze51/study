package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertUtil {
	public static void warning(String title, String header, String msg) {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle(title);
		warning.setHeaderText(header);
		warning.setContentText(msg);
		
		warning.showAndWait();
	}
	
	public static void information(String title, String header, String msg) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle(title);
		info.setHeaderText(header);
		info.setContentText(msg);
		
		info.showAndWait();
	}
	
	public static void error(String title, String header, String msg) {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle(title);
		error.setHeaderText(header);
		error.setContentText(msg);
		
		error.showAndWait();
	}
	
	public static boolean confirmation(String title, String header, String msg) {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle(title);
		confirm.setHeaderText(header);
		confirm.setContentText(msg);
		
		boolean result = false;
		ButtonType confirmResult = confirm.showAndWait().get();
		if(confirmResult == ButtonType.OK) { // OK버튼을 누른 경우
			result = true;
		} else if(confirmResult == ButtonType.CANCEL) { // 취소버튼을 누른 경우
			result = false;
		}
		return result;
	}
}

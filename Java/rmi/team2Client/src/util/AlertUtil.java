package util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {
	public static void warning(String title, String header, String msg) {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle(title);
		warning.setHeaderText(header);
		warning.setContentText(msg);
		warning.showAndWait();
	}
	
	public static void information(String title, String header, String msg) {
		Alert information = new Alert(AlertType.INFORMATION);
		information.setTitle(title);
		information.setHeaderText(header);
		information.setContentText(msg);
		information.showAndWait();
	}
	
	public static void error(String title, String header, String msg) {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle(title);
		error.setHeaderText(header);
		error.setContentText(msg);
		error.showAndWait();
	}
	
	public static boolean confirmation(String title, String header, String msg) {
		boolean result = false;
		Alert confirmation = new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle(title);
		confirmation.setHeaderText(header);
		confirmation.setContentText(msg);
		ButtonType confirmResult = confirmation.showAndWait().get();
		if(confirmResult == ButtonType.OK) {
			result = true;
		}else {
			result = false;
		}
		return result;
	}
	
	public static String prompt(String title, String header, String msg, String defaultValue) {
		TextInputDialog prompt = new TextInputDialog(defaultValue);
		prompt.setTitle(title);
		prompt.setHeaderText(header);
		prompt.setContentText(msg);
		Optional<String> result = prompt.showAndWait();
		String strTemp="undefined";
		if(result.isPresent()) {
			strTemp = result.get();
		}
		return strTemp;
	}
		
	public static String prompt(String title, String header, String msg) {
		TextInputDialog prompt = new TextInputDialog();
		prompt.setTitle(title);
		prompt.setHeaderText(header);
		prompt.setContentText(msg);
		Optional<String> result = prompt.showAndWait();
		String strTemp="undefined";
		if(result.isPresent()) {
			strTemp = result.get();
		}
		return strTemp;
	}
	
}

package g_Exception;

public class ExceptionEx04 {
	public static void main(String[] args) {
		copyFile();
		try {
			startInstall();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			deleteFile();
		}
		
	}
	
	static void copyFile(){
		
	}
	
	static void startInstall(){
		
	}
	
	static void deleteFile(){
		
	}
	
}

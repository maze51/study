package basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest02 {

	public static void main(String[] args) {
		File f1 = new File("d:/D_Other/test.txt");
		
		if(f1.exists()){ // 해당 파일이나 디렉토리가 있으면 true, 없으면 false
			System.out.println(f1.getPath() + "은(는) 존재합니다.");
		} else {
			System.out.println(f1.getAbsolutePath() + "은(는) 존재하지 않습니다.");
		}
		
		System.out.println("파일 크기 : " + f1.length() + "byte(s)");
		System.out.println();
		
		File testFile = new File("c:/");
		
		displayFileList(testFile);
	}

	// 지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	public static void displayFileList(File dir){
		if(dir.isFile()) {
			System.out.println("지정한 내용은 파일입니다. 디렉토리를 설정해 주세요.");
			return;
		}
		
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		
		File[] files = dir.listFiles(); // 디렉토리 안의 파일 목록 읽기
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		
		for (int i = 0; i < files.length; i++) {
			String name = files[i].getName();
			String attr = ""; // 파일의 속성 (읽기, 쓰기, 히든, 디렉토리 구분)
			String size = "";
			
			if(files[i].isDirectory()){
				attr = "<DIR>";
			} else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : " ";
				attr += files[i].canWrite() ? "W" : " ";
				attr += files[i].isHidden() ? "H" : " ";
			}
			System.out.printf("%s %5s %12s %s\n", 
					df.format(new Date(files[i].lastModified())), attr,
					size, name);
		}
	}
}

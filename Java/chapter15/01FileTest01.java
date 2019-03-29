/*
순서
FileTest01 02
ByteArrayIOTest 01 02
FileIOTest 01~05
BufferedIOTest 01 02
FileStreamTest 01
DataIOTest
ObjectStreamTest01
Maptest2
 */

package basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		// File객체 만들기 연습
		
		// 1) new File(String 파일명 또는 경로)
		//		==> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분 문자는 '\' 또는 '/' 중 아무거나 사용 가능하다.
		
		//File file = new File("D:\\D_Other\\test.txt");		// 방법 1
		File file = new File("D:/D_Other/test.txt");		// 방법 2
		
		System.out.println("name = " + file.getName());
		System.out.println("파일인가요? => " + file.isFile());
		System.out.println("디렉토리(폴더)인가요? => " + file.isDirectory());
		System.out.println("------------------------------------------");
		System.out.println();
		
		File file2 = new File("D:/D_Other");
		System.out.println("name = " + file2.getName());
		System.out.println("파일인가요? => " + file2.isFile());
		System.out.println("디렉토리(폴더)인가요? => " + file2.isDirectory());
		System.out.println("------------------------------------------");
		System.out.println();
		
		// 2) new File(File parent, String child);
		//		==> 'parent'디렉토리 안에 있는 'child'파일을 나타낸다.
		File file3 = new File(file2, "test.txt");
		System.out.println("name = " + file3.getName());
		System.out.println("파일인가요? => " + file3.isFile());
		System.out.println("디렉토리(폴더)인가요? => " + file3.isDirectory());
		System.out.println("------------------------------------------");
		System.out.println();
		
		// 3) new File(String parent, String child);
		File file4 = new File("d:/D_Other", "test.txt");
		//==============================================================
		
		/*
			- 디렉토리(폴더) 만들기
		1) mkdir() ==> File 객체의 경로 중에서 마지막 위치의 디렉토리를 만들어 준다.
				   ==> 반환값 : 생성 성공 (true), 생성 실패 (false)
				   ==> 중간의 경로가 모두 미리 만들어져 있어야 한다. 그렇지 않으면 만들지 못한다.
		2) mkdirs() ==> 중간의 경로가 만들어져 있지 않으면 중간 경로도 같이 만들어준다.
		
		 */
		File file5 = new File("D:/D_Other/연습용");
		if(file5.mkdir()){ // 만들기에 성공하면 
			System.out.println(file5.getName() + " 폴더 새로만들기 성공!!");
		} else {
			System.out.println(file5.getName() + " 폴더 새로만들기 실패~~");
		}
		System.out.println();
		
		File file6 = new File("D:/D_Other/test/java/src");
		if(file6.mkdirs()){
			System.out.println(file6.getName() + " 폴더 새로만들기 성공!!");
		} else {
			System.out.println(file6.getName() + " 폴더 새로만들기 실패~~");
		}
		System.out.println();
	}

}

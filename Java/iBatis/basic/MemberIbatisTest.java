package basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MemberIbatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// ibatis를 이용하여 DB자료를 처리하는 순서
		try {
			// 1. ibatis의 설정파일(sqlMapConfig.xml)을 읽어와 실행한다.
			// 1-1. xml문 읽어오기
			Charset charset = Charset.forName("UTF-8"); // UTF-8 설정되어 있지만, 확실하게 하기 위해서 읽어올 형식 지정
			Resources.setCharset(charset); // 문자 인코딩 캐릭터셋 설정하기
			
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader객체 닫기
			//-------------환경설정끝-----------------
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 작업을 수행한다.
			
			// 2-1. insert 작업 연습
			/*
			System.out.println("insert할 데이터 입력하기");
			System.out.print("회원ID : ");
			String memId = scan.next();
			System.out.print("이  름 : ");
			String memName = scan.next();
			System.out.print("전  화 : ");
			String memTel = scan.next();
			scan.nextLine();
			System.out.print("주  소 : ");
			String memAddr = scan.nextLine();
			
			// 입력한 데이터를 VO객체에 담는다.
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(memId);
			memVo.setMem_name(memName);
			memVo.setMem_tel(memTel);
			memVo.setMem_addr(memAddr);
			
			// SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식) smc.insert("namespace값.id값", 파라미터클래스);
			//	==> 반환값 : 작업성공시 null
			Object obj = smc.insert("mymember.insertMember", memVo);
			if(obj==null){
				System.out.println("insert 작업 성공");
			} else {
				System.out.println("insert 작업 실패");
			}
			System.out.println("-----------------------------------");
			*/
			
			// 2-2. update작업 연습
			System.out.println("update 작업 시작...");
			
			// Update할 정보를 MemberVO객체에 담는다.
			MemberVO memVo2 = new MemberVO();
			memVo2.setMem_id("a001");
			memVo2.setMem_name("이순신");
			memVo2.setMem_tel("010");
			memVo2.setMem_addr("서울");
			// 위처럼 입력받아 넣을 수 있지만 편의상 이렇게만 했음
			
			// 실행은 smc.update()메서드를 이용한다.
			// 반환값은 작업에 성공한 레코드 수 이다.
			/*
			int cnt = smc.update("mymember.updateMember", memVo2);
			if(cnt>0){
				System.out.println("update작업 성공");
			} else {
				System.out.println("update작업 실패");
			}
			*/
			System.out.println("-----------------------------------");
			
			// 2-3. delete작업 연습
			System.out.println("delete 작업 시작...");
			
			// 실행은 smc.delete()메서드를 이용한다.
			// 반환값은 작업에 성공한 레코드 수 이다.
			/*
			int cnt2 = smc.delete("mymember.deleteMember", "a001");
			
			if(cnt2>0){
				System.out.println("delete 작업 성공");
			} else {
				System.out.println("delete 작업 실패");
			}
			System.out.println("-----------------------------------");
			*/
			
			// 2-4. select 연습
			// 1) 응답의 결과가 여러개일 경우
			//		응답의 결과가 여러개일 경우에는 queryForList()메서드를
			//		사용한다. 이 메서드는 여러개의 레코드를 VO에 담은 후
			//		이 VO데이터를 List에 추가해서 반환해 준다.
			/*
			System.out.println("select연습(결과가 여러개일 경우)");
			List<MemberVO> memList = smc.queryForList("mymember.getAllMember");
			for (MemberVO memVo3 : memList) {
				System.out.println("ID : " + memVo3.getMem_id());
				System.out.println("이름 : " + memVo3.getMem_name());
				System.out.println("전화 : " + memVo3.getMem_tel());
				System.out.println("주소 : " + memVo3.getMem_addr());
				System.out.println("--------------------------------");
			}
			System.out.println("출력 끝...");
			*/
			
			// 2) 응답의 결과가 1개일 경우
			//		응답 결과가 1개의 레코드일 경우에는 queryForObject()메서드를 사용한다.
			System.out.println("select연습(결과가 1개일 경우)");
			MemberVO memVo4 = 
					(MemberVO) smc.queryForObject("mymember.getMember", "a001");
			System.out.println("ID : " + memVo4.getMem_id());
			System.out.println("이름 : " + memVo4.getMem_name());
			System.out.println("전화 : " + memVo4.getMem_tel());
			System.out.println("주소 : " + memVo4.getMem_addr());
			System.out.println("--------------------------------");
			System.out.println("출력 끝...");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

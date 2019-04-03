/**
 * 
 */

function inputPro(){
		// 입력한 값들을 가져온다
		f = document.ff;
		
		// 공백검증 - 이름, 이메일, 아이디, 휴대폰
		name = f.name.value;
		if(name.trim().length<1){
			alert("이름을 입력해주세요");
			f.name.focus();
			return;
		}
		email = f.email.value;
		if(email.trim().length<1){
			alert("이메일을 입력해주세요");
			f.email.focus();
			return;
		}
		id = f.id.value;
		idlength = id.trim().length;
		if(id.trim().length<1){
			alert("아이디를 입력해주세요");
			f.id.focus();
			return;
		}
		// 길이 검증
		if(idlength < 6 || idlength > 8){
			alert("아이디는 6~8자 사이로 입력하세요");
			return;
		}
		phone = f.phone.value;
		if(phone.trim().length<1){
			alert("휴대폰 번호를 입력해주세요");
			f.phone.focus();
			return;
		}
		
		f.submit();
	}
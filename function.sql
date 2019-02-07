--      <함수> (23P)
-- 함수는? 어떤 통이 있다고 하자. 통 안에는 규칙(X+7)이 있다. 어떤 값(10)이 들어오면? 처리해서 반환(17)한다.
-- 이 때 들어가는 값은 파라미터, 인수 / 반환하는 값은 리턴값

-- || (파이프라인) 둘 이상의 문자열을 연결     CONCAT 두 문자열을 연결하여 반환

SELECT 'a'||'bcde' FROM DUAL;
SELECT MEM_ID||'name is '||MEM_NAME FROM MEMBER;

SELECT CONCAT('My Name is', MEM_NAME) FROM MEMBER;

SELECT CHR(65) "CHR", ASCII('ABC') "ASCII" FROM DUAL;
SELECT ASCII( CHR(65) ) RESULT FROM DUAL;
SELECT CHR(75) "CHR", ASCII('K') "ASCII" FROM DUAL;

-- 회원테이블의 회원ID Colomn의 ASCII값을 검색하시오
SELECT MEM_ID               "회원 ID"
     , ASCII(MEM_ID)        "회원 ASCII"
     , CHR(ASCII(MEM_ID))   "회원 CHR"
FROM MEMBER;

SELECT LOWER('DATA manipulation Language')      AS "LOWER"
     , UPPER('DATA manipulation Language')      AS "UPPER"
     , INITCAP('DATA manipulation Language')      AS "INITCAP"
FROM DUAL;

--24P 회원테이블의 회원ID를 대문자로 변환하여 검색하시오
--(ALIAS명은 변환전ID, 변환후ID)

SELECT MEM_ID AS 변환전ID, UPPER(MEM_ID)  AS 변환후ID
FROM MEMBER;

SELECT LPAD('Java', 10) "LPAD"      --10은 크기(바이트) 수
     , RPAD('Java', 10) "RPAD"
FROM   DUAL;                        --DUAL은 가상 테이블

SELECT LPAD('*',1, '*') FROM DUAL;  --왼쪽에 여백, 총 1바이트
SELECT LPAD('*',2, '*') FROM DUAL;
SELECT LPAD('*',3, '*') FROM DUAL;
SELECT LPAD('*',4, '*') FROM DUAL;
SELECT LPAD('*',5, '*') FROM DUAL;  --왼쪽에 여백, 총 5바이트, 마지막 '*'는 빈 공백을 *로 채우라는 뜻

SELECT LPAD ('Java', 10, '*') "LPAD" --총 10바이트, 왼쪽에 여백, 오른쪽 네 칸에 Java들어감, 빈 공간은 *로 채워라
     , RPAD ('Flex', 12, '^') "RPAD" --총 12바이트, 오른쪽에 여백, 왼쪽 네 칸에 Flex들어감, 빈 공간은 ^로 채워라
FROM   SYS.DUAL;                     --DUAL은 관리자 계정에서 준 가상의 테이블. 여기에 MEMBER를 쓴다면 MEMBER의 행 개수만큼 출력됨

--24P 상품테이블의 소비자가격과 소비자가격을 치환하여 다음과 같이 출력되게 하시오
--ALIAS: PROD_PRICE, PROD_RESULT
SELECT PROD_PRICE                 AS PROD_PRICE
      ,LPAD (PROD_PRICE, 10, '*') AS PROD_RESULT
FROM   PROD;

--25P TRIM
SELECT '<' || LTRIM('      AAA      ') || '>' "LTRIM1"                      --왼쪽에서 제거
     , '<' || LTRIM('Hello World', 'He') || '>' "LTRIM2"                    --(이처럼 문자를 지정한 경우)왼쪽에서 일치하는 문자를 제거
     , '<' || LTRIM('llo World', 'He') || '>' "LTRIM3"                      --일치하는 문자가 없어 그대로
     , '<' || LTRIM('Hello He World', 'He') || '>' "LTRIM4"                 --있으니까 제거
     , '<' || LTRIM('HeHello HeWorld', 'He') || '>' "LTRIM5"                --왼쪽에서 일치하는 문자를 제거(여러개라도 전부)
FROM DUAL;

SELECT '<' || RTRIM('      AAA      ') || '>' "RTRIM1"                      --오른쪽에서 제거
     , '<' || RTRIM('Hello World', 'ld') || '>' "RTRIM2"                    --일치하는 문자를 오른쪽에서 제거
FROM DUAL;

SELECT '<' || TRIM('      AAA      ') || '>' "TRIM1"                        --왼, 오른쪽에서 제거
     , '<' || TRIM(LEADING 'a' FROM TRIM('     aaAaBaAaa')) || '>' "TRIM2"  --왼쪽에서 제거
     , '<' || TRIM('a' FROM 'aaAaBaAaa') || '>' "TRIM3"                     --왼, 오른쪽에서 제거(생략과 BOTH는 동일)
     , '<' || TRIM(BOTH 'a' FROM 'aaAaBaAaa') || '>' "TRIM4"                --역시 양쪽에서 제거
     , '<' || TRIM(TRAILING 'a' FROM 'aaAaBaAaa') || '>' "TRIM5"            --오른쪽에서 제거
FROM SYS.DUAL;

--책25P      SUBSTR
SELECT SUBSTR('SQL PROJECT', 1, 3)  RESULT1
     , SUBSTR('SQL PROJECT', 5)     RESULT2         -- 글자수 없으면 끝까지. (...,5,7)과 의미가 같음
     , SUBSTR('SQL PROJECT', -7, 3) RESULT3         -- 음수를 넣으면 오른쪽부터 찾는다. 여기서는 P 위치. 거기부터 세 글자
FROM DUAL;

--회원테이블의 성씨 조회
SELECT MEM_ID       회원ID
     , MEM_NAME
     , SUBSTR(MEM_NAME,1,1) 성씨
FROM MEMBER;

--25P 상품테이블의 상품명의...
SELECT PROD_ID             상품코드
     , PROD_NAME           상품명
FROM   PROD
WHERE SUBSTR(PROD_NAME,4,2)='칼라';           -- WHERE 자리에 조건식을 써야 한다
--WHERE  PROD_NAME LIKE '___칼라%';            --이런 방법도 가능

--장바구니 테이블(CART)에서 장바구니 번호(CART_NO)중에 04월에 해당하는 데이터만 출력하기
--ALIAS : CART_NO, CART_PROD

SELECT CART_NO
     , CART_PROD
FROM CART
WHERE SUBSTR(CART_NO,5,2)='04';         -- 5번째에서 시작해서 두 글자

--P.25
--상품테이블의 상품코드(PROD_ID)에서 왼쪽 4자리, 오른쪽 6자리를 검색하시오
--ALIAS명은 상품코드, 대분류, 순번
SELECT PROD_ID  상품코드
     , SUBSTR(PROD_ID,1,4) 대분류
     , SUBSTR(PROD_ID,5)  순번 -- 글자수 없으면 끝까지
--     , SUBSTR(PROD_ID,5,6)  순번 -- 이렇게 써도 같음
FROM PROD;

--회원ID를 다음과 같이 출력하기
--ALIAS: 회원ID, 앞1자, 나머지
--예시: a001, a, 001

SELECT MEM_ID               회원ID
     , SUBSTR(MEM_ID,1,1)   앞1자
     , SUBSTR(MEM_ID,2)     나머지
FROM MEMBER;

--26P       REPLACE
SELECT REPLACE('SQL Project', 'SQL', 'SSQQLL')  문자치환1   --1번 문자열에서 2번 문자열을 찾아 3번으로 대체
     , REPLACE('Java Flex Via', 'a')            문자치환2   --1번 문자열에서 2번 문자열을 찾아 삭제
     , RTRIM('Java Flex Via', 'a')              문자치환3   --일치하는 문자를 오른쪽에서 제거
FROM DUAL;

--거래처 테이블의 거래처명 중 '삼' -> '육'으로 치환
SELECT BUYER_NAME
     , REPLACE(BUYER_NAME,'삼','육') 치환
FROM BUYER;

--회원테이블의 회원성명 중 '이'->'리'로 치환해 검색
--ALIAS: 회원명, 회원명치환
SELECT MEM_NAME                     회원명
     , REPLACE(MEM_NAME,'이','리')  회원명치환
FROM MEMBER;

--26P       INSTR: 위치를 리턴한다.
SELECT INSTR('hello heidi', 'he')       RESULT1     --앞에서부터 찾아 결과는 1
     , INSTR('hello heidi', 'he', 3)    RESULT2     --마지막 3은 시작점 지정. 시작점부터 가장 가깝게 일치하는 대상의 위치 리턴
     , INSTR('hello heidi', 'he', 1, 2)    RESULT3  --마지막 2는 번째. 첫자리부터 시작해서 두 번째 맞는 대상의 위치 리턴
FROM DUAL;

--문제: I have a hat.
--위 문장에서 첫번째 ha의 위치 출력
--위 문장에서 두번째 ha의 위치 출력
--alias: 첫번째위치, 두번째위치
SELECT INSTR('I have a hat.','ha')            첫번째위치
     , INSTR('I have a hat.','ha', 1, 2)      두번째위치
FROM DUAL;

--mepch@test.com  상위 문자에서 @를 기준으로 다음과 같이 출력하기
--아이디 | 도메인         mepch | test.com
SELECT SUBSTR('mepch@test.com',1,INSTR('mepch@test.com','@')-1)     아이디    --처음부터 @위치까지
     , SUBSTR('mepch@test.com',INSTR('mepch@test.com','@')+1)       도메인    --@위치부터 끝까지
FROM DUAL;

SELECT SUBSTR(MEM_MAIL,1,INSTR(MEM_MAIL,'@')-1)   아이디                    --이런 방식으로 메일의 아이디와 도메인을 분리할 수 있다
     , SUBSTR(MEM_MAIL,INSTR(MEM_MAIL,'@')+1)     도메인
FROM MEMBER;

--26P       LENGTH
SELECT LENGTH('SQL 프로젝트')   LENGTH          --글자 단위 길이(어떤 글자든 상관없이)
     , LENGTHB('SQL 프로젝트')   LENGTHB        --바이트 단위 길이(알파벳, 숫자, 공백 등은 1바이트, 한글, 한자 등은 3바이트)
FROM DUAL;

SELECT BUYER_ID                 거래처코드
     , LENGTH(BUYER_ID)         거래처코드길이
     , BUYER_NAME               거래처명
     , LENGTH(BUYER_NAME)       거래처명길이
     , LENGTHB(BUYER_NAME)      거래처명byte수
FROM BUYER;

SELECT ABS(-365) FROM DUAL;     -- 절대값

SELECT SIGN(12), SIGN(0), SIGN(-55) FROM DUAL;      --양수, 음수, 0을 판단해 1, -1, 0을 리턴

SELECT POWER(3, 2), POWER(2, 10) FROM DUAL;     --승수 값(a의 b승)

SELECT SQRT(2), SQRT(9) FROM DUAL;      --n의 제곱근

--27P       GREATEST, LEAST
--비교해서 큰/작은 값을 리턴한다
--숫자보다 문자가 크다 / ㄱ보다 ㅅ이 크다
SELECT GREATEST(10, 20, 30)     큰값
     , LEAST(10, 20, 30)        작은값
FROM DUAL;

SELECT ASCII('ㄱ') FROM DUAL;        --ㄱ에 해당하는 ASCII코드 리턴
SELECT CHR(14910641) FROM DUAL;      --ASCII코드에 해당하는 값 리턴

SELECT GREATEST('강아지', 256, '송아지')      큰값
     , LEAST('강아지', 256, '송아지')         작은값
FROM DUAL;

SELECT GREATEST('龜', 4728903572398572925) FROM DUAL;

--27P 회원 테이블에서 회원이름, 마일리지를 출력하시오
--단 마일리지가 1000보다 작은 경우 1000으로 변경
SELECT MEM_NAME     회원명
     , MEM_MILEAGE  마일리지
     , GREATEST(MEM_MILEAGE, 1000)  변경마일리지
FROM MEMBER;

--27P       ROUND           TRUNC
--지정된 자릿수 밑에서 반올림       버림
SELECT ROUND(345.123, -2) 결과 FROM DUAL;
SELECT ROUND(345.123, -1) 결과 FROM DUAL;
SELECT ROUND(345.123, 0) 결과 FROM DUAL;
SELECT ROUND(345.123, 1) 결과 FROM DUAL;
SELECT ROUND(345.123, 2) 결과 FROM DUAL;

SELECT TRUNC(345.123, 2) 결과 FROM DUAL;
SELECT TRUNC(345.123, 1) 결과 FROM DUAL;
SELECT TRUNC(345.123, 0) 결과 FROM DUAL;
SELECT TRUNC(345.123, -1) 결과 FROM DUAL;
SELECT TRUNC(345.123, -2) 결과 FROM DUAL;

--회원 테이블의 마일리지를 12로 나눈 값을 검색(소수2째자리 살리기 반올림, 절삭)

SELECT MEM_MILEAGE                  마일리지
     , ROUND(MEM_MILEAGE/12, 2)     반올림
     , TRUNC(MEM_MILEAGE/12, 2)     버림
FROM MEMBER;

--ROUND('191.666666',2) 결과는?
--여기에서 ",2"라는 것은 소수점 2째자리까지 살아남는다는 것임
--따라서 191.66이 살아남는데 그 다음 3번째 자리가 6이므로 191.67이 나옴

SELECT 191.222
     , ROUND(191.222 + 0.05,1)  올림 -- 올림하려면 해당 자리값에 5 더해주기
FROM SYS.DUAL;

/*
상품테이블의 상품명, 원가율(매입가/판매가)을 비율(%)로
반올림 없는 것과 소수 첫째자리 살리기 반올림 비교) 검색하시오
ALIAS는 상품명, 원가율1, 원가율2
비율(%)은 비율에 100을 곱한 값임
소수점 첫째자리를 살리는 반올림 - ROUND((PROD_COST / PROD_SALE)*100,1) 여기서 ",1"의미는 소수점 첫째자리를 살린다(둘째 자리에서 반올림한다)는 의미
*/

SELECT PROD_NAME                            상품명
     ,(PROD_COST/PROD_SALE)*100             원가율1
     , ROUND((PROD_COST/PROD_SALE)*100, 1)  원가율2
FROM PROD;

--28P       MOD
--나머지를 구하는 함수. JAVA의 %역할
SELECT MOD(10,3) FROM DUAL; -- 나머지 1이 출력됨


--28P   FLOOR 내림(바닥으로) CEIL 올림(천장으로)
--결과는 반드시 정수가 된다
SELECT FLOOR(1332.69), CEIL(1332.69) FROM DUAL;
SELECT FLOOR(-1332.69), CEIL(-1332.69) FROM DUAL;
SELECT FLOOR(2.69), CEIL(2.69) FROM DUAL;
SELECT FLOOR(-2.69), CEIL(-2.69) FROM DUAL;

-- -3.141592의 내림(FLOOR)과 올림(CEIL)을 구하시오
--ALIAS: 원본, 내림, 올림
SELECT -3.141592         원본
     , FLOOR(-3.141592)  내림
     , CEIL(-3.141592)   올림
FROM SYS.DUAL;
                        --이 때 DUAL과 SYS.DUAL은 같다. SYS는 관리자 계정명을 의미. SELECT * FROM PC10.MEMBER처럼 사용도 가능

--28P       SYSDATE
--현재 날짜와 시간 값.
--날짜형 데이터는 +, -연산이 가능하다(날짜/시각에 더하거나 빼기). *, /는 불가능
SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH:MI:SS')  현재시간
     , SYSDATE - 1      "어제 이시간"
     , SYSDATE + 1      "내일 이시간"
     , TO_CHAR(SYSDATE + 1/24,'YYYY-MM-DD HH:MI:SS')        "1시간 후"
FROM DUAL;

--TO_CHAR(): 문자로 형변환하는 함수. 이 때 형식을 지정해줄 수 있음


--28P 회원테이블의 생일과 12000일째 되는 날을 검색하시오
--ALIAS는 회원명, 생일, 12000일째
--12000일째 되는 날을 구하는 문제이므로 날짜형 데이터에 12000을 더해주면 된다

SELECT MEM_NAME          회원명
     , MEM_BIR           생일
     , MEM_BIR+12000     "12000일째"
    --, '1995-01-01' + 12000         --이러면 에러. 문자와 숫자가 만나면 숫자가 우선. 문자는 자동 형변환됨. 그런데 이 때 중간에 '-'이 있어 자동 형변환 불가.
     , TO_DATE('1995-01-01') + 12000 --이렇게 해결
FROM MEMBER;
/*
문제: 나는 몇 일을 살았는가? TO_DATE()함수 이용
단, 밥은 하루에 3번. 소수점 2째자리까지 표현
ALIAS: 내생일, 산 일수, 밥먹은수
밥먹은 비용은 3000원으로 처리
*/

SELECT TO_DATE('1986-03-30')                            내생일
     , ROUND(SYSDATE - TO_DATE('1986-03-30'), 2)          산일수
     , ROUND((SYSDATE - TO_DATE('1986-03-30'))*3, 2)      밥먹은수
     , ROUND((SYSDATE - TO_DATE('1986-03-30'))*9000, 2)   밥먹은비용
FROM DUAL;

--28P       ADD_MONTHS(월을 더함)  NEXT_DAY(해당 날짜 이후 가장 빠른 요일의 날짜)  LAST_DAY
SELECT ADD_MONTHS(SYSDATE, 5) FROM DUAL;

SELECT NEXT_DAT(SYSDATE, '월요일')     --오늘부터 다가오는 월요일이 몇일인가?
     , LAST_DAY(SYSDATE)              --이번달 마지막 날은 언제인가?
FROM DUAL;     

--이번달이 며칠 남았나 검색
--ALIAS: 오늘날짜, 이달마지막날짜, 이번달에남은날짜
SELECT SYSDATE                          오늘날짜
     , LAST_DAY(SYSDATE)                이달마지막날짜
     , LAST_DAY(SYSDATE) - SYSDATE      이번달에남은날짜
FROM DUAL;

--29P     날짜의 ROUND, TRUNC
SELECT ROUND(SYSDATE,'MM')
     , TRUNC(SYSDATE,'MM')
FROM DUAL;

SELECT ROUND(SYSDATE,'YEAR')
     , TRUNC(SYSDATE,'YEAR')
FROM DUAL;

--날짜 사이의 달수(MONTHS_BETWEEN)
SELECT MONTHS_BETWEEN(SYSDATE, '2000-01-01')
FROM DUAL;

--29P           EXTRACT 날짜형일 때 사용가능
SELECT EXTRACT(YEAR FROM SYSDATE) 년도
     , EXTRACT(MONTH FROM SYSDATE) 월
     , EXTRACT(DAY FROM SYSDATE) 일
     , EXTRACT(HOUR FROM SYSTIMESTAMP) + 9
     , EXTRACT(MINUTE FROM SYSTIMESTAMP)
     , EXTRACT(SECOND FROM SYSTIMESTAMP)
FROM DUAL;
     -- +9 이유는? 서울이 UTC+9라서
     
--생일이 3월인 회원을 검색하시오
--ALIAS: 회원ID(MEM_ID), 회원명(MEM_NAME), 생일(MEM_BIR) 75/03/23
--"생일이 3월인 회원"이라는 것은 조건. 쿼리에서 조건에 해당하는 절은? WHERE
SELECT MEM_ID       회원ID
     , MEM_NAME     회원명
     , MEM_BIR      생일
FROM MEMBER
WHERE EXTRACT(MONTH FROM MEM_BIR) = 3;  --결과가 숫자니까 3으로 비교하는 것이 보다 올바를 것
--WHERE MEM_BIR LIKE '%/03/%';      다른방법
--WHERE SUPSTR(MEM_BIR,4,2) = '03'; 다른방법2       이 때는 문자 : 문자 비교

--입고상품(BUYPROD) 중 3월에 입고된 내역 검색
--ALIAS: 상품코드(BUY_PROD), 입고일자(BUY_DATE), 매입수량(BUY_QTY), 매입단가(BUY_COST)
--EXTRACT, SUBSTR, LIKE 사용하기

SELECT BUY_PROD     상품코드
     , BUY_DATE     입고일자
     , BUY_QTY      매입수량
     , BUY_COST     매입단가
FROM BUYPROD
WHERE EXTRACT(MONTH FROM BUY_DATE) = 3;
--WHERE SUBSTR(BUY_DATE,4,2) = '03';
--WHERE BUY_DATE LIKE '%/03/%';

----------------------------------------------------
--30P       형변환(Conversion)
--CAST: 명시적으로 형변환

SELECT '[' || 'Hello' || ']'                        형변환
     , '[' || CAST('Hello' AS CHAR(10)) || ']'      형변환
     , '[' || CAST('Hello' AS VARCHAR2(10)) || ']'  형변환
FROM DUAL;

SELECT '2018/03/29' + 10        --이 때 ''안은 문자로 생각 - 실행하면 오류
FROM DUAL;

SELECT CAST('2018/03/29' AS DATE) + 10      --이러면 정상적으로 10일이 더해짐
FROM DUAL;

SELECT CAST('1997/12/25' AS DATE)
FROM DUAL;

--***
SELECT '2017/01/23' + 1         --이때도 위처럼 오류
FROM DUAL;

SELECT '2017/01/23'
     , TO_DATE('2017/01/23') + 1
     , CAST('2017/01/23' AS DATE) + 1
FROM DUAL;

--TO_CHAR 문자로 변환    TO_NUMBER 숫자로 변환    TO_DATE 날짜로 변환
SELECT SYSDATE
     , TO_CHAR(SYSDATE, 'AD YYYY, CC"세기" ')     --(SYSDATE를 오른쪽 문자 형식으로 변환하겠다는 의미)
FROM DUAL;

SELECT TO_CHAR('2017-01-23','YYYY.MM.DD HH24:MI')   --이때 '2017-'은 (날짜 형식의)문자. 어떤 형식? 오른쪽 ''안의 형식. 그런데 이렇게는 오류
FROM DUAL;

SELECT TO_CHAR(TO_DATE('2008-12-25','YYYY.MM.DD'),'YYYY.MM.DD HH24:MI:SS')  --TO_DATE써서 날짜형으로 바꿔줘야
FROM DUAL;

SELECT TO_CHAR(CAST('2008-12-25' AS DATE), 'YYYY.MM.DD HH24:MI')  --혹은 CAST('' AS DATE)로도 해결 가능
FROM DUAL;

--32P 상품테이블에서 상품입고일을 '2008-09-28 12:00:00'형식으로 나오게 검색
--ALIAS: 상품명(PROD_NAME), 상품판매가(PROD_SALE), 입고일(PROD_INSDATE)
SELECT PROD_NAME        상품명
     , PROD_SALE        상품판매가
     , TO_CHAR(CAST(PROD_INSDATE AS DATE), 'YYYY-MM-DD HH24:MI:SS')     입고일
--     , TO_CHAR(PROD_INSDATE,'YYYY-MM-DD HH:MI:SS') --DATE형이라 이렇게 가능
FROM PROD;

--31P 회원이름(MEM_NAME)과 생일(MEM_BIR)로 다음처럼 출력되게 작성하시오. (MEMBER)
--김은대님은 1976년 1월 출생이고 태어난 요일은 목요일

SELECT MEM_NAME || '님은 ' || TO_CHAR(MEM_BIR,'YYYY "년 " MM "월 출생이고 태어난 요일은 " DAY')
FROM MEMBER;
-- 문자열과 문자열을 묶을 때 ||사용. 문자열 안에 문자열 삽입 시 ""사용

/*
PPT 198P
LOB는 대용량 데이터 타입으로 오라클8부터 지원
보통 잘 안 쓰지만 CLOB는 좀 쓴다. 자유게시판에 1333자를 넘는 글이 들어갈 수 있기 때문.
단 데이터 입출력이 좀 어렵다
*/

/*
형변환
날짜, 숫자, 문자 -> 문자로
숫자 -> 숫자로 예) '419'(숫자형 문자) -> 419
날짜 -> 날짜로 예) '19-01-20'(날짜형 문자) 여기에 +1하면 오류     '2019-01-30 10:37:11' 여기는 +1하면 일이 1 올라감
*/

--  숫자를 문자로
SELECT TO_CHAR(1234.6,'L9,999.00')
FROM DUAL;
-- 숫자가 이런 형식('L9,999.00')을 만나 어떻게 될 것인가? 자리수 매핑 되는대로 일단 쓴다. L은 LOCALE(지역화폐).
-- 그래서 원화표시가 뜨고 소수점 아래 매핑 안되는 자릿수는 0으로 써 줌
-- 매핑된다=유효하다. 매핑되면 유효한 데이터를 출력해 준다. 안되면? 무효한 데이터. 무효할 때는 생략한다(9의 경우). 무효할 때는 0을 써 준다(0의 경우).
-- 0은 왜? 자연수는 0이 앞에 들어갈 수 없다. 하지만 소수점 아래 소수부에는 0을 쓸 수 있다.

SELECT TO_CHAR(-1234.6,'L9999.00PR')
     , TO_CHAR(-1234.6,'L9999.00MI')
FROM DUAL;
-- PR은? - 데이터를 처리할 때 사용. -일 때 전체 결과 앞뒤에 <>를 붙여 음수임을 표현한다.
-- MI는? 맨 뒤에 -를 붙여 음수임을 표현한다.

SELECT TO_CHAR(255, 'XXX') FROM DUAL;       -- 해당 숫자를 16진수로 출력


-- 상품 판매가를 다음과 같은 형식으로 출력하시오        ￦230,000
--ALIAS: 상품ID(PROD_ID), 상품명(PROD_NAME), 판매가(PROD_SALE)
SELECT PROD_ID          상품ID
     , PROD_NAME        상품명
     , TO_CHAR(PROD_SALE,'L9,999,999')      판매가
FROM PROD;

--31P 상품테이블에서 상품코드, 상품명, 매입가격, 소비자가격, 판매가격을 출력하시오
--가격은 천단위 구분 및 원화표시
SELECT PROD_ID          상품코드
     , PROD_NAME        상품명
     , TO_CHAR(PROD_COST,'L999,999,999')   매입가격
     , TO_CHAR(PROD_PRICE,'L999,999,999')  소비자가격
     , TO_CHAR(PROD_SALE,'L999,999,999')   판매가격
FROM PROD;

--매입테이블(BUYPROD)의 매입가의 평균 AVG(BUY_COST)를 다음 형식으로 출력
--소수점 2번째 자리까지 반올림한다  ￦210,000.350
SELECT TO_CHAR(ROUND(AVG(BUY_COST),2), 'L999,999.000')
FROM BUYPROD;

--장바구니테이블(CART)의 판매수량(CART_QTY)의 AVG(CART_QTY)를 다음 형식으로 출력
--소수점 첫번째 자리까지 살리고 반올림처리
--ALIAS: 평균값, ROUND로 처리, TO_CHAR로 처리

SELECT AVG(CART_QTY)            평균값
     , ROUND(AVG(CART_QTY),1)   ROUND로처리
     , TO_CHAR(ROUND(AVG(CART_QTY),1), '999.0')    TO_CHAR로처리
FROM CART;

--31P 숫자/문자에 따른 정렬
SELECT 3.1415 FROM DUAL; -- 숫자는 오른쪽정렬

SELECT '3.1415' FROM DUAL; -- 문자는 왼쪽정렬

SELECT TO_NUMBER('3.1415') FROM DUAL; -- 이때는? 숫자 형식의 문자지만 숫자가 되었다. 그래서 오른쪽정렬

SELECT TO_NUMBER('￦1,200') + 1 FROM DUAL; -- 문자로 인식. 해결하려면?

SELECT TO_NUMBER('￦1,200','L999,999') + 1
     , TO_CHAR(TO_NUMBER('￦1,200','L999,999') + 1,'L999,999')
FROM DUAL;
-- 첫줄(TO_NUMBER)은 이게 이런 형식의 숫자라고 알려주는 것. 이러면 +1이 가능해짐

/*
32P 회원테이블(MEMBER)에서 이쁜이회원(MEM_NAME='이쁜이')의 회원ID 2~4문자열을 숫자형으로 치환한 후
10을 더하여 새로운 회원ID로 조합하시오(ALIAS=회원ID(MEM_ID), 조합회원ID)
이 때 001 + 10 은 문자 + 숫자 -> 숫자 + 숫자 -> 001이 1로 바뀜 -> 결과는 11
이후 b에 다시 붙여라. 이 때 b사이에 0을 붙여줘야(TO_CHAR 등의 방법으로)
*/
SELECT MEM_ID                                       회원ID
     , SUBSTR(MEM_ID,1,1)
     || TRIM(TO_CHAR(SUBSTR(MEM_ID,2) + 10, '000')) 조합회원ID
     , TO_CHAR(SUBSTR(MEM_ID,2) + 10, '000')
FROM MEMBER
WHERE MEM_NAME = '이쁜이';

--SUBSTR로 뽑고, 10을 더하고, TO_CHAR로 011모양으로 만들어 줌. ||로 b와 붙여주고, TRIM으로 공백제거

--30P TO_DATE
SELECT '2017-01-24' + 3  --에러. 문자로 인식했기 때문
FROM DUAL;

SELECT TO_DATE('2017-01-24') + 3    --이러면 3일이 잘 더해짐
FROM DUAL;

SELECT TO_DATE('2017-01-24','YYYY-MM-DD') + 3   -- 오라클에 이게 날짜형 문자라고 알려줘도 해결가능
FROM DUAL;

--TO_DATE는 알려주는 것
--TO_CHAR는 바꾸는 것
--SELECT TO_CHAR('2017-01-24','YYYY-MM-DD HH:MI:SS') -- TO_DATE를 써서 바꿔주면 해결
SELECT TO_CHAR(TO_DATE('2017-01-24','YYYY-MM-DD'),'YYYY-MM-DD HH:MI:SS')
FROM DUAL;

SELECT '200803101234' + 3 -- 자동 형변환
FROM DUAL;

SELECT TO_DATE('200803101234') + 3 -- 에러. 날짜형 문자로 인식하지 못함. 그저 숫자형 문자. 때문에 자동 형변환 불가
FROM DUAL;

SELECT TO_DATE('200803101234','YYYYMMDDHHMI') + 3 -- 이게 이런 형식이라고 알려주면 인식가능
FROM DUAL;

--32P 회원테이블(MEMBER)에서 주민등록번호1(MEM_REGNO1)을 날짜로 치환한 후 검색하시오
--ALIAS는 회원명(MEM_NAME), 주민등록번호1(MEM_REGNO1), 치환날짜(MEM_REGNO1 활용)
SELECT MEM_NAME             회원명
     , MEM_REGNO1           주민등록번호1
     , TO_DATE(MEM_REGNO1)  치환날짜 -- TO_DATE(MEM_REGNO1,'YYMMDD')로 알려줄 수도 있음
FROM MEMBER;

--장바구니 테이블(CART)에서 장바구니번호(CART_NO)를 날짜로 치환한 후 다음과 같이 출력하기
--2005년 3월 14일(ALIAS: 장바구니번호, 상품코드, 판매일, 판매수)
SELECT CART_NO      장바구니번호
     , CART_PROD    상품코드
--     , TO_DATE(SUBSTR(CART_NO,1,8),'YYYYMMDD')      판매일
     , TO_CHAR(TO_DATE(SUBSTR(CART_NO,1,8),'YYYY-MM-DD'),'YYYY"년 "MM"월 " DD"일"') 판매일
     , CART_QTY     판매수
FROM CART;
-- 날짜형 변환이 끝난 뒤, 특정 형식으로 출력하려면 TO_CHAR(       ,'YYYY"년 "MM"월 " DD"일"') 처럼 쉼표 찍고, 큰따옴표로 중간에 들어갈 문자를 찍어주기

----------------------------------------------------
--101P 집계함수
--집계는? 묶는다. 묶어서 SUM합계, AVG평균, MAX최대값, MIN최소값, COUNT개수세기 등을 구한다

SELECT PROD_COST
FROM PROD
ORDER BY 1;

SELECT AVG(DISTINCT PROD_COST) --AS "중복된 값은 제외"
     , AVG(ALL PROD_COST) --AS "DEFAULT로써 모든 값을 포함."   생략도 ALL과 동일
     , AVG(PROD_COST) -- AS "매입가 평균"
FROM PROD;

--상품테이블의 상품분류별 매입가격 평균 값
SELECT PROD_LGU     상품분류
     , ROUND(AVG(PROD_COST),2)    매입가격
--이것도 묶어야 하니 매입가격 평균을 구해 한 값으로 만든다
FROM PROD
GROUP BY PROD_LGU          --묶어주기
ORDER BY 1;                 --첫번째 열로 정렬한다

--상품테이블의 총 판매가격 평균값을 구하라(ALIAS: 상품총판매가격평균)

SELECT AVG(PROD_SALE)    상품총판매가격평균
FROM PROD;

--상품테이블의 상품분류별 판매가격 평균값을 구하라
--ALIAS: 상품분류, 상품분류별판매가평균

SELECT PROD_LGU                 상품분류
     , ROUND(AVG(PROD_SALE),2)  상품분류별판매가평균
FROM PROD
GROUP BY PROD_LGU
ORDER BY 상품분류;

----------------------------------------------------
--101P      COUNT        자료 수가 몇 개인가?
SELECT COUNT(DISTINCT PROD_COAST)
     , COUNT(ALL PROD_COAST)
     , COUNT(PROD_COAST)
     , COUNT(*)         -- 기본키를 포함해서 세기 때문에 중복이 나올 수 없다
     , COUNT(DISTINCT PROD_ID||PROD_NAME||PROD_COAST)
-- 하나로 묶어서 세기. PROD_ID가 들어갔으므로 DISTINCT는 의미가 없다
FROM PROD;

SELECT COUNT(*) AS RESULT1
     , COUNT(PROD_LGU) AS RESULT2
FROM PROD;

--상품테이블의 상품분류별 자료의 수
SELECT PROD_LGU "상품 분류"
     , COUNT(*) "상품 분류별 자료의 수"
FROM PROD
GROUP BY PROD_LGU;

--102P 거래처테이블(BUYER)의 담당자(BUYER_CHARGER)를 컬럼으로 하여 COUNT집계하시오
--ALIAS: 자료수(DISTINCT), 자료수, 자료수(*)

SELECT COUNT(DISTINCT BUYER_CHARGER)    "자료수(DISTINCT)"
     , COUNT(BUYER_CHARGER)             "자료수"
     , COUNT(*)                         "자료수(*)"
FROM BUYER;
--여기서는 중복 DATA 없음. 그룹별로 세는 게 아님. 그래서 GROUP BY가 필요없음

--회원테이블의 취미종류수를 COUNT집계(ALIAS: 취미종류수)
SELECT COUNT(DISTINCT MEM_LIKE)    취미종류수
FROM MEMBER
ORDER BY 1;

--회원테이블의 취미별 인원수 COUNT집계(ALIAS: 취미, 자료수, 자료수(*)
SELECT MEM_LIKE         취미
     , COUNT(MEM_LIKE)  자료수
     , COUNT(*)         "자료수(*)" -- 모든 컬럼이 다 들어간 것
FROM MEMBER
GROUP BY MEM_LIKE
ORDER BY 1;

--회원테이블의 직업종류수를 COUNT집계(ALIAS: 직업종류수)
SELECT COUNT(DISTINCT MEM_JOB) 직업종류수
FROM MEMBER;

--회원테이블의 직업별 COUNT집계(ALIAS: 직업, 자료수, 자료수(*))
--SELECT MEM_JOB          직업
--     , COUNT(MEM_JOB)   자료수
--     , COUNT(*)         "자료수(*)"
--FROM MEMBER
--GROUP BY MEM_JOB
--ORDER BY 1;

SELECT MEM_JOB          
     , COUNT(MEM_ID)
     , COUNT(*)         
FROM MEMBER
GROUP BY MEM_JOB;
--집계함수가 아닌 MEM_JOB을 GROUP BY에 기술

--장바구니테이블의 회원(CART_MEMBER)별 COUNT집계
--(ALIAS: 회원ID, 구매수(DISTINCT), 구매수, 구매수(*))
--SELECT CART_MEMBER                      회원ID
--     , COUNT(DISTINCT CART_MEMBER)      "구매수(DISTINCT)"
--     , COUNT(CART_MEMBER)               구매수
--     , COUNT(*)                         "구매수(*)"
--FROM CART
--GROUP BY CART_MEMBER
--ORDER BY 1;

SELECT CART_MEMBER
     , COUNT(DISTINCT CART_PROD)
     , COUNT(CART_PROD)
     , COUNT(*)
FROM CART
GROUP BY CART_MEMBER
ORDER BY 1;

--104P <중요!!!>       SELECT구문에서 집계함수 이외의 컬럼들은 GROUP BY절에 기술해야 한다

----------------------------------------------------
--102P      MAX, MIN
--상품 중 최고판매가격과 최저판매가격
SELECT MAX(PROD_SALE) "최고판매가"
     , MIN(PROD_SALE) "최저판매가"
FROM PROD;

--상품 중 거래처별 최고매입가격과 최저매입가격
SELECT PROD_BUYER       "거래처"
     , MAX(PROD_COST)   "최고매입가"
     , MIN(PROD_COST)   "최저매입가"
FROM PROD
GROUP BY PROD_BUYER;

--장바구니테이블의 회원별 최대구매수량을 검색
--ALIAS: 회원ID, 최대수량, 최소수량
SELECT CART_MEMBER      회원ID
     , MAX(CART_QTY)    최대수량
     , MIN(CART_QTY)    최소수량
FROM CART
GROUP BY CART_MEMBER
ORDER BY 1;

--오늘이 2005년 7월 11일이라 가정하고 장바구니테이블에 발생될 추가주문번호를 검색하시오
--ALIAS: 최고치주문번호, 추가주문번호
--일단 2005년 7월 11일 찾기. 주문번호가 현재 002까지 왔음. 거기에 고객 한명 더 들어오면 다 똑같고 끝이 3이 됨. GROUP BY X

SELECT MAX(CART_NO) 최고치주문번호
     , MAX(CART_NO) + 1 추가주문번호
FROM CART
WHERE CART_NO LIKE '20050711%';

--103P      SUM

--상품테이블의 매입가의 총 합계값
SELECT SUM(DISTINCT PROD_COST)
     , SUM(PROD_COST)
FROM PROD;

--상품테이블의 판매가의 총 합계값
SELECT SUM(PROD_SALE)   "상품 판매가 총합계"
FROM PROD;

--상품테이블의 상품분류별 판매가 합계값
SELECT PROD_LGU
     , SUM(PROD_SALE)   "분류별 판매가 합계"
FROM PROD
GROUP BY PROD_LGU;

--상품입고테이블의 상품별 입고수량의 합계값
SELECT BUY_PROD     상품
     , SUM(BUY_QTY) 입고수량합계
FROM   BUYPROD
GROUP BY BUY_PROD;

--장바구니테이블의 상품분류별 판매수량의 합계값(ALIAS 상품, 판매수량합계)
SELECT CART_PROD            상품
     , SUM(CART_QTY)        판매수량합계
FROM CART
GROUP BY CART_PROD
ORDER BY 1;

--회원테이블의 회원 전체의 마일리지 평균, 마일리지 합계, 최고 마일리지, 최소 마일리지, 인원수를 검색
--ALIAS 마일리지평균, 마일리지합계, 최고마일리지, 최소마일리지, 인원수
SELECT AVG(MEM_MILEAGE)     마일리지평균
     , SUM(MEM_MILEAGE)     마일리지합계
     , MAX(MEM_MILEAGE)     최고마일리지
     , MIN(MEM_MILEAGE)     최소마일리지 
     , COUNT(MEM_MILEAGE)   인원수 
FROM MEMBER;

--상품테이블에서 판매가 전체의 평균, 합계, 최고값, 최저값, 자료수를 검색
--ALIAS 평균, 합계, 최고값, 최저값, 자료수
SELECT AVG(PROD_SALE)       평균
     , SUM(PROD_SALE)       합계
     , MAX(PROD_SALE)       최고값
     , MIN(PROD_SALE)       최저값
     , COUNT(PROD_SALE)     자료수
FROM PROD;

--상품테이블에서 상품분류, 거래처 별로 최고판매가, 최소판매가, 자료수를 검색하시오

SELECT PROD_LGU     상품분류
     , PROD_BUYER   거래처
     , MAX(PROD_SALE)    최고판매가
     , MIN(PROD_SALE)    최소판매가
     , COUNT(PROD_ID)      자료수
FROM PROD
GROUP BY PROD_LGU, PROD_BUYER
ORDER BY 1,2;
--상품분류는 대그룹, 거래처는 소그룹 -> 대그룹부터 써 준다

--104P PPT 215-216P
--장바구니테이블에서 회원, 상품분류별로 구매수량평균,
--구매수량합계, 자료수를 검색하시오 ?
--( Alias는 회원ID, 상품분류, 구매수량평균, 구매수량합계, 자료수 )
--(회원ID, 상품분류 순으로 SORT하시오)

SELECT CART_MEMBER                      회원
     , SUBSTR(CART_PROD,1,4)            상품분류        -- 여기까지는 생략해도 작동에 문제 없지만, 컬럼의 의미를 알 수 없어진다
     , ROUND(AVG(CART_QTY),2)           구매수량평균
     , SUM(CART_QTY)                    구매수량합계
     , COUNT(CART_NO)                   자료수
FROM CART
GROUP BY CART_MEMBER, SUBSTR(CART_PROD,1,4)
ORDER BY 1,2;

--회원테이블에서 지역(주소1의 2자리),생일년도별로 마일리지평균,
--   마일리지합계, 최고마일리지, 최소마일리지, 자료수를 검색하시오 ?
--   ( Alias는 지역,생일연도, 마일리지평균, 마일리지합계, 
--                  최고마일리지,최소마일리지, 자료수 )

SELECT SUBSTR(MEM_ADD1,1,2)     지역
     , EXTRACT(YEAR FROM MEM_BIR) 생일연도            -- 자료형 확인. DATE형. EXTRACT 사용가능.
     , AVG(MEM_MILEAGE)         마일리지평균
     , SUM(MEM_MILEAGE)         마일리지합계
     , MAX(MEM_MILEAGE)         최고마일리지
     , MIN(MEM_MILEAGE)         최소마일리지
     , COUNT(MEM_ID)            자료수
FROM MEMBER
GROUP BY SUBSTR(MEM_ADD1,1,2), EXTRACT(YEAR FROM MEM_BIR)
ORDER BY 1,2;

----------------------------------------------------
--책35P PPT 228P       DECODE
--DECODE는 JAVA의 SWITCH문과 유사

SELECT DECODE (7
            , 10, 'A'
            , 9, 'B'
            , 8, 'C'
            , 'D')
FROM DUAL;

--35P 상품 분류 중...
SELECT PROD_NAME            상품명
     , SUBSTR(PROD_LGU,1,2)
     , PROD_SALE            판매가
     , DECODE(SUBSTR(PROD_LGU,1,2)          -- 따질 대상
            ,'P1',PROD_SALE * 1.1           -- 맞으면 옆문장 수행
            ,'P2',PROD_SALE * 1.15          -- 맞으면 옆문장 수행
            ,PROD_SALE                      -- else부분. 그냥 출력하고 끝
            ) 변경판매가
FROM PROD;

--대전측기사에서는 3월에 생일인(MEM_BIR) 회원을 대상으로 마일리지를 10% 인상해주는 이벤트를 시행하고자 한다.
--생일이 3월이 아닌 회원은 짝수인 경우만 5% 인상 처리한다.
--ALIAS: 회원ID, 회원명, 마일리지, 변경마일리지

SELECT MEM_ID       회원ID
     , MEM_NAME     회원명
     , MEM_MILEAGE  마일리지
     , DECODE(EXTRACT(MONTH FROM MEM_BIR)  
            , 03, MEM_MILEAGE * 1.1
            , DECODE(MOD(EXTRACT(MONTH FROM MEM_BIR), 2)
                 , 0,MEM_MILEAGE * 1.05
                 , MEM_MILEAGE
            ) 
            ) 변경마일리지
FROM MEMBER;
--EXTRACT를 쓰려면 데이터형이 DATE여야
--3월인지 아닌지, 짝수인지 아닌지 두 번의 분기. MOD는 나머지를 구하는 함수

--회원이 서울이면 서울특별시... 출력
--ALIAS 회원ID, 회원명, 지역
SELECT MEM_ID       회원ID
     , MEM_NAME     회원명
     , DECODE(SUBSTR(MEM_ADD1,1,2),
         '서울', '서울특별시',
         '충남', '충청남도',
         '대전', '대전광역시',
         '대구', '대구광역시',
         MEM_ADD1
        ) 지역
FROM MEMBER;

--회원의 주민번호 뒷자리 첫글자를 사용하여 1이면 남성, 2이면 여성, 3이면 2000이후남성 4이면 2000이후여성
--ALIAS: 회원ID, 회원명, 주민번호 뒷자리, 성별
SELECT MEM_ID           회원ID
     , MEM_NAME         회원명
     , MEM_REGNO2       "주민번호 뒷자리"
     , DECODE(SUBSTR(MEM_REGNO2,1,1),
            '1', '남성',
            '2', '여성',
            '3', '2000년이후 남성',
            '4', '2000년이후 여성',
            MEM_REGNO2
            )   성별
FROM MEMBER;

----------------------------------------------------
--35P
--SIMPLE-CASE-EXPRESSION
--CASE와 WHEN 사이에 비교 대상이 있을 때

SELECT
  CASE '나' WHEN '철호' THEN '아니다'
            WHEN '너' THEN '아니다'
            WHEN '나' THEN '맞다'
            ELSE '모르겠다'
  END RESULT
FROM DUAL;

--SEARCHED-CASE-EXPRESSION
--CASE와 WHEN 사이에 비교 대상이 없을 때

SELECT
  CASE      WHEN '철호' = '나' THEN '아니다'
            WHEN '너' = '나' THEN '아니다'
            WHEN '나' = '우리' THEN '맞다'
            ELSE '모르겠다'
  END RESULT
FROM DUAL;

SELECT PROD_NAME 상품
     , PROD_LGU 분류
     ,
 CASE
  WHEN PROD_LGU = 'P101' THEN '컴퓨터제품'
  WHEN PROD_LGU = 'P102' THEN '전자제품'
  WHEN PROD_LGU = 'P201' THEN '여성캐주얼'
  WHEN PROD_LGU = 'P202' THEN '남성캐주얼'
  WHEN PROD_LGU = 'P301' THEN '피혁잡화'
  WHEN PROD_LGU = 'P302' THEN '화장품'
  WHEN PROD_LGU = 'P401' THEN '음반/CD'
  WHEN PROD_LGU = 'P402' THEN '도서'
  WHEN PROD_LGU = 'P403' THEN '문구류'
  ELSE '미등록분류'
 END 상품분류
FROM PROD;

--회원테이블(member)에서 주민등록번호(MEM_REGNO2)의 첫번째 자리를 통해 성별 구분
--ALIAS: 회원ID, 회원명, 주민번호2 ,성별 (CASE WHEN 구문 사용)
SELECT MEM_ID           회원ID
     , MEM_NAME         회원명
     , MEM_REGNO2       주민번호2
 , CASE
  WHEN SUBSTR(MEM_REGNO2,1,1) = '1' THEN '남성'
  WHEN SUBSTR(MEM_REGNO2,1,1) = '2' THEN '여성'
  WHEN SUBSTR(MEM_REGNO2,1,1) = '3' THEN '남성'
  WHEN SUBSTR(MEM_REGNO2,1,1) = '4' THEN '여성'
--  WHEN SUBSTR(MEM_REGNO2,1,1) = IN('1','3') THEN '남성'     
--  WHEN SUBSTR(MEM_REGNO2,1,1) = IN('2','4') THEN '여성'

--  CASE WHEN MOD(SUBSTR(MEM_REGNO2,1,1),2) = '0' THEN '남성'
-- SEARCHED-CASE-EXPRESSION은 이런 식으로 보다 간결하게 표현 가능. 그래서 더 많이 사용
  ELSE '기타'
 END 성별
FROM MEMBER;

SELECT PROD_NAME 상품
     , PROD_PRICE 판매가
     , CASE
        WHEN (100000 - PROD_PRICE) >= 0 THEN '10만원 미만'
        WHEN (200000 - PROD_PRICE) >= 0 THEN '10만원대'
        WHEN (300000 - PROD_PRICE) >= 0 THEN '20만원대'
        WHEN (400000 - PROD_PRICE) >= 0 THEN '30만원대'
        WHEN (500000 - PROD_PRICE) >= 0 THEN '40만원대'
        WHEN (600000 - PROD_PRICE) >= 0 THEN '50만원대'
        WHEN (700000 - PROD_PRICE) >= 0 THEN '60만원대'
        WHEN (800000 - PROD_PRICE) >= 0 THEN '70만원대'
        WHEN (900000 - PROD_PRICE) >= 0 THEN '80만원대'
        WHEN (1000000 - PROD_PRICE) >= 0 THEN '90만원대'
        ELSE '100만원 이상'
       END 가격대
FROM PROD;
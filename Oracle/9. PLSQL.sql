--책 뒤쪽 7P

/
SET SERVEROUTPUT ON;
/
DECLARE
 v_i        NUMBER(9,2) := 0;   -- 할당된다는 것. 정수부 9, 소수부 2
 v_name     VARCHAR2(20);
 c_pi       CONSTANT NUMBER(8,6) := 3.141592;
 v_flag     BOOLEAN NOT NULL := TRUE;
 v_date     VARCHAR2(10) := TO_CHAR(SYSDATE, 'YYYY-MM-DD');
BEGIN
--    DBMS_OUTPUT.PUT_LINE('v_i:'||v_i);
    --System.out.println('v_i:'||v_i);
    v_name := '홍길동';
-- DBMS_OUTPUT.ENABLE;
    DBMS_OUTPUT.PUT_LINE('v_i:'||v_i);
    DBMS_OUTPUT.PUT_LINE('v_name:'||v_name);
    DBMS_OUTPUT.PUT_LINE('c_pi:'||c_pi);
    DBMS_OUTPUT.PUT_LINE('v_date:'||v_date);
END;
/

--IF문

--조건이 TRUE이면 이하 문장을 실행하고
--조건이 FALSE이면 관련된 문장을 통과한다.
--ELSIF절은 여러 개가 가능하나, ELSE절은 한 개만 가능하다
DECLARE
    V_NUM NUMBER := 37;
BEGIN
    IF MOD(V_NUM, 2) = 0 THEN
        DBMS_OUTPUT.PUT_LINE(V_NUM || '는 짝수');
    ELSE
        DBMS_OUTPUT.PUT_LINE(V_NUM || '는 홀수');
    END IF;
END;

--0225 anonymous block을 활용할 수 있다
--DECALRE부분은 선택, BEGIN END부분은 필수. :=는 할당연산자
--PL/SQL의 변수 네 가지: SCALAR, REFERENCES, COMPOSITE, BIND

--조건에 따른 다중 ELSIF
/
SET SERVEROUTPUT ON;
/
DECLARE
    --SCALAR변수
    V_NUM NUMBER := 77;
BEGIN
    --쓰려면 먼저 SETSERVEROUTPUT ON을 해줘야 한다. 처음 한 번 하면 로그아웃 전까지 계속 기능함
    IF V_NUM > 90 THEN
        DBMS_OUTPUT.PUT_LINE('수');
    ELSIF V_NUM > 80 THEN
        DBMS_OUTPUT.PUT_LINE('우');
    ELSIF V_NUM > 70 THEN
        DBMS_OUTPUT.PUT_LINE('미');
    ELSE
        DBMS_OUTPUT.PUT_LINE('분발합시다');
    END IF;
END;
/

/
DECLARE
    V_AVG_SALE PROD.PROD_SALE%TYPE;
    --NUMBER(10) -- REFERENCES변수
    V_SALE NUMBER := 500000;
    --SCALAR변수
BEGIN
    SELECT AVG(PROD_SALE) INTO V_AVG_SALE FROM PROD;
    IF V_SALE < V_AVG_SALE THEN
        DBMS_OUTPUT.PUT_LINE('평균 단가가 500000 초과입니다.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('평균 단가가 500000 이하입니다.');
    END IF;
END;
/

--회원테이블에서 아이디가 'e001'인 회원의 마일리지가 5000을 넘으면 'VIP 회원'
--그렇지 않다면 '일반회원'으로 출력하시오(회원이름, 마일리지 포함)

--CASE문을 사용해서 원하는 대로 출력하는 방법
SELECT CASE WHEN MEM_MILEAGE > 5000 THEN 'VIP회원'
            ELSE '일반회원'
        END "결과"
FROM MEMBER WHERE MEM_ID = 'e001';

--PL/SQL을 사용하는 방법
/
DECLARE
    V_MILEAGE NUMBER(7);
    -- 이러면 일단 NULL이 할당됨
BEGIN
    SELECT MEM_MILEAGE INTO V_MILEAGE
    FROM MEMBER
    WHERE MEM_ID = 'e001';
    -- V_MILEAGE라는 특정 변수에 담겠다는 의미
    
    IF V_MILEAGE > 5000 THEN
        DBMS_OUTPUT.PUT_LINE('VIP 회원');
    ELSE
        DBMS_OUTPUT.PUT_LINE('일반회원');
    END IF;
END;
/
/*
상품분류가 화장품인 상품의 평균판매가를 구한 후 평균판매가가 3천원 미만이면 싸다,
3000이상~6000미만이면 보통, 6000이상~9000미만이면 비싸다, 9000이상이면 너무비싸다 출력
단, CASE문 사용하여 처리하기
출력형식 : 화장품의 평균판매가는 5000원이고 보통이다.
*/

/
DECLARE
    --변수 선언부
    V_SALE NUMBER(8);
BEGIN
    SELECT AVG(PROD_SALE) INTO V_SALE
    FROM PROD
    WHERE PROD_LGU = (SELECT LPROD_GU FROM LPROD WHERE LPROD_NM = '화장품');
    --WHERE절에 서브쿼리를 사용하기
    
   CASE WHEN V_SALE < 30000 THEN
            DBMS_OUTPUT.PUT_LINE('화장품의 평균판매가는 '|| V_SALE ||'원이고 싸다.');
        WHEN V_SALE >= 30000 AND V_SALE < 60000 THEN
            DBMS_OUTPUT.PUT_LINE('화장품의 평균판매가는 '|| V_SALE ||'원이고 보통.');
        WHEN V_SALE BETWEEN 60000 AND 90000 THEN
            DBMS_OUTPUT.PUT_LINE('화장품의 평균판매가는 '|| V_SALE ||'원이고 비싸다.');
        WHEN V_SALE >= 90000 THEN
            DBMS_OUTPUT.PUT_LINE('화장품의 평균판매가는 '|| V_SALE ||'원이고 너무비싸다.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('기타.');
    END CASE;
END;
/
/*
IF문을 쓸 경우
    IF V_SALE < 3000 THEN
        DBMS_OUTPUT.PUT_LINE('화장품의 평균판매가는 '|| V_SALE ||'원이고 싸다.');
    ELSIF V_SALE < 6000 THEN
        DBMS_OUTPUT.PUT_LINE('화장품의 평균판매가는 '|| V_SALE ||'원이고 보통이다.');
    ELSIF V_SALE < 9000 THEN
        DBMS_OUTPUT.PUT_LINE('화장품의 평균판매가는 '|| V_SALE ||'원이고 비싸다.');
    ELSIF V_SALE >= 9000 THEN
        DBMS_OUTPUT.PUT_LINE('화장품의 평균판매가는 '|| V_SALE ||'원이고 너무비싸다.');
    END IF;
END;
/
*/

--가파치 업체의 지역을 검색하여 다음과 같이 출력하기. 단CASE문사용
--대구부산: 경남 대전: 충청 서울인천: 수도권 기타: 기타
/
DECLARE
    V_REGION VARCHAR2(30);
BEGIN
    SELECT SUBSTR(BUYER_ADD1,1,2) INTO V_REGION
    FROM BUYER
    WHERE BUYER_NAME = '가파치';
        
    CASE WHEN V_REGION = '대구' OR V_REGION = '부산' THEN
            DBMS_OUTPUT.PUT_LINE('경남');
        WHEN V_REGION = '대전' THEN
            DBMS_OUTPUT.PUT_LINE('충청');
        WHEN V_REGION IN('서울','인천') THEN
            DBMS_OUTPUT.PUT_LINE('수도권');
        ELSE
            DBMS_OUTPUT.PUT_LINE('기타');
    END CASE;
END;
/

--1부터 10까지 더하기
/
DECLARE
    V_SUM NUMBER := 0;
    V_VAR NUMBER := 1;
BEGIN
    WHILE V_VAR <= 10 LOOP
        V_SUM := V_SUM + V_VAR;
        V_VAR := V_VAR + 1;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('1부터 10까지의 합=' || V_SUM);
END;
/
--별로 피라미드 모양 만들기
--여백은 별 숫자와 반대로 반복될때마다 줄어든다
/
DECLARE 
  V_ID NUMBER := 1;
  V_B INT := 9;
BEGIN
    DBMS_OUTPUT.PUT_LINE(' ');
  WHILE V_ID < 20 LOOP
    DBMS_OUTPUT.PUT(RPAD(' ',V_B,' '));
    DBMS_OUTPUT.PUT_LINE(RPAD('*',V_ID , '*'));
    V_ID  := V_ID  + 2;
    V_B := V_B - 1;
  END LOOP;  
END;
/

--다중 WHILE문을 사용하여 구구단 만들기

DECLARE
    DAN INT := 2;
    NUM INT := 1;
BEGIN
    WHILE DAN < 10 LOOP
        DBMS_OUTPUT.PUT_LINE(DAN || '단');
        
        WHILE NUM < 9 LOOP
            DBMS_OUTPUT.PUT_LINE(DAN || 'X' || NUM || '=' || DAN * NUM);
            NUM := NUM + 1;
        END LOOP;
            
        NUM := 1;
        DAN := DAN + 1;
    END LOOP;
END;

/
SET SERVEROUTPUT ON
/
DECLARE
    V_NM VARCHAR2(60);
BEGIN
    SELECT LPROD_NM
    INTO V_NM
    FROM LPROD
    WHERE LPROD_GU = 'P201';
    IF SQL%FOUND THEN
        DBMS_OUTPUT.PUT_LINE('받은 값 =' || V_NM);
        DBMS_OUTPUT.PUT_LINE('행 수 =' || SQL%ROWCOUNT);
    END IF;
END;
/

--상품분류테이블에 6개의 코드 증가

DECLARE
    V_ADD NUMBER(5) := 1000;
    V_CODE CHAR(4) := '';
    V_ID NUMBER(5);
BEGIN
    SELECT MAX(LPROD_ID) INTO V_ID FROM LPROD;
    WHILE V_ADD <= 1005 LOOP
        V_ADD := V_ADD + 1;
        V_ID := V_ID + 1;
        V_CODE := 'TT' || SUBSTR(TO_CHAR(V_ADD), -2);
        INSERT INTO LPROD(LPROD_ID, LPROD_GU, LPROD_NM) VALUES(V_ID, V_CODE, 'LOOP TEST');
        IF SQL%FOUND THEN
            DBMS_OUTPUT.PUT_LINE('신규코드' || V_CODE || '가 추가되었음');
        END IF;
    END LOOP;
END;
/

--WHILE문을 사용하여 상기 INSERT된 데이터를 삭제하시오
--삭제되었는지 확인 메시지 출력

DECLARE
    CNT INT := 10;
    I INT := 1;
BEGIN
    WHILE I <= 6 LOOP
        DELETE FROM LPROD WHERE LPROD_ID = CNT;
        CNT := CNT + 1;
        I := I + 1;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('삭제 완료');
END;
/

DECLARE
    V_SUM INT := 0;
    V_VAR INT := 1;
BEGIN
<<MYLABEL>>
    V_SUM := V_SUM + V_VAR; --1 3 6 10 15 21 28 36 45 55
    V_VAR := V_VAR + 1;     --2 3 4 5 6 7 8 9 10 11
    
    IF V_VAR <= 10 THEN
        GOTO MYLABEL;
    END IF;
    
    DBMS_OUTPUT.PUT_LINE(V_SUM);
    DBMS_OUTPUT.PUT_LINE(V_VAR);
END;
/

DECLARE
    DAN INT := 2;
    NUM INT := 1;
BEGIN
    LOOP
        DBMS_OUTPUT.PUT_LINE(DAN || '단');
        LOOP
            DBMS_OUTPUT.PUT_LINE(DAN || 'X' || NUM || '=' || DAN * NUM);
            NUM := NUM + 1;
            EXIT WHEN NUM > 9;
        END LOOP;
        NUM := 1;
        DAN := DAN + 1;
        EXIT WHEN DAN > 9;
    END LOOP;
END;
/
--DECLARE       --옵션
--i NUMBER;
BEGIN
    --i : 자동선언 정수형 변수
    FOR i IN 1..10 LOOP
        DBMS_OUTPUT.PUT_LINE('i=' || i);
    END LOOP;
END;
/

--1씩 감소
BEGIN
    FOR i IN REVERSE 1..10 LOOP
        DBMS_OUTPUT.PUT_LINE('i=' || i);
    END LOOP;
END;
/

--FOR문을 사용하여 구구단 출력하기
BEGIN
    FOR DAN IN 2..9 LOOP
        DBMS_OUTPUT.PUT_LINE(DAN || '단');
        
        FOR NUM IN 1..9 LOOP
            DBMS_OUTPUT.PUT_LINE(DAN || 'X' || NUM || '=' || DAN * NUM);
        END LOOP;
    END LOOP;
END;
/

--미리 정의된 예외인 경우
DECLARE
    V_NAME VARCHAR2(20);
BEGIN
    SELECT LPROD_NM + 0 INTO V_NAME
    FROM LPROD
    WHERE LPROD_GU = 'P101';
    DBMS_OUTPUT.PUT_LINE('분류명=' || V_NAME);
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('데이터가 없습니다, 오류번호? ' || SQLERRM);
        WHEN TOO_MANY_ROWS THEN
            DBMS_OUTPUT.PUT_LINE('행의 수가 너무 많습니다, 오류번호? ' || SQLERRM);
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('문자에 숫자를 더할 수 없습니다, 오류번호? ' || SQLERRM);
END;
/

--ID가 z001인 회원의 이름과 직업을 구하기
--단, 해당 정보가 없을 경우 예외처리 하시오
DECLARE
    V_NAME VARCHAR2(30);
    V_JOB VARCHAR2(30);
BEGIN
SELECT MEM_NAME, MEM_JOB INTO V_NAME, V_JOB
FROM MEMBER
WHERE MEM_ID = 'z001';
    DBMS_OUTPUT.PUT_LINE(V_NAME || ', ' || V_JOB);
    
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('데이터가 없습니다');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('기타 오류발생');
END;
/

--정의되지 않은 예외인 경우
DECLARE
    --예외처리형의 EXP1 변수를 선언
    EXP1 EXCEPTION;
    --PRAGMA : 컴파일때만 실행 BUT, 실행시에는 실행안됨
    --EXCEPTION_INIT : 예외처리형 EXP1변수와 오류코드번호를 매칭
    PRAGMA EXCEPTION_INIT(EXP1,-2292);
BEGIN
    DELETE FROM LPROD
    WHERE LPROD_GU = 'P101';
    
    EXCEPTION
        WHEN EXP1 THEN
            --SQLERRM : SQL ERROR MESSAGE
            DBMS_OUTPUT.PUT_LINE('삭제 불가 :' || SQLERRM);
END;
/

--정의되지 않은 예외 문제
--MEMBER테이블에 a001회원의 정보를 삭제. MEMBER테이블의 MEM_ID를 참조하고 있는
--CART테이블의 CART_MEMBER(FK)로 인해 MEMBER테이블 데이터가 삭제되지 않는데
--예외로 처리하기

DECLARE
    EX_CRF EXCEPTION;
    --예외형 변수선언
    PRAGMA EXCEPTION_INIT(EX_CRF, -2292);
    --컴파일할 때만 실행할 문장. EXCEPTION_INIT함수로 EX_CRF와 2292가 연결된다
BEGIN
    DELETE FROM MEMBER
    WHERE MEM_ID = 'a001';
    
    EXCEPTION
        WHEN EX_CRF THEN
            DBMS_OUTPUT.PUT_LINE('삭제불가');
END;
/
--
ACCEPT p_lgu PROMPT '등록하려는 분류코드 입력:'
DECLARE
    exp_lprod_gu EXCEPTION;
    v_lgu VARCHAR2(10) := UPPER('&p_lgu');
BEGIN
    IF v_lgu IN('P101','P102','P201','P202') THEN
        RAISE exp_lprod_gu;
    END IF;
    DBMS_OUTPUT.PUT_LINE(v_lgu||'는 등록 가능');
EXCEPTION
    WHEN exp_lprod_gu THEN
        DBMS_OUTPUT.PUT_LINE(v_lgu||'는 이미 등록된 코드입니다.');
END;
/

--사용자 정의 예외
--MEMBER테이블에 회원ID b001을 추가하려고 할 때 사용자 정의 예외를 발생시키기
--회원ID 체크는 a001, b001, c001로 하기
ACCEPT P_ID PROMPT '회원ID 입력:'
DECLARE
    V_ID VARCHAR2(30) := LOWER('&P_ID');
    EX_IDCHK EXCEPTION;
BEGIN
    DBMS_OUTPUT.PUT_LINE('V_ID : ' || V_ID);
    
    IF V_ID IN('a001','b001','c001') THEN
        RAISE EX_IDCHK;
    END IF;
    
    EXCEPTION
        WHEN EX_IDCHK THEN
            DBMS_OUTPUT.PUT_LINE('등록불가');
END;
/

--커서
DECLARE
    V_ID VARCHAR2(20);
    V_QTY NUMBER(7);
    CURSOR UpRemain_cur IS
        SELECT buy_prod, SUM(buy_qty)  FROM buyprod
        WHERE  EXTRACT(YEAR FROM  buy_date) = 2005
        GROUP BY buy_prod ORDER BY buy_prod ASC;
BEGIN
    OPEN UpRemain_cur;
    
    FETCH UpRemain_cur INTO V_ID,V_QTY;
    WHILE UpRemain_cur%FOUND LOOP
        DBMS_OUTPUT.PUT_LINE(V_ID || ', ' || V_QTY);
        FETCH UpRemain_cur INTO V_ID,V_QTY;
    END LOOP;
    
    CLOSE UpRemain_cur;
END;
/
--페치 따지고(DATA가 있는가? 없는가?) 출력한다

--회원의 마일리지 현황을 출력하기. 단, 직업이 주부인 회원만 출력
--MEM_NAME, MEM_MILEAGE     정렬 MEM_NAME 오름차순
BEGIN
    FOR CUR_ARR IN (SELECT MEM_NAME, MEM_MILEAGE FROM MEMBER
        WHERE MEM_JOB = '주부'
        ORDER BY MEM_NAME ASC) LOOP
        DBMS_OUTPUT.PUT_LINE(CUR_ARR.MEM_NAME ||
                     ',' || CUR_ARR.MEM_MILEAGE);
    END LOOP;
END;
/
--WHILE, LOOP, FOR 중 FOR문을 쓰는 것이 가장 간단한 방법

CREATE TABLE PROCTEST(
    PROC_SEQ NUMBER,
    PROC_CONTENT VARCHAR2(30),
    CONSTRAINT PK_PROCTEST PRIMARY KEY(PROC_SEQ)
);

CREATE SEQUENCE SEQPROC1
START WITH 1
INCREMENT BY 1;
/
-- 프로시저
CREATE OR REPLACE PROCEDURE PROC_TEST1
IS
BEGIN
    INSERT INTO PROCTEST(PROC_SEQ, PROC_CONTENT)
    VALUES(SEQPROC1.NEXTVAL,'개똥이'); --NEXTVAL은 시퀀스
    
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('예외 발생 : ' || SQLERRM);
END;
/

/
SET SERVEROUTPUT ON
/

/
EXECUTE PROC_TEST1;
-- 긴 SQL문이 아닌 이름만 넣으면 호출되고 안의 구문이 실행된다
/

SELECT * FROM PROCTEST;

ROLLBACK;
--실행했던 것이 취소되지만, 부여되었던 시퀀스 번호는 사라지지 않는다

--프로시저 PROC_TEST2를 생성. 실행하면 PROCTEST테이블의 마지막 데이터를 삭제,
--삭제할 데이터가 없을 경우 "삭제할 데이터가 없습니다"라는 에러처리
CREATE OR REPLACE PROCEDURE PROC_TEST2
IS
    CNT INT := 0;
    NDF EXCEPTION;
BEGIN
    --PROCTEST테이블의 로우개수를 구해서 0이면 RAISE EXCEPTION
    SELECT COUNT(*) INTO CNT FROM PROCTEST;
    
    IF CNT < 1 THEN
        RAISE NDF;
    END IF;
    
    DELETE FROM PROCTEST
    WHERE   PROC_SEQ = (SELECT MAX(PROC_SEQ) FROM PROCTEST);
    -- 대상의 마지막 값 찾기
    
    
    EXCEPTION
        WHEN NDF THEN
            DBMS_OUTPUT.PUT_LINE('삭제할데이터없음');
END;
/
EXECUTE PROC_TEST2;
/
SELECT * FROM PROCTEST;

--회원ID를 매개변수(PARAMETER)로 하여 해당 회원의 마일리지를 100점 추가하는
--프로시저를 생성하기(PROC_TEST3)
CREATE OR REPLACE PROCEDURE PROC_TEST3(P_ID IN VARCHAR2,P_MIL IN NUMBER)
--P_ID는 BIND변수. 매개변수를 받아준다
IS
BEGIN
    UPDATE MEMBER
    SET MEM_MILEAGE = MEM_MILEAGE + P_MIL
    WHERE MEM_ID = P_ID;
END;
/
EXECUTE PROC_TEST3('b001',200);
/
SELECT MEM_ID, MEM_MILEAGE FROM MEMBER
WHERE MEM_ID = 'b001';

--PL/SQL 변수의 종류
--1. SCALAR변수       A NUMBER(7) := 3;
--2. REFERENCE변수    A MEMBER.MEM_ID%TYPE := 'a001';
--3. BIND변수

--회원아이디(CART_MEMBER) 및 구매수량을 입력받아 구매수량(CART_QTY)을
--업데이트 하는 프로시저(USP_CART_QTYUP)를 생성하기.
--EXECUTE를 통해 신용환(c001)회원의 수량값을 10씩 추가하여 5회에 걸쳐 50 올리기
CREATE OR REPLACE PROCEDURE USP_CART_QTYUP(P_ID IN VARCHAR2, P_QTY IN NUMBER)
IS
BEGIN
    UPDATE CART
    SET CART_QTY = CART_QTY + P_QTY
    WHERE CART_MEMBER = P_ID;
END;
/
EXECUTE USP_CART_QTYUP('c001',10);
/
SELECT CART_MEMBER, CART_QTY FROM CART
WHERE CART_MEMBER = 'c001';

--회원아이디를 입력받아 이름과 취미를 OUT매개변수로 처리
CREATE OR REPLACE PROCEDURE USP_MEMBERID
    ( P_MEM_ID IN MEMBER.MEM_ID%TYPE,
      P_MEM_NAME OUT MEMBER.MEM_NAME%TYPE,
      P_MEM_LIKE OUT MEMBER.MEM_LIKE%TYPE )
IS
BEGIN
    SELECT MEM_NAME, MEM_LIKE
            INTO P_MEM_NAME, P_MEM_LIKE
        FROM MEMBER
    WHERE MEM_ID = P_MEM_ID;
END;
/
VAR P_ID VARCHAR2(30)
VAR P_LIKE VARCHAR2(30)
EXECUTE USP_MEMBERID('b001',:P_ID,:P_LIKE);
PRINT P_ID;
PRINT P_LIKE;
/
--상품코드와 월을 입력하면 해당월에 대한 해당 상품의 입고, 출고를 처리해 화면에 출력하시오.
--프로시저명 USP_PROD_INFO 월 입력형식은 'YYYYMM'이라 가정, 입고/출고는 OUT매개변수로 처리
--컬럼구성: PROD_ID, EXTRACT(MONTH FROM BUY_DATE), SUM(BUY_QTY), SUM(CART_QTY)
--ALIAS: 상품코드, 월, 입고수량합계, 출고수량합계
CREATE OR REPLACE PROCEDURE USP_PROD_INFO
    ( V_ID IN VARCHAR2, V_MON IN VARCHAR2, 
      V_BQTY OUT NUMBER, V_CQTY OUT NUMBER)

IS
 V_PID VARCHAR2(30);
 V_BMON NUMBER(10);
BEGIN
    SELECT P.PROD_ID, EXTRACT(MONTH FROM BP.BUY_DATE), SUM(BP.BUY_QTY), SUM(C.CART_QTY)
        INTO V_PID,    V_BMON,      V_BQTY,     V_CQTY
    FROM PROD P, BUYPROD BP, CART C
    WHERE P.PROD_ID = C.CART_PROD
    AND P.PROD_ID = BP.BUY_PROD
    AND BP.BUY_DATE LIKE '05/%'
    AND P.PROD_ID = 'P101000002'
    AND EXTRACT(MONTH FROM BP.BUY_DATE) = V_MON
    GROUP BY P.PROD_ID, EXTRACT(MONTH FROM BP.BUY_DATE)
    ORDER BY 1,2;
END;
/

--사용자 함수
CREATE OR REPLACE FUNCTION TEST_FUNC(P_NAME IN VARCHAR2)
RETURN VARCHAR2
IS
V_NAME VARCHAR2(100) := '';
BEGIN
    V_NAME := 'MY NAME IS' || P_NAME;
    RETURN V_NAME;
END;
/
SELECT TEST_FUNC(' RPOD KIM') FROM DUAL;
/

--회원 아이디를 받으면 해당 이름을 리턴하는 함수 만들기
CREATE OR REPLACE FUNCTION fn_memName(p_mem_id IN VARCHAR2)
RETURN VARCHAR2
IS
    r_name VARCHAR2(30);
BEGIN
   SELECT MEM_NAME INTO r_name
   FROM MEMBER
   WHERE MEM_ID = p_mem_id;
   
   RETURN r_name;
END;
/
--실행 테스트
SELECT CART_NO, CART_PROD, CART_MEMBER, fn_memName(CART_MEMBER)
FROM CART
WHERE CART_NO='2005040100001';

--function 입력, 연산, 출력이 있는 것. 입출력은 없을 수도 있다
--사용자가 정의해서 사용하는 함수 -> 사용자 정의 함수
CREATE OR REPLACE FUNCTION fn_prodAvgQty
    (p_year     IN NUMBER DEFAULT (EXTRACT(YEAR FROM SYSDATE)),
     p_prod_id  IN VARCHAR2)
     --p_year는 인바인드변수
RETURN NUMBER
IS
    r_qty NUMBER(10);
    v_year VARCHAR2(5) := TO_CHAR(p_year) || '%';
BEGIN
    SELECT NVL(AVG(cart_qty),0) INTO r_qty FROM cart
        WHERE cart_prod = p_prod_id AND cart_no LIKE v_year;
        --LIKE와 함께 쓰인 %는 와일드카드
        --PL/SQL에서 SELECT절이 쓰이면 INTO는 필수
    RETURN r_qty;
    
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('예외 발생:' || SQLERRM);
            RETURN 0;
END;
/

SELECT prod_id, prod_name,
    fn_prodAvgQty(2004,prod_id) "2004년 평균 판매횟수",
    fn_prodAvgQty(2005,prod_id) "2005년 평균 판매횟수"
FROM PROD;
/




SELECT PROD_ID
     , PROD_NAME
     , PROD_LGU
     , LPROD_NM
FROM PROD, LPROD
WHERE PROD_LGU = LPROD_GU;

SELECT PROD_ID
     , PROD_NAME
     , PROD_LGU
     , (SELECT LPROD_NM FROM LPROD WHERE LPROD_GU = PROD_LGU)
FROM PROD;
--아래 서브쿼리 방식이 더 빠르게 처리할 수 있다

--함수 문제
--다음과 같이 출력하시오
--상품코드, 상품명, 대분류코드, 대분류명
--함수를 사용. 함수명은 FN_PRODNM

SELECT PROD_ID
     , PROD_NAME
     , PROD_LGU
     , FN_PRODNM(PROD_LGU)
FROM PROD;
/
CREATE OR REPLACE FUNCTION FN_PRODNM(P_LGU IN VARCHAR2)
RETURN VARCHAR2
IS
    V_NM VARCHAR2(60);
BEGIN
    SELECT LPROD_NM INTO V_NM
    FROM LPROD WHERE LPROD_GU = P_LGU;
    RETURN V_NM;
END;
/
--셋 중 함수가 가장 빠르다

SELECT CART_NO
     , CART_PROD
     , CART_MEMBER
     , fn_memName(CART_MEMBER)
FROM CART
WHERE CART_NO = '2005040100001';
/
CREATE OR REPLACE FUNCTION fm_memName(P_ID IN VARCHAR2)
RETURN VARCHAR2
IS
    V_NAME VARCHAR2(60);
BEGIN
    SELECT MEM_NAME INTO V_NAME
    FROM MEMBER
    WHERE MEM_ID = P_ID;
    
    RETURN V_NAME;
END;
/

------------------------트리거 연습
CREATE OR REPLACE TRIGGER TG_LPROD_IN
AFTER INSERT
ON LPROD
BEGIN
    --LPROD 테이블에 데이터가 INSERT(이벤트)된 후(타이밍)에
    --다음과 같은 문자열을 콘솔에 출력함
    DBMS_OUTPUT.PUT_LINE('상품분류가 추가되었습니다');
END;
/


--직원테이블
CREATE TABLE EMP01(
    EMPNO NUMBER(4) PRIMARY KEY,
    EMPNAME VARCHAR2(45),
    EMPJOB VARCHAR2(60)
);
--급여테이블
CREATE TABLE SAL01(
    SALNO NUMBER(4) PRIMARY KEY,
    SAL NUMBER(7,2),
    EMPNO NUMBER(4) REFERENCES EMP01(EMPNO)
);
--시퀀스 생성
CREATE SEQUENCE SAL01_SALNO_SEQ
INCREMENT BY 1
START WITH 1;

--트리거 생성1
CREATE OR REPLACE TRIGGER TRG_02
--EMP01테이블에 데이터가 INSERT(이벤트)된 후(AFTER 타이밍)에
AFTER INSERT
    ON EMP01
--행레벨트리거 -> 여러건 처리가능
FOR EACH ROW
BEGIN
    INSERT INTO SAL01 VALUES(SAL01_SALNO_SEQ.NEXTVAL, 100, :NEW.EMPNO);
END;
/

INSERT INTO EMP01 VALUES(1,'개똥이','프로그래머');
INSERT INTO EMP01 VALUES(2,'박명수','프로그래머');
INSERT INTO EMP01 VALUES(3,'윤종신','프로그래머');
INSERT INTO EMP01 VALUES(4,'하하','프로그래머');
INSERT INTO EMP01 VALUES(5,'메뚜기','프로그래머');

/
SELECT * FROM EMP01;
SELECT * FROM SAL01;
/

DELETE FROM EMP01 WHERE EMPNO=2;
--행레벨트리거연습
/
CREATE OR REPLACE TRIGGER TRG_02
AFTER UPDATE
    ON EMP01
--FOR EACH ROW 있으면 행레벨트리거이고, 없으면 문장레벨트리거이다.
FOR EACH ROW
BEGIN
    
    DELETE FROM SAL01 WHERE EMPNO = :OLD.EMPNO;
END;
/

CREATE TABLE PROD2(
    PROD_CODE VARCHAR2(20) PRIMARY KEY,
    PROD_NAME VARCHAR2(60),
    PROD_COM VARCHAR2(30),
    PROD_SALE NUMBER(10),
    PROD_QTY NUMBER(5)
);

CREATE TABLE BUYPROD2(
    BUY_NUMBER VARCHAR2(20) PRIMARY KEY,
    BUY_CODE VARCHAR2(20) REFERENCES PROD2(PROD_CODE),
    BUY_DATE DATE,
    BUY_QTY NUMBER(5),
    BUY_EACHCOST NUMBER(10),
    BUY_COST NUMBER(10)
);

ALTER TABLE BUYPROD2
ADD(CONSTRAINT FR_BUY_CODE FOREIGN KEY(BUY_CODE) REFERENCES PROD2(PROD_CODE));

SELECT * FROM PROD2;
SELECT * FROM BUYPROD2;

INSERT INTO PROD2
    VALUES('A00001','세탁기','LG','500,0);
INSERT INTO PROD2
    VALUES('A00002','컴퓨터','LG','700,0);
INSERT INTO PROD2
    VALUES('A00003','냉장고','삼성','500,0);

CREATE OR REPLACE TRIGGER TRG_04
AFTER INSERT
ON 
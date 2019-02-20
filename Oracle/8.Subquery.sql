--------------------------------------------------------------
--책 78P PPT 280P 서브쿼리(SUBQUERY)
--서브쿼리는 항상 괄호로 묶는다
--SELECT 절의 서브쿼리: SCALAR 서브쿼리

--입고일자(BUY_DATE) 상품코드(BUY_PROD), 상품명, 매입수량(BUY_QTY), 매입단가(BUY_COST)
SELECT BUY_DATE 입고일자
     , BUY_PROD 상품코드
--     , (SELECT PROD_NAME FROM PROD WHERE PROD_ID = 'P202000001') 상품명 -- 고정되어 있기 때문에 상품명이 전부 '남성 봄 셔츠 1'로 출력됨
     , (SELECT PROD_NAME FROM PROD WHERE PROD_ID = BUY_PROD) 상품명 -- 이렇게 종속관계를 WHERE절에 입력하면 잘 출력됨
     , BUY_QTY 매입수량
     , BUY_COST 매입단가
FROM BUYPROD;
-- 여러 테이블간 JOIN은 기본키 외래키를 하나하나 따져야 한다. 실행 시간이 길다
-- 서브쿼리는 같은 일을 빠르게 처리 가능

SELECT PROD_NAME
FROM PROD
WHERE PROD_ID = 'P202000001';

--SCALAR서브쿼리: 거래처코드(BUYER_ID), 거래처명(BUYER_NAME), 상품분류코드(BUYER_LGU), 상품분류명
SELECT BUYER_ID         거래처코드
     , BUYER_NAME       거래처명
     , BUYER_LGU        상품분류코드
     , (SELECT LPROD_NM FROM LPROD WHERE LPROD_GU = BUYER_LGU) 상품분류명
FROM BUYER;

--NESTED 서브쿼리: WHERE절에 사용된 서브쿼리
--상품분류가 컴퓨터제품인 상품의 리스트를 출력하기
--상품코드, 상품명, 상품분류코드
SELECT PROD_ID      상품코드
     , PROD_NAME    상품명
     , PROD_LGU     상품분류코드
FROM PROD
--WHERE PROD_LGU='컴퓨터제품'; -- 출력X PROD_LGU는 코드, '컴퓨터제품'은 명. 코드와 명은 같을 수 없다
WHERE PROD_LGU=(SELECT LPROD_GU FROM LPROD WHERE LPROD_NM = '컴퓨터제품'); -- 서브쿼리를 이용해 해결

SELECT LPROD_GU FROM LPROD WHERE LPROD_NM = '컴퓨터제품'; -- 이렇게 코드를 만들어서 위에 대입

--상품분류가 컴퓨터제품인 거래처 리스트를 출력하기
--거래처코드, 거래처명, 상품분류코드
SELECT BUYER_ID     거래처코드
     , BUYER_NAME   거래처명
     , BUYER_LGU    상품분류코드
FROM BUYER
WHERE BUYER_LGU=(SELECT LPROD_GU FROM LPROD WHERE LPROD_NM = '컴퓨터제품');
-- 컴퓨터제품이 코드로 바뀐다

--NESTED    상품분류가 전자제품인 상품매입 현황 리스트 출력
--입고일자, 상품코드, 매입수량, 매입단가
SELECT BUY_DATE             입고일자
     , BUY_PROD             상품코드
     , BUY_QTY              매입수량
     , BUY_COST             매입단가
FROM BUYPROD
WHERE SUBSTR(BUY_PROD,1,4)=(SELECT LPROD_GU FROM LPROD WHERE LPROD_NM = '전자제품');
-- 전자제품이 LPROD_GU를 통해 코드로 변환된다

--장바구니테이블에서 정은실 회원의 구매 현황을 출력
--주문번호, 상품코드, 회원ID, 수량
SELECT CART_NO      주문번호
     , CART_PROD    상품코드
     , CART_MEMBER  회원ID
     , CART_QTY     수량
FROM CART
WHERE CART_MEMBER=(SELECT MEM_ID FROM MEMBER WHERE MEM_NAME = '정은실');

--피리어스 업체로부터 들여온 상품의 리스트 출력(상품ID, 상품명, 업체코드)    서브쿼리 사용
SELECT PROD_ID      상품ID
     , PROD_NAME    상품명
     , PROD_BUYER   업체코드
FROM PROD
WHERE PROD_BUYER=(SELECT BUYER_ID FROM BUYER WHERE BUYER_NAME = '피리어스');

--79P 상품TABLE에서 판매가가 상품평균판매가보다 큰 상품 검색
--상품명, 판매가, 평균판매가
SELECT PROD_NAME    상품명
     , PROD_SALE    판매가
     , (SELECT AVG(PROD_SALE) FROM PROD) 평균판매가
FROM PROD
WHERE PROD_SALE > (SELECT AVG(PROD_SALE) FROM PROD);

--FROM절에 쓰인 서브쿼리: INLINE VIEW

--79P 회원TABLE에서 마일리지가 평균마일리지보다 큰 회원 검색
--회원명, 마일리지, 평균마일리지
SELECT MEMBER.MEM_NAME         회원명
     , MEMBER.MEM_MILEAGE      마일리지
     , A.AVG_MIL    평균마일리지
FROM (SELECT * FROM MEMBER) MEMBER
    ,(SELECT  ROUND(AVG(MEM_MILEAGE),2) AVG_MIL FROM MEMBER) A -- 이것도 테이블로 간주. 컬럼명으로는 쓸 수 없다. 예약어(ROUND, AVG등)가 있음
WHERE MEMBER.MEM_MILEAGE > A.AVG_MIL;

--77P 상품TABLE에서 판매가가 상품평균판매가보다 큰 상품 검색(상품명, 판매가, 평균판매가) - INLINE VIEW
SELECT PROD.PROD_NAME       상품명
     , PROD.PROD_SALE       판매가
     , (SELECT AVG(PROD_SALE) FROM PROD) 평균판매가
FROM   (SELECT * FROM PROD) PROD
     , (SELECT ROUND(AVG(PROD_SALE),2) AVG_SALE FROM PROD) P
WHERE PROD.PROD_SALE > P.AVG_SALE;

--회원TABLE에서 나이가 평균나이보다 많은 회원 검색
--회원ID, 성명, 주민번호앞자리, 나이, 평균나이
SELECT MEM_ID
     , MEM_NAME
     , MEM_REGNO1
     , (SELECT ROUND((SYSDATE - TO_DATE(MEM_BIR))/365),0 FROM MEMBER) A
     , (SELECT ROUND(AVG(SYSDATE - TO_DATE(MEM_BIR))/365),0 FROM MEMBER) B
FROM MEMBER
WHERE ;

SELECT MEM_ID
     , MEM_NAME
     , MEM_REGNO1
     , (EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM MEM_BIR)) 나이
     
FROM MEMBER A, (SELECT ROUND(AVG(EXTRACT(YEAR FROM SYSDAYE)
                -EXTRACT(YEAR FROM MEM_BIR))) AVG_YEAR FROM MEMBER)B
WHERE (EXTRACT(YEAR FROM SYSDATE
                
                
                
     , (SELECT ROUND(AVG(SYSDATE - TO_DATE(MEM_BIR))/365),0 FROM MEMBER) B
FROM MEMBER
WHERE ;


--(SELECT * FROM MEMEBER) 집합
--(SELECT  ROUND(AVG(MEM_MILEAGE),2) AVG_MIL FROM MEMBER) A  집합  A

--------------------------------------------------------------------
-- PPT 293P CORRELATED 상관관계 서브쿼리
--장바구니table에서 회원별 최고의 구매수량을 가진 자료의 회원, 주문번호, 상품, 수량에 대해 검색하기

--회원ID와 MAX QTY가 없었다면?
SELECT CART_MEMBER
     , MAX(CART_QTY)
FROM CART
GROUP BY CART_MEMBER
ORDER BY 1 ASC;

--하지만 언제? 무슨 상품인지? 이걸 포함시키면 나머지도 전부 나오게 된다
--왜? 중복 없는 기본키가 GROUP BY절에 들어가버리니까
SELECT CART_MEMBER
     , CART_NO
     , CART_PROD
     , MAX(CART_QTY)
FROM CART
GROUP BY CART_MEMBER, CART_NO, CART_PROD
ORDER BY 1 ASC;

-- CORRELATED 상관관계 서브쿼리
SELECT A.CART_MEMBER        회원ID
     , A.CART_NO            주문번호
     , A.CART_PROD          상품
     , A.CART_QTY           수량
FROM CART A
WHERE A.CART_QTY = (            -- 소괄호 바깥은 A영역, 안쪽은 B영역
            SELECT MAX(B.CART_QTY)      -- 한정시킨 것 중 MAX찾기
            FROM    CART B
            WHERE B.CART_MEMBER = A.CART_MEMBER    -- 데이터 한정시키기(a001, b001,...)=>A.CART_MEMBER
    );

--입고테이블(BUYPROD)에서 "상품별" 최고 매입수량을 가진 자료의
--입고일자, 상품코드, 매입수량, 매입단가 검색
SELECT A.BUY_DATE     입고일자
     , A.BUY_PROD     상품코드
     , A.BUY_QTY      매입수량
     , A.BUY_COST     매입단가
FROM BUYPROD A -- A집합
WHERE A.BUY_QTY = (
        SELECT MAX(B.BUY_QTY)
        FROM   BUYPROD B -- B집합
        WHERE  B.BUY_PROD = A.BUY_PROD
    );
--찾는 것을 풀어보면? A.BUY_QTY = 23 AND A.BUY_PROD = 'P00000..'

ORDER BY 2,3 DESC; -- 이걸로 상품별 최고 매입수량을(뭘 가져와야 할지) 일단 눈으로 확인하기
-- 1. A집합의 상품코드가 B집합의 조건으로 들어간다
-- 2. 그 때의 MAX QTY를 찾아라(예: P10100001이면서 22인 것, P10100002이면서 23인 것,...)
-- 3. 찾은 값을 (1+2가)리턴


--장바구니TABLE에서 일자별 최고의 구매수량을 가진 자료의 회원
--주문번호, 상품, 수량에 대해 모두 검색(회원, 일자, 상품, 수량)
SELECT A.CART_MEMBER              회원
     , SUBSTR(A.CART_NO,1,8)      일자
     , A.CART_PROD                상품
     , A.CART_QTY                 수량
FROM CART A
WHERE A.CART_QTY = (
        SELECT MAX(B.CART_QTY)
        FROM CART B
        WHERE SUBSTR(B.CART_NO,1,8) = SUBSTR(A.CART_NO,1,8)
        --WHERE절에 쓰이는 건 보통 어떤 식으로 묶으라는 것
);

--80P 모든 거래처의 2005년도 거래처별 매입금액 합계를 조회
--매입금액: BUY_COST * BUY_QTY 얼마짜리 상품이 몇 개 들어왔는가?
--ALIAS: 거래처코드, 거래처명, 매입금액합계

SELECT B.BUYER_ID             거래처코드
     , B.BUYER_NAME           거래처명
     , SUM(BP.BUY_COST * BP.BUY_QTY)   매입금액합계
FROM   BUYER B, PROD P, BUYPROD BP
WHERE  B.BUYER_ID = P.PROD_BUYER
AND    P.PROD_ID = BP.BUY_PROD
AND    BP.BUY_DATE LIKE '05%'
GROUP BY B.BUYER_ID, B.BUYER_NAME
ORDER BY 1; -- 이 때  기존에는 ANSI표준으로 LEFT OUTER JOIN해서 처리했음(LIKE절이 포함되어 있어서)


SELECT TBL1.BUYER_ID        거래처코드
     , TBL1.BUYER_NAME      거래처명
     , TBL2.AMT             매입금액합계
FROM
(SELECT BUYER_ID
      , BUYER_NAME
FROM BUYER) TBL1,
(SELECT B.BUYER_ID
      , B.BUYER_NAME
      , SUM(BP.BUY_COST * BP.BUY_QTY) AMT
FROM   BUYER B, PROD P, BUYPROD BP
WHERE  B.BUYER_ID = P.PROD_BUYER
AND    P.PROD_ID = BP.BUY_PROD
AND    BP.BUY_DATE LIKE '05%'
GROUP BY B.BUYER_ID, B.BUYER_NAME) TBL2
WHERE TBL1.BUYER_ID = TBL2.BUYER_ID(+);
--INLINE VIEW로 풀어가는 방법
--쓰는 이유? 다른 건 그대로 놓고 TBL2안 영역만(괄호 안 영역만) 적당히 바꿔줄 수 있다. 확장성이 크다

--79P 모든 거래처의 2005년도 거래처별 매출금액합계 검색
--(거래처코드, 거래처명, 매출금액합계 거래처명)
--(CART테이블, 매출금액=PROD_SALE * CART_QTY)

SELECT TBL1.BUYER_ID
     , TBL1.BUYER_NAME
     , NVL(TBL2.QTY_SALE)
FROM 
(SELECT BUYER_ID, BUYER_NAME FROM BUYER) TBL1,

(SELECT B.BUYER_ID
     , B.BUYER_NAME
     , SUM(P.PROD_SALE * C.CART_QTY) QTY_SALE
FROM BUYER B, PROD P, CART C
WHERE B.BUYER_ID = P.PROD_BUYER
AND   P.PROD_ID = C.CART_PROD
AND   C.CART_NO LIKE '2005%'
GROUP BY B.BUYER_ID, B.BUYER_NAME) TBL2
WHERE TBL1.BUYER_ID = TBL2.BUYER_ID(+);

--LPROD테이블과 PROD테이블 조인
--LPROD 및 PROD는 INLINE VIEW로 처리
--ALIAS: PROD_ID, PROD_NAME, LPROD_NM

SELECT A.PROD_ID
     , A.PROD_NAME
     , B.LPROD_NM
FROM (SELECT * FROM PROD) A,
     (SELECT * FROM LPROD) B
WHERE LPROD_GU = PROD_LGU;

--책 82P 다중행 서브쿼리
--비교연산자를 쓸 수 없다(= != < > 등)

--회원TABLE에서 회원주소 지역이 거래처주소 지역 중 하나이면 검색하시오(회원명, 지역)

SELECT MEM_NAME 회원명
     , SUBSTR(MEM_ADD1,1,2) 지역
FROM MEMBER
WHERE SUBSTR(MEM_ADD1,1,2)
        IN(SELECT DISTINCT SUBSTR(BUYER_ADD1,1,2)
            FROM BUYER); -- 회원 중 충남에 사는 사람은 빼고 출력
--IN은 포함관계를 물을 때 사용

--상품분류TABLE에서 분류코드가 상품테이블의 대분류에 포함된 것만 선택 검색
--상품분류코드, 순번, 상품분류명

SELECT LPROD_GU     상품분류코드
     , LPROD_ID     순번
     , LPROD_NM     상품분류명
FROM LPROD
WHERE LPROD_GU IN(SELECT DISTINCT PROD_LGU FROM PROD);

--PPT 302P ANY(OR연산), ALL(AND연산)
--직업이...
SELECT MEM_NAME     회원명
     , MEM_JOB      직업
     , MEM_MILEAGE  마일리지
FROM MEMBER
WHERE MEM_MILEAGE > ANY(
SELECT MEM_MILEAGE FROM MEMBER WHERE MEM_JOB = '공무원'); -- WHERE절에 쓰였으므로 NESTED 서브쿼리 다시 기억하기

--a001회원의 구입수량을 검색하여 최소한 a001회원보다는 구입수량이(AND개념) 큰 주문내역 출력
--단, a001회원은 제외하고 검색(주문번호, 상품코드, "회원명", 구입수량)
SELECT CART_NO          주문번호
     , CART_PROD        상품코드
     , (SELECT MEM_NAME FROM MEMBER WHERE CART_MEMBER = MEM_ID)      회원명 -- 일단 회원과 관련된 컬럼을 쓰고, 나중에 조건 부여
     , CART_QTY         상품수량
FROM CART
WHERE CART_MEMBER <> 'a001' -- a001회원은 제외하고
AND CART_QTY > ALL ( -- 결국은 16보다 큰 값을 가져옴
    SELECT CART_QTY FROM CART
    WHERE CART_MEMBER = 'a001');

--책 84P 집합
/*
UNION(합집합): 중복부분(교집합)은 1회 출력되고 결과물은 자동정렬.
UNION ALL(합집합): 중복부분(교집합)도 모두 출력되고 결과물은 자동정렬되지 않음.
INTERSECT(교집합): JOIN할 테이블간 중복 부분만 출력.
MINUS(차집합): JOIN할 테이블 중 A - B하고(같은 부분을 빼고) 남는 부분만 출력.

집합은 밑으로 붙는 것(JOIN은 옆으로 붙는 것). 그러려면
1. 열의 개수가 같아야 한다 2. 매핑되는 열의 자료형이 동일해야 한다
조건을 만족하면 집합 처리가 가능하다
*/

SELECT MEM_NAME
     , MEM_JOB
     , MEM_MILEAGE
FROM MEMBER
WHERE MEMBER.MEM_MILEAGE > 4000
MINUS
SELECT MEM_NAME
     , MEM_JOB
     , MEM_MILEAGE
FROM MEMBER
WHERE MEM_JOB = '자영업';

SELECT 'LPROD'      TABLE_ID
     , '상품분류테이블'    TABLE_NAME
     , COUNT(*)     DATA_COUNT
FROM LPROD
UNION
SELECT 'LPROD'      TABLE_ID
     , '상품테이블'  TABLE_NAME
     , COUNT(*)     DATA_COUNT
FROM PROD
UNION
SELECT 'MEMBER'     TABLE_ID
     , '회원테이블'  TABLE_NAME
     , COUNT(*)     DATA_COUNT
FROM MEMBER;

--책 86P EXISTS(교집합 연산자. 이것도 다중행일때 쓰이는 연산자. IN, ALL, ANY와 함께)
SELECT MEM_ID -- 이렇게 열 갯수가 달라지면? UNION할 수 없다.
     , MEM_NAME
     , MEM_JOB
     , MEM_MILEAGE
FROM MEMBER
WHERE MEM_MILEAGE > 4000;

SELECT MEM_NAME
     , MEM_JOB
     , MEM_MILEAGE
FROM MEMBER
WHERE MEM_JOB = '자영업';

--가능하게 하려면?
--EXIST심경
--집합과집합사이에AND EXISTS를쓰고 B집합을괄호로묶은후
--B집합에연결고리를작성(JOIN조건을 쓰면)하면되느니라             --JOIN조건? 자료형, 크기가 같고 같은 데이터가 들어 있어야(복습)
SELECT A.MEM_ID
     , A.MEM_NAME
     , A.MEM_JOB
     , A.MEM_MILEAGE
FROM MEMBER A
WHERE A.MEM_MILEAGE > 4000
AND EXISTS
    (SELECT B.MEM_NAME
         , B.MEM_JOB
         , B.MEM_MILEAGE
    FROM MEMBER B
    WHERE B.MEM_JOB = '자영업'
    AND A.MEM_NAME = B.MEM_NAME --****중요!**** B집합에 JOIN조건을 쓴다
    );

--84P EXIST를 이용하여 INTERSECT구현
--2005년 4월에 판매된 상품 & 6월에 판매된 상품 찾는 문제
--P302### 중에서 P302000012 여성 향수가 앞쪽 테이블에 없음

SELECT DISTINCT C.CART_PROD   판매상품
     , P.PROD_NAME            상품명
FROM CART C, PROD P
WHERE C.CART_PROD = P.PROD_ID
AND SUBSTR(C.CART_NO,1,8) BETWEEN '20050401' AND '20050430'
AND EXISTS
    (SELECT DISTINCT CART_PROD
    FROM CART D
    WHERE D.CART_PROD = C.CART_PROD
    AND D.CART_NO LIKE '200506%');

--85P 2005년도 구매금액 2천만 이상 우수고객으로 지정하여 검색
--회원ID, 회원명, '우수고객'(문자열 출력)
--구매금액 : SUM(CART.CART_QTY * PROD.PROD_SALE)

SELECT A.MEM_ID     회원ID
     , A.MEM_NAME   회원명
     , '우수고객'
FROM MEMBER A
WHERE EXISTS     -- A쪽에 WHERE가 있다면 AND EXISTS를 쓸 수 있지만 보안 위험이 있다(SQL INJECTION)
(SELECT B.MEM_ID
      , B.MEM_NAME
      , SUM(CART.CART_QTY * PROD.PROD_SALE)
FROM CART, PROD, MEMBER B
WHERE PROD.PROD_ID = CART.CART_PROD
AND B.MEM_ID = CART.CART_MEMBER
AND CART.CART_NO LIKE '2005%'
AND A.MEM_ID = B.MEM_ID             -- 연결고리
GROUP BY B.MEM_ID, B.MEM_NAME
HAVING SUM(CART.CART_QTY * PROD.PROD_SALE) > 20000000)
;
--아래처럼 MEMBER가 빠져도 작동한다.
--또한 GROUP BY 절도 뺄 수 있다
SELECT A.MEM_ID     회원ID
     , A.MEM_NAME   회원명
     , '우수고객'
FROM MEMBER A
WHERE EXISTS     -- A쪽에 WHERE가 있다면 AND EXISTS를 쓸 수 있지만 보안 위험이 있다(SQL INJECTION)
(SELECT SUM(CART_QTY * PROD_SALE)
FROM CART, PROD
WHERE PROD_ID = CART_PROD
AND CART_NO LIKE '2005%'
AND MEM_ID = CART_MEMBER             -- 연결고리 중요!
HAVING SUM(CART_QTY * PROD_SALE) > 20000000)
;

--2005년도 매입금액 천만원 이상 우수거래처로 지정하여 검색하기
--거래처코드, 거래처명, '우수거래처'
--(구매금액 : SUM(BUYPROD.BUY_QTY * BUYPROD.BUY_COST)
SELECT A.BUYER_ID
     , A.BUYER_NAME
     , '우수거래처'
FROM BUYER A
WHERE EXISTS
    (SELECT B.BUYER_ID
         , B.BUYER_NAME
         , SUM(BP.BUY_QTY * BP.BUY_COST)
    FROM BUYER B, PROD P, BUYPROD BP
    WHERE B.BUYER_ID = P.PROD_BUYER
    AND P.PROD_ID = BP.BUY_PROD
    AND BP.BUY_DATE LIKE '05/%'
    AND A.BUYER_ID = B.BUYER_ID
    GROUP BY B.BUYER_ID, B.BUYER_NAME
    HAVING SUM(BP.BUY_QTY * BP.BUY_COST) > 10000000);
    
--------------------------------------------------------------------
/*
88P 집계용 문법 - 잘 사용하지 않음
RANK() : 순위 출력 함수(점수가 같은 부분은 같은 순위.
다음 순위는 내부적으로 카운트되어 순위가 같은 부분만큼 밀린다=순번을 따라간다)
DENSE RANK() : 서열 출력 함수(점수가 같은 부분은 같은 순위.
다음 순위는 내부적으로 카운트되지 않아 다음번 숫자가 된다)
*/


SELECT RANK('c001')
WITHIN GROUP (ORDER BY CART_MEMBER) RANK
     , DENSE_RANK('c001')
WITHIN GROUP (ORDER BY CART_MEMBER) DENSE_RANK
FROM CART;

--분석용 문법

--장바구니(CART) 테이블에서 회원들의 회원ID와 구매수, 구매수순위를 출력
SELECT CART_MEMBER  회원ID
     , CART_QTY     구매수
     , RANK() OVER(ORDER BY CART_QTY DESC)          구매수순위
     , DENSE_RANK() OVER(ORDER BY CART_QTY DESC)    서열
FROM CART;
--중요한 것은 순위가 내부적으로 카운트되어 공동 순위 이후가 밀리는 것(서열은 그렇지 않다)

--CART테이블에서 아이디가 'a001'인 회원의 회원아이디, 상품코드, 구매수, 구매수순위를 출력
SELECT CART_MEMBER  회원아이디
     , CART_PROD    상품코드
     , CART_QTY     구매수
     , RANK() OVER(ORDER BY CART_QTY DESC)  구매수순위
     -- RANK() 소괄호 안에 넣는 것은 집계용 문법. 안에 넣은 것 하나만 출력할 때 사용
     , DENSE_RANK() OVER(ORDER BY CART_QTY DESC) 서열
FROM CART
WHERE CART_MEMBER='a001';
/*
책90P 행순서 함수(행번호)
ROWNUM 레코드에 대한(행 자체에 대한) 일련번호
현재 출력된 것 결과값 사이의 일련번호를 보여주는 것
정렬 옵션이 적용되기 전에 출력됨
제대로 결과값을 조회하려면 INLINE VIEW(FROM절에 쓰인 서브쿼리)를 이용해
원하는 결과를 먼저 정해놓은 다음 ROWNUM을 적용

사용처: 게시판의 페이징 처리(이전 페이지, 다음 페이지)
*/
SELECT ROWNUM
     , LPROD_ID
     , LPROD_GU
     , LPROD_NM
FROM LPROD;

SELECT ROWNUM
     , L.*
FROM LPROD L
ORDER BY LPROD_NM ASC;

SELECT ROWNUM "RNUM1", T.*         -- 섞이지 않는다
FROM (SELECT ROWNUM "RNUM2"        -- 섞인다
     , L.*
FROM LPROD L
ORDER BY LPROD_NM ASC) T;

--ROWID : 테이블의 특정 레코드로 랜덤하게 접근하기 위한 논리적인 주소값.
--DB에서 고유한 값. DB안에서 절대 중복될 수 없다.
SELECT LPROD_GU
     , LPROD_NM
     , ROWID
FROM LPROD
ORDER BY 3;

--파일저장방법
SELECT MEM_ID
     , MEM_NAME
     , MEM_PATH
     , MEM_IMG
FROM MEMBER;

--파일저장방법 실습
--회원테이블에 회원의 사진 첨부파일을 저장하기
ALTER TABLE MEMBER
ADD(
    MEM_PATH VARCHAR2(1000),
    MEM_IMG  VARCHAR2(1000)
);

UPDATE MEMBER
SET     MEM_PATH = '\\Sem-pc\공유폴더\', MEM_IMG = '힘내요.png'
WHERE   MEM_ID = 'a001';

--RATIO_TO_REPORT 전체 대비 해당ROW의 값이 차지하는 비율
SELECT T1.VAL
     , RATIO_TO_REPORT(T1.VAL) OVER() * 100 || '%'
FROM
(
    SELECT 10 VAL FROM DUAL
    UNION ALL
    SELECT 20 VAL FROM DUAL
    UNION ALL
    SELECT 30 VAL FROM DUAL
    UNION ALL
    SELECT 40 VAL FROM DUAL
) T1;

--a001회원이 구입한 상품의 내역을 활용하여 구매개수(CART_QTY) 대비 해당 구매개수 값이
--차지하는 비율 구하기(회원ID, 상품코드, 구매수, 차지비율)
SELECT CART_MEMBER      회원ID
     , CART_PROD        상품코드
     , CART_QTY         구매수
     , RATIO_TO_REPORT(CART_QTY) OVER() * 100 || '%'    차지비율
FROM CART
WHERE CART_MEMBER = 'a001';

--92P ROLLUP    소계 구하기
--상품분류별, 거래처별 입고수와 입고가격의 합(상품분류, 거래처, 상품수, 입고가격합)
SELECT B.BUYER_LGU           상품분류
     , B.BUYER_ID            거래처
     , SUM(BP.BUY_QTY)       상품수
     , SUM(BP.BUY_COST)      입고가격합
FROM BUYER B, PROD P, BUYPROD BP
WHERE B.BUYER_ID = P.PROD_BUYER
AND P.PROD_ID = BP.BUY_PROD
GROUP BY ROLLUP(B.BUYER_LGU, B.BUYER_ID)
ORDER BY 1,2;

--ROLLUP을 UNION ALL로 처리
/*
GROUP BY ROLLUP(PROD_LGU, PROD_BUYER)
=>
GROUP BY PROD_LGU, PROD_BUYER
UNION ALL
GROUP BY PROD_LGU
UNION ALL
전체 집계
*/

SELECT B.BUYER_LGU           상품분류
     , B.BUYER_ID            거래처
     , SUM(BP.BUY_QTY)       상품수
     , SUM(BP.BUY_COST)      입고가격합
FROM BUYER B, PROD P, BUYPROD BP
WHERE B.BUYER_ID = P.PROD_BUYER
AND P.PROD_ID = BP.BUY_PROD
GROUP BY B.BUYER_LGU, B.BUYER_ID
UNION ALL
SELECT NULL                  상품분류
     , NULL                  거래처
     , SUM(BP.BUY_QTY)       상품수
     , SUM(BP.BUY_COST)      입고가격합
FROM BUYER B, PROD P, BUYPROD BP
WHERE B.BUYER_ID = P.PROD_BUYER
AND P.PROD_ID = BP.BUY_PROD
ORDER BY 1,2;


-- ROLLUP

SELECT E.DEPTNO
     , D.DNAME
     , E.JOB
     , COUNT(E.EMPNO)
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
GROUP BY ROLLUP((E.DEPTNO, D.DNAME), E.JOB);

--위를 UNION ALL로 바꿔보기
SELECT E.DEPTNO
     , D.DNAME
     , E.JOB
     , COUNT(E.EMPNO)
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
GROUP BY ROLLUP(E.DEPTNO, D.DNAME), E.JOB
UNION ALL
SELECT E.DEPTNO
     , D.DNAME
     , NULL
     , COUNT(E.EMPNO)
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
GROUP BY (E.DEPTNO, D.DNAME) -- 분류별 소계 영역. 불필요한 JOB을 없애고 NULL로 바꾼다
UNION ALL
SELECT NULL
     , NULL
     , NULL
     , COUNT(E.EMPNO)
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
-- 전체 합계 영역. GROUP BY가 사라지고 불필요한 이름을 NULL로 바꾼다
ORDER BY 1,2,3;

--책 93P CUBE
/*
중분류로 소계를 낼 때 사용. ROLLUP과 사용법은 동일.

CUBE정리
GROUP BY CUBE(A,B) => ?
GROUP BY A,B
UNION ALL
GROUP BY A
UNION ALL -- CUBE
GROUP BY B -- CUBE
UNION ALL -- 나머지는 ROLLUP
모든 집합 그룹 결과
*/
SELECT E.DEPTNO
     , D.DNAME
     , E.JOB
     , COUNT(E.EMPNO)
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO
GROUP BY CUBE((E.DEPTNO, D.DNAME), E.JOB);

SELECT PROD_LGU
     , PROD_BUYER
     , COUNT(*)
     , SUM(PROD_COST)
FROM PROD
GROUP BY CUBE(PROD_LGU, PROD_BUYER);

--UNION ALL로 바꿔보기
SELECT PROD_LGU
     , PROD_BUYER
     , COUNT(*)
     , SUM(PROD_COST)
FROM PROD
GROUP BY CUBE(PROD_LGU, PROD_BUYER)
UNION ALL
SELECT NULL
     , PROD_BUYER
     , COUNT(*)
     , SUM(PROD_COST)
FROM PROD
GROUP BY (PROD_LGU, PROD_BUYER)
UNION ALL
SELECT NULL
     , NULL
     , COUNT(*)
     , SUM(PROD_COST)
FROM PROD;
/*
94P GROUPING SET 함수
그루핑 조건이 여러개일 때 사용
GROUP BU GROUPING SET? (A,B,C)


*/
SELECT MEM_JOB
     , MEM_LIKE -- 결합연산자 ||로 묶는게 더 빠르다
     , COUNT(*)
FROM MEMBER
GROUP BY GROUPING SETS(MEM_JOB, MEM_LIKE); -- 직업 따로, 취미 따로. 따로따로 그루핑해서 세는 것

SELECT MEM_JOB
     , COUNT(*)
FROM MEMBER
GROUP BY MEM_JOB
UNION -- 위에서 MEM_JOB으로 한 번 묶고, 아래에서 MEM_LIKE로 다시 한 번 묶는다
SELECT MEM_LIKE
     , COUNT(*)
FROM MEMBER
GROUP BY MEM_LIKE;

--0219 PPT 317P             INSERT 심화
CREATE TABLE REMAIN
(
    REMAIN_YEAR     CHAR(4) NOT NULL,           -- 해당 년도
    REMAIN_PROD     VARCHAR2(10) NOT NULL,      -- 상품 코드
    REMAIN_J_00     NUMBER(5),                  -- 전년 재고
    REMAIN_I        NUMBER(5),                  -- 입고
    REMAIN_O        NUMBER(5),                  -- 출고
    REMAIN_J_99     NUMBER(5),                  -- 현재고
    REMAIN_DATE     DATE,                       -- 처리일자
    CONSTRAINT PK_REMAIN PRIMARY KEY(REMAIN_YEAR, REMAIN_PROD),
    CONSTRAINT FR_REMAIN_PROD FOREIGN KEY(REMAIN_PROD)
                              REFERENCES PROD(PROD_ID) ON DELETE CASCADE
);
--ON DELETE CASCADE는 자식 테이블에 쓴다
/*
참조 무결성을 지키는 방법들
NO ACTION(안한다) 안씀
RESTRICT 부모 테이블의 데이터 삭제 시 자식 테이블에서 참조하는 키가 있다면 삭제할 수 없게 막는다(이게 기본옵션)
CASCADE(연쇄) 부모 테이블의 데이터 삭제 시 자식 테이블에서 참조하는 키가 있다면 같이 삭제된다
DEFAULT 부모 테이블의 데이터 삭제 시 자식 테이블에서 참조하는 키가 있다면 지정한 DEFAULT값으로 바뀐다
NULLIFY 부모 테이블의 데이터 삭제 시 자식 테이블에서 참조하는 키가 있다면 NULL값으로 바뀐다
*/

--연습예제
CREATE TABLE TBL_PARENT(
        COL1 VARCHAR2(10) PRIMARY KEY,
        COL2 VARCHAR2(10)
);

INSERT INTO TBL_PARENT VALUES('P101','과일');
INSERT INTO TBL_PARENT VALUES('P102','채소');
INSERT INTO TBL_PARENT VALUES('P103','스낵');

CREATE TABLE TBL_CHILD(
        COL3 VARCHAR2(10) PRIMARY KEY,
        COL1 VARCHAR2(10) REFERENCES TBL_PARENT(COL1)
);--CREATE TABLE 시 CONSTRAINT 안 하고 이런 식으로 간단하게 처리할 수도 있다.

INSERT INTO TBL_CHILD VALUES(1,'P101');
INSERT INTO TBL_CHILD VALUES(2,'P101');
INSERT INTO TBL_CHILD VALUES(3,'P101');
INSERT INTO TBL_CHILD VALUES(4,'P102');
INSERT INTO TBL_CHILD VALUES(5,'P102');
INSERT INTO TBL_CHILD VALUES(6,'P103');

DELETE FROM TBL_PARENT
WHERE COL1 = 'P101'; -- 그냥 지울 수 없다. RESTRICT가 기본값으로 걸려 있기 때문에
--테이블 제약조건을 수정하면 삭제가능
SELECT * FROM TBL_CHILD;
-----------------------------------------
--모든 컬럼에 데이터를 넣을 때만 컬럼 리스트를 생략 가능하다.
INSERT INTO REMAIN
VALUES('2003','P101000001',20,10,12,18,'2004-01-01');
--하지만 다음과 같이 컬럼명을 명시하는 것을 권장한다
INSERT INTO REMAIN(REMAIN_YEAR,REMAIN_PROD,REMAIN_J_00,
                   REMAIN_I,REMAIN_O,REMAIN_J_99,
                   REMAIN_DATE)
VALUES('2003','P101000002',11,7,6,12,'2004-01-02');

SELECT * FROM REMAIN;
DESC REMAIN;

INSERT INTO REMAIN(REMAIN_YEAR,REMAIN_PROD,REMAIN_I)
        VALUES('2003','P102000007',10);
-- 입력된 부분만 들어가고 나머지는 NULL
-- 단 NOT NULL로 지정된 부분의 값은 반드시 들어가야 한다
        
--NULL값 입력방법 1) INSERT문장의 COLOMN LIST에서 생략
INSERT INTO REMAIN(REMAIN_YEAR,REMAIN_PROD,REMAIN_J_00,REMAIN_I
                   ,REMAIN_J_99,REMAIN_DATE)
VALUES('2003','P102000002',31,21,41,'2003-12-31');

--오라클에서는 NULL과 공백문자('')는 동일

--INSERT문장의 VALUE절에서 NULL 또는 빈공백(화이트스페이스-'')으로 지정
INSERT INTO REMAIN(REMAIN_YEAR,REMAIN_PROD,REMAIN_J_00,REMAIN_I
                   ,REMAIN_O,REMAIN_J_99,REMAIN_DATE)
VALUES('2003','P102000002',31,21,NULL,41,'2003-12-31');

INSERT INTO REMAIN(REMAIN_YEAR,REMAIN_PROD,REMAIN_J_00,REMAIN_I
                   ,REMAIN_O,REMAIN_J_99,REMAIN_DATE)
VALUES('2003','P102000003',31,21,'',41,SYSDATE);

--REMAIN테이블에 다음 데이터 입력하기
INSERT INTO REMAIN(REMAIN_YEAR,REMAIN_PROD,REMAIN_J_00,REMAIN_I
                   ,REMAIN_O,REMAIN_J_99,REMAIN_DATE)
VALUES('2003','P302000023',35,NULL,20,15,SYSDATE);
--여기서 P302000023이 부모 테이블의 참조할 영역에 없다면 추가할 수 없다

--REMAIN테이블에 다음 데이터를 입력하기. 오류발생 시 상품코드의 첫글자를 수정하여 디버깅
INSERT INTO REMAIN(REMAIN_YEAR,REMAIN_PROD,REMAIN_J_00,REMAIN_I
                   ,REMAIN_O,REMAIN_J_99,REMAIN_DATE)
VALUES('2003','P302000022',27,NULL,NULL,NULL,'2004-01-01');

--REMAIN테이블에 다음 데이터 입력. 오류발생 시 디버깅(2003년도)
INSERT INTO REMAIN(REMAIN_YEAR,REMAIN_PROD,REMAIN_J_00,REMAIN_I
                   ,REMAIN_O,REMAIN_J_99,REMAIN_DATE)
VALUES('2003','P302000016',30,10,15,25,'2004-01-02');

commit;

--모든 상품에 대한 재고 수불 파일 생성 (2016년도 재고 수불 마감)
--해당년도 2017년    상품코드 전 상품(PROD테이블)
--기초(전년)재고 상품코드의 우측2자리를 숫자로 컨버전하여 처리(원칙은 전년도말 재고가 되어야 함)
--입고    10으로 일괄 처리      출고  7로 일괄 처리        현재고 전년재고+입고-출고
INSERT INTO REMAIN(REMAIN_YEAR,REMAIN_PROD,REMAIN_J_00,REMAIN_I
                   ,REMAIN_O,REMAIN_J_99,REMAIN_DATE)
SELECT '2017',PROD_ID,TO_NUMBER(SUBSTR(PROD_ID,-2))
       ,10,7,TO_NUMBER(SUBSTR(PROD_ID,-2))+10-7, SYSDATE
FROM PROD;
--INSERT와 SELECT를 동시에 써서 데이터를 일괄 입력하는 방법. 이렇게 하면 VALUES없어도 입력가능.

--2018년도 재고수불 마감 작업. REMAIN테이블사용. 모든 상품에 대한 재고 수불 파일 생성
--2018년, 상품코드: 2017년도 수불 상품, 전년재고: 2017년말의 현재고 현재고: 2017년말의 현재고
INSERT INTO REMAIN(REMAIN_YEAR,REMAIN_PROD,REMAIN_J_00,REMAIN_J_99)
SELECT '2018',REMAIN_PROD, REMAIN_J_99, REMAIN_J_99
FROM REMAIN
WHERE REMAIN_YEAR LIKE '2017%';

--INSERT ALL
--여러개의 SQL문을 한 번에 INSERT하는 구문
CREATE TABLE LPROD_ALL
AS
SELECT * FROM LPROD;

DELETE FROM LPROD_ALL;

SELECT * FROM LPROD_ALL;

--PPT 321P          UPDATE 심화
--'이'와 '김'씨 성을 가진 회원의 휴대폰 컬럼을 '011-111-1111'로 갱신
UPDATE MEMBER
SET MEM_HP = '011-111-1111'
WHERE MEM_NAME LIKE '이%' OR MEM_NAME LIKE '김%';
--또는 SUBSTR(MEM_NAME,1,1) IN('이','김')

--회원테이블에서 회원ID가 'a001'인 회원의 데이터를 다음과 같이 수정
--취미: 독서, 직업: 군인
SELECT * FROM MEMBER WHERE MEM_ID = 'a001';

UPDATE MEMBER
SET MEM_JOB = '군인',MEM_LIKE = '독서'
WHERE MEM_ID = 'a001';

--'이'와 '김'씨 성을 가진 회원 중 회원ID가 'a001','j001'인 회원을 제외하고
--휴대폰 컬럼에 '099-999-9999'로 갱신
SELECT * FROM MEMBER;

UPDATE MEMBER
SET MEM_HP = '099-999-9999'
WHERE (MEM_NAME LIKE '이%' OR MEM_NAME LIKE '김%')
      AND MEM_ID NOT IN('a001','j001');

--회원테이블(MEMBER)에서 모든 회원의 마일리지 컬럼값을 10% 높게 수정
UPDATE MEMBER
SET    MEM_MILEAGE = MEM_MILEAGE * 1.1; -- WHERE가 없으면 모든 컬럼이 업데이트된다

--MEMBER테이블에서 마일리지가 3000점 이상이고, 휴대폰번호가 011로 시작되는 회원의 마일리지를
--10%인상처리

UPDATE MEMBER
SET    MEM_MILEAGE = MEM_MILEAGE * 1.1
WHERE  MEM_MILEAGE >= 3000
       AND SUBSTR(MEM_HP,1,3) = '011';

-- PPT 325P EXISTS UPDATE
-- WHERE절에서 IN이나 EXISTS를 활용


/*
2005년도 판매금액이 8천만 이상인 거래처의 담당자 컬럼을 '우수거래처'로 갱신
WHERE절에 EXISTS문 사용
*/
--1단계: 2005년도 판매금액이 8천만 이상인 거래처를 출력하기
SELECT B.BUYER_ID                     거래처ID
     , B.BUYER_NAME                   거래처명
     , SUM(C.CART_QTY * P.PROD_SALE)    판매금액
FROM BUYER B, PROD P, CART C
WHERE B.BUYER_ID = P.PROD_BUYER
AND P.PROD_ID = C.CART_PROD
AND C.CART_NO LIKE '2005%'
GROUP BY B.BUYER_ID, B.BUYER_NAME
HAVING SUM(C.CART_QTY * P.PROD_SALE) >= 80000000;

--0220 계속
--2단계: 모든 거래처 출력
SELECT BUYER_ID
     , BUYER_NAME
     , BUYER_CHARGER
FROM BUYER;

--3단계: 두 테이블의 교집합 만들기
SELECT A.BUYER_ID
     , A.BUYER_NAME
     , A.BUYER_CHARGER
FROM BUYER A
WHERE EXISTS -- WHERE절이 없으므로 WHERE EXISTS를 써 준다
    (SELECT B.BUYER_ID                     거래처ID
         , B.BUYER_NAME                   거래처명
         , SUM(C.CART_QTY * P.PROD_SALE)    판매금액
    FROM BUYER B, PROD P, CART C
    WHERE B.BUYER_ID = P.PROD_BUYER
    AND P.PROD_ID = C.CART_PROD
    AND C.CART_NO LIKE '2005%'
    AND A.BUYER_ID = B.BUYER_ID     --자료형, 데이터가 같아야 연결고리 역할을 할 수 있다
    GROUP BY B.BUYER_ID, B.BUYER_NAME
    HAVING SUM(C.CART_QTY * P.PROD_SALE) >= 80000000);

--4단계: UPDATE
UPDATE BUYER A
SET    A.BUYER_CHARGER = '우수'
WHERE EXISTS
    (SELECT B.BUYER_ID                     거래처ID
         , B.BUYER_NAME                   거래처명
         , SUM(C.CART_QTY * P.PROD_SALE)    판매금액
    FROM BUYER B, PROD P, CART C
    WHERE B.BUYER_ID = P.PROD_BUYER
    AND P.PROD_ID = C.CART_PROD
    AND C.CART_NO LIKE '2005%'
    AND A.BUYER_ID = B.BUYER_ID
    GROUP BY B.BUYER_ID, B.BUYER_NAME
    HAVING SUM(C.CART_QTY * P.PROD_SALE) >= 80000000);
    
--2005년도 구매금액이 3천만 이상인 회원의 마일리지 점수를 20만으로 하여 수정하시오
--A테이블
SELECT A.MEM_ID
     , A.MEM_NAME
     , A.MEM_MILEAGE
FROM MEMBER A;

--B테이블
--2005년도 구매금액이 3천만 이상인 회원 출력
SELECT M.MEM_ID                     회원ID
     , M.MEM_NAME                   회원명
     , SUM(C.CART_QTY * P.PROD_SALE)    구매금액
FROM MEMBER M, CART C, PROD P
WHERE M.MEM_ID = C.CART_MEMBER
AND P.PROD_ID = C.CART_PROD
AND C.CART_NO LIKE '2005%'
GROUP BY M.MEM_ID, M.MEM_NAME
HAVING SUM(C.CART_QTY * P.PROD_SALE) >= 30000000;
--합치기
UPDATE MEMBER A
SET A.MEM_MILEAGE = 200000
WHERE EXISTS
    (SELECT M.MEM_ID                     회원ID
         , M.MEM_NAME                   회원명
         , SUM(C.CART_QTY * P.PROD_SALE)    구매금액
    FROM MEMBER M, CART C, PROD P
    WHERE M.MEM_ID = C.CART_MEMBER
    AND P.PROD_ID = C.CART_PROD
    AND C.CART_NO LIKE '2005%'
    AND A.MEM_ID = M.MEM_ID
    GROUP BY M.MEM_ID, M.MEM_NAME
    HAVING SUM(C.CART_QTY * P.PROD_SALE) >= 30000000);
    
--PPT 330P JOIN UPDATE

--상품 모두의 2005년도 입고수량을 합산하여 상품테이블의 총 입고수량 COLOMN(PROD_QTYIN) 갱신
--매입은? 상품이 창고에 들어오는 것. 우선 상품별 입고수량을 합산
--A집합
SELECT PROD_ID, PROD_QTYIN
FROM PROD;

--B집합
SELECT BUY_PROD
     , SUM(BUY_QTY)
FROM BUYPROD
WHERE BUY_DATE LIKE '05/%'
GROUP BY BUY_PROD;

--교집합
SELECT PROD_ID, PROD_QTYIN
FROM PROD
WHERE EXISTS
    (SELECT BUY_PROD
         , SUM(BUY_QTY)
    FROM BUYPROD
    WHERE BUY_DATE LIKE '05/%'
    AND PROD_ID = BUY_PROD
    GROUP BY BUY_PROD);
    
--PROD_QTYIN 업데이트
UPDATE PROD
SET PROD_QTYIN = 
    (SELECT SUM(BUY_QTY) -- WHERE EXISTS와 BUY_PROD를 없애는 이유? SUM을 바로 적용시키기 위해
    FROM BUYPROD
    WHERE BUY_DATE LIKE '05/%'
    AND PROD_ID = BUY_PROD
    GROUP BY BUY_PROD);

--상품 모두의 2005년도 판매수량을 합산하여 상품테이블의 총판매수량 COLOMN갱신
--A
SELECT PROD_ID
     , PROD_QTYSALE
FROM PROD;
--B
SELECT CART_PROD
     , SUM(CART_QTY)
FROM CART
WHERE CART_NO LIKE '2005%'
GROUP BY CART_PROD;
--
UPDATE PROD
SET    PROD_QTYSALE = (
    SELECT SUM(CART_QTY)
    FROM CART
    WHERE CART_NO LIKE '2005%'
    AND PROD_ID = CART_PROD
    GROUP BY CART_PROD); -- 상관관계 SUBQUERY형식

--336P 상품 모두의 2005년도 입고수량, 판매수량을 합산하여  재고수불(remain)테이블의  입고, 출고, 현재고 Column을 수정하시오 ? 
--최종결과
UPDATE REMAIN
SET     (REMAIN_I, REMAIN_O, REMAIN_J_99) =

(
SELECT TBL1.IN_AMT, TBL1.OUT_AMT, TBL1.IN_AMT - TBL1.OUT_AMT
FROM
(SELECT A.PROD_ID
     , (SELECT SUM(BUY_QTY)
        FROM BUYPROD
        WHERE BUY_DATE LIKE '05%'
        AND A.PROD_ID = BUYPROD.BUY_PROD) IN_AMT
     , (SELECT SUM(CART_QTY)
        FROM CART
        WHERE CART_NO LIKE '2005%'
        AND A.PROD_ID = CART.CART_PROD) OUT_AMT
FROM    PROD A) TBL1
WHERE   REMAIN_PROD = TBL1.PROD_ID
)
WHERE REMAIN_YEAR = '2017';

--1
SELECT PROD_ID
     , IN_AMT
     , OUT_AMT
FROM PROD;
--2 위 IN_AMT를 정리한 것
SELECT PROD_ID
     , SUM(BUY_QTY)
FROM PROD, BUYPROD
WHERE PROD_ID = BUY_PROD
AND BUY_DATE LIKE '05%'
GROUP BY PROD_ID;
--3 OUT_AMT 정리
SELECT PROD_ID
     , SUM(CART_QTY)
FROM PROD, CART
WHERE PROD_ID = CART_PROD
AND CART_NO LIKE '2005%'
GROUP BY PROD_ID;
--한곳으로 모으기
SELECT A.PROD_ID
     , (SELECT SUM(B.BUY_QTY)
        FROM BUYPROD B
        WHERE A.PROD_ID = B.BUY_PROD
        AND B.BUY_DATE LIKE '05%') IN_AMT
     , (SELECT SUM(CART_QTY)
        FROM CART
        WHERE PROD_ID = CART_PROD
        AND CART_NO LIKE '2005%') OUT_AMT
    FROM PROD A;
--다음 단계
SELECT TBL1.PROD_ID
     , TBL1.IN_AMT
     , TBL1.OUT_AMT
     , TBL1.IN_AMT - TBL1.OUT_AMT
FROM
(SELECT A.PROD_ID
     , (SELECT SUM(B.BUY_QTY)
        FROM BUYPROD B
        WHERE A.PROD_ID = B.BUY_PROD
        AND B.BUY_DATE LIKE '05%') IN_AMT
     , (SELECT SUM(CART_QTY)
        FROM CART
        WHERE PROD_ID = CART_PROD
        AND CART_NO LIKE '2005%') OUT_AMT
    FROM PROD A
) TBL1;
--마무리
UPDATE REMAIN
SET     (REMAIN_I, REMAIN_O, REMAIN_J_99) =

(
SELECT TBL1.IN_AMT, TBL1.OUT_AMT, TBL1.IN_AMT - TBL1.OUT_AMT
FROM
(SELECT A.PROD_ID
     , (SELECT SUM(BUY_QTY)
        FROM BUYPROD
        WHERE BUY_DATE LIKE '05%'
        AND A.PROD_ID = BUYPROD.BUY_PROD) IN_AMT
     , (SELECT SUM(CART_QTY)
        FROM CART
        WHERE CART_NO LIKE '2005%'
        AND A.PROD_ID = CART.CART_PROD) OUT_AMT
FROM    PROD A) TBL1
WHERE   REMAIN_PROD = TBL1.PROD_ID -- 서브쿼리 바깥 REMAIN과 PROD테이블의 JOIN조건
)
WHERE REMAIN_YEAR = '2017';
-----------------------------------------------------
/*
          PPT 341P DELETE 심화
방법은 크게 세 가지. DELETE, TRUNCATE, DROP
DELETE는 테이블의 데이터를 모두 삭제하지만 ROLLBACK이 가능하다. 느리다(롤백을 대비해 특정 캐시에 데이터를 옮기고 지우기 때문)
TRUNCATE는 자동 커밋, 초기화됨. FLASHBACK으로 되돌릴 수는 있음
DROP은 테이블 구조 및 데이터 모두 삭제. 자동 커밋.
TRUNCATE와 DROP처럼 자동 커밋되는 명령어는 사용주의!
*/

CREATE TABLE REMAIN2 AS SELECT * FROM REMAIN;

CREATE TABLE LPROD_DELETE AS SELECT * FROM LPROD;
CREATE TABLE LPROD_TRUNCATE AS SELECT * FROM LPROD;
CREATE TABLE LPROD_DROP AS SELECT * FROM LPROD;

DELETE FROM LPROD_DELETE;

TRUNCATE TABLE LPROD_TRUNCATE;

DROP TABLE LPROD_DROP;

--재고수불2 테이블에서 2003년도 자료 중 출고수량이 6개 또는 11개인 자료를 삭제
SELECT * FROM REMAIN2
WHERE REMAIN_YEAR = 2003
AND REMAIN_O IN(6,11);

DELETE FROM REMAIN2
WHERE REMAIN_YEAR = 2003
AND REMAIN_O IN(6,11);

--재고수불2 테이블에서 2003년도 자료 중 입고수량+출고수량이 20개 이상인 자료를 삭제
SELECT * FROM REMAIN2
WHERE REMAIN_YEAR = 2003
AND (NVL(REMAIN_I,0) + NVL(REMAIN_O,0)) >= 20; --NULL인 행도 찾고 싶으면

DELETE FROM REMAIN2
WHERE REMAIN_YEAR = 2003
AND (NVL(REMAIN_I,0) + NVL(REMAIN_O,0)) >= 20;

-------------------------------------------
--PPT 352P VIEW객체
--VIEW는 논리적이다(물리적 X)
--VIEW는 쇼윈도의 유리와 같은 존재. VIEW를 통해 실제 데이터를 바라본다.

--LPROD테이블의 LPROD_GU, LPROD_NM컬럼만 추출, 가상의 테이블인 VW_LPROD 뷰 생성
CREATE OR REPLACE VIEW VW_LPROD
AS
SELECT LPROD_GU
     , LPROD_NM
FROM LPROD;








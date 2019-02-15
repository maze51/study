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
UNION(합집합): 중복부분은 1회 출력되고 결과물은 자동정렬.
UNION ALL: 중복부분도 모두 출력되고 결과물은 자동정렬되지 않음.
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



--JOIN ORDER BY

--INSERT심화

--UPDATE심화




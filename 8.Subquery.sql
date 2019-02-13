--------------------------------------------------------------
--책 78P 서브쿼리(SUBQUERY)
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


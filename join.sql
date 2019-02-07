----------------------------------------------------
--PPT 244P 책 70P      조인(JOIN)

SELECT LPROD_GU
     , LPROD_NM
FROM LPROD;
--JOIN할 집합 1

SELECT PROD_ID
     , PROD_LGU
     , PROD_NAME
FROM PROD;
--JOIN할 집합 2

--PPT 247P 1. CARTESIAN PRODUCT(카티션곱: 모든 가능한 행들의 조합)
--물리적으로 합쳐지는 것이 아닌, 논리적으로(가상으로) 합쳐지는 것(합쳐진 것 처럼 보일 뿐)

SELECT LPROD_GU
     , LPROD_NM
     , PROD_ID
     , PROD_LGU
     , PROD_NAME
FROM LPROD, PROD;

--LPROD와 BUYER 카티션곱
SELECT LPROD_GU
     , LPROD_NM
FROM LPROD;
--JOIN할 집합 1. 9행

SELECT BUYER_ID
     , BUYER_NAME
     , BUYER_LGU
FROM BUYER;
--JOIN할 집합 2. 13행

SELECT LPROD_GU
     , LPROD_NM
     , BUYER_ID
     , BUYER_NAME
     , BUYER_LGU
FROM LPROD, BUYER;
--5열, 9 * 13 = 117행

--PPT 248P 2. EQUAL(EQUI) JOIN(공통점을 찾아 조합)
--무엇이 무엇을 참조하는가(어떤 연결고리가 있는가) 찾아서 연결
--ERwin에서 관계선 더블클릭 - Rolename에 표기된 부분을 참고하면 좋다

SELECT LPROD_GU
     , LPROD_NM
     , BUYER_ID
     , BUYER_NAME
     , BUYER_LGU
FROM LPROD, BUYER
WHERE LPROD_GU = BUYER_LGU;
--상품분류코드가 같은 것만 찾으면? 데이터가 올바르게 나온다

--JOIN조건(WHERE절) : JOIN은 서로 관계(연결고리)가 있어야 가능

SELECT BUYER_ID
     , BUYER_NAME
     , PROD_ID
     , PROD_NAME
     , PROD_BUYER
FROM BUYER, PROD
WHERE  BUYER_ID = PROD_BUYER;

--(참고) 기본키/외래키 설정 없이는 개체간 관계를 설정할 수 없다

SELECT P.PROD_ID                        -- 여기에 다 달아준다
     , P.PROD_NAME
     , P.PROD_BUYER
     , BP.BUY_DATE
     , BP.BUY_PROD
     , BP.BUY_QTY
     , BP.BUY_COST
     , B.BUYER_ID
     , B.BUYER_NAME
FROM PROD P, BUYPROD BP, BUYER B        -- 여기서 부여하고
WHERE P.PROD_ID = BP.BUY_PROD
AND B.BUYER_ID = P.PROD_BUYER;

--TABLE ALIAS.  일반 ALIAS처럼 AS는 쓰지 않는다
--구별을 위해 사용하는 것을 권장


--관계가 있다면 3개 이상도 가능
SELECT PROD_ID
     , PROD_NAME
     , CART_PROD
     , CART_MEMBER
     , CART_QTY
     , MEM_ID
     , MEM_NAME
FROM PROD , CART, MEMBER
WHERE PROD_ID = CART_PROD AND CART_MEMBER = MEM_ID;

SELECT PROD_ID
     , PROD_NAME
     , BUYER_NAME
     , BUY_QTY
     , CART_QTY
     , MEM_NAME
FROM PROD, BUYER, BUYPROD, CART, MEMBER
WHERE PROD_BUYER = BUYER_ID
AND PROD_ID = BUY_PROD
AND PROD_ID = CART_PROD
AND CART_MEMBER = MEM_ID;

--다대다 관계 JOIN? 가능은 하나 좋지 않다(CART와 BUYPROD JOIN)등

--72P
--상품테이블에서 거래처가 '삼성전자'인 자료의 상품코드, 상품명, 거래처명을 조회
--먼저 물리명을 확인. PROD_ID, PROD_NAME, BUYER_NAME
SELECT P.PROD_ID
     , P.PROD_NAME
     , B.BUYER_NAME
FROM PROD P, BUYER B
WHERE PROD_BUYER = BUYER_ID
AND B.BUYER_NAME = '삼성전자';

--EQUI JOIN을 ANSI표준인 INNER JOIN으로 바꾸기
--, 대신 INNER JOIN을 쓴다. WHERE대신 ON을 쓴다. 합쳐서 INNER JOIN ON
SELECT P.PROD_ID
     , P.PROD_NAME
     , B.BUYER_NAME
FROM PROD P INNER JOIN BUYER B ON(P.PROD_BUYER = B. BUYER_ID)
AND B.BUYER_NAME = '삼성전자';

/*
JOIN의 종류 다섯가지(PPT 244P) 암기 필요. 이건 오라클 사투리.
1. CARTESIAN PRODUCT 모든 가능한 행들의 조합
2. EQUI JOIN 조건이 일치하는 컬럼을 매칭. "중요"
3. NON-EQUI JOIN 조건절에서 JOIN조건이 '='아닌 다른 연산기호로 주어질 때
4. OUTER JOIN 조건이 일치하지 않더라도 모든 행을 검색할 때. (+)로 표시. "중요"
5. SELF JOIN 한 테이블 내에서 JOIN하는 경우

246P ANSI JOIN 표준말. 역시 숙지 필요
1. CROSS JOIN
2. NATURAL JOIN
3. INNER JOIN
4. OUTER JOIN
*/
--장바구니번호, 상품코드, 구입개수, 회원ID, 회원명
--사용테이블 : CART, MEMBER
--단 2005년도 4월 데이터로 한정. 상품코드를 상품분류코드로 바꾸기, 장바구니번호를
--년월로 바꾸기. 월별, 상품분류별, 회원별, 구입개수합계를 구하기

SELECT SUBSTR(C.CART_NO,1,6)      장바구니번호    --CART_NO가 문자형이라 EXTRACT를 쓸 수 없다
     , SUBSTR(C.CART_PROD,1,4)    상품코드
     , C.CART_MEMBER              회원ID
     , M.MEM_NAME                 회원명
     , SUM(C.CART_QTY)            구입개수      --집계함수 이외의 컬럼은 GROUP BY절에 기술해라!
FROM CART C, MEMBER M
WHERE C.CART_MEMBER = M.MEM_ID
AND SUBSTR(C.CART_NO,1,6) = '200504'    --AND CART_NO LIKE '200504%' 쪽이 같은 기능이면서 간결
GROUP BY SUBSTR(C.CART_NO,1,6)
     , SUBSTR(C.CART_PROD,1,4)
     , C.CART_MEMBER
     , M.MEM_NAME
ORDER BY 1, 2;
/*
참고) AND쪽 두 가지를 쿼리박스로 분석시
LIKE 쓸 경우 별도 캐시에 인덱스가 자동 생성됨. 인덱스로 RANGE SCAN해서 찾음
SUBSTR 쓸 경우 인덱스가 깨지고 옵티마이저가 TABLE ACCESS FULL로 찾음. 하지만 더 빠르다. 빠른 쪽을 쓰는 게 좋음.
보통은 인덱스가 살아있을 때 속도가 빠르다. 이 경우는 예외
*/

--상품테이블에서 상품코드, 상품명, 분류명(분류 관련 정보는 LPROD테이블), 거래처명, 거래처주소를 조회
--판매가격이 10만원 이하이고 거래처주소가 부산인 경우만 조회

SELECT P.PROD_ID      상품코드
     , P.PROD_NAME    상품명
     , L.LPROD_NM     분류명
     , B.BUYER_NAME   거래처명
     , B.BUYER_ADD1 || ' ' || B.BUYER_ADD2  거래처주소
FROM PROD P, BUYER B, LPROD L
WHERE P.PROD_BUYER = B.BUYER_ID
AND L.LPROD_GU = P.PROD_LGU
AND P.PROD_SALE <= 100000
AND B.BUYER_ADD1 LIKE '부산%';

--상품분류가 전자제품(P102)인 상품의 상품코드, 상품명, 분류명, 거래처명을 조회
SELECT P.PROD_ID          상품코드
     , P.PROD_NAME        상품명
     , L.LPROD_NM         분류명
     , B.BUYER_NAME       거래처명
FROM PROD P, LPROD L, BUYER B
WHERE P.PROD_BUYER = B.BUYER_ID
AND L.LPROD_GU = P.PROD_LGU
AND L.LPROD_GU = 'P102';

--ANSI표준으로
SELECT P.PROD_ID          상품코드
     , P.PROD_NAME        상품명
     , L.LPROD_NM         분류명
     , B.BUYER_NAME       거래처명
FROM PROD P INNER JOIN LPROD L ON(L.LPROD_GU = P.PROD_LGU)
            INNER JOIN BUYER B ON(P.PROD_BUYER = B.BUYER_ID)
AND L.LPROD_GU = 'P102';

--상품입고테이블(BUYPROD)의 2005년도 1월의 거래처별(거래처코드 BUYER_ID 거래처명 BUYER_NAME)
--매입금액을 검색하시오.
--매입금액=매입수량(BUY_QTY) * 매입단가(PROD_COST)
--ALIAS 거래처코드, 거래처명, 매입금액

SELECT B.BUYER_ID             거래처코드
     , B.BUYER_NAME           거래처명
     , SUM(BP.BUY_QTY * P.PROD_COST)  매입금액
FROM BUYER B, BUYPROD BP , PROD P -- FROM BUYER CROSS JOIN BUYPROD CROSS JOIN PROD 이렇게 쓰면 ANSI표준
WHERE BP.BUY_PROD = P.PROD_ID
AND P.PROD_BUYER = B.BUYER_ID
AND BUY_DATE LIKE '05/01/%'
GROUP BY BUYER_ID, BUYER_NAME
ORDER BY 1;

--ANSI 표준으로
SELECT B.BUYER_ID             거래처코드
     , B.BUYER_NAME           거래처명
     , SUM(BP.BUY_QTY * P.PROD_COST)  매입금액
FROM PROD P  INNER JOIN BUYPROD BP ON(BP.BUY_PROD = P.PROD_ID)
             INNER JOIN BUYER B ON(P.PROD_BUYER = B.BUYER_ID)
--FROM BUYER B INNER JOIN PROD P ON(B.BUYER_ID = P.PROD_BUYER)
--              INNER JOIN BUYPROD BP(P.PROD_ID = 
AND BUY_DATE LIKE '05/01/%'
--BP.BUY_DATE           BETWEEN 도 사용 가능
GROUP BY BUYER_ID, BUYER_NAME
ORDER BY 1;

--70P 장바구니테이블(CART)의 2005년도 5월의 회원별 구매금액을 검색
--구매금액 = 구매수량(CART_QTY) * 판매가(PROD_SALE)
--ALIAS 회원ID(MEM_ID), 회원명(MEM_NAME), 구매금액
--EQUI JOIN, INNER JOIN모두 사용

SELECT MEM_ID           회원ID
     , MEM_NAME         회원명
     , SUM(CART_QTY * PROD_SALE)     구매금액
FROM CART, MEMBER, PROD
WHERE CART_MEMBER = MEM_ID
AND CART_PROD = PROD_ID
AND CART_NO LIKE '200505%'
GROUP BY MEM_ID, MEM_NAME
ORDER BY 1;

--ANSI표준으로
SELECT M.MEM_ID           회원ID
     , M.MEM_NAME         회원명
     , SUM(C.CART_QTY * P.PROD_SALE)     구매금액
FROM CART C INNER JOIN MEMBER M ON(CART_MEMBER = MEM_ID)    --TABLE ALIAS 빼먹지 않도록
          INNER JOIN PROD P ON(CART_PROD = PROD_ID)
AND C.CART_NO LIKE '200505%'
GROUP BY M.MEM_ID, M.MEM_NAME
ORDER BY 1;

--------------------------
--OUTER JOIN
----------------------------------------------------
--PPT 244P 책 70P      조인(JOIN)
--JOIN하려면? 자료형, 길이, 데이터가 같아야

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
2. EQUI JOIN (기본키/외래키)조건이 일치하는 컬럼을 매칭. "중요"
3. NON-EQUI JOIN 조건절에서 JOIN조건이 '='아닌 다른 연산기호로 주어질 때
4. OUTER JOIN 조건이 일치하지 않더라도 모든 행을 검색할 때. (+)로 표시. "중요"
5. SELF JOIN 한 테이블 내에서 JOIN하는 경우
CENOS 로 암기

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

/*
상품입고테이블(BUYPROD)의 2005년도 1월의 거래처별(거래처코드 BUYER_ID 거래처명 BUYER_NAME)
매입금액을 검색하시오.
매입금액=매입수량(BUY_QTY) * 매입단가(PROD_COST)
ALIAS 거래처코드, 거래처명, 매입금액
*/
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

/*
70P 장바구니테이블(CART)의 2005년도 5월의 회원별 구매금액을 검색
구매금액 = 구매수량(CART_QTY) * 판매가(PROD_SALE)
ALIAS 회원ID(MEM_ID), 회원명(MEM_NAME), 구매금액
EQUI JOIN, INNER JOIN모두 사용
*/
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

----------------------------------------------------
/*
책 72P PPT 259P OUTER JOIN(EQUI JOIN과 함께 가장 중요한 방법)
JOIN조건: 보통은 1. 기본키와 외래키(필수는 아니나 무결성을 지키기 위해 필요함)
                2. 자료형, 크기가 동일해야
                3. 같은 데이터가 들어 있어야
EQUI JOIN시 한 쪽에 없는 데이터는 자동으로 걸러짐
모두 포함시키고자 한다면 OUTER JOIN을 사용해야 한다.
<OUTER JOIN의 종류>
    1. 왼쪽 외부 조인(LEFT OUTER JOIN): JOIN할 테이블 중 왼쪽에 무게를 실어준다
        JOIN결과물에 왼쪽 테이블의 모든 정보 포함. 오른쪽 테이블에 (+)가 붙음.
    2. 오른쪽 외부 조인(RIGHT OUTER JOIN): JOIN할 테이블 중 오른쪽에 무게를 실어준다
        JOIN결과물에 오른쪽 테이블의 모든 정보 포함. 왼쪽 테이블에 (+)가 붙음.
        EQUI JOIN시 이미 오른쪽 테이블의 데이터가 모두 포함되어 있다. 그래서 큰 의미는 없음
    3. 완전 외부 조인(FULL OUTER JOIN 합집합): JOIN할 테이블 양쪽 모두를 포함한다.
        왼쪽, 오른쪽 외부 조인을 각각 작성 후 UNION으로 결합.
        이 때 중복부분은 1회 출력되고 결과물은 자동정렬.
        UNION ALL: 중복부분도 모두 출력되고 결과물은 자동정렬되지 않음.
        INTERSECT(교집합): JOIN할 테이블간 중복 부분만 출력.
        MINUS(차집합): JOIN할 테이블 중 A - B하고 남는 부분만 출력.
*/

--1. S테이블 생성 후 기본키는 C / 컬럼 : C, D, E
CREATE TABLE S(
   C VARCHAR2(5),
   D VARCHAR2(5),
   E VARCHAR2(5),
   CONSTRAINT PK_S PRIMARY KEY(C)
);

--2. R테이블 생성 후 기본키는 A / 컬럼 : A, B, C
CREATE TABLE R(
   A VARCHAR2(5),
   B VARCHAR2(5),
   C VARCHAR2(5),
   CONSTRAINT PK_R PRIMARY KEY(A)
);

--3. R테이블에 데이터 입력
INSERT INTO R(A, B, C) VALUES('a1','b1','c1');
INSERT INTO R(A, B, C) VALUES('a2','b2','c2');

--4. S테이블에 데이터 입력
INSERT INTO S(C, D, E) VALUES('c1','d1','e1');
INSERT INTO S(C, D, E) VALUES('c3','d2','e2');

--TCL(Transaction Control Language)
--COMMIT : 기존 트랜잭션이 종료되고 새로운 트랜잭션이 시작됨
COMMIT;

--데이터 확인하기
SELECT * FROM R;
SELECT * FROM S;

--5. 두 테이블을 EQUI JOIN, INNER JOIN처리
--EQUI JOIN
SELECT R.A, R.B, R.C, S.C, S.D, S.E
FROM R, S
WHERE R.C = S.C;
--ANSI표준 INNER JOIN
SELECT R.A, R.B, R.C, S.C, S.D, S.E
FROM   R INNER JOIN S ON(R.C = S.C);

--6. 왼쪽 외부 조인(LEFT OUTER JOIN)
SELECT R.A, R.B, R.C, S.C, S.D, S.E
FROM R, S
WHERE R.C = S.C(+);
--ANSI표준 LEFT OUTER JOIN
SELECT R.A, R.B, R.C, S.C, S.D, S.E
FROM   R LEFT OUTER JOIN S ON(R.C = S.C);

--7. R테이블과 S테이블을 오른쪽 외부조인
SELECT R.A, R.B, R.C, S.C, S.D, S.E
FROM R, S
WHERE R.C(+) = S.C;
--ANSI표준 RIGHT OUTER JOIN
SELECT R.A, R.B, R.C, S.C, S.D, S.E
FROM   R RIGHT OUTER JOIN S ON(R.C = S.C);

--8. R테이블과 S테이블을 완전 외부 조인
SELECT R.A, R.B, R.C, S.C, S.D, S.E
FROM R, S
WHERE R.C(+) = S.C
UNION
SELECT R.A, R.B, R.C, S.C, S.D, S.E
FROM R, S
WHERE R.C = S.C(+);
--ANSI표준 FULL OUTER JOIN
SELECT R.A, R.B, R.C, S.C, S.D, S.E
FROM   R FULL OUTER JOIN S ON(R.C = S.C);


--DEPART, STUDENT 테이블을 통해 OUTER JOIN 연습
--1. DEPART 테이블 생성
CREATE TABLE DEPART(
    DEP_CODE VARCHAR2(10),
    DEP_NAME VARCHAR2(10),
    CONSTRAINT PK_DEPART PRIMARY KEY(DEP_CODE)
);
--2. STUDENT 테이블 생성
--PK: STUD_NO FK: STUD_DEP(DEPART의 DEP_CODE컬럼 참조) / 컬럼: STUD_NO, STUD_NAME STUD_DEP
CREATE TABLE STUDENT(
    STUD_NO VARCHAR2(10),
    STUD_NAME VARCHAR2(10),
    STUD_DEP VARCHAR2(10),
    CONSTRAINT PK_STUDENT PRIMARY KEY (STUD_NO),
    CONSTRAINT FK_STUD_DEP FOREIGN KEY (STUD_DEP) REFERENCES DEPART (DEP_CODE)
);
--마지막 행을 분리해서 쓸 수도 있다.
--ALTER TABLE STUDENT
--ADD(CONSTRAINT FK_STUD_DEP FOREIGN KEY (STUD_DEP) REFERENCES DEPART (DEP_CODE));

--3. DEPART 데이터 입력하기
INSERT INTO DEPART(DEP_CODE, DEP_NAME) VALUES('201','12월반');
INSERT INTO DEPART(DEP_CODE, DEP_NAME) VALUES('202','5월반');
INSERT INTO DEPART(DEP_CODE, DEP_NAME) VALUES('203','4월반');
INSERT INTO DEPART(DEP_CODE, DEP_NAME) VALUES('204','1월반');
INSERT INTO DEPART(DEP_CODE, DEP_NAME) VALUES('205','3월반');
INSERT INTO DEPART(DEP_CODE, DEP_NAME) VALUES('206','6월반');
INSERT INTO DEPART(DEP_CODE, DEP_NAME) VALUES('207','11월반');

COMMIT;
--4. STUDENT 데이터 입력하기
INSERT INTO STUDENT(STUD_NO,STUD_NAME,STUD_DEP)
VALUES('2006120001','박서경','206');
INSERT INTO STUDENT(STUD_NO,STUD_NAME,STUD_DEP)
VALUES('2006120002','정요한','206');
INSERT INTO STUDENT(STUD_NO,STUD_NAME,STUD_DEP)
VALUES('2006120003','박영춘','205');
INSERT INTO STUDENT(STUD_NO,STUD_NAME,STUD_DEP)
VALUES('2006120004','유다연','201');
INSERT INTO STUDENT(STUD_NO,STUD_NAME,STUD_DEP)
VALUES('2006120005','유민하','202');

COMMIT;

--5. DEPART테이블과 STUDENT테이블을 EQUI JOIN & INNER JOIN 모든 컬럼 포함
SELECT D.DEP_CODE, D.DEP_NAME, S.STUD_NO, S.STUD_NAME, S.STUD_DEP
FROM DEPART D, STUDENT S
WHERE D.DEP_CODE = S.STUD_DEP;

SELECT D.DEP_CODE, D.DEP_NAME, S.STUD_NO, S.STUD_NAME, S.STUD_DEP
FROM DEPART D INNER JOIN STUDENT S ON(D.DEP_CODE = S.STUD_DEP);

--6. DEPART테이블과 STUDENT테이블을 왼쪽 외부조인. 모든 컬럼 포함
SELECT D.DEP_CODE, D.DEP_NAME, S.STUD_NO, S.STUD_NAME, S.STUD_DEP
--SELECT * 로 간단하게 쓸 수도 있다. 하지만 보안상 컬럼명을 써주는 것을 권장
FROM DEPART D, STUDENT S
WHERE D.DEP_CODE = S.STUD_DEP(+)
ORDER BY 1;

SELECT D.DEP_CODE, D.DEP_NAME, S.STUD_NO, S.STUD_NAME, S.STUD_DEP
FROM DEPART D LEFT OUTER JOIN STUDENT S
ON(D.DEP_CODE = S.STUD_DEP)
ORDER BY 1;

--7. DEPART테이블과 STUDENT테이블을 오른쪽 외부조인. 모든 컬럼 포함
SELECT *
FROM DEPART D, STUDENT S
WHERE D.DEP_CODE(+) = S.STUD_DEP;

SELECT *
FROM DEPART D RIGHT OUTER JOIN STUDENT S
ON(D.DEP_CODE = S.STUD_DEP);

--8. DEPART테이블과 STUDENT테이블을 완전 외부조인.
SELECT STUD_NO      학번
     , STUD_NAME    이름
     , DEP_CODE     반번호
     , DEP_NAME     반이름
FROM STUDENT, DEPART
WHERE STUD_DEP = DEP_CODE(+)
UNION
SELECT STUD_NO
     , STUD_NAME
     , DEP_CODE
     , DEP_NAME
FROM STUDENT, DEPART
WHERE STUD_DEP(+) = DEP_CODE;

SELECT STUD_NO      학번
     , STUD_NAME    이름
     , DEP_CODE     반번호
     , DEP_NAME     반이름
FROM DEPART FULL OUTER JOIN STUDENT ON(STUD_DEP = DEP_CODE);

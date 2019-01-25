
--P. 65
--상품분류정보테이블 생성
CREATE TABLE LPROD
(
 LPROD_ID   NUMBER(5)       NOT NULL,       -- 순번
 LPROD_GU   CHAR(4)         NOT NULL,       -- 상품분류코드
 LPROD_NM   VARCHAR2(40)    NOT NULL,       -- 상품분류명
 CONSTRAINT PK_LPROD PRIMARY KEY(LPROD_GU)
);

--거래처정보테이블 생성
CREATE TABLE BUYER
 (
 BUYER_ID       CHAR(6)         NOT NULL,   -- 거래처코드
 BUYER_NAME     VARCHAR2(40)    NOT NULL,   -- 거래처명
 BUYER_LGU      CHAR(4)         NOT NULL,   -- 취급상품 대분류
 BUYER_BANK     VARCHAR2(60),               -- 은행
 BUYER_BANKNO   VARCHAR2(60),               -- 계좌번호
 BUYER_BANKNAME VARCHAR2(15),               -- 예금주
 BUYER_ZIP      CHAR(7),                    -- 우편번호
 BUYER_ADD1     VARCHAR2(100),              -- 주소1
 BUYER_ADD2     VARCHAR2(70),               -- 주소2
 BUYER_COMTEL   VARCHAR2(14)    NOT NULL,   -- 전화번호
 BUYER_FAX      VARCHAR2(20)    NOT NULL    -- FAX번호
 );
 
 --BUYER 테이블의 구조 확인(DESCRIBE)
DESC BUYER;
 
/*
    ALTER TABLE BUYER
    ADD : 컬럼 추가
    MODIFY : 컬럼 수정
    DROP : 컬럼 삭제
    RENAME : 컬럼명 변경
*/

ALTER TABLE BUYER
ADD(
    BUYER_MAIL VARCHAR2(60) NOT NULL,
    BUYER_CHARGER VARCHAR2(20),
    BUYER_TELEXT VARCHAR2(2)
    );

COMMENT ON COLUMN BUYER.BUYER_MAIL IS 'E-MAIL';

DESC BUYER;

ALTER TABLE BUYER
MODIFY (BUYER_NAME VARCHAR2(60));

DESC BUYER;

--기본키 조건 : NO DUPLICATE, NOT NULL(중복불가 널값불허)
ALTER TABLE BUYER
ADD(CONSTRAINT PK_BUYER PRIMARY KEY(BUYER_ID));

ALTER TABLE BUYER
ADD(CONSTRAINT FR_BUYER_LPROD FOREIGN KEY(BUYER_LGU)
                        REFERENCES LPROD(LPROD_GU)
    );

CREATE TABLE PROD
(
PROD_ID             VARCHAR2(10)        NOT NULL,
PROD_NAME           VARCHAR2(40)        NOT NULL,
PROD_LGU            CHAR(4)             NOT NULL,
PROD_BUYER          CHAR(6)             NOT NULL,
PROD_COST           NUMBER(10)          NOT NULL,
PROD_PRICE          NUMBER(10)          NOT NULL,
PROD_SALE           NUMBER(10)          NOT NULL,
PROD_OUTLINE        VARCHAR2(100)       NOT NULL,
PROD_DETAIL         CLOB,
PROD_IMG            VARCHAR2(40)        NOT NULL,
PROD_TOTALSTOCK     NUMBER(10)          NOT NULL,
PROD_INSDATE        DATE,
PROD_PROPERSTOCK    NUMBER(10)          NOT NULL,
PROD_SIZE           VARCHAR2(20),
PROD_COLOR          VARCHAR2(20),
PROD_DELIVERY       VARCHAR2(255),
PROD_UNIT           VARCHAR2(6),
PROD_QTYIN          NUMBER(10),
PROD_GTYSALE        NUMBER(10),
PROD_MILEAGE        NUMBER(10),
Constraint pk_prod Primary Key (prod_id),
Constraint fr_prod_lprod Foreign Key (prod_lgu)
                                    References lprod(lprod_gu),
Constraint fr_prod_buyer Foreign Key (prod_buyer)
                                    References buyer(buyer_id)
);

CREATE TABLE BUYPROD
(
BUY_DATE        DATE            NOT NULL,
BUY_PROD        VARCHAR2(10)    NOT NULL,
BUY_QTY         NUMBER(10)      NOT NULL,
BUY_COST        NUMBER(10)      NOT NULL,
Constraint pk_buyprod Primary Key (buy_date,buy_prod),
Constraint fr_buyprod_prod Foreign Key (buy_prod) References prod(prod_id)
);

CREATE TABLE MEMBER
(
MEM_ID          VARCHAR2(15)        NOT NULL,
MEM_PASS        VARCHAR2(15)        NOT NULL,
MEM_NAME        VARCHAR2(20)        NOT NULL,
MEM_REGNO1      CHAR(6)             NOT NULL,
MEM_REGNO2      CHAR(7)             NOT NULL,
MEM_BIR         DATE,
MEM_ZIP         CHAR(7)             NOT NULL,
MEM_ADD1        VARCHAR2(100)       NOT NULL,
MEM_ADD2        VARCHAR2(80)        NOT NULL,
MEM_HOMETEL     VARCHAR2(14)        NOT NULL,
MEM_COMTEL      VARCHAR2(14)        NOT NULL,
MEM_HP          VARCHAR2(15),
MEM_MAIL        VARCHAR2(60)        NOT NULL,
MEM_JOB         VARCHAR2(40),
MEM_LIKE        VARCHAR2(40),
MEM_MEMORIAL    VARCHAR2(40),
MEM_MEMORIALDAY DATE,
MEM_MILEAGE     NUMBER(10),
MEM_DELETE      VARCHAR2(1),
Constraint pk_member Primary Key (mem_id)
);

CREATE TABLE CART
(
CART_MEMBER         VARCHAR2(15)        NOT NULL,
CART_NO             CHAR(13)            NOT NULL,
CART_PROD           VARCHAR2(10)        NOT NULL,
CART_QTY            NUMBER(8)           NOT NULL,
Constraint pk_cart Primary Key (cart_no,cart_prod),
Constraint fr_cart_member Foreign Key (cart_member)
                                        References member(mem_id),
Constraint fr_cart_prod Foreign Key (cart_prod)
                                        References prod(prod_id)
);

CREATE TABLE ZIPTB
(
ZIPCODE         CHAR(7)             NOT NULL,
SIDO            VARCHAR2(2 CHAR)    NOT NULL,
GUGUN           VARCHAR2(10 CHAR)   NOT NULL,
DONG            VARCHAR2(30 CHAR)   NOT NULL,
BUNJI           VARCHAR2(10 CHAR),
SEQ             NUMBER(5)           NOT NULL
);

CREATE INDEX idx_ziptb_zipcode ON ziptb(zipcode);

----------------------DB 구현 끝----------------------------
--P.17
INSERT INTO LPROD (LPROD_ID,LPROD_GU,LPROD_NM) VALUES (1, 'P101', '컴퓨터제품');
INSERT INTO LPROD (LPROD_ID,LPROD_GU,LPROD_NM) VALUES (2, 'P102', '전자제품');
INSERT INTO LPROD (LPROD_ID,LPROD_GU,LPROD_NM) VALUES (3, 'P201', '여성캐주얼');
INSERT INTO LPROD (LPROD_ID,LPROD_GU,LPROD_NM) VALUES (4, 'P202', '남성캐주얼');
INSERT INTO LPROD (LPROD_ID,LPROD_GU,LPROD_NM) VALUES (5, 'P301', '피혁잡화');
INSERT INTO LPROD (LPROD_ID,LPROD_GU,LPROD_NM) VALUES (6, 'P302', '화장품');
INSERT INTO LPROD (LPROD_ID,LPROD_GU,LPROD_NM) VALUES (7, 'P401', '음반/CD');
INSERT INTO LPROD (LPROD_ID,LPROD_GU,LPROD_NM) VALUES (8, 'P402', '도서');
INSERT INTO LPROD (LPROD_ID,LPROD_GU,LPROD_NM) VALUES (9, 'P403', '문구류');

SELECT * FROM LPROD;

--ROLLBACK;       --위에서 INSERT한 데이터가 날아간다. 돌아간 상태에서 트랜잭션 종료
COMMIT;           --메모리에 있던 데이터가 HDD로 들어감. 저장.

SELECT LPROD_ID
     , LPROD_GU
     , LPROD_NM
FROM LPROD;

SELECT LPROD_GU
     , LPROD_NM
FROM LPROD;

SELECT LPROD_GU
     , LPROD_NM
FROM LPROD
WHERE LPROD_GU < 'P401';

--LPROD_ID가 3인 ROW를 SELECT하시오
--LPROD ID, GU, NM 컬럼을 모두 출력
SELECT LPROD_ID
     , LPROD_GU
     , LPROD_NM
FROM LPROD
WHERE LPROD_ID = '3';

--p.19
--업데이트 전 검증 절차. 업데이트할 곳에 뭐가 있는가?
SELECT *
FROM LPROD
WHERE LPROD_GU = 'P102';
--업데이트 쎄대여 로 외우기
UPDATE LPROD
SET    LPROD_NM = '향수'
WHERE  LPROD_GU = 'P102';

ROLLBACK;

SELECT * FROM LPROD;

--LPROD 테이블을 LPROD2 테이블로 복사(이동이 아님)
CREATE TABLE LPROD2
AS
SELECT * FROM LPROD;

--LPROD2 테이블의 LPROD_GU가 P202인 LPROD-NM을
--남성 캐쥬얼에서 도서류로 UPDATE하세요
SELECT *
FROM    LPROD2
WHERE   LPROD_GU = 'P202';

UPDATE  LPROD2
SET     LPROD_NM = '도서류'
WHERE   LPROD_GU = 'P202'; -- 기본키로 지정하는 것이 좋다. 아니면 중복지정되어 원치 않는 UPDATE가 있을 수 있다

--LPROD2 테이블에서 LPROD_ID가 7인
--LPROD_GU를 P401에서 P303으로 UPDATE 하시오
SELECT *
FROM LPROD2
WHERE LPROD_ID = '7';

UPDATE LPROD2
SET LPROD_GU = 'P303'
WHERE LPROD_ID = '7';

--LPROD2 테이블에서 
--데이터를 삭제하시오.(row가 삭제됨)
SELECT *
FROM    LPROD2
WHERE   LPROD_NM = '화장품';

--등푸른생선 주세여 로 외우기
DELETE FROM LPROD2
WHERE LPROD_NM = '화장품';

--테이블의 모든 row와 colomn을 검색
select * from LPROD;

--상품 테이블로부터 모든 row와 colomn을 검색하시오
SELECT * FROM prod;

--회원 테이블로부터 모든 row와 colomn을 검색하시오
--아스테리크
SELECT * FROM MEMBER;

--장바구니 테이블로부터 모든 row와 colomn을 검색하시오
SELECT * FROM CART;

--buyer 테이블을 buyer2 테이블로 복사하시오
CREATE TABLE BUYER2
AS
SELECT * FROM BUYER;

--BUYER2 테이블의 BUYER_ID, BUYER_NAME, BUYER_LGU 컬럼을 모두 SELECT하시오
SELECT BUYER_ID, BUYER_NAME, BUYER_LGU FROM BUYER2;

--BUYER2 테이블의 BUYER_ID가 P30203인 BUYER_NAME값을 거성으로 UPDATE하시오
SELECT *
FROM BUYER2
WHERE BUYER_ID = 'P30203';

UPDATE BUYER2
SET BUYER_NAME = '거성'
WHERE BUYER_ID = 'P30203';

--BUYER2 테이블의 BUYER_NAME이 피리어스인 ROW를 삭제하시오
SELECT *
FROM BUYER2
WHERE BUYER_NAME = '피리어스';

DELETE FROM BUYER2
WHERE BUYER_NAME = '피리어스';
/*
select prod_id, prod_name from prod;
-- Select 구문
Select 절 : 컬럼, 상수, 식, 함수
from 절 : 테이블, 서브쿼리
where 절 : 조건
Group by 절 : 그룹핑
having 절 : (그룹 함수) 조건
order by 절 : 정렬;
*/

SELECT MEM_ID
    , 1004
    , '내일이 지나면..'   --별도 문자를 결과에 추가한 것
    , MEM_NAME
    , MEM_MILEAGE
    , MEM_MILEAGE / 12 AS "월 평균"    --결과값에 나올 이름을 따로 지정
FROM MEMBER;

--회원 테이블로부터 회원ID와 성명과 마일리지 * 12를 검색하시오
SELECT MEM_ID
    ,MEM_NAME
    ,MEM_MILEAGE * 12
FROM MEMBER;

--상품 테이블(PROD)로부터 상품코드와 상품명을 검색하시오
SELECT PROD_ID, PROD_NAME FROM PROD;
--장바구니 테이블로부터 주문번호, 상품코드, 회원ID, 수량을 검색하시오
SELECT CART_NO, CART_PROD, CART_MEMBER, CART_QTY FROM CART;
--상품 테이블(PROD)의 상품코드, 상품명, 판매금액을 검색하시오
--판매금액은 = 판매단가 * 55로 계산한다.
--상품코드(PROD_ID), 상품명(PROD_NAME), 판매단가(PROD_SALE)
SELECT PROD_ID, PROD_NAME, PROD_SALE * 55 FROM PROD;

--상품테이블에서 prod_id, prod_name, prod_buyer를 검색하시오.
--단, Colomn Alias는 상품코드, 상품명, 거래처코드로 정의하시오
SELECT PROD_ID      상품코드
    , PROD_NAME     상품명
    , PROD_BUYER    거래처코드
FROM PROD;

SELECT PROD_LGU     상품분류
FROM PROD;
--위에서 중복 ROW의 제거. SELECT결과값의 중복을 없애고 UNIQUE하게 검색
SELECT DISTINCT PROD_LGU    상품분류
FROM PROD;

--상품 테이블의 거래처코드를 중복되지 않게 검색하시오(Alias는 거래처)
SELECT DISTINCT PROD_BUYER  거래처
FROM PROD;
--회원테이블에서 ID, 회원명, 생일, 마일리지 검색
--ASC(오름차순) : ASCENDING, DESC(내림차순) : DESCENDING
SELECT MEM_ID       회원ID
     , MEM_NAME     성명
     , MEM_BIR      생일
     , MEM_MILEAGE  마일리지
FROM MEMBER
ORDER BY 성명 DESC;

--컬럼순서를 통해 정렬
SELECT MEM_ID
    , MEM_NAME
    , MEM_BIR
    , MEM_MILEAGE
FROM MEMBER
ORDER BY 3 DESC;

--2차정렬
SELECT MEM_ID
    , MEM_NAME
    , MEM_BIR
    , MEM_MILEAGE
FROM MEMBER
ORDER BY MEM_MILEAGE, 1 ASC;
--그냥 MILEAGE로만 정렬할 때 같은 값은?
--컬럼순서를 지정해 (여기서는 1번:MEM_ID)2차정렬 기준을 부여할 수 있다.
--뒤 ASC는 생략가능(ASC가 기본값). 이 때 MEM_ID의 알파벳과 001은 분리된 것이 아닌 하나의 데이터. 숫자순으로는 정렬할 수 없다

SELECT DISTINCT CART_MEMBER     회원ID
    , CART_PROD 상품코드
FROM CART
ORDER BY 1, 2;
--    회원 아이디로 오름차순 정렬을 1차 시행.
--    2차로 상품코드를 기준으로 한번 더 정렬한 것.

--회원테이블(MEMBER)에서
--MEM_ID(회원ID), MEM_JOB(직업), MEM_LIKE(취미)를 검색하기
--직업으로 오름차순, 취미로 내림차순, 회원ID로 오름차순 정렬

SELECT MEM_ID       회원ID
    , MEM_JOB       직업
    , MEM_LIKE      취미
FROM MEMBER
ORDER BY MEM_JOB ASC, MEM_LIKE DESC, MEM_ID ASC; -- MEM_JOB대신 ALIAS,
--SELECT에서 지정한 컬럼순서도 가능

CREATE TABLE MEMBER2
AS
SELECT * FROM MEMBER;

--직업(MEM_JOB)이 회사원인 회원의 MEM_MEMORIAL 컬럼의 데이터를 NULL로 수정하기
--(단, COMMIT은 하지 않음)
--** MEM_MEMORIAL = NULL
--** 조건검색 시 ''를 사용함

SELECT *
FROM MEMBER2
WHERE MEM_JOB = '회사원';

UPDATE MEMBER2
SET MEM_MEMORIAL = NULL
WHERE MEM_JOB = '회사원';

COMMIT;

SELECT MEM_MEMORIAL, MEM_ID
FROM MEMBER
ORDER BY MEM_MEMORIAL DESC;
--NULL값이 있는 데이터에서 오름차순 정렬은 NULL이 마지막. 내림차순 정렬은 NULL이 처음.


--상품 테이블(PROD)의 전체 컬럼 검색
--판매가(PROD_SALE)로 내림차순 후, 상품분류코드(PROD_LGU)로 오름차순 후 상품명(PROD_NAME)으로 오름차순 정렬

SELECT * FROM PROD
ORDER BY PROD_SALE DESC, PROD_LGU, PROD_NAME;

--비교연산(WHERE 절)
--같다를 표시할 때 자바는 ==지만 오라클은 =다
--생소한 표현? A <> B 는 A != B 와 같다


--상품 중 판매가가 170,000원인 상품 조회(ALIAS : 상품명, 판매가)
SELECT PROD_NAME    상품명     --대기업 SI에서 이렇게 왼쪽 떼고 컬럼명에 맞추는 방식을 기준으로 쓰기도 함
     , PROD_SALE    판매가
  FROM PROD
 WHERE PROD_SALE = 170000;

--상품 중 판매가가 170,000원이 아닌 상품 조회
SELECT PROD_NAME    상품명
     , PROD_SALE    판매가
  FROM PROD
 WHERE PROD_SALE <> 170000;
--170,000이하인 상품
SELECT PROD_NAME    상품명
     , PROD_SALE    판매가
  FROM PROD
 WHERE PROD_SALE <= 170000;
--170,000이상인 상품
SELECT PROD_NAME    상품명
     , PROD_SALE    판매가
 FROM PROD
 WHERE PROD_SALE >= 170000;
 
--책 중간 21P 상품중 매입가 200000이하 검색
SELECT PROD_ID      상품코드
     , PROD_NAME     상품명
     , PROD_COST     매입가
FROM PROD
WHERE PROD_COST <= 200000;

--21P 76년 1월 1일 이후에...
SELECT MEM_ID       회원ID
     , MEM_NAME     회원명
     , MEM_REGNO1   주민등록번호앞자리
FROM MEMBER
WHERE MEM_REGNO1 >= '760101';       --''쓴 이유? MEM_REGNO1이 문자형(CHAR)이기 때문.
--안 써도 되긴 되는 이유? 문자와 숫자가 만나면 우선순위가 있는데,
--숫자가 우선이라 문자가 숫자로 자동 형변환된다. 그래서 숫자-숫자간 비교해서 같은 결과가 나옴.

--논리연산
--NOT(조건1): 조건1이 아닌 경우 참
--우선순위: (), NOT, AND, OR    낫앤더워로 외우기

--21P 분류가P201이고...
SELECT PROD_NAME    상품
     , PROD_LGU      상품분류
     , PROD_SALE     판매가
FROM PROD
WHERE PROD_LGU = 'P201'
  AND PROD_SALE=170000;

--22P 상품 중 상품분류가 P201(여성 캐주얼)이거나 판매가가 170,000원인 상품 조회
--ALIAS는 상품, 상품분류, 판매가

SELECT PROD_NAME    상품
     , PROD_LGU      상품분류
     , PROD_SALE     판매가
FROM PROD
WHERE PROD_LGU = 'P201'
   OR PROD_SALE=170000;
   
--상품 중 상품분류가 P201(여성 캐주얼)도 아니고 판매가가 170,000원도 아닌 상품 조회
--ALIAS는 상품, 상품분류, 판매가

SELECT PROD_NAME     상품
     , PROD_LGU      상품분류
     , PROD_SALE     판매가
FROM PROD
WHERE NOT(PROD_LGU = 'P201'
OR PROD_SALE = 170000);
--WHERE PROD_LGU != 'P201'
--AND PROD_SALE != 170000;

--참고: 드모르간 정리 not(a or b) = not a and not b

--22P 판매가가 300,000이상...
SELECT PROD_ID      상품코드
     , PROD_NAME    상품명
     , PROD_SALE    판매가
FROM    PROD
WHERE   PROD_SALE BETWEEN 300000 AND 500000;
--WHERE   PROD_SALE >= 300000
--AND     PROD_SALE <= 500000;

--22P 회원 중 생일이 1975...
SELECT MEM_ID        회원ID
     , MEM_NAME      회원명
     , MEM_BIR       생일
FROM MEMBER
WHERE MEM_BIR BETWEEN '75/01/01' AND '76/12/31';    --이 때도 자동 형변환.
--'1975-01-01'도 가능. 날짜 형식의 문자면 날짜형으로 자동 형변환됨.

DESC MEMBER;        --MEMBER테이블의 데이터형 조회

--WHERE절 기타 연산자
--IN(찾을 대상 중 포함하는 것. 여러 개)

--22P 상품 중 판매가가 150,000원 또는 170,000원 또는 330,000원인 상품 조회
SELECT PROD_NAME    상품명
     , PROD_SALE    판매가
FROM   PROD
WHERE  PROD_SALE IN(150000,170000,330000);
--WHERE  PROD_SALE=150000
--OR     PROD_SALE=170000
--OR     PROD_SALE=330000;

--22P 회원테이블에서 회원ID가 c001, f001, w001인 회원만 검색
SELECT MEM_ID   회원ID
     , MEM_NAME  회원명
FROM   MEMBER
WHERE MEM_ID IN('c001','f001','w001');

--22P 상품 분류테이블에서 현재 상품테이블에 존재하는 분류만 검색(분류코드, 분류명)
SELECT LPROD_GU     분류코드
     , LPROD_NM     분류명
FROM LPROD
WHERE LPROD_GU IN(SELECT DISTINCT PROD_LGU FROM PROD);
--IN('P101','P102','P201','P202','P301','P302')를 대신해서 위 문장 삽입;

--상품 분류테이블에서 현재 상품테이블에 존재하지 않는 분류만 검색
SELECT LPROD_GU     분류코드
     , LPROD_NM     분류명
FROM LPROD
WHERE LPROD_GU NOT IN(SELECT DISTINCT PROD_LGU FROM PROD);

--거래처 테이블(BUYER)에서 현재 상품테이블(PROD)에 존재하는 거래처(PROD_BUYER)만 검색하시오
--ALIAS : 거래처ID(BUYER_ID), 거래처명(BUYER_NAME)

SELECT BUYER_ID     거래처ID
     , BUYER_NAME   거래처명
FROM BUYER
WHERE BUYER_ID IN(SELECT DISTINCT PROD_BUYER FROM PROD);
--  QUERY 안에 또 다른 SELECT문 형태 : 서브쿼리
--  서브쿼리 바깥 쿼리는 메인쿼리.

--22P 상품 중 매입가(PROD_COST)가 30만~150만이고 판매가(PROD_SALE)가 80만~200만인 상품을 검색하시오
--ALIAS는 상품명(PROD_NAME), 매입가(PROD_COST), 판매가(PROD_SALE)

SELECT PROD_NAME    상품명
     , PROD_COST    매입가
     , PROD_SALE    판매가
FROM PROD
WHERE (PROD_COST BETWEEN 300000 AND 1500000)
AND (PROD_SALE BETWEEN 800000 AND 2000000);

--LIKE 
--와일드 카드 사용('_' : 한 글자, '%' : 여러 글자)

SELECT PROD_ID      상품코드
     , PROD_NAME    상품명
FROM PROD
WHERE PROD_NAME LIKE '삼%';
--'삼'으로 시작하는 것을 찾아라

SELECT PROD_ID      상품코드
     , PROD_NAME    상품명
FROM PROD
WHERE PROD_NAME LIKE '_성%'; 
-- 첫번째는 뭐든 한 글자, 두번째는 '성'인 것을 찾아라

-- '치'자로 끝나는 상품 정보를 검색하시오
--ALIAS: 상품코드, 상품명
SELECT PROD_ID      상품코드
     , PROD_NAME    상품명
FROM PROD
WHERE PROD_NAME LIKE '%치';
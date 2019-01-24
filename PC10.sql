
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

CREATE TABLE prod
(
prod_id             varchar2(10)        NOT NULL,
prod_name           varchar2(40)        NOT NULL,
prod_lgu            char(4)             NOT NULL,
prod_buyer          char(6)             NOT NULL,
prod_cost           number(10)          NOT NULL,
prod_price          number(10)          NOT NULL,
prod_sale           number(10)          NOT NULL,
prod_outline        varchar2(100)       NOT NULL,
prod_detail         clob,
prod_img            varchar2(40)        NOT NULL,
prod_totalstock     number(10)          NOT NULL,
prod_insdate        date,
prod_properstock    number(10)          NOT NULL,
prod_size           varchar2(20),
prod_color          varchar2(20),
prod_delivery       varchar2(255),
prod_unit           varchar2(6),
prod_qtyin          number(10),
prod_gtysale        number(10),
prod_mileage        number(10),
Constraint pk_prod Primary Key (prod_id),
Constraint fr_prod_lprod Foreign Key (prod_lgu)
                                    References lprod(lprod_gu),
Constraint fr_prod_buyer Foreign Key (prod_buyer)
                                    References buyer(buyer_id)
);

CREATE TABLE buyprod
(
buy_date        date            NOT NULL,
buy_prod        varchar2(10)    NOT NULL,
buy_qty         number(10)      NOT NULL,
buy_cost        number(10)      NOT NULL,
Constraint pk_buyprod Primary Key (buy_date,buy_prod),
Constraint fr_buyprod_prod Foreign Key (buy_prod) References prod(prod_id)
);

CREATE TABLE member
(
mem_id          varchar2(15)        NOT NULL,
mem_pass        varchar2(15)        NOT NULL,
mem_name        varchar2(20)        NOT NULL,
mem_regno1      char(6)             NOT NULL,
mem_regno2      char(7)             NOT NULL,
mem_bir         date,
mem_zip         char(7)             NOT NULL,
mem_add1        varchar2(100)       NOT NULL,
mem_add2        varchar2(80)        NOT NULL,
mem_hometel     varchar2(14)        NOT NULL,
mem_comtel      varchar2(14)        NOT NULL,
mem_hp          varchar2(15),
mem_mail        varchar2(60)        NOT NULL,
mem_job         varchar2(40),
mem_like        varchar2(40),
mem_memorial    varchar2(40),
mem_memorialday date,
mem_mileage     number(10),
mem_delete      varchar2(1),
Constraint pk_member Primary Key (mem_id)
);

CREATE TABLE cart
(
cart_member         varchar2(15)        NOT NULL,
cart_no             char(13)            NOT NULL,
cart_prod           varchar2(10)        NOT NULL,
cart_qty            number(8)           NOT NULL,
Constraint pk_cart Primary Key (cart_no,cart_prod),
Constraint fr_cart_member Foreign Key (cart_member)
                                        References member(mem_id),
Constraint fr_cart_prod Foreign Key (cart_prod)
                                        References prod(prod_id)
);

CREATE TABLE ziptb
(
zipcode         char(7)             NOT NULL,
sido            varchar2(2 char)    NOT NULL,
gugun           varchar2(10 char)   NOT NULL,
dong            varchar2(30 char)   NOT NULL,
bunji           varchar2(10 char),
seq             number(5)           NOT NULL
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
--업데이트 쎄대여
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

--등푸른생선 주세여
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
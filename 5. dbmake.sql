
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
--BUYER 테이블에 컬럼을 추가한다

COMMENT ON COLUMN BUYER.BUYER_MAIL IS 'E-MAIL';
--BUYER 테이블 BUYER_MAIL 옆에 주석이 추가됨

DESC BUYER;

ALTER TABLE BUYER
MODIFY (BUYER_NAME VARCHAR2(60));
--ppt 145p 교재중간 10-11p BUYER_NAME 크기를 60바이트로 수정
--이처럼 ALTER는 구조 변경에 사용됨

DESC BUYER; -- 묘사하다(DESCRIBE), 설명하다. 실행하면 해당 테이블 출력

--기본키 조건 : NO DUPLICATE, NOT NULL(중복불가 널값불허)
ALTER TABLE BUYER
ADD(CONSTRAINT PK_BUYER PRIMARY KEY(BUYER_ID));
--PPT 145p
--제약사항	테이블을 지목 	BUYER_ID를 기본키로 잡겠다
--기본키는 테이블마다 있다. 그런데 이름이 없다면? 어느게 어느건지 애매하다.
--이름을 붙여 다른 기본키와 구분하기 위해 제약사항 앞부분에 이름이 붙는것.
--NOT NULL도 CONSTRAINT(제약사항). 필수(mandatory)

ALTER TABLE BUYER
ADD(CONSTRAINT FR_BUYER_LPROD FOREIGN KEY(BUYER_LGU)
                        REFERENCES LPROD(LPROD_GU)
    );
--buyer 자식의 buyer_lgu가 lprod 부모의 기본키를 참조한다
--lprod부모의 기본키가 buyer 자식의 buyer_lgu로 전이된다
--ERwin에서 까마귀발로 이어주기(1대다 관계라면 1부터)

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
--prod_lgu가 lprod_gu를 참조한다		LPROD 1 : 다 PROD	LPROD 부모 - 자식 PROD
Constraint fr_prod_buyer Foreign Key (prod_buyer)
                                    References buyer(buyer_id)
--prod_buyer가 buyer_id를 참조한다	BUYER 1 : 다 PROD	BUYER 부모 - 자식 PROD
);

CREATE TABLE BUYPROD
(
BUY_DATE        DATE            NOT NULL,
BUY_PROD        VARCHAR2(10)    NOT NULL,
BUY_QTY         NUMBER(10)      NOT NULL,
BUY_COST        NUMBER(10)      NOT NULL,
Constraint pk_buyprod Primary Key (buy_date,buy_prod),
--buy_date, buy_prod는 식별영역, 기준키
Constraint fr_buyprod_prod Foreign Key (buy_prod) References prod(prod_id)
--buy_prod컬럼이 참조한다. prod테이블의 prod_id컬럼을.   buyprod는 자식, prod는 부모
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
--member테이블은 부모. 외래키가 없다(이 구조에서는). 그래서 primary key만 설정했다
);

CREATE TABLE CART
(
CART_MEMBER         VARCHAR2(15)        NOT NULL,
CART_NO             CHAR(13)            NOT NULL,
CART_PROD           VARCHAR2(10)        NOT NULL,
CART_QTY            NUMBER(8)           NOT NULL,
Constraint pk_cart Primary Key (cart_no,cart_prod), -- 기본키
Constraint fr_cart_member Foreign Key (cart_member)
                                        References member(mem_id),
--cart_member는 외래키. mem_id를 참조한다.
Constraint fr_cart_prod Foreign Key (cart_prod)
                                        References prod(prod_id)
--cart_prod는 외래키. prod_id를 참조한다.
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
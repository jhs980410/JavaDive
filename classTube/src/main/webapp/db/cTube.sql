-- MEMBER 테이블 DROP
DROP TABLE MEMBER CASCADE CONSTRAINTS;

-- MEMBER 테이블
CREATE TABLE MEMBER (
    MEMBER_NO NUMBER(5) NOT NULL,
    MEMBER_EMAIL VARCHAR2(100) NOT NULL,
    MEMBER_PWD VARCHAR2(100) NOT NULL,
    MEMBER_NAME VARCHAR2(100) NOT NULL,
    RRN VARCHAR2(15) NOT NULL,
    TEL VARCHAR2(15) NOT NULL,
    MEMBER_PRIV VARCHAR2(30) DEFAULT 'MEMBER' NOT NULL,
    CREATE_AT DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT PK_MEMBER PRIMARY KEY (MEMBER_NO)
);
--RRN 'resident registration number' 주민등록번호
--priv는 (privilege 권한), 약어

-- MEMBER_REPORT 테이블 DROP
DROP TABLE MEMBER_REPORT CASCADE CONSTRAINTS;

-- MEMBER_REPORT 테이블
CREATE TABLE MEMBER_REPORT (
    MEMBER_NO NUMBER(3) NOT NULL,
    REPORT_TIME NUMBER(3) NOT NULL,
    REPORTED_TIME NUMBER(3) NOT NULL,
    CONSTRAINT PK_MEMBER_REPORT PRIMARY KEY (MEMBER_NO),
    CONSTRAINT FK_MEMBER_TO_MEMBER_REPORT_1 FOREIGN KEY (MEMBER_NO) REFERENCES MEMBER (MEMBER_NO)
);

-- CLASS_CATEGORY 테이블 DROP
DROP TABLE CLASS_CATEGORY CASCADE CONSTRAINTS;

-- CLASS_CATEGORY 테이블
CREATE TABLE CLASS_CATEGORY (
    CATEGORY_NO NUMBER(5) NOT NULL,
    CATEGORY_NAME VARCHAR2(100) NOT NULL,
    CONSTRAINT PK_CLASS_CATEGORY PRIMARY KEY (CATEGORY_NO)
);


-- ODCLASS 테이블 DROP
DROP TABLE ODCLASS CASCADE CONSTRAINTS;

-- ODCLASS 테이블
CREATE TABLE ODCLASS (
    CLASS_NO NUMBER(3) NOT NULL,
    CLASS_NAME VARCHAR2(100) NOT NULL,
    PRICE NUMBER(6) NOT NULL,
    CLASS_DESC VARCHAR2(1000) NOT NULL,
    INSTRUCTOR VARCHAR2(100) NOT NULL,
    CREATED_AT DATE DEFAULT SYSDATE NOT NULL,
    VIEWS NUMBER(5) DEFAULT 0 NOT NULL ,
    CLASS_LIMIT NUMBER(2) NOT NULL,
    IMG VARCHAR2(100),
    REGION VARCHAR2(100) NOT NULL,
    CATEGORY_NO NUMBER(5) NOT NULL,
    CONSTRAINT PK_ODCLASS PRIMARY KEY (CLASS_NO)
);

-- BOARD_CATEGORY 테이블 DROP
DROP TABLE BOARD_CATEGORY CASCADE CONSTRAINTS;

-- BOARD_CATEGORY 테이블
CREATE TABLE BOARD_CATEGORY (
    CATEGORY_NO NUMBER(3) NOT NULL,
    CATEGORY_NAME VARCHAR2(50) NOT NULL,
    CONSTRAINT PK_BOARD_CATEGORY PRIMARY KEY (CATEGORY_NO)
);

-- NOTE 테이블 DROP
DROP TABLE NOTE CASCADE CONSTRAINTS;

-- NOTE 테이블
CREATE TABLE NOTE (
    NOTE_NO NUMBER(5) NOT NULL,
    NOTE_TITLE VARCHAR2(100) NOT NULL,
    MEMBER_NO NUMBER(5) NOT NULL,
    CREATE_AT DATE DEFAULT SYSDATE NOT NULL,
    MODIFY_AT DATE DEFAULT SYSDATE NOT NULL,
    NOTE_CONTENT VARCHAR2(1000) NOT NULL,
    CATEGORY_NO NUMBER(3) NOT NULL,
    CONSTRAINT PK_NOTE PRIMARY KEY (NOTE_NO)
);

-- COMMENT 테이블 DROP
DROP TABLE NOTE_COMMENT CASCADE CONSTRAINTS;

-- COMMENT 테이블
CREATE TABLE NOTE_COMMENT (
    COMMENT_NO NUMBER(5) NOT NULL,
    COMMENT_WRITER VARCHAR2(100) NOT NULL,
    COMMENT_CONTENT VARCHAR2(1000) NOT NULL,
    CREATE_AT DATE DEFAULT SYSDATE NOT NULL,
    MODIFY_AT DATE,
    NOTE_NO NUMBER(5) NOT NULL,
    CONSTRAINT PK_COMMENT PRIMARY KEY (COMMENT_NO),
    CONSTRAINT FK_NOTE_TO_COMMENT FOREIGN KEY (NOTE_NO) REFERENCES NOTE (NOTE_NO)
);

-- REPORT 테이블 DROP
DROP TABLE MEMBER_REPORT CASCADE CONSTRAINTS;

-- REPORT 테이블
CREATE TABLE MEMBER_REPORT (
    REPORT_NO NUMBER(3) NOT NULL,
    REPORT_MNO NUMBER(5) NOT NULL,
    REPORT_TYPE VARCHAR2(100) NOT NULL,
    REPORT_OBJECT_EMAIL VARCHAR2(100) NOT NULL,
    REPORT_OBJECT_TYPE VARCHAR2(100) NOT NULL,
    REPORT_DATE DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT PK_REPORT PRIMARY KEY (REPORT_NO)
    
);

COMMIT;
>>>>>>> feat-jcm

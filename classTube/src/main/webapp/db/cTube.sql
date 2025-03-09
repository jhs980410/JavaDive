DROP TABLE MEMBER;
DROP SEQUENCE MEMBER_SEQ;

CREATE TABLE MEMBER (
	MEMBER_NO NUMBER(5)	NOT NULL PRIMARY KEY,
	MEMBER_EMAIL VARCHAR2(100) NOT NULL,
	MEMBER_PWD VARCHAR2(100) NOT NULL,
	MEMBER_NAME VARCHAR2(100) NOT NULL,
	RRN VARCHAR2(100) NOT NULL,
	TEL VARCHAR2(100) NOT NULL,
	PRIV VARCHAR2(30) NOT NULL
);

ALTER TABLE MEMBER MODIFY (PRIV DEFAULT 'user');

--RRN number(13) NOT NULL COMMENT 'resident registration number' 주민등록번호
--priv는 (privilege 권한), 약어

desc MEMBER;

CREATE SEQUENCE MEMBER_SEQ
INCREMENT BY 1
START WITH 1;
--MEMBER_NO의 고유 인덱스

INSERT INTO MEMBER
VALUE(MEMBER_NO, MEMBER_EMAIL, MEMBER_PWD, MEMBER_NAME, RRN, TEL, PRIV)
VALUES(MEMBER_SEQ.NEXTVAL, 'ctube@gmail.com', '1234', '클튜브', '000101-3000000', '010-0000-0000', 'admin');

COMMIT;



CREATE TABLE category (
    category_no NUMBER(3) NOT NULL PRIMARY KEY,   -- 카테고리 번호 (PK)
    category_name VARCHAR2(50) NOT NULL UNIQUE    -- 카테고리 이름 (유니크)
);


CREATE TABLE note (
    note_no NUMBER(5) NOT NULL PRIMARY KEY,         -- 게시글 번호 (PK)
    note_title VARCHAR2(100) NOT NULL,              -- 제목
    note_writer VARCHAR2(100) NOT NULL,             -- 작성자 (user_email)
    create_at DATE DEFAULT SYSDATE NOT NULL,        -- 작성일
    modify_at DATE,                                 -- 수정일
    content VARCHAR2(1000) NOT NULL,                -- 글 내용
    category VARCHAR2(100) NOT NULL,                -- 글 카테고리
    category_no NUMBER(3) NOT NULL,                 -- 카테고리 번호 (FK)
    CONSTRAINT FK_NOTE_CATEGORY FOREIGN KEY (category_no) REFERENCES category(category_no) ON DELETE CASCADE
);
CREATE TABLE comment (
    comment_no NUMBER(5) NOT NULL PRIMARY KEY,    -- 댓글 번호 (PK)
    comment_writer VARCHAR2(100) NOT NULL,        -- 댓글 작성자
    comment_content VARCHAR2(1000) NOT NULL,      -- 댓글 내용
    create_at DATE DEFAULT SYSDATE NOT NULL,      -- 작성일
    modify_at DATE,                               -- 수정일
    note_no NUMBER(5) NOT NULL,                   -- 게시글 번호 (FK)
    CONSTRAINT FK_COMMENT_NOTE FOREIGN KEY (note_no) REFERENCES note(note_no) ON DELETE CASCADE
);


-- 게시글 번호 자동 증가 시퀀스
CREATE SEQUENCE note_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- 댓글 번호 자동 증가 시퀀스
CREATE SEQUENCE comment_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- 카테고리 번호 자동 증가 시퀀스
CREATE SEQUENCE category_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;


-- 카테고리 삽입
INSERT INTO category (category_no, category_name) 
VALUES (category_seq.NEXTVAL, '공지사항');

INSERT INTO category (category_no, category_name) 
VALUES (category_seq.NEXTVAL, '자유 게시판');

-- 게시글 삽입
INSERT INTO note (note_no, note_title, note_writer, create_at, content, category, category_no)
VALUES (note_seq.NEXTVAL, '첫 번째 공지', 'admin@gmail.com', SYSDATE, '공지사항 게시판입니다.', '공지사항', 1);

INSERT INTO note (note_no, note_title, note_writer, create_at, content, category, category_no)
VALUES (note_seq.NEXTVAL, '자유게시판 첫 글', 'user1@gmail.com', SYSDATE, '자유롭게 글을 작성하세요!', '자유 게시판', 2);

-- 댓글 삽입
INSERT INTO comment (comment_no, comment_writer, comment_content, create_at, note_no)
VALUES (comment_seq.NEXTVAL, 'user2@gmail.com', '좋은 글 감사합니다!', SYSDATE, 1);

INSERT INTO comment (comment_no, comment_writer, comment_content, create_at, note_no)
VALUES (comment_seq.NEXTVAL, 'user3@gmail.com', '자유게시판 환영합니다!', SYSDATE, 2);

COMMIT;






INSERT INTO MEMBER
VALUE(MEMBER_NO, MEMBER_EMAIL, MEMBER_PWD, MEMBER_NAME, RRN, TEL, MEMBER_PRIV, CREATE_AT)
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'ctube@gmail.com', '1234', '클튜브', '000101-3000000', '010-0000-0000', 'ADMIN', SYSDATE);

INSERT INTO MEMBER
VALUE(MEMBER_NO, MEMBER_EMAIL, MEMBER_PWD, MEMBER_NAME, RRN, TEL, MEMBER_PRIV, CREATE_AT)
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01@gmail.com', '1234', '가나다', '000101-3333333', '010-1111-1111', 'USER', SYSDATE);

INSERT INTO MEMBER
VALUE(MEMBER_NO, MEMBER_EMAIL, MEMBER_PWD, MEMBER_NAME, RRN, TEL, MEMBER_PRIV, CREATE_AT)
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user02@gmail.com', '1234', '나다라', '000101-4000000', '010-2222-2222', 'USER', SYSDATE);

INSERT INTO MEMBER
VALUE(MEMBER_NO, MEMBER_EMAIL, MEMBER_PWD, MEMBER_NAME, RRN, TEL, MEMBER_PRIV, CREATE_AT)
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user03@gmail.com', '1234', '다라마', '000101-4444444', '010-3333-3333', 'USER', SYSDATE);

SELECT * 
FROM MEMBER;

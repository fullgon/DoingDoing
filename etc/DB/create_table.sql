-- CREATE DATABASE doingdoing;
USE doingdoing;

CREATE TABLE tb_user (
	USER_ID varchar(20) PRIMARY KEY COMMENT '사용자 아이디', 
	NAME varchar(10) NOT NULL COMMENT '이름', 
	EMAIL varchar(50) NOT NULL COMMENT '이메일', 
	COMPANY varchar(50)  COMMENT '직장'
);

ALTER TABLE tb_user COMMENT ='사용자 정보';

CREATE TABLE tb_auth (
	USER_ID varchar(20) PRIMARY KEY COMMENT '사용자 아이디', 
	PASSWORD varchar(255) NOT NULL COMMENT '비밀번호',
    
    FOREIGN KEY (USER_ID) references tb_user(USER_ID)
);

ALTER TABLE tb_user COMMENT ='인증 정보';

CREATE TABLE tb_authkey (
	NO int AUTO_INCREMENT PRIMARY KEY COMMENT '구분 번호', 
	USER_ID varchar(20) NOT NULL COMMENT '사용자 아이디', 
	EMAIL varchar(50) NOT NULL COMMENT '이메일', 
	AUTHKEY varchar(50) NOT NULL COMMENT '인증키', 
	CREATE_TIME datetime NOT NULL COMMENT '발급시간'
);

ALTER TABLE tb_authkey COMMENT ='인증키';

CREATE TABLE tb_schedule (
	NO int AUTO_INCREMENT PRIMARY KEY COMMENT '구분 번호', 
	USER_ID varchar(20) NOT NULL COMMENT '사용자 아이디', 
	TITLE varchar(50) NOT NULL COMMENT '제목', 
	CONTENT varchar(50) NOT NULL COMMENT '내용', 
	END_TIME datetime NOT NULL COMMENT '기한', 
	IS_PUBLIC BOOLEAN NOT NULL COMMENT '공개여부',
    
    FOREIGN KEY (USER_ID) references tb_user(USER_ID)
);

ALTER TABLE tb_schedule COMMENT ='일정';

CREATE TABLE tb_friend (
	REQUESTER_ID varchar(20) COMMENT '신청한 사람 아이디', 
	ADDRESSEE_ID varchar(20) COMMENT '수신 받은 사람 아이디', 
	STATUS varchar(20) NOT NULL COMMENT '상태',
    
    PRIMARY KEY (REQUESTER_ID, ADDRESSEE_ID),
    FOREIGN KEY (REQUESTER_ID) references tb_user(USER_ID),
    FOREIGN KEY (ADDRESSEE_ID) references tb_user(USER_ID)
);
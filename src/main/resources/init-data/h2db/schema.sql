drop table if exists ACCOUNT;

CREATE TABLE ACCOUNT (
    ACCOUNT_ID BIGINT NOT NULL AUTO_INCREMENT COMMENT '회원번호',
    ACCOUNT_NAME VARCHAR(50) NOT NULL COMMENT '회원이름',
    STATUS CHAR DEFAULT '1' COMMENT '상태 (1:사용, 9:정지, 0:탈퇴)',
    BIRTH VARCHAR(8) NOT NULL COMMENT '생년월일',
    HPHONE VARCHAR(11) NOT NULL COMMENT '휴대폰번호',
    EMAIL VARCHAR(50) NOT NULL COMMENT '이메일',
    VIP_YN BOOLEAN DEFAULT TRUE COMMENT 'VIP여부',
    INSERT_DATE TIMESTAMP COMMENT '등록일자',
    INSERT_USER BIGINT COMMENT '등록자ID',
    UPDATE_DATE TIMESTAMP COMMENT '수정일자',
    UPDATE_USER BIGINT COMMENT '수정자ID',
    CONSTRAINT ACCOUNT_PK PRIMARY KEY(ACCOUNT_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '회원정보';


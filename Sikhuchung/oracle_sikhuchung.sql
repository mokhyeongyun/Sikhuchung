----테이블 삭제----
drop table s_review;
drop table s_order_detail;
drop table s_cart;
drop table s_payment;
drop table s_order;
drop table s_notice;
drop table s_user;
drop table s_product;
----시퀀스 삭제----
drop SEQUENCE sequ_notice_number;
drop SEQUENCE sequ_order_number;
drop SEQUENCE sequ_product_number;
drop SEQUENCE sequ_cart_number;
drop SEQUENCE sequ_order_detail_number;
drop SEQUENCE sequ_review_number;
drop SEQUENCE sequ_payment_number;



-- ****시퀀스 생성**** --
--공지사항번호 시퀀스
CREATE SEQUENCE sequ_notice_number START WITH 1 INCREMENT BY 1;
--주문번호 시퀀스
CREATE SEQUENCE sequ_order_number START WITH 1 INCREMENT BY 1;
--상품번호 시퀀스
CREATE SEQUENCE sequ_product_number START WITH 1 INCREMENT BY 1;
--장바구니번호 시퀀스
CREATE SEQUENCE sequ_cart_number START WITH 1 INCREMENT BY 1;
--상세주문번호 시퀀스
CREATE SEQUENCE sequ_order_detail_number START WITH 1 INCREMENT BY 1;
--후기번호 시퀀스
CREATE SEQUENCE sequ_review_number START WITH 1 INCREMENT BY 1;
--결제번호 시퀀스
CREATE SEQUENCE sequ_payment_number START WITH 1 INCREMENT BY 1;



-- 계정 테이블 생성 1 --------------------------------------------------------
CREATE TABLE s_user (
  user_id VARCHAR2(30) PRIMARY KEY,
  user_pw VARCHAR2(20) NOT NULL,
  user_name VARCHAR2(20) NOT NULL,
  user_email VARCHAR2(30) NOT NULL,
  user_tel VARCHAR2(12),
  user_grade VARCHAR(20),
  user_joindate DATE
);
-- 관리자계정 생성
insert into s_user values('admin','a1234*','관리자','admin@naver.com','01011111111','ADMIN',sysdate);
insert into s_user values('test','a1234*','테스트','test@naver.com','01012345678','USER',sysdate);

-- 공지사항 테이블 생성 2 --------------------------------------------------------
CREATE TABLE s_notice (
  notice_number NUMBER(10) PRIMARY KEY,
  user_id VARCHAR2(30) REFERENCES s_user(user_id),
  notice_title VARCHAR2(50) NOT NULL,
  notice_content VARCHAR2(500) NOT NULL,
  notice_date DATE NOT NULL,
  notice_hit NUMBER(10) NOT NULL,
  notice_update_id VARCHAR2(30),
  notice_update_date DATE,
  notice_delete VARCHAR2(10) NOT NULL
);
-- 주문 테이블 생성 3 --------------------------------------------------------
CREATE TABLE s_order (
  order_number NUMBER(10) PRIMARY KEY,
  user_id VARCHAR2(30) REFERENCES s_user(user_id),
  order_address1 VARCHAR2(50) NOT NULL,
  order_address2 VARCHAR2(50) NOT NULL,
  order_address3 VARCHAR2(50) NOT NULL,
  order_recipient_name VARCHAR2(10) NOT NULL,
  order_recipient_tel VARCHAR2(12) NOT NULL,
  order_date DATE NOT NULL
);

-- 상품 테이블 생성 4--------------------------------------------------------
CREATE TABLE s_product (
  product_number NUMBER(10) PRIMARY KEY,
  product_category VARCHAR2(20) NOT NULL,
  product_name VARCHAR2(50) NOT NULL,
  product_price NUMBER(20) NOT NULL,
  product_origin VARCHAR2(30) NOT NULL,
  product_delivery VARCHAR2(30) NOT NULL,
  product_stock NUMBER(20) NOT NULL,
  product_info VARCHAR2(500) NOT NULL,
  product_delete VARCHAR2(10) NOT NULL,
  product_thumbnail VARCHAR2(100) NOT NULL,
  product_register_date DATE NOT NULL,
  product_register VARCHAR2(30) NOT NULL,
  product_update_date DATE,
  product_updater VARCHAR2(30)
);


-- 장바구니 테이블 생성 5 --------------------------------------------------------
CREATE TABLE s_cart (
  cart_number NUMBER(10) PRIMARY KEY,
  product_number NUMBER(10) REFERENCES s_product(product_number),
  user_id VARCHAR2(30) REFERENCES s_user(user_id),
  cart_count NUMBER(10) NOT NULL,
  cart_indate DATE NOT NULL
);

-- 상세주문 테이블 생성 6 --------------------------------------------------------
CREATE TABLE s_order_detail (
  order_detail_number NUMBER(10) PRIMARY KEY,
  product_number NUMBER(10) REFERENCES s_product(product_number),
  order_number NUMBER(10) REFERENCES s_order(order_number),
  order_detail_count NUMBER(5) NOT NULL,
  order_detail_result VARCHAR2(20) NOT NULL
);


-- 후기 테이블 생성 7 --------------------------------------------------------
CREATE TABLE s_review (
  review_number NUMBER(10) PRIMARY KEY,
  order_detail_number NUMBER(10) REFERENCES s_order_detail(order_detail_number),
  review_title VARCHAR2(500) NOT NULL,
  review_content VARCHAR2(500) NOT NULL,
  review_date DATE NOT NULL,
  review_rate VARCHAR2(50) NOT NULL,
  review_update_date DATE,
  review_delete VARCHAR2(10) NOT NULL
);

-- 결제 테이블 생성 8 --------------------------------------------------------
CREATE TABLE s_payment (
  payment_number NUMBER(10) PRIMARY KEY,
  order_number NUMBER(10) REFERENCES s_order(order_number),
  payment_delivery VARCHAR2(30) NOT NULL,
  payment_date DATE NOT NULL,
  payment_method  VARCHAR2(20) NOT NULL,
  payment_total_price NUMBER(20) NOT NULL,
  payment_discount_price NUMBER(20) NOT NULL,
  payment_delivery_price NUMBER(10) NOT NULL,
  payment_final_price NUMBER(20) NOT NULL,
  payment_deposit_name VARCHAR2(20) NOT NULL,
  payment_deposit_bank VARCHAR2(20) NOT NULL,
  payment_account_number VARCHAR2(30) NOT NULL
);



---------상품 등록-------------
INSERT INTO S_PRODUCT
VALUES (2022022501, '제철', '[서귀포] 천혜향 1kg', 13800, '제주 서귀포', '새벽배송 일반배송', 872, '../img/천혜향1.jpg', 'N', '../img/천혜향0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022502, '제철', '[성주] 꿀 참외 1kg', 19800, '경북 성주', '새벽배송 일반배송', 998, '../img/꿀참외1.jpg', 'N', '../img/꿀참외0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022503, '제철', '[논산] 금실 딸기 500g', 32800, '충남 논산', '새벽배송 일반배송', 892, '../img/금실딸기1.jpg', 'N', '../img/금실딸기0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022504, '제철', '[담양] 죽향 딸기 500g', 32800, '전남 담양', '새벽배송 일반배송', 778, '../img/죽향딸기1.jpg', 'N', '../img/죽향딸기0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022505, '제철', '[포항] 못난이 시나노골드 사과 1kg', 10800, '경북 포항', '새벽배송 일반배송', 798, '..img/골드사과1.jpg', 'N', '../img/골드사과0.jpg/', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022506, '제철', '[서귀포] 레드향 1kg', 14800, '제주 서귀포', '새벽배송 일반배송', 798, '../img/레드향1.jpg', 'N', '../img/레드향0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022507, '제철', '[제주] 천천 감귤 1kg', 12800, '제주', '새벽배송 일반배송', 697, '../img/천천감귤1.jpg', 'N', '../img/천천감귤0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022508, '제철', '[영주] 가을스타 별사과 600g', 20800, '경북 영주', '새벽배송 일반배송', 779, '../img/별사과1.jpg', 'N', '../img/별사과0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022509, '제철', '[포항] 못난이 부사 사과 1kg', 20800, '경북 포항', '새벽배송 일반배송', 987, '../img/부사사과1.jpg', 'N', '../img/부사사과0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022510, '별난', '[거창] 딸기 크리스피롤 1봉', 4000, '경남 거창', '새벽배송 일반배송', 835, '../img/딸기롤1.jpg', 'N', '../img/딸기롤0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022511, '별난', '[담양] 신령 호두 100g', 15800, '전남 담양', '새벽배송 일반배송', 784, '../img/신령호두1.jpg', 'N', '../img/신령호두0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022512, '별난', '[산청] 반건시 곶감 10개입', 19800, '경남 산청', '새벽배송 일반배송', 352, '../img/반건시곶감1.jpg', 'N', '../img/반건시곶감0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022513, '별난', '[여주] 반건조 군고구마 꿀말랭이 200g', 8800, '경기 여주', '새벽배송 일반배송', 352, '../img/꿀말랭이1.jpg', 'N', '../img/꿀말랭이0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022514, '별난', '[고창] 햇 토종 오디 2kg', 34800, '전북 고창', '새벽배송 일반배송', 279, '../img/오디1.jpg', 'N', '../img/오디0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022515, '별난', '[장기] 햇 흑 산딸기 1kg', 25800, '포항 장기', '새벽배송 일반배송', 279, '../img/흑딸기1.jpg', 'N', '../img/흑딸기0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022516, '간편', '간편과일 4팩', 19800, '국내산', '일반배송', 927, '../img/간편과일1.jpg', 'N', '../img/간편과일0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022517, '간편', '제철과일 1상자', 50000, '국내산', '일반배송', 215, '../img/제철과일1.jpg', 'N', '../img/제철과일0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022518, '선물', '[선물과일] 진맛과 2월 추천 세트 1호', 70000, '국내산', '일반배송', 100, '../img/세트1호1.jpg', 'N', '../img/세트1호0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022519, '선물', '[선물과일] 진맛과 2월 추천 세트 2호', 85000, '국내산', '일반배송', 100, '../img/세트2호1.jpg', 'N', '../img/세트2호0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022520, '선물', '[선물과일] 진맛과 2월 추천 세트 3호', 70000, '국내산', '일반배송', 100, '../img/세트3호1.jpg', 'N', '../img/세트3호0.jpg', sysdate, '관리자', sysdate, '관리자');
INSERT INTO S_PRODUCT
VALUES (2022022521, '선물', '[선물과일] 진맛과 2월 추천 세트 4호', 70000, '국내산', '일반배송', 100, '../img/세트4호1.jpg', 'N', '../img/세트4호0.jpg', sysdate, '관리자', sysdate, '관리자');
----------------공지사항 더미----------------
insert into s_notice(notice_number,user_id,notice_title,notice_content,notice_date,notice_hit,notice_delete) values (sequ_notice_number.nextval,'admin','테스트 제목1','테스트 내용1',sysdate,0,'N');
insert into s_notice(notice_number,user_id,notice_title,notice_content,notice_date,notice_hit,notice_delete) values (sequ_notice_number.nextval,'admin','테스트 제목2','테스트 내용2',sysdate,0,'N');
insert into s_notice(notice_number,user_id,notice_title,notice_content,notice_date,notice_hit,notice_delete) values (sequ_notice_number.nextval,'admin','테스트 제목3','테스트 내용3',sysdate,0,'N');
insert into s_notice(notice_number,user_id,notice_title,notice_content,notice_date,notice_hit,notice_delete) values (sequ_notice_number.nextval,'admin','테스트 제목4','테스트 내용4',sysdate,0,'N');
insert into s_notice(notice_number,user_id,notice_title,notice_content,notice_date,notice_hit,notice_delete) values (sequ_notice_number.nextval,'admin','테스트 제목5','테스트 내용5',sysdate,0,'N');
insert into s_notice(notice_number,user_id,notice_title,notice_content,notice_date,notice_hit,notice_delete) values (sequ_notice_number.nextval,'admin','테스트 제목6','테스트 내용6',sysdate,0,'N');
insert into s_notice(notice_number,user_id,notice_title,notice_content,notice_date,notice_hit,notice_delete) values (sequ_notice_number.nextval,'admin','테스트 제목7','테스트 내용7',sysdate,0,'N');
insert into s_notice(notice_number,user_id,notice_title,notice_content,notice_date,notice_hit,notice_delete) values (sequ_notice_number.nextval,'admin','테스트 제목8','테스트 내용8',sysdate,0,'N');
insert into s_notice(notice_number,user_id,notice_title,notice_content,notice_date,notice_hit,notice_delete) values (sequ_notice_number.nextval,'admin','테스트 제목9','테스트 내용9',sysdate,0,'N');
insert into s_notice(notice_number,user_id,notice_title,notice_content,notice_date,notice_hit,notice_delete) values (sequ_notice_number.nextval,'admin','테스트 제목10','테스트 내용10',sysdate,0,'N');

----------커밋-----------
commit;

--=====================================================================
-- 관리자 계정
--=====================================================================
-- web 계정 생성
alter session set "_oracle_script" = true; -- c##으로 시작하지 않는 일반계정 생성 허용

create user web
identified by web
default tablespace users;

grant connect, resource to web;
alter user web quota unlimited on users;

--==================================================================
-- web 계정
--==================================================================
-- 회원 테이블 생성
create table member (
    member_id varchar2(15),
    password varchar2(300) not null,
    member_name varchar2(50) not null,
    member_role char(1) default 'U' not null,
    gender char(1),
    birthday date, 
    email varchar2(200),
    phone char(11) not null,
    address varchar2(200),
    hobby varchar2(200), 
    enroll_date date default sysdate,
    constraint pk_member_id primary key(member_id),
    constraint ck_member_roll check(member_role in ('U', 'A')),
    constraint ck_member_gender check(gender in ('M', 'F')),
    constraint uq_member_email unique(email)
);

    --회원 추가
    insert into member
    values (
        'honggd', '1234', '홍길동', 'U', 'M', to_date('20000909','yyyymmdd'),
        'honggd@naver.com', '01012341234', '서울시 강남구', '운동,등산,독서', default
    );

    insert into member
    values (
        'qwerty', '1234', '쿠어티', 'U', 'F', to_date('19900215','yyyymmdd'),
        'qwerty@naver.com', '01012341234', '서울시 송파구', '운동,등산', default
    );

    insert into member
    values (
        'admin', '1234', '관리자', 'A', 'M', to_date('19801010','yyyymmdd'),
        'admin@naver.com', '01056785678', '서울시 관악구', '게임,독서', default
    );

 select * from member;
 commit;
 
 
-- 페이징 쿼리
-- 1. rownum
--2. row_number
select
    *
from (
select
    row_number() over(order by enroll_date desc) rnum,
    m.*
from
        member m) m
where
    rnum between 1 and 10;
    
   
--select * from (select row_number() over(order by enroll_date desc) rnum, m.* from member m) m where rnum between ? and ?;




--  게시판
create table board (
    no number,
    title varchar2(100) not null,
    member_id varchar2(20),
    content varchar2(4000) not null,
    read_count number default 0,
    reg_date date default sysdate,
    constraint pk_board_no primary key(no),
    constraint fk_board_member_id foreign key(member_id) references member(member_id) on delete set null
);

create sequence seq_board_no;

create table attachment (
    no number,
    board_no number not null,
    original_filename varchar2(255) not null, -- 업로드한 파일명
    renamed_filename varchar2(255) not null, -- 저장된 파일명
    reg_date date default sysdate,
    constraint pk_attachment_no primary key(no),
    constraint fk_attachment_board_no foreign key(board_no) references board(no) on delete cascade
);

create sequence seq_attachment_no;

insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 1','honggd','반갑습니다',to_date('18/02/10','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 2','qwerty','안녕하세요',to_date('18/02/12','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 3','admin','반갑습니다',to_date('18/02/13','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 4','honggd','안녕하세요',to_date('18/02/14','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 5','qwerty','반갑습니다',to_date('18/02/15','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 6','admin','안녕하세요',to_date('18/02/16','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 7','honggd','반갑습니다',to_date('18/02/17','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 8','qwerty','안녕하세요',to_date('18/02/18','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 9','admin','반갑습니다',to_date('18/02/19','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 10','honggd','안녕하세',to_date('18/02/20','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 11','qwerty','반갑습니다',to_date('18/03/11','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 12','admin','안녕하세',to_date('18/03/12','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 13','honggd','반갑습니다',to_date('18/03/13','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 14','qwerty','안녕하세',to_date('18/03/14','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 15','admin','반갑습니다',to_date('18/03/15','RR/MM/DD'),0);


insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 16','honggd','안녕하세',to_date('18/03/16','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 17','qwerty','반갑습니다',to_date('18/03/17','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 18','admin','안녕하세',to_date('18/03/18','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 19','honggd','반갑습니다',to_date('18/03/19','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 20','qwerty','안녕하세',to_date('18/03/20','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 21','admin','반갑습니다',to_date('18/04/01','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 22','honggd','안녕하세',to_date('18/04/02','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 23','qwerty','반갑습니다',to_date('18/04/03','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 24','admin','안녕하세',to_date('18/04/04','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 25','honggd','반갑습니다',to_date('18/04/05','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 26','qwerty','안녕하세',to_date('18/04/06','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 27','admin','반갑습니다',to_date('18/04/07','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 28','honggd','안녕하세',to_date('18/04/08','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 29','qwerty','반갑습니다',to_date('18/04/09','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 30','admin','안녕하세',to_date('18/04/10','RR/MM/DD'),0);

insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 31','honggd','반갑습니다',to_date('18/04/16','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 32','qwerty','안녕하세',to_date('18/04/17','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 33','admin','반갑습니다',to_date('18/04/18','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 34','honggd','안녕하세',to_date('18/04/19','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 35','qwerty','반갑습니다',to_date('18/04/20','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 36','admin','안녕하세',to_date('18/05/01','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 37','honggd','반갑습니다',to_date('18/05/02','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 38','qwerty','안녕하세',to_date('18/05/03','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 39','admin','반갑습니다',to_date('18/05/04','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 40','honggd','안녕하세',to_date('18/05/05','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 41','qwerty','반갑습니다',to_date('18/05/06','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 42','admin','안녕하세',to_date('18/05/07','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 43','honggd','반갑습니다',to_date('18/05/08','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 44','qwerty','안녕하세',to_date('18/05/09','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 45','admin','반갑습니다',to_date('18/05/10','RR/MM/DD'),0);

insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 46','honggd','안녕하세',to_date('18/05/16','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 47','qwerty','반갑습니다',to_date('18/05/17','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 48','admin','안녕하세',to_date('18/05/18','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 49','honggd','반갑습니다',to_date('18/05/19','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 50','qwerty','안녕하세',to_date('18/05/20','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 51','admin','반갑습니다',to_date('18/05/01','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 52','honggd','안녕하세',to_date('18/06/02','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 53','qwerty','반갑습니다',to_date('18/06/03','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 54','admin','안녕하세',to_date('18/06/04','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 55','honggd','반갑습니다',to_date('18/06/05','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 56','qwerty','안녕하세',to_date('18/06/06','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 57','admin','반갑습니다',to_date('18/06/07','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 58','honggd','안녕하세',to_date('18/06/08','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 59','qwerty','반갑습니다',to_date('18/06/09','RR/MM/DD'),0);
insert into web.board (no,title,member_id,content,reg_date,read_count) values (seq_board_no.nextval,'안녕하세요, 게시판입니다 - 60','admin','안녕하세',to_date('18/06/10','RR/MM/DD'),0);

insert into web.attachment(no, board_no, original_filename, renamed_filename)
values(seq_attachment_no.nextval, 60, 'test.txt', '20211007_12345.txt');

commit;

select * from board order by reg_date desc;
select * from attachment order by reg_date desc;

-- 댓글 테이블 생성
create table board_comment (
    no number,
    comment_level number default 1, -- 댓글(1) /대댓글(2)
    member_id varchar2(15),
    content varchar2(2000),
    board_no number,
    comment_ref number, -- 대댓글인 경우에만 참조하는 댓글(댓글인 경우는 null)
    reg_date date default sysdate,
    constraint pk_board_comment_no primary key(no),
    constraint fk_board_comment_member_id foreign key(member_id) references member(member_id) on delete set null,
    constraint fk_board_comment_board_no foreign key(board_no) references board(no) on delete cascade,
    constraint fk_board_comment_ref foreign key(comment_ref) references board_comment(no) on delete cascade
);

create sequence seq_board_comment_no;

select * from board order by no desc;

-- 샘플 데이터 입력 202번
insert into board_comment
values(seq_board_comment_no.nextval,  default, 'honggd', '테스트가 흥미롭습니다.', 202, null, sysdate);
insert into board_comment
values(seq_board_comment_no.nextval,  default, 'admin', '안녕하세요. 관리자입니다.', 202, null, sysdate);
insert into board_comment
values(seq_board_comment_no.nextval,  default, 'qwerty', '한 주가 쏜살같이 지나가네요~', 202, null, sysdate);

select * from board_comment order by no;

-- 대댓글
insert into board_comment
values(seq_board_comment_no.nextval,  2, 'sinsa', '안녕', 202, 1, sysdate);

insert into board_comment
values(seq_board_comment_no.nextval,  2, 'qwerty', '오랜만.', 202, 1, sysdate);

insert into board_comment
values(seq_board_comment_no.nextval,  2, 'qwerty', '안녕하세요~', 202, 2, sysdate);


-- 계층형 쿼리
-- 부모행 no - 댓글
-- 자식행 comment_ref - 대댓글
select 
        level,
        lpad('  ' , (level-1) * 5) || bc.content,
        bc.*
from
        board_comment bc
where
        board_no = 202
start with comment_level = 1
connect by comment_ref =  prior no;

-- kh 계정에서 관리자 - 관리사원정보 계층형 쿼리
select * from employee;
select 
        level,
        lpad('  ' , (level-1) * 6) || e.emp_id "계층",
        e.*
from
        employee e
start with emp_id  = 200
connect by manager_id = prior emp_id;

-- 상향식
select
        level,
        emp_name
from 
        employee
where
        level > 1
start with emp_name = '윤은해'
connect by prior manager_id = emp_id;

-- photo 테이블 생성
create table photo (
    no number,
    member_id varchar2(15),
    content varchar2(4000),
    original_filename varchar2(100),
    renamed_filename varchar2(100),
    read_count number default 0,
    reg_date date default sysdate,
    constraint pk_photo_no primary key(no),
    constraint fk_photo_member_id foreign key(member_id) references member(member_id)
);

create sequence seq_photo_no;

select * from member where member_id = 'abcd';



Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','하와이 가는 하늘길~','adult-adventure-aircraft-443430.jpg','20220418_174158873_108.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','프랑스산 와인 시음회 :)','adult-alcohol-blur-290316.jpg','20220418_174412447_349.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','adventure-albay-clouds-672358.jpg','20220418_174453770_556.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','airplane-apartment-architecture-364245.jpg','20220418_174505657_4.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','america-architecture-billboards-461903.jpg','20220418_174516697_101.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','ancient-architecture-building-415980.jpg','20220418_174527327_327.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','arch-architecture-art-332208.jpg','20220418_174539548_250.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','자나깨나 차조심, 트램조심','architecture-avenue-blur-258447.jpg','20220418_174601509_281.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','architecture-background-buildings-698604.jpg','20220418_174616171_833.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','architecture-barcelona-blue-sky-819764.jpg','20220418_174652399_241.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','architecture-basilica-buildings-326709.jpg','20220418_174743637_226.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','architecture-building-door-206767.jpg','20220418_174800692_837.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','atmosphere-beautiful-cloudburst-531318.jpg','20220418_174814411_4.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','이과수이과수','back-beach-beautiful-670060.jpg','20220418_174839106_197.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','bicycle-tour-bicycles-bicyclists-17729.jpg','20220418_174856071_779.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','billboard-business-city-733778.jpg','20220418_174910053_722.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','black-clouds-dark-420885.jpg','20220418_174924429_849.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','boulders-clouds-daylight-464440.jpg','20220418_174941759_108.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','여행은 즐거워','capital-cathedral-city-6502.jpg','20220418_174957191_842.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'abcd','퐈이아아아아아','evening-fair-fire-65297.jpg','20220418_175019576_86.jpg',to_date('22-04-18','DD-MM-RR'),0);
Insert into WEB.PHOTO (NO,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,REG_DATE,READ_COUNT) values (SEQ_PHOTO_NO.NEXTVAL,'wxyz','소나무야','375px-Pinus_densiflora_Kumgangsan.jpg','20220418_125936088_36.jpg',to_date('22-04-19','DD-MM-RR'),0);
commit;

select * from photo order by no desc;

-- 전체 게시물 수 
select count(*) from photo;

-- 페이징 쿼리
select
    *
from
    (select row_number() over(order by reg_date desc) rnum, p.* from photo p) p 
where 
    rnum between 1 and 5;

-- select * from (select row_number() over(order by reg_date desc) rnum, p.* from photo p) p where rnum between 1 and 5;

commit;
--============================================================
-- hello-mybatis
--============================================================
-- student 테이블 생성
create table student (
    no number,
    name varchar2(50) not null,
    tel char(11) not null,
    created_at date default sysdate,
    updated_at date default sysdate,
    deleted_at date,
    constraint pk_student_no primary key(no)
);
create sequence seq_student_no;
--drop sequence seq_student_no;
insert into
        student(no, name, tel)
values
    (seq_student_no.nextval, '홍길동', '01012341234');

insert into
        student(no, name, tel)
values
    (seq_student_no.nextval, '신사임당', '01033334444');

commit;

-- 수정
update 
    student
set
    tel = '01011112222',
    updated_at = sysdate
where
        deleted_at is null
        and no = 2;

-- 삭제
update 
    student
set 
    deleted_at = sysdate
where 
    no = 1;

select * from student where (deleted_at is null) and no = 1;
select * from student where (deleted_at is null) ;
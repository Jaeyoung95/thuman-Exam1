create table member_tbl_02(
custno number(6) not null primary key,
custname varchar2(20),
phone varchar2(13),
address varchar2(60),
joindate date,
grade char(1),
city char(2)
);
-- 테이블을 만든 후 문제의 조건과 다르게 만든 것을 인지하는 경우
-- 본인이 alter table명령어를 사용하는데에 자신이 있으면 사용하면 되지만
-- 만일 그렇지 않으면 테이블을 지우고 조건에 맞게 create table문을 수정 후
-- 테이블을 다시 만든다.
drop table member_tbl_02
drop table money_tbl_02

create table money_tbl_02(
custno number(6) not null,
salenol number(8) not null,
pcost number(8),
amount number(4),
price number(8),
pcode varchar2(4),
sdate date,
primary key (custno,salenol)
-- 두 개의 조합으로 기본키를 만들 경우 제약조건을 별도로 작성.
);

-- 시퀀스 만들기(문제에서 시퀀스를 만드는 것을 지시했다면 문제대로 하는 것을 권장)
create sequence member_seq
start with 100001
increment by 1
nocache;

-- 시퀀스 삭제
drop sequence member_seq

-- MEMBER_TBL_02 테이블 조회
select * from MEMBER_TBL_02 order by custno

-- MONEY_TBL_02 테이블 조회
select * from MONEY_TBL_02

-- MEMBER_TBL_02 테이블 레코드 삭제
delete from MEMBER_TBL_02

-- MONEY_TBL_02 테이블 레코드 삭제
delete from MONEY_TBL_02

-- 레코드 삽입 방법1(시퀀스 이용) member_tbl_02 레코드
insert into member_tbl_02 values(member_seq.nextval,'김행복','010-1111-2222','서울 동대문구 휘경1동','2015-12-02','A','01');
insert into member_tbl_02 values(member_seq.nextval,'이축복','010-1111-3333','서울 동대문구 휘경2동','2015-12-06','B','01');
insert into member_tbl_02 values(member_seq.nextval,'장믿음','010-1111-4444','울릉군 울릉읍 독도1리','2015-10-01','B','30');
insert into member_tbl_02 values(member_seq.nextval,'최사랑','010-1111-5555','울릉군 울릉읍 독도2리','2015-11-13','A','30');
insert into member_tbl_02 values(member_seq.nextval,'진평화','010-1111-6666','제주도 제주시 외나무골','2015-12-25','B','60');
insert into member_tbl_02 values(member_seq.nextval,'차공단','010-1111-7777','제주도 제주시 감나무골','2015-12-11','C','60');

-- 레코드 삽입 money_tbl_02
insert into money_tbl_02 values(100001,20160001,500,5,2500,'A001','2016-01-01');
insert into money_tbl_02 values(100001,20160002,1000,4,4000,'A002','2016-01-01');
insert into money_tbl_02 values(100001,20160003,500,3,1500,'A008','2016-01-01');
insert into money_tbl_02 values(100002,20160004,2000,1,2000,'A004','2016-01-02');
insert into money_tbl_02 values(100002,20160005,500,1,500,'A001','2016-01-03');
insert into money_tbl_02 values(100003,20160006,1500,2,3000,'A003','2016-01-03');
insert into money_tbl_02 values(100004,20160007,500,2,1000,'A001','2016-01-04');
insert into money_tbl_02 values(100004,20160008,300,1,300,'A005','2016-01-04');
insert into money_tbl_02 values(100004,20160009,600,1,600,'A006','2016-01-04');
insert into money_tbl_02 values(100004,20160010,3000,1,3000,'A007','2016-01-06');


-- 레코드 삽입 방법2(시퀀스 이용하지 않고 서브쿼리 이용..)
-- 문제에서 시퀀스를 이용하는 것을 권장하는 암시를 하였기에 되도록 시퀀스를 이용하자.
insert into member_tbl_02 
values((select max(custno) from MEMBER_TBL_02)+1,'김행복','010-1111-2222','서울 동대문구 휘경1동','2015-12-02','A','01')

-- 회원번호 중에서 가장 최근 번호 검색
select max(custno)+1 from member_tbl_02

-- 회원리스트 조회
select custno,custname,phone,address,to_char(joindate,'yyyy-mm-dd') as joindate,grade,city from member_tbl_02

-- 회원별 매출 총합
select member.custno,member.custname,member.grade,sum(money.price) as totalprice from member_tbl_02 member,money_tbl_02 money where member.custno=money.custno group by member.custno,member.custname,member.grade order by totalprice desc

-- 회원번호별 조회
select * from member_tbl_02 where custno=100006

drop table `User`;
drop table `notice`;

CREATE TABLE IF NOT EXISTS `User` (
  `id` VARCHAR(20) NOT NULL,
  `pass` VARCHAR(20) NOT NULL,
  `name` VARCHAR(40) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `gender` int default 0,
  `age` int default 0,
  `phone` VARCHAR(30) NOT NULL,
  `preferOrder1` VARCHAR(30),
  `preferOrder2` VARCHAR(30),
  `preferRegArr` VARCHAR(1000),
  `preferAptArr` VARCHAR(1000),
  `state` int default 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Notice` (
  `no` INT NOT NULL auto_increment,
  `title` VARCHAR(50) NOT NULL,
  `author` VARCHAR(40) NOT NULL,
  `regDate` VARCHAR(30) NOT NULL,
  `hit` INT default 0 NOT NULL,
  `content` VARCHAR(1000) NOT NULL,
  `type` INT,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;

INSERT INTO `user` VALUES 
('admin','admin', '관리자', 'admin@ssafy.com', 0, 0, '010-000-0000', '', '', '', '', 1),
('ssafy','ssafy', '싸아피', 'ssafy@ssafy.com', 1, 24, '010-000-0000', '', '', '', '', 1),
('ssafy2','ssafy', '한싸피', 'ssafy@ssafy.com', 2, 24, '010-000-0000', '', '', '', '', 1),
('ssafy3','ssafy', '박싸피', 'ssafy@ssafy.com', 2, 0, '010-000-0000', '', '', '', '', 1),
('ssafy4','ssafy', '김싸피', 'ssafy@ssafy.com', 1, 26, '010-000-0000', '', '', '', '', 1),
('ssafy5','ssafy', '최싸피', 'ssafy@ssafy.com', 0, 0, '010-000-0000', '', '', '', '', 0),
('ssafy6','ssafy', '정싸피', 'ssafy@ssafy.com', 0, 0, '010-000-0000', '', '', '', '', 0);


insert into user (id, pass, name, email, phone)
values ('ssafy8', 'ssafy', '인증안한싸피', 'ssafy@ssafy.com', '010-0000-0000');

select *
from user;

select count(id)
from user
where id = "ssafy10";

update notice
set type = 0
where type is null;

insert into notice (title, author, regDate, content, type)
values ('공지사항입니다.', '관리자', now(), '내용입니다.', 0),
('공지사항 테스트', '관리자', now(), '내용 테스트', 0),
('공지사항 테스트2', '관리자', now(), '내용 테스트2', 0),
('공지사항 테스트3', '관리자', now(), '내용 테스트3', 0),
('공지사항 테스트4', '관리자', now(), '내용 테스트4', 0),
('공지사항 테스트5', '관리자', now(), '내용 테스트5', 0),
('공지사항 테스트6', '관리자', now(), '내용 테스트6', 0),
('공지사항 테스트7', '관리자', now(), '내용 테스트7', 0);

insert into notice (title, author, regDate, content, type)
values ('!!공지사항입니다!!', '관리자', now(), '내용입니다.', 1),
('!!공지사항 테스트!!', '관리자', now(), '내용 테스트', 1),
('!!공지사항 테스트!!', '관리자', now(), '내용 테스트2', 1),
('공지사항 테스트', '관리자', now(), '내용 테스트3', 0),
('공지사항 테스트', '관리자', now(), '내용 테스트4', 0),
('공지사항 테스트', '관리자', now(), '내용 테스트5', 0),
('공지사항 테스트', '관리자', now(), '내용 테스트6', 0),
('공지사항 테스트', '관리자', now(), '내용 테스트7', 0);
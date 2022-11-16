-- drop table `User`;
-- drop table `notice`;

CREATE TABLE IF NOT EXISTS `User` (
  `id` VARCHAR(20) NOT NULL,
  `pass` VARCHAR(20) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `name` VARCHAR(40) NOT NULL,
  `phone` VARCHAR(30) NOT NULL,
  `age` VARCHAR(30) NOT NULL,
  `gender` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Notice` (
  `no` INT NOT NULL auto_increment,
  `title` VARCHAR(50) NOT NULL,
  `reg_date` VARCHAR(30) NOT NULL,
  `hit` INT NOT NULL,
  `content` VARCHAR(1000) NOT NULL,
--   `admin_id` VARCHAR(20),
--   CONSTRAINT `fk_manager` FOREIGN KEY (`admin_id`) 
--   REFERENCES `user` (`id`),
  PRIMARY KEY (`no`))
ENGINE = InnoDB;

INSERT INTO `user` VALUES ('admin','admin', 'admin@ssafy.com', '관리자', '010-000-0000', '30', 'm') ,('ssafy','ssafy', 'ssafy1@ssafy.com', '박싸피', '010-000-0000', '20', 'f');

select *
from user;

use home;
select * from dongcode limit 5;
select * from houseinfo limit 5;

select * from houseinfo where dongcode like "4511111900%";

select apartmentName, dong, area, dealAmount, dealYear, dealMonth, dealDay, lng, lat
		from houseinfo as info inner join housedeal as deal on info.aptCode = deal.aptCode
		where info.dongCode like concat(11, '%') limit 10;
        
select apartmentName, dong, area, dealAmount, dealYear, dealMonth, dealDay, lng, lat
		from houseinfo as info inner join housedeal as deal on info.aptCode = deal.aptCode
		where info.apartmentName like concat ('%', '스페', '%') limit 30
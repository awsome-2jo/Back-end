alter table houseinfo
add (min_deal_amount int, max_deal_amount int, min_area varchar(8), max_area varchar(8));

update houseinfo a, (select a.aptCode,
       (select max(cast(replace(dealAmount, ',', '') as unsigned)) from housedeal where  aptCode = a.aptCode) max_deal_amount,
       (select min(cast(replace(dealAmount, ',', '') as unsigned)) from housedeal where  aptCode = a.aptCode) min_deal_amount,
       (select max(cast(area as DECIMAL(10,2))) from housedeal where  aptCode = a.aptCode) max_area,
       (select min(cast(area as DECIMAL(10,2))) from housedeal where  aptCode = a.aptCode) min_area
            from houseinfo a) b
   set a.max_deal_amount = b.max_deal_amount,
       a.min_deal_amount = b.min_deal_amount,
       a.max_area = b.max_area,
       a.min_area = b.min_area
 where a.aptCode = b.aptCode
   and a.min_area is null;
   
   
create table housedeal_2021
select *
from housedeal 
where dealYear = 2021;

insert into housedeal_stats (dealYear, aptCode, maxdealmount, mindealmount, maxarea, minarea)
select dealYear, aptCode, max(cast(replace(dealAmount, ',', '') as unsigned)) as maxdealmount, 
min(cast(replace(dealAmount, ',', '') as unsigned)) as mindealmount,
max(cast(area as float)) as maxarea,
min(cast(area as float)) as minarea
from housedeal_2020
group by aptCode;



# =============================== 구분 ==========================================


select info.aptCode, apartmentName, roadName, jibun, lng, lat, buildYear, dealAmount, area 
		from houseinfo as info inner join housedeal as deal on info.aptCode = deal.aptCode
		where dongCode like concat('11', '%') 
		group by apartmentName limit 10;

select info.aptCode, apartmentName, roadName, jibun, lng, lat, buildYear, dealAmount, area 
		from houseinfo as info inner join housedeal as deal on info.aptCode = deal.aptCode
		where dongCode like concat('11', '%') limit 10;

show index from houseinfo;

select info.aptCode, apartmentName, roadName, jibun, lng, lat, buildYear, dealAmount, cast(area as float) as area
		from houseinfo as info inner join housedeal as deal on info.aptCode = deal.aptCode
		where apartmentName like concat ('%', '', '%') 
        and dongCode like concat('11', '%')
        and cast(replace(dealAmount, ',', '') as unsigned) >= 0
        and cast(replace(dealAmount, ',', '') as unsigned) <= 1000000
        and area >= 0
        and area <= 100000
        and dealYear >= 2020
		group by aptCode limit 10 offset 5;


create table housedeal_2021
select *
from housedeal 
where dealYear = 2021;

insert into housedeal_stats (dealYear, aptCode, maxdealmount, mindealmount, maxarea, minarea)
select dealYear, aptCode, max(cast(replace(dealAmount, ',', '') as unsigned)) as maxdealmount, 
min(cast(replace(dealAmount, ',', '') as unsigned)) as mindealmount,
max(cast(area as float)) as maxarea,
min(cast(area as float)) as minarea
from housedeal_2020
group by aptCode;

select dealAmount, info.aptCode
		from houseinfo as info inner join housedeal as deal on info.aptCode = deal.aptCode
		where apartmentName like concat ('%', "", '%') 
        and dongCode like concat("11", '%')
        and dealYear <= 2020;

select info.aptCode, apartmentName, roadName, jibun, lng, lat, buildYear, dealAmount, cast(area as float) as area, 
		(select max(cast(replace(dealAmount, ',', '') as unsigned)) from housedeal where info.aptCode = deal.aptCode and dealAmount is not null) maxdealamount,
		(select min(cast(replace(dealAmount, ',', '') as unsigned)) from housedeal where info.aptCode = deal.aptCode and dealAmount is not null) mindealamount,
		(select max(cast(area as float)) from housedeal where info.aptCode = deal.aptCode and dealAmount is not null) maxarea,
		(select max(cast(area as float)) from housedeal where info.aptCode = deal.aptCode and dealAmount is not null) minarea
		from houseinfo as info inner join housedeal as deal on info.aptCode = deal.aptCode
		where apartmentName like concat ('%', '', '%') 
        and dongCode like concat('11', '%')
        and cast(replace(dealAmount, ',', '') as unsigned) >= 0
        and cast(replace(dealAmount, ',', '') as unsigned) <= 1000000
        and area >= 0
        and area <= 100000
        and dealYear >= 2020
		group by aptCode limit 10 offset 5;

drop table housedeal_count;

select *
from houseinfo limit 10;

create table housedeal_count 
select no, info.aptCode, dongCode, dealAmount, dealYear
from houseinfo as info inner join housedeal as deal on info.aptCode = deal.aptCode;

explain select *
from housedeal_count limit 20;

show index from housedeal_count;
alter table housedeal_count add index dongCode(dongCode);
alter table housedeal_count add index dealYear(dealYear);

select dealAmount, aptCode
from housedeal_count;

select count(aptCode)
from houseinfo
where dongCode like concat('dongcode', '%')
and apartmentName like concat('%', '', '%');

select *
from housedeal_count;

select dealAmount, info.aptCode
		from houseinfo as info inner join housedeal as deal on info.aptCode = deal.aptCode
		where apartmentName like concat ('%', '', '%') 
        and dongCode like concat('11', '%')
        and dealYear >= 2020;

select buildYear, roadName, jibun, apartmentName, lng, lat
from houseinfo
where aptCode = "11110000000006";

select no, dealAmount, dealYear, dealMonth, dealDay, area, floor, cancelDealType
from housedeal
where aptCode = "11110000000006";

select *
from houseinfo limit 20;



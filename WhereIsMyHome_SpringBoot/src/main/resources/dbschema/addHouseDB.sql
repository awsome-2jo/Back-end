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

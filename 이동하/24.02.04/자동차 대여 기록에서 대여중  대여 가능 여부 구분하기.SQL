# select CAR_ID,
# (case
#  when START_DATE < '2022-10-16' and '2022-10-16' <= END_DATE then '대여중'
#  else '대여 가능'
# end) as 'AVILABILITY'
# from CAR_RENTAL_COMPANY_RENTAL_HISTORY;

select distinct(a.CAR_ID),
(case
 when a.CAR_ID in (select distinct(CAR_ID) from CAR_RENTAL_COMPANY_RENTAL_HISTORY where DATE_FORMAT(START_DATE, "%Y-%m-%d") <= '2022-10-16' and '2022-10-16' <=  DATE_FORMAT(END_DATE, "%Y-%m-%d")) then '대여중'
 else '대여 가능'
end) as 'AVILABILITY'

from CAR_RENTAL_COMPANY_RENTAL_HISTORY a

order by a.CAR_ID desc;
# select distinct(CAR_ID) from CAR_RENTAL_COMPANY_RENTAL_HISTORY where START_DATE <= '2022-10-16' and '2022-10-16' <= END_DATE
# order by car_id desc
-- 코드를 입력하세요


select month(START_DATE) as MONTH, a.CAR_ID, count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY a
join (
    select CAR_ID from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where '2022-08' <= date_format(START_DATE, "%Y-%m") and date_format(START_DATE, "%Y-%m") <= '2022-10'
    group by CAR_ID
    having count(CAR_ID) >= 5
) b on a.CAR_ID = b.CAR_ID
where '2022-08' <= date_format(START_DATE, "%Y-%m") and date_format(START_DATE, "%Y-%m") <= '2022-10'
group by MONTH, CAR_ID
order by MONTH, CAR_ID desc
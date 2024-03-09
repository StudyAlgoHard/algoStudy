-- 코드를 입력하세요
# select a.FOOD_TYPE, a.REST_ID, a.FAVORITES
# from (
#     select FOOD_TYPE, FAVORITES, REST_ID
#     from REST_INFO
#     order by FOOD_TYPE, FAVORITES desc
# ) a
# group by a.FOOD_TYPE


# select FOOD_TYPE, REST_ID, FAVORITES
# from REST_INFO
# order by FOOD_TYPE, FAVORITES desc

select a.FOOD_TYPE, a.REST_ID, a.REST_NAME, a.FAVORITES
from REST_INFO a, (
    select FOOD_TYPE, max(FAVORITES) as FAVORITES
    from REST_INFO
    group by FOOD_TYPE
) b
where a.FAVORITES = b.FAVORITES and a.FOOD_TYPE = b.FOOD_TYPE
order by a.FOOD_TYPE desc

# 분식 8 151
# 양식 3 102
# 일식 4 230
# 중식 15 20
# 한식 1 734
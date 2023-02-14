-- Programmers (JOIN) 특정 기간동안 대여 가능한 자동차들의 대여비용 구하기
SELECT B.CAR_ID, B.CAR_TYPE, ROUND(B.DAILY_FEE * 30 * (1 - A.DISCOUNT_RATE / 100)) AS FEE
FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN AS A
INNER JOIN CAR_RENTAL_COMPANY_CAR AS B ON A.CAR_TYPE = B.CAR_TYPE
WHERE A.DURATION_TYPE = '30일 이상'
    AND B.CAR_TYPE IN ('세단', 'SUV') 
    AND B.CAR_ID NOT IN (SELECT DISTINCT CAR_ID
                         FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                         WHERE START_DATE BETWEEN '2022-11-01' AND '2022-11-30'
                            OR END_DATE BETWEEN '2022-11-01' AND '2022-11-30'
                            OR (START_DATE < '2022-11-01' AND '2022-11-30' < END_DATE))
HAVING FEE BETWEEN 500000 AND 2000000
ORDER BY FEE DESC, B.CAR_TYPE ASC, B.CAR_ID DESC
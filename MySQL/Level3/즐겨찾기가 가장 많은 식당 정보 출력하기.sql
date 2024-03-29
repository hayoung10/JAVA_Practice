-- Programmers (GROUP BY) 즐겨찾기가 가장 많은 식당 정보 출력하기
SELECT A.FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
FROM REST_INFO AS A,
    (SELECT FOOD_TYPE, MAX(FAVORITES) AS FAV
    FROM REST_INFO
    GROUP BY FOOD_TYPE) AS B
WHERE A.FOOD_TYPE = B.FOOD_TYPE AND A.FAVORITES = B.FAV
ORDER BY A.FOOD_TYPE DESC
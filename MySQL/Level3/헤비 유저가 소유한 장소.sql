-- Programmers 헤비 유저가 소유한 장소
SELECT *
FROM PLACES
WHERE HOST_ID IN (SELECT HOST_ID
                  FROM PLACES
                  GROUP BY HOST_ID
                  HAVING COUNT(HOST_ID) > 1)
ORDER BY ID
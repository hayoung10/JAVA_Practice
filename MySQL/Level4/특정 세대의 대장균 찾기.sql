-- Programmers (SELECT) 특정 세대의 대장균 찾기
SELECT C.ID
FROM ECOLI_DATA C
WHERE C.PARENT_ID IN (SELECT B.ID
                      FROM ECOLI_DATA B
                      INNER JOIN ECOLI_DATA A ON A.ID = B.PARENT_ID
                      WHERE A.PARENT_ID IS NULL)
ORDER BY C.ID;
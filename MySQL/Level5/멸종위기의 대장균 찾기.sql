-- Programmers (SELECT) 멸종위기의 대장균 찾기
WITH RECURSIVE TREE AS (
    SELECT ID, PARENT_ID, 1 AS GENERATION
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
    
    UNION ALL
    
    SELECT A.ID, A.PARENT_ID, B.GENERATION + 1
    FROM ECOLI_DATA A
    INNER JOIN TREE B ON A.PARENT_ID = B.ID
)

SELECT COUNT(A.ID) AS COUNT, A.GENERATION
FROM TREE A
LEFT JOIN ECOLI_DATA B ON A.ID = B.PARENT_ID
WHERE B.PARENT_ID IS NULL
GROUP BY A.GENERATION
ORDER BY A.GENERATION;
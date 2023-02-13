-- Programmers (JOIN) 보호소에서 중성화한 동물
SELECT AI.ANIMAL_ID, AI.ANIMAL_TYPE, AI.NAME
FROM ANIMAL_INS AI
INNER JOIN ANIMAL_OUTS AO
ON AI.ANIMAL_ID = AO.ANIMAL_ID
WHERE AI.SEX_UPON_INTAKE LIKE 'Intact%' AND AO.SEX_UPON_OUTCOME REGEXP 'Spayed|Neutered'
ORDER BY AI.ANIMAL_ID
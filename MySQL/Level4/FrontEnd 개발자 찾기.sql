-- Programmers (JOIN) FrontEnd 개발자 찾기
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPERS
WHERE SKILL_CODE & (SELECT SUM(CODE)
                    FROM SKILLCODES
                    WHERE CATEGORY = 'Front End')
ORDER BY ID;
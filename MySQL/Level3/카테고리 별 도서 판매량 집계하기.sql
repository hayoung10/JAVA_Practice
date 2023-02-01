-- Programmers (GROUP BY) 카테고리 별 도서 판매량 집계하기
SELECT BK.CATEGORY, SUM(BS.SALES) AS TOTAL_SALES
FROM BOOK AS BK, BOOK_SALES AS BS
WHERE BS.SALES_DATE LIKE '2022-01%' AND BK.BOOK_ID = BS.BOOK_ID
GROUP BY BK.CATEGORY
ORDER BY BK.CATEGORY ASC
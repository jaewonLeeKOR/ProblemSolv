SELECT COUNT(*) FISH_COUNT, MONTH(TIME) MONTH
FROM FISH_INFO
GROUP BY MONTH
ORDER BY MONTH ASC
SELECT p.y AS YEAR, p.m - SIZE_OF_COLONY AS YEAR_DEV, o.id AS ID
FROM ECOLI_DATA o
LEFT JOIN (
    SELECT YEAR(DIFFERENTIATION_DATE) as y, MAX(SIZE_OF_COLONY) as m
    FROM ECOLI_DATA
    GROUP BY YEAR(DIFFERENTIATION_DATE)
) AS p
ON YEAR(o.DIFFERENTIATION_DATE) = p.y
ORDER BY YEAR ASC, YEAR_DEV ASC
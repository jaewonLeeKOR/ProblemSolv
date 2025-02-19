SELECT ID
,CASE
    WHEN ecoli_rank BETWEEN total_count * 0 AND total_count * 0.25 THEN 'CRITICAL'
    WHEN ecoli_rank BETWEEN total_count * 0.25 AND total_count * 0.50 THEN 'HIGH'
    WHEN ecoli_rank BETWEEN total_count * 0.50 AND total_count * 0.75 THEN 'MEDIUM'
    WHEN ecoli_rank BETWEEN total_count * 0.75 AND total_count THEN 'LOW'
ELSE ecoli_rank END AS COLONY_NAME
FROM ECOLI_DATA
LEFT JOIN (
    SELECT ID,
    RANK() OVER(ORDER BY SIZE_OF_COLONY DESC) AS ecoli_rank,
    tc.total_count
    FROM ECOLI_DATA
    CROSS JOIN (
        SELECT
        COUNT(*) as total_count
        FROM ECOLI_DATA
    ) AS tc
) AS r
USING (ID)
ORDER BY ID ASC
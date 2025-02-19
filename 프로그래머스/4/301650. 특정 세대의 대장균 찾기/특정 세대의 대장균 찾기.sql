SELECT c.id
FROM ECOLI_DATA c
INNER JOIN (
    SELECT p.id AS p_id, p.parent_id
    FROM ECOLI_DATA p
    INNER JOIN (
        SELECT id AS pp_id
        FROM ECOLI_DATA
        WHERE parent_id IS NULL
    ) pp
    ON pp.pp_id = p.parent_id
) p
ON p.p_id = c.parent_id
ORDER BY ID ASC
SELECT COUNT(
    CASE WHEN LENGTH IS NULL THEN 1 ELSE NULL END
) AS FISH_COUNT
FROM FISH_INFO
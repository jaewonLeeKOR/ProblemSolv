SELECT COUNT(*) FISH_COUNT, fn.fish_name FISH_NAME
FROM fish_info f
LEFT JOIN fish_name_info fn
USING (fish_type)
GROUP BY fn.fish_name
ORDER BY FISH_COUNT DESC
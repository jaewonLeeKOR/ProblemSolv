SELECT ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO child_info
INNER JOIN (
SELECT t.item_id rare_parent_child_id
FROM ITEM_INFO parent_info
JOIN ITEM_TREE t
ON t.parent_item_id = parent_info.item_id
WHERE parent_info.rarity = 'RARE'
) parent_info
ON child_info.item_id = parent_info.rare_parent_child_id
ORDER BY item_id DESC
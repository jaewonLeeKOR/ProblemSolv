select i.ITEM_ID, i.ITEM_NAME, i.RARITY
from ITEM_INFO i
left join ITEM_TREE t
on i.item_id = t.parent_item_id
where t.parent_item_id is null
order by i.item_id desc
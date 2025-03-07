select i.ITEM_ID, i.ITEM_NAME
from ITEM_INFO i
left join ITEM_TREE t
using (ITEM_ID)
where t.PARENT_ITEM_ID is null
order by i.ITEM_ID asc
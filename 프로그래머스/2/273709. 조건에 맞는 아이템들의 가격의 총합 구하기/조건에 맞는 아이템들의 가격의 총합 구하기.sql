select SUM(i.price) TOTAL_PRICE
from item_info i
where i.RARITY = 'LEGEND'
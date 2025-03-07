with big_fish as (
    select fi.FISH_TYPE, max(fi.LENGTH) LENGTH
    from FISH_INFO fi
    group by fi.FISH_TYPE
)

select fi.ID, fni.FISH_NAME, bf.LENGTH
from big_fish bf
left join FISH_NAME_INFO fni
on bf.FISH_TYPE = fni.FISH_TYPE
left join FISH_INFO fi
on bf.FISH_TYPE = fi.FISH_TYPE AND bf.LENGTH = fi.LENGTH
order by fi.ID asc
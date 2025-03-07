with pre_processed_fish as (
    select id, fish_type, 
    (case when length is null then 10
    else length end) length
    , time
    from FISH_INFO
), big_fish_type as (
    select fish_type, avg(length) avg_length
    from pre_processed_fish
    group by fish_type
    having avg(length) > 33
)

select count(id) FISH_COUNT, max(fi.length) MAX_LENGTH, fi.FISH_TYPE
from FISH_INFO fi
left join big_fish_type bft
on fi.fish_type = bft.fish_type
where bft.avg_length is not null
group by fi.fish_type
order by fi.fish_type asc
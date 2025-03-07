with recursive res as (
	# 초기 조건
    select id, parent_id, 1 as generation
    from ecoli_data
    where parent_id is null
    union all # 반복 union
    select ecoli_data.id, ecoli_data.parent_id, res.generation + 1 # 반복 연산
    from res # 기존의
    join ecoli_data
    on res.id = ecoli_data.parent_id
)

select count(id) COUNT, GENERATION
from res
where res.id not in (
    select distinct parent_id
    from res
    where res.parent_id is not null
)
group by GENERATION
order by GENERATION asc
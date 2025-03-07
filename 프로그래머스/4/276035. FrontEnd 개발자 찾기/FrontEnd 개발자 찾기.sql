with front_bit as (
    select bit_or(code) as front_code
    from skillcodes
    where category = 'Front End'
)

select ID, EMAIL, FIRST_NAME, LAST_NAME
from DEVELOPERS, front_bit
where skill_code & front_code
order by id asc
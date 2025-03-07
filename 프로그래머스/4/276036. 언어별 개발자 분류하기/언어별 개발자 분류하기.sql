# A : FRONT, Python
# B : C#
# C : 그외 FRONT

with front_end as (
    select bit_or(code) fe_code from skillcodes where category='Front End'
), grades as (
    select
    case
        when
            d.skill_code & fe_code
                and
            d.skill_code & (select code from skillcodes where name='Python')
            then 'A'
        when
            d.skill_code & (select code from skillcodes where name='C#')
            then 'B'
        when
            d.skill_code & fe_code
            then 'C'
        else null
    end as GRADE
    , ID, EMAIL
    from DEVELOPERS d, front_end
)

select *
from grades
where grade is not null
order by grade asc, id asc
with dept_sal as (
    select dept_id, round(avg(sal), 0) AVG_SAL
    from HR_EMPLOYEES
    group by DEPT_ID
)
select DEPT_ID, DEPT_NAME_EN, AVG_SAL
from HR_DEPARTMENT
right join dept_sal
using (dept_id)
order by AVG_SAL desc
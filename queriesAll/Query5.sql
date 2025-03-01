select deg.dgname, deg.level 
from degrees deg
join departments dpt on dpt.dcode = deg.department_code
where dpt.dname = 'Computer Science'
order by deg.level;

select deg.dgname, deg.level 
from degrees deg
join offers o on o.dcode = deg.department_code
join departments dpt on o.dcode = dpt.dcode
where dpt.dname = 'Computer Science';

select c.cnumber, c.cname
from courses c
join departments d on c.department_code = d.dcode
where d.dname = 'Computer Science'
order by c.cnumber asc;

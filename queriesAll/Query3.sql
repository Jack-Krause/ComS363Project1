select c.cname, c.cnumber
from courses c
join offers o on o.cnumber = c.cnumber
join departments d on o.dcode = d.dcode
where d.dname = 'Computer Science'
order by c.cnumber asc;

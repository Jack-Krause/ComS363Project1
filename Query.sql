-- query 1
select p_addr from students
where name = "Gail";

-- query 2
select m.name, m.level
from major m
join students s on s.sid = m.sid
where s.name = 'Julie';

-- query 3
select c.cname, c.cnumber
from courses c
join offers o on o.cnumber = c.cnumber
join departments d on o.dcode = d.dcode
where d.dname = 'Computer Science'
order by c.cnumber asc;

-- query 4
select s.name
from students s
join register r on r.sid = s.sid
where r.regtime = 'Fall2022';



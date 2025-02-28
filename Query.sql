-- query 1
select p_addr from students
where name = "Gail";

-- query 2
select m.name, m.level
from major m
join students s on s.sid = m.sid
where s.name = "Julie";







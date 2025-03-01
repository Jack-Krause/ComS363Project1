select s.name, s.sid
from students s
join register r on r.sid = s.sid
join courses c on c.cnumber = r.course_number
join major m on s.sid = m.sid
where m.level in ('MS', 'PhD') and c.cname = 'database'
order by s.sid;

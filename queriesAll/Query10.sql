select c.cnumber, c.cname, count(r.sid)
from courses c
left join register r on r.course_number = c.cnumber
group by c.cnumber, c.cname
order by c.cnumber asc;

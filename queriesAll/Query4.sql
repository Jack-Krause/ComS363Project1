select s.name
from students s
join register r on r.sid = s.sid
where r.regtime = 'Fall2022';

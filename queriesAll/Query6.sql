select s.sid, s.name
from students s
join minor m on m.sid = s.sid
order by s.sid asc;

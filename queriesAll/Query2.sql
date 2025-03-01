select m.name, m.level
from major m
join students s on s.sid = m.sid
where s.name = 'Julie';

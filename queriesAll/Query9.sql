select s.name, s.sid, s.ssn
from students s
where name between 'Amy' and 'Gail'
    and s.sid = (
        select MIN(s2.sid)
        from students s2
        where s2.name = s.name
    )
order by s.name asc;

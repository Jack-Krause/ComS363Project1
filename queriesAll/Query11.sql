select count(distinct s.sid)
from students s
join (
    select sid
    from major
    where name = 'Software Engineering'
    union
    select sid
    from minor
    where name = 'Software Engineering'
) deg on s.sid = deg.sid
where s.gender = 'F';

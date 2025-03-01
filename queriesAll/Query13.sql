select name, level, count(*) as num_students
from 
(
    select sid, name, level from major
    union
    select sid, name, level from minor
) as degrees
group by name, level
having count(*) = 
(
    select count(*)
    from (
        select sid, name, level from major
        union
        select sid, name, level from minor
    ) as degreescount
    group by name, level
    order by count(*) desc
    limit 1
)
order by name;

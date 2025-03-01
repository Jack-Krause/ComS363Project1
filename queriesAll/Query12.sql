select name, level, count(*) as num_students
from major
group by name, level
having count(*) = 
(
    select count(*)
    from major
    group by name, level
    order by count(*) desc
    limit 1
)
order by name;

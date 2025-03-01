-- query 1:
select p_addr from students
where name = "Gail";

-- query 2:
select m.name, m.level
from major m
join students s on s.sid = m.sid
where s.name = 'Julie';

-- query 3:
select c.cname, c.cnumber
from courses c
join offers o on o.cnumber = c.cnumber
join departments d on o.dcode = d.dcode
where d.dname = 'Computer Science'
order by c.cnumber asc;

-- query 4:
select s.name
from students s
join register r on r.sid = s.sid
where r.regtime = 'Fall2022';

-- query 5:
select deg.dgname, deg.level 
from degrees deg
join offers o on o.dcode = deg.department_code
join departments dpt on o.dcode = dpt.dcode
where dpt.dname = 'Computer Science';

-- query 6:
select s.sid, s.name
from students s
join minor m on m.sid = s.sid
order by s.sid asc;

-- query 7:
select s.name, s.sid
from students s
join register r on r.sid = s.sid
join courses c on c.cnumber = r.course_number
join major m on s.sid = m.sid
where m.level in ('MS', 'PhD');

-- query 8:
select name, sid, ssn
from students
where name like '%n%' 
order by sid asc;

-- query 9:
select name, sid, ssn
from students
where name between 'Amy' and 'Gail'
order by name asc;

-- query 10:
select c.cnumber, c.cname, count(r.sid)
from courses c
left join register r on r.course_number = c.cnumber
group by c.cnumber, c.cname
order by c.cnumber asc;

-- query 11:
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

-- query 12:





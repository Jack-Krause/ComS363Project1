-- modification 1
update students
set name = 'Frank11'
where ssn = 144672271;

-- modification 2
update major m
join students s on s.sid = m.sid
set m.name = 'Computer Science', m.level = 'MS'
where s.sid = 144673371;

-- modification 3
delete from register
where regtime = 'Summer2024';

SET GLOBAL local_infile=1;


load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\students.csv'
into table students
fields terminated by ','
enclosed by '"' 
lines terminated by '\r\n'
ignore 1 rows
(sid, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone);
-- (sid, ssn, @col3, @col4, @col5, @col6, @col7, @col8, @col9);


load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\departments.csv'
into table departments
fields terminated by ','
enclosed by '"'
lines terminated by '\r\n'
ignore 1 rows
(dcode, dname, phone, college);


load data local infile 'C:\\Users\\jackm\\OneDrive\\CS3630\\Project1\\courses.csv'
into table courses
fields terminated by ','
enclosed by '"'
lines terminated by '\r\n'
ignore 1 rows
(cname, cnumber, description, credithours, level, department_code);

select * from departments;
select * from students;
select * from courses;

create database fsd_java_march_25;
use fsd_java_march_25;

create table employee
(emp_id int primary key auto_increment, 
emp_name varchar(255), 
branch varchar(255), 
department text, 
salary double);

INSERT INTO EMPLOYEE (emp_id, emp_name, branch, department, salary) VALUES
(1, 'Amit Sharma', 'Mumbai', 'HR', 75000.00),
(2, 'Priya Patel', 'Delhi', 'IT', 82000.50),
(3, 'Rajesh Kumar', 'Bangalore', 'Finance', 90000.75),
(4, 'Neha Verma', 'Mumbai', 'HR', 78000.25),
(5, 'Vikram Singh', 'Delhi', 'IT', 85000.00),
(6, 'Anjali Gupta', 'Bangalore', 'Finance', 92000.40),
(7, 'Manish Tiwari', 'Mumbai', 'HR', 76000.90),
(8, 'Kavita Reddy', 'Chennai', 'IT', 89000.30),
(9, 'Arjun Nair', 'Bangalore', 'Finance', 94000.20),
(10, 'Sneha Iyer', 'Chennai', 'HR', 77000.10),
(11, 'Suresh Pillai', 'Mumbai', 'IT', 81000.60),
(12, 'Divya Menon', 'Delhi', 'Finance', 93000.80),
(13, 'Ravi Shankar', 'Chennai', 'HR', 74000.55),
(14, 'Pooja Deshmukh', 'Bangalore', 'IT', 86000.95),
(15, 'Karan Mehta', 'Mumbai', 'Finance', 97000.70),
(16, 'Meera Joshi', 'Delhi', 'HR', 73000.15),
(17, 'Akash Bansal', 'Chennai', 'IT', 88000.45),
(18, 'Rohan Agarwal', 'Delhi', 'Finance', 95000.60),
(19, 'Swati Saxena', 'Bangalore', 'HR', 72000.85),
(20, 'Vivek Choudhary', 'Chennai', 'Finance', 96000.90);


-- sql programming : (variable, loops, conditional statements, functions) -- pl/sql 

-- Stored procedure 
/*
	working with variables 
*/
-- creating the proc
DELIMITER $$
create procedure emp_sal_increment()
BEGIN
	-- declare variables
	DECLARE ename varchar(255);
    DECLARE esalary double default 0;
    DECLARE final_salary double; 
    -- Assign values
    SET ename='harry potter';
    SET esalary = 140000;
    
    -- calculate increment - 8%
    SET final_salary = esalary + (esalary * 0.08);
    
    -- display the output 
    select CONCAT('Emp Name: ' , ename , '        Final Salary: ', final_salary) as final_output; 
END
$$

-- drop proc
drop procedure emp_sal_increment;
-- call the proc 
CALL emp_sal_increment()

DELIMITER $$
/* create a stored procedure to update the salary of employee. take ID and increment as parameters and update the salary in the table. */
create procedure update_emp_salary(IN param_id int,IN param_increment double) 
BEGIN
	DECLARE variable_sal double;
    DECLARE final_salary double; 
    
	-- fetch current salary 
    select salary into variable_sal from employee where emp_id=param_id;
    SET final_salary = variable_sal + (variable_sal*(param_increment/100));
    
	update employee SET salary=final_salary where emp_id=param_id;
	select CONCAT('Salary of Employee with id: ', param_id, ' has been updated') as output;
END; 
$$

CALL update_emp_salary(1,10);

-- loops 
DELIMITER $$
CREATE PROCEDURE display() 
BEGIN 
	declare i int default 0;
	declare result text default ''; 
    
    my_loop:LOOP
		IF i >5 THEN
			LEAVE my_loop; 
		END IF; 
		SET result = CONCAT(result , (i+1) , '  ' );
        SET i = i+1;
    END LOOP my_loop;	
    select result; 
END; 
$$
drop procedure display;
CALL display();


DELIMITER $$
CREATE PROCEDURE displayWithWhile() 
BEGIN 
	declare i int default 0;
	declare result text default ''; 
    
    while i<5 do
		SET result = CONCAT(result , (i+1) , '  ' );
        SET i = i+1;
    END while; 	
    select result; 
END; 
$$
drop procedure displayWithWhile;
CALL displayWithWhile();

/**
CAP to update salaries of all employees using LOOP (one by one) 
as per following criteria

department		increment
----------		---------
	HR				5%
    IT				7%
    FINANCE			10%
    
*/
DELIMITER $$
create PROCEDURE updateSal(IN param_dept varchar(255),IN param_increment double)
BEGIN
	declare cnt int; 
    declare i int default 0;
    declare eid int;
    declare edept varchar(255); 
	select count(*) into cnt from employee; 
		while i<cnt do
			select emp_id,department into eid,edept from employee LIMIT i,1; 
			if edept = param_dept THEN
				update employee SET salary = salary + (salary * (param_increment/100)) where emp_id = eid;
                -- select 'record updated id: ' , eid;
			END IF;
		SET i = i + 1;
		end while;
END;
$$
drop procedure updateSal;
call updateSal("HR", 5);

DELIMITER $$
create procedure displayRecords(IN param_tbl_name varchar(255), IN param_orderby varchar(255) , IN param_limit int)
BEGIN
	SET @query = CONCAT('select * from ' , param_tbl_name , ' order by ',  param_orderby, ' LIMIT ' , param_limit);
    PREPARE stmt FROM @query;
    EXECUTE stmt; 
    deallocate prepare stmt;
END;
$$

drop procedure displayRecords;
call displayRecords("employee")

/* create a select statement with where clause for a table 
	call procName("<tbl_name>", "column_name", "column_value") 
 */
delimiter $$
create procedure displayGeneral(IN param_tb_name varchar(255),IN param_columname varchar(255),IN param_columnvalue varchar(255)) 
begin
  set @query = concat('select * from ',param_tb_name, ' where ',param_columname,' = "' ,param_columnvalue, '" ');
  prepare stmt from @query;
  execute stmt;
  deallocate prepare stmt;
end;
$$
drop procedure displayGeneral;
call  displayGeneral("employee", "branch","mumbai"); 

-- ex of out param 
delimiter $$
create procedure outProc(OUT cnt INT)
BEGIN
	select count(emp_id) into cnt from employee;
END;
$$

-- session variable 
SET @num_employee = 0;
CALL outProc(@num_employee);
select @num_employee;

-- Triggers 
/*
DB : proc --- salary update ---update/insert/delete 
employee 

employee_log (id,old_salary, new_salary, date , user)  --- 

--- all log records --- proc
*/

/*
create trigger <trigger_name> 
BEFORE UPDATE ON employee
FOR EACH ROW ---> 
BEGIN
	--sql : insert record in employee_log 
END
*/

create table employee_log (id int primary key auto_increment,old_salary double, new_salary double, dateOfOp date , username varchar(255) );

DELIMITER $$ 
create trigger emp_salary_update_trigger
BEFORE UPDATE ON employee
FOR EACH ROW
BEGIN
		INSERT INTO employee_log(old_salary,new_salary,dateOfOp,username) 
		values (OLD.salary,NEW.salary,now(), user()) ;
END;
$$
drop trigger emp_salary_update_trigger;

update employee SET salary=200000 where emp_id=2;

select user();
select current_user();
select session_user();

-- views 
/* create view to hide salary info from employee table */

create or replace view employee_details AS
select emp_id,emp_name,branch,department 
from employee
where department <> 'FINANCE';

/* NO GRANT ON EMPLOYEE 
   GRANT ON employee_details
*/

select * from employee_details; 

-- functions 
/* functions can return a value (must return a value) 
   procedures cannot return a value(workaround-use OUT param)
*/
DELIMITER $$ 
create function emp_func(param_id int)  RETURNS double
DETERMINISTIC 
BEGIN
    declare esal double;
    
	select salary into esal
    from employee
    where emp_id = param_id;

	return esal;
END
$$ 

DELIMITER $$ 
create function current_date_func() returns date     -- by default, NOT DETERMINISTIC
 BEGIN
	declare curr date; 
    select NOW() into curr; 
	return curr;
END;
$$ 
drop function current_date_func;
/*
select NOW() 
RAND() 
*/
-- calling the func
select emp_func(3) AS "Salary value";
select current_date_func() AS "CURRENT_DATE";



-- set global log_bin_trust_function_creators = 1;
 /*
 1	7	12:15:01	set global log_bin_trust_function_creators = 1	0 row(s) affected, 1 warning(s):
 1287 '@@log_bin_trust_function_creators' is deprecated and will be removed in a future release.	0.093 sec
 */

/*
A routine is considered “deterministic” if it always produces the same result for the same input parameters, 
and “not deterministic” otherwise. If neither DETERMINISTIC nor NOT DETERMINISTIC is given in the routine definition, 
the default is NOT DETERMINISTIC. 

To declare that a function is deterministic, you must specify DETERMINISTIC explicitly.
*/

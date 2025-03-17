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
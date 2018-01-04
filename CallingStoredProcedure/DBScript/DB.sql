use hibernatedb;
DELIMITER //
DROP PROCEDURE IF EXISTS getEmployeeInfoById;
CREATE PROCEDURE getEmployeeInfoById(IN empId INT)
BEGIN
	SELECT employee_id as empid,bonus,dept_name as deptName,date_of_joining as doj,email,employee_name as employeeName, salary FROM employee_table WHERE employee_id=empId;
END //
DELIMITER ;
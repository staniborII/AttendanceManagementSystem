Genesis V2":
To Implement:

- Log all admin activities
- Log other user activities
- all requests from frontend should come with the currently logged in person

- Implement Spring Security
- Password Encryption
- Testing and Error Handling
- Migrate business logic in UserApi to the respective **Dao classes
- Rethink logic for assignment of User and Admin Roles 
  (e.g. separate FrontEnd pages for admin and user creation)



Implemented:
- Add employee address to form
- If admin adds registers new employee he can select the role (revert)
- If new employee adds themselves, the role is set to USER
- Implementation of timecard table on frontend and timesheet object on backend
- One to Many relationship between Employee object and timeSheet object
- clockIn and clockOut mehtods implemented in EmployeeServiceImpl and EmployeeDAOImpl
- TimeSheet list/s is/are added as soon as the Employee object is created
- One to One relationship between employee entity and user login entity
- On to many relationship between employee entity and timesheet entity
- Employee registration: includes phone number
- Multiple hibernate_sequences using sequence strategy for tables in db
- clock in and clock out methods in employee dao
- Some Validators
- On registration of new employee from Front-End, on the Back-End an auto username and password is created
- Changed date format dd-mm-yyyy
- Catch and handle primary/duplicate key violation constraint when a user that already
  exists is being added to the database
- Automatic generation of username and password on registration of employee
Note: There are some not fully functional modules in this version
	node_modules folder needed

Updates:
1. Login implemented with connection to backend
2. Login component integrated with other components e.g. create user, user details etc
3. Login uses auth-guard service to make sure the user has access to other components only when they are loggged in
   and if a user tries to access another component through a url e.g ./users or ./create-user, without being logged in,
   the user is redirected to the login page
4. Authorization gaurd implemented: No access to page unless logged in
5. Admin gaurd implemented: No access to admin pages unless logged in as an admin
6. Implementation of timecard table on frontend
7. Synchronised all admin functions into an admin folder
8. Create nonAdmin component for ordinary users and configure the routing
9. When employee logs in, his employee id is used to retrieve only his details from the database
10. Log all users out after x period of inactivity
11. Generate multiple hibernate sequences and confirm password functionality, can employee id be added to login?
12. Multiple users and admin can be created by an admin
13. Employee should be able to change username and password because on registraiton
  of a new employee, the username and password is automatically generated (Executed on backend)
14. User can register themselves and default credential is created for them
15. User can able to update his/her details
16. Admin can create an admin



TODO:
*Should a user who is not an admin be able to change his password in his view?

- Reset user credentials
  Flow:
  - User should enter email address and then a to reset the password should be sent to the email address
  - The link should open a component where the user can choose new user name and password
  - new username and password should be updated in db

- After registration navigate to congratulations page and inform user to check email
  to see default credentials. There should be a link to change credentials to what the user wants

- Implement SSL and https
- Log all admin activities
- Log other user activities
- Add address
- Implement Security measures
- Implement Password Encryption
- Error Handling (frontend) and Unit tests
- Reset password functionality
- Implement Confirm Password functionality
- On page where you can see all employees, add a search bar
- Add information in meta tag in index.html (Check again on FrontEnd)
- Seperate FrontEnd pages for admin and normal user creation (Create admin creation form)
- Discuss with team multiple admin issues (admin can see other admins and can delete them, 
  this should be restricted), suggest a super admin
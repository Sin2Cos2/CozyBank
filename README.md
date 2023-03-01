# Description
Back-end application for a bank. In this project, a
simple Rest API was implemented, with the main goal
of learning and applying Spring Security.
Technologies such as session-based authentication,
JWT token, OAuth2 and OpenConnect were studied.

---

## Functionality

### GET requests

- Get account details
    
    >/account
    >> int accountId;

- Get card details
    
    >/cards
    >> int accountId;
   
- Get balance details
    
    >/balance
    >> int accountId;

- Get loans details
    
    >/loans
    >> int accountId;

- Get active notices

    >/notices
    >> this endpoint is cached for 30 seconds

- Get user
  
  >/user
  >> This endpoint should be used to authenticate user.
  >> After successful authentication a JWT token will be returned in headers. 
    
### POST requests

- Save contact inquiry details
  >/contact
  #### Request body:
      List of contacts:

      String contactId;
      String contactName;
      String contactEmail;
      String subject;
      String message;
      Date createDt;

- Register a user
  >/register
  #### Request body: 

      String name;
      String email;
      String mobileNumber;
      String password;
      String role;
      String createDate;

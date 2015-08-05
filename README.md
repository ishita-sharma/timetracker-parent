# timetracker-parent

Restful Web Service for time tracking 
--------------------------------------
--------------------------------------
***Technologies used: Maven, Spring, JDBC***

Steps for deployment:
 - Check out maven modules directly from SCM
 - Build > 'mvn clean package' on the parent directly
 - Use the WAR in "-web" module to deploy to tomcat.
 
Steps for testing/executing:
  - Create Customer:
    - http://localhost:8080/timetracker-web/track/create/customer?customerName=xyz
  - Create user for the customer:
    - http://localhost:8080/timetracker-web/track/create/user?customerName=xyz&userName=abc
  - Start a timer:
   - http://localhost:8080/timetracker-web/track/start?userName=abc&customerName=xyz   
   - recieve a unique tracking id in the response
  - After these initial steps you can perform these opertaions in any order
       - View the status of a timer: 
        - http://localhost:8080/timetracker-web/track/status?trackingId=72eebc70-3465-40be-a35a-f2d7fe349b25
       - View history of a user: 
        - http://localhost:8080/timetracker-web/track/user/history?userName=abc&customerName=xyz
       - Stop a timer: 
        - http://localhost:8080/timetracker-web/track/stop?trackingId=72eebc70-3465-40be-a35a-f2d7fe349b25

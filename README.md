# timetracker-parent

Restful Web Service for time tracking 

Technologies used: Maven, Spring,  

After deployment:
  1. http://localhost:8080/timetracker-web/track/create/customer?customerName=xyz
  2. http://localhost:8080/timetracker-web/track/create/user?customerName=xyz&userName=abc
  3. http://localhost:8080/timetracker-web/track/start?userName=abc&customerName=xyz   
      //you will recieve a unique tracking id
  4. After these initial steps you can perform these opertaions in any order
      4.1. http://localhost:8080/timetracker-web/track/status?trackingId=72eebc70-3465-40be-a35a-f2d7fe349b25
      4.2. http://localhost:8080/timetracker-web/track/user/history?userName=abc&customerName=xyz
      4.3

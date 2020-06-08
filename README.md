# sb-project
This is the project that is built using technologies like spring boot, hibernate and spring data jpa. 

Built for learning purpose, but it still has some things to share. 

WHAT THIS PROJECT HAS ? 
-----------------------
1) This project has signup [adding user ] with validation like if user with username or email already exists then it will not allow. 

2) Email has been verified, only after verifying the email , it will change active status to 1 which means you can now login .

3) Password has been encrypted using Bcyrpt. 

3) Has the functionality of login, when the combination of  ( username and password )  or ( email and password ) this allows login. 

4) CRUD operations for the users.

5) user can add products and has associations in between users and products table [ OneToMany ]

6) Various CRUD operations for the products , listing products based on productId and userId and so on.

7) Has used the sms sending api [twilio ] which sends the sms to the number. After user buy the products then it sends 
the message to the user's phone number with some message [ like thank you for shopping with us ] .

8) If it's birthday of user , it send message like [happy birthday username ...] at that day [at scheduled time 8am for eg.]

9) Has login with facebook functionality [ however no extra work has been done with storing and tracking that user ... to be updated ]

10) Spring security has been added to secure endpoints , which endpoints to allow to the user and which to others [ admin for eg ] 
....
....
to be continued. 

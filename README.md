# spring-boot-ld

This project has been developed with spring boot , hibernate and JPA . This was developed for learning purposes but it has got something to share.

WHAT THIS PROJECT HAS ?
----------------------

Everything is just endpoint here [ REST API ]

1) Has signup [adding users ] with validation like if username or email already exists then dont allow.

2) Verify email [ only after email verification change the active status to 1 which means now can login ]

3) Password has been encrypted using bcrypt .

4) Allow login , if ( username or password ) OR ( password and email ) matches. 

5) Various CRUD operations for the USERS [ for eg. we can assume an ADMIN can delete users depends on what we want ]

6) User can add products [ OneToMany relationship ] and perform various CRUD Operations on products. 

7) Has SMS sending capability [ I have used twilio ] which can send the sms to the user's phone number with some message after the user 
buys some prouducts [ custom message ]

8) Can send birthday with to the user at scheduled time [ say 8am for eg ] if it's his or her birthday.

9)Can login with facebook functionality [ however nothing has been done to track users & store their credentials ] just to check the login function.

.................... to be continued

WELL, HOW TO RUN THIS PROJECT ? 
-----------------------------------

1) clone or download this project. 

2) import this as existing maven project . 

3) First change things that are in properties file like i can't share my twilio secret_id and facebook's id, gmail username and password. 

4) check if something has to be change in config file and service implementation file .

5) Now create the database with name specified in application.properties file in datasource url. No need to create table as they will be 
created automatically by hibernate and jpa based on entity class. 

6) I guess that's all you need to know. 

7) Understand and run the projcet with various endpoints in controller class to see how project works. 

8) Give me feedbacks in this code , where and what can I imporove. 

---------

/*----------------------------------------------------------------------------------------*/

/* insert ACCOUNTS START*/

USE QUIZ_DB;

Insert into accounts 
(username, first_name, last_name, mail, gender, 
password, image,date_created, is_deleted, is_banned, num_points, is_admin)
values 
('vika_shonia', 'vika', 'shonia', 'vshon17@freeuni.edu.ge', 'Female',
'pass', null, sysdate(), false, false, 100, true);

USE QUIZ_DB;

Insert into accounts 
(username, first_name, last_name, mail, gender, 
password, image,date_created, is_deleted, is_banned, num_points, is_admin)
values 
('ziki', 'zviad', 'nozadze', 'znoza17@freeuni.edu.ge', 'Male',
'passcode', null, curresysdatent_date(), false, true, 5.53, true);

USE QUIZ_DB;

Insert into accounts 
(username, first_name, last_name, mail, gender, 
password, image,date_created, is_deleted, is_banned, num_points, is_admin)
values 
('jikksi', 'gio', 'jikia', 'gjiki17@freeuni.edu.ge', 'Male',
'password', null, sysdate(), false, false, 500, true);

/* insert ACCOUNTS END*/

/*----------------------------------------------------------------------------------------*/

/* insert achievements START*/

USE QUIZ_DB;

Insert into achievements 
(name, num_points, description)
values 
('Newbie', 50, 'user who just started quizzes');

USE QUIZ_DB;

Insert into achievements 
(name, num_points, description)
values 
('Specialsit', 250, 'user who is pretty good at quizzes');

USE QUIZ_DB;

Insert into achievements 
(name, num_points, description)
values 
('Master', 1000, 'user who is really great quizzes');

USE QUIZ_DB;

Insert into achievements 
(name, num_points, description)
values 
('Quizaholic', 5000, 'user who is in love with quizzes');

/* insert achievements END*/

/*----------------------------------------------------------------------------------------*/

/* insert achievings START*/

USE QUIZ_DB;

insert into achievings
(achievment_id, account_id, date_achieved)
values
((select id from achievements where name = 'Quizaholic'), 
(select id from accounts where username = 'vika_shonia'), sysdate());

USE QUIZ_DB;

insert into achievings
(achievment_id, account_id, date_achieved)
values
((select id from achievements where name = 'Specialsit'), 
(select id from accounts where username = 'ziki'), sysdate());

USE QUIZ_DB;

insert into achievings
(achievment_id, account_id, date_achieved)
values
((select id from achievements where name = 'Master'), 
(select id from accounts where username = 'jikksi'), sysdate());

USE QUIZ_DB;

insert into achievings
(achievment_id, account_id, date_achieved)
values
((select id from achievements where name = 'Newbie'), 
(select id from accounts where username = 'jikksi'), sysdate());

/* insert achievings END*/

/*----------------------------------------------------------------------------------------*/

/* insert START*/

/* insert END*/

/*----------------------------------------------------------------------------------------*/

/* insert START*/

/* insert END*/

/*----------------------------------------------------------------------------------------*/

/* insert START*/

/* insert END*/

/*----------------------------------------------------------------------------------------*/

/* insert START*/

/* insert END*/

/*----------------------------------------------------------------------------------------*/

/* insert START*/

/* insert END*/

/*----------------------------------------------------------------------------------------*/

/* insert START*/

/* insert END*/

/*----------------------------------------------------------------------------------------*/


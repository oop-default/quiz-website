/* >>>>>>> SCENARIO TESTS FROR ALL TABLES AND ALL KEY CONNECTIONS <<<<<<<<< */

/*----------------------------------------------------------------------------------------*/

/* insert ACCOUNTS START*/

USE QUIZ_DB;

Insert into accounts 
(username, first_name, last_name, mail, gender, 
password, image, num_points, date_created, is_deleted, is_banned, is_admin, is_active)
values 
('vika_shonia', 'vika', 'shonia', 'vshon17@freeuni.edu.ge', 'Female',
'pass', null, 100, sysdate(), false, false, true, false);

USE QUIZ_DB;

Insert into accounts 
(username, first_name, last_name, mail, gender, 
password, image, num_points, date_created, is_deleted, is_banned, is_admin, is_active)
values 
('ziki', 'zviad', 'nozadze', 'znoza17@freeuni.edu.ge', 'Male',
'passcode', null, 5.53, sysdate(), false, true, true, false);

USE QUIZ_DB;

Insert into accounts 
(username, first_name, last_name, mail, gender, 
password, image, num_points, date_created, is_deleted, is_banned, is_admin, is_active)
values 
('gioJikia', 'gio', 'jikia', 'gjiki17@freeuni.edu.ge', 'Male',
'password', null, 500, sysdate(), false, false, true, true);

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
(select id from accounts where mail = 'vshon17@freeuni.edu.ge'), sysdate());

USE QUIZ_DB;

insert into achievings
(achievment_id, account_id, date_achieved)
values
((select id from achievements where name = 'Specialsit'), 
(select id from accounts where mail = 'znoza17@freeuni.edu.ge'), sysdate());

USE QUIZ_DB;

insert into achievings
(achievment_id, account_id, date_achieved)
values
((select id from achievements where name = 'Master'), 
(select id from accounts where mail = 'gjiki17@freeuni.edu.ge'), sysdate());

USE QUIZ_DB;

insert into achievings
(achievment_id, account_id, date_achieved)
values
((select id from achievements where name = 'Newbie'), 
(select id from accounts where mail = 'gjiki17@freeuni.edu.ge'), sysdate());

/* insert achievings END*/

/*----------------------------------------------------------------------------------------*/

/* insert categories START*/
USE QUIZ_DB;

insert into categories
(category)
values 
('Geography');

USE QUIZ_DB;

insert into categories
(category)
values 
('Math');

USE QUIZ_DB;

insert into categories
(category)
values 
('Culinary');

USE QUIZ_DB;

insert into categories
(category)
values 
('Programming');

USE QUIZ_DB;

insert into categories
(category)
values 
('History');

/* insert categories END*/

/*----------------------------------------------------------------------------------------*/

/* insert quizzes START*/

USE QUIZ_DB;

insert into quizzes
(tittle, author_id, description, date_created, category_id, num_points)
values
('Capital cities', 
(select id from accounts where mail = 'znoza17@freeuni.edu.ge'),
'Quiz is about capital cities of countries, check how well you know them',
sysdate(), 
(select id from categories where category = 'Geography'), 50);

USE QUIZ_DB;

insert into quizzes
(tittle, author_id, description, date_created, category_id, num_points)
values
('GRE exam simulation', 
(select id from accounts where mail = 'vshon17@freeuni.edu.ge'),
'Quiz is like GRE exam with real GRE question',
sysdate(), 
(select id from categories where category = 'Math'), 100);

insert into quizzes
(tittle, author_id, description, date_created, category_id, num_points)
values
('Significant WW II political moves', 
(select id from accounts where mail = 'gjiki17@freeuni.edu.ge'),
'Quiz will check how well you know reasons and outcomes of political moves',
sysdate(), 
(select id from categories where category = 'History'), 150);

/* insert quizzes END*/

/*----------------------------------------------------------------------------------------*/

/* insert tags START*/

insert into tags
(tag)
values
('WW2');

insert into tags
(tag)
values
('politics');

insert into tags
(tag)
values
('cities');

insert into tags
(tag)
values
('countries');

insert into tags
(tag)
values
('quantitive thinking');

insert into tags
(tag)
values
('math');

insert into tags
(tag)
values
('GRE');

insert into tags
(tag)
values
('math exam');

/* insert tags END*/

/*----------------------------------------------------------------------------------------*/

/* insert taggings START*/

insert into taggings
(tag_id, quiz_id)
values
((select id from tags where tag = 'countries'),
(select id from quizzes where tittle = 'Capital cities'));

insert into taggings
(tag_id, quiz_id)
values
((select id from tags where tag = 'cities'),
(select id from quizzes where tittle = 'Capital cities'));

insert into taggings
(tag_id, quiz_id)
values
((select id from tags where tag = 'politics'),
(select id from quizzes where tittle = 'Significant WW II political moves'));

insert into taggings
(tag_id, quiz_id)
values
((select id from tags where tag = 'WW2'),
(select id from quizzes where tittle = 'Significant WW II political moves'));

insert into taggings
(tag_id, quiz_id)
values
((select id from tags where tag = 'math exam'),
(select id from quizzes where tittle = 'GRE exam simulation'));

insert into taggings
(tag_id, quiz_id)
values
((select id from tags where tag = 'GRE'),
(select id from quizzes where tittle = 'GRE exam simulation'));


insert into taggings
(tag_id, quiz_id)
values
((select id from tags where tag = 'math'),
(select id from quizzes where tittle = 'GRE exam simulation'));

/* insert taggings END*/

/*----------------------------------------------------------------------------------------*/

/* insert questions START*/

insert into questions
(type, question, quiz_id, num_points, image)
values
('True/False', 'World War 2 started on the 1 September 1939', 
(select id from quizzes where tittle = 'Significant WW II political moves'),
5, null);

insert into questions
(type, question, quiz_id, num_points, image)
values
('True/False', 'The attack on Pearl Harbour brought the US into the war', 
(select id from quizzes where tittle = 'Significant WW II political moves'),
5, null);

insert into questions
(type, question, quiz_id, num_points, image)
values
('True/False', 'World War 2 ended in the year 1946', 
(select id from quizzes where tittle = 'Significant WW II political moves'),
5, null);

insert into questions
(type, question, quiz_id, num_points, image)
values
('Fill-in-the-blank', 'What is Capital of Brazil?', 
(select id from quizzes where tittle = 'Capital cities'),
5, null);

insert into questions
(type, question, quiz_id, num_points, image)
values
('True/False', 'Capital of Georgia is Tbilisi', 
(select id from quizzes where tittle = 'Capital cities'),
5, null);

/* insert questions END*/

/*----------------------------------------------------------------------------------------*/

/* insert answers START*/

insert into answers
(answer, is_correct, question_id)
values
('True', true, 1);

insert into answers
(answer, is_correct, question_id)
values
('False', false, 1);

insert into answers
(answer, is_correct, question_id)
values
('True', true, 2);

insert into answers
(answer, is_correct, question_id)
values
('False', false, 2);

insert into answers
(answer, is_correct, question_id)
values
('True', false, 3);

insert into answers
(answer, is_correct, question_id)
values
('False', true, 3);

insert into answers
(answer, is_correct, question_id)
values
('Brasilia', true, 4);

insert into answers
(answer, is_correct, question_id)
values
('Batumi', false, 5);

insert into answers
(answer, is_correct, question_id)
values
('Tbilisi', true, 5);

/* insert answers END*/

/*----------------------------------------------------------------------------------------*/

/* insert answers history START */

insert into history
(account_id, quiz_id, num_points, time_spent, date_taken)
values
(1, 1, 10.3, 27, sysdate());

insert into history
(account_id, quiz_id, num_points, time_spent, date_taken)
values
(2, 1, 8.3, 37, sysdate());

insert into history
(account_id, quiz_id, num_points, time_spent, date_taken)
values
(2, 2, 50, 10, sysdate());

insert into history
(account_id, quiz_id, num_points, time_spent, date_taken)
values
(3, 3, 9.3, 20, sysdate());

/* insert answers history END */

/*----------------------------------------------------------------------------------------*/

/* insert  notes START */

/*id : vika 1 , zviki 2, gio 3*/
USE QUIZ_DB;

insert into notes
(note, sender_id, reciever_id, date_sent, is_seen)
values
('Wazzzzuuuppp?', 1, 2, sysdate(), true);

insert into notes
(note, sender_id, reciever_id, date_sent, is_seen)
values
('nthng U?', 2, 1, sysdate(), true);

insert into notes
(note, sender_id, reciever_id, date_sent, is_seen)
values
('me 2', 1, 3, sysdate(), true);

insert into notes
(note, sender_id, reciever_id, date_sent, is_seen)
values
('are u codeing?', 2, 3, sysdate(), true);

insert into notes
(note, sender_id, reciever_id, date_sent, is_seen)
values
('how r u', 3, 2, sysdate(), true);

insert into notes
(note, sender_id, reciever_id, date_sent, is_seen)
values
('u? :)', 3, 1, sysdate(), true);

/*

*/

/* insert  notes END */

/*----------------------------------------------------------------------------------------*/

/* insert friend_requests START */

insert into friend_requests
(sender_id, reciever_id, date_sent, is_seen)
values
(1, 2, sysdate(), true);

insert into friend_requests
(sender_id, reciever_id, date_sent, is_seen)
values
(2, 3, sysdate(), true);

/* insert friend_requests END */

/*----------------------------------------------------------------------------------------*/

/* insert friends START */

insert into friends
(first_id, second_id)
values
(1, 2);

insert into friends
(first_id, second_id)
values
(2, 3);

/* insert friends END */

/*----------------------------------------------------------------------------------------*/

/* insert challenges START */

insert into challenges
(quiz_id, status, sender_id, reciever_id, date_sent, is_seen)
values
(2, 'accepted', 2, 3, sysdate(), true);

insert into challenges
(quiz_id, status, sender_id, reciever_id, date_sent, is_seen)
values
(1, 'accepted', 1, 2, sysdate(), true);

/* insert challenges END */

/* ---------------------------------------------------------------------------------------*/

/* insert news START */

insert into news
(author_id, tittle, description)
values
(1, 'coca cola open cup', 'here is description of coca cola cup, winner will get bunch of coca colas.');

insert into news
(author_id, tittle, description)
values
(2, 'fanta open cup', 'here is description of fanta cup, winner will get bunch of fantas.');

insert into news
(author_id, tittle, description)
values
(3, 'sprite open cup', 'here is description of sprite cup, winner will get bunch of sprites.');

/* insert news END */

/*----------------------------------------------------------------------------------------*/




/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>   THE END   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
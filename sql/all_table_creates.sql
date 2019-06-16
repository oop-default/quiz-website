/*THIS FILE IS ONLY USED TO EXECUTE ALL CREATES AT SAME TIME */
USE QUIZ_DB;

CREATE SCHEMA `QUIZ_DB` ;

DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR (255) NOT NULL,
    first_name VARCHAR (255) NOT NULL, 
    last_name VARCHAR (255) NOT NULL,
    mail VARCHAR (255) NOT NULL,
    gender VARCHAR(50) NOT NULL, /* 'Male', 'Female' or 'Other' */
    password VARCHAR (255) NOT NULL,
    image LONGBLOB,
    date_created DATETIME NOT NULL,
    is_deleted BOOLEAN,
    is_banned BOOLEAN,
    num_points DOUBLE,
    is_admin BOOLEAN,

    UNIQUE KEY (mail),
    UNIQUE KEY (username)
);

USE QUIZ_DB;

DROP TABLE IF EXISTS achievements;

CREATE TABLE achievements (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR (255) NOT NULL,
    num_points DOUBLE NOT NULL, /* needed to get it */
    description TEXT,

    UNIQUE KEY (name)
);

USE QUIZ_DB;

DROP TABLE IF EXISTS achievings;

CREATE TABLE achievings (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    achievment_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    date_achieved DATETIME NOT NULL,

    UNIQUE KEY (account_id, achievment_id)
);

USE QUIZ_DB;

DROP TABLE IF EXISTS answers;

CREATE TABLE answers (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    answer TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL,
    question_id INTEGER NOT NULL
);

USE QUIZ_DB;

DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR (255) NOT NULL,

    UNIQUE KEY (category)
);

USE QUIZ_DB;

DROP TABLE IF EXISTS challenges;

CREATE TABLE challenges (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    quiz_id INTEGER NOT NULL,
    status VARCHAR (55) NOT NULL, /*acepted, denied or smth*/
    notification_id INTEGER NOT NULL,

    UNIQUE KEY (quiz_id, notification_id)
);

USE QUIZ_DB;

DROP TABLE IF EXISTS friend_requests;

CREATE TABLE friend_requests (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    notification_id INTEGER NOT NULL
);

USE QUIZ_DB;

DROP TABLE IF EXISTS friends;

CREATE TABLE friends (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_id INTEGER NOT NULL,
    second_id INTEGER NOT NULL,

    UNIQUE KEY (first_id, second_id)
);

/*
    IT MAY CHANGE 
    status coud be done with another table of statuses
    or ENUM('Accepted', 'Pending', 'Denied') i think using 
    enum would be harder to accociate numbers with statuses 
    and then insert or get data from tables 
*/

USE QUIZ_DB;

DROP TABLE IF EXISTS history;

/* History of past taken quizzes. */

CREATE TABLE history (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_id INTEGER NOT NULL,
    quiz_id INTEGER NOT NULL,
    num_points DOUBLE,
    time_spent BIGINT,
    date_taken DATETIME 
);

USE QUIZ_DB;

DROP TABLE IF EXISTS notes;

CREATE TABLE notes (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    note TEXT NOT NULL,
    notification_id INTEGER NOT NULL
);

USE QUIZ_DB;

DROP TABLE IF EXISTS notifications;

CREATE TABLE notifications (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    sender_id INTEGER NOT NULL,
    reciever_id INTEGER NOT NULL,
    date_sent DATETIME NOT NULL,
    is_seen BOOLEAN
);

USE QUIZ_DB;

DROP TABLE IF EXISTS questions;

CREATE TABLE questions (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(55) NOT NULL, /* type may be multiple choice, etc. */
    question TEXT NOT NULL,
    quiz_id INTEGER NOT NULL,
    num_points DOUBLE NOT NULL,
    image LONGBLOB
);

USE QUIZ_DB;

DROP TABLE IF EXISTS quizzes;

CREATE TABLE quizzes (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tittle VARCHAR(255) NOT NULL,
    author_id INTEGER NOT NULL,
    description TEXT,
    date_created DATETIME NOT NULL,
    category_id INTEGER NOT NULL,
    num_points DOUBLE,
    
    UNIQUE KEY (tittle)
);

USE QUIZ_DB;

DROP TABLE IF EXISTS taggings;

CREATE TABLE taggings (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tag_id INTEGER NOT NULL,
    quiz_id INTEGER NOT NULL,

    UNIQUE KEY (tag_id, quiz_id)
);

USE QUIZ_DB;

DROP TABLE IF EXISTS tags;

CREATE TABLE tags (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tag VARCHAR (55),

    UNIQUE KEY (tag)
);
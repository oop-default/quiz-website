USE QUIZ_DB;

DROP TABLE IF EXISTS taggings;

CREATE TABLE taggings (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tag_id INTEGER NOT NULL,
    quiz_id INTEGER NOT NULL,

    UNIQUE KEY (tag_id, quiz_id)
);
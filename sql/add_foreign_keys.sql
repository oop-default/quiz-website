ALTER TABLE quizzes
ADD FOREIGN KEY (author_id) 
        REFERENCES accounts (id)
        ON DELETE CASCADE;

ALTER TABLE quizzes
ADD FOREIGN KEY (category_id) 
    REFERENCES categories (id)
    ON DELETE CASCADE;

ALTER TABLE achievings
ADD FOREIGN KEY (achievement_id) 
    REFERENCES achievements (id)
    ON DELETE CASCADE;
    
ALTER TABLE achievings
ADD FOREIGN KEY (account_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;

ALTER TABLE answers
ADD FOREIGN KEY (question_id) 
    REFERENCES questions (id)
    ON DELETE CASCADE;

ALTER TABLE challenges
ADD FOREIGN KEY (quiz_id) 
    REFERENCES quizzes (id)
    ON DELETE CASCADE;

ALTER TABLE history
ADD FOREIGN KEY (account_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;
    
ALTER TABLE history
ADD FOREIGN KEY (quiz_id) 
    REFERENCES quizzes (id)
    ON DELETE CASCADE;

ALTER TABLE questions
ADD FOREIGN KEY (quiz_id) 
    REFERENCES quizzes (id)
    ON DELETE CASCADE;

ALTER TABLE taggings
ADD FOREIGN KEY (tag_id) 
    REFERENCES tags (id) 
    ON DELETE CASCADE;

ALTER TABLE taggings
ADD FOREIGN KEY (quiz_id) 
    REFERENCES quizzes (id)
    ON DELETE CASCADE;

ALTER TABLE friends
ADD FOREIGN KEY (first_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;

ALTER TABLE friends
ADD FOREIGN KEY (second_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;

ALTER TABLE news
ADD FOREIGN KEY (author_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;

ALTER TABLE notes
ADD FOREIGN KEY (sender_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;

ALTER TABLE notes
ADD FOREIGN KEY (receiver_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;

ALTER TABLE challenges
ADD FOREIGN KEY (sender_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;

ALTER TABLE challenges
ADD FOREIGN KEY (receiver_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;

ALTER TABLE friend_requests
ADD FOREIGN KEY (sender_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;

ALTER TABLE friend_requests
ADD FOREIGN KEY (receiver_id) 
    REFERENCES accounts (id)
    ON DELETE CASCADE;
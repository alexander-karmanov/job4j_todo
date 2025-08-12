ALTER TABLE tasks ADD COLUMN priority_id int NOT NULL;
ALTER TABLE tasks ADD CONSTRAINT fk_priority FOREIGN KEY
(priority_id) REFERENCES priorities(id);